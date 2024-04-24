package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;


/**
 *
 * @author MRDAK-PC
 */
public class SOZaduziKnjigu extends OpstiSO {

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Zaduzenje)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Zaduzenje");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().insert(odo);
        Zaduzenje z=(Zaduzenje)odo;
        PrimerakKnjige pk=z.getPrimerak();
        DBBroker.getInstanca().update(pk);
              
    }
    
    

}
