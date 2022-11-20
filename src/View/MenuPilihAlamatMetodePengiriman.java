/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CurrentUser;
import Model.Customer;
import Model.Keranjang;
import Model.TipePengiriman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setTitle("Input Alamat & Metode Pengiriman");
        //Pilih Alamat
        JLabel labelAlamat = new JLabel("Pilih Pengambilan Alamat");
        labelAlamat.setBounds(10, 30, 150, 30);
        String pilihanPengambilanAlamat[] = {"Ambil Dari Data Pengguna", "Tulis Alamat Berbeda"};
        JComboBox comboAlamat = new JComboBox(pilihanPengambilanAlamat);
        comboAlamat.setBounds(160, 30, 200, 30);
        JLabel labelAlamatTulis = new JLabel("Alamat :");
        labelAlamatTulis.setBounds(10, 70, 150, 30);
        labelAlamatTulis.setVisible(false);
        JTextField alamatTulis = new JTextField();
        alamatTulis.setBounds(160, 70, 200, 30);
        alamatTulis.setVisible(false);
        comboAlamat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Ambil Dari Data Pengguna".equals((String) comboAlamat.getSelectedItem())) {
                    labelAlamatTulis.setVisible(false);
                    alamatTulis.setVisible(false);
                } else if ("Tulis Alamat Berbeda".equals((String) comboAlamat.getSelectedItem())) {
                    labelAlamatTulis.setVisible(true);
                    alamatTulis.setVisible(true);
                }
            }
        });
        frame.add(labelAlamat);
        frame.add(comboAlamat);
        frame.add(labelAlamatTulis);
        frame.add(alamatTulis);
        //Pilih Metode pengiriman
        JLabel labelMetodePengiriman = new JLabel("Pilih Metode Pengiriman");
        labelMetodePengiriman.setBounds(10, 110, 150, 30);
        String pilihanMetodePengiriman[] = {"JNE", "GOJEK", "JNT", "PEGAWAI"};
        JComboBox comboMetodePengiriman = new JComboBox(pilihanMetodePengiriman);
        comboMetodePengiriman.setBounds(160, 110, 200, 30);
        frame.add(labelMetodePengiriman);
        frame.add(comboMetodePengiriman);

        JButton buttonBayar = new JButton("Bayar");
        buttonBayar.setBounds(10, 150, 200, 30);
        buttonBayar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Alamat = "";
                if (comboAlamat.getSelectedIndex() == 0) {
                    Alamat = curCust.getAlamat();
                    labelAlamatTulis.setVisible(false);
                    alamatTulis.setVisible(false);
                } else if (comboAlamat.getSelectedIndex() == 1) {
                    labelAlamatTulis.setVisible(true);
                    alamatTulis.setVisible(true);
                    Alamat = alamatTulis.getText();
                }
                int metodePengiriman = comboMetodePengiriman.getSelectedIndex();
                int konfirmasi = JOptionPane.showConfirmDialog(null, "Alamat : " + Alamat + "\nMetode Pengiriman : " + metodePengiriman, "Nota Pembelian", JOptionPane.OK_CANCEL_OPTION);
                if (konfirmasi == JOptionPane.CANCEL_OPTION) {
                    frame.dispose();
                    new MenuPilihAlamatMetodePengiriman(listKeranjangDipilih);
                } else {
                    frame.dispose();
                    new MenuPilihMetodePembayaran(listKeranjangDipilih,metodePengiriman);
                }
            }
        });
        frame.add(buttonBayar);
        frame.setVisible(true);

    }
}
