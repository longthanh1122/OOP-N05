package com.mycompany.quanlybaiguixe.action;

import com.mycompany.quanlybaiguixe.controller.ParkingLotController;
import com.mycompany.quanlybaiguixe.entity.ParkingLot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManagerParkingLot extends JFrame {

    private ParkingLotController controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTenBai, txtDiaChi, txtSoChoToiDa;
    private JButton btnThem, btnSua, btnXoa, btnLoad;

    public ManagerParkingLot() {
        controller = new ParkingLotController();
        initComponents();
        loadTableData();
    }

    private void initComponents() {
        setTitle("Quản Lý Bãi Gửi Xe");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTenBai = new JLabel("Tên bãi:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JLabel lblSoChoToiDa = new JLabel("Số chỗ tối đa:");

        txtTenBai = new JTextField(15);
        txtDiaChi = new JTextField(15);
        txtSoChoToiDa = new JTextField(5);

        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnLoad = new JButton("Tải lại");

        tableModel = new DefaultTableModel(new Object[]{"ID", "Tên bãi", "Địa chỉ", "Số chỗ tối đa", "Số chỗ hiện có"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel inputPanel = new JPanel();
        inputPanel.add(lblTenBai);
        inputPanel.add(txtTenBai);
        inputPanel.add(lblDiaChi);
        inputPanel.add(txtDiaChi);
        inputPanel.add(lblSoChoToiDa);
        inputPanel.add(txtSoChoToiDa);
        inputPanel.add(btnThem);
        inputPanel.add(btnSua);
        inputPanel.add(btnXoa);
        inputPanel.add(btnLoad);

        add(inputPanel, "North");
        add(scrollPane, "Center");

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAdd();
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEdit();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDelete();
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTableData();
            }
        });
    }

    private void loadTableData() {
        List<ParkingLot> lots = controller.getAllParkingLots();
        tableModel.setRowCount(0);
        for (ParkingLot lot : lots) {
            tableModel.addRow(new Object[]{lot.getId(), lot.getTenBai(), lot.getDiaChi(), lot.getSoChoToiDa(), lot.getSoChoHienCo()});
        }
    }

    private void handleAdd() {
        try {
            String tenBai = txtTenBai.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String soChoToiDaStr = txtSoChoToiDa.getText().trim();

            if (tenBai.isEmpty() || diaChi.isEmpty() || soChoToiDaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
                return;
            }

            int soChoToiDa = Integer.parseInt(soChoToiDaStr);
            ParkingLot lot = new ParkingLot(0, tenBai, diaChi, soChoToiDa, 0);
            controller.addParkingLot(lot);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Thêm bãi gửi xe thành công.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số chỗ tối đa phải là số nguyên.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    private void handleEdit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa.");
            return;
        }

        try {
            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            String tenBai = txtTenBai.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String soChoToiDaStr = txtSoChoToiDa.getText().trim();

            if (tenBai.isEmpty() || diaChi.isEmpty() || soChoToiDaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin để sửa.");
                return;
            }

            int soChoToiDa = Integer.parseInt(soChoToiDaStr);
            int soChoHienCo = Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString());
            ParkingLot lot = new ParkingLot(id, tenBai, diaChi, soChoToiDa, soChoHienCo);
            controller.updateParkingLot(lot);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số chỗ tối đa phải là số nguyên.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }

    private void handleDelete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            controller.deleteParkingLot(id);
            loadTableData();
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }
}