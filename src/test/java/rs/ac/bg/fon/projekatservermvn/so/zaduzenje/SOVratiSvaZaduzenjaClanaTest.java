package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;

import java.util.ArrayList;
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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;

public class SOVratiSvaZaduzenjaClanaTest {

    SOVratiOdredjenaZaduzenjaClana so;

    public SOVratiSvaZaduzenjaClanaTest() {
        so = new SOVratiOdredjenaZaduzenjaClana();
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
        assertEquals("Prosledjeni objekat nije instanca klase zaduzenje.", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo = new Zaduzenje();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute() {
        Zaduzenje z = new Zaduzenje();
        z.setBrZaduzenja(10);
        PrimerakKnjige pk = new PrimerakKnjige();
        pk.setPrimerakId(8);
        z.setPrimerak(pk);
        ArrayList<OpstiDomenskiObjekat> zaduzenja=new ArrayList<>();
        zaduzenja.add(z);

        try {
            when(dbBrokerMock.selectPoUslovu(z)).thenReturn(zaduzenja);
            so.execute(z);
            verify(dbBrokerMock).selectPoUslovu(z);
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        
        assertEquals(1, so.getLista().size());
        assertEquals(10, so.getLista().get(0).getBrZaduzenja());
        assertEquals(8, so.getLista().get(0).getPrimerak().getPrimerakId());
    }

}
