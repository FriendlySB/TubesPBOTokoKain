package Model;

public class MotifKain {

    private int id_motif;
    private String nama_motif;
    private int harga_motif;

    public MotifKain() {
    }

    public MotifKain(int id_motif, String nama_motif, int harga_motif) {
        this.id_motif = id_motif;
        this.nama_motif = nama_motif;
        this.harga_motif = harga_motif;
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

    public int getHarga_motif() {
        return harga_motif;
    }

    public void setHarga_motif(int harga_motif) {
        this.harga_motif = harga_motif;
    }
    
}
