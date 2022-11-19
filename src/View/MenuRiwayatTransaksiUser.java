package View;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuRiwayatTransaksiUser {
    
    Sql sql = new Sql();
    Controller controller = new Controller();
    
    public MenuRiwayatTransaksiUser() {
        Customer customer = (Customer) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(1000,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Menu Riwayat Transaksi");
        
        //Tabel Transaksi
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("ID Transaksi");
        tableModel.addColumn("Waktu Transaksi");
        tableModel.addColumn("Tipe Bayar");
        tableModel.addColumn("Tipe Pengiriman");
        tableModel.addColumn("Progress");
        tableModel.addColumn("Total Bayar");
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        
        ArrayList<Transaksi>listTransaksi = new ArrayList<>(customer.getTransaksi());
        for(int i = listTransaksi.size() - 1; i >= 0; i--){
            tableModel.addRow(controller.createIsiTableTransaksi(listTransaksi.get(i)));
        }
        JMenuBar mb = new JMenuBar();  
        JButton mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuUser();
            }
        });
        JButton lihatDetail = new JButton("Lihat Detail");
        lihatDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(table.getSelectionModel().isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Mohon mengklik transaksi yang akan dilihat detailnya", 
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id_transaksi = (int)table.getValueAt(table.getSelectedRow(), 0);
                    new MenuLihatDetailTransaksi(id_transaksi);
                }
            }
        });
        mb.add(lihatDetail);
        mb.add(Box.createGlue());
        mb.add(mainMenu);
        
        frame.add(scrollPane);
        frame.setJMenuBar(mb);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                new MainMenuUser();
            }
        });
    }
    
    public static void main(String[] args) {
        new MenuRiwayatTransaksiUser();
    }
    
}
