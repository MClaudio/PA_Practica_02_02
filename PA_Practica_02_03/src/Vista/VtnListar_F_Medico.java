package Vista;

import Controlador.GD_Programa_F;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Modelo.Programa_F.Doctor;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_F_Medico extends JInternalFrame {

    private JTable tlbDoctores;
    private JPanel panel;
    private GD_Programa_F gdF;

    public VtnListar_F_Medico() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Doctores");
        setClosable(true);
        setMaximizable(true);
        setSize(650, 500);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdF = new GD_Programa_F();

            String[] cabecera = {"#", "Nombre", "Apellido", "Cedula", "Especialidad"};

            List<Doctor> doc = gdF.leeDatosMedico("src/Archivos/Programa_F/Medicos.txt");
            String[][] datos = new String[doc.size()][cabecera.length];
            for (int i = 0; i < doc.size(); i++) {
                Doctor get = doc.get(i);
                datos[i][0] = "" + (i + 1);
                datos[i][1] = get.getNombre();
                datos[i][2] = get.getApellido();
                datos[i][3] = get.getCedula();
                datos[i][4] = get.getEspecialidad();
            }
            tlbDoctores = new JTable(datos, cabecera);
            JScrollPane scroll = new JScrollPane(tlbDoctores);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
