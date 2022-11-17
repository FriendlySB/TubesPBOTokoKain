/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Controller.Sql;
import Model.Transaksi;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class MenuLihatRiwayatTransaksiAdmin {

    public MenuLihatRiwayatTransaksiAdmin() {
        JFrame f = new JFrame("Riwayat Transaksi Toko");
        f.setSize(700, 700);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable tableTransaksi = new JTable(tableModel);
        tableModel.addColumn("ID Transaksi");
        tableModel.addColumn("Waktu Transaksi");
        tableModel.addColumn("Tipe Bayar");
        tableModel.addColumn("Tipe Pengiriman");
        tableModel.addColumn("Progress");
        tableModel.addColumn("Total Bayar");
        JScrollPane scrollPane = new JScrollPane(tableTransaksi);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        Sql sql = new Sql();
        Controller controller = new Controller();
        ArrayList<Transaksi>listTransaksi = new ArrayList<>(sql.getAllTransaksi());
        
        for(int i = listTransaksi.size() - 1; i >= 0; i--){
            tableModel.addRow(controller.createIsiTableTransaksi(listTransaksi.get(i)));
        }
        
        f.add(scrollPane);
    }

    public static void main(String args[]) {
        new MenuLihatRiwayatTransaksiAdmin();

    }
}
