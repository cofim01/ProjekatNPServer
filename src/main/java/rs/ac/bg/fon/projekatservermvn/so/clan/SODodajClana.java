package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja dodaje novog clana u bazi podataka.
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SODodajClana extends OpstiSO {
    /**
     * Primerna kljuc novog clana koji je dodat u bazi.
     */
    private int pk;
    
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Autor.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Clan.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Clan ili ako vec postoji clan sa istim brojem telefona.
     * 
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
        ArrayList<Clan> lista = new ArrayList<>();
        ArrayList<OpstiDomenskiObjekat> clanovi = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat c : clanovi) {
            lista.add((Clan) c);
        }

        Clan clan = (Clan) odo;
        for (Clan c : lista) {
            if (c.getBrTelefona().equals(clan.getBrTelefona())) {
                throw new Exception("već postoji član sa unetim brojem telefona!");
            }
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Dodaje novog clana u bazi podataka.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Clan.
     * @throws Exception - ukoliko dodavanje clana u bazu podataka nije uspesno.
     * 
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
    }
    
    /**
     * Vraca primarni kljuc novog clana koji je dodat u bazu podataka.
     * @return primarni kljuc novog clana kao int.
     */
    public int getPk() {
        return pk;
    }
    

}
