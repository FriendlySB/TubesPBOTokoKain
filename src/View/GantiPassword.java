package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;

public class GantiPassword {

    public GantiPassword(Customer customer) {
        Sql con = new Sql();
        JFrame frame = new JFrame();
        frame.setTitle("Data Profil");
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labOld = new JLabel("Masukan password lama");
        labOld.setBounds(10, 20, 150, 25);
        frame.add(labOld);

        JTextField oldPass = new JTextField();
        oldPass.setBounds(160, 20, 100, 25);
        frame.add(oldPass);

        JLabel labNew = new JLabel("Masukan password baru");
        labNew.setBounds(10, 55, 150, 25);
        frame.add(labNew);

        JTextField newPass = new JTextField();
        newPass.setBounds(160, 55, 100, 25);
        frame.add(newPass);

        JButton submit = new JButton("ganti password");
        submit.setBounds(160, 85, 200, 25);
        frame.add(submit);

        submit.addActionListener(e -> {
            if (!customer.getPassword().equals(oldPass.getText())) {
                JOptionPane.showMessageDialog(null, "gagal mengganti password anda, password salah");
            } else if (customer.getPassword().equals(newPass.getText())) {
                JOptionPane.showMessageDialog(null, "gagal mengganti password anda, password baru tidak bisa sama dengan password lama");
            } else if (customer.getPassword().equals(oldPass.getText())) {
                con.updatePassword(oldPass.getText(), newPass.getText(), customer.getId_user());
                frame.dispose();
            }
        });
    }
}
