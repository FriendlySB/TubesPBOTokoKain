/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.Transaksi;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author JoeMaxwell
 */
public class MenuLihatPendapatan {

    public MenuLihatPendapatan() {
        JFrame frame = new JFrame("Menu Lihat Pendapatan");
        frame.setSize(600, 475);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JLabel totalPendapatanTitle = new JLabel("Total Pendapatan:");
        totalPendapatanTitle.setBounds(150, 75, 300, 50);
        totalPendapatanTitle.setFont(new Font("Sans", Font.CENTER_BASELINE, 35));
        frame.add(totalPendapatanTitle);

        Sql db = new Sql();
        ArrayList<Transaksi> listTrans = db.getAllTransaksi();
        int sumPendapatan = 0;
        for (int i = 0; i < listTrans.size(); i++) {
            sumPendapatan += listTrans.get(i).getTotal_bayar();
        }

        JLabel totalPendapatan = new JLabel("Rp " + sumPendapatan);
        totalPendapatan.setBounds(175, 150, 300, 50);
        totalPendapatan.setFont(new Font("Sans", Font.CENTER_BASELINE, 32));
        frame.add(totalPendapatan);

        JLabel totalTransaksiTitle = new JLabel("Dari total: ");
        totalTransaksiTitle.setBounds(175, 250, 300, 50);
        totalTransaksiTitle.setFont(new Font("Sans", Font.CENTER_BASELINE, 20));
        frame.add(totalTransaksiTitle);

        JLabel totalTransaksi = new JLabel(listTrans.size() + " transaksi");
        totalTransaksi.setBounds(175, 300, 300, 50);
        totalTransaksi.setFont(new Font("Sans", Font.CENTER_BASELINE, 20));
        frame.add(totalTransaksi);

        JButton backMenu = new JButton("Back to Menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
            new MainMenuAdmin();
        });
        backMenu.setBounds(25, 375, 150, 40);
        frame.add(backMenu);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuUser();
            }
        });
    }

    public static void main(String[] args) {
        new MenuLihatPendapatan();

    }
}
