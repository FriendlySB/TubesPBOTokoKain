package Model;

public class MotifKain {

    private int id_motif;
    private String nama_motif;
    private int harga_bahan;

    public MotifKain() {
    }

    public MotifKain(int id_motif, String nama_motif, int harga_bahan) {
        this.id_motif = id_motif;
        this.nama_motif = nama_motif;
        this.harga_bahan = harga_bahan;
    }

    public int getId_motif() {
        return id_motif;
    }

    public void setId_motif(int id_motif) {
        this.id_motif = id_motif;
    }

    public String getNama_motif() {
        return nama_motif;
    }

    public void setNama_motif(String nama_motif) {
        this.nama_motif = nama_motif;
    }

    public int getHarga_bahan() {
        return harga_bahan;
    }

    public void setHarga_bahan(int harga_bahan) {
        this.harga_bahan = harga_bahan;
    }
    
}
