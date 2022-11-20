package Model;

import java.sql.Timestamp;
import java.util.Date;

public class Message {

    private int id_message;
    private int id_pengirim;
    private String message;
    private Timestamp waktu;

    public Message() {
    }

    public Message(int id_message, int id_pengirim, String message, Timestamp waktu) {
        this.id_message = id_message;
        this.id_pengirim = id_pengirim;
        this.message = message;
        this.waktu = waktu;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public int getId_pengirim() {
        return id_pengirim;
    }

    public void setId_pengirim(int id_pengirim) {
        this.id_pengirim = id_pengirim;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getWaktu() {
        return waktu;
    }

    public void setWaktu(Timestamp waktu) {
        this.waktu = waktu;
    }
    
    
}
