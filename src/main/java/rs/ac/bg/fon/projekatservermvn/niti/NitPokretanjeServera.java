package rs.ac.bg.fon.projekatservermvn.niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author MRDAK-PC
 */
public class NitPokretanjeServera extends Thread {

    private ServerSocket ss;

    public NitPokretanjeServera() {
        try {
            ss = new ServerSocket(9000);
            System.out.println("Uspesno podignut server.");
        } catch (IOException ex) {
            System.out.println("Neuspesno podizanje servera.");
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                System.out.println("Server ceka klijente...");
                Socket s = ss.accept();
                System.out.println("Klijent se povezao sa serverom.");
                NitObradaZahteva obradaZahteva = new NitObradaZahteva(s);
                obradaZahteva.start();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
