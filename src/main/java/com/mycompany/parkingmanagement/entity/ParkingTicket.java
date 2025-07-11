/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingmanagement.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ParkingTicket")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParkingTicket {
    private String ticketId;
    private String licensePlate;
    private LocalDateTime entryTime;
    private String vehicleType;

    public ParkingTicket() {
    }

    public ParkingTicket(String licensePlate, String vehicleType) {
        this.ticketId = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}
