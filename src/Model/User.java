package Model;

public abstract class User {

    private int id_user;
    private String username;
    private String nama_lengkap;
    private String email;
    private String password;
    private TipeUser tipeuser;

    public User() {
    }

    public User(int id_user, String username, String nama_lengkap, String email, String password, TipeUser tipeuser) {
        this.id_user = id_user;
        this.username = username;
        this.nama_lengkap = nama_lengkap;
        this.email = email;
        this.password = password;
        this.tipeuser = tipeuser;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipeUser getTipeuser() {
        return tipeuser;
    }

    public void setTipeuser(TipeUser tipeuser) {
        this.tipeuser = tipeuser;
    }
    
    
}
