package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;

public class MenuLihatProfile {

    public MenuLihatProfile(Customer customer) {
        JFrame frame = new JFrame();
        frame.setTitle("Data Profil");
        frame.setSize(600, 300);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labUser = new JLabel("username: ");
        labUser.setBounds(20, 20, 70, 25);
        frame.add(labUser);

        JLabel labNama = new JLabel("Nama Lengkap: ");
        labNama.setBounds(20, 55, 100, 25);
        frame.add(labNama);

        JLabel namaLengkap = new JLabel(customer.getNama_lengkap());
        namaLengkap.setBounds(120, 55, 100, 25);
        frame.add(namaLengkap);

        JLabel userName = new JLabel(customer.getUsername());
        userName.setBounds(110, 20, 100, 25);
        frame.add(userName);

        JLabel labAlamat = new JLabel("Alamat: ");
        labAlamat.setBounds(20, 85, 70, 25);
        frame.add(labAlamat);

        JLabel alamat = new JLabel(customer.getAlamat());
        alamat.setBounds(110, 85, 70, 25);
        frame.add(alamat);

        JLabel labEmail = new JLabel("Email :");
        labEmail.setBounds(20, 115, 70, 25);
        frame.add(labEmail);

        JLabel email = new JLabel(customer.getEmail());
        email.setBounds(110, 115, 200, 25);
        frame.add(email);

        JLabel labTelfon = new JLabel("No. Telfon: ");
        labTelfon.setBounds(20, 145, 100, 25);
        frame.add(labTelfon);

        JLabel telfon = new JLabel(customer.getNoTelpon());
        telfon.setBounds(110, 145, 100, 25);
        frame.add(telfon);
        
        JButton edit = new JButton("Edit Data");
        edit.setBounds(10, 200, 150, 40);
        edit.addActionListener(e -> {
            frame.dispose();
            new MenuEditUser(customer);
        });
        frame.add(edit);

        JButton backMenu = new JButton("back to menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
        });
        backMenu.setBounds(210, 200, 150, 40);
        frame.add(backMenu);
        
        JButton pass = new JButton("ganti password");
        pass.addActionListener(c -> {
            frame.dispose();
            new GantiPassword(customer);
        });
        pass.setBounds(410, 200, 150, 40);
        frame.add(pass);
    }

}
