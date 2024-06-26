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
import rs.ac.bg.fon.projekatzajednickimvn.domen.Autor;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Clan;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Knjiga;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

public class SODodajClanaTest {

    SODodajClana so;

    public SODodajClanaTest() {
        so = new SODodajClana();
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
    public void testValidatePostojeciClan() {
        ArrayList<OpstiDomenskiObjekat> clanovi = new ArrayList<>();
        Clan c = new Clan();
        c.setBrTelefona("0616655333");
        clanovi.add(c);
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(clanovi);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

        Exception e = assertThrows(java.lang.Exception.class, () -> so.validate(c));
        assertEquals("već postoji član sa unetim brojem telefona!", e.getMessage());

    }

    @Test
    public void testValidateOk() {
        ArrayList<OpstiDomenskiObjekat> clanovi = new ArrayList<>();
        Clan c = new Clan();
        c.setBrTelefona("0616655333");
        clanovi.add(c);
        
        Clan c1=new Clan();
        c1.setBrTelefona("0645599666");
        try {
            when(dbBrokerMock.select(any(OpstiDomenskiObjekat.class))).thenReturn(clanovi);
            so.validate(c1);
        } catch (Exception ex) {
            fail("Neocekivana greska.");
        }

    }

    @Test
    public void testExecute() {
        try {
            when(dbBrokerMock.insert(any(OpstiDomenskiObjekat.class))).thenReturn(1);
            so.execute(new Autor());
        } catch (Exception ex) {
            fail("Neocekivana greska prilikom izvrsavanja so.");
        }

        assertEquals(1, so.getPk());
    }

}
