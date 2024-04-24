package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveClanove extends OpstiSO {

    ArrayList<Clan> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> clanovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat c : clanovi) {
            lista.add((Clan) c);
        }
    }

    public ArrayList<Clan> getLista() {
        return lista;
    }

}
