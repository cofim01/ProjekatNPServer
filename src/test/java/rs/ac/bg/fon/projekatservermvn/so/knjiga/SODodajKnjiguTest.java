package rs.ac.bg.fon.projekatservermvn.so.knjiga;

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
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;
import rs.ac.bg.fon.projekatzajednickimvn.domen.PrimerakKnjige;

public class SODodajKnjiguTest {

    SODodajKnjigu so;

    public SODodajKnjiguTest() {
        so = new SODodajKnjigu();
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
        assertEquals("Prosledjeni objekat nije instaca knjige!", e.getMessage());
    }

    @Test
    public void testValidatePostojeciClan() {
        ArrayList<OpstiDomenskiObjekat> knjige = new ArrayList<>();
        Knjiga k = new Knjiga();
        k.setNaziv("Na Drini cuprija");
        knjige.add(k);
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(knjige
            );
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }
        Exception e = assertThrows(java.lang.Exception.class, () -> so.validate(k));
        assertEquals("Uneta knjiga: Na Drini cuprija vec postoji u bazi!", e.getMessage());

    }

    @Test
    public void testValidateOk() {
        ArrayList<OpstiDomenskiObjekat> knjige = new ArrayList<>();
        Knjiga k1 = new Knjiga();
        k1.setNaziv("Na Drini cuprija");
        knjige.add(k1);

        Knjiga k2 = new Knjiga();
        k2.setNaziv("Hajduci");
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(knjige);
            so.validate(k2);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute() {
        Knjiga k = new Knjiga();
        k.setNaziv("Neka knjiga");
        PrimerakKnjige pk1 = new PrimerakKnjige();
        pk1.setGodinaIzdanja(2016);
        PrimerakKnjige pk2 = new PrimerakKnjige();
        pk2.setGodinaIzdanja(2017);
        ArrayList<PrimerakKnjige> primerci = new ArrayList<>();
        primerci.add(pk1);
        primerci.add(pk2);
        k.setPrimerci(primerci);

        try {
            when(dbBrokerMock.insert(k)).thenReturn(1);
            when(dbBrokerMock.insert(pk1)).thenReturn(2);
            when(dbBrokerMock.insert(pk2)).thenReturn(3);
            so.execute(k);
            verify(dbBrokerMock).insert(k); 
            verify(dbBrokerMock).insert(pk1); 
            verify(dbBrokerMock).insert(pk2); 
            verifyNoMoreInteractions(dbBrokerMock);
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }
        
        assertEquals(1, k.getKnjigaId()); 
        assertEquals(1, so.getPk()); 
    }

}
