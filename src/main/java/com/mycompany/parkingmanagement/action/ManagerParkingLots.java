/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingmanagement.action;

import com.mycompany.parkingmanagement.entity.VehicleXML;
import com.mycompany.parkingmanagement.entity.VehicleList;
import com.mycompany.parkingmanagement.utils.FileUtils;
import com.mycompany.parkingmanagement.view.ResidentView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ManagerParkingLots 
{
    private static final String PARKING_FILE_NAME = "parking.xml";
    private List<VehicleList> listParkingLots;
    private ResidentView residentView;

    public ManagerParkingLots()
    {
        this.listParkingLots = readParkingLotList();
        if (listParkingLots == null) {
            listParkingLots = new ArrayList<VehicleList>();
        }
    }

    public List<VehicleList> readParkingLotList() 
    {
        List<VehicleList> list = new ArrayList<VehicleList>();
        VehicleXML residentXML = (VehicleXML) FileUtils.readXMLFile(PARKING_FILE_NAME, VehicleXML.class);
        if (residentXML != null) 
        {
            list = residentXML.getResidents();
        }
        return list;
    }

    public void writeParkingLotList(List<VehicleList> parkingLots) 
    {
        VehicleXML residentXML = new VehicleXML();
        residentXML.setResidents(parkingLots);
        FileUtils.writeXMLtoFile(PARKING_FILE_NAME, residentXML);
    }

    public List<VehicleList> searchByName(String search){
        List<VehicleList> temp = new ArrayList<VehicleList>();
        for(VehicleList lot : listParkingLots){
            if(lot.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(lot);
            }
        }
        return temp;
    }

    public List<VehicleList> searchByAddress(String search){
        List<VehicleList> temp = new ArrayList<VehicleList>();
        for(VehicleList lot : listParkingLots){
            if(lot.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(lot);
            }
        }
        return temp;
    }

    public List<VehicleList> searchByIDFamily(String search){
        List<VehicleList> temp = new ArrayList<VehicleList>();
        for(VehicleList lot : listParkingLots){
            if(lot.getIDFamily().contains(search)){
                temp.add(lot);
            }
        }
        return temp;
    }

    public List<VehicleList> searchByYear(String year) {
        List<VehicleList> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (VehicleList lot : listParkingLots) {
            String lotYearStr = yearFormat.format(lot.getBirthday());
            if (lotYearStr.equals(year)) {
                temp.add(lot);
            }
        }

        return temp;
    }

    public void add(VehicleList lot) 
    {
        int max = 0;
        for (VehicleList existing : listParkingLots) {
            if(existing.getId() > max) max = existing.getId();
        }
        lot.setId(max + 1);
        listParkingLots.add(lot);
        writeParkingLotList(listParkingLots);
    }

    public boolean isCMTUnique(VehicleList lot) {
        String cmt = lot.getCMT();
        for (VehicleList existing : listParkingLots) {
            if (existing.getCMT().equals(cmt)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHouseholdUnique(VehicleList lot) {
        String IDFamily = lot.getIDFamily();
        String role = lot.getRole();
        for (VehicleList existing : listParkingLots) {
            if ("Chủ hộ".equals(role) && existing.getIDFamily().equals(IDFamily) && existing.getRole().equals(role)) {
                return false;
            }
        }
        return true;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(residentView, message);
    }

    public void edit(VehicleList lot) throws ParseException 
    {
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        int size = listParkingLots.size();
        for (int i = 0; i < size; i++) 
        {
            if (listParkingLots.get(i).getId() == lot.getId()) 
            {
                listParkingLots.get(i).setIDFamily(lot.getIDFamily());
                listParkingLots.get(i).setName(lot.getName());
                listParkingLots.get(i).setRole(lot.getRole());
                listParkingLots.get(i).setBirthday(lot.getBirthday());
                listParkingLots.get(i).setAddress(lot.getAddress());
                listParkingLots.get(i).setSex(lot.getSex());
                listParkingLots.get(i).setTypeCMT(lot.getTypeCMT());
                listParkingLots.get(i).setCMT(lot.getCMT());
                listParkingLots.get(i).setBirthPlace(lot.getBirthPlace());
                listParkingLots.get(i).setPhoneNumber(lot.getPhoneNumber());

                writeParkingLotList(listParkingLots);
                break;
            }
        }
    }

    public boolean delete(VehicleList lot) {
        boolean isFound = false;
        int size = listParkingLots.size();
        for (int i = 0; i < size; i++) {
            if (listParkingLots.get(i).getId() == lot.getId()) {
                listParkingLots.remove(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            for (int i = 0; i < listParkingLots.size(); i++) {
                if (listParkingLots.get(i).getId() > lot.getId()) {
                    listParkingLots.get(i).setId(listParkingLots.get(i).getId() - 1);
                }
            }
            writeParkingLotList(listParkingLots);
            return true;
        }
        return false;
    }

    public void sortByName() 
    {
        Collections.sort(listParkingLots, new Comparator<VehicleList>() {
            public int compare(VehicleList p1, VehicleList p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
    }

    public void sortByIDFamily() {
        Collections.sort(listParkingLots, new Comparator<VehicleList>() {
            public int compare(VehicleList p1, VehicleList p2) {
                return p1.getIDFamily().compareTo(p2.getIDFamily());
            }
        });
    }

    public void sortByID() 
    {
        Collections.sort(listParkingLots, new Comparator<VehicleList>() 
        {
            public int compare(VehicleList p1, VehicleList p2) 
            {
                return Integer.compare(p1.getId(), p2.getId());
            }
        });
    }

    public List<VehicleList> getListParkingLots() 
    {
        return this.listParkingLots;
    }
}
