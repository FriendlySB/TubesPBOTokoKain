package Model;

public class DetailTransaksi {

    private int quantity;
    private int berat;
    private Kain kain;

    public DetailTransaksi() {
    }

    public DetailTransaksi(int quantity, int berat, Kain kain) {
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

    public Kain getKain() {
        return kain;
    }

    public void setKain(Kain kain) {
        this.kain = kain;
    }

    
}
