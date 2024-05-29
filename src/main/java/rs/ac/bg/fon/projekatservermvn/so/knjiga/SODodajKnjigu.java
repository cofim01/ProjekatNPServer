package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 * Predstavlja sistemsku operaciju koja dodaje novu knjigu u bazu podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SODodajKnjigu extends OpstiSO {
    
    /**
     * Primarni kljuc nove knjige koja je dodata u bazu.
     */
    private int pk;
    
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Knjiga i ukoliko prosledjena knjiga ne postoji vec u bazi podataka.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Knjiga, ili ako vec postoji knjiga sa istim nazivom u bazi podataka.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Knjiga)) {
            throw new Exception("Prosledjeni objekat nije instaca knjige!");
        }
        
        ArrayList<OpstiDomenskiObjekat> lista=DBBroker.getInstanca().select(odo);
        ArrayList<Knjiga> knjige=new ArrayList<>();
        for(OpstiDomenskiObjekat knjiga:lista){
            knjige.add((Knjiga) knjiga);
        }
        Knjiga k=(Knjiga) odo;
        for(Knjiga knjiga:knjige){
            if(knjiga.getNaziv().equals(k.getNaziv())){
                throw new Exception("Uneta knjiga: "+k.getNaziv()+" vec postoji u bazi!");
            }
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Dodaje novu knjigu u bazu podataka zajedno sa svim njenim primercima.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Knjiga.
     * @throws Exception - ukoliko dodje do greske prilikom upisa u bazu podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
        Knjiga k=(Knjiga)odo;
        k.setKnjigaId(pk);
        
        ArrayList<PrimerakKnjige> primerci=k.getPrimerci();
        for(PrimerakKnjige p:primerci){
            p.setKnjiga(k);
            DBBroker.getInstanca().insert(p);
        }

    }
    /**
     * Vraca primerni kljuc nove knjige koja je dodata u bazu.
     * @return - primerni kljuc nove knjige kao int.
     */
    public int getPk() {
        return pk;
    }
    

}
