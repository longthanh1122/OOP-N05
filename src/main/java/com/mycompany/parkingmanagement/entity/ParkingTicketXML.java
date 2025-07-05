package com.mycompany.parkingmanagement.entity;

import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SpecialPersons")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParkingTicketXML {
    private static final String FILE_PATH = "parking_tickets.xml";

    public static void writeToXML(List<ParkingTicket> tickets) {
        XStream xstream = new XStream();
        xstream.alias("ticket", ParkingTicket.class);
        xstream.alias("tickets", List.class);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            xstream.toXML(tickets, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ParkingTicket> readFromXML() {
        XStream xstream = new XStream();
        xstream.alias("ticket", ParkingTicket.class);
        xstream.alias("tickets", List.class);

        try (FileReader reader = new FileReader(FILE_PATH)) {
            return (List<ParkingTicket>) xstream.fromXML(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
