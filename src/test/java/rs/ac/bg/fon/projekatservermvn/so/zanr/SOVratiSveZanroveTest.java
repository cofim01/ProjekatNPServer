package rs.ac.bg.fon.projekatservermvn.so.zanr;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zanr;

public class SOVratiSveZanroveTest {

    SOVratiSveZanrove so;

    public SOVratiSveZanroveTest() {
        so = new SOVratiSveZanrove();
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
        assertEquals("Prosledjeni objekat nije instanca klase Zanr", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new Zanr();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute()  {
        Zanr z1=new Zanr(5, "Komedija");
        Zanr z2=new Zanr(6, "Tragedija");
        ArrayList<OpstiDomenskiObjekat> zanrovi=new ArrayList<>();
        zanrovi.add(z1);
        zanrovi.add(z2);
        
        try {
            when(dbBrokerMock.select(any(Zanr.class))).thenReturn(zanrovi);
            so.execute(new Zanr());
            verify(dbBrokerMock).select(any(Zanr.class));
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        
        assertEquals(2, so.getLista().size());
        assertEquals(5, so.getLista().get(0).getZanrId());
        assertEquals("Komedija", so.getLista().get(0).getNaziv());
        assertEquals(6, so.getLista().get(1).getZanrId());
        assertEquals("Tragedija", so.getLista().get(1).getNaziv());
    }



}
