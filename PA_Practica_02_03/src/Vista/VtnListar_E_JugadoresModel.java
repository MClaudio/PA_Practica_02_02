
package Vista;

import Modelo.Programa_E.Jugador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_E_JugadoresModel extends AbstractTableModel{
    public String[] columnas = {"Nombre", "Apellido", "Cedula", "Edad", "Nombre Deportivo", "Numero de Camiseta"};
    public Class[] columnasTipos = {String.class, String.class, String.class, Integer.class, String.class, Integer.class};
    private List<Jugador> datos;

    public VtnListar_E_JugadoresModel() {
        datos=new ArrayList<Jugador>();
    }

    public VtnListar_E_JugadoresModel(List<Jugador> datos) {
        this.datos = datos;
    }
    
    
    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Jugador dato = (Jugador) (datos.get(row));

        switch (col) {
            case 0:
                return dato.getNombre();
            case 1:
                return dato.getApellido();
            case 2:
                return dato.getCedula();
            case 3:
                return dato.getEdad();
            case 4:
                return dato.getNombreDeportivo();
            case 5:
                return dato.getNumCamiseta();
            default:
                break;
        }
        return new String();
    }

    public String getColumnName(int col) {
        return columnas[col];
    }

    public Class getColumnClass(int col) {
        return columnasTipos[col];
    }

    public void setValueAt(Object value, int row, int col) {
        Jugador dato = (Jugador) (datos.get(row));

        switch (col) {
            case 0:
                dato.setNombre((String) value);
                break;
            case 1:
                dato.setApellido((String) value);
                break;
            case 2:
                dato.setCedula((String) value);
                break;
                case 3:
                dato.setEdad((Integer) value);
                break;
                case 4:
                dato.setNombreDeportivo((String) value);
                break;
            case 5:
               dato.setNumCamiseta((Integer) value);
                break;
            default:
                break;
        }
    }
}
