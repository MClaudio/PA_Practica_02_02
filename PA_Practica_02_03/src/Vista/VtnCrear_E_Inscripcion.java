
package Vista;

import Controlador.GD_Programa_E;
import Modelo.Programa_E.Equipo;
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

/**
 *
 * @author ClauMldo
 */
public class VtnCrear_E_Inscripcion extends JInternalFrame implements ActionListener{
    private JTextField txtFecha;
    private JTextField txtNumero;
    private JTextField txtTorneo;
    private JComboBox equipos;
    private GD_Programa_E gdE;

    public VtnCrear_E_Inscripcion() {
        initComponents();
    }
    
    private void initComponents(){
        setTitle("Crear Inscripcion");
        setClosable(true);
        setMaximizable(true);
        setSize(300, 200);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        
        panel.add(new JLabel("Fecha:"));
        txtFecha=new JTextField(10);
        panel.add(txtFecha);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(new JLabel("Numero:"), gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        txtNumero=new JTextField(10);
        panel.add(txtNumero, gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(new JLabel("Torneo:"), gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        txtTorneo=new JTextField(15);
        panel.add(txtTorneo, gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        panel.add(new JLabel("Equipo:"), gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        equipos=new JComboBox();
        initComboBox();
        panel.add(equipos, gbc);
        
        gbc.gridx=1;
        gbc.gridy=4;
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
            gdE=new GD_Programa_E("src/Archivos/Programa_E/Inscripciones.txt");
            if (txtFecha.getText().equals("") || txtNumero.getText().equals("") || txtTorneo.getText().equals("")) {
                throw new Exception("Debe llenar todos los campos.");
            }
            if (!gdE.validNumeros(txtNumero.getText())) {
                throw new Exception("El campo numero debe contener numeros.");
            }
            if (equipos.getSelectedItem()==null) {
                throw new Exception("La lista de equipos esta vacia deve crear un equipo.");
            }
            gdE.verificarDuplicados(txtTorneo.getText());
            gdE.creaInscripcion(txtFecha.getText(), Integer.parseInt(txtNumero.getText()), txtTorneo.getText(), (String)equipos.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            
            txtFecha.setText("");
            txtNumero.setText("");
            txtTorneo.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void initComboBox(){
        try {
            gdE = new GD_Programa_E();
            List<Equipo> equipo=gdE.listarEquipo("src/Archivos/Programa_E/Equipos.txt");
            String[] datos=new String[equipo.size()];
            for (int i = 0; i < equipo.size(); i++) {
                Equipo get = equipo.get(i);
                datos[i]=get.getNombre();
            }
            equipos.setModel(new DefaultComboBoxModel(datos));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
