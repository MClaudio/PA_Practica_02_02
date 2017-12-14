
package Vista;

import Controlador.GD_Programa_F;
import Modelo.Programa_F.Consulta;
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
public class VtnListar_F_Consulta extends JInternalFrame{
    private JTable tlbConsultas;
    private JPanel panel;
    private GD_Programa_F gdF;

    public VtnListar_F_Consulta() {
        initComponets();
    }

    private void initComponets() {
        setTitle("Listar Consultas");
        setClosable(true);
        setMaximizable(true);
        setSize(650, 400);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    private void initTable() {
        try {
            gdF = new GD_Programa_F(); 
            String[] cabecera = {"#", "Fecha", "Hora", "Doctor", "Paciente"};
            List<Consulta> consultas=gdF.listaConsulta("src/Archivos/Programa_F/Consultas.txt");
            String[][] datos=new String[consultas.size()][cabecera.length];
            for (int i = 0; i < consultas.size(); i++) {
                Consulta get = consultas.get(i);
                datos[i][0]=""+(i+1);
                datos[i][1]=get.getFecha();
                datos[i][2]=get.getHora();
                datos[i][3]=get.getDoctor().getNombre()+" "+get.getDoctor().getApellido();
                datos[i][4]=get.getPaciente().getNombre()+" "+get.getPaciente().getApellido();
            }
            
            tlbConsultas=new JTable(datos, cabecera);
            JScrollPane scroll=new JScrollPane(tlbConsultas);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
