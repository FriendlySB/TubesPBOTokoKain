package Model;

public class Keranjang {

    private String id_kain;
    private int quantity;

    public Keranjang() {
    }

    public Keranjang(String id_kain, int quantity) {
        this.id_kain = id_kain;
        this.quantity = quantity;
    }

    public String getId_kain() {
        return id_kain;
    }

    public void setId_kain(String id_kain) {
        this.id_kain = id_kain;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
