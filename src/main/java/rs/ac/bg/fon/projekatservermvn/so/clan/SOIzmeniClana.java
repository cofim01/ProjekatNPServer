package rs.ac.bg.fon.projekatservermvn.so.clan;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja vrsi izmene nad postojecim clanom u bazi podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SOIzmeniClana extends OpstiSO {

    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase clan, i ukoliko ne postoji clan u bazi koji ima isti broj telefona kao prosledjeni objekat. 
     */
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
            if (c.getBrTelefona().equals(clan.getBrTelefona()) && c.getClanId()!=clan.getClanId()) {
                throw new Exception("vec postoji član sa unetim brojem telefona!");
            }
        }
    }
    /**
     * Azurira postojeceg clana u bazi podataka na osnovu objekata koji je prosledjen.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstanca().update(odo);
    }

}
