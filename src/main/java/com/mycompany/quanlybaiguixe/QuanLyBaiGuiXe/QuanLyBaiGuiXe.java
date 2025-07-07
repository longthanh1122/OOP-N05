/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

<<<<<<<< HEAD:src/main/java/com/mycompany/quanlybaiguixe/QuanLyBaiGuiXe/QuanLyBaiGuiXe.java
package com.mycompany.quanlybaiguixe.QuanLyBaiGuiXe;

import com.mycompany.quanlybaiguixe.controller.LoginController;
import com.mycompany.quanlybaiguixe.view.LoginView;
========
package com.mycompany.parkingmanagement.ParkingManagement;

import com.mycompany.parkingmanagement.controller.LoginController;
import com.mycompany.parkingmanagement.view.LoginView;
>>>>>>>> 4a59f0f273372d0b334d10346bb8c5d5e6b5e109:src/main/java/com/mycompany/parkingmanagement/ParkingManagement/ParkingManagement.java
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
<<<<<<<< HEAD:src/main/java/com/mycompany/quanlybaiguixe/QuanLyBaiGuiXe/QuanLyBaiGuiXe.java
public class QuanLyBaiGuiXe 
========
public class ParkingManagement 
>>>>>>>> 4a59f0f273372d0b334d10346bb8c5d5e6b5e109:src/main/java/com/mycompany/parkingmanagement/ParkingManagement/ParkingManagement.java
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}
