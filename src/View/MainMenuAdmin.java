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
import Model.Admin;
import Model.CurrentUser;
import Model.Customer;
import Model.TipeAdmin;
import Model.TipeUser;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuAdmin implements TipeAdmin{

    public MainMenuAdmin() {
        Admin admin = (Admin) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Main Menu Admin");
        
        String pathLogo = "logo_toko.png";
        ImageIcon iconFoto = new ImageIcon(new ImageIcon(pathLogo).getImage().getScaledInstance(300, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(iconFoto);
        logo.setBounds(150, 10, 300, 50);
        frame.add(logo);

        JPanel panel = new JPanel();
        panel.setSize(600, 450);
        panel.setLayout(null);
        panel.setVisible(true);

        JLabel judul = new JLabel("" , SwingConstants.CENTER);
        if(admin.getTipeAdmin() == TipeAdmin.OWNER){
            judul.setText("Selamat Datang di Toko Kain XYZ, Owner");
        } else {
            judul.setText("Selamat Datang di Toko Kain XYZ, Admin " + admin.getUsername());
        }
        judul.setBounds(160, 60, 300, 50);

        JButton menuInputKain = new JButton("Input Kain");
        menuInputKain.setBounds(150, 100, 300, 50);

        menuInputKain.addActionListener(e -> {
            frame.dispose();
            new MenuInputKain();
        });

        JButton menuEditKain = new JButton("Edit Data Kain");
        menuEditKain.setBounds(150, 160, 300, 50);

        menuEditKain.addActionListener(e -> {
            frame.dispose();
            new MenuEditKain();
        });

        JButton menuLihatTransaksi = new JButton("Lihat Riwayat Transaksi Toko");
        menuLihatTransaksi.setBounds(150, 220, 300, 50);
        menuLihatTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuLihatRiwayatTransaksiAdmin();
            }
        });

        JButton menuLogout = new JButton("Log Out");
        menuLogout.setBounds(150, 280, 300, 50);
        menuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi kami",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new MenuLogin();
            }
        });
        
        JMenuBar mb = new JMenuBar();
        JButton addAdmin = new JButton("Add Admin");
        addAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuTambahAdmin();
            }
        });
        addAdmin.setVisible(false);
        JButton pendapatanToko = new JButton("Pendapatan Toko");
        pendapatanToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuLihatPendapatan();
            }
        });
        pendapatanToko.setVisible(false);
        JButton listChat = new JButton("Chat");
        listChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuListChat();
            }
        });
        listChat.setVisible(false);
        mb.add(Box.createGlue());
        mb.add(addAdmin);
        mb.add(pendapatanToko);
        mb.add(listChat);
        
        if(admin.getTipeAdmin() == TipeAdmin.CS){
            listChat.setVisible(true);
        }
        if(admin.getTipeAdmin() == TipeAdmin.OWNER){
            listChat.setVisible(true);
            addAdmin.setVisible(true);
            pendapatanToko.setVisible(true);
        }
        
        panel.add(judul);
        panel.add(menuInputKain);
        panel.add(menuEditKain);
        panel.add(menuLihatTransaksi);
        panel.add(menuLogout);
        frame.add(panel);
        frame.setJMenuBar(mb);
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
