
package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;




public class SOVratiOdredjeneClanove extends OpstiSO {
    private ArrayList<Clan> lista=new ArrayList<>();
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        
    }

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> clanovi = DBBroker.getInstanca().selectPoUslovu(odo);
        for (OpstiDomenskiObjekat c : clanovi) {
            lista.add((Clan) c);
        }
    }

    public ArrayList<Clan> getLista() {
        return lista;
    }
    
}
