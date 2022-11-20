/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.Sql;
import Model.ChatRoom;
import Model.Customer;
import Model.Keranjang;
import Model.TipeUser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author JoeMaxwell
 */
public class MenuRegister {

    public MenuRegister() {
        JFrame frame = new JFrame("Register");
        frame.setSize(600, 475);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        JLabel title = new JLabel("Register");
        title.setBounds(225, 50, 200, 50);
        title.setFont(new Font("Sans", Font.CENTER_BASELINE, 32));
        frame.add(title);
        
        String pathLogo = "logo_toko.png";
        ImageIcon iconFoto = new ImageIcon(new ImageIcon(pathLogo).getImage().getScaledInstance(300, 50, Image.SCALE_DEFAULT));
        JLabel logo = new JLabel();
        logo.setIcon(iconFoto);
        logo.setBounds(150, 10, 300, 50);
        frame.add(logo);

        JLabel username = new JLabel("Username");
        username.setBounds(90, 110, 75, 25);
        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(90, 135, 175, 25);
        frame.add(username);
        frame.add(inputUsername);

        JLabel namaLengkap = new JLabel("Nama Lengkap");
        namaLengkap.setBounds(90, 170, 100, 25);
        JTextField inputNamaLengkap = new JTextField();
        inputNamaLengkap.setBounds(90, 195, 175, 25);
        frame.add(namaLengkap);
        frame.add(inputNamaLengkap);

        JLabel email = new JLabel("Email");
        email.setBounds(90, 230, 100, 25);
        JTextField inputEmail = new JTextField();
        inputEmail.setBounds(90, 255, 175, 25);
        frame.add(email);
        frame.add(inputEmail);

        JLabel password = new JLabel("Password");
        password.setBounds(320, 110, 100, 25);
        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(320, 135, 175, 25);
        frame.add(password);
        frame.add(inputPassword);

        JLabel alamat = new JLabel("Alamat");
        alamat.setBounds(320, 170, 100, 25);
        JTextField inputAlamat = new JTextField();
        inputAlamat.setBounds(320, 195, 175, 25);
        frame.add(alamat);
        frame.add(inputAlamat);

        JLabel noTelepon = new JLabel("No. Telepon");
        noTelepon.setBounds(320, 230, 100, 25);
        JTextField inputNoTelepon = new JTextField();
        inputNoTelepon.setBounds(320, 255, 175, 25);
        frame.add(noTelepon);
        frame.add(inputNoTelepon);

        JButton register = new JButton("Register");
        register.setBounds(230, 305, 120, 30);
        frame.add(register);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = inputUsername.getText().trim();
                String namaLengkap = inputNamaLengkap.getText().trim();
                String email = inputEmail.getText().trim();
                String password = new String(inputPassword.getPassword()).trim();
                String alamat = inputAlamat.getText().trim();
                String noTelepon = inputNoTelepon.getText().trim();
                String warning = "Data belum lengkap";
                if ("".equals(username) || "".equals(namaLengkap) || "".equals(email) || "".equals(password) || "".equals(alamat) || "".equals(noTelepon)) {
                    JOptionPane.showMessageDialog(null, warning, "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    Sql db = new Sql();
                    ArrayList<Customer> customers = db.getAllCustomers();
                    int i = 0;
                    boolean exist = false;
                    while (i < customers.size() && !exist) {
                        if (customers.get(i).getUsername().equals(username)) {
                            exist = true;
                            warning = "Username telah digunakan";
                        } else if (customers.get(i).getNama_lengkap().equals(namaLengkap)) {
                            exist = true;
                            warning = "Nama sudah terdaftar";
                        } else if (customers.get(i).getEmail().equals(email)) {
                            exist = true;
                            warning = "Email sudah terdaftar";
                        } else if (customers.get(i).getNoTelpon().equals(noTelepon)) {
                            exist = true;
                            warning = "Nomor Telepon telah digunakan";
                        } else {
                            i++;
                        }
                    }
                    if (exist) {
                        JOptionPane.showMessageDialog(null, warning, "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        Customer newCust = new Customer();
                        newCust.setAlamat(alamat);
                        newCust.setMessage(new ArrayList<>());
                        newCust.setEmail(email);
                        newCust.setKeranjang(new ArrayList<>());
                        newCust.setNama_lengkap(namaLengkap);
                        newCust.setNoTelpon(noTelepon);
                        newCust.setPassword(password);
                        newCust.setTipeuser(TipeUser.CUSTOMER);
                        newCust.setTransaksi(new ArrayList<>());
                        newCust.setUsername(username);

                        boolean executeInsert = db.insertNewUser(newCust);
                        if (executeInsert) {
                            frame.dispose();
                            JOptionPane.showMessageDialog(null, "Registrasi berhasil!");
                            new MenuLogin();
                        } else {
                            JOptionPane.showMessageDialog(null, "Registrasi gagal!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }

            }
        });

        JLabel haveAccount = new JLabel("Already have an account? Log in");
        haveAccount.setBounds(200, 365, 200, 25);
        haveAccount.setForeground(Color.BLUE);
        haveAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new MenuLogin();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                Color c = Color.BLACK;
                haveAccount.setBackground(haveAccount.getForeground());
                haveAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
                haveAccount.setForeground(c);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                Color c = haveAccount.getBackground();
                haveAccount.setBackground(haveAccount.getForeground());
                haveAccount.setForeground(c);
            }
        });
        frame.add(haveAccount);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
               new MenuLogin();
            }
        });
    }
}
