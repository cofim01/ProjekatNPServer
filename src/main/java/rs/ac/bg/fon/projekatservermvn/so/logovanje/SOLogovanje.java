package rs.ac.bg.fon.projekatservermvn.so.logovanje;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;



/**
 * Predstavlja sistemsku operaciju koja loguje korisnika na sistem. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOLogovanje extends OpstiSO {
    /**
     * Podaci o korisniku koji se ulogovao na sistem.
     */
    Korisnik korisnik;
    
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Korisnik.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Autor.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Loguje korisnika na sistem ako postoji prosledjeni korisnik u bazi podataka za datim podacima.
     * Ukoliko postoji korisnik u bazi sa prosledjenim podacima, postavlja sve podatke ulogovanog korisnika.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Korisnik.
     * @throws Exception - ukoliko dodje do greske prilikom izvrsenja upita nad bazom podataka ili ako korisnik ne postoji.
     */
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
    /**
     * Vraca sve podatke o ulogovanom korisniku.
     * @return - ulogovani korisnik.
     */
    public Korisnik getKorisnik() {
        return korisnik;
    }

}
