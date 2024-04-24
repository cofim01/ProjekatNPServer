package rs.ac.bg.fon.projekatservermvn.so.logovanje;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;



/**
 *
 * @author MRDAK-PC
 */
public class SOLogovanje extends OpstiSO {

    Korisnik korisnik;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstanca().select(odo);
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        for (OpstiDomenskiObjekat admin : lista) {
            korisnici.add((Korisnik) admin);
        }

        Korisnik k = (Korisnik) odo;

        for (Korisnik ko : korisnici) {
            if (k.getIme().equals(ko.getIme()) && k.getPrezime().equals(ko.getPrezime())
                    && k.getPassword().equals(ko.getPassword())) {
                korisnik = ko;
                return;
            }

        }
        throw new Exception("Neuspesno logovanje, nepostojeći korisnik.");
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

}
