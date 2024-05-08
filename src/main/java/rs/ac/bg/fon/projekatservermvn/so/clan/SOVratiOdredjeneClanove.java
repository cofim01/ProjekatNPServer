
package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;



/**
 * Predstavlja sistemsku operaciju koja vraca odredjene clanove (po uslovu) iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author MRDAK-PC
 * 
 */
public class SOVratiOdredjeneClanove extends OpstiSO {
    /**
     * Lista trazenih clanova biblioteke prema prosledjenom uslovu kao ArrayList.
     */
    private ArrayList<Clan> lista=new ArrayList<>();
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Clan.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
    }
    /**
     * Vraca sve clanove biblioteke koji odgovaraju prosledjenom uslovu i dodaje ih u listu.
     * Vracaju se svi clanovi koji u svom imenu ili prezimenu sadrze ime i prezime koji su prosledjeni kroz objekat domenskog modela.
     * 
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
