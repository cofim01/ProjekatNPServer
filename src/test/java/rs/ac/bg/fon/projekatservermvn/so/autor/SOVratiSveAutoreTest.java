
package rs.ac.bg.fon.projekatservermvn.so.autor;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Autor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;


public class SOVratiSveAutoreTest {
    
    SOVratiSveAutore so;
    public SOVratiSveAutoreTest() {
        so=new SOVratiSveAutore();
    }
    
    @Mock
    private DBBroker dbBrokerMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        DBBroker.setInstanca(dbBrokerMock);
    }
    
    
    @Test
    public void testValidateGreska() {
        OpstiDomenskiObjekat odo=new Clan();
        Exception e=assertThrows(java.lang.Exception.class, ()->so.validate(odo));
        assertEquals("Prosledjeni objekat nije instanca autora!", e.getMessage());
    }
    
    @Test
    public void testValidateOk() {
        OpstiDomenskiObjekat odo=new Autor();
        try {
            so.validate(odo);
            assertTrue(true);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom validacije.");
        }
    }

    @Test
    public void testExecute()  {

        ArrayList<OpstiDomenskiObjekat> autori = new ArrayList<>();
        Autor autor1 = new Autor();
        autor1.setAutorId(1);
        autor1.setIme("Ivo");
        autor1.setPrezime("Andric");
        autori.add(autor1);

        
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(autori);
            so.execute(new Autor());
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        ArrayList<Autor> rezultat=so.getLista();
        assertEquals(1, rezultat.size());
        assertEquals("Ivo", rezultat.get(0).getIme());
        assertEquals("Andric", rezultat.get(0).getPrezime());
        assertEquals(1, rezultat.get(0).getAutorId());
    }



    
}
