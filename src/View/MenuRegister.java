/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel title = new JLabel("Register");
        title.setBounds(225, 50, 200, 50);
        title.setFont(new Font("Sans", Font.CENTER_BASELINE, 32));
        frame.add(title);

        JLabel username = new JLabel("Username");
        username.setBounds(90, 110, 75, 25);
        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(90, 135, 175, 25);
        frame.add(username);
        frame.add(inputUsername);

        JLabel namaLengkap = new JLabel("Nama Lengkap");
        namaLengkap.setBounds(90, 170, 100, 25);
        JTextField inputnamaLengkap = new JTextField();
        inputnamaLengkap.setBounds(90, 195, 175, 25);
        frame.add(namaLengkap);
        frame.add(inputnamaLengkap);

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
        
        JLabel createAccount = new JLabel("Already have an account? Log in");
        createAccount.setBounds(200, 365, 200, 25);
        createAccount.setForeground(Color.BLUE);
        createAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new MenuLogin();
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                Color c = Color.BLACK;
                createAccount.setBackground(createAccount.getForeground());
                createAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
                createAccount.setForeground(c);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                Color c = createAccount.getBackground();
                createAccount.setBackground(createAccount.getForeground());
                createAccount.setForeground(c);
            }
        });
        frame.add(createAccount);
    }

    public static void main(String[] args) {
        new MenuRegister();
    }
}
