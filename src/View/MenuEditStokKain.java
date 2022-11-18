/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import Controller.Sql;
import Model.Customer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author asus
 */
public class MenuEditStokKain {

    Sql sql = new Sql();
    Controller controller = new Controller();
    ArrayList<String> listIDKain = new ArrayList<>(sql.getAllIDKain());

    public MenuEditStokKain() {
        JFrame f = new JFrame();
        f.setSize(500, 370);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setLayout(null);

        JLabel labelBeliKainToko = new JLabel("Silahkan pilih kain yang ingin di edit stoknya...");
        labelBeliKainToko.setBounds(20, 20, 300, 25);
        JLabel labelBeliKain = new JLabel("Pilih kain");
        labelBeliKain.setBounds(20, 50, 125, 25);
        String arrNamaKain[] = new String[listIDKain.size()];
        for (int i = 0; i < arrNamaKain.length; i++) {
            arrNamaKain[i] = controller.getNamaKain(listIDKain.get(i));
        }
        
        JComboBox comboBoxKainToko = new JComboBox(arrNamaKain);
        comboBoxKainToko.setBounds(150, 50, 250, 25);
        
        JLabel old_dataKain = new JLabel("stok sebelumnya");
        old_dataKain.setBounds(20, 80, 125, 25);
        
        Sql sql = new Sql();
        int stoklama = sql.countStockKain(listIDKain.get(0));
        JLabel old_dataKain1 = new JLabel(String.valueOf(stoklama));
        old_dataKain1.setBounds(150, 80, 75, 25);
        
        JLabel labelJumlahKainToko = new JLabel("Input jumlah stok baru");
        labelJumlahKainToko.setBounds(20, 110, 150, 25);
        JTextField inputJumlahKainToko = new JTextField();
        inputJumlahKainToko.setBounds(150, 110, 50, 25);
        JLabel labelStok = new JLabel();
        int stockKain = sql.countStockKain(listIDKain.get(0));
        if (stockKain == 0) {
            labelStok.setText("Stok habis!");
        } else {
            labelStok.setText("Sisa " + stockKain);
        }
        labelStok.setBounds(205, 80, 150, 25);
        comboBoxKainToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int jumlah = sql.countStockKain(listIDKain.get(comboBoxKainToko.getSelectedIndex()));
                
                old_dataKain1.setText(String.valueOf(jumlah));
            }
        });

        JButton buttonAddToCartToko = new JButton("Update");
        buttonAddToCartToko.setBounds(250, 150, 100, 40);
        buttonAddToCartToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Sql sql = new Sql();
                String id_kain = listIDKain.get(comboBoxKainToko.getSelectedIndex());
                int stok = Integer.parseInt(inputJumlahKainToko.getText());
                String warn = "Stok yang ingin di edit belum terisi";
                
                if(inputJumlahKainToko.getText().equals("")){
                     JOptionPane.showMessageDialog(null, warn , "Message",JOptionPane.WARNING_MESSAGE);
                } else {
                    sql.updateStokKain(id_kain, stok);
                    JOptionPane.showMessageDialog(null, "Update stok baru sukses!" , "Message",JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        
        JButton back = new JButton("Back");
        buttonAddToCartToko.setBounds(370, 150, 100, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               f.dispose();
               new MenuEditKain();
            }

        });

        f.add(labelBeliKainToko);
        f.add(labelBeliKain);
        f.add(comboBoxKainToko);
        f.add(old_dataKain);
        f.add(old_dataKain1);
        f.add(labelJumlahKainToko);
        f.add(inputJumlahKainToko);
        f.add(buttonAddToCartToko);
    }

    public static void main(String args[]) {
        new MenuEditStokKain();

    }
}
