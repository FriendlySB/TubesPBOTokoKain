package Model;

public class DetailKeranjang {

    private int quantity;
    private Kain kain;
    private KainCustom kainCustom;

    public DetailKeranjang() {
    }

    public DetailKeranjang(int quantity, Kain kain, KainCustom kainCustom) {
        this.quantity = quantity;
        this.kain = kain;
        this.kainCustom = kainCustom;
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

    public KainCustom getKainCustom() {
        return kainCustom;
    }

    public void setKainCustom(KainCustom kainCustom) {
        this.kainCustom = kainCustom;
    }

    
}
