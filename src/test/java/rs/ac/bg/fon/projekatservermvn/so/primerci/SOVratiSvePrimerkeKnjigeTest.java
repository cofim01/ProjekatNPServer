package rs.ac.bg.fon.projekatservermvn.so.primerci;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;

public class SOVratiSvePrimerkeKnjigeTest {

    SOVratiSvePrimerkeKnjige so;

    public SOVratiSvePrimerkeKnjigeTest() {
        so = new SOVratiSvePrimerkeKnjige();
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
    public void testExecute() {
        PrimerakKnjige pk = new PrimerakKnjige();
        pk.setPrimerakId(25);
        Knjiga k = new Knjiga();
        k.setKnjigaId(8);
        pk.setKnjiga(k);
        ArrayList<OpstiDomenskiObjekat> lista = new ArrayList<>();
        lista.add(pk);

        try {
            when(dbBrokerMock.selectPoUslovu(any(PrimerakKnjige.class))).thenReturn(lista);
            so.execute(new PrimerakKnjige());
            verify(dbBrokerMock).selectPoUslovu(any(PrimerakKnjige.class));
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        assertEquals(1, so.getLista().size());
        assertEquals(25, so.getLista().get(0).getPrimerakId());
        assertEquals(8, so.getLista().get(0).getKnjiga().getKnjigaId());
    }

}
