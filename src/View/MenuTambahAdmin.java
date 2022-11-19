/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.Admin;
import Model.Customer;
import Model.TipeUser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class MenuTambahAdmin {
    
    public MenuTambahAdmin(){
        JFrame f = new JFrame();
        f.setSize(700, 700);
        JLabel title = new JLabel ("Tambah Admin");
        title.setBounds(250, 30, 300, 100);
        title.setFont(new Font("Sans", Font.BOLD, 28));
        f.setVisible(true);
        f.add(title);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        
        JLabel admin_username = new JLabel("User Name");
        admin_username.setBounds(250, 150, 125, 25);
        JTextField field_username = new JTextField();
        field_username.setBounds(250, 180, 200, 25);
        f.add(admin_username);
        f.add(field_username);
        
        JLabel admin_namalengkap = new JLabel("Nama Lengkap");
        admin_namalengkap.setBounds(250, 220, 125, 25);
        JTextField field_namalengkap = new JTextField();
        field_namalengkap.setBounds(250, 250, 200, 25);
        f.add(admin_namalengkap);
        f.add(field_namalengkap);
        
        JLabel admin_email = new JLabel("E-mail");
        admin_email.setBounds(250, 290, 125, 25);
        JTextField field_email = new JTextField();
        field_email.setBounds(250, 320, 200, 25);
        f.add(admin_email);
        f.add(field_email);
        
        JLabel admin_pass = new JLabel("Password");
        admin_pass.setBounds(250, 360, 125, 25);
        JTextField field_pass = new JTextField();
        field_pass.setBounds(250, 390, 200, 25);
        f.add(admin_pass);
        f.add(field_pass);
        
//        JLabel admin_address = new JLabel("Alamat");
//        admin_address.setBounds(250, 420, 125, 25);
//        JTextField field_address = new JTextField();
//        field_address.setBounds(250, 450, 200, 25);
//        f.add(admin_address);
//        f.add(field_address);
        
//        JLabel admin_telp = new JLabel("no.telp");
//        admin_telp.setBounds(250, 490, 125, 25);
//        JTextField field_telp = new JTextField();
//        field_telp.setBounds(250, 520, 200, 25);
//        f.add(admin_telp);
//        f.add(field_telp);
        
        JButton add = new JButton("Add");
        add.setBounds(270, 570, 80, 50);
        f.add(add);
        add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String admin_username = field_username.getText();
                String admin_namaLengkap = field_namalengkap.getText();
                String admin_email = field_email.getText();
                String admin_pass = field_pass.getText();
                String admin_address = "";
                String admin_telp = "";
                
                if(admin_username.equals("") || admin_email.equals("") || admin_namaLengkap.equals("") ||
                        admin_pass.equals("")){
                    JOptionPane.showMessageDialog(null, "Terdapat data yang masih kosong!" , "Message",JOptionPane.WARNING_MESSAGE);
                } else {
                    Sql sql = new Sql();
                    Customer customer = new Customer();
                    customer.setUsername(admin_username);
                    customer.setNama_lengkap(admin_namaLengkap);
                    customer.setEmail(admin_email);
                    customer.setPassword(admin_pass);
                    customer.setAlamat(admin_address);
                    customer.setNoTelpon(admin_telp);
                    customer.setTipeuser(TipeUser.ADMIN);
                    
                    sql.insertNewUser(customer);
                    JOptionPane.showMessageDialog(null, "Tambah admin baru berhasil!" , "Message",JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    new MenuTambahAdmin();
                }
            }
        });
        
        JButton cancel = new JButton("Cancel");
        cancel.setBounds(360, 570, 80, 50);
        f.add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });
        f.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                f.dispose();
                new MainMenuAdmin();
            }
        });
    }
    
    
    public static void main(String args[]) {
        new MenuTambahAdmin();
        
    }
    
}
