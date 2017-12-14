
package Vista;

import Controlador.GD_Programa_E; 
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ClauMldo
 */
public class VtnCrear_E_Equipo extends JInternalFrame implements ActionListener{
    
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private GD_Programa_E gdE;

    public VtnCrear_E_Equipo() {
        initComponents();
    }
    
    private void initComponents(){
        setTitle("Crear Equipo");
        setClosable(true);
        setMaximizable(true);
        setSize(300, 200);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        
        panel.add(new JLabel("Nombre:"));
        txtNombre=new JTextField(15);
        panel.add(txtNombre);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(new JLabel("Categoria:"), gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        txtCategoria=new JTextField(5);
        panel.add(txtCategoria, gbc);
        
        gbc.gridx=1;
        gbc.gridy=2;
        JButton btnGuardar=new JButton("Guardar");
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
    
    public void btnGuardar(){
        try {
            gdE = new GD_Programa_E("src/Archivos/Programa_E/Equipos.txt");
            
            if (txtNombre.getText().equals("") || txtCategoria.getText().equals("")) {
                throw new Exception("Debe llenar todos los campos.");
            }
            gdE.verificarDuplicados(txtNombre.getText());
            
            gdE.crearEquipo(txtNombre.getText(), txtCategoria.getText());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            
            txtNombre.setText("");
            txtCategoria.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
