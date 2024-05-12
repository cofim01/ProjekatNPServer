package rs.ac.bg.fon.projekatservermvn.so.knjigaAutor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.KnjigaAutor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SODodajAutoreKnjigeTest {
    
    SODodajAutoreKnjige so;
    
    public SODodajAutoreKnjigeTest() {
        so = new SODodajAutoreKnjige();
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
        assertEquals("Proesledjeni objekat nije instaca klase knjigaAutor!", e.getMessage());
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
    public void testExecute() throws Exception {
        KnjigaAutor ka = new KnjigaAutor();
        
        try {
            when(dbBrokerMock.insert(ka)).thenReturn(5);
            so.execute(ka);
            verify(dbBrokerMock).insert(ka);
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        assertEquals(5, so.getPk());
        
    }
    
}
