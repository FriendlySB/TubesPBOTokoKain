
package Model;
import java.util.ArrayList;

public class Customer extends User {
    private String alamat;
    private String noTelpon;
    private ChatRoom chatroom;
    private ArrayList<Keranjang> keranjang;
    private ArrayList<Transaksi> transaksi;

    public Customer() {
    }

    public Customer(String alamat, String noTelpon, ChatRoom chatroom, ArrayList<Keranjang> keranjang, ArrayList<Transaksi> transaksi, int id_user, String username, String nama_lengkap, String email, String password, TipeUser tipeuser) {
        super(id_user, username, nama_lengkap, email, password, tipeuser);
        this.alamat = alamat;
        this.noTelpon = noTelpon;
        this.chatroom = chatroom;
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

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
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
