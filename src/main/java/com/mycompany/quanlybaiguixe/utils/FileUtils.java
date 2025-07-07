package com.mycompany.quanlybaiguixe.utils;

import com.mycompany.quanlybaiguixe.entity.ParkingLot;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    private static final String FILE_NAME = "parkinglots.dat";

    public static void writeParkingLotsToXML(List<ParkingLot> parkingLots) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(parkingLots);
            System.out.println("Dữ liệu bãi đỗ đã được lưu thành công.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<ParkingLot> readParkingLotsFromXML() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<ParkingLot>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
