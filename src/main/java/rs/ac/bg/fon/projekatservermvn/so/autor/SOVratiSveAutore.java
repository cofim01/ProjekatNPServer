package rs.ac.bg.fon.projekatservermvn.so.autor;

import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Autor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 *
 * @author Filip Mrdak
 * Predstavlja sistemsku operaciju koja vraca sve autore iz baze podataka kao listu (ArrayList).
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * 
 */
public class SOVratiSveAutore extends OpstiSO {
    
    /**
     * Lista svih autora u bazi podataka kao ArrayList
     */
    ArrayList<Autor> lista = new ArrayList<>();
    
    /**
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Autor.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Autor)) {
            throw new Exception("Prosledjeni objekat nije instanca autora!");
        }
    }
    /**
     * Pronalazi sve autore iz baze podataka i dodaje ih u listu autora.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        ArrayList<OpstiDomenskiObjekat> autori = DBBroker.getInstanca().select(odo);
        for (OpstiDomenskiObjekat a : autori) {
            lista.add((Autor) a);
        }
    }
    /**
     * Vraca listu svih autora koji se nalaze u bazi podataka.
     * @return lista autora kao ArrayList.
     */
    public ArrayList<Autor> getLista() {
        return lista;
    }

}
