package View;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Model.CurrentUser;
import Model.User;
import Model.Transaksi;
import Controller.Sql;
import Controller.Controller;
import Model.Admin;
import Model.Customer;
import Model.Message;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuChat {
    
    Controller controller = new Controller();
    Sql sql = new Sql();
    public MenuChat(int id_chat_user) {
        User user = CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame("Menu Chat");
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        JTextArea messageArea = new JTextArea(controller.createMessagesForChat(id_chat_user));
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Message");
        JTextField inputPesan = new JTextField(40);
        JButton send = new JButton("Kirim");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message();
                if(inputPesan.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Mohon mengisi pesan Anda!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    msg.setMessage(inputPesan.getText());
                    msg.setId_pengirim(user.getId_user());
                    sql.insertMessage(id_chat_user, msg);
                    JOptionPane.showMessageDialog(null, "Pesan telah terkirim", "Message", JOptionPane.INFORMATION_MESSAGE);
                    messageArea.setText(controller.createMessagesForChat(id_chat_user));
                    inputPesan.setText("");
                }
            }
        });
        JButton backToMenu = new JButton("Back");
        backToMenu.addActionListener(b -> {
            frame.dispose();
            if(user instanceof Customer){
                new MainMenuUser();
            } 
        });
        panel.add(label);
        panel.add(inputPesan);
        panel.add(send);
        panel.add(backToMenu);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                if(user instanceof Customer){
                    new MainMenuUser();
                }    
            }
        });
    }
}
