/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CurrentUser;
import Model.Customer;
import Model.Keranjang;
import Model.TipeBayar;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lenovo
 */
public class MenuPilihMetodePembayaran {

    Customer curCust = (Customer) CurrentUser.getInstance().getUser();

    public MenuPilihMetodePembayaran(ArrayList<Keranjang> listKeranjangDipilih,int kurir) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setTitle("Pilih Metode Pembayaran");
        JLabel labelTipeBayar = new JLabel("Pilih Metode Pembayaran");
        labelTipeBayar.setBounds(10, 30, 150, 30);
        JComboBox comboTipeBayar = new JComboBox();
        comboTipeBayar.setBounds(160, 30, 200, 30);
        if (kurir == 3) {
            comboTipeBayar.addItem(TipeBayar.COD);
        } else {
            comboTipeBayar.addItem(TipeBayar.CASH);
            comboTipeBayar.addItem(TipeBayar.GOPAY);
            comboTipeBayar.addItem(TipeBayar.OVO);
            comboTipeBayar.addItem(TipeBayar.TRANSFER);
        }

        frame.setVisible(true);
    }
}
