package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuEditUser {

    public MenuEditUser() {
        Customer customer = (Customer) CurrentUser.getInstance().getUser();
        Sql con = new Sql();
        JFrame frame = new JFrame();
        frame.setTitle("Menu Edit Data Profile");
        frame.setSize(400, 300);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel labUser = new JLabel("Username: ");
        labUser.setBounds(20, 25, 100, 25);
        frame.add(labUser);

        JLabel labNama = new JLabel("Nama Lengkap: ");
        labNama.setBounds(20, 55, 100, 25);
        frame.add(labNama);

        JTextField namaLengkap = new JTextField(customer.getNama_lengkap());
        namaLengkap.setBounds(120, 55, 100, 25);
        frame.add(namaLengkap);

        JTextField userName = new JTextField(customer.getUsername());
        userName.setBounds(120, 25, 100, 25);
        frame.add(userName);

        JLabel labAlamat = new JLabel("Alamat: ");
        labAlamat.setBounds(20, 85, 70, 25);
        frame.add(labAlamat);

        JTextField alamat = new JTextField(customer.getAlamat());
        alamat.setBounds(120, 85, 70, 25);
        frame.add(alamat);

        JLabel labEmail = new JLabel("Email :");
        labEmail.setBounds(20, 115, 70, 25);
        frame.add(labEmail);

        JTextField email = new JTextField(customer.getEmail());
        email.setBounds(120, 115, 200, 25);
        frame.add(email);

        JLabel labTelfon = new JLabel("No. Telepon: ");
        labTelfon.setBounds(20, 145, 100, 25);
        frame.add(labTelfon);

        JTextField telfon = new JTextField(customer.getNoTelpon());
        telfon.setBounds(120, 145, 100, 25);
        frame.add(telfon);

        JButton edit = new JButton("Edit Data");
        edit.setBounds(10, 200, 150, 40);
        edit.addActionListener(e -> {
            Customer editan = new Customer(alamat.getText(), telfon.getText(), customer.getMessage(), customer.getKeranjang(),
                    customer.getTransaksi(), customer.getId_user(), userName.getText(), namaLengkap.getText(), email.getText(),
                    customer.getPassword(), customer.getTipeuser());
            con.updateDataCustomer(editan);
            JOptionPane.showMessageDialog(null, "Data Anda telah berhasil diupdate","Message",JOptionPane.INFORMATION_MESSAGE);
            CurrentUser.getInstance().setUser(editan);
            frame.dispose();
            new MenuLihatProfile();
        });
        frame.add(edit);

        JButton backMenu = new JButton("Back to Menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
            new MenuLihatProfile(); 
        });
        backMenu.setBounds(210, 200, 150, 40);
        frame.add(backMenu);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MenuLihatProfile(); 
            }
        });
    }
}
