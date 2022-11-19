/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Admin;
import Model.BahanKain;
import Model.ChatRoom;
import Model.Customer;
import Model.DetailTransaksi;
import Model.Kain;
import Model.KainCustom;
import Model.Keranjang;
import Model.Message;
import Model.MotifKain;
import Model.Progress;
import Model.TipeBayar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.User;
import Model.TipeUser;
import Model.Transaksi;
import Model.WarnaKain;
import Model.kain_toko;
import java.sql.PreparedStatement;

public class Sql {

    static DatabaseHandler conn = new DatabaseHandler();

    public ArrayList<BahanKain> getAllBahan() {
        ArrayList<BahanKain> listBahan = new ArrayList();
        conn.connect();
        String query = "SELECT * FROM bahan";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                BahanKain bahan = new BahanKain();
                bahan.setId_bahan(rs.getInt("id_bahan"));
                bahan.setNama_bahan(rs.getString("nama_bahan"));
                bahan.setHarga_bahan(rs.getInt("harga_bahan"));
                listBahan.add(bahan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBahan;
    }

    public ArrayList<WarnaKain> getAllWarna() {
        ArrayList<WarnaKain> listWarna = new ArrayList();
        conn.connect();
        String query = "SELECT * FROM warna";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                WarnaKain warna = new WarnaKain();
                warna.setId_warna(rs.getInt("id_warna"));
                warna.setNama_warna(rs.getString("nama_warna"));
                warna.setHarga_warna(rs.getInt("harga_warna"));
                listWarna.add(warna);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listWarna;
    }

    public ArrayList<MotifKain> getAllMotif() {
        ArrayList<MotifKain> listMotif = new ArrayList();
        conn.connect();
        String query = "SELECT * FROM motif";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                MotifKain motif = new MotifKain();
                motif.setId_motif(rs.getInt("id_motif"));
                motif.setNama_motif(rs.getString("nama_motif"));
                motif.setHarga_motif(rs.getInt("harga_motif"));
                listMotif.add(motif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMotif;
    }

    public ArrayList<String> getAllIDKain() {
        ArrayList<String> listIDKain = new ArrayList();
        conn.connect();
        String query = "SELECT * FROM kain_toko";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id_kain");
                listIDKain.add(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listIDKain;
    }

    public ArrayList<KainCustom> getAllKainCustom() {
        ArrayList<KainCustom> listKain = new ArrayList();
        conn.connect();
        String query = "SELECT * FROM kain_custom";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                KainCustom kainCustom = new KainCustom();
                kainCustom.setId_kain(rs.getString("id_kain"));
                kainCustom.setBahan_kain_custom(rs.getString("nama_bahan_custom"));
                kainCustom.setWarna_kain_custom(rs.getString("nama_warna_custom"));
                kainCustom.setMotif_kain_custom(rs.getString("nama_motif_custom"));
                kainCustom.setHarga_kain_custom(rs.getInt("harga_kain_custom"));
                listKain.add(kainCustom);
            }
            return listKain;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Kain getKain(String id_kain) {
        Controller controller = new Controller();
        conn.connect();
        if (id_kain.contains("CUSTOM")) {
            String query = "SELECT * FROM kain_custom WHERE id_kain='" + id_kain + "'";
            KainCustom curKain = new KainCustom();
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    curKain.setId_kain(rs.getString("id_kain"));
                    curKain.setBahan_kain_custom(rs.getString("nama_bahan_custom"));
                    curKain.setWarna_kain_custom(rs.getString("nama_warna_custom"));
                    curKain.setMotif_kain_custom(rs.getString("nama_motif_custom"));
                    curKain.setHarga_kain_custom(rs.getInt("harga_kain_custom"));
                }
                return curKain;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM kain_toko WHERE id_kain='" + id_kain + "'";
            try {;
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                kain_toko curKain = new kain_toko();
                while (rs.next()) {
                    String bahan = controller.getNamaBahan(id_kain);
                    String warna = controller.getNamaWarna(id_kain);
                    String motif = controller.getNamaMotif(id_kain);
                    curKain.setId_kain(rs.getString("id_kain"));
                    curKain.setBahan(getBahan(bahan));
                    curKain.setWarna(getWarna(warna));
                    curKain.setMotif(getMotif(motif));
                }
                return curKain;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public BahanKain getBahan(String nama_bahan) {
        String query = "SELECT * FROM bahan WHERE nama_bahan='" + nama_bahan + "'";
        BahanKain curBahan = new BahanKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curBahan.setId_bahan(rs.getInt("id_bahan"));
                curBahan.setNama_bahan(rs.getString("nama_bahan"));
                curBahan.setHarga_bahan(rs.getInt("harga_bahan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curBahan;
    }

    public BahanKain getBahanBottom() {
        String query = "SELECT * FROM bahan order by id_bahan DESC limit 1";
        BahanKain curBahan = new BahanKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curBahan.setId_bahan(rs.getInt("id_bahan"));
                curBahan.setNama_bahan(rs.getString("nama_bahan"));
                curBahan.setHarga_bahan(rs.getInt("harga_bahan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curBahan;
    }

    public WarnaKain getWarnaBottom() {
        String query = "SELECT * FROM warna order by id_warna DESC limit 1";
        WarnaKain curWarna = new WarnaKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curWarna.setId_warna(rs.getInt("id_warna"));
                curWarna.setNama_warna(rs.getString("nama_warna"));
                curWarna.setHarga_warna(rs.getInt("harga_warna"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curWarna;
    }

    public WarnaKain getWarna(String namaWarna) {
        String query = "SELECT * FROM warna WHERE nama_warna='" + namaWarna + "'";
        WarnaKain curWarna = new WarnaKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curWarna.setId_warna(rs.getInt("id_warna"));
                curWarna.setNama_warna(rs.getString("nama_warna"));
                curWarna.setHarga_warna(rs.getInt("harga_warna"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curWarna;
    }

    public MotifKain getMotifBottom() {
        String query = "SELECT * FROM motif order by id_motif DESC limit 1";
        MotifKain curMotif = new MotifKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curMotif.setId_motif(rs.getInt("id_motif"));
                curMotif.setNama_motif(rs.getString("nama_motif"));
                curMotif.setHarga_motif(rs.getInt("harga_motif"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curMotif;
    }

    public MotifKain getMotif(String nama_motif) {
        String query = "SELECT * FROM motif WHERE nama_motif='" + nama_motif + "'";
        MotifKain curMotif = new MotifKain();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curMotif.setId_motif(rs.getInt("id_motif"));
                curMotif.setNama_motif(rs.getString("nama_motif"));
                curMotif.setHarga_motif(rs.getInt("harga_motif"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curMotif;
    }

    // SELECT ALL from table users
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        conn.connect();
        String query = "SELECT * FROM users";
        try {
            Statement stmt1 = conn.con.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            while (rs.next()) {
                TipeUser enumVal = TipeUser.valueOf(rs.getString("status"));
                if (enumVal == TipeUser.CUSTOMER) {
                    Customer currentCust = new Customer();
                    currentCust.setId_user(rs.getInt("id_user"));
                    currentCust.setUsername(rs.getString("username"));
                    currentCust.setNama_lengkap(rs.getString("nama_lengkap"));
                    currentCust.setEmail(rs.getString("email"));
                    currentCust.setPassword(rs.getString("password"));
                    currentCust.setTipeuser(enumVal);

                    currentCust.setAlamat(rs.getString("alamat"));
                    currentCust.setNoTelpon(rs.getString("no_telpon"));
                    currentCust.setKeranjang(getKeranjang(rs.getInt("id_user")));
                    currentCust.setTransaksi(getSQLListTransaksi(rs.getInt("id_user")));

                    //currentCust.setChatroom(getSQLChatRoom(rs.getInt("id_user")));
                    users.add(currentCust);
                } else if (enumVal == TipeUser.ADMIN) {
                    Admin curAdmin = new Admin();
                    curAdmin.setId_user(rs.getInt("id_user"));
                    curAdmin.setUsername(rs.getString("username"));
                    curAdmin.setNama_lengkap(rs.getString("nama_lengkap"));
                    curAdmin.setEmail(rs.getString("email"));
                    curAdmin.setPassword(rs.getString("password"));
                    curAdmin.setTipeuser(enumVal);

                    String query2 = "SELECT * FROM admin WHERE id_user='" + rs.getInt("id_user") + "'";
                    try {
                        Statement stmt2 = conn.con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(query2);
                        //curAdmin.setId_admin(rs2.getInt("id_admin"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //curAdmin.setChatroom(getSQLChatRoom(rs.getInt("id_user")));

                    users.add(curAdmin);
                } else if (enumVal == TipeUser.OWNER) {
                    Admin curOwner = new Admin();
                    curOwner.setId_user(rs.getInt("id_user"));
                    curOwner.setUsername(rs.getString("username"));
                    curOwner.setNama_lengkap(rs.getString("nama_lengkap"));
                    curOwner.setEmail(rs.getString("email"));
                    curOwner.setPassword(rs.getString("password"));
                    curOwner.setTipeuser(enumVal);
                    //curOwner.setChatroom(getSQLChatRoom(rs.getInt("id_user")));

                    users.add(curOwner);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    // SELECT ALL Customers from table users
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        conn.connect();
        String query = "SELECT * FROM users WHERE status = 'CUSTOMER'";
        try {
            Statement stmt1 = conn.con.createStatement();
            ResultSet rs = stmt1.executeQuery(query);
            while (rs.next()) {
                TipeUser enumVal = TipeUser.valueOf(rs.getString("status"));
                if (enumVal == TipeUser.CUSTOMER) {
                    Customer currentCust = new Customer();
                    currentCust.setId_user(rs.getInt("id_user"));
                    currentCust.setUsername(rs.getString("username"));
                    currentCust.setNama_lengkap(rs.getString("nama_lengkap"));
                    currentCust.setEmail(rs.getString("email"));
                    currentCust.setPassword(rs.getString("password"));
                    currentCust.setTipeuser(enumVal);
                    currentCust.setAlamat(rs.getString("alamat"));
                    currentCust.setNoTelpon(rs.getString("no_telpon"));
                    currentCust.setKeranjang(getKeranjang(rs.getInt("id_user")));
                    currentCust.setTransaksi(getSQLListTransaksi(rs.getInt("id_user")));
                    //currentCust.setChatroom(getSQLChatRoom(rs.getInt("id_user")));
                    customers.add(currentCust);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (customers);
    }

    public ArrayList<Transaksi> getSQLListTransaksi(int id_User) {
        ArrayList<Transaksi> listTransaksi = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM transaksi WHERE id_user='" + id_User + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaksi curTransaksi = new Transaksi();
                curTransaksi.setId_transaksi(rs.getInt("id_transaksi"));
                curTransaksi.setProgress(Progress.valueOf(rs.getString("progress")));
                curTransaksi.setTipeBayar(TipeBayar.valueOf(rs.getString("tipe_bayar")));
                curTransaksi.setTipe_pengiriman(rs.getInt("tipe_pengiriman"));
                curTransaksi.setAlamat(rs.getString("alamat"));
                curTransaksi.setWaktu_transaksi(rs.getTimestamp("waktu_transaksi"));
                curTransaksi.setTotal_bayar(rs.getInt("total_bayar"));
                curTransaksi.setDetailTransaksi(getSQLDetailTransaksi(rs.getInt("id_transaksi")));
                listTransaksi.add(curTransaksi);
            }
            return listTransaksi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Transaksi> getAllTransaksi() {
        ArrayList<Transaksi> listTransaksi = new ArrayList<>();
        conn.connect();
        String query = "SELECT * FROM `transaksi`";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Transaksi curTransaksi = new Transaksi();
                curTransaksi.setId_transaksi(rs.getInt("id_transaksi"));
                curTransaksi.setProgress(Progress.valueOf(rs.getString("progress")));
                curTransaksi.setTipeBayar(TipeBayar.valueOf(rs.getString("tipe_bayar")));
                curTransaksi.setTipe_pengiriman(rs.getInt("tipe_pengiriman"));
                curTransaksi.setAlamat(rs.getString("alamat"));
                curTransaksi.setWaktu_transaksi(rs.getTimestamp("waktu_transaksi"));
                curTransaksi.setTotal_bayar(rs.getInt("total_bayar"));
                listTransaksi.add(curTransaksi);
            }
            return listTransaksi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<DetailTransaksi> getSQLDetailTransaksi(int id_transaksi) {
        String query4 = "SELECT * FROM detail_transaksi WHERE id_transaksi='" + id_transaksi + "'";
        try {
            Statement stmt4 = conn.con.createStatement();
            ResultSet rs4 = stmt4.executeQuery(query4);
            ArrayList<DetailTransaksi> listDetailTransaksi = new ArrayList<>();
            while (rs4.next()) {
                DetailTransaksi curDetailTransaksi = new DetailTransaksi();
                curDetailTransaksi.setQuantity(rs4.getInt("quantity"));
                curDetailTransaksi.setKain(getKain(rs4.getString("id_kain")));
                listDetailTransaksi.add(curDetailTransaksi);
            }
            return listDetailTransaksi;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Keranjang> getKeranjang(int id_user) {
        conn.connect();
        String query = "SELECT * FROM keranjang WHERE id_user='" + id_user + "'";
        ArrayList<Keranjang> listKeranjang = new ArrayList<>();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Keranjang curKeranjang = new Keranjang();
                curKeranjang.setId_kain(rs.getString("id_kain"));
                curKeranjang.setQuantity(rs.getInt("quantity"));
                listKeranjang.add(curKeranjang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listKeranjang;
    }

    public KainCustom getKainCustomWithId_Kain(String id_kain) {
        conn.connect();
        String query = "SELECT * FROM kain_custom WHERE id_kain='" + id_kain + "'";
        KainCustom curkain = new KainCustom();
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                curkain.setId_kain(rs.getString("id_kain"));
                curkain.setBahan_kain_custom(rs.getString("nama_bahan_custom"));
                curkain.setWarna_kain_custom(rs.getString("nama_warna_custom"));
                curkain.setMotif_kain_custom(rs.getString("nama_motif_custom"));
                curkain.setHarga_kain_custom(rs.getInt("harga_kain_custom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curkain;
    }

    public ChatRoom getSQLChatRoom(int id_user) {
        String query = "SELECT * FROM chat_room WHERE id_user='" + id_user + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ChatRoom curChatRoom = new ChatRoom();
            curChatRoom.setId_chat_room(rs.getInt("id_chat_room"));
            curChatRoom.setNama_room(rs.getString("nama_room"));
            curChatRoom.setMessage(getSQLListMessage(rs.getInt("id_chat_room"), id_user));
            return curChatRoom;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Message> getSQLListMessage(int id_chat_room, int id_user) {
        ArrayList<Message> listMessage = new ArrayList<>();
        String query = "SELECT * FROM message WHERE id_user='" + id_user + "'&& id_chat_room='" + id_chat_room + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Message curMessage = new Message();
                curMessage.setId_message(rs.getInt("id_message"));
                curMessage.setPesan(rs.getString("pesan"));
                curMessage.setWaktu(rs.getTimestamp("waktu"));
                listMessage.add(curMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listMessage;
    }

    //Insert User
    public boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            if (user instanceof Customer) {
                Customer curCust = (Customer) user;
                stmt.setString(1, null);
                stmt.setString(2, curCust.getUsername());
                stmt.setString(3, curCust.getNama_lengkap());
                stmt.setString(4, curCust.getEmail());
                stmt.setString(5, curCust.getPassword());
                stmt.setString(6, curCust.getAlamat());
                stmt.setString(7, curCust.getNoTelpon());
                stmt.setString(8, curCust.getTipeuser().toString());
//                String query2 = "INSERT INTO keranjang VALUES(?,?)"; 
//                try {//bagian ini dicomment sementara agar tidak error ketika menjalankan registrasi
//                    PreparedStatement stmt2 = conn.con.prepareStatement(query2);
//                    stmt2.setInt(1, curCust.getId_user());
//                    stmt2.setInt(2, curCust.getKeranjang().getId_keranjang());
//                    for (int i = 0; i < curCust.getKeranjang().getDetailKeranjang().size(); i++) {
//                        insertDetailKeranjang(curCust.getKeranjang(), i);
//                    }
//                    stmt2.executeUpdate();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                    return (false);
//                }
                stmt.executeUpdate();
            }

            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertKeranjang(User user, String id_kain, int jumlahKain) {
        conn.connect();
        String query = "INSERT INTO keranjang VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId_user());
            stmt.setString(2, id_kain);
            stmt.setInt(3, jumlahKain);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertTransaksi(Customer customer, int id_transaksi) {
        conn.connect();
        String query = "INSERT INTO transaksi VALUES(?,?,CAST(? AS Progress),CAST(? AS TipeBayar),?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, id_transaksi);
            stmt.setInt(2, customer.getId_user());
            stmt.setString(3, customer.getTransaksi().get(id_transaksi).getProgress().toString());
            stmt.setString(4, customer.getTransaksi().get(id_transaksi).getTipeBayar().toString());
            stmt.setInt(5, customer.getTransaksi().get(id_transaksi).getTipe_pengiriman());
            stmt.setString(6, customer.getTransaksi().get(id_transaksi).getAlamat());
            stmt.setTimestamp(7, customer.getTransaksi().get(id_transaksi).getWaktu_transaksi());
            stmt.setInt(8, customer.getTransaksi().get(id_transaksi).getTotal_bayar());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    // Insert DetailTransaksi

    public static boolean insertDetailTransaksi(Transaksi transaksi, int id_detail_transaksi) {
        conn.connect();
        String query = "INSERT INTO detail_transaksi VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, transaksi.getId_transaksi());
            stmt.setInt(2, id_detail_transaksi);
            stmt.setString(3, transaksi.getDetailTransaksi().get(id_detail_transaksi + 1).getKain().getId_kain());
            stmt.setInt(4, transaksi.getDetailTransaksi().get(id_detail_transaksi + 1).getQuantity());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertBahan(BahanKain curKain) {
        conn.connect();
        String query = "INSERT INTO bahan VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getId_bahan());
            stmt.setString(2, curKain.getNama_bahan());
            stmt.setInt(3, curKain.getHarga_bahan());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertWarna(WarnaKain curKain) {
        conn.connect();
        String query = "INSERT INTO warna VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getId_warna());
            stmt.setString(2, curKain.getNama_warna());
            stmt.setInt(3, curKain.getHarga_warna());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertMotif(MotifKain curKain) {
        conn.connect();
        String query = "INSERT INTO motif VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getId_motif());
            stmt.setString(2, curKain.getNama_motif());
            stmt.setInt(3, curKain.getHarga_motif());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // Insert Kain
    public static boolean insertKain(Kain kain, String id_kain) {
        conn.connect();
        String query = "INSERT INTO kain VALUES(?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, id_kain);
            stmt.executeUpdate();
            if (kain instanceof kain_toko) {
                Statement stmtForreigKeyOff = conn.con.createStatement();
                stmtForreigKeyOff.execute("SET FOREIGN_KEY_CHECKS=0");
                stmtForreigKeyOff.close();
                String query2 = "INSERT INTO kain_toko VALUES(?,?,?,?,?);";
                try {
                    PreparedStatement stmt2 = conn.con.prepareStatement(query2);
                    kain_toko curKain = (kain_toko) kain;
                    stmt2.setString(1, id_kain);
                    stmt2.setInt(2, curKain.getBahan().getId_bahan());
                    stmt2.setInt(3, curKain.getWarna().getId_warna());
                    stmt2.setInt(4, curKain.getMotif().getId_motif());
                    stmt2.setInt(5, curKain.getStok());
                    stmt2.executeUpdate();
                    Statement stmtForreigKeyOn = conn.con.createStatement();
                    stmtForreigKeyOn.execute("SET FOREIGN_KEY_CHECKS=1");
                    stmtForreigKeyOn.close();
                    return (true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return (false);
                }
            } else if (kain instanceof KainCustom) {
                String query2 = "INSERT INTO kain_custom VALUES(?,?,?,?,?)";
                try {
                    PreparedStatement stmt2 = conn.con.prepareStatement(query2);
                    KainCustom curKain = (KainCustom) kain;
                    stmt2.setString(1, id_kain);
                    stmt2.setString(2, curKain.getBahan_kain_custom());
                    stmt2.setString(3, curKain.getWarna_kain_custom());
                    stmt2.setString(4, curKain.getMotif_kain_custom());
                    stmt2.setInt(5, curKain.getHarga_kain_custom());
                    stmt2.executeUpdate();
                    return (true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return (false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
        return (false);
    }

    public static boolean updateDataCustomer(Customer customer) {
        conn.connect();
        String query = "UPDATE users set username = ?,nama_lengkap = ?,email = ?,alamat = ?,no_telpon = ? "
                + "where id_user = '" + customer.getId_user() + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, customer.getUsername());
            stmt.setString(2, customer.getNama_lengkap());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getAlamat());
            stmt.setString(5, customer.getNoTelpon());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean updatePassword(String oldPass, String newPass, int idUser) {
        conn.connect();
        String query = "Update users set password = ? where password = '" + oldPass + "' AND id_user = '" + idUser + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setString(1, newPass);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }

    }

    public static int countIDKainCustom() {
        conn.connect();
        String query = "SELECT COUNT(id_kain) AS 'jumlah' FROM kain_custom";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            int result = 0;
            while (rs.next()) {
                result = rs.getInt("jumlah");
            }
            return result + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }

    public boolean deleteKain(String idKain) {
        conn.connect();
        String query = "DELETE FROM kain where id_kain LIKE '%" + idKain + "%';";
        String query2 = "DELETE FROM kain_toko where id_kain LIKE '%" + idKain + "%';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            PreparedStatement stmt2 = conn.con.prepareStatement(query);
            stmt.executeUpdate(query);
            stmt2.executeUpdate(query2);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBahan(int id_bahan) {
        conn.connect();
        String query = "DELETE FROM bahan where id_bahan ='" + id_bahan + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateBahan(int id_bahan, int change) {
        conn.connect();
        String query = "UPDATE bahan SET harga_bahan = ? WHERE id_bahan = '" + id_bahan + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, change);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteWarna(int id_warna) {
        conn.connect();
        String query = "DELETE FROM warna where id_warna ='" + id_warna + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateWarna(int id_warna, int change) {
        conn.connect();
        String query = "UPDATE warna SET harga_warna = ? WHERE id_warna = '" + id_warna + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, change);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletemotif(int id_motif) {
        conn.connect();
        String query = "DELETE FROM motif where id_motif ='" + id_motif + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateMotif(int id_motif, int change) {
        conn.connect();
        String query = "UPDATE bahan SET harga_motif = ? WHERE id_motif = '" + id_motif + "';";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, change);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertAdmin() {
        conn.connect();
        String query = "INSERT INTO admin (id_user) VALUES (?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            int id = getID_userBottom();
            stmt.setInt(1, id);
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public int getID_userBottom() {
        String query = "SELECT * FROM users ORDER BY id_user DESC LIMIT 1";
        int id = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id_user");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int countStockKain(String id_kain) {
        String query = "SELECT stok FROM kain_toko WHERE id_kain = '" + id_kain + "'";
        int stock = 0;
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                stock = rs.getInt("stok");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

    public String getIDKainCustomBottom() {
        String query = "SELECT id_kain FROM kain_custom ORDER BY id_kain DESC LIMIT 1";
        String id = "";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getString("id_kain");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean updateStokKain(String id_kain, int stok) {
        conn.connect();
        String query = "UPDATE kain_toko set stok = ? where id_kain = ?";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, stok);
            stmt.setString(2, id_kain);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public static boolean updateHargaKainCustom(String id_kain, int harga) {
        conn.connect();
        String query = "UPDATE kain_custom set harga_kain_custom = ? where id_kain = ?";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, harga);
            stmt.setString(2, id_kain);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

}
