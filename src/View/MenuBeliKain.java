package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import Model.Kain;
import Model.kain_toko;
import Model.KainCustom;
import Controller.Sql;
import Controller.Controller;
import java.util.ArrayList;

public class MenuBeliKain {
    Sql sql = new Sql();
    Controller controller = new Controller();
    
    public MenuBeliKain() {
        JFrame frame = new JFrame();
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Menu Beli Kain");
        
        JPanel panelKainToko = new JPanel();
        panelKainToko.setSize(600, 400);
        panelKainToko.setLayout(null);
        panelKainToko.setVisible(true);
        
        JPanel panelKainCustom = new JPanel();
        panelKainCustom.setSize(600, 400);
        panelKainCustom.setLayout(null);
        panelKainCustom.setVisible(true);
        
        JLabel judul = new JLabel("Silahkan pilih kain toko atau kain custom");
        judul.setBounds(20, 5, 300, 20);
        
        //Kain Toko
        JLabel labelBeliKainToko = new JLabel("Silahkan memilih kain toko");
        labelBeliKainToko.setBounds(20, 20, 250, 25);
        JLabel labelBeliKain = new JLabel("Pilih kain");
        labelBeliKain.setBounds(20, 50, 125, 25);
        JComboBox comboBoxKainToko = new JComboBox();
        comboBoxKainToko.setBounds(150, 50, 250, 25);
        JLabel labelJumlahKainToko = new JLabel("Input Jumlah Kain");
        labelJumlahKainToko.setBounds(20, 80, 150, 25);
        JTextField inputJumlahKainToko = new JTextField();
        inputJumlahKainToko.setBounds(150, 80, 50, 25);
        JButton buttonAddToCartToko = new JButton("Add to Cart");
        buttonAddToCartToko.setBounds(250, 180, 100, 40);
        buttonAddToCartToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //t
            }
        });
        
        panelKainToko.add(labelBeliKainToko);
        panelKainToko.add(labelBeliKain);
        panelKainToko.add(comboBoxKainToko);
        panelKainToko.add(labelJumlahKainToko);
        panelKainToko.add(inputJumlahKainToko);
        panelKainToko.add(buttonAddToCartToko);
        //Kain Custom
        JLabel labelBahanCustom = new JLabel("Input Bahan Custom");
        labelBahanCustom.setBounds(20, 20, 150, 25);
        JTextField inputBahanCustom = new JTextField();
        inputBahanCustom.setBounds(180, 20, 150, 25);
        JLabel labelWarnaCustom = new JLabel("Input Warna Custom");
        labelWarnaCustom.setBounds(20, 50, 150, 25);
        JTextField inputWarnaCustom = new JTextField();
        inputWarnaCustom.setBounds(180, 50, 150, 25);
        JLabel labelMotifCustom = new JLabel("Input Motif Custom");
        labelMotifCustom.setBounds(20, 80, 150, 25);
        JTextField inputMotifCustom = new JTextField();
        inputMotifCustom.setBounds(180, 80, 150, 25);
        JLabel labelJumlahCustom = new JLabel("Input Jumlah Kain");
        labelJumlahCustom.setBounds(20, 110, 150, 25);
        JTextField inputJumlahCustom = new JTextField();
        inputJumlahCustom.setBounds(180, 110, 150, 25);
        JButton buttonAddToCartCustom = new JButton("Add to Cart");
        buttonAddToCartCustom.setBounds(220, 140, 100, 40);
        buttonAddToCartCustom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Sql sql = new Sql();
                Controller controller = new Controller();
                String idKain = controller.createIDKainCustom(sql.countIDKainCustom());
                KainCustom kain = new KainCustom(inputBahanCustom.getText(),
                        inputWarnaCustom.getText(),inputMotifCustom.getText(),0,idKain);
                sql.insertKain(kain,idKain);
            }
        });
        
        panelKainCustom.add(labelBahanCustom);
        panelKainCustom.add(inputBahanCustom);
        panelKainCustom.add(labelWarnaCustom);
        panelKainCustom.add(inputWarnaCustom);
        panelKainCustom.add(labelMotifCustom);
        panelKainCustom.add(inputMotifCustom);
        panelKainCustom.add(labelJumlahCustom);
        panelKainCustom.add(inputJumlahCustom);
        panelKainCustom.add(buttonAddToCartCustom);
        
        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0,50,600,400);
        tp.add("Kain Toko",panelKainToko);
        tp.add("Kain Custom",panelKainCustom); 
        
        JMenuBar mb = new JMenuBar();  
        JButton menuCart = new JButton("Cart");
        JButton mainMenu = new JButton("Main Menu");
        mb.add(Box.createGlue());
        mb.add(menuCart);
        mb.add(mainMenu);
        
        frame.add(tp);
        //frame.add(panelKainCustom);
        frame.add(judul);
        frame.setJMenuBar(mb);
    }
    
    public static void main(String args[]) {
        new MenuBeliKain();
    }
}
