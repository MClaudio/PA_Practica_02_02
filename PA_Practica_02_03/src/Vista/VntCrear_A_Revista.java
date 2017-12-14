package Vista;

import Controlador.GD_Programa_A;
import Modelo.Programa_A.Articulo;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VntCrear_A_Revista extends JInternalFrame implements ActionListener {

    private JTextField Isdn;
    private JTextField numeroEdicion;
    private JTextField nombre;
    private JTextField idioma;
    private JComboBox articulos;
    private GD_Programa_A gdA;

    public VntCrear_A_Revista() {
        implement();
    }

    private void implement() {
        setTitle("Revista");
        setClosable(true);
        setMaximizable(true);
        setSize(300, 200);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints abc=new GridBagConstraints();
        
        panel.add(new JLabel("Ingrese el ISDN"));
        Isdn=new JTextField(10);
        panel.add(Isdn);
        
        abc.gridx=0;
        abc.gridy=1;
        panel.add(new JLabel("Numero de Edicion"),abc);
        abc.gridx=1;
        abc.gridy=1;
        numeroEdicion=new JTextField(5);
        panel.add(numeroEdicion,abc);
        
        abc.gridx=0;
        abc.gridy=2;
        panel.add(new JLabel("Nombre de la Revista"),abc);
        abc.gridx=1;
        abc.gridy=2;
        nombre=new JTextField(15);
        panel.add(nombre, abc);
        
        abc.gridx=0;
        abc.gridy=3;
        panel.add(new JLabel("Idioma"),abc);
        abc.gridx=1;
        abc.gridy=3;
        idioma=new JTextField(10);
        panel.add(idioma,abc);
        
        abc.gridx=0;
        abc.gridy=4;
        panel.add(new JLabel("Articulo"),abc);
        abc.gridx=1;
        abc.gridy=4;
        articulos=new JComboBox();
        listarAutor();
        panel.add(articulos,abc);
        
        abc.gridx=1;
        abc.gridy=5;
        JButton btnGuardar=new JButton("Guardar"); 
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("btnGuardar");
        panel.add(btnGuardar,abc);
        
        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("btnGuardar")) {
            btnGuardar();
        }
    }
    
    public void listarAutor(){
        gdA=new GD_Programa_A();
        try{
            List<Articulo> articulo= gdA.listarArticulo("src/Archivos/Programa_A/Articulo.txt");
            String [] listArticulo=new String[articulo.size()];
            for(int i=0; i<articulo.size();i++){
                Articulo get = articulo.get(i);
                listArticulo[i]=get.getTitulo();
            }
            articulos.setModel(new DefaultComboBoxModel(listArticulo));
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void btnGuardar(){
        try{
            GD_Programa_A gdA=new GD_Programa_A("src/Archivos/Programa_A/Revista.txt");
            if(Isdn.getText().equals("") || numeroEdicion.getText().equals("") || nombre.getText().equals("") || idioma.getText().equals("")){
                throw new Exception("Deve llenar todos los campos.");
            }
            gdA.agragarRevista(Isdn.getText(), numeroEdicion.getText(), nombre.getText(), idioma.getText(), (String) articulos.getSelectedItem());
            JOptionPane.showMessageDialog(this,"Datos Guardados", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
