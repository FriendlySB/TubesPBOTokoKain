package View;

import Controller.Controller;
import Controller.Sql;
import Model.CurrentUser;
import Model.Customer;
import Model.DetailTransaksi;
import Model.KainDibeli;
import Model.Keranjang;
import Model.Progress;
import Model.TipeBayar;
import Model.TipePengiriman;
import Model.Transaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lenovo
 */
public class MenuPilihMetodePembayaran {

    Customer curCust = (Customer) CurrentUser.getInstance().getUser();

    public MenuPilihMetodePembayaran(ArrayList<Keranjang> listKeranjangDipilih, int kurir, String alamat, int totalBiayaPengiriman) {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setTitle("Pilih Metode Pembayaran");
        JLabel labelTipeBayar = new JLabel("Pilih Metode Pembayaran");
        labelTipeBayar.setBounds(10, 30, 150, 30);
        JComboBox comboTipeBayar = new JComboBox();
        comboTipeBayar.setBounds(160, 30, 200, 30);
        Controller control = new Controller();
        comboTipeBayar.addItem(control.getTipeBayar(TipeBayar.GOPAY));
        comboTipeBayar.addItem(control.getTipeBayar(TipeBayar.OVO));
        comboTipeBayar.addItem(control.getTipeBayar(TipeBayar.TRANSFER));
        if (kurir == TipePengiriman.PICKUP) {
             comboTipeBayar.addItem(control.getTipeBayar(TipeBayar.CASH));
        }else if (kurir == TipePengiriman.PEGAWAI) {
             comboTipeBayar.addItem(control.getTipeBayar(TipeBayar.COD));
        }
        frame.add(labelTipeBayar);
        frame.add(comboTipeBayar);
        JButton buttonPilihMetodePembayaran = new JButton("Bayar");
        buttonPilihMetodePembayaran.setBounds(10, 70, 200, 30);
        buttonPilihMetodePembayaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sql sql = new Sql();
                Controller controller = new Controller();
                TipeBayar tipeBayar = (TipeBayar) comboTipeBayar.getSelectedItem();
                Transaksi tempo = sql.getTransaksiBottom(CurrentUser.getInstance().getUser().getId_user());
                int idTransaksi = tempo.getId_transaksi() + 1;
                ArrayList<DetailTransaksi> listDetailTransaksi = new ArrayList<>();
                for (int i = 0; i < listKeranjangDipilih.size(); i++) {
                    KainDibeli curKainDibeli = controller.ubahKainKeranjangMenjadiKainDibeli(listKeranjangDipilih.get(i));
                    listDetailTransaksi.add(new DetailTransaksi(listKeranjangDipilih.get(i).getQuantity(), listKeranjangDipilih.get(i).getQuantity(), curKainDibeli));
                    controller.kurangiStokToko(listKeranjangDipilih.get(i));
                }
                Date date = new Date();
                Timestamp timestamp2 = new Timestamp(date.getTime());
                int totalBayar = totalBiayaPengiriman + controller.hitungTotalDetailTransaksi(listDetailTransaksi);
                Transaksi curTransaksi = new Transaksi(idTransaksi, kurir, alamat, totalBayar, Progress.DIBUAT, tipeBayar, listDetailTransaksi, timestamp2);
                sql.insertTransaksi(CurrentUser.getInstance().getUser().getId_user(), curTransaksi);
                for (int i = 0; i < listKeranjangDipilih.size(); i++) {
                    sql.deleteKainKeranjang(listKeranjangDipilih.get(i).getKain().getId_kain(), CurrentUser.getInstance().getUser().getId_user());
                }
                frame.dispose();
                new MainMenuUser();
            }
        });
        frame.add(buttonPilihMetodePembayaran);
        frame.setVisible(true);
    }
}
