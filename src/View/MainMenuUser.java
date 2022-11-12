package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;
import Model.Customer;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuUser {

    public MainMenuUser(Customer customer) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Main Menu User");

        JPanel panel = new JPanel();
        panel.setSize(600, 400);
        panel.setLayout(null);
        panel.setVisible(true);

        JLabel judul = new JLabel("Selamat Datang di Toko Kain, "+ customer.getUsername());
        judul.setBounds(180, 20, 300, 50);

        JButton menuBeliKain = new JButton("Beli Kain");
        menuBeliKain.setBounds(150, 100, 300, 50);
        menuBeliKain.addActionListener(e -> {
            new MenuBeliKain();
        });

        JButton menuCekTransaksi = new JButton("Transaksi");
        menuCekTransaksi.setBounds(150, 160, 300, 50);

        JButton menuLihatProfile = new JButton("Profile");
        menuLihatProfile.setBounds(150, 220, 300, 50);
        menuLihatProfile.addActionListener(e -> {
            new MenuLihatProfile(customer);
        });

        JButton menuLogout = new JButton("Log Out");
        menuLogout.setBounds(150, 280, 300, 50);

        JMenuBar mb = new JMenuBar();
        JButton menuCart = new JButton("Cart");
        mb.add(Box.createGlue());
        mb.add(menuCart);

        panel.add(judul);
        panel.add(menuBeliKain);
        panel.add(menuCekTransaksi);
        panel.add(menuLihatProfile);
        panel.add(menuLogout);
        frame.setJMenuBar(mb);
        frame.add(panel);
    }
}
