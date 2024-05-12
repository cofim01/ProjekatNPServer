package rs.ac.bg.fon.projekatservermvn.so.korisnik;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SOVratiSveKorisnikeTest {

    SOVratiSveKorisnike so;

    public SOVratiSveKorisnikeTest() {
        so = new SOVratiSveKorisnike();
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
    public void testExecute() {
        Korisnik k1 = new Korisnik();
        k1.setKorisnikId(8);
        k1.setIme("Pera");
        k1.setPrezime("Peric");
        k1.setPassword("Pera123");
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        lista.add(k1);

        try {
            when(dbBrokerMock.select(any(Korisnik.class))).thenReturn(lista);
            so.execute(new Korisnik());
            verify(dbBrokerMock).select(any(Korisnik.class));
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        assertEquals(1, so.getLista().size());
        assertEquals(8, so.getLista().get(0).getKorisnikId());
        assertEquals("Pera", so.getLista().get(0).getIme());
        assertEquals("Peric", so.getLista().get(0).getPrezime());
        assertEquals("Pera123", so.getLista().get(0).getPassword());
    }

}
