package Model;

public class Admin extends User {

    private int id_admin;
    private ChatRoom chatroom;

    public Admin() {
    }

    public Admin(int id_admin, ChatRoom chatroom, int id_user, String username, String nama_lengkap, String email, String password, Tipeuser tipeuser) {
        super(id_user, username, nama_lengkap, email, password, tipeuser);
        this.id_admin = id_admin;
        this.chatroom = chatroom;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
    }
    
}
