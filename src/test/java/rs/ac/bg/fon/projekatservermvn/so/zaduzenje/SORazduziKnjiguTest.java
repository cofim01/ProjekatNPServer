package rs.ac.bg.fon.projekatservermvn.so.zaduzenje;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Zaduzenje;

public class SORazduziKnjiguTest {

    SORazduziKnjigu so;

    public SORazduziKnjiguTest() {
        so = new SORazduziKnjigu();
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
        assertEquals("Prosledjeni objekat nije instanca klase Zaduzenje!", e.getMessage());
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
        z.setBrZaduzenja(5);
        PrimerakKnjige pk = new PrimerakKnjige();
        pk.setPrimerakId(8);
        z.setPrimerak(pk);

        try {
            doNothing().when(dbBrokerMock).update(z);
            doNothing().when(dbBrokerMock).update(pk);
            so.execute(z);
            verify(dbBrokerMock).update(z);
            verify(dbBrokerMock).update(pk);
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
    }

}
