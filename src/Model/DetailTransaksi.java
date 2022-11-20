package Model;

public class DetailTransaksi {

    private int quantity;
    private int berat;
    private KainDibeli kain;

    public DetailTransaksi() {
    }

    public DetailTransaksi(int quantity, int berat, KainDibeli kain) {
        this.quantity = quantity;
        this.berat = berat;
        this.kain = kain;
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

    public KainDibeli getKain() {
        return kain;
    }

    public void setKain(KainDibeli kain) {
        this.kain = kain;
    }
    
}
