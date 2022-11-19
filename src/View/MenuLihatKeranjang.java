package View;

import Controller.Controller;
import Controller.Sql;
import Model.CurrentUser;
import Model.Kain;
import Model.Keranjang;
import Model.User;
import Model.kain_toko;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuLihatKeranjang {

    public MenuLihatKeranjang(User curUser) {
        System.out.println("Halo");
        Controller control = new Controller();
        Sql database = new Sql();
        ArrayList<Keranjang> listKeranjang = database.getKeranjang(curUser.getId_user());
//        User curUser = CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setTitle("Lihat Keranjang");
        JLabel title = new JLabel("Daftar Kain Keranjang : ");
        
        frame.add(title);
        ArrayList<JPanel> listPanel = new ArrayList<>();
        ArrayList<JCheckBox> checkBoxKeranjang = new ArrayList<>();
        ArrayList<JButton> listButtonX = new ArrayList<>();
        for (int i = 0; i < listKeranjang.size(); i++) {
            if (listKeranjang.get(i).getId_kain().contains("CUSTOM-")) {
                checkBoxKeranjang.add(new JCheckBox(control.getNamaKainCustom(listKeranjang.get(i).getId_kain()) + "(" + listKeranjang.get(i).getQuantity() + ")"));
            } else {
                checkBoxKeranjang.add(new JCheckBox(control.getNamaKain(listKeranjang.get(i).getId_kain()) + "(" + listKeranjang.get(i).getQuantity() + ")"));
            }
            JButton buttonX = new JButton("x");
            buttonX.setForeground(Color.RED);
            buttonX.setOpaque(false);
            listButtonX.add(buttonX);
        }
        int tempX = 10;
        int tempY = 115;
        for (int i = 0; i < checkBoxKeranjang.size(); i++) {
            if (tempY > 205) {
                tempY = 115;
                tempX += 110;
            }
            listPanel.add(new JPanel());
            listPanel.get(i).add(checkBoxKeranjang.get(i));
            listPanel.get(i).add(listButtonX.get(i));
            listPanel.get(i).setBounds(tempX, tempY, 300, 30);
            frame.add(listPanel.get(i));
            tempY += 30;
        }
        for (int i = 0; i < listButtonX.size(); i++) {
            final int final_i = i;
            listButtonX.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    boolean hasilHapus =database.deleteKainKeranjang(listKeranjang.get(final_i).getId_kain(), CurrentUser.getInstance().getUser().getId_user());
                    if(hasilHapus){
                         JOptionPane.showMessageDialog(null, "Kain Berhasil Dihapus Dari Keranjang", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Kain Gagal Dihapus dari Keranjang", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Sql con = new Sql();
        ArrayList<User> daftarUser = con.getAllUsers();
        new MenuLihatKeranjang(daftarUser.get(0));
    }
}
