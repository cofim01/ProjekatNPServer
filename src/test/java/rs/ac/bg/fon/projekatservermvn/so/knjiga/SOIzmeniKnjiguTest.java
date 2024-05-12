
package rs.ac.bg.fon.projekatservermvn.so.knjiga;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


public class SOIzmeniKnjiguTest {
    
    SOIzmeniKnjigu so;
    public SOIzmeniKnjiguTest() {
        so=new SOIzmeniKnjigu();
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
        assertEquals("Prosledjeni objekat nije instanca klase knjiga", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo=new Knjiga();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }



    @Test
    public void testExecute() throws Exception {
        Knjiga k = new Knjiga();
        k.setNaziv("Neka knjiga");

        try {
            doNothing().when(dbBrokerMock).update(k);
            so.execute(k);
            verify(dbBrokerMock).update(k); 
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
    }
    
}
