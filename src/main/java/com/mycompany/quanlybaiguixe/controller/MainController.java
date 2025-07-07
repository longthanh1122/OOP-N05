/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlybaiguixe.controller;

import com.mycompany.quanlybaiguixe.view.MainView;
import com.mycompany.quanlybaiguixe.action.ManagerParkingLot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {

    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        // Gắn sự kiện nút "Quản lý bãi đỗ xe" (dùng nút Residents)
        this.mainView.addChooseResidentsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerParkingLot managerParkingLot = new ManagerParkingLot();
                managerParkingLot.setVisible(true);
                mainView.dispose();
            }
        });

        // Gắn sự kiện nút "Thoát" (dùng nút SpecialPerson)
        this.mainView.addChooseSpecialPersonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void showMainView() {
        mainView.setVisible(true);
    }
}
