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
import Model.Customer;
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
    
    public MenuChat() {
        JFrame frame = new JFrame("Menu Chat");
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Message");
        JTextField input = new JTextField(40);
        JButton send = new JButton("Kirim");
        JButton backToMenu = new JButton("Back");
        panel.add(label);
        panel.add(input);
        panel.add(send);
        panel.add(backToMenu);
        
        JTextArea messageArea = new JTextArea(controller.createMessagesForChat());
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        
    }
    
    public static void main(String[] args) {
        new MenuChat();
    }

}
