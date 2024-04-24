package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveKnjige extends OpstiSO {

    ArrayList<Knjiga> lista=new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Knjiga)) {
            throw new Exception("Prosledjeni objekat nije instanca knjige!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> knjige = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat k : knjige) {
            lista.add((Knjiga) k);
        }
    }

    public ArrayList<Knjiga> getLista() {
        return lista;
    }

}
