package Model;

public class DetailKeranjang {

    private int quantity;
    private Kain kain;

    public DetailKeranjang() {
    }

    public DetailKeranjang(int quantity, Kain kain) {
        this.quantity = quantity;
        this.kain = kain;
        
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Kain getKain() {
        return kain;
    }

    public void setKain(Kain kain) {
        this.kain = kain;
    }

}
