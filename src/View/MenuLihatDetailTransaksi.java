package View;

import javax.swing.*;
import Model.Transaksi;
import Controller.Sql;
import Controller.Controller;
import Model.DetailTransaksi;
import java.util.ArrayList;
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
        Transaksi transaksi = controller.getTransaksiByID(sql.getAllTransaksi(), id_transaksi);
        int ongkir = controller.totalBiayaPengirimanTransaksi(transaksi.getTipe_pengiriman(), transaksi.getDetailTransaksi());
        tableModel.addRow(new Object[]{"","","","","Ongkir",ongkir});
        Object[] lastRow = {"", "", "", "", "Subtotal", (ongkir + controller.hitungTotalDetailTransaksi(listDetail))};
        tableModel.addRow(lastRow);
        frame.add(scrollPane);
    }
}
