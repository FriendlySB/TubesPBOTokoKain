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
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenuAdmin{

    public MainMenuAdmin() {
        JFrame frame = new JFrame();
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Main Menu Admin");
        
        JPanel panel = new JPanel();
        panel.setSize(600, 400);
        panel.setLayout(null);
        panel.setVisible(true);
        
        JLabel judul = new JLabel("Selamat Datang di Toko Kain, Admin Anonymous!",SwingConstants.CENTER);
        judul.setBounds(160, 20, 300, 50);
        
        JButton menuInputKain = new JButton("Input Kain");
        menuInputKain.setBounds(150, 100, 300, 50);
        
        JButton menuEditKain = new JButton("Edit Data Kain");
        menuEditKain.setBounds(150, 160, 300, 50);
        
        JButton menuEditProgressPesanan = new JButton("Edit Progress Pesanan");
        menuEditProgressPesanan.setBounds(150, 220, 300, 50);
        
        JButton menuLihatTransaksi = new JButton("Lihat Riwayat Transaksi Toko");
        menuLihatTransaksi.setBounds(150, 280, 300, 50);
        
        JButton menuLogout = new JButton("Log Out");
        menuLogout.setBounds(150, 340, 300, 50);
        
        JMenuBar mb = new JMenuBar();  
        JButton menuOwner = new JButton("Owner Menu");
        mb.add(Box.createGlue());
        mb.add(menuOwner);
        
        panel.add(judul);
        panel.add(menuInputKain);
        panel.add(menuEditKain);
        panel.add(menuEditProgressPesanan);
        panel.add(menuLihatTransaksi);
        panel.add(menuLogout);
        frame.add(panel);
    }

    
    public static void main(String args[]) {
        new MainMenuAdmin();
    }
}
