
package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja vrsi razduzivanje odredjenog zaduzenja u bazi podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 */
public class SORazduziKnjigu extends OpstiSO {
    
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Zaduzenje,
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
       if(!(odo instanceof Zaduzenje)){
           throw new Exception("Prosledjeni objekat nije instanca klase Zaduzenje!");
       } 
    }
    
    /**
     * Vrsi razduzivanje odredjenog zaduzenja (prosledjenog). Razduzivanje se vrsi tako sto se postavlja datum razduzenja na trenutni datum.
     * Prilikom razduzivanja se vrsi izmena primerka knjige u bazi podataka kako bi se oznacilo da je primerak knjige ponovo dostupan.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
        Zaduzenje z=(Zaduzenje)odo;
        PrimerakKnjige pk=z.getPrimerak();
        DBBroker.getInstanca().update(pk);
    }
    
}
