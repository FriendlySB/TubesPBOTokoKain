package Model;

public class DetailTransaksi {

    private int detail_transaksi;
    private int quantity;
    private int berat;
    private KainCustom kainCustom;
    private Kain kain;

    public DetailTransaksi() {
    }

    public DetailTransaksi(int detail_transaksi, int quantity, int berat, KainCustom kainCustom, Kain kain) {
        this.detail_transaksi = detail_transaksi;
        this.quantity = quantity;
        this.berat = berat;
        this.kainCustom = kainCustom;
        this.kain = kain;
    }

    public int getDetail_transaksi() {
        return detail_transaksi;
    }

    public void setDetail_transaksi(int detail_transaksi) {
        this.detail_transaksi = detail_transaksi;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public KainCustom getKainCustom() {
        return kainCustom;
    }

    public void setKainCustom(KainCustom kainCustom) {
        this.kainCustom = kainCustom;
    }

    public Kain getKain() {
        return kain;
    }

    public void setKain(Kain kain) {
        this.kain = kain;
    }

    
}
