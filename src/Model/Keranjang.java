package Model;

import java.util.ArrayList;

public class Keranjang {

    private ArrayList<DetailKeranjang> detailKeranjang;
    private int id_user;
    private int id_keranjang;

    public Keranjang(ArrayList<DetailKeranjang> detailKeranjang, int id_user, int id_keranjang) {
        this.detailKeranjang = detailKeranjang;
        this.id_user = id_user;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_keranjang() {
        return id_keranjang;
    }

    public void setId_keranjang(int id_keranjang) {
        this.id_keranjang = id_keranjang;
    }
    
}
