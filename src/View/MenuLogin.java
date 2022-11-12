/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.*;
import Control.Sql;
import Model.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author JoeMaxwell
 */
public class MenuLogin {

    public MenuLogin() {
        JFrame frame = new JFrame("Login");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel title = new JLabel("Login");
        title.setBounds(197, 50, 200, 50);
        title.setFont(new Font("Sans", Font.CENTER_BASELINE, 32));
        frame.add(title);

        JLabel username = new JLabel("Username");
        username.setBounds(155, 110, 75, 25);
        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(155, 135, 175, 25);
        frame.add(username);
        frame.add(inputUsername);

        JLabel password = new JLabel("Password");
        password.setBounds(155, 170, 75, 25);
        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(155, 195, 175, 25);
        frame.add(password);
        frame.add(inputPassword);

        JButton login = new JButton("Login");
        login.setBounds(205, 235, 75, 25);
        frame.add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = inputUsername.getText();
                String password = new String(inputPassword.getPassword());
                Sql db = new Sql();
                ArrayList<User> users = db.getAllUsers();
                String warning = "User tidak ditemukan";
                int i = 0;
                boolean found = false;
                boolean passwordCheck = false;
                while (i < users.size() && !found) {
                    if (users.get(i).getUsername().equals(username)) {
                        found = true;
                        if (users.get(i).getPassword().equals(password)) {
                            passwordCheck = true;
                        } else {
                            warning = "Password kurang tepat";
                        }
                    } else {
                        i++;
                    }
                }
                if (passwordCheck) {
                    if (users.get(i) instanceof Customer) {
                        frame.dispose();
                        Customer customer = (Customer) users.get(i);
                        new MainMenuUser(customer);
                    } else {
                        frame.dispose();
                        Admin admin = (Admin) users.get(i);
                        new MainMenuAdmin(admin);
                    }
                } else if ((found && !passwordCheck) || !found) {
                    JOptionPane.showMessageDialog(null, warning, "Warning", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        JLabel createAccount = new JLabel("Click here to create new account");
        createAccount.setBounds(148, 285, 200, 25);
        createAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                //new MenuRegister();
//                JOptionPane.showMessageDialog(null, "hi"); //test 
            }

            @Override
            public void mouseEntered(MouseEvent evt) {
                Color c = Color.red; // When the mouse moves over a label, the background color changed.
                createAccount.setBackground(createAccount.getForeground());
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
}
