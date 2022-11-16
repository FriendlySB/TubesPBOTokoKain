package Model;

public abstract class Kain {

    private String id_kain;
   

    public Kain(String id_kain) {
        this.id_kain = id_kain;
    }

    public Kain() {
    }

    public String getId_kain() {
        return id_kain;
    }

    public void setId_kain(String id_kain) {
        this.id_kain = id_kain;
    }

}
