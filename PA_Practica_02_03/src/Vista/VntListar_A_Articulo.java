
package Vista;

import Controlador.GD_Programa_A;
import Modelo.Programa_A.Articulo;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VntListar_A_Articulo extends JInternalFrame {
    
    private JTable tblArticulo;
    private JPanel panel;
    private GD_Programa_A gdA;
    
    public VntListar_A_Articulo(){
        initcomponentes();
    }
    
    public void initcomponentes(){
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
            String[] cabecera={"#", "Titulo", "Abstract", "Pagina de Inicio", "Pagina de Fin", "Autor"};
            List<Articulo> ar=gdA.listarArticulo("src/Archivos/Programa_A/Articulo.txt");
            String[][]datos=new String [ar.size()][cabecera.length];
            for(int i=0; i<ar.size();i++){
                Articulo get=ar.get(i);
                int j=i;
                datos[j][0]=""+(j+1);
                datos[j][1]=get.getTitulo();
                datos[j][2]=get.getAbstrac();
                datos[j][3]=get.getPaginaInicio();
                datos[j][4]=get.getPaginaFin();
                datos[j][5]=get.getAutores().getNombre();
            }
            tblArticulo=new JTable(datos, cabecera);
            JScrollPane scroll = new JScrollPane(tblArticulo);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
            "Error: Algunos datos no an sido ingresados.", e.getMessage(),
            JOptionPane.ERROR_MESSAGE); 
        }
    }
}
