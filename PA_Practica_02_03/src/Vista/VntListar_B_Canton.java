
package Vista;

import Controlador.GD_Programa_B;
import Modelo.Programa_A.Revista;
import Modelo.Programa_B.Canton;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VntListar_B_Canton extends JInternalFrame {
    
    private JTable tblCanton;
    private JPanel panel;
    private GD_Programa_B gdB;
    
    public VntListar_B_Canton(){
        initcomponent();
    }
    
    public void initcomponent(){
        setTitle("Listar Canton");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 500);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }
    
    public void initTable(){
        try{
            gdB=new GD_Programa_B();
            String[] cabezera = {"#", "Canton", "Codigo Postal", "N° Habitantes", "Provincia"};
            List<Canton> re=gdB.listarCanton("src/Archivos/Programa_B/Canton.txt");
            String[][] datos=new String[re.size()][cabezera.length];
            for(int i=0; i<re.size();i++){
                Canton get=re.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getNombreCanton();
                datos[j][2]=get.getCodigoPostal();
                datos[j][3]=get.getNumeroHabitantes();
                datos[j][4]=get.getProvincia().getNombreProvincia();
            }
            tblCanton=new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tblCanton);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "Error: Algunos datos no an sido ingresados.", e.getMessage(),
            JOptionPane.ERROR_MESSAGE);   
        }
    }
}
