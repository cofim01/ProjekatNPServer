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
 * Satoji se od metoda validate, execute i metode getLista koja vraca listu autora. 
 */
public class SOVratiSveAutore extends OpstiSO {
    
    /**
     * Lista svih autora u bazi podataka kao ArrayList
     */
    ArrayList<Autor> lista = new ArrayList<>();
    
    /**
     * Predstavlja proveru uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase Autor.
     * @param odo - prosledjeni objekat nad kojim se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije klase Autor.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Autor)) {
            throw new Exception("Prosledjeni objekat nije instanca autora!");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Pronalazi sve aautore iz baze podataka i dodaje ih u listu autora.
     * @param odo - objekat na kojim se vrsi sistemska operacija.
     * @throws Exception - ukoliko je doslo do greske prilikom izvrsavanja sistemske operacije.
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
