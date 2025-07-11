package com.mycompany.parkingmanagement.controller;

import com.mycompany.parkingmanagement.action.VehicleManager;
import com.mycompany.parkingmanagement.entity.Vehicle;
import com.mycompany.parkingmanagement.view.LoginView;
import com.mycompany.parkingmanagement.view.MainView;
import com.mycompany.parkingmanagement.view.ParkingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ParkingSlotController {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private VehicleManager vehicleManager;
    private ParkingView parkingView;
    private LoginView loginView;
    private MainView mainView;

    public ParkingSlotController(ParkingSlotView view) {
        this(view);
    }

    public ParkingSlotController(ParkingSlotView view) {
        this(view);
    }

    public ParkingSlotController(ParkingSlotView view) {
        this.parkingView = view;
        this.vehicleManager = new VehicleManager();
        view.addAddVehicleListener(new AddVehicleListener());
        view.addEditVehicleListener(new EditVehicleListener());
        view.addClearListener(new ClearVehicleListener());
        view.addDeleteVehicleListener(new DeleteVehicleListener());
        view.addListVehicleSelectionListener(new VehicleSelectionListener());
        view.addSortByLicenseListener(new SortVehicleLicenseListener());
        view.addSortByTypeListener(new SortVehicleTypeListener());
        view.addSearchListener(new SearchVehicleListener());
        view.addCancelSearchListener(new CancelSearchListener());
        view.addImageVehicleListener(new ImageVehicleListener());
        view.addUndoListener(new UndoListener());
        view.addStatisticTypeListener(new StatisticVehicleTypeListener());
    }

    ParkingSlotController(ParkingSlotView parkingSlotView) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    ParkingSlotController(com.mycompany.parkingmanagement.view.ParkingSlotView parkingSlotView) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void showParkingView() {
        List<Vehicle> vehicles = vehicleManager.getVehicleList();
        parkingView.setVisible(true);
        parkingView.showVehicleList(vehicles);
        parkingView.showVehicleCount(vehicles);
    }

    void showParkingSlotView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    class AddVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = parkingView.getVehicleInfo();
            if (vehicle != null) {
                vehicleManager.add(vehicle);
                parkingView.showVehicle(vehicle);
                parkingView.showVehicleList(vehicleManager.getVehicleList());
                parkingView.showVehicleCount(vehicleManager.getVehicleList());
                parkingView.showMessage("Thêm phương tiện thành công!");
            }
        }
    }

    class EditVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = parkingView.getVehicleInfo();
            if (vehicle != null) {
                vehicleManager.edit(vehicle);
                parkingView.showVehicle(vehicle);
                parkingView.showVehicleList(vehicleManager.getVehicleList());
                parkingView.showVehicleCount(vehicleManager.getVehicleList());
                parkingView.showMessage("Cập nhật phương tiện thành công!");
            }
        }
    }

    class DeleteVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Vehicle vehicle = parkingView.getVehicleInfo();
            if (vehicle != null) {
                vehicleManager.delete(vehicle);
                parkingView.clearVehicleInfo();
                parkingView.showVehicleList(vehicleManager.getVehicleList());
                parkingView.showVehicleCount(vehicleManager.getVehicleList());
                parkingView.showMessage("Xóa phương tiện thành công!");
            }
        }
    }

    class ClearVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingView.clearVehicleInfo();
        }
    }

    class SortVehicleLicenseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleManager.sortByLicense();
            parkingView.showVehicleList(vehicleManager.getVehicleList());
        }
    }

    class SortVehicleTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleManager.sortByType();
            parkingView.showVehicleList(vehicleManager.getVehicleList());
        }
    }

    class SearchVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String query = parkingView.getSearchQuery();
            List<Vehicle> results = vehicleManager.search(query);
            if (!results.isEmpty()) {
                parkingView.showVehicleList(results);
            } else {
                parkingView.showMessage("Không tìm thấy phương tiện phù hợp.");
            }
        }
    }

    class CancelSearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingView.showVehicleList(vehicleManager.getVehicleList());
            parkingView.cancelSearch();
        }
    }

    class ImageVehicleListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingView.selectVehicleImage();
        }
    }

    class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            parkingView.setVisible(false);
        }
    }

    class VehicleSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            parkingView.fillVehicleFromSelectedRow();
        }
    }

    class StatisticVehicleTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            parkingView.displayStatisticView();
            parkingView.showStatisticByType(vehicleManager.getVehicleList());
        }
    }
} 
