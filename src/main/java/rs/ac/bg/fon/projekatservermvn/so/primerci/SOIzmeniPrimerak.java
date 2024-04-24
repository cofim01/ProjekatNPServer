
package rs.ac.bg.fon.projekatservermvn.so.primerci;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 *
 * @author MRDAK-PC
 */
public class SOIzmeniPrimerak extends OpstiSO{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof PrimerakKnjige)) {
            throw new Exception("Proesledjeni objekat nije instaca klase PrimerakKnjige!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
    }
    
}
