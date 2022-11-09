/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Admin;
import Model.BahanKain;
import Model.ChatRoom;
import Model.Customer;
import Model.DetailKeranjang;
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

    public Kain getKain(String id_kain) {
        if (id_kain.contains("CUSTOM")) {
            String query = "SELECT * FROM kain_custom WHERE id_kain='" + id_kain + "'";
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                KainCustom curKain = new KainCustom();
                curKain.setBahan_kain_custom(rs.getString("nama_bahan_custom"));
                curKain.setWarna_kain_custom(rs.getString("nama_warna_custom"));
                curKain.setMotif_kain_custom(rs.getString("nama_motif_custom"));
                curKain.setHarga_kain_custom(rs.getInt("harga_kain_custom"));
                return curKain;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "SELECT * FROM kain_toko WHERE id_kain='" + id_kain + "'";
            try {
                Statement stmt = conn.con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                kain_toko curKain = new kain_toko();
                curKain.setBahan(getBahan(rs.getInt("id_bahan")));
                curKain.setWarna(getWarna(rs.getInt("id_warna")));
                curKain.setMotif(getMotif(rs.getInt("id_motif")));
                return curKain;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public BahanKain getBahan(int id_bahan) {
        String query = "SELECT * FROM bahan WHERE id_bahan='" + id_bahan + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            BahanKain curBahan = new BahanKain();
            curBahan.setId_bahan(rs.getInt("id_bahan"));
            curBahan.setNama_bahan(rs.getString("nama_bahan"));
            curBahan.setHarga_bahan(rs.getInt("harga_bahan"));
            return curBahan;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WarnaKain getWarna(int id_warna) {
        String query = "SELECT * FROM warna WHERE id_warna='" + id_warna + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            WarnaKain curWarna = new WarnaKain();
            curWarna.setId_warna(rs.getInt("id_warna"));
            curWarna.setNama_warna(rs.getString("nama_warna"));
            curWarna.setHarga_warna(rs.getInt("harga_warna"));
            return curWarna;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MotifKain getMotif(int id_motif) {
        String query = "SELECT * FROM motif WHERE id_motif='" + id_motif + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            MotifKain curMotif = new MotifKain();
            curMotif.setId_motif(rs.getInt("id_motif"));
            curMotif.setNama_motif(rs.getString("nama_motif"));
            curMotif.setHarga_bahan(rs.getInt("harga_motif"));
            return curMotif;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                    currentCust.setKeranjang(getSQLKeranjang(rs.getInt("id_user")));
                    currentCust.setTransaksi(getSQLListTransaksi(rs.getInt("id_user")));

                    currentCust.setChatroom(getSQLChatRoom(rs.getInt("id_user")));

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
                        curAdmin.setId_admin(rs2.getInt("id_admin"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    curAdmin.setChatroom(getSQLChatRoom(rs.getInt("id_user")));

                    users.add(curAdmin);
                } else if (enumVal == TipeUser.OWNER) {
                    Admin curOwner = new Admin();
                    curOwner.setId_user(rs.getInt("id_user"));
                    curOwner.setUsername(rs.getString("username"));
                    curOwner.setNama_lengkap(rs.getString("nama_lengkap"));
                    curOwner.setEmail(rs.getString("email"));
                    curOwner.setPassword(rs.getString("password"));
                    curOwner.setTipeuser(enumVal);
                    curOwner.setChatroom(getSQLChatRoom(rs.getInt("id_user")));

                    users.add(curOwner);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (users);
    }

    public ArrayList<Transaksi> getSQLListTransaksi(int id_User) {
        ArrayList<Transaksi> listTransaksi = new ArrayList<>();
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

    public ArrayList<DetailTransaksi> getSQLDetailTransaksi(int id_transaksi) {
        String query4 = "SELECT * FROM detail_transaksi WHERE id_transaksi='" + id_transaksi + "'";
        try {
            Statement stmt4 = conn.con.createStatement();
            ResultSet rs4 = stmt4.executeQuery(query4);
            ArrayList<DetailTransaksi> listDetailTransaksi = new ArrayList<>();
            while (rs4.next()) {
                DetailTransaksi curDetailTransaksi = new DetailTransaksi();
                curDetailTransaksi.setDetail_transaksi(rs4.getInt("id_detail_transaksi"));
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

    public Keranjang getSQLKeranjang(int id_user) {
        String query = "SELECT * FROM keranjang WHERE id_user='" + id_user + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Keranjang curKeranjang = new Keranjang();
            curKeranjang.setId_keranjang(rs.getInt("id_keranjang"));
            curKeranjang.setDetailKeranjang(getSQLDetailKeranjang(rs.getInt("id_keranjang")));
            return curKeranjang;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<DetailKeranjang> getSQLDetailKeranjang(int id_keranjang) {
        ArrayList<DetailKeranjang> listDetailKeranjang = new ArrayList<>();
        String query = "SELECT * FROM detail_keranjang WHERE id_keranjang='" + id_keranjang + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DetailKeranjang curDetailKeranjang = new DetailKeranjang();
                curDetailKeranjang.setQuantity(rs.getInt("quantity"));
                curDetailKeranjang.setKain(getKain(rs.getString("id_kain")));
                listDetailKeranjang.add(curDetailKeranjang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDetailKeranjang;

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
    public static boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,CAST(? AS TipeUser))";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            if (user instanceof Customer) {
                Customer curCust = (Customer) user;
                stmt.setString(2, curCust.getUsername());
                stmt.setString(3, curCust.getNama_lengkap());
                stmt.setString(4, curCust.getEmail());
                stmt.setString(5, curCust.getPassword());
                stmt.setString(6, curCust.getAlamat());
                stmt.setString(7, curCust.getNoTelpon());
                stmt.setString(8, curCust.getTipeuser().toString());
                String query2 = "INSERT INTO keranjang VALUES(?,?)";
                try {
                    PreparedStatement stmt2 = conn.con.prepareStatement(query2);
                    stmt2.setInt(1, curCust.getId_user());
                    stmt2.setInt(2, curCust.getKeranjang().getId_keranjang());
                    for (int i = 0; i < curCust.getKeranjang().getDetailKeranjang().size(); i++) {
                        insertDetailKeranjang(curCust.getKeranjang(), i);
                    }
                    stmt2.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return (false);
                }
            }
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    // Insert DetailKeranjang
    public static boolean insertDetailKeranjang(Keranjang keranjang, int id_detail_keranjang) {
        conn.connect();
        String query = "INSERT INTO detail_keranjang VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, keranjang.getId_keranjang());
            stmt.setInt(2, id_detail_keranjang);
            stmt.setString(3, keranjang.getDetailKeranjang().get(id_detail_keranjang + 1).getKain().getId_kain());
            stmt.setInt(4, keranjang.getDetailKeranjang().get(id_detail_keranjang + 1).getQuantity());
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

    public static boolean insertBahan(kain_toko curKain) {
        conn.connect();
        String query = "INSERT INTO bahan VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getBahan().getId_bahan());
            stmt.setString(2, curKain.getBahan().getNama_bahan());
            stmt.setInt(3, curKain.getBahan().getHarga_bahan());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertWarna(kain_toko curKain) {
        conn.connect();
        String query = "INSERT INTO warna VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getWarna().getId_warna());
            stmt.setString(2, curKain.getWarna().getNama_warna());
            stmt.setInt(3, curKain.getWarna().getHarga_warna());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public static boolean insertMotif(kain_toko curKain) {
        conn.connect();
        String query = "INSERT INTO motif VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, curKain.getMotif().getId_motif());
            stmt.setString(2, curKain.getMotif().getNama_motif());
            stmt.setInt(3, curKain.getMotif().getHarga_bahan());
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
            if (kain instanceof kain_toko) {
                String query2 = "INSERT INTO kain_toko VALUES(?,?,?,?,?)";
                try {
                    PreparedStatement stmt2 = conn.con.prepareStatement(query2);
                    kain_toko curKain = (kain_toko) kain;
                    stmt2.setString(1, id_kain);
                    stmt2.setInt(2, curKain.getBahan().getId_bahan());
                    stmt2.setInt(3, curKain.getWarna().getId_warna());
                    stmt2.setInt(4, curKain.getMotif().getId_motif());
                    stmt2.setInt(5, curKain.getStok());
                    insertBahan(curKain);
                    insertWarna(curKain);
                    insertMotif(curKain);
                    stmt2.executeUpdate();
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
        return (false);
    }

}