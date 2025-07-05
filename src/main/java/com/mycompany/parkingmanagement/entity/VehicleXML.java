/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingmanagement.entity;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC   
 */
@XmlRootElement(name = "Residents")
@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleXML {
    private static final String FILE_PATH = "vehicles.xml";

    public static void writeToXML(List<Vehicle> vehicles) {
        XStream xstream = new XStream();
        xstream.alias("vehicle", Vehicle.class);
        xstream.alias("vehicles", List.class);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            xstream.toXML(vehicles, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Vehicle> readFromXML() {
        XStream xstream = new XStream();
        xstream.alias("vehicle", Vehicle.class);
        xstream.alias("vehicles", List.class);

        try (FileReader reader = new FileReader(FILE_PATH)) {
            return (List<Vehicle>) xstream.fromXML(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
