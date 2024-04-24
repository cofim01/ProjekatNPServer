
package rs.ac.bg.fon.projekatservermvn.so.knjiga;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SOIzmeniKnjigu extends OpstiSO{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof  Knjiga)){
            throw new Exception("Prosledjeni objekat nije instanca klase knjiga");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
    }
    
}
