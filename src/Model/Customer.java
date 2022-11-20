
package Model;
import java.util.ArrayList;

public class Customer extends User {
    private String alamat;
    private String noTelpon;
    private ArrayList<Message> message;
    private ArrayList<Keranjang> keranjang;
    private ArrayList<Transaksi> transaksi;

    public Customer() {
    }

    public Customer(String alamat, String noTelpon, ArrayList<Message> message, ArrayList<Keranjang> keranjang, ArrayList<Transaksi> transaksi) {
        this.alamat = alamat;
        this.noTelpon = noTelpon;
        this.message = message;
        this.keranjang = keranjang;
        this.transaksi = transaksi;
    }

    public Customer(String alamat, String noTelpon, ArrayList<Message> message, ArrayList<Keranjang> keranjang, ArrayList<Transaksi> transaksi, int id_user, String username, String nama_lengkap, String email, String password, TipeUser tipeuser) {
        super(id_user, username, nama_lengkap, email, password, tipeuser);
        this.alamat = alamat;
        this.noTelpon = noTelpon;
        this.message = message;
        this.keranjang = keranjang;
        this.transaksi = transaksi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<Message> message) {
        this.message = message;
    }

    public ArrayList<Keranjang> getKeranjang() {
        return keranjang;
    }

    public void setKeranjang(ArrayList<Keranjang> keranjang) {
        this.keranjang = keranjang;
    }

    public ArrayList<Transaksi> getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(ArrayList<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }

    
}
