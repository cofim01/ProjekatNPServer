package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;


import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatservermvn.so.OpstiSO;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


/**
 * Predstavlja sistemsku operaciju koja dodaje autora za odredjenu knjigu. 
 * Nasledjuje apstraktnu klasu OpstiSo i implementira metode validate i execute.
 * @author Filip Mrdak
 * 
 */
public class SODodajAutoreKnjige extends OpstiSO {
    /**
     * Primarni kljuc dodate veze izmedju autora i knjige u bazi podataka kao int.
     */
    private int pk;
    
    /**
     * Vrsi validaciju uslova koji su nephodni kako bi se sistemka operacija izvrsila.
     * Validacija je uspesna ukoliko je prosledjeni objekat klase KnjigaAutor.
     * 
     * @param odo - prosledjeni objekat za koji se vrsi validacija.
     * @throws Exception - ukoliko prosledjeni objekat nije instanca klase KnjigaAutor.
     */
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof KnjigaAutor)) {
            throw new Exception("Proesledjeni objekat nije instaca klase knjigaAutor!");
        }
    }
    /**
     * Predstavlja izvrsenje sistemske operacije.
     * Doodaje novog autora za odredjenu knjigu i formira vezu izmedju njih.
     * 
     * @param odo - prosledjeni objekat domenskog modela, ocekuje se da bude instanca klase Autor.
     * @throws Exception - ukoliko dodje do greske prilikom upisa u bazu podataka.
     */
    @Override
    protected void execute(OpstiDomenskiObjekat odo) throws Exception {
        pk=DBBroker.getInstanca().insert(odo);
    }
    /**
     * Vraca primarni kljuc novoformirane veze izmedju knjige i autora.
     * @return primarni kljuc nove veze izmedju knjige i autora kao int.
     */
    public int getPk() {
        return pk;
    }
    
    

}
