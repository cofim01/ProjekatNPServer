package rs.ac.bg.fon.projekatservermvn.niti;

import rs.ac.bg.fon.projekatzajednickimvn.domen.*;
import rs.ac.bg.fon.projekatzajednickimvn.komunikacija.*;
import rs.ac.bg.fon.projekatzajednickimvn.komunikacija.transfer.*;
import java.net.Socket;
import java.util.ArrayList;
import rs.ac.bg.fon.projekatservermvn.kom.KomunikacijaSaKlijentom;
import rs.ac.bg.fon.projekatservermvn.kontroler.Kontroler;


/**
 *
 * @author MRDAK-PC
 */
public class NitObradaZahteva extends Thread {

    private Socket s;
    
    public NitObradaZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Zahtev z = KomunikacijaSaKlijentom.getInstanca().primiZahtev(s);
                Odgovor o = obradiZahtev(z);
                KomunikacijaSaKlijentom.getInstanca().posaljiOdgovor(o, s);
                if(z.getOperacija()==Operacije.ODJAVA){
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Odgovor obradiZahtev(Zahtev z) {
        Odgovor o = new Odgovor(null, StatusOdgovora.SUCCESS, null);

        try {
            switch (z.getOperacija()) {
                case LOGOVANJE:
                    Korisnik k = Kontroler.getInstanca().logovanje((Korisnik) z.getPodaci());
                    o.setPodaci(k);
                    break;
                case VRATI_SVE_AUTORE:
                    ArrayList<Autor> listaA = Kontroler.getInstanca().vratiSveAutore((Autor) z.getPodaci());
                    o.setPodaci(listaA);
                    break;
                case DODAJ_CLANA:
                    int pkc = Kontroler.getInstanca().dodajClana((Clan) z.getPodaci());
                    o.setPodaci(pkc);
                    break;
                case AZURIRAJ_CLANA:
                    Kontroler.getInstanca().izmeniClana((Clan) z.getPodaci());
                    break;
                case VRATI_SVE_CLANOVE:
                    ArrayList<Clan> listaC = Kontroler.getInstanca().vratiSveClanove((Clan) z.getPodaci());
                    o.setPodaci(listaC);
                    break;
                case VRATI_CLANOVE_SA_USLOVOM:
                    ArrayList<Clan> listaOdredjeni = Kontroler.getInstanca().vratiClanoveSaUslovom((Clan) z.getPodaci());
                    o.setPodaci(listaOdredjeni);
                    break;
                case VRATI_KNJIGE_SA_USLOVOM:
                    ArrayList<Knjiga> odredjeneKnjige = Kontroler.getInstanca().vratiKnjigeSaUslovom((Knjiga) z.getPodaci());
                    o.setPodaci(odredjeneKnjige);
                    break;
                case VRATI_SVE_IZDAVACE:
                    ArrayList<Izdavac> listaI = Kontroler.getInstanca().vratiSveIzdavace((Izdavac) z.getPodaci());
                    o.setPodaci(listaI);
                    break;
                case DODAJ_KNJIGU:
                    int pkk = Kontroler.getInstanca().dodajKnjigu((Knjiga) z.getPodaci());
                    o.setPodaci(pkk);
                    break;
                case OBRSI_KNJIGU:
                    Kontroler.getInstanca().obrisiKnjigu((Knjiga) z.getPodaci());
                    break;
                case VRATI_SVE_KNJIGE:
                    ArrayList<Knjiga> listaK = Kontroler.getInstanca().vratiSveKnjige((Knjiga) z.getPodaci());
                    o.setPodaci(listaK);
                    break;
                case IZMENI_PRIMERAK:
                    Kontroler.getInstanca().izmeniPrimerak((PrimerakKnjige)z.getPodaci());
                    break;
                case VRATI_SVE_ZANROVE:
                    ArrayList<Zanr> listaZ = Kontroler.getInstanca().vratiSveZanrove((Zanr) z.getPodaci());
                    o.setPodaci(listaZ);
                    break;
                case DODAJ_ZADUZENJE:
                    Kontroler.getInstanca().zaduziKnjigu((Zaduzenje) z.getPodaci());
                    break;
                case RAZDUZI:
                    Kontroler.getInstanca().razduziKnjigu((Zaduzenje) z.getPodaci());
                    break;
                case DODAJ_AUTORE_KNJIGE:
                    int pak=Kontroler.getInstanca().dodajAutoreKnjige((KnjigaAutor) z.getPodaci());
                    o.setPodaci(pak);
                    break;
                case VRATI_SVE_AUTORE_KNJIGE:
                    ArrayList<KnjigaAutor> ka=Kontroler.getInstanca().vratiSveAutoreKnjige((KnjigaAutor) z.getPodaci());
                    o.setPodaci(ka);
                    break;
                case VRATI_SVA_ZADUZENJA:
                    ArrayList<Zaduzenje> listaZaduzenja=Kontroler.getInstanca().vratiSvaZaduzenja((Zaduzenje) z.getPodaci());
                    o.setPodaci(listaZaduzenja);
                    break;
                case VRATI_SVE_PRIMERKE:
                    ArrayList<PrimerakKnjige> listaPrimeraka=Kontroler.getInstanca().vratiSvePrimerke((PrimerakKnjige)z.getPodaci());
                    o.setPodaci(listaPrimeraka);
                    break;
                case DODAJ_PRIMERAK:
                    int pk=Kontroler.getInstanca().dodajPrimerakKnjige((PrimerakKnjige) z.getPodaci());
                    o.setPodaci(pk);
                    break;
                case ODJAVA:
                    Kontroler.getInstanca().odjava((Korisnik) z.getPodaci());
                    break;
                    
                    

            }
        } catch (Exception e) {
            o.setStatus(StatusOdgovora.ERROR);
            o.setGreska(e);

        }
        return o;

    }

}
