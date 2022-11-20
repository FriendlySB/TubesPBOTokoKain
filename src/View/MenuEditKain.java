package View;

import javax.swing.*;
import java.util.ArrayList;
import Controller.Sql;
import Model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuEditKain {

    JButton backMenu = new JButton("Back to menu");

    public MenuEditKain() {
        Sql con = new Sql();
        backMenu.setBounds(10, 210, 150, 40);

        JFrame frame = new JFrame();
        frame.setTitle("Menu Edit Data Kain");
        frame.setSize(600, 300);

        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        JButton backToMainMenu = new JButton("Back to Menu");
        backToMainMenu.setBounds(10, 210, 150, 40);

        JFrame subFrame = new JFrame();
        subFrame.setSize(500, 700);
        subFrame.setLocationRelativeTo(null);
        subFrame.setLayout(null);

        backToMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                frame.dispose();
                new MainMenuAdmin();
            }
        });
        frame.add(backToMainMenu);

        JButton butBahan = new JButton("Bahan");
        butBahan.setBounds(10, 25, 150, 40);
        frame.add(butBahan);
        butBahan.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            ArrayList<JLabel> listBahan = new ArrayList();
            ArrayList<JButton> delete = new ArrayList();
            ArrayList<JTextField> inpBahanHarga = new ArrayList();
            ArrayList<BahanKain> bahanData = con.getAllBahan();
            ArrayList<Integer> idDelete = new ArrayList();
            for (int i = 0; i < bahanData.size(); i++) {
                JLabel temp = new JLabel();
                JTextField temp2 = new JTextField();
                String temp3 = String.valueOf(bahanData.get(i).getHarga_bahan());
                JButton temp4 = new JButton("Delete");
                idDelete.add(bahanData.get(i).getId_bahan());
                delete.add(temp4);
                temp.setText(bahanData.get(i).getNama_bahan());
                temp2.setText(temp3);
                inpBahanHarga.add(temp2);
                listBahan.add(temp);
            }
            int sizeX = 10;
            int sizeY = 25;
            for (int i = 0; i < listBahan.size(); i++) {
                JLabel labHarga = new JLabel("Harga :");
                labHarga.setBounds((sizeX + 120), sizeY, 50, 25);
                listBahan.get(i).setBounds(sizeX, sizeY, 150, 25);
                inpBahanHarga.get(i).setBounds((sizeX + 170), sizeY, 150, 25);
                delete.get(i).setBounds((sizeX + 340), sizeY, 100, 25);
                subFrame.add(listBahan.get(i));
                subFrame.add(inpBahanHarga.get(i));
                subFrame.add(delete.get(i));
                subFrame.add(labHarga);
                sizeY += 30;
            }
            for (int i = 0; i < delete.size(); i++) {
                final int final_i = i;
                delete.get(i).addActionListener(a -> {
                    con.deleteBahan(idDelete.get(final_i));
                    String temp = Integer.toString(idDelete.get(final_i));
                    con.deleteKain(temp);
                    JOptionPane.showOptionDialog(null, "Berhasil didelete", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    subFrame.dispose();
                });
            }
            JButton update = new JButton("Update");
            update.setBounds(370, 620, 100, 25);
            subFrame.add(update);
            update.addActionListener(b -> {
                for (int i = 0; i < inpBahanHarga.size(); i++) {
                    int temp = Integer.parseInt(inpBahanHarga.get(i).getText());
                    con.UpdateBahan(bahanData.get(i).getId_bahan(), temp); 
                }
                JOptionPane.showOptionDialog(null, "Berhasil diubah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                subFrame.dispose();
                new MenuEditKain();
            });
            backMenu.setBounds(10, 620, 200, 25);
            subFrame.add(backMenu);
            backMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent b) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
            subFrame.addWindowListener(new WindowAdapter() { 
                @Override
                public void windowClosing(WindowEvent e) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
        }
        );

        JButton butWarna = new JButton("Warna");
        butWarna.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            ArrayList<JLabel> listWarna = new ArrayList();
            ArrayList<JButton> delete = new ArrayList();
            ArrayList<JTextField> inpWarnaHarga = new ArrayList();
            ArrayList<WarnaKain> warnaData = con.getAllWarna();
            ArrayList<Integer> idDelete = new ArrayList();
            for (int i = 0; i < warnaData.size(); i++) {
                JLabel temp = new JLabel();
                JTextField temp2 = new JTextField();
                String temp3 = String.valueOf(warnaData.get(i).getHarga_warna());
                JButton temp4 = new JButton("Delete");
                idDelete.add(warnaData.get(i).getId_warna());
                delete.add(temp4);
                temp.setText(warnaData.get(i).getNama_warna());
                temp2.setText(temp3);
                inpWarnaHarga.add(temp2);
                listWarna.add(temp);
            }
            int sizeX = 10;
            int sizeY = 25;
            for (int i = 0; i < listWarna.size(); i++) {
                JLabel labHarga = new JLabel("harga :");
                labHarga.setBounds((sizeX + 120), sizeY, 50, 25);
                listWarna.get(i).setBounds(sizeX, sizeY, 150, 25);
                inpWarnaHarga.get(i).setBounds((sizeX + 170), sizeY, 150, 25);
                delete.get(i).setBounds((sizeX + 340), sizeY, 100, 25);
                subFrame.add(listWarna.get(i));
                subFrame.add(inpWarnaHarga.get(i));
                subFrame.add(delete.get(i));
                subFrame.add(labHarga);
                sizeY += 30;
            }
            for (int i = 0; i < delete.size(); i++) {
                final int final_i = i;
                delete.get(i).addActionListener(a -> {
                    con.deleteBahan(idDelete.get(final_i));
                    String temp = Integer.toString(idDelete.get(final_i));
                    con.deleteKain(temp);
                    JOptionPane.showOptionDialog(null, "Berhasil didelete", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    subFrame.dispose();
                });
            }
            JButton update = new JButton("Update");
            update.setBounds(370, 620, 100, 25);
            subFrame.add(update);
            update.addActionListener(b -> {
                for (int i = 0; i < inpWarnaHarga.size(); i++) {
                    int temp = Integer.parseInt(inpWarnaHarga.get(i).getText());
                    con.UpdateWarna(warnaData.get(i).getId_warna(), temp);
                }
                JOptionPane.showOptionDialog(null, "Berhasil diubah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                subFrame.dispose();
                new MenuEditKain();
            });
            backMenu.setBounds(10, 620, 200, 25);
            subFrame.add(backMenu);
            backMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent b) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
            subFrame.addWindowListener(new WindowAdapter() { 
                @Override
                public void windowClosing(WindowEvent e) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
        });

        butWarna.setBounds(180, 25, 150, 40);
        frame.add(butWarna);

        JButton butMotif = new JButton("Motif");
        butMotif.addActionListener(c -> {
            frame.dispose();
            subFrame.setVisible(true);
            ArrayList<JLabel> listMotif = new ArrayList();
            ArrayList<JButton> delete = new ArrayList();
            ArrayList<JTextField> inpMotifHarga = new ArrayList();
            ArrayList<MotifKain> motifData = con.getAllMotif();
            ArrayList<Integer> idDelete = new ArrayList();
            for (int i = 0; i < motifData.size(); i++) {
                JLabel temp = new JLabel();
                JTextField temp2 = new JTextField();
                String temp3 = String.valueOf(motifData.get(i).getHarga_motif());
                JButton temp4 = new JButton("Delete");
                idDelete.add(motifData.get(i).getId_motif());
                delete.add(temp4);
                temp.setText(motifData.get(i).getNama_motif());
                temp2.setText(temp3);
                inpMotifHarga.add(temp2);
                listMotif.add(temp);
            }
            int sizeX = 10;
            int sizeY = 25;
            for (int i = 0; i < listMotif.size(); i++) {
                JLabel labHarga = new JLabel("Harga :");
                labHarga.setBounds((sizeX + 120), sizeY, 50, 25);
                listMotif.get(i).setBounds(sizeX, sizeY, 150, 25);
                inpMotifHarga.get(i).setBounds((sizeX + 170), sizeY, 150, 25);
                delete.get(i).setBounds((sizeX + 340), sizeY, 100, 25);
                subFrame.add(listMotif.get(i));
                subFrame.add(inpMotifHarga.get(i));
                subFrame.add(delete.get(i));
                subFrame.add(labHarga);
                sizeY += 30;
            }
            for (int i = 0; i < delete.size(); i++) {
                final int final_i = i;
                delete.get(i).addActionListener(a -> {
                    con.deleteBahan(idDelete.get(final_i));
                    String temp = Integer.toString(idDelete.get(final_i));
                    con.deleteKain(temp);
                    JOptionPane.showOptionDialog(null, "Berhasil didelete", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    subFrame.dispose();
                });
            }
            JButton update = new JButton("Update");
            update.setBounds(370, 620, 100, 25);
            subFrame.add(update);
            update.addActionListener(b -> {
                for (int i = 0; i < inpMotifHarga.size(); i++) {
                    int temp = Integer.parseInt(inpMotifHarga.get(i).getText());
                    con.UpdateMotif(motifData.get(i).getId_motif(), temp);
                }
                JOptionPane.showOptionDialog(null, "Berhasil diubah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                subFrame.dispose();
                new MenuEditKain();
            });
            backMenu.setBounds(10, 620, 200, 25);
            subFrame.add(backMenu);
            backMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent b) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
            subFrame.addWindowListener(new WindowAdapter() { 
                @Override
                public void windowClosing(WindowEvent e) {
                    subFrame.dispose();
                    new MenuEditKain();
                }
            });
        });
        butMotif.setBounds(350, 25, 150, 40);
        frame.add(butMotif);
        frame.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new MainMenuAdmin();
            }
        });
        
        JButton butEditStokKain = new JButton("Edit Stok Kain");
        butEditStokKain.setBounds(10, 85, 150, 40);
        butEditStokKain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                frame.dispose();
                new MenuEditStokKain();
            }
        });
        
        JButton butEditHargaKainCustom = new JButton("Edit Kain Custom");
        butEditHargaKainCustom.setBounds(180, 85, 150, 40);
        butEditHargaKainCustom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent b) {
                frame.dispose();
                new MenuEditHargaKainCustom();
            }
        });
        
        frame.add(butEditStokKain);
        frame.add(butEditHargaKainCustom);
        
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuEditKain();
    }
}
