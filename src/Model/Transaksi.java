package Model;

import java.util.ArrayList;

public class Transaksi implements TipePengiriman {

    private int id_transaksi;
    private int tipe_pengiriman;
    private String alamat;
    private int total_bayar;
    private Progress progress;
    private TipeBayar tipeBayar;
    private TipePengiriman tipePengiriman;
    private ArrayList<DetailTransaksi> detailTransaksi;

    public Transaksi() {
    }

    public Transaksi(int id_transaksi, int tipe_pengiriman, String alamat, int total_bayar, Progress progress, TipeBayar tipeBayar, TipePengiriman tipePengiriman, ArrayList<DetailTransaksi> detailTransaksi) {
        this.id_transaksi = id_transaksi;
        this.tipe_pengiriman = tipe_pengiriman;
        this.alamat = alamat;
        this.total_bayar = total_bayar;
        this.progress = progress;
        this.tipeBayar = tipeBayar;
        this.tipePengiriman = tipePengiriman;
        this.detailTransaksi = detailTransaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getTipe_pengiriman() {
        return tipe_pengiriman;
    }

    public void setTipe_pengiriman(int tipe_pengiriman) {
        this.tipe_pengiriman = tipe_pengiriman;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getTotal_bayar() {
        return total_bayar;
    }

    public void setTotal_bayar(int total_bayar) {
        this.total_bayar = total_bayar;
    }

    public Progress getProgress() {
        return progress;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    public TipeBayar getTipeBayar() {
        return tipeBayar;
    }

    public void setTipeBayar(TipeBayar tipeBayar) {
        this.tipeBayar = tipeBayar;
    }

    public TipePengiriman getTipePengiriman() {
        return tipePengiriman;
    }

    public void setTipePengiriman(TipePengiriman tipePengiriman) {
        this.tipePengiriman = tipePengiriman;
    }

    public ArrayList<DetailTransaksi> getDetailTransaksi() {
        return detailTransaksi;
    }

    public void setDetailTransaksi(ArrayList<DetailTransaksi> detailTransaksi) {
        this.detailTransaksi = detailTransaksi;
    }

}
