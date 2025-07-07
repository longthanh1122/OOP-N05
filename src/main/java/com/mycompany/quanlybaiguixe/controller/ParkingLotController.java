/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlybaiguixe.controller;

import com.mycompany.quanlybaiguixe.entity.ParkingLot;
import com.mycompany.quanlybaiguixe.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {

    private List<ParkingLot> parkingLots;

    public ParkingLotController() {
        parkingLots = FileUtils.readParkingLotsFromXML();
        if (parkingLots == null) {
            parkingLots = new ArrayList<>();
        }
    }

    public List<ParkingLot> getAllParkingLots() {
        return new ArrayList<>(parkingLots);
    }

    public void addParkingLot(ParkingLot lot) {
        lot.setId(generateNextId());
        parkingLots.add(lot);
        FileUtils.writeParkingLotsToXML(parkingLots);
    }

    public void updateParkingLot(ParkingLot updatedLot) {
        for (int i = 0; i < parkingLots.size(); i++) {
            ParkingLot lot = parkingLots.get(i);
            if (lot.getId() == updatedLot.getId()) {
                parkingLots.set(i, updatedLot);
                FileUtils.writeParkingLotsToXML(parkingLots);
                return;
            }
        }
        System.out.println("Không tìm thấy bãi đỗ xe với ID: " + updatedLot.getId());
    }

    public void deleteParkingLot(int id) {
        boolean removed = parkingLots.removeIf(lot -> lot.getId() == id);
        if (removed) {
            FileUtils.writeParkingLotsToXML(parkingLots);
        } else {
            System.out.println("Không tìm thấy bãi đỗ xe với ID: " + id);
        }
    }

    private int generateNextId() {
        int maxId = 0;
        for (ParkingLot lot : parkingLots) {
            if (lot.getId() > maxId) {
                maxId = lot.getId();
            }
        }
        return maxId + 1;
    }
}
