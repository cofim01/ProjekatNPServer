package rs.ac.bg.fon.projekatservermvn.kom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.projekatzajednickimvn.komunikacija.transfer.Odgovor;
import rs.ac.bg.fon.projekatzajednickimvn.komunikacija.transfer.Zahtev;


/**
 *
 * @author MRDAK-PC
 */
public class KomunikacijaSaKlijentom {

    private static KomunikacijaSaKlijentom instanca;

    private KomunikacijaSaKlijentom() {

    }

    public static KomunikacijaSaKlijentom getInstanca() {
        if(instanca==null){
            instanca=new KomunikacijaSaKlijentom();
        }
        return instanca;
    }

    public Zahtev primiZahtev(Socket s) throws Exception {
        Zahtev z;
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
        z = (Zahtev) in.readObject();
        return z;
    }
    
    public void posaljiOdgovor(Odgovor o,Socket s) throws Exception{
        ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
        out.writeObject(o);
    
    
    }

}
