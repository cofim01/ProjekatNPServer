package rs.ac.bg.fon.projekatservermvn.so.logovanje;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SOLogovanjeTest {

    SOLogovanje so;

    public SOLogovanjeTest() {
        so = new SOLogovanje();
    }

    @Mock
    private DBBroker dbBrokerMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        DBBroker.setInstanca(dbBrokerMock);
    }

    @Test
    public void testValidateNeodgovarajuciObjekat() {
        OpstiDomenskiObjekat odo = new Clan();
        Exception e = assertThrows(java.lang.Exception.class, () -> so.validate(odo));
        assertEquals("Prosledjeni objekat nije instanca klase Korisnik", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new Korisnik();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecuteNepostojeciKorisnik()  {
        ArrayList<OpstiDomenskiObjekat> korisnici = new ArrayList<>();
        Korisnik k=new Korisnik();
        k.setIme("Filip");
        k.setPrezime("Mrdak");
        k.setPassword("Filip123");
        korisnici.add(k);
        
        Korisnik k2=new Korisnik();
        k2.setIme("Filip");
        k2.setPrezime("Mrdak");
        k2.setPassword("Filip1234");
        
        try {
            when(dbBrokerMock.select(any(Korisnik.class))).thenReturn(korisnici);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }
        Exception e = assertThrows(java.lang.Exception.class, () -> so.execute(k2));
        assertEquals("Neuspesno logovanje, nepostojeći korisnik.", e.getMessage());
    }
    
    @Test
    public void testExecuteOk()  {
        ArrayList<OpstiDomenskiObjekat> korisnici = new ArrayList<>();
        Korisnik k=new Korisnik();
        k.setKorisnikId(5);
        k.setIme("Filip");
        k.setPrezime("Mrdak");
        k.setPassword("Filip123");
        korisnici.add(k);
        
        Korisnik k2=new Korisnik();
        k2.setIme("Filip");
        k2.setPrezime("Mrdak");
        k2.setPassword("Filip123");
        
        try {
            when(dbBrokerMock.select(k2)).thenReturn(korisnici);
            so.execute(k2);
            verify(dbBrokerMock).select(k2);
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }
        assertEquals(5, so.getKorisnik().getKorisnikId());
        assertEquals("Filip", so.getKorisnik().getIme());
        assertEquals("Mrdak", so.getKorisnik().getPrezime());
        assertEquals("Filip123", so.getKorisnik().getPassword());
    }

}
