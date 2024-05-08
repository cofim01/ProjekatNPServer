package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;


import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja vraca sve objekte KnjigaAutor iz baze podataka. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * 
 */
public class SOVratiSveAutoreKnjige extends OpstiSO {
    
    /**
     * Lista svih objekata KnjigaAutor iz baze podataka kao ArrayList.
     */
    ArrayList<KnjigaAutor> lista=new ArrayList<>();
    
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase KnjigaAutor.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof KnjigaAutor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase knjigaAutor!");
        }
    }
    /**
     * Vraca sve veze izmedju autora i knjige u bazi podataka i dodaje ih u listu.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> autoriKnjige = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat ka : autoriKnjige) {
            lista.add((KnjigaAutor) ka);
        }
    }

    /**
     * Vraca listu svih objekata KnjigaAutor iz baze podataka.
     * @return - lista objekata KnjigaAutor kao ArrayList.
     */
    public ArrayList<KnjigaAutor> getLista() {
        return lista;
    }

}
