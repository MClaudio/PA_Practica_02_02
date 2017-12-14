
package Vista;

import Controlador.GD_Programa_B;
import Modelo.Programa_B.Pais;
import Modelo.Programa_B.Provincia;
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
 
public class VntCrear_B_Canton extends JInternalFrame implements ActionListener{
    
    private JTextField nombreCanton;
    private JTextField codigoPostal;
    private JTextField numeroHabitantes;
    private JComboBox provincia1;
    private GD_Programa_B gdB;
    
    public VntCrear_B_Canton(){
        initcomponent();
    }
    
    public void initcomponent(){
        setTitle("Canton");
        setClosable(true);
        setMaximizable(true);
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panel.add(new JLabel("Nombre:"));
        nombreCanton = new JTextField(10);
        panel.add(nombreCanton);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Codigo postal:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        codigoPostal = new JTextField(5);
        panel.add(codigoPostal, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Numero de habitantes:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        numeroHabitantes = new JTextField(5);
        panel.add(numeroHabitantes, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Provincia:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        provincia1 = new JComboBox();
        listarProvincia();
        panel.add(provincia1, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("btnGuardar");
        panel.add(btnGuardar, gbc);

        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("btnGuardar")) {
            btnGuardar();
        }
    }
    
    private void listarProvincia() {
        gdB = new GD_Programa_B();
        try {
            
            List<Provincia> provincias = gdB.listarProvincia("src/Archivos/Programa_B/Provincia.txt");
            String[] listProvincias = new String[provincias.size()];
            for (int i = 0; i < provincias.size(); i++) {
                Provincia get = provincias.get(i);
                listProvincias[i] = get.getNombreProvincia();
            }
            provincia1.setModel(new DefaultComboBoxModel(listProvincias));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e.getMessage(), e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void btnGuardar(){
        try{
            gdB=new GD_Programa_B("src/Archivos/Programa_B/Canton.txt");
            if (nombreCanton.getText().equals("") || codigoPostal.getText().equals("") || numeroHabitantes.getText().equals("")) {
                throw new Exception("Porfavor rellene todos los campos");
            }
            if (provincia1.getSelectedItem() == null) {
                throw new Exception("La lista de autores esta vacia");
            }
            gdB.agregarCanton(nombreCanton.getText(), codigoPostal.getText(), numeroHabitantes.getText(), (String) provincia1.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
