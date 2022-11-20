package Model;

public class Admin extends User implements TipeAdmin {

    private int tipeAdmin;
    private ChatRoom chatroom;

    public Admin() {
    }

    public Admin(int tipeAdmin) {
        this.tipeAdmin = tipeAdmin;
    }

    public Admin(int tipeAdmin, int id_user, String username, String nama_lengkap, String email, String password, TipeUser tipeuser) {
        super(id_user, username, nama_lengkap, email, password, tipeuser);
        this.tipeAdmin = tipeAdmin;
    }

    public int getTipeAdmin() {
        return tipeAdmin;
    }

    public void setTipeAdmin(int tipeAdmin) {
        this.tipeAdmin = tipeAdmin;
    }

    public ChatRoom getChatroom() {
        return chatroom;
    }

    public void setChatroom(ChatRoom chatroom) {
        this.chatroom = chatroom;
    }

    
    
}
