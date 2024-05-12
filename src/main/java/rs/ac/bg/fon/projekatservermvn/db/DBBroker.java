package rs.ac.bg.fon.projekatservermvn.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

/**
 *
 * @author MRDAK-PC
 */
public class DBBroker {

    private static DBBroker instanca;
    private Connection conection;

    private DBBroker() {
        try ( FileReader in = new FileReader("src/main/resources/konfiguracijabaze.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject json = gson.fromJson(in, JsonObject.class);
            String url = json.get("url").getAsString();
            String user = json.get("username").getAsString();
            String password = json.get("password").getAsString();
            System.out.println(url + " " + user + " " + password);
            conection = DriverManager.getConnection(url, user, password);
            conection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBBroker getInstanca() {
        if (instanca == null) {
            instanca = new DBBroker();
        }
        return instanca;
    }

    public Connection getConection() {
        return conection;
    }

    public ArrayList<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat odo) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + odo.getNazivTabele() + " " + odo.getAlijas() + " " + odo.join();
        System.out.println(upit);
        Statement st = conection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.getLista(rs);
    }

    public ArrayList<OpstiDomenskiObjekat> selectPoUslovu(OpstiDomenskiObjekat odo) throws SQLException {
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + odo.getNazivTabele() + " " + odo.getAlijas() + " " + odo.join() + " " + odo.getKriterijum();
        System.out.println(upit);
        Statement st = conection.createStatement();
        ResultSet rs = st.executeQuery(upit);
        return odo.getLista(rs);
    }

    public int insert(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.getNazivTabele() + " " + odo.getKoloneZaDodavanje() + " VALUES (" + odo.vrednostiZaDodavanje() + ")";
        System.out.println(upit);
        PreparedStatement ps = conection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int pk = rs.getInt(1);
        return pk;

    }

    public void update(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.getNazivTabele() + " SET " + odo.vrednostiZaAzuriranje() + " WHERE " + odo.getVrednostPrimarniKljuc();
        System.out.println(upit);
        Statement st = conection.createStatement();
        st.executeUpdate(upit);

    }

    public void delete(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "DELETE FROM " + odo.getNazivTabele() + " WHERE " + odo.getVrednostPrimarniKljuc();
        System.out.println(upit);
        Statement st = conection.createStatement();
        st.executeUpdate(upit);

    }

    public static void setInstanca(DBBroker instanca) {
        DBBroker.instanca = instanca;
    }

}
