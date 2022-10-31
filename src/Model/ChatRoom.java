package Model;
import java.util.ArrayList;
public class ChatRoom {

    private int id_chat_room;
    private String nama_room;
    private ArrayList<Message> message;

    public ChatRoom() {
    }

    public ChatRoom(int id_chat_room, String nama_room, ArrayList<Message> message) {
        this.id_chat_room = id_chat_room;
        this.nama_room = nama_room;
        this.message = message;
    }

    public int getId_chat_room() {
        return id_chat_room;
    }

    public void setId_chat_room(int id_chat_room) {
        this.id_chat_room = id_chat_room;
    }

    public String getNama_room() {
        return nama_room;
    }

    public void setNama_room(String nama_room) {
        this.nama_room = nama_room;
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<Message> message) {
        this.message = message;
    }
    

}
