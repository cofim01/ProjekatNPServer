package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SODodajAutoreKnjige extends OpstiSO {
    
    private int pk;
    
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof KnjigaAutor)) {
            throw new Exception("Proesledjeni objekat nije instaca klase knjigaAutor!");
        }
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
    }

    public int getPk() {
        return pk;
    }
    
    

}
