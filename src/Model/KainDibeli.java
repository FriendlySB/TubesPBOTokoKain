package Model;

public class KainDibeli {
    private String id_kain;
    private String nama_kain;
    private int harga;

    public KainDibeli() {
    }
    
    public KainDibeli(String id_kain, String nama_kain, int harga) {
        this.id_kain = id_kain;
        this.nama_kain = nama_kain;
        this.harga = harga;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getId_kain() {
        return id_kain;
    }

    public void setId_kain(String id_kain) {
        this.id_kain = id_kain;
    }

    public String getNama_kain() {
        return nama_kain;
    }

    public void setNama_kain(String nama_kain) {
        this.nama_kain = nama_kain;
    }
    
}
