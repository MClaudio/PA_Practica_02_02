package Vista;

import Controlador.GD_Programa_E;
import Modelo.Programa_E.Equipo;
import Modelo.Programa_E.Inscripcion;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_E_Inscripciones extends JInternalFrame {

    private GD_Programa_E gdE;
    private JTable tlbInscripciones;
    private JPanel panel;

    public VtnListar_E_Inscripciones() {
        initComponets();
    }

    private void initComponets() {
        setTitle("Listar Inscripciones");
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
            String[] cabecera = {"#", "Fecha", "Torneo", "Equipo"};
            List<Inscripcion> inscripciones = gdE.listarInscripciones("src/Archivos/Programa_E/Inscripciones.txt");

            String[][] datos = new String[inscripciones.size()][cabecera.length];
            for (int i = 0; i < inscripciones.size(); i++) {
                Inscripcion get = inscripciones.get(i);
                datos[i][0] = "" + (i + 1);
                datos[i][1] = get.getFecha();
                datos[i][2] = get.getTorneo();
                datos[i][3] = get.getEquipo().getNombre();
            }
            tlbInscripciones = new JTable(datos, cabecera);
            JScrollPane scroll = new JScrollPane(tlbInscripciones);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
