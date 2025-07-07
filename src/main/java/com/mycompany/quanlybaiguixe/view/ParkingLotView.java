/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlybaiguixe.view;
import com.mycompany.quanlybaiguixe.controller.ParkingLotController;
import com.mycompany.quanlybaiguixe.entity.ParkingLot;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 *
 * @author Admin
 */
public class ParkingLotView extends JFrame {
    private ParkingLotController controller = new ParkingLotController();
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTenBai, txtDiaChi, txtSoChoToiDa;
    private JButton btnAdd, btnUpdate, btnDelete, btnLoad;

    public ParkingLotView() {
        setTitle("Quản Lý Bãi Gửi Xe");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        loadData();
        setVisible(true);
    }

    private void initComponents() {
        JLabel lblTenBai = new JLabel("Tên bãi:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JLabel lblSoChoToiDa = new JLabel("Số chỗ tối đa:");

        txtTenBai = new JTextField(15);
        txtDiaChi = new JTextField(15);
        txtSoChoToiDa = new JTextField(5);

        btnAdd = new JButton("Thêm");
        btnUpdate = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnLoad = new JButton("Load");

        JPanel inputPanel = new JPanel();
        inputPanel.add(lblTenBai);
        inputPanel.add(txtTenBai);
        inputPanel.add(lblDiaChi);
        inputPanel.add(txtDiaChi);
        inputPanel.add(lblSoChoToiDa);
        inputPanel.add(txtSoChoToiDa);
        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);
        inputPanel.add(btnDelete);
        inputPanel.add(btnLoad);

        tableModel = new DefaultTableModel(new String[]{"ID", "Tên bãi", "Địa chỉ", "Số chỗ tối đa", "Số chỗ hiện có"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addParkingLot());
        btnUpdate.addActionListener(e -> updateParkingLot());
        btnDelete.addActionListener(e -> deleteParkingLot());
        btnLoad.addActionListener(e -> loadData());
    }

    private void addParkingLot() {
        try {
            String tenBai = txtTenBai.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            int soChoToiDa = Integer.parseInt(txtSoChoToiDa.getText().trim());
            if (tenBai.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin.");
                return;
            }
            ParkingLot lot = new ParkingLot(0, tenBai, diaChi, soChoToiDa, 0);
            controller.addParkingLot(lot);
            loadData();
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    private void updateParkingLot() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            try {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                String tenBai = txtTenBai.getText().trim();
                String diaChi = txtDiaChi.getText().trim();
                int soChoToiDa = Integer.parseInt(txtSoChoToiDa.getText().trim());
                int soChoHienCo = Integer.parseInt(tableModel.getValueAt(row, 4).toString());
                ParkingLot lot = new ParkingLot(id, tenBai, diaChi, soChoToiDa, soChoHienCo);
                controller.updateParkingLot(lot);
                loadData();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần sửa.");
        }
    }

    private void deleteParkingLot() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
                controller.deleteParkingLot(id);
                loadData();
                JOptionPane.showMessageDialog(this, "Xóa thành công.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần xóa.");
        }
    }

    private void loadData() {
        List<ParkingLot> lots = controller.getAllParkingLots();
        tableModel.setRowCount(0);
        for (ParkingLot lot : lots) {
            tableModel.addRow(new Object[]{lot.getId(), lot.getTenBai(), lot.getDiaChi(), lot.getSoChoToiDa(), lot.getSoChoHienCo()});
        }
    }

    public static void main(String[] args) {
        new ParkingLotView();
    }
}
