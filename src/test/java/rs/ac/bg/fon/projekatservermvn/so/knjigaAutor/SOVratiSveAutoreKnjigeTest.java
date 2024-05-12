package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Autor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SOVratiSveAutoreKnjigeTest {

    SOVratiSveAutoreKnjige so;

    public SOVratiSveAutoreKnjigeTest() {
        so = new SOVratiSveAutoreKnjige();
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
        assertEquals("Prosledjeni objekat nije instanca klase knjigaAutor!", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new KnjigaAutor();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute() {
        Knjiga k1 = new Knjiga();
        k1.setNaziv("Kum");
        k1.setKnjigaId(6);
        Autor a = new Autor();
        a.setAutorId(25);
        a.setIme("Mario");
        a.setPrezime("Puzo");

        KnjigaAutor ka = new KnjigaAutor();
        ka.setKnjigaAutorId(6);
        ka.setKnjiga(k1);
        ka.setAutor(a);
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        lista.add(ka);

        try {
            when(dbBrokerMock.select(any(KnjigaAutor.class))).thenReturn(lista);
            so.execute(new KnjigaAutor());
            verify(dbBrokerMock).select(any(KnjigaAutor.class));
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        assertEquals(1, so.getLista().size());
        assertEquals(6, so.getLista().get(0).getKnjigaAutorId());
        assertEquals(6, so.getLista().get(0).getKnjiga().getKnjigaId());
        assertEquals("Kum", so.getLista().get(0).getKnjiga().getNaziv());
        assertEquals(25, so.getLista().get(0).getAutor().getAutorId());
        assertEquals("Mario", so.getLista().get(0).getAutor().getIme());
        assertEquals("Puzo", so.getLista().get(0).getAutor().getPrezime());
    }

}
