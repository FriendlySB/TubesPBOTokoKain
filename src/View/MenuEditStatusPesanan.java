/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Controller.Sql;
import Model.Transaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author asus
 */
public class MenuEditStatusPesanan {
    
    Sql sql = new Sql();
    Controller controller = new Controller();
    ArrayList<Transaksi> listTransaksi = new ArrayList<>(sql.getAllTransaksi());
    
    public MenuEditStatusPesanan(int id_transaksi){
        JFrame f = new JFrame("Menu Edit Status Kain");
        f.setSize(500, 200);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        JLabel status = new JLabel("Silahkan pilih status yang ingin diupdate");
        status.setBounds(20, 20, 300, 25);
        f.add(status);
        
        JRadioButton dibuat = new JRadioButton("Dibuat");
        dibuat.setBounds(20, 50, 75, 25);
        f.add(dibuat);
        
        JRadioButton dikemas = new JRadioButton("Dikemas");
        dikemas.setBounds(105, 50, 75, 25);
        f.add(dikemas);
        
        JRadioButton dikirim = new JRadioButton("Dikirim");
        dikirim.setBounds(190, 50, 75, 25);
        f.add(dikirim);
        
        JRadioButton selesai = new JRadioButton("Selesai");
        selesai.setBounds(275, 50, 75, 25);
        f.add(selesai);
        
        JButton update = new JButton("Update");
        update.setBounds(175, 100, 80, 40);
        f.add(update);
        
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String stat= "";
                
                if(dikemas.isSelected()){
                    stat = "DIKEMAS";
                } else if(dibuat.isSelected()){
                    stat = "DIBUAT";
                } else if(dikirim.isSelected()){
                    stat = "DIKIRIM";
                } else {
                    stat = "SELESAI";
                } 
               
                
                if(stat.equals("")) {
                     JOptionPane.showMessageDialog(null, "Mohon untuk memilih status pesanannya!", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    Sql sql = new Sql();
                    sql.updateStatusTransaksi(stat, id_transaksi);
                    f.dispose();
                    new MenuLihatRiwayatTransaksiAdmin();
                    JOptionPane.showMessageDialog(null, "Update Status Pesanan Sukses", 
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });       
        f.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });      
    }
    
    public static void main(String args[]) {

    }
}
