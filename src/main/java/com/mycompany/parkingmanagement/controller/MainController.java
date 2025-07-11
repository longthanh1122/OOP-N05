package com.mycompany.parkingmanagement.controller;

import com.mycompany.parkingmanagement.action.VehicleManager;
import com.mycompany.parkingmanagement.view.LoginView;
import com.mycompany.parkingmanagement.view.MainView;
import com.mycompany.parkingmanagement.view.ParkingSlotView;
import com.mycompany.parkingmanagement.view.VehicleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController 
{
    private LoginView loginView;
    private ParkingSlotView parkingSlotView;
    private VehicleView vehicleView;
    private MainView mainView;
    
    public MainController(MainView view)
    {
        this.mainView = view;
        view.addChooseParkingSlotListener(new ChooseParkingSlotListener());
        view.addChooseVehicleListener(new ChooseVehicleListener());
    }

    public void showMainView() 
    {
        mainView.setVisible(true);
    }

    // Bấm vào “Quản lý bãi đỗ xe”
    class ChooseParkingSlotListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            parkingSlotView = new ParkingSlotView();
            ParkingSlotController parkingSlotController = new ParkingSlotController(parkingSlotView);
            parkingSlotController.showParkingSlotView();
            mainView.setVisible(false);
        }
    }

    // Bấm vào “Quản lý phương tiện”
    class ChooseVehicleListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            vehicleView = new VehicleView();
            VehicleController vehicleController = new VehicleController(vehicleView);
            vehicleController.showVehicleView();
            mainView.setVisible(false);
        }
    }
}
