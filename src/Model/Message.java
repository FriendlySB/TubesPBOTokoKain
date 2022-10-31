package Model;

import java.util.Date;

public class Message {

    private int id_message;
    private String pesan;
    private Date waktu;

    public Message() {
    }
    
    public Message(int id_message, String pesan, Date waktu) {
        this.id_message = id_message;
        this.pesan = pesan;
        this.waktu = waktu;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Date getWaktu() {
        return waktu;
    }

    public void setWaktu(Date waktu) {
        this.waktu = waktu;
    }
    
}
