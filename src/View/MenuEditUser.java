package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;

public class MenuEditUser {

    public MenuEditUser(Customer customer) {
        Sql con = new Sql();
        JFrame frame = new JFrame();
        frame.setTitle("Edit Data Profil");
        frame.setSize(700, 600);

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

        JTextField namaLengkap = new JTextField(customer.getNama_lengkap());
        namaLengkap.setBounds(120, 55, 100, 25);
        frame.add(namaLengkap);

        JTextField userName = new JTextField(customer.getUsername());
        userName.setBounds(110, 20, 100, 25);
        frame.add(userName);

        JLabel labAlamat = new JLabel("Alamat: ");
        labAlamat.setBounds(20, 85, 70, 25);
        frame.add(labAlamat);

        JTextField alamat = new JTextField(customer.getAlamat());
        alamat.setBounds(110, 85, 70, 25);
        frame.add(alamat);

        JLabel labEmail = new JLabel("Email :");
        labEmail.setBounds(20, 115, 70, 25);
        frame.add(labEmail);

        JTextField email = new JTextField(customer.getEmail());
        email.setBounds(110, 115, 200, 25);
        frame.add(email);

        JLabel labTelfon = new JLabel("No. Telfon: ");
        labTelfon.setBounds(20, 145, 100, 25);
        frame.add(labTelfon);

        JTextField telfon = new JTextField(customer.getNoTelpon());
        telfon.setBounds(110, 145, 100, 25);
        frame.add(telfon);

        JButton edit = new JButton("Edit Data");
        edit.setBounds(10, 200, 150, 40);
        edit.addActionListener(e -> {
            Customer editan = new Customer(alamat.getText(), telfon.getText(), customer.getMessage(), customer.getKeranjang(),
                    customer.getTransaksi(), customer.getId_user(), userName.getText(), namaLengkap.getText(), email.getText(),
                    customer.getPassword(), customer.getTipeuser());
            con.updateDataCustomer(editan);
            frame.dispose();
        });
        frame.add(edit);

        JButton backMenu = new JButton("back to menu");
        backMenu.addActionListener(b -> {
            frame.dispose();
        });
        backMenu.setBounds(210, 200, 150, 40);
        frame.add(backMenu);
    }
}
