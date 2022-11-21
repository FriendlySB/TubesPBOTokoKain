package View;

import Controller.Controller;
import Controller.Sql;
import Model.CurrentUser;
import Model.Kain;
import Model.KainCustom;
import Model.Keranjang;
import Model.User;
import Model.KainToko;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MenuLihatKeranjang {

    public MenuLihatKeranjang() {
        Controller control = new Controller();
        Sql database = new Sql();
        User curUser = CurrentUser.getInstance().getUser();
        ArrayList<Keranjang> listKeranjang = database.getKeranjang(curUser.getId_user());
        ArrayList<JCheckBox> checkBoxKeranjang = new ArrayList<>();
        ArrayList<JButton> listButtonX = new ArrayList<>();
        if (listKeranjang.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Keranjang Kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            new MenuLihatKeranjang();
        }
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Lihat Keranjang");
        JLabel title = new JLabel("Daftar Kain Keranjang : ");
        title.setBounds(10, 5, 300, 30);
        frame.add(title);
        for (int i = 0; i < listKeranjang.size(); i++) {
            if (listKeranjang.get(i).getKain() instanceof KainCustom) {
                KainCustom curKain = (KainCustom) listKeranjang.get(i).getKain();
                JCheckBox curCheckbox = new JCheckBox(control.getNamaKainCustom(curKain.getId_kain()) + "(" + listKeranjang.get(i).getQuantity() + ")");
                if (control.cekHargaKainCustom(curKain)) {
                    curCheckbox.setEnabled(false);
                }
                checkBoxKeranjang.add(curCheckbox);
            } else if (listKeranjang.get(i).getKain() instanceof KainToko) {
                checkBoxKeranjang.add(new JCheckBox(control.getNamaKain(listKeranjang.get(i).getKain().getId_kain()) + "(" + listKeranjang.get(i).getQuantity() + ")"));
            }
            JButton buttonX = new JButton("x");
            buttonX.setForeground(Color.RED);
            buttonX.setContentAreaFilled(false);
            buttonX.setBorderPainted(false);
            listButtonX.add(buttonX);
        }
        int tempX = 10;
        int tempX2 = 420;
        int tempY = 40;
        for (int i = 0; i < checkBoxKeranjang.size(); i++) {
            frame.add(checkBoxKeranjang.get(i));
            checkBoxKeranjang.get(i).setBounds(tempX, tempY, 400, 30);
            frame.add(listButtonX.get(i));
            listButtonX.get(i).setBounds(tempX2, tempY, 50, 30);
            tempY += 35;
        }
        JButton buttonCheckout = new JButton("Checkout");
        buttonCheckout.setBounds(tempX, tempY, 100, 30);
        frame.add(buttonCheckout);
        buttonCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Keranjang> listKeranjangDipilih = new ArrayList<>();
                for (int i = 0; i < checkBoxKeranjang.size(); i++) {
                    if (checkBoxKeranjang.get(i).isSelected()) {
                        listKeranjangDipilih.add(listKeranjang.get(i));
                    }
                }
                frame.dispose();
                new MenuPilihAlamatMetodePengiriman(listKeranjangDipilih);
            }
        });
        frame.setVisible(true);
        for (int i = 0; i < listButtonX.size(); i++) {
            final int final_i = i;
            listButtonX.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean hasilHapus = database.deleteKainKeranjang(listKeranjang.get(final_i).getKain().getId_kain(), CurrentUser.getInstance().getUser().getId_user());
                    if (listKeranjang.get(final_i).getKain() instanceof KainCustom) {
                        database.deleteKain(listKeranjang.get(final_i).getKain().getId_kain());
                    }
                    if (hasilHapus) {
                        JOptionPane.showMessageDialog(null, "Kain Berhasil Dihapus Dari Keranjang", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        new MenuLihatKeranjang();
                    } else {
                        JOptionPane.showMessageDialog(null, "Kain Gagal Dihapus dari Keranjang", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuUser();
            }
        });
    }
}
