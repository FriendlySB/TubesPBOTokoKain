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
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Main Menu Admin");

        JPanel panel = new JPanel();
        panel.setSize(600, 500);
        panel.setLayout(null);
        panel.setVisible(true);

        JLabel judul = new JLabel("" , SwingConstants.CENTER);
        if(admin.getTipeAdmin() == TipeAdmin.OWNER){
            judul.setText("Selamat Datang di Toko Kain, Owner");
        } else {
            judul.setText("Selamat Datang di Toko Kain, Admin " + admin.getUsername());
        }
        judul.setBounds(160, 20, 300, 50);

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

        JButton menuEditProgressPesanan = new JButton("Edit Status Pesanan");
        menuEditProgressPesanan.setBounds(150, 220, 300, 50);

        JButton menuLihatTransaksi = new JButton("Lihat Riwayat Transaksi Toko");
        menuLihatTransaksi.setBounds(150, 280, 300, 50);
        menuLihatTransaksi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuLihatRiwayatTransaksiAdmin();
            }
        });

        JButton menuLogout = new JButton("Log Out");
        menuLogout.setBounds(150, 340, 300, 50);
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
        mb.add(Box.createGlue());
        mb.add(addAdmin);
        mb.add(pendapatanToko);

        if(admin.getTipeAdmin() == TipeAdmin.OWNER){
            addAdmin.setVisible(true);
            pendapatanToko.setVisible(true);
        }
        
        panel.add(judul);
        panel.add(menuInputKain);
        panel.add(menuEditKain);
        panel.add(menuEditProgressPesanan);
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
