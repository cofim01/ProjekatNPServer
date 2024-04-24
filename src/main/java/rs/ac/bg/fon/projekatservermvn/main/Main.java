
package rs.ac.bg.fon.projekatservermvn.main;


import java.sql.SQLException;
import javax.swing.JFrame;
import rs.ac.bg.fon.projekatservermvn.forme.ServerskaForma;

/**
 *
 * @author MRDAK-PC
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        ServerskaForma s=new ServerskaForma();
        s.setVisible(true);
        s.setExtendedState(JFrame.MAXIMIZED_BOTH);
           

        
        
    }
}
