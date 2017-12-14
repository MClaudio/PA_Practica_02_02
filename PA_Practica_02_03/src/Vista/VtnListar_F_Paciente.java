
package Vista;

import Controlador.GD_Programa_F;
import Modelo.Programa_F.Paciente;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_F_Paciente extends JInternalFrame{
    private GD_Programa_F gdF;
    private JPanel panel;
    private JTable tlbPacientes;

    public VtnListar_F_Paciente() {
        initComponets();
    }
    
    public void initComponets() {
        setTitle("Listar Pacientes");
        setClosable(true);
        setMaximizable(true);
        setSize(650, 500);
        
        panel=new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }
    
    public void initTable(){
        gdF=new GD_Programa_F();
        String[] cabecera = {"#", "Nombre", "Apellido", "Cedula", "Edad"};
        try {
            List<Paciente> pacientes=gdF.leerDatosPacientes("src/Archivos/Programa_F/Pacientes.txt");
            String[][]datos=new String[pacientes.size()][cabecera.length];
            
            for (int i = 0; i < pacientes.size(); i++) {
                Paciente get = pacientes.get(i);
                datos[i][0]=""+(i+1);
                datos[i][1]=get.getNombre();
                datos[i][2]=get.getApellido();
                datos[i][3]=get.getCedula();
                datos[i][4]=""+get.getEdad();
            }
            tlbPacientes=new JTable(datos, cabecera);
            JScrollPane scroll=new JScrollPane(tlbPacientes);
            panel.add(scroll);
            getContentPane().add(scroll);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
