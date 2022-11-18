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
import Model.DetailTransaksi;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MenuLihatDetailTransaksi {
    
    Sql sql = new Sql();
    Controller controller = new Controller();
    
    public MenuLihatDetailTransaksi(int id_transaksi) {
        JFrame frame = new JFrame();
        frame.setSize(800,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Detail Transaksi");
        
        //Tabel Detail Transaksi
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Nomor");
        tableModel.addColumn("ID Kain");
        tableModel.addColumn("Nama Kain");
        tableModel.addColumn("Harga Kain");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Total Harga");
        
        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);
        
        ArrayList<DetailTransaksi> listDetail = new ArrayList<>(sql.getSQLDetailTransaksi(id_transaksi));
        for(int i = 0; i < listDetail.size(); i++){
            tableModel.addRow(controller.createIsiTableDetailTransaksi(listDetail.get(i), i));
        }
        tableModel.addRow(new Object[]{"","","","","",""});
        Object[] lastRow = {"", "", "", "", "Subtotal", controller.hitungTotalDetailTransaksi(listDetail)};
        tableModel.addRow(lastRow);
        frame.add(scrollPane);
    }
}
