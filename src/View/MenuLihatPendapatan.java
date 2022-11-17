/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.Transaksi;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author JoeMaxwell
 */
public class MenuLihatPendapatan {
    
    public MenuLihatPendapatan (){
        JFrame frame = new JFrame("Register");
        frame.setSize(600, 475);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        JLabel totalPendapatanTitle = new JLabel("Total Pendapatan:");
        totalPendapatanTitle.setBounds(150, 75, 300, 50);
        totalPendapatanTitle.setFont(new Font("Sans", Font.CENTER_BASELINE, 35));
        frame.add(totalPendapatanTitle);
               
        JLabel totalPendapatan = new JLabel("Rp 120000");
        totalPendapatan.setBounds(175, 150, 300, 50);
        totalPendapatan.setFont(new Font("Sans", Font.CENTER_BASELINE, 32));
        frame.add(totalPendapatan);     
         
        JLabel totalTransaksiTitle = new JLabel("Dari total: ");
        totalTransaksiTitle.setBounds(175, 250, 300, 50);
        totalTransaksiTitle.setFont(new Font("Sans", Font.CENTER_BASELINE, 20));
        frame.add(totalTransaksiTitle);  
        
        JLabel totalTransaksi = new JLabel("12 transaksi");
        totalTransaksi.setBounds(175, 300, 300, 50);
        totalTransaksi.setFont(new Font("Sans", Font.CENTER_BASELINE, 20));
        frame.add(totalTransaksi);  
        
        JButton backMenu = new JButton("back to menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
        });
        backMenu.setBounds(25, 375, 150, 40);
        frame.add(backMenu);
    }
    
    public static void main(String[] args) {
        new MenuLihatPendapatan();
        
    }
}
