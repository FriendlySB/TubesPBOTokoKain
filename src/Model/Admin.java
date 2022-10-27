
package Model;

public class Admin {
    int id_admin;
    int id_user;

    public Admin(int id_admin, int id_user) {
        this.id_admin = id_admin;
        this.id_user = id_user;
    }

    public Admin() {
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Admin{" + "id_admin=" + id_admin + ", id_user=" + id_user + '}';
    }
    
}
