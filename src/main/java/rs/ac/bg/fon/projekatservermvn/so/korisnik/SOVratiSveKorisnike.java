package rs.ac.bg.fon.projekatservermvn.so.korisnik;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOVratiSveKorisnike extends OpstiSO {

    ArrayList<Korisnik> lista = new ArrayList<>();

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> korisnici = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat k : korisnici) {
            lista.add((Korisnik) k);
        }
    }

    public ArrayList<Korisnik> getLista() {
        return lista;
    }

}
