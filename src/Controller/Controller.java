package Controller;

import Model.*;
import View.MenuLihatDetailTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controller implements TipePengiriman {

    public Controller() {

    }

    public String createIDKainCustom(int noKain) {
        String id = "CUSTOM-" + String.valueOf(noKain);
        return id;
    }

    public String getNamaKain(String id_kain) {
        String nama = "Kain ";
        nama += getNamaBahan(id_kain) + " ";
        nama += getNamaWarna(id_kain) + " ";
        nama += getNamaMotif(id_kain);
        return nama;
    }

    public String getNamaKainCustom(String id_kain) {
        Sql sql = new Sql();
        KainCustom curKain = sql.getKainCustomWithId_Kain(id_kain);
        String nama = "Kain ";
        nama += curKain.getBahan_kain_custom()+" ";
        nama += curKain.getWarna_kain_custom() + " ";
        nama += curKain.getMotif_kain_custom();
        return nama;
    }

    public String getNamaBahan(String id_kain) {
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[0]) - 1000;
        String bahan = "";
        ArrayList<BahanKain> listBahan = new ArrayList<>(sql.getAllBahan());
        for (int i = 0; i < listBahan.size(); i++) {
            if (id == listBahan.get(i).getId_bahan()) {
                bahan = listBahan.get(i).getNama_bahan();
            }
        }
        return bahan;
    }

    public String getNamaWarna(String id_kain) {
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[1]) - 2000;
        String warna = "";
        ArrayList<WarnaKain> listWarna = new ArrayList<>(sql.getAllWarna());
        for (int i = 0; i < listWarna.size(); i++) {
            if (id == listWarna.get(i).getId_warna()) {
                warna = listWarna.get(i).getNama_warna();
            }
        }
        return warna;
    }

    public String getNamaMotif(String id_kain) {
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[2]) - 3000;
        String motif = "";
        ArrayList<MotifKain> listMotif = new ArrayList<>(sql.getAllMotif());
        for (int i = 0; i < listMotif.size(); i++) {
            if (id == listMotif.get(i).getId_motif()) {
                motif = listMotif.get(i).getNama_motif();
            }
        }
        return motif;
    }

    public boolean cekStokKain(String id_kain, int jumlahDibeli) {
        Sql sql = new Sql();
        int stokKain = sql.countStockKain(id_kain);
        if (jumlahDibeli <= stokKain) {
            return true;
        }
        return false;
    }

    public String getTipeBayar(TipeBayar tipe) {
        if (tipe.equals(TipeBayar.CASH)) {
            return "Cash";
        }
        if (tipe.equals(TipeBayar.COD)) {
            return "COD";
        }
        if (tipe.equals(TipeBayar.GOPAY)) {
            return "GoPay";
        }
        if (tipe.equals(TipeBayar.OVO)) {
            return "OVO";
        }
        if (tipe.equals(TipeBayar.TRANSFER)) {
            return "TransferBank";
        }
        return "Unknown";
    }

    public String getTipePengiriman(int tipePengiriman) {
        if (tipePengiriman == GOJEK) {
            return "Gojek";
        }
        if (tipePengiriman == JNE) {
            return "JNE";
        }
        if (tipePengiriman == JNT) {
            return "JNT";
        }
        if (tipePengiriman == PEGAWAI) {
            return "Pegawai";
        }
        return "Unknown";
    }

    public String getProgress(Progress progress) {
        if (progress.equals(Progress.DIBUAT)) {
            return "Sedang dibuat";
        }
        if (progress.equals(Progress.DIKEMAS)) {
            return "Sedang dikemas";
        }
        if (progress.equals(Progress.DIKIRIM)) {
            return "Sedang dikirim";
        }
        if (progress.equals(Progress.SELESAI)) {
            return "Selesai";
        }
        return "Unknown";
    }

    public Object[] createIsiTableTransaksi(Transaksi transaksi) {
        int id = transaksi.getId_transaksi();
        String waktu = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transaksi.getWaktu_transaksi());
        String tipeBayar = getTipeBayar(transaksi.getTipeBayar());
        String tipePengiriman = getTipePengiriman(transaksi.getTipe_pengiriman());
        String progress = getProgress(transaksi.getProgress());
        int totalBayar = transaksi.getTotal_bayar();
        Object[] data = {id, waktu, tipeBayar, tipePengiriman, progress, totalBayar};
        return data;
    }
}
