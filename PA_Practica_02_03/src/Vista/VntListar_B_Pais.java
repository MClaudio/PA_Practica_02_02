
package Vista;

import Controlador.GD_Programa_B;
import Modelo.Programa_B.Pais;
import Modelo.Programa_B.Provincia;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
 
public class VntListar_B_Pais extends JInternalFrame{
    private JTable tblPais;
    private JPanel panel;
    private GD_Programa_B gdB;
    
    public VntListar_B_Pais(){
        initcomponent();
    }
    
    public void initcomponent(){
        setTitle("Listar Pais");
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
            String[] cabezera = {"#", "Continente", "Pais", "Idioma"};
            List<Pais> re=gdB.listarPais("src/Archivos/Programa_B/Pais.txt");
            String[][] datos=new String[re.size()][cabezera.length];
            for(int i=0; i<re.size();i++){
                Pais get=re.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getNombreContinente();
                datos[j][2]=get.getNombrePais();
                datos[j][3]=get.getIdioma();
            } 
            tblPais=new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tblPais);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "Error: Algunos datos no an sido ingresados.", e.getMessage(),
            JOptionPane.ERROR_MESSAGE);   
        }
    }
}
