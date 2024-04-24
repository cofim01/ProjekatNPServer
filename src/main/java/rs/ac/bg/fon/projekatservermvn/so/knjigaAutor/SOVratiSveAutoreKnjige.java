package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveAutoreKnjige extends OpstiSO {

    ArrayList<KnjigaAutor> lista=new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof KnjigaAutor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase knjigaAutor!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> autoriKnjige = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat ka : autoriKnjige) {
            lista.add((KnjigaAutor) ka);
        }
    }

    public ArrayList<KnjigaAutor> getLista() {
        return lista;
    }

}
