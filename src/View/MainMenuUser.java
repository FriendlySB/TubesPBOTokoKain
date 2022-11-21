package View;

import Model.CurrentUser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import Model.Customer;

public class MainMenuUser {

    public MainMenuUser() {
        Customer customer = (Customer) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Main Menu User");

        String pathLogo = "logo_toko.png";
        ImageIcon iconFoto = new ImageIcon(new ImageIcon(pathLogo).getImage().getScaledInstance(300, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(iconFoto);
        logo.setBounds(150, 10, 300, 50);
        frame.add(logo);

        JPanel panel = new JPanel();
        panel.setSize(600, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        JLabel judul = new JLabel("Selamat Datang di Toko Kain XYZ, " + customer.getUsername());
        judul.setBounds(180, 60, 300, 50);

        JButton menuBeliKain = new JButton("Beli Kain");
        menuBeliKain.setBounds(150, 100, 300, 50);
        menuBeliKain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuBeliKain();
            }
        });

        JButton menuCekTransaksi = new JButton("Riwayat Transaksi");
        menuCekTransaksi.setBounds(150, 160, 300, 50);
        menuCekTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Back ke main menu jika belum ada transaksi
                if (customer.getTransaksi().size() < 1) {
                    JOptionPane.showMessageDialog(null, "Riwayat transaksi Anda masih kosong!",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    frame.dispose();
                    new MenuRiwayatTransaksiUser();
                }
            }
        });

        JButton menuLihatProfile = new JButton("Profile");
        menuLihatProfile.setBounds(150, 220, 300, 50);
        menuLihatProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuLihatProfile();
            }
        });

        JButton menuChat = new JButton("Chat");
        menuChat.setBounds(150, 280, 300, 50);
        menuChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuChat(customer.getId_user());
            }
        });

        JButton logout = new JButton("Log Out");
        logout.setBounds(150, 340, 300, 50);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi kami",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new MenuLogin();
            }
        });

        JMenuBar mb = new JMenuBar();
        JButton menuCart = new JButton("Cart");
        menuCart.addActionListener(e -> {
            if (customer.getKeranjang().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Keranjang Kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            } else {
                frame.dispose();
                new MenuLihatKeranjang();
            }
        });
        mb.add(Box.createGlue());
        mb.add(menuCart);

        panel.add(judul);
        panel.add(menuBeliKain);
        panel.add(menuCekTransaksi);
        panel.add(menuLihatProfile);
        panel.add(menuChat);
        panel.add(logout);
        frame.setJMenuBar(mb);
        frame.add(panel);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi kami",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new MenuLogin();
            }
        });
    }
}
