package Controller;

import Model.*;
import java.util.ArrayList;

public class Controller {

    public Controller() {
        
    }
    
    public String createIDKainCustom(int noKain){
        String id = "CUSTOM-" + String.valueOf(noKain);
        return id;
    }
    
    public String getNamaKain(String id_kain){
        String nama = "Kain ";
        nama += getNamaBahan(id_kain) + " ";
        nama += getNamaWarna(id_kain) + " ";
        nama += getNamaMotif(id_kain);
        return nama;
    }
    
    public String getNamaBahan(String id_kain){
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[0]) - 1000;
        String bahan = "";
        ArrayList<BahanKain> listBahan = new ArrayList<>(sql.getAllBahan());
        for(int i = 0; i < listBahan.size(); i++){
            if(id == listBahan.get(i).getId_bahan()){
                bahan = listBahan.get(i).getNama_bahan();
            }
        }
        return bahan;
    }
    
    public String getNamaWarna(String id_kain){
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[1]) -2000;
        String warna = "";
        ArrayList<WarnaKain> listWarna = new ArrayList<>(sql.getAllWarna());
        for(int i = 0; i < listWarna.size(); i++){
            if(id == listWarna.get(i).getId_warna()){
                warna = listWarna.get(i).getNama_warna();
            }
        }
        return warna;
    }
    
    public String getNamaMotif(String id_kain){
        Sql sql = new Sql();
        int id = Integer.parseInt(id_kain.split("-")[2]) - 3000;
        String motif = "";
        ArrayList<MotifKain> listMotif = new ArrayList<>(sql.getAllMotif());
        for(int i = 0; i < listMotif.size(); i++){
            if(id == listMotif.get(i).getId_motif()){
                motif = listMotif.get(i).getNama_motif();
            }
        }
        return motif;
    }
    
    public boolean cekStokKain(String id_kain, int jumlahDibeli){
        Sql sql = new Sql();
        int stokKain = sql.countStockKain(id_kain);
        if(jumlahDibeli <= stokKain){
            return true;
        }
        return false;
    }
}
