/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.KainCustom;
import java.awt.Font;
import java.util.ArrayList;
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
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Sql db = new Sql();
        ArrayList<KainCustom> listKainCustom = db.getAllKainCustom();
        if (listKainCustom == null) {
            frame.dispose();
            new MainMenuAdmin();
            JOptionPane.showMessageDialog(null, "Semua Kain Custom telah diberikan harga", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ArrayList<KainCustom> kainCustomZeroPrice = new ArrayList<>();
            for (int i = 0; i < listKainCustom.size(); i++) {
                kainCustomZeroPrice.add(listKainCustom.get(i));
            }
            String trueZeroKainCustom[] = new String[kainCustomZeroPrice.size()];
            for (int i = 0; i < trueZeroKainCustom.length; i++) {
                trueZeroKainCustom[i] = kainCustomZeroPrice.get(i).getBahan_kain_custom() + " " + kainCustomZeroPrice.get(i).getWarna_kain_custom() + " " + kainCustomZeroPrice.get(i).getMotif_kain_custom();
            }

            JLabel labelListKain = new JLabel("List Kain yang belum diberikan harga :");
            labelListKain.setBounds(50, 50, 225, 25);
            frame.add(labelListKain);

            JComboBox kainCustoms = new JComboBox(trueZeroKainCustom);
            kainCustoms.setBounds(50, 80, 250, 25);
            frame.add(kainCustoms);

            JLabel username = new JLabel("---");
            username.setBounds(155, 110, 75, 25);
            JTextField inputUsername = new JTextField();
            inputUsername.setBounds(155, 135, 175, 25);
            frame.add(username);
            frame.add(inputUsername);
        }

    }

    public static void main(String[] args) {
        new MenuEditHargaKainCustom();
    }
}
