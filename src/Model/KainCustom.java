package Model;

public class KainCustom extends Kain {

    private String bahan_kain_custom;
    private String warna_kain_custom;
    private String motif_kain_custom;
    private int harga_kain_custom;

    public KainCustom() {
    }

    public KainCustom(String bahan_kain_custom, String warna_kain_custom, String motif_kain_custom, int harga_kain_custom, String id_kain) {
        super(id_kain);
        this.bahan_kain_custom = bahan_kain_custom;
        this.warna_kain_custom = warna_kain_custom;
        this.motif_kain_custom = motif_kain_custom;
        this.harga_kain_custom = harga_kain_custom;
    }

    public String getBahan_kain_custom() {
        return bahan_kain_custom;
    }

    public void setBahan_kain_custom(String bahan_kain_custom) {
        this.bahan_kain_custom = bahan_kain_custom;
    }

    public String getWarna_kain_custom() {
        return warna_kain_custom;
    }

    public void setWarna_kain_custom(String warna_kain_custom) {
        this.warna_kain_custom = warna_kain_custom;
    }

    public String getMotif_kain_custom() {
        return motif_kain_custom;
    }

    public void setMotif_kain_custom(String motif_kain_custom) {
        this.motif_kain_custom = motif_kain_custom;
    }

    public int getHarga_kain_custom() {
        return harga_kain_custom;
    }

    public void setHarga_kain_custom(int harga_kain_custom) {
        this.harga_kain_custom = harga_kain_custom;
    }

}
