package View;

import javax.swing.*;
import Controller.Sql;
import Model.*;
import java.util.ArrayList;

public class MenuInputKain {

    JButton backMenu = new JButton("Back to menu");

    public MenuInputKain() {
        backMenu.setBounds(10, 200, 150, 40);

        Sql con = new Sql();
        JFrame frame = new JFrame();
        JFrame subFrame = new JFrame();
        subFrame.setLayout(null);
        subFrame.setSize(500, 600);
        backMenu.addActionListener(b -> {
            frame.dispose();
        });
        frame.setSize(700, 300);
        frame.setTitle("Menu Input Kain");
        frame.setLocationRelativeTo(null);
        subFrame.setLocationRelativeTo(null);
        JLabel salam1 = new JLabel("Apa yang mau ditambahkan");
        salam1.setBounds(250, 20, 200, 25);
        frame.add(salam1);
        JButton bBahan = new JButton("Bahan");
        bBahan.setBounds(20, 50, 200, 50);
        frame.add(bBahan);
        bBahan.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Bahan");
            JLabel labBahan = new JLabel("Masukan nama Bahan");
            JTextField inpBahan = new JTextField();
            labBahan.setBounds(10, 25, 200, 25);
            inpBahan.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga bahan");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labBahan);
            subFrame.add(inpBahan);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JLabel labWarna = new JLabel("pilihan warna: ");
            labWarna.setBounds(10, 85, 100, 25);
            subFrame.add(labWarna);
            ArrayList<JRadioButton> tomWarna = new ArrayList();
            ArrayList<WarnaKain> warnaKain = con.getAllWarna();

            for (int i = 0; i < warnaKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(warnaKain.get(i).getNama_warna());
                tomWarna.add(tempTom);
            }
            int tempX = 10;
            int tempY = 115;
            for (int i = 0; i < tomWarna.size(); i++) {
                if (tempY > 205) {
                    tempY = 115;
                    tempX += 110;
                }
                tomWarna.get(i).setBounds(tempX, tempY, 100, 25);
                subFrame.add(tomWarna.get(i));
                tempY += 30;
            }

            JLabel labMotif = new JLabel("pilihan motif: ");
            labMotif.setBounds(10, 225, 100, 25);
            subFrame.add(labMotif);
            ArrayList<JRadioButton> tomMotif = new ArrayList();
            ArrayList<MotifKain> motifKain = con.getAllMotif();

            for (int i = 0; i < motifKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(motifKain.get(i).getNama_motif());
                tomMotif.add(tempTom);
            }
            int tempX2 = 10;
            int tempY2 = 255;
            for (int i = 0; i < tomMotif.size(); i++) {
                if (tempY2 > 345) {
                    tempY2 = 255;
                    tempX2 += 110;
                }
                tomMotif.get(i).setBounds(tempX2, tempY2, 100, 25);
                subFrame.add(tomMotif.get(i));
                tempY2 += 30;
            }

            JButton add = new JButton("Tambah");
            add.setBounds(10, 400, 100, 25);

            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                BahanKain tempo = con.getBahanBottom();
                int idBahan = tempo.getId_bahan() + 1;
                BahanKain bahan = new BahanKain(idBahan, inpBahan.getText(), harga);
                con.insertBahan(bahan);
                int input = JOptionPane.showOptionDialog(null, "berhasil ditambah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    ArrayList<String> idMotif = new ArrayList();
                    ArrayList<String> idWarna = new ArrayList();
                    ArrayList<String> namaMotif = new ArrayList();
                    ArrayList<String> namaWarna = new ArrayList();
                    for (int i = 0; i < motifKain.size(); i++) {
                        if (tomMotif.get(i).isSelected()) {
                            MotifKain temp = con.getMotif(tomMotif.get(i).getText());
                            namaMotif.add(temp.getNama_motif());
                            String idToAdd = Integer.toString(temp.getId_motif());
                            idMotif.add(idToAdd);
                        }
                    }
                    for (int i = 0; i < warnaKain.size(); i++) {
                        if (tomWarna.get(i).isSelected()) {
                            WarnaKain temp = con.getWarna(tomWarna.get(i).getText());
                            namaWarna.add(temp.getNama_warna());
                            String idToAdd = Integer.toString(temp.getId_warna());
                            idWarna.add(idToAdd);
                        }
                    }
                    if (idMotif.size() >= idWarna.size()) {
                        for (int i = 0; i < idMotif.size(); i++) {
                            MotifKain tempMotif = con.getMotif(namaMotif.get(i));
                            for (int j = 0; j < idWarna.size(); j++) {
                                String idKain = Integer.toString(bahan.getId_bahan());
                                WarnaKain tempWarna = con.getWarna(namaWarna.get(j));
                                idKain += "-" + idWarna.get(j) + "-" + idMotif.get(i);
                                kain_toko tempKain = new kain_toko(tempMotif, tempWarna, bahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = Integer.toString(bahan.getId_bahan());
                            }
                        }
                    } else {
                        for (int i = 0; i < idWarna.size(); i++) {
                            WarnaKain tempWarna = con.getWarna(idWarna.get(i));
                            for (int j = 0; j < idMotif.size(); j++) {
                                String idKain = Integer.toString(bahan.getId_bahan());
                                MotifKain tempMotif = con.getMotif(idMotif.get(j));
                                idKain += "-" + idWarna.get(j) + "-" + idMotif.get(i);
                                kain_toko tempKain = new kain_toko(tempMotif, tempWarna, bahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = Integer.toString(bahan.getId_bahan());
                            }
                        }
                    }
                };
            });
            subFrame.add(add);
            backMenu.setBounds(10, 450, 150, 40);
            subFrame.add(backMenu);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        JButton bWarna = new JButton("Warna");

        bWarna.setBounds(
                240, 50, 200, 50);
        frame.add(bWarna);

        bWarna.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Warna");
            JLabel labWarna = new JLabel("Masukan nama Warna");
            JTextField inpWarna = new JTextField();
            labWarna.setBounds(10, 25, 200, 25);
            inpWarna.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga Warna");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labWarna);
            subFrame.add(inpWarna);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JLabel labBahan = new JLabel("pilihan bahan: ");
            labBahan.setBounds(10, 85, 100, 25);
            subFrame.add(labBahan);
            ArrayList<JRadioButton> tomBahan = new ArrayList();
            ArrayList<BahanKain> bahanKain = con.getAllBahan();

            for (int i = 0; i < bahanKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(bahanKain.get(i).getNama_bahan());
                tomBahan.add(tempTom);
            }
            int tempX = 10;
            int tempY = 115;
            for (int i = 0; i < tomBahan.size(); i++) {
                if (tempY > 205) {
                    tempY = 115;
                    tempX += 110;
                }
                tomBahan.get(i).setBounds(tempX, tempY, 100, 25);
                subFrame.add(tomBahan.get(i));
                tempY += 30;
            }

            JLabel labMotif = new JLabel("pilihan motif: ");
            labMotif.setBounds(10, 225, 100, 25);
            subFrame.add(labMotif);
            ArrayList<JRadioButton> tomMotif = new ArrayList();
            ArrayList<MotifKain> motifKain = con.getAllMotif();

            for (int i = 0; i < motifKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(motifKain.get(i).getNama_motif());
                tomMotif.add(tempTom);
            }
            int tempX2 = 10;
            int tempY2 = 255;
            for (int i = 0; i < tomMotif.size(); i++) {
                if (tempY2 > 345) {
                    tempY2 = 255;
                    tempX2 += 110;
                }
                tomMotif.get(i).setBounds(tempX2, tempY2, 100, 25);
                subFrame.add(tomMotif.get(i));
                tempY2 += 30;
            }

            JButton add = new JButton("Tambah");
            add.setBounds(10, 400, 100, 25);

            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                WarnaKain tempo = con.getWarnaBottom();
                int idWarna = tempo.getId_warna() + 1;
                WarnaKain warna = new WarnaKain(idWarna, inpWarna.getText(), harga);
                con.insertWarna(warna);
                int input = JOptionPane.showOptionDialog(null, "berhasil ditambah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    ArrayList<String> idMotif = new ArrayList();
                    ArrayList<String> idBahan = new ArrayList();
                    ArrayList<String> namaMotif = new ArrayList();
                    ArrayList<String> namaBahan = new ArrayList();

                    for (int i = 0; i < motifKain.size(); i++) {
                        if (tomMotif.get(i).isSelected()) {
                            MotifKain temp = con.getMotif(tomMotif.get(i).getText());
                            namaMotif.add(temp.getNama_motif());
                            String idToAdd = Integer.toString(temp.getId_motif());
                            idMotif.add(idToAdd);
                        }
                    }
                    for (int i = 0; i < bahanKain.size(); i++) {
                        if (tomBahan.get(i).isSelected()) {
                            BahanKain temp = con.getBahan(tomBahan.get(i).getText());
                            namaBahan.add(temp.getNama_bahan());
                            String idToAdd = Integer.toString(temp.getId_bahan());
                            idBahan.add(idToAdd);
                        }
                    }
                    if (idMotif.size() >= idBahan.size()) {
                        for (int i = 0; i < idMotif.size(); i++) {
                            MotifKain tempMotif = con.getMotif(namaMotif.get(i));
                            for (int j = 0; j < idBahan.size(); j++) {
                                String idKain = idBahan.get(j);
                                BahanKain tempBahan = con.getBahan(namaBahan.get(j));
                                idKain += "-" + warna.getId_warna() + "-" + idMotif.get(i);
                                kain_toko tempKain = new kain_toko(tempMotif, warna, tempBahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = idBahan.get(j);
                            }
                        }
                    } else {
                        for (int i = 0; i < idBahan.size(); i++) {
                            BahanKain tempBahan = con.getBahan(namaBahan.get(i));
                            for (int j = 0; j < idMotif.size(); j++) {
                                String idKain = idBahan.get(j);
                                MotifKain tempMotif = con.getMotif(namaMotif.get(j));
                                idKain += "-" + warna.getId_warna() + "-" + idMotif.get(i);
                                kain_toko tempKain = new kain_toko(tempMotif, warna, tempBahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = idBahan.get(j);
                            }
                        }
                    }
                };
            });
            subFrame.add(add);
            backMenu.setBounds(10, 450, 150, 40);
            subFrame.add(backMenu);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        JButton bMotif = new JButton("Motif");

        bMotif.setBounds(
                460, 50, 200, 50);
        frame.add(bMotif);

        bMotif.addActionListener(e -> {
            frame.dispose();
            subFrame.setVisible(true);
            subFrame.setTitle("Tambah Motif");
            JLabel labMotif = new JLabel("Masukan nama Motif");
            JTextField inpMotif = new JTextField();
            labMotif.setBounds(10, 25, 200, 25);
            inpMotif.setBounds(220, 25, 200, 25);
            JLabel labHarga = new JLabel("masukan harga Motif");
            JTextField inpHarga = new JTextField();
            labHarga.setBounds(10, 55, 200, 25);
            inpHarga.setBounds(220, 55, 200, 25);
            subFrame.add(labMotif);
            subFrame.add(inpMotif);
            subFrame.add(labHarga);
            subFrame.add(inpHarga);
            JLabel labBahan = new JLabel("pilihan bahan: ");
            labBahan.setBounds(10, 85, 100, 25);
            subFrame.add(labBahan);
            ArrayList<JRadioButton> tomBahan = new ArrayList();
            ArrayList<BahanKain> bahanKain = con.getAllBahan();

            for (int i = 0; i < bahanKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(bahanKain.get(i).getNama_bahan());
                tomBahan.add(tempTom);
            }
            int tempX = 10;
            int tempY = 115;
            for (int i = 0; i < tomBahan.size(); i++) {
                if (tempY > 205) {
                    tempY = 115;
                    tempX += 110;
                }
                tomBahan.get(i).setBounds(tempX, tempY, 100, 25);
                subFrame.add(tomBahan.get(i));
                tempY += 30;
            }

            JLabel labWarna = new JLabel("pilihan warna: ");
            labWarna.setBounds(10, 225, 100, 25);
            subFrame.add(labWarna);
            ArrayList<JRadioButton> tomWarna = new ArrayList();
            ArrayList<WarnaKain> warnaKain = con.getAllWarna();

            for (int i = 0; i < warnaKain.size(); i++) {
                JRadioButton tempTom = new JRadioButton(warnaKain.get(i).getNama_warna());
                tomWarna.add(tempTom);
            }
            int tempX2 = 10;
            int tempY2 = 255;
            for (int i = 0; i < tomWarna.size(); i++) {
                if (tempY2 > 345) {
                    tempY2 = 255;
                    tempX2 += 110;
                }
                tomWarna.get(i).setBounds(tempX2, tempY2, 100, 25);
                subFrame.add(tomWarna.get(i));
                tempY2 += 30;
            }
            JButton add = new JButton("Tambah");
            add.setBounds(10, 400, 100, 25);

            add.addActionListener(a -> {
                int harga = Integer.parseInt(inpHarga.getText());
                MotifKain tempo = con.getMotifBottom();
                int idMotif = tempo.getId_motif() + 1;
                MotifKain motif = new MotifKain(idMotif, inpMotif.getText(), harga);
                con.insertMotif(motif);
                int input = JOptionPane.showOptionDialog(null, "berhasil ditambah", "Berhasil", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (input == JOptionPane.OK_OPTION) {
                    ArrayList<String> idWarna = new ArrayList();
                    ArrayList<String> idBahan = new ArrayList();
                    ArrayList<String> namaWarna = new ArrayList();
                    ArrayList<String> namaBahan = new ArrayList();

                    for (int i = 0; i < warnaKain.size(); i++) {
                        if (tomWarna.get(i).isSelected()) {
                            WarnaKain temp = con.getWarna(tomWarna.get(i).getText());
                            namaWarna.add(temp.getNama_warna());
                            String idToAdd = Integer.toString(temp.getId_warna());
                            idWarna.add(idToAdd);
                        }
                    }
                    for (int i = 0; i < bahanKain.size(); i++) {
                        if (tomBahan.get(i).isSelected()) {
                            BahanKain temp = con.getBahan(tomBahan.get(i).getText());
                            namaBahan.add(temp.getNama_bahan());
                            String idToAdd = Integer.toString(temp.getId_bahan());
                            idBahan.add(idToAdd);
                        }
                    }
                    if (idWarna.size() >= idBahan.size()) {
                        for (int i = 0; i < idWarna.size(); i++) {
                            WarnaKain tempWarna = con.getWarna(namaWarna.get(i));
                            for (int j = 0; j < idBahan.size(); j++) {
                                String idKain = idBahan.get(j);
                                BahanKain tempBahan = con.getBahan(namaBahan.get(j));
                                idKain += "-" + idWarna.get(i) + "-" + motif.getId_motif();
                                kain_toko tempKain = new kain_toko(motif, tempWarna, tempBahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = idBahan.get(j);
                            }
                        }
                    } else {
                        for (int i = 0; i < idBahan.size(); i++) {
                            BahanKain tempBahan = con.getBahan(namaBahan.get(i));
                            for (int j = 0; j < idWarna.size(); j++) {
                                String idKain = idBahan.get(i);
                                WarnaKain tempWarna = con.getWarna(namaWarna.get(j));
                                idKain += "-" + idWarna.get(j) + "-" + motif.getId_motif();
                                kain_toko tempKain = new kain_toko(motif, tempWarna, tempBahan, 0, idKain);
                                con.insertKain(tempKain, idKain);
                                idKain = idBahan.get(j);
                            }
                        }
                    }
                };
            });
            subFrame.add(add);
            subFrame.add(backMenu);
            backMenu.setBounds(10, 450, 150, 40);
            backMenu.addActionListener(b -> {
                subFrame.dispose();
            });
            subFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(backMenu);

        frame.setLayout(
                null);
        frame.setVisible(
                true);
    }

    public static void main(String[] args) {
        new MenuInputKain();
    }
}
