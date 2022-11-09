package Model;

import java.util.ArrayList;

public class Keranjang {

    private ArrayList<DetailKeranjang> detailKeranjang;
    private int id_keranjang;

    public Keranjang(ArrayList<DetailKeranjang> detailKeranjang, int id_keranjang) {
        this.detailKeranjang = detailKeranjang;
        this.id_keranjang = id_keranjang;
    }

    public Keranjang() {
    }

    public ArrayList<DetailKeranjang> getDetailKeranjang() {
        return detailKeranjang;
    }

    public void setDetailKeranjang(ArrayList<DetailKeranjang> detailKeranjang) {
        this.detailKeranjang = detailKeranjang;
    }

    public int getId_keranjang() {
        return id_keranjang;
    }

    public void setId_keranjang(int id_keranjang) {
        this.id_keranjang = id_keranjang;
    }
    
}
