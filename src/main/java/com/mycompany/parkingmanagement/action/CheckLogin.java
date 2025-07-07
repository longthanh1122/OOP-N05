/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
<<<<<<<< HEAD:src/main/java/com/mycompany/quanlybaiguixe/action/CheckLogin.java
package com.mycompany.quanlybaiguixe.action;

import com.mycompany.quanlybaiguixe.entity.User;
========
package com.mycompany.parkingmanagement.action;

import com.mycompany.parkingmanagement.entity.User;
>>>>>>>> 4a59f0f273372d0b334d10346bb8c5d5e6b5e109:src/main/java/com/mycompany/parkingmanagement/action/CheckLogin.java

/**
 *
 * @author PC
 */
public class CheckLogin {
    public boolean checkUser(User user) {
        if (user != null) {
            if ("admin".equals(user.getUserName()) 
                    && "admin".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
