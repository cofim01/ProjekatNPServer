
package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;



/**
 * Predstavlja sistemsku operaciju koja vraca odredjene clanove (po uslovu) iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiOdredjeneClanove extends OpstiSO {
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Lista trazenih clanova biblioteke prema prosledjenom uslovu kao ArrayList.
     */
    private ArrayList<Clan> lista=new ArrayList<>();
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Clan.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Clan.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vraca sve clanove biblioteke koji odgovaraju prosledjenom uslovu i dodaje ih u listu.
     * Vracaju se svi clanovi koji u svom imenu ili prezimenu sadrze ime i prezime koji su prosledjeni kroz objekat domenskog modela.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da sadrzi uslove pretrage.
     * @throws Exception - ukoliko dodje do greške prilikom izvrsenja pretrage.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> clanovi = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat c : clanovi) {
            lista.add((Clan) c);
        }
    }
    /**
     * Vraca listu clanova koji odgovaraju uslovu.
     * @return - lista clanova kao ArrayList
     */
    public ArrayList<Clan> getLista() {
        return lista;
    }
    
}
