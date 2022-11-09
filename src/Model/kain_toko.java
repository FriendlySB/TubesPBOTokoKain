/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author lenovo
 */
public class kain_toko extends Kain{

    private MotifKain motif;
    private WarnaKain warna;
    private BahanKain bahan;
    private int stok;

    public kain_toko(MotifKain motif, WarnaKain warna, BahanKain bahan, int stok, String id_kain) {
        super(id_kain);
        this.motif = motif;
        this.warna = warna;
        this.bahan = bahan;
        this.stok = stok;
    }

    public kain_toko() {
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
}
