/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingmanagement.action;

import com.mycompany.parkingmanagement.entity.Vehicle;
import com.mycompany.parkingmanagement.entity.VehicleXML;
import com.mycompany.parkingmanagement.utils.FileUtils;

import java.text.Collator;
import java.util.*;

/**
 * Quản lý danh sách phương tiện gửi xe: thêm, sửa, xoá, tìm kiếm, sắp xếp.
 */
public class ManagerVehicles {
    private static final String VEHICLE_FILE_NAME = "Vehicles.xml";
    private List<Vehicle> listVehicles;

    public ManagerVehicles() {
        this.listVehicles = readListVehicles();
        if (listVehicles == null) {
            listVehicles = new ArrayList<>();
        }
    }

    public void writeListVehicles(List<Vehicle> vehicles) {
        VehicleXML vehicleXML = new VehicleXML();
        VehicleXML.setResidents(vehicles);
        FileUtils.writeXMLtoFile(VEHICLE_FILE_NAME, vehicleXML);
    }

    public List<Vehicle> readListVehicles() {
        VehicleXML vehicleXML = FileUtils.readXMLFile(VEHICLE_FILE_NAME, VehicleXML.class);
        if (vehicleXML != null) {
            return vehicleXML.getVehicles();
        }
        return new ArrayList<>();
    }

    public List<Vehicle> searchVehicleByPlate(String plate) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : listVehicles) {
            if (vehicle.getPlateNumber() != null &&
                vehicle.getPlateNumber().toLowerCase().contains(plate.toLowerCase())) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public List<Vehicle> searchVehicleByType(String type) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : listVehicles) {
            if (vehicle.getType() != null &&
                vehicle.getType().toLowerCase().contains(type.toLowerCase())) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public void addVehicle(Vehicle vehicle) {
        int maxId = listVehicles.stream()
                                .mapToInt(Vehicle::getId)
                                .max()
                                .orElse(0);
        vehicle.setId(maxId + 1);
        listVehicles.add(vehicle);
        writeListVehicles(listVehicles);
    }

    public void editVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < listVehicles.size(); i++) {
            if (listVehicles.get(i).getId() == updatedVehicle.getId()) {
                listVehicles.set(i, updatedVehicle);
                writeListVehicles(listVehicles);
                return;
            }
        }
    }

    public boolean deleteVehicle(Vehicle vehicle) {
        Iterator<Vehicle> iterator = listVehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle v = iterator.next();
            if (v.getId() == vehicle.getId()) {
                iterator.remove();
                writeListVehicles(listVehicles);
                return true;
            }
        }
        return false;
    }

    public void sortVehiclesByPlate() {
        listVehicles.sort(Comparator.comparing(
            v -> Optional.ofNullable(v.getPlateNumber()).orElse(""),
            Collator.getInstance(new Locale("vi", "VN"))
        ));
    }

    public void sortVehiclesByType() {
        listVehicles.sort(Comparator.comparing(
            v -> Optional.ofNullable(v.getType()).orElse("")
        ));
    }

    public void sortVehiclesByTimeIn() {
        listVehicles.sort(Comparator.comparing(
            v -> Optional.ofNullable(v.getTimeIn()).orElse("")
        ));
    }

    public List<Vehicle> getListVehicles() {
        return listVehicles;
    }

    public void setListVehicles(List<Vehicle> listVehicles) {
        this.listVehicles = listVehicles;
    }
}