
package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vraca odredjene knjige iz baze podataka (po uslovu). 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOVratiOdredjeneKnjige extends OpstiSO {
    /**
     * Lista svih knjiga iz baze podataka koje zadovoljavaju uslov kao ArrayList.
     */
    ArrayList<Knjiga> lista=new ArrayList<>();

     /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila. 
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Knjiga.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase Knjiga.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Knjiga)) {
            throw new Exception("Prosledjeni objekat nije instanca knjige!");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Vraca sve knjige iz baze koje ispunjavaju uslov i dodaje ih u listu.
     * Uslov je ispunjen ukoliko ime knjige sadrzi ime prosledjenog objekta.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Knjiga.
     * @throws Exception - ukoliko dodje do greske prilikom izvrsenja upita nad bazom podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> knjige = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat k : knjige) {
            lista.add((Knjiga) k);
        }
    }
    /**
     * Vraca listu svih knjiga iz baze koje zadovoljavaju uslov.
     * @return - lista svih knjiga po uslovu kao ArrayList.
     */
    public ArrayList<Knjiga> getLista() {
        return lista;
    }
    
    
    
}
