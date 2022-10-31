package Model;

public class WarnaKain {
    private int id_warna;
    private int nama_warna;
    private int harga_warna;

    public WarnaKain() {
    }

    public WarnaKain(int id_warna, int nama_warna, int harga_warna) {
        this.id_warna = id_warna;
        this.nama_warna = nama_warna;
        this.harga_warna = harga_warna;
    }

    public int getId_warna() {
        return id_warna;
    }

    public void setId_warna(int id_warna) {
        this.id_warna = id_warna;
    }

    public int getNama_warna() {
        return nama_warna;
    }

    public void setNama_warna(int nama_warna) {
        this.nama_warna = nama_warna;
    }

    public int getHarga_warna() {
        return harga_warna;
    }

    public void setHarga_warna(int harga_warna) {
        this.harga_warna = harga_warna;
    }
    
}
