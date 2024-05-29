package rs.ac.bg.fon.projekatservermvn.so.korisnik;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vraca sve korisnike iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiSveKorisnike extends OpstiSO {
    /**
     * Lista svih korisnika iz baze podataka kao ArrayList.
     */
    ArrayList<Korisnik> lista = new ArrayList<>();
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase  Korisnik.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Korisnik.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vraca sve korisnike iz baze podataka i dodaje ih u listu svih korisnika.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Korisnik.
     * @throws Exception - ukoliko dodje do greske prilikom izvrsenja upita nad bazom podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> korisnici = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat k : korisnici) {
            lista.add((Korisnik) k);
        }
    }
    /**
     * Vraca listu svih korisnika iz baze podataka.
     * @return - lista svih korisnika kao ArrayList
     */
    public ArrayList<Korisnik> getLista() {
        return lista;
    }

}
