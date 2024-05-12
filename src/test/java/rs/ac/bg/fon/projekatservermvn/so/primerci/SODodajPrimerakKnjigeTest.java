package rs.ac.bg.fon.projekatservermvn.so.primerci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;

public class SODodajPrimerakKnjigeTest {

    SODodajPrimerakKnjige so;

    public SODodajPrimerakKnjigeTest() {
        so = new SODodajPrimerakKnjige();
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
        assertEquals("Proesledjeni objekat nije instaca klase PrimerakKnjige!", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new PrimerakKnjige();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute() {
        PrimerakKnjige pk = new PrimerakKnjige();

        try {
            when(dbBrokerMock.insert(pk)).thenReturn(89);
            so.execute(pk);
            verify(dbBrokerMock).insert(pk);
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        assertEquals(89, so.getPk());
    }

}
