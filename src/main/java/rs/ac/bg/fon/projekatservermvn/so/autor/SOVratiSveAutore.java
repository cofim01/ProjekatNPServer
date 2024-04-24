package rs.ac.bg.fon.projekatservermvn.so.autor;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Autor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveAutore extends OpstiSO {

    ArrayList<Autor> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Autor)) {
            throw new Exception("Prosledjeni objekat nije instanca autora!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> autori = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat a : autori) {
            lista.add((Autor) a);
        }
    }

    public ArrayList<Autor> getLista() {
        return lista;
    }

}
