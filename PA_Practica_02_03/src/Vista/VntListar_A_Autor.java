
package Vista;

import Controlador.GD_Programa_A;
import Modelo.Programa_A.Autor;
import Modelo.Programa_A.Revista;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VntListar_A_Autor extends JInternalFrame {
    
    private JTable tblAutor;
    private JPanel panel;
    private GD_Programa_A gdA;
    
    public VntListar_A_Autor(){
        initcomponent();
    }
    
    public void initcomponent(){
        setTitle("Listar Revistas");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 500);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }
    
    public void initTable(){
        try{
            gdA=new GD_Programa_A();
            String[] cabezera = {"#", "Codigo", "Nombre del Autor", "Año de Nacimiento", "Nacionalidad"};
            List<Autor> re=gdA.listarAutor("src/Archivos/Programa_A/Autor.txt");
            String[][] datos=new String[re.size()][cabezera.length];
            for(int i=0; i<re.size();i++){
                Autor get=re.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getCodigo();
                datos[j][2]=get.getNombre();
                datos[j][3]=get.getAnioNacimiento();
                datos[j][4]=get.getNacionalidad();
            }
            tblAutor=new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tblAutor);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            
        }
    }
}
