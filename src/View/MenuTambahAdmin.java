/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Sql;
import Model.Admin;
import Model.TipeAdmin;
import Model.TipeUser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class MenuTambahAdmin implements TipeAdmin{
    
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
        
        JLabel tipe_admin = new JLabel("Tipe Admin");
        tipe_admin.setBounds(250, 430, 125, 25);
        f.add(tipe_admin);
        
        JRadioButton radioAdmin = new JRadioButton("Admin");
        radioAdmin.setBounds(250, 460, 150, 25);
        f.add(radioAdmin);
        
        JRadioButton radioCS = new JRadioButton("Customer Service");
        radioCS.setBounds(250, 490, 150, 25);
        f.add(radioCS);
        
        ButtonGroup radioTipeAdmin = new ButtonGroup();
        radioTipeAdmin.add(radioAdmin);
        radioTipeAdmin.add(radioCS);
        
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
                int tipeAdmin = -1;
                if(radioAdmin.isSelected()){
                    tipeAdmin = TipeAdmin.ADMIN;
                }
                if(radioCS.isSelected()) {
                    tipeAdmin = TipeAdmin.CS;
                }
                
                if(admin_username.equals("") || admin_email.equals("") || admin_namaLengkap.equals("") ||
                        admin_pass.equals("")|| tipeAdmin == -1){
                    JOptionPane.showMessageDialog(null, "Terdapat data yang masih kosong!" , "Message",JOptionPane.WARNING_MESSAGE);
                } else {
                    Sql sql = new Sql();
                    Admin admin = new Admin();
                    admin.setUsername(admin_username);
                    admin.setNama_lengkap(admin_namaLengkap);
                    admin.setEmail(admin_email);
                    admin.setPassword(admin_pass);
                    admin.setTipeuser(TipeUser.ADMIN);
                    admin.setTipeAdmin(tipeAdmin);
                    System.out.println(admin.getTipeAdmin());
                    sql.insertNewUser(admin);
                    admin.setId_user(sql.getID_userBottom());
                    sql.insertAdmin(admin);
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
