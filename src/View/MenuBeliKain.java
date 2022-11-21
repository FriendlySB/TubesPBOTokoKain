package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import Model.KainToko;
import Model.KainCustom;
import Controller.Sql;
import Controller.Controller;
import Model.CurrentUser;
import Model.Customer;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuBeliKain {

    Sql sql = new Sql();
    Controller controller = new Controller();
    ArrayList<KainToko> listKainToko = new ArrayList<>(sql.getAllKainToko());

    public MenuBeliKain() {
        Customer customer = (Customer) CurrentUser.getInstance().getUser();
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
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
        String arrNamaKain[] = new String[listKainToko.size()];
        for (int i = 0; i < arrNamaKain.length; i++) {
            arrNamaKain[i] = controller.getNamaKainToko(listKainToko.get(i));
        }
        JComboBox comboBoxKainToko = new JComboBox(arrNamaKain);
        comboBoxKainToko.setBounds(150, 50, 250, 25);
        JLabel labelHargaKain = new JLabel("Harga Kain");
        labelHargaKain.setBounds(20, 80, 150, 25);
        int hargaKain = controller.hitungHargaKainToko(listKainToko.get(comboBoxKainToko.getSelectedIndex()));
        JLabel labelHargaKainDisplay = new JLabel();
        labelHargaKainDisplay.setText(String.valueOf(hargaKain));
        labelHargaKainDisplay.setBounds(150, 80, 150, 25);
        JLabel labelJumlahKainToko = new JLabel("Input Jumlah Kain");
        labelJumlahKainToko.setBounds(20, 110, 150, 25);
        SpinnerModel value = new SpinnerNumberModel(0, 0, 99, 1);
        JSpinner spinnerJumlahKainToko = new JSpinner(value);
        spinnerJumlahKainToko.setBounds(150, 110, 50, 25);
        JLabel labelStok = new JLabel();
        int stockKain = listKainToko.get(0).getStok();
        if (stockKain == 0) {
            labelStok.setText("Stok habis!");
        } else {
            labelStok.setText("Sisa " + stockKain);
        }
        labelStok.setBounds(205, 110, 150, 25);
        JLabel labelHargaTotal = new JLabel("Harga Total");
        labelHargaTotal.setBounds(20, 150, 150, 25);
        JLabel labelHargaTotalDisplay = new JLabel("0");
        labelHargaTotalDisplay.setBounds(150, 150, 150, 25);

        comboBoxKainToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int jumlah = listKainToko.get(comboBoxKainToko.getSelectedIndex()).getStok();
                int hargaKain = controller.hitungHargaKainToko(listKainToko.get(comboBoxKainToko.getSelectedIndex()));
                if (jumlah == 0) {
                    labelStok.setText("Stok habis!");
                } else {
                    labelStok.setText("Sisa " + jumlah);
                }
                labelHargaKainDisplay.setText(String.valueOf(hargaKain));
            }
        });

        spinnerJumlahKainToko.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int hargaKain = controller.hitungHargaKainToko(listKainToko.get(comboBoxKainToko.getSelectedIndex()));
                int jumlah = (Integer) spinnerJumlahKainToko.getValue();
                int total = hargaKain * jumlah;
                labelHargaTotalDisplay.setText(String.valueOf(total));
            }
        });

        JButton buttonAddToCartToko = new JButton("Add to Cart");
        buttonAddToCartToko.setBounds(250, 180, 100, 40);
        buttonAddToCartToko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if ((Integer) spinnerJumlahKainToko.getValue() == 0) {
                    JOptionPane.showMessageDialog(null, "Mohon mengisi jumlah kain", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    String idKain = listKainToko.get(comboBoxKainToko.getSelectedIndex()).getId_kain();
                    int quantity = (Integer) spinnerJumlahKainToko.getValue();
                    if (controller.cekKainDuplikatKeranjang(customer.getId_user(), idKain) == true) {
                        JOptionPane.showMessageDialog(null, "Kain tersebut sudah ada di keranjang Anda!",
                                "Peringatan", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (controller.cekStokKain(idKain, quantity) == true) {
                            sql.insertKeranjang(customer, idKain, quantity);
                            customer.setKeranjang(sql.getKeranjang(customer.getId_user()));
                            CurrentUser.getInstance().setUser(customer);
                            JOptionPane.showMessageDialog(null, "Kain telah ditambahkan ke cart Anda",
                                    "Message", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();
                            new MenuBeliKain();
                        } else {
                            JOptionPane.showMessageDialog(null, "Maaf, stok " + controller.getNamaKain(idKain)
                                    + " kami tidak dapat mencukupi permintaan Anda",
                                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
            }
        });
        panelKainToko.add(labelHargaKain);
        panelKainToko.add(labelHargaKainDisplay);
        panelKainToko.add(spinnerJumlahKainToko);
        panelKainToko.add(labelBeliKainToko);
        panelKainToko.add(labelBeliKain);
        panelKainToko.add(comboBoxKainToko);
        panelKainToko.add(labelJumlahKainToko);
        panelKainToko.add(labelStok);
        panelKainToko.add(labelHargaTotal);
        panelKainToko.add(labelHargaTotalDisplay);
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
        JSpinner spinnerJumlahKainCustom = new JSpinner(value);
        spinnerJumlahKainCustom.setBounds(180, 110, 50, 25);
        JButton buttonAddToCartCustom = new JButton("Add to Cart");
        buttonAddToCartCustom.setBounds(250, 180, 100, 40);
        buttonAddToCartCustom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Sql sql = new Sql();
                Controller controller = new Controller();
                if (inputBahanCustom.getText().equals("")
                        || inputWarnaCustom.getText().equals("")
                        || inputMotifCustom.getText().equals("")
                        || (Integer) spinnerJumlahKainCustom.getValue() == 0) {
                    JOptionPane.showMessageDialog(null, "Mohon lengkapi data kain custom Anda",
                            "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    String idKain = controller.createIDKain();
                    KainCustom kain = new KainCustom(inputBahanCustom.getText(),
                            inputWarnaCustom.getText(), inputMotifCustom.getText(), 0, idKain);
                    sql.insertKain(kain, idKain);
                    sql.insertKeranjang(customer, idKain, (Integer) spinnerJumlahKainCustom.getValue());
                    customer.setKeranjang(sql.getKeranjang(customer.getId_user()));
                    CurrentUser.getInstance().setUser(customer);
                    JOptionPane.showMessageDialog(null, "Kain telah ditambahkan ke cart Anda",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                    new MenuBeliKain();
                }
            }
        });

        panelKainCustom.add(labelBahanCustom);
        panelKainCustom.add(inputBahanCustom);
        panelKainCustom.add(labelWarnaCustom);
        panelKainCustom.add(inputWarnaCustom);
        panelKainCustom.add(labelMotifCustom);
        panelKainCustom.add(inputMotifCustom);
        panelKainCustom.add(labelJumlahCustom);
        panelKainCustom.add(spinnerJumlahKainCustom);
        panelKainCustom.add(buttonAddToCartCustom);

        JTabbedPane tp = new JTabbedPane();
        tp.setBounds(0, 50, 600, 400);
        tp.add("Kain Toko", panelKainToko);
        tp.add("Kain Custom", panelKainCustom);

        JMenuBar mb = new JMenuBar();
        JButton mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuUser();
            }
        });
        mb.add(Box.createGlue());
        mb.add(mainMenu);

        frame.add(tp);
        frame.add(judul);
        frame.setJMenuBar(mb);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuUser();
            }
        });
    }

    public static void main(String args[]) {
        new MenuBeliKain();
    }
}
