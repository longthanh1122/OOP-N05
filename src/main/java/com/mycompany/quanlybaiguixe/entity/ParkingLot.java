/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlybaiguixe.entity;

public class ParkingLot {
    private int id;
    private String tenBai;
    private String diaChi;
    private int soChoToiDa;
    private int soChoHienCo;

    public ParkingLot() {}

    public ParkingLot(int id, String tenBai, String diaChi, int soChoToiDa, int soChoHienCo) {
        this.id = id;
        this.tenBai = tenBai;
        this.diaChi = diaChi;
        this.soChoToiDa = soChoToiDa;
        this.soChoHienCo = soChoHienCo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoChoToiDa() {
        return soChoToiDa;
    }

    public void setSoChoToiDa(int soChoToiDa) {
        this.soChoToiDa = soChoToiDa;
    }

    public int getSoChoHienCo() {
        return soChoHienCo;
    }

    public void setSoChoHienCo(int soChoHienCo) {
        this.soChoHienCo = soChoHienCo;
    }

    @Override
    public String toString() {
        return "ParkingLot{" + "id=" + id + ", tenBai='" + tenBai + '\'' + ", diaChi='" + diaChi + '\'' + ", soChoToiDa=" + soChoToiDa + ", soChoHienCo=" + soChoHienCo + '}';
    }
}