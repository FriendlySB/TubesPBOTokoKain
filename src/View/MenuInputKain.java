package View;

import java.awt.*;
import javax.swing.*;
import Control.Sql;
import Model.*;

public class MenuInputKain {

    JButton backMenu = new JButton("Back to menu");

    public MenuInputKain() {
        backMenu.setBounds(10, 200, 150, 40);

        Sql con = new Sql();
        JFrame frame = new JFrame();
        JFrame subFrame = new JFrame();
        subFrame.setLayout(null);
        subFrame.setSize(500, 300);
        backMenu.addActionListener(b -> {
            frame.dispose();
            new MainMenuAdmin();
        });
        frame.setSize(700, 300);
        frame.setTitle("Menu Input Kain");
        frame.setLocationRelativeTo(null);
        subFrame.setLocationRelativeTo(null);
        JLabel salam1 = new JLabel("Apa yang mau ditambahkan");
        salam1.setBounds(250, 20, 200, 25);
        frame.add(salam1);
        JButton bBahan = new JButton("Bahan");
        bBahan.setBounds(20, 50, 200, 50);
        frame.add(bBahan);
        bBahan.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Bahan");
            JLabel labBahan = new JLabel("Masukan nama Bahan");
            JTextField inpBahan = new JTextField();
            labBahan.setBounds(10, 25, 200, 25);
            inpBahan.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga bahan");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labBahan);
            subFrame.add(inpBahan);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JButton add = new JButton("Tambah");
            add.setBounds(10, 150, 100, 25);
            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                BahanKain bahan = new BahanKain(0, inpBahan.getText(), harga);
                con.insertBahan(bahan);
            });
            subFrame.add(add);
            subFrame.add(backMenu);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
                new MainMenuAdmin();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        JButton bWarna = new JButton("Warna");
        bWarna.setBounds(240, 50, 200, 50);
        frame.add(bWarna);
        bWarna.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Warna");
            JLabel labWarna = new JLabel("Masukan nama Warna");
            JTextField inpWarna = new JTextField();
            labWarna.setBounds(10, 25, 200, 25);
            inpWarna.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga Warna");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labWarna);
            subFrame.add(inpWarna);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JButton add = new JButton("Tambah");
            add.setBounds(10, 200, 100, 25);
            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                WarnaKain warna = new WarnaKain(0, inpWarna.getText(), harga);
                con.insertWarna(warna);
            });
            subFrame.add(add);
            subFrame.add(backMenu);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
                new MainMenuAdmin();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        JButton bMotif = new JButton("Motif");
        bMotif.setBounds(460, 50, 200, 50);
        frame.add(bMotif);
        bMotif.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Motif");
            JLabel labMotif = new JLabel("Masukan nama Motif");
            JTextField inpMotif = new JTextField();
            labMotif.setBounds(10, 25, 200, 25);
            inpMotif.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga Motif");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labMotif);
            subFrame.add(inpMotif);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JButton add = new JButton("Tambah");
            add.setBounds(10, 200, 100, 25);
            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                MotifKain motif = new MotifKain(0, inpMotif.getText(), harga);
                con.insertMotif(motif);
            });
            subFrame.add(add);
            subFrame.add(backMenu);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
                new MainMenuAdmin();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(backMenu);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuInputKain();
    }
}
