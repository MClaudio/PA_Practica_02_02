package Vista;

import Controlador.GD_Programa_E;
import Modelo.Programa_E.Equipo;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_E_Equipos extends JInternalFrame {

    private GD_Programa_E gdE;
    private JTable tlbEquipos;
    private JPanel panel;

    public VtnListar_E_Equipos() {
        initComponets();
    }

    private void initComponets() {
        setTitle("Listar Equipos");
        setClosable(true);
        setMaximizable(true);
        setSize(650, 500);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    private void initTable() {
        try {
            gdE = new GD_Programa_E();
            String[] cabecera = {"#", "Nombre", "Categoria"};
            List<Equipo> equipos = gdE.listarEquipo("src/Archivos/Programa_E/Equipos.txt");
            String[][] datos = new String[equipos.size()][cabecera.length];
            for (int i = 0; i < equipos.size(); i++) {
                Equipo get = equipos.get(i);
                datos[i][0]=""+(i+1);
                datos[i][1]=get.getNombre();
                datos[i][2]=get.getCategoria();   
            }
            tlbEquipos=new JTable(datos, cabecera);
            JScrollPane scroll=new JScrollPane(tlbEquipos);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
