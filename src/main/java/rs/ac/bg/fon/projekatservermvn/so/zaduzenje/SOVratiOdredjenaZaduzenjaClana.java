
package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiOdredjenaZaduzenjaClana extends OpstiSO {
    private ArrayList<Zaduzenje> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zaduzenje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase zaduzenje.");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> zaduzenja = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat z : zaduzenja) {
            lista.add((Zaduzenje) z);
        }

    }

    public ArrayList<Zaduzenje> getLista() {
        return lista;
    }
}
