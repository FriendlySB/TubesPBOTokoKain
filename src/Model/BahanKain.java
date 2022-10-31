package Model;

public class BahanKain {

    private int id_bahan;
    private String nama_bahan;
    private int harga_bahan;

    public BahanKain() {
    }

    public BahanKain(int id_bahan, String nama_bahan, int harga_bahan) {
        this.id_bahan = id_bahan;
        this.nama_bahan = nama_bahan;
        this.harga_bahan = harga_bahan;
    }

    public int getId_bahan() {
        return id_bahan;
    }

    public void setId_bahan(int id_bahan) {
        this.id_bahan = id_bahan;
    }

    public String getNama_bahan() {
        return nama_bahan;
    }

    public void setNama_bahan(String nama_bahan) {
        this.nama_bahan = nama_bahan;
    }

    public int getHarga_bahan() {
        return harga_bahan;
    }

    public void setHarga_bahan(int harga_bahan) {
        this.harga_bahan = harga_bahan;
    }

}
