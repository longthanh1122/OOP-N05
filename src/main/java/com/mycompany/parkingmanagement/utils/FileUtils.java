package com.mycompany.parkingmanagement.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Tiện ích hỗ trợ đọc và ghi đối tượng Java từ/ra file XML bằng JAXB.
 */
public class FileUtils {

    /**
     * Ghi một đối tượng bất kỳ vào file XML.
     *
     * @param <T>      Kiểu dữ liệu của đối tượng
     * @param fileName Đường dẫn tới file XML
     * @param object   Đối tượng cần ghi
     */
    public static <T> void writeXMLtoFile(String fileName, T object) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, new File(fileName));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi ghi file XML: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * Đọc file XML và chuyển thành đối tượng kiểu clazz.
     *
     * @param <T>      Kiểu dữ liệu mong muốn
     * @param fileName Đường dẫn tới file XML
     * @param clazz    Lớp của kiểu dữ liệu mong muốn
     * @return Đối tượng đã được đọc, hoặc null nếu lỗi
     */
    public static <T> T readXMLFile(String fileName, Class<T> clazz) {
        try {
            File xmlFile = new File(fileName);
            if (!xmlFile.exists()) {
                System.err.println("File không tồn tại: " + fileName);
                return null;
            }
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return clazz.cast(unmarshaller.unmarshal(xmlFile));
        } catch (JAXBException e) {
            System.err.println("Lỗi khi đọc file XML: " + fileName);
            e.printStackTrace();
        }
        return null;
    }
}
