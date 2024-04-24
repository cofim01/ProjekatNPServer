package rs.ac.bg.fon.projekatservermvn.so;


import java.sql.SQLException;
import rs.ac.bg.fon.projekatservermvn.db.DBBroker;
import rs.ac.bg.fon.projekatzajednickimvn.domen.OpstiDomenskiObjekat;

/**
 *
 * @author MRDAK-PC
 */
public abstract class OpstiSO {

    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;

    protected abstract void execute(OpstiDomenskiObjekat odo) throws Exception;

    public void executeTemplate(OpstiDomenskiObjekat odo) throws Exception {
        try {
            validate(odo);
            execute(odo);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }

    }

    private void commit() throws SQLException {
        DBBroker.getInstanca().getConection().commit();

    }

    private void rollback() throws SQLException {
        DBBroker.getInstanca().getConection().rollback();
    }

}
