
package Vista;

import Controlador.GD_Programa_B;
import Modelo.Programa_B.Provincia;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VntListar_B_Provincia extends JInternalFrame {
    private JTable tblProvincia;
    private JPanel panel;
    private GD_Programa_B gdB;
    
    public VntListar_B_Provincia(){
        initcomponent();
    }
    
    public void initcomponent(){
        setTitle("Listar Provincia");
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
            String[] cabezera = {"#", "Provincia", "Region", "Gobernador", "Pais"};
            List<Provincia> re=gdB.listarProvincia("src/Archivos/Programa_B/Provincia.txt");
            String[][] datos=new String[re.size()][cabezera.length];
            for(int i=0; i<re.size();i++){
                Provincia get=re.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getNombreProvincia();
                datos[j][2]=get.getRegion();
                datos[j][3]=get.getGobernador();
                datos[j][4]=get.getPais().getNombrePais();
            }
            tblProvincia=new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tblProvincia);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "Error: Algunos datos no an sido ingresados.", e.getMessage(),
            JOptionPane.ERROR_MESSAGE);   
        }
    }
}
