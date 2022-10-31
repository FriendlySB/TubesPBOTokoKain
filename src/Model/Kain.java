package Model;

public class Kain {

    private String id_kain;
    private int harga_kain;
    private int stok;
    private MotifKain motif;
    private WarnaKain warna;
    private BahanKain bahan;

    public Kain(String id_kain, int harga_kain, int stok, MotifKain motif, WarnaKain warna, BahanKain bahan) {
        this.id_kain = id_kain;
        this.harga_kain = harga_kain;
        this.stok = stok;
        this.motif = motif;
        this.warna = warna;
        this.bahan = bahan;
    }

    public Kain() {
    }

    public String getId_kain() {
        return id_kain;
    }

    public void setId_kain(String id_kain) {
        this.id_kain = id_kain;
    }

    public int getHarga_kain() {
        return harga_kain;
    }

    public void setHarga_kain(int harga_kain) {
        this.harga_kain = harga_kain;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public MotifKain getMotif() {
        return motif;
    }

    public void setMotif(MotifKain motif) {
        this.motif = motif;
    }

    public WarnaKain getWarna() {
        return warna;
    }

    public void setWarna(WarnaKain warna) {
        this.warna = warna;
    }

    public BahanKain getBahan() {
        return bahan;
    }

    public void setBahan(BahanKain bahan) {
        this.bahan = bahan;
    }
    

}
