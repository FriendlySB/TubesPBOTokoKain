/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Model.CurrentUser;
import Model.Customer;
import Model.Keranjang;
import Model.TipePengiriman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class MenuPilihAlamatMetodePengiriman {

    Customer curCust = (Customer) CurrentUser.getInstance().getUser();

    public MenuPilihAlamatMetodePengiriman(ArrayList<Keranjang> listKeranjangDipilih) {
        Controller controller = new Controller();
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Input Alamat & Metode Pengiriman");
        //Pilih Alamat
        JLabel labelBandung = new JLabel("Berada di");
        labelBandung.setBounds(10, 10, 150, 30);
        String pilihanBandung[] = {"Luar Bandung", "Bandung"};
        JComboBox comboBandung = new JComboBox(pilihanBandung);
        comboBandung.setBounds(160, 10, 150, 30);
        JLabel labelAlamat = new JLabel("Pilih Pengambilan Alamat");
        labelAlamat.setBounds(10, 50, 150, 30);
        JComboBox comboAlamat = new JComboBox();
        comboAlamat.setBounds(160, 50, 200, 30);
        comboAlamat.addItem("Ambil Dari Data Pengguna");
        comboAlamat.addItem("Tulis Alamat Berbeda");
        JLabel labelAlamatTulis = new JLabel("Alamat :");
        labelAlamatTulis.setBounds(10, 90, 150, 30);
        labelAlamatTulis.setVisible(false);
        JTextField alamatTulis = new JTextField();
        alamatTulis.setBounds(160, 90, 200, 30);
        alamatTulis.setVisible(false);
        JLabel labelAmbilDiToko = new JLabel("Ambil Di Toko");
        labelAmbilDiToko.setBounds(160, 90, 200, 30);
        labelAmbilDiToko.setVisible(false);
        
        comboAlamat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Ambil Dari Data Pengguna".equals((String) comboAlamat.getSelectedItem())) {
                    labelAlamatTulis.setVisible(false);
                    alamatTulis.setVisible(false);
                    labelAmbilDiToko.setVisible(false);
                } else if ("Tulis Alamat Berbeda".equals((String) comboAlamat.getSelectedItem())) {
                    labelAlamatTulis.setVisible(true);
                    alamatTulis.setVisible(true);
                } else if ("Ambil Di Toko".equals((String) comboAlamat.getSelectedItem())) {
                    labelAmbilDiToko.setVisible(true);
                }
            }
        });
        frame.add(labelBandung);
        frame.add(comboBandung);
        frame.add(labelAlamat);
        frame.add(comboAlamat);
        frame.add(labelAlamatTulis);
        frame.add(alamatTulis);
        frame.add(labelAmbilDiToko);
        //Pilih Metode pengiriman
        JLabel labelMetodePengiriman = new JLabel("Pilih Metode Pengiriman");
        labelMetodePengiriman.setBounds(10, 130, 150, 30);
        Controller control = new Controller();
        String tipePengiriman[] = {control.getTipePengiriman(TipePengiriman.JNE), control.getTipePengiriman(TipePengiriman.GOJEK), control.getTipePengiriman(TipePengiriman.JNT)};
        JComboBox comboMetodePengiriman = new JComboBox(tipePengiriman);
        comboMetodePengiriman.setBounds(160, 130, 200, 30);
        comboBandung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Bandung".equals((String) comboBandung.getSelectedItem())) {
                    comboMetodePengiriman.addItem(control.getTipePengiriman(TipePengiriman.PEGAWAI));
                    comboMetodePengiriman.addItem(control.getTipePengiriman(TipePengiriman.PICKUP));
                    comboAlamat.addItem("Ambil Di Toko");
                } else if ("Luar Bandung".equals((String) comboBandung.getSelectedItem())) {
                    comboMetodePengiriman.removeItem(control.getTipePengiriman(TipePengiriman.PEGAWAI));
                    comboMetodePengiriman.removeItem(control.getTipePengiriman(TipePengiriman.PICKUP));
                    comboAlamat.removeItem("Ambil Di Toko");
                }
            }
        });
        frame.add(labelMetodePengiriman);
        frame.add(comboMetodePengiriman);

        JButton buttonPilihMetodePembayaran = new JButton("Pilih Metode Pembayaran");
        buttonPilihMetodePembayaran.setBounds(10, 170, 200, 30);
        buttonPilihMetodePembayaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adaDi = (String) comboBandung.getSelectedItem();
                String Alamat = "";
                if (comboAlamat.getSelectedIndex() == 0) {
                    Alamat = curCust.getAlamat();
                    labelAlamatTulis.setVisible(false);
                    alamatTulis.setVisible(false);
                } else if (comboAlamat.getSelectedIndex() == 1) {
                    labelAlamatTulis.setVisible(true);
                    alamatTulis.setVisible(true);
                    Alamat = alamatTulis.getText();
                }else if (comboAlamat.getSelectedIndex() == 2){
                    Alamat="Ambil di toko";
                }
                int metodePengiriman = comboMetodePengiriman.getSelectedIndex();
                int totalBiayaPengiriman = controller.totalBiayaPengiriman(metodePengiriman, listKeranjangDipilih);

                int konfirmasi = JOptionPane.showConfirmDialog(null, "Berada di :" + adaDi
                        + "\nAlamat : " + Alamat
                        + "\nMetode Pengiriman : " + controller.getTipePengiriman(metodePengiriman)
                        + "\nTotal Biaya Pengiriman : " + totalBiayaPengiriman,
                        "Nota Pembelian",
                        JOptionPane.OK_CANCEL_OPTION);
                if (konfirmasi == JOptionPane.CANCEL_OPTION) {
                    frame.dispose();
                    new MenuPilihAlamatMetodePengiriman(listKeranjangDipilih);
                } else if (konfirmasi == JOptionPane.OK_OPTION) {
                    frame.dispose();
                    new MenuPilihMetodePembayaran(listKeranjangDipilih, metodePengiriman, Alamat, totalBiayaPengiriman);
                }
            }
        });
        frame.add(buttonPilihMetodePembayaran);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MenuLihatKeranjang();
            }
        });
    }
}
