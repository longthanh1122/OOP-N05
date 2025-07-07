/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<<< HEAD:src/main/java/com/mycompany/quanlybaiguixe/controller/LoginController.java
package com.mycompany.quanlybaiguixe.controller;

import com.mycompany.quanlybaiguixe.action.CheckLogin;
import com.mycompany.quanlybaiguixe.entity.User;
import com.mycompany.quanlybaiguixe.view.LoginView;
import com.mycompany.quanlybaiguixe.view.MainView;
========
package com.mycompany.parkingmanagement.controller;

import com.mycompany.parkingmanagement.action.CheckLogin;
import com.mycompany.parkingmanagement.entity.User;
import com.mycompany.parkingmanagement.view.LoginView;
import com.mycompany.parkingmanagement.view.MainView;
import com.mycompany.parkingmanagement.view.ManagerView;
>>>>>>>> 4a59f0f273372d0b334d10346bb8c5d5e6b5e109:src/main/java/com/mycompany/parkingmanagement/controller/LoginController.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author PC
 */
public class LoginController 
{
    private CheckLogin checkLogin;
    private LoginView loginView;
    private MainView mainView;
    
    public LoginController(LoginView view) 
    {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() 
    {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            User user = loginView.getUser();
            if (checkLogin.checkUser(user)) 
            {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                mainView = new MainView();
                MainController mainController = new MainController(mainView);
                mainController.showMainView();
                loginView.setVisible(false);
            } 
            else 
            {
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}
