package rs.ac.bg.fon.projekatservermvn.so.zanr;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zanr;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveZanrove extends OpstiSO {

    ArrayList<Zanr> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zanr)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zanr");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zanrovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat z : zanrovi) {
            lista.add((Zanr) z);
        }
    }

    public ArrayList<Zanr> getLista() {
        return lista;
    }

}
