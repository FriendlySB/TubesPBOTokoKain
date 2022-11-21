/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.KainCustom;
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
 * @author JoeMaxwell
 */
public class MenuEditHargaKainCustom {

    public MenuEditHargaKainCustom() {
        JFrame frame = new JFrame("Edit Harga Kain Custom");
        frame.setSize(420, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Sql db = new Sql();
        ArrayList<KainCustom> listKainCustom = db.getAllKainCustom();
        ArrayList<KainCustom> kainCustomZeroPrice = new ArrayList<>();
        for (int i = 0; i < listKainCustom.size(); i++) {
            if (listKainCustom.get(i).getHarga_kain_custom() == 0) {
                kainCustomZeroPrice.add(listKainCustom.get(i));
            }
        }
        if (kainCustomZeroPrice.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua Kain Custom telah diberikan harga", "Info", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            new MenuEditKain();
        } else {
            String trueZeroKainCustom[] = new String[kainCustomZeroPrice.size()];
            for (int i = 0; i < trueZeroKainCustom.length; i++) {
                trueZeroKainCustom[i] = kainCustomZeroPrice.get(i).getId_kain() + " kain " + kainCustomZeroPrice.get(i).getBahan_kain_custom() + " " + kainCustomZeroPrice.get(i).getWarna_kain_custom() + " " + kainCustomZeroPrice.get(i).getMotif_kain_custom();
            }
            JLabel labelListKain = new JLabel("List Kain yang belum diberikan harga :");
            labelListKain.setBounds(65, 50, 225, 25);
            frame.add(labelListKain);

            JComboBox kainCustoms = new JComboBox(trueZeroKainCustom);
            kainCustoms.setBounds(65, 80, 275, 25);
            frame.add(kainCustoms);

            JLabel harga = new JLabel("Silahkan masukkan harga yang ingin diberikan : ");
            harga.setBounds(65, 150, 275, 25);
            JTextField inputHarga = new JTextField();
            inputHarga.setBounds(65, 175, 175, 25);
            frame.add(harga);
            frame.add(inputHarga);

            JButton back = new JButton("Back to menu");
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                    new MenuEditKain();
                }
            });
            back.setBounds(65, 245, 115, 35);
            frame.add(back);

            JButton setHarga = new JButton("Set");
            setHarga.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!"".equals(inputHarga.getText())) {
                        int index = kainCustoms.getSelectedIndex();
                        String idKain = kainCustomZeroPrice.get(index).getId_kain();
                        boolean hasilUpdate = db.updateHargaKainCustom(idKain, Integer.parseInt(inputHarga.getText()));
                        if (hasilUpdate) {
                            JOptionPane.showMessageDialog(null, "Harga berhasil di set", "Message", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            new MenuEditKain();
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal Set Harga", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Input Masih kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }

                }
            });
            setHarga.setBounds(220, 245, 115, 35);
            frame.add(setHarga);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    new MainMenuUser();
                }
            });
        }
    }

    public static void main(String[] args) {
        new MenuEditHargaKainCustom();
    }
}
