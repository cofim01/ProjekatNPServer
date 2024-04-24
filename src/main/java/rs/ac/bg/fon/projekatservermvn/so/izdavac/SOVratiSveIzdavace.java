package rs.ac.bg.fon.projekatservermvn.so.izdavac;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Izdavac;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveIzdavace extends OpstiSO {

    ArrayList<Izdavac> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Izdavac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izdavac!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> izdavaci = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat i : izdavaci) {
            lista.add((Izdavac) i);
        }
    }

    public ArrayList<Izdavac> getLista() {
        return lista;
    }

}
