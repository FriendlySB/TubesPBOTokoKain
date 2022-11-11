/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;

import java.awt.Font;

/**
 *
 * @author JoeMaxwell
 */
public class MenuLogin {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel title = new JLabel("Account");
        title.setBounds(175, 50, 200, 50);
        title.setFont(new Font("Sans", Font.BOLD, 32));
        frame.add(title);

        JLabel username = new JLabel("Username");
        username.setBounds(155, 130, 75, 25);
        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(155, 155, 175, 25);
        frame.add(username);
        frame.add(inputUsername);

        JLabel password = new JLabel("Password");
        password.setBounds(155, 190, 75, 25);
        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(155, 215, 175, 25);
        frame.add(password);
        frame.add(inputPassword);

        JButton login = new JButton("Login");
        login.setBounds(205, 250, 75, 25);
        frame.add(login);

        JLabel createAccount = new JLabel("Click here to create new account");
        createAccount.setBounds(148, 285, 200, 25);
        frame.add(createAccount);

    }

}
