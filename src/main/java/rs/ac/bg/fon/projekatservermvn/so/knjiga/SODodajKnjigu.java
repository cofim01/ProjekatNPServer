package rs.ac.bg.fon.projekatservermvn.so.knjiga;

;
import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;


/**
 *
 * @author MRDAK-PC
 */
public class SODodajKnjigu extends OpstiSO {

    private int pk;

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

    public int getPk() {
        return pk;
    }
    

}
