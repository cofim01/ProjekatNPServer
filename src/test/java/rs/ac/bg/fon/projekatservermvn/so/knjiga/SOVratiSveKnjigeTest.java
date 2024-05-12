package rs.ac.bg.fon.projekatservermvn.so.knjiga;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SOVratiSveKnjigeTest {

    SOVratiSveKnjige so;

    public SOVratiSveKnjigeTest() {
        so = new SOVratiSveKnjige();
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
        assertEquals("Prosledjeni objekat nije instanca knjige!", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new Knjiga();
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
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        lista.add(k1);

        try {
            when(dbBrokerMock.select(any(Knjiga.class))).thenReturn(lista);
            so.execute(new Knjiga());
            verify(dbBrokerMock).select(any(Knjiga.class));
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        assertEquals(1, so.getLista().size());
        assertEquals(6, so.getLista().get(0).getKnjigaId());
        assertEquals("Kum", so.getLista().get(0).getNaziv());

    }

}
