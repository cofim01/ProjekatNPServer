
package rs.ac.bg.fon.projekatservermvn.so.clan;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


public class SOVratiOdredjeneClanoveTest {
    
    SOVratiOdredjeneClanove so;
    public SOVratiOdredjeneClanoveTest() {
        so=new SOVratiOdredjeneClanove();
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
        assertEquals("Prosledjeni objekat nije instanca klase Clan!", e.getMessage());
    }

    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo=new Clan();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom validacije.");
        }
    }
    
    @Test
    public void testExecute() {
        ArrayList<OpstiDomenskiObjekat> clanovi = new ArrayList<>();
        Clan c = new Clan();
        c.setBrTelefona("0616655333");
        c.setClanId(5);
        c.setIme("Filip");
        c.setPrezime("Filipovic");
        c.setStatus("Neaktivan");
        clanovi.add(c);
        try {
            when(dbBrokerMock.selectPoUslovu(any(OpstiDomenskiObjekat.class))).thenReturn(clanovi);
            so.execute(new Clan());
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        
        assertEquals(1, so.getLista().size());
        assertEquals("0616655333", so.getLista().get(0).getBrTelefona());
        assertEquals(5, so.getLista().get(0).getClanId());
        assertEquals("Filip", so.getLista().get(0).getIme());
        assertEquals("Filipovic", so.getLista().get(0).getPrezime());
        assertEquals("Neaktivan", so.getLista().get(0).getStatus());
    }



    
}
