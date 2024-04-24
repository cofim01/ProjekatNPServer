package rs.ac.bg.fon.projekatservermvn.kontroler;




import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.forme.ServerskaForma;
import rs.ac.bg.fon.projekatservermvn.so.autor.SOVratiSveAutore;
import rs.ac.bg.fon.projekatservermvn.so.clan.SODodajClana;
import rs.ac.bg.fon.projekatservermvn.so.clan.SOIzmeniClana;
import rs.ac.bg.fon.projekatservermvn.so.clan.SOVratiOdredjeneClanove;
import rs.ac.bg.fon.projekatservermvn.so.clan.SOVratiSveClanove;
import rs.ac.bg.fon.projekatservermvn.so.izdavac.SOVratiSveIzdavace;
import rs.ac.bg.fon.projekatservermvn.so.knjiga.SODodajKnjigu;
import rs.ac.bg.fon.projekatservermvn.so.knjiga.SOObrisiKnjigu;
import rs.ac.bg.fon.projekatservermvn.so.knjiga.SOVratiOdredjeneKnjige;
import rs.ac.bg.fon.projekatservermvn.so.knjiga.SOVratiSveKnjige;
import rs.ac.bg.fon.projekatservermvn.so.knjigaAutor.SODodajAutoreKnjige;
import rs.ac.bg.fon.projekatservermvn.so.knjigaAutor.SOVratiSveAutoreKnjige;
import rs.ac.bg.fon.projekatservermvn.so.logovanje.SOLogovanje;
import rs.ac.bg.fon.projekatservermvn.so.primerci.SODodajPrimerakKnjige;
import rs.ac.bg.fon.projekatservermvn.so.primerci.SOIzmeniPrimerak;
import rs.ac.bg.fon.projekatservermvn.so.primerci.SOVratiSvePrimerkeKnjige;
import rs.ac.bg.fon.projekatservermvn.so.zaduzenje.SORazduziKnjigu;
import rs.ac.bg.fon.projekatservermvn.so.zaduzenje.SOVratiSvaZaduzenjaClana;
import rs.ac.bg.fon.projekatservermvn.so.zaduzenje.SOZaduziKnjigu;
import rs.ac.bg.fon.projekatservermvn.so.zanr.SOVratiSveZanrove;

import rs.ac.bg.fon.projekatzajednickimvn.domen.*;


/**
 *
 * @author MRDAK-PC
 */
public class Kontroler {

    private static Kontroler instanca;
    private ServerskaForma sf;
    private ArrayList<Korisnik> ulogovani = new ArrayList<>();

    private Kontroler() {
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    public Korisnik logovanje(Korisnik k) throws Exception {
        if (!ulogovani.contains(k)) {
            SOLogovanje so = new SOLogovanje();
            so.executeTemplate(k);
            sf.dodajUlogovanog(so.getKorisnik());
            ulogovani.add(so.getKorisnik());
            return so.getKorisnik();
        } else {
            throw new Exception("Vec ulogovan korisnik.");
        }

    }

    public void odjava(Korisnik k) {
        ulogovani.remove(k);
        sf.odjaviKorisnika(k);
    }

    public int dodajClana(Clan c) throws Exception {
        SODodajClana so = new SODodajClana();
        so.executeTemplate(c);
        return so.getPk();

    }

    public void izmeniClana(Clan c) throws Exception {
        SOIzmeniClana so = new SOIzmeniClana();
        so.executeTemplate(c);

    }


    public ArrayList<Clan> vratiSveClanove(Clan c) throws Exception {
        SOVratiSveClanove so = new SOVratiSveClanove();
        so.executeTemplate(c);
        return so.getLista();
    }
    
    public ArrayList<Clan> vratiClanoveSaUslovom(Clan c) throws Exception {
        SOVratiOdredjeneClanove so = new SOVratiOdredjeneClanove();
        so.executeTemplate(c);
        return so.getLista();
    }
    
    public ArrayList<Knjiga> vratiKnjigeSaUslovom(Knjiga k) throws Exception {
        SOVratiOdredjeneKnjige so = new SOVratiOdredjeneKnjige();
        so.executeTemplate(k);
        return so.getLista();
    }
    
    

    public ArrayList<Autor> vratiSveAutore(Autor a) throws Exception {
        SOVratiSveAutore so = new SOVratiSveAutore();
        so.executeTemplate(a);
        return so.getLista();

    }

    public ArrayList<Izdavac> vratiSveIzdavace(Izdavac i) throws Exception {
        SOVratiSveIzdavace so = new SOVratiSveIzdavace();
        so.executeTemplate(i);
        return so.getLista();

    }

    public int dodajKnjigu(Knjiga k) throws Exception {
        SODodajKnjigu so = new SODodajKnjigu();
        so.executeTemplate(k);
        return so.getPk();

    }

    public void obrisiKnjigu(Knjiga k) throws Exception {
        SOObrisiKnjigu so = new SOObrisiKnjigu();
        so.executeTemplate(k);

    }

    public ArrayList<Knjiga> vratiSveKnjige(Knjiga k) throws Exception {
        SOVratiSveKnjige so = new SOVratiSveKnjige();
        so.executeTemplate(k);
        return so.getLista();

    }

    public int dodajAutoreKnjige(KnjigaAutor ka) throws Exception {
        SODodajAutoreKnjige so = new SODodajAutoreKnjige();
        so.executeTemplate(ka);
        return so.getPk();

    }

    public ArrayList<KnjigaAutor> vratiSveAutoreKnjige(KnjigaAutor ka) throws Exception {
        SOVratiSveAutoreKnjige so = new SOVratiSveAutoreKnjige();
        so.executeTemplate(ka);
        return so.getLista();

    }

    public ArrayList<Zanr> vratiSveZanrove(Zanr z) throws Exception {
        SOVratiSveZanrove so = new SOVratiSveZanrove();
        so.executeTemplate(z);
        return so.getLista();

    }

    public void zaduziKnjigu(Zaduzenje z) throws Exception {
        SOZaduziKnjigu so = new SOZaduziKnjigu();
        so.executeTemplate(z);

    }

    public void razduziKnjigu(Zaduzenje z) throws Exception {
        SORazduziKnjigu so = new SORazduziKnjigu();
        so.executeTemplate(z);

    }
    public void izmeniPrimerak(PrimerakKnjige pk) throws Exception{
        SOIzmeniPrimerak so=new SOIzmeniPrimerak();
        so.executeTemplate(pk);
    }

    public ArrayList<Zaduzenje> vratiSvaZaduzenja(Zaduzenje z) throws Exception {
        SOVratiSvaZaduzenjaClana so = new SOVratiSvaZaduzenjaClana();
        so.executeTemplate(z);
        return so.getLista();
    }
    
    public ArrayList<PrimerakKnjige> vratiSvePrimerke(PrimerakKnjige pk) throws Exception{
        SOVratiSvePrimerkeKnjige so=new SOVratiSvePrimerkeKnjige();
        so.executeTemplate(pk);
        return so.getLista();
                
    }
    
    public int dodajPrimerakKnjige(PrimerakKnjige pk) throws Exception{
        SODodajPrimerakKnjige so=new SODodajPrimerakKnjige();
        so.executeTemplate(pk);
        return so.getPk();
    
    }

}
