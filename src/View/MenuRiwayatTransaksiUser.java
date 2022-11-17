package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Model.CurrentUser;
import Model.User;
import Model.Transaksi;
import Controller.Sql;
import Controller.Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MenuRiwayatTransaksiUser {
    
    Sql sql = new Sql();
    Controller controller = new Controller();
    
    public MenuRiwayatTransaksiUser() {
        User curUser = CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(1000,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Menu Riwayat Transaksi");
        
        //Tabel Transaksi
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
        
        ArrayList<Transaksi>listTransaksi = new ArrayList<>(sql.getSQLListTransaksi(curUser.getId_user()));
        
        //Back ke main menu jika belum ada transaksi
        if(listTransaksi.size() < 1){
            JOptionPane.showMessageDialog(null, "Riwayat transaksi Anda masih kosong!", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
            frame.dispose();
        }
        for(int i = listTransaksi.size() - 1; i >= 0; i--){
            tableModel.addRow(controller.createIsiTableTransaksi(listTransaksi.get(i)));
        }
        JMenuBar mb = new JMenuBar();  
        JButton menuCart = new JButton("Cart");
        JButton mainMenu = new JButton("Main Menu");
        JButton lihatDetail = new JButton("Lihat Detail");
        
        lihatDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(tableTransaksi.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Mohon mengklik transaksi yang akan dilihat detailnya", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id_transaksi = (int)tableTransaksi.getValueAt(tableTransaksi.getSelectedRow(), 0);
                    new MenuLihatDetailTransaksi(id_transaksi);
                }
            }
        });
        mb.add(lihatDetail);
        mb.add(Box.createGlue());
        mb.add(menuCart);
        mb.add(mainMenu);
        
        frame.add(scrollPane);
        frame.setJMenuBar(mb);
    }
    
    public static void main(String[] args) {
        new MenuRiwayatTransaksiUser();
    }
    
}
