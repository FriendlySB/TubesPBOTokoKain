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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuLihatRiwayatTransaksiAdmin {

    public MenuLihatRiwayatTransaksiAdmin() {
        JFrame f = new JFrame("Riwayat Transaksi Toko");
        f.setSize(800, 700);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("ID Transaksi");
        tableModel.addColumn("Waktu Transaksi");
        tableModel.addColumn("Tipe Bayar");
        tableModel.addColumn("Tipe Pengiriman");
        tableModel.addColumn("Progress");
        tableModel.addColumn("Total Bayar");
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        Sql sql = new Sql();
        Controller controller = new Controller();
        ArrayList<Transaksi>listTransaksi = new ArrayList<>(sql.getAllTransaksi());
        
        for(int i = listTransaksi.size() - 1; i >= 0; i--){
            tableModel.addRow(controller.createIsiTableTransaksi(listTransaksi.get(i)));
        }
        JMenuBar mb = new JMenuBar();  
        JButton mainMenu = new JButton("Back to Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                new MainMenuAdmin();
            }
        });
        JButton lihatDetail = new JButton("Lihat Detail");
        lihatDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(table.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Mohon mengklik transaksi yang akan dilihat detailnya", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id_transaksi = (int)table.getValueAt(table.getSelectedRow(), 0);
                    new MenuLihatDetailTransaksi(id_transaksi);
                }
            }
        });
        
        
        JButton editStatus = new JButton("Edit Status Pesanan");
        editStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                if(table.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Mohon mengklik data yang ingin di ubah status pesanannya!", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id_transaksi = (int)table.getValueAt(table.getSelectedRow(), 0);
                    new MenuEditStatusPesanan(id_transaksi);
                    f.dispose();
                }
                
            }
        });
        
        mb.add(editStatus);
        mb.add(lihatDetail);
        mb.add(Box.createGlue());
        mb.add(mainMenu);
        f.setJMenuBar(mb);
        f.add(scrollPane);
        f.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });
        
        
    }

}
