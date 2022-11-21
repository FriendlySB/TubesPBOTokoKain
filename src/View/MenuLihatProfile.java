package View;

import javax.swing.*;
import Model.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuLihatProfile {

    public MenuLihatProfile() {
        Customer customer = (Customer) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setTitle("Menu Data Profile");
        frame.setSize(600, 300);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel labUser = new JLabel("Username: ");
        labUser.setBounds(20, 20, 70, 25);
        frame.add(labUser);

        JLabel labNama = new JLabel("Nama Lengkap: ");
        labNama.setBounds(20, 55, 100, 25);
        frame.add(labNama);

        JLabel namaLengkap = new JLabel(customer.getNama_lengkap());
        namaLengkap.setBounds(120, 55, 250, 25);
        frame.add(namaLengkap);

        JLabel userName = new JLabel(customer.getUsername());
        userName.setBounds(120, 20, 250, 25);
        frame.add(userName);

        JLabel labAlamat = new JLabel("Alamat: ");
        labAlamat.setBounds(20, 85, 70, 25);
        frame.add(labAlamat);

        JLabel alamat = new JLabel(customer.getAlamat());
        alamat.setBounds(120, 85, 350, 25);
        frame.add(alamat);

        JLabel labEmail = new JLabel("Email :");
        labEmail.setBounds(20, 115, 70, 25);
        frame.add(labEmail);

        JLabel email = new JLabel(customer.getEmail());
        email.setBounds(120, 115, 250, 25);
        frame.add(email);

        JLabel labTelfon = new JLabel("No. Telepon: ");
        labTelfon.setBounds(20, 145, 100, 25);
        frame.add(labTelfon);

        JLabel telfon = new JLabel(customer.getNoTelpon());
        telfon.setBounds(120, 145, 250, 25);
        frame.add(telfon);
        
        JButton edit = new JButton("Edit Data");
        edit.setBounds(10, 200, 150, 40);
        edit.addActionListener(e -> {
            frame.dispose();
            new MenuEditUser();
        });
        frame.add(edit);

        JButton backMenu = new JButton("Back to Menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
            new MainMenuUser(); 
        });
        backMenu.setBounds(210, 200, 150, 40);
        frame.add(backMenu);
        
        JButton pass = new JButton("Ganti password");
        pass.addActionListener(c -> {
            frame.dispose();
            new MenuGantiPassword(customer);
        });
        pass.setBounds(410, 200, 150, 40);
        frame.add(pass);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuUser(); 
            }
        });
    }

}
