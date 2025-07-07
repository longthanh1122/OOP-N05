<<<<<<<< HEAD:src/main/java/com/mycompany/quanlybaiguixe/entity/User.java
package com.mycompany.quanlybaiguixe.entity;
========
package com.mycompany.parkingmanagement.entity;
>>>>>>>> 4a59f0f273372d0b334d10346bb8c5d5e6b5e109:src/main/java/com/mycompany/parkingmanagement/entity/User.java

public class User {
    private String userName;
    private String password;

    public User() {
    }

    public User(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
