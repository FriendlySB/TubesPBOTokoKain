package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuGantiPassword {

    public MenuGantiPassword(Customer customer) {
        Sql con = new Sql();
        JFrame frame = new JFrame();
        frame.setTitle("Menu Ganti Password");
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel labOld = new JLabel("Masukkan password lama");
        labOld.setBounds(10, 20, 150, 25);
        frame.add(labOld);

        JPasswordField oldPass = new JPasswordField();
        oldPass.setBounds(180, 20, 100, 25);
        frame.add(oldPass);

        JLabel labNew = new JLabel("Masukkan password baru");
        labNew.setBounds(10, 55, 150, 25);
        frame.add(labNew);

        JPasswordField newPass = new JPasswordField();
        newPass.setBounds(180, 55, 100, 25);
        frame.add(newPass);

        JButton submit = new JButton("Ganti password");
        submit.setBounds(100, 100, 200, 25);
        frame.add(submit);

        submit.addActionListener(e -> {
            if (!customer.getPassword().equals(new String(oldPass.getPassword()).trim())) {
                JOptionPane.showMessageDialog(null, "Gagal mengganti password Anda, password salah","Peringatan", JOptionPane.WARNING_MESSAGE);
            } else if (customer.getPassword().equals(new String(newPass.getPassword()).trim())) {
                JOptionPane.showMessageDialog(null, "Gagal mengganti password Anda, password baru tidak bisa sama dengan password lama","Peringatan", JOptionPane.WARNING_MESSAGE);
            } else if (customer.getPassword().equals(new String(oldPass.getPassword()).trim())) {
                String oldP = new String(oldPass.getPassword()).trim();
                String newP = new String(newPass.getPassword()).trim();
                con.updatePassword(oldP, newP, customer.getId_user());
                JOptionPane.showMessageDialog(null, "Password telah berhasil diganti","Message",JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new MenuLihatProfile();
            }
        });
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MenuLihatProfile(); 
            }
        });
    }
}
