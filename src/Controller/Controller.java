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

    public boolean cekHargaKainCustom(KainCustom curKain) {
        if (curKain.getHarga_kain_custom() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int totalBiayaPengiriman(int tipePengiriman, ArrayList<Keranjang> listKeranjang) {
        int totalKG = hitungTotalBeratKeranjang(listKeranjang);
        int totalHarga = 0;
        switch (tipePengiriman) {
            case JNE:
                totalHarga = totalKG * 3000;
                break;
            case GOJEK:
                totalHarga = totalKG * 2000;
                break;
            case JNT:
                totalHarga = totalKG * 4000;
                break;
            case PEGAWAI:
                totalHarga = totalKG * 1000;
                break;
        }
        return totalHarga;
    }

    public int hitungTotalBeratKeranjang(ArrayList<Keranjang> listKeranjang) {
        int total = 0;
        for (int i = 0; i < listKeranjang.size(); i++) {
            total += listKeranjang.get(i).getQuantity();
        }
        return total;
    }

    public KainDibeli ubahKainKeranjangMenjadiKainDibeli(Keranjang curKeranjang) {
        Kain curKain = curKeranjang.getKain();
        KainDibeli curKainDibeli = new KainDibeli();

        curKainDibeli.setId_kain(curKeranjang.getKain().getId_kain());
        if (curKain instanceof KainCustom) {
            KainCustom curKainCustom = (KainCustom) curKain;
            curKainDibeli.setNama_kain(getNamaKainCustom(curKainCustom.getId_kain()));
            curKainDibeli.setHarga(curKainCustom.getHarga_kain_custom());
        } else if (curKain instanceof KainToko) {
            KainToko curKainToko = (KainToko) curKain;
            curKainDibeli.setNama_kain(getNamaKainToko(curKainToko));
            curKainDibeli.setHarga(totalHargaKainToko(curKainToko));
        }
        return curKainDibeli;
    }

    public int totalHargaKainToko(KainToko curKainToko) {
        return curKainToko.getBahan().getHarga_bahan() + curKainToko.getWarna().getHarga_warna() + curKainToko.getMotif().getHarga_motif();
    }

    //Contoh polymorphism
    public String createIDKain(BahanKain bahan, WarnaKain warna, MotifKain motif) {
        Sql sql = new Sql();
        String idKain = "";
        idKain += String.valueOf(bahan.getId_bahan()) + "-";
        idKain += String.valueOf(warna.getId_warna()) + "-";
        idKain += String.valueOf(motif.getId_motif());
        return idKain;
    }

    public String createIDKain() {
        Sql sql = new Sql();
        String lastKain = sql.getIDKainCustomBottom();
        if (lastKain.equals("")) {
            return "CUSTOM-1";
        }
        String id = "CUSTOM-" + String.valueOf(Integer.valueOf(lastKain.split("-")[1]) + 1);
        return id;
    }

    public String getNamaKain(String id_kain) {
        String nama = "Kain ";
        nama += getNamaBahan(id_kain) + " ";
        nama += getNamaWarna(id_kain) + " ";
        nama += getNamaMotif(id_kain);
        return nama;
    }

    public String getNamaKainToko(KainToko kain) {
        String nama = "Kain ";
        nama += kain.getBahan().getNama_bahan() + " ";
        nama += kain.getWarna().getNama_warna() + " ";
        nama += kain.getMotif().getNama_motif();
        return nama;
    }

    public String getNamaKainCustom(String id_kain) {
        Sql sql = new Sql();
        KainCustom curKain = sql.getKainCustomWithId_Kain(id_kain);
        String nama = "Kain ";
        nama += curKain.getBahan_kain_custom() + " ";
        nama += curKain.getWarna_kain_custom() + " ";
        nama += curKain.getMotif_kain_custom();
        return nama;
    }

    public String getNamaBahan(String id_kain) {
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[0]);
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
        int id = Integer.parseInt(id_kain.split("-")[1]);
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
        int id = Integer.parseInt(id_kain.split("-")[2]);
        String motif = "";
        ArrayList<MotifKain> listMotif = new ArrayList<>(sql.getAllMotif());
        for (int i = 0; i < listMotif.size(); i++) {
            if (id == listMotif.get(i).getId_motif()) {
                motif = listMotif.get(i).getNama_motif();
            }
        }
        return motif;
    }

    public int hitungHargaKainToko(KainToko kain) {
        return kain.getBahan().getHarga_bahan()
                + kain.getWarna().getHarga_warna()
                + kain.getMotif().getHarga_motif();
    }

    public void kurangiStokToko(Keranjang keranjang) {
        Sql sql = new Sql();
        if (keranjang.getKain() instanceof KainToko) {
            int totalStok = sql.countStockKain(keranjang.getKain().getId_kain()) - 1;
            sql.updateStokKain(keranjang.getKain().getId_kain(), totalStok);
        }
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
            return "Transfer Bank";
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
        if (tipePengiriman == PICKUP) {
            return "Pickup";
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

    public Object[] createIsiTableDetailTransaksi(DetailTransaksi detail, int nomor) {
        int no = nomor + 1;
        KainDibeli kain = detail.getKain();
        String id = kain.getId_kain();
        String nama = kain.getNama_kain();
        int harga = kain.getHarga();
        int quantity = detail.getQuantity();
        int totalHarga = harga * quantity;

        Object[] data = {no, id, nama, harga, quantity, totalHarga};
        return data;
    }

    public int hitungTotalDetailTransaksi(ArrayList<DetailTransaksi> listDetail) {
        int total = 0;
        for (int i = 0; i < listDetail.size(); i++) {
            total += listDetail.get(i).getQuantity() * listDetail.get(i).getKain().getHarga();
        }
        return total;
    }

    public boolean cekKainDuplikatKeranjang(int id_user, String id_kain) {
        Sql sql = new Sql();
        ArrayList<Keranjang> cart = new ArrayList<>(sql.getKeranjang(id_user));
        for (int i = 0; i < cart.size(); i++) {
            if (id_kain.equals(cart.get(i).getKain().getId_kain())) {
                return true;
            }
        }
        return false;
    }

    public boolean cekIDKainDuplikat(String id_kain) {
        Sql sql = new Sql();
        ArrayList<String> listIDKain = new ArrayList<>(sql.getAllIDKain());
        for (int i = 0; i < listIDKain.size(); i++) {
            if (id_kain.equals(listIDKain.get(i))) {
                return true;
            }
        }
        return false;
    }

    public String createMessagesForChat(int id_user) {
        Sql sql = new Sql();
        String text = "";
        ArrayList<Message> listMessage = new ArrayList<>(sql.getMessage(id_user));
        if (listMessage.isEmpty()) {
            return "Chat Anda masih kosong";
        } else {
            for (int i = 0; i < listMessage.size(); i++) {
                Message msg = listMessage.get(i);
                String pengirim = sql.getUsernameByID(msg.getId_pengirim());
                String pesan = msg.getMessage();
                String waktu = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(msg.getWaktu());
                text += pengirim + "    " + waktu + "\n";
                text += pesan + "\n\n";
            }
        }
        return text;
    }

    public ArrayList<Customer> getCustomerFromUser(ArrayList<User> listUser) {
        ArrayList<Customer> listCustomer = new ArrayList<>();
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i) instanceof Customer) {
                listCustomer.add((Customer) listUser.get(i));
            }
        }
        return listCustomer;
    }

    public Message getLastMessage(ArrayList<Message> listMessage) {
        if (listMessage.size() == 0) {
            return null;
        }
        return listMessage.get(listMessage.size() - 1);
    }
    
    public Transaksi getTransaksiByID(ArrayList<Transaksi> listTransaksi, int id_transaksi){
        Transaksi transaksi = null;
        for(int i = 0; i < listTransaksi.size(); i++){
            if(listTransaksi.get(i).getId_transaksi() == id_transaksi){
                transaksi = listTransaksi.get(i);
            }
        }
        return transaksi;
    }
    
    public int totalBiayaPengirimanTransaksi(int tipePengiriman, ArrayList<DetailTransaksi> listDetail) {
        int totalKG = hitungTotalBeratTransaksi(listDetail);
        int totalHarga = 0;
        switch (tipePengiriman) {
            case JNE:
                totalHarga = totalKG * 3000;
                break;
            case GOJEK:
                totalHarga = totalKG * 2000;
                break;
            case JNT:
                totalHarga = totalKG * 4000;
                break;
            case PEGAWAI:
                totalHarga = totalKG * 1000;
                break;
        }
        return totalHarga;
    }

    public int hitungTotalBeratTransaksi(ArrayList<DetailTransaksi> listDetail) {
        int total = 0;
        for (int i = 0; i < listDetail.size(); i++) {
            total += listDetail.get(i).getQuantity();
        }
        return total;
    }
}
