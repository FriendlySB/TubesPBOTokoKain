package View;

import Controller.Controller;
import Controller.Sql;
import Model.Admin;
import Model.CurrentUser;
import Model.Customer;
import Model.Message;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

public class MenuListChat {
    
    Sql sql = new Sql();
    Controller controller = new Controller();
    
    public MenuListChat() {
        Admin admin = (Admin) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("List Chat");
        
        JPanel panel = new JPanel();
        ArrayList<Customer>listCustomer = new ArrayList<>(controller.getCustomerFromUser(sql.getAllUsers()));
        for(int i = 0; i < listCustomer.size(); i++){

            JLabel username = new JLabel(listCustomer.get(i).getUsername());
            JLabel waktuTerakhir = new JLabel("Belum ada chat");
            JLabel pesanTerakhir = new JLabel("Belum ada chat");
            JButton chat = new JButton("Chat");
            final int index = i;
            chat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int id_user = listCustomer.get(index).getId_user();
                    new MenuChat(id_user);
                }
            });
            Message msg = controller.getLastMessage(listCustomer.get(i).getMessage());
            if(msg != null){
                String waktu = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(msg.getWaktu());
                waktuTerakhir.setText(waktu);
                String pesan = msg.getMessage();
                if(pesan.length() > 16){
                    pesan = pesan.substring(0, 15) + "...";
                }
                pesanTerakhir.setText(pesan);
            }
            panel.add(username);
            panel.add(waktuTerakhir);
            panel.add(pesanTerakhir);
            panel.add(chat);
        }
        panel.setLayout (new GridLayout(listCustomer.size(),4,0,0)); 
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane,BorderLayout.CENTER);
        JButton mainMenu = new JButton("Back to Main Menu");
        mainMenu.setPreferredSize(new Dimension(100, 25));
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenuAdmin();
            }
        });
        frame.add(mainMenu,BorderLayout.SOUTH);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuAdmin();
            }
        });
    }   
    
}
