package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author MRDAK-PC
 */
public class SODodajClana extends OpstiSO {
    private int pk;
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

    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
    }

    public int getPk() {
        return pk;
    }
    

}
