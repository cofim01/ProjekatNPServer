package rs.ac.bg.fon.projekatservermvn.modeli;


import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.projekatzajednickimvn.domen.Korisnik;

/**
 *
 * @author MRDAK-PC
 */
public class ModelTabele extends AbstractTableModel {

    private String[] columnName = {"Ime", "Prezime"};
    private Class[] columnClass = {String.class, String.class};
    private ArrayList<Korisnik> ulogovani = new ArrayList<>();

    @Override
    public int getRowCount() {
        return ulogovani.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = ulogovani.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIme();

            case 1:
                return k.getPrezime();
            default:
                return "";

        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
    
    public void dodajUlogovanog(Korisnik k){
        ulogovani.add(k);
        fireTableDataChanged();
    }
    public void izlogujKorisnika(Korisnik k){
        ulogovani.remove(k);
        fireTableDataChanged();
    }

    
    
    

}
