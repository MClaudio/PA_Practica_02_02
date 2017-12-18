
package Vista;

import Controlador.GD_Programa_A;
import Modelo.Programa_A.Revista;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VntListar_A_Revista extends JInternalFrame{
    
    private JTable tblRevista;
    private JPanel panel;
    private GD_Programa_A gdA;
    
    public VntListar_A_Revista(){
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
            gdA=new GD_Programa_A("src/Archivos/Programa_A/Revista.dat");
            String[] cabezera = {"#", "ISBN", "Numero de Edicion", "Nombre de Revista", "Idioma", "Articulo"};
            List<Revista> re=gdA.listarRevista("src/Archivos/Programa_A/Revista.dat");
            String[][] datos=new String[re.size()][cabezera.length];
            for(int i=0; i<re.size();i++){
                Revista get=re.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getIsdn();
                datos[j][2]=get.getNumeroEdicion();
                datos[j][3]=get.getNombre();
                datos[j][4]=get.getIdioma();    
                datos[j][5]=get.getArticulos().getTitulo();
            }
            tblRevista=new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tblRevista);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            /*JOptionPane.showMessageDialog(this,
            "Error: Algunos datos no an sido ingresados.", e.getMessage(),
            JOptionPane.ERROR_MESSAGE);  */ 
        }
    }
}
