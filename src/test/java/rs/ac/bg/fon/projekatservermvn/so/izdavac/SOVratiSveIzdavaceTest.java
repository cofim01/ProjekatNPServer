
package rs.ac.bg.fon.projekatservermvn.so.izdavac;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Izdavac;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


public class SOVratiSveIzdavaceTest {
    
    SOVratiSveIzdavace so;
    public SOVratiSveIzdavaceTest() {
        so=new SOVratiSveIzdavace();
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
        OpstiDomenskiObjekat odo = new Knjiga();
        
        Exception e = assertThrows(java.lang.Exception.class, () -> so.validate(odo));
        assertEquals("Prosledjeni objekat nije instanca klase Izdavac!", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo=new Izdavac();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom validacije.");
        }
    }

    @Test
    public void testExecute() {
        ArrayList<OpstiDomenskiObjekat> izdavaci = new ArrayList<>();
        Izdavac i=new Izdavac();
        i.setIzdavacId(5);
        i.setNaziv("Laguna");
        izdavaci.add(i);
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(izdavaci);
            so.execute(new Izdavac());
            verify(dbBrokerMock).select(any(OpstiDomenskiObjekat.class)); 
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        
        
        
        assertEquals(1, so.getLista().size());
        assertEquals(5, so.getLista().get(0).getIzdavacId());
        assertEquals("Laguna", so.getLista().get(0).getNaziv());

    }

    
}
