
package Vista;

import Controlador.GD_Programa_E;
import Modelo.Programa_E.Equipo;
import Modelo.Programa_E.Jugador;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
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
public class VtnCrear_E_Jugador extends JInternalFrame implements ActionListener{
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtEdad;
    private JTextField txtNombreDeportivo;
    private JTextField txtNumCamiseta;
    private JComboBox equipos;
    private GD_Programa_E gdE;

    public VtnCrear_E_Jugador() {
        initComponets();
    }
    
    private void initComponets(){
        setTitle("Crear Jugador");
        setClosable(true);
        setMaximizable(true);
        setSize(400, 300);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        
        panel.add(new JLabel("Nombre:"));
        txtNombre=new JTextField(15);
        panel.add(txtNombre);
        
        gbc.gridx=0;
        gbc.gridy=1;
        panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        txtApellido=new JTextField(15);
        panel.add(txtApellido, gbc);
        
        gbc.gridx=0;
        gbc.gridy=2;
        panel.add(new JLabel("Cedula:"), gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        txtCedula=new JTextField(15);
        panel.add(txtCedula, gbc);
        
        gbc.gridx=0;
        gbc.gridy=3;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        txtEdad=new JTextField(5);
        panel.add(txtEdad, gbc);
        
        gbc.gridx=0;
        gbc.gridy=4;
        panel.add(new JLabel("Nombre Deportivo:"), gbc);
        gbc.gridx=1;
        gbc.gridy=4;
        txtNombreDeportivo=new JTextField(15);
        panel.add(txtNombreDeportivo, gbc);
        
        gbc.gridx=0;
        gbc.gridy=5;
        panel.add(new JLabel("Numero de Camiseta:"), gbc);
        gbc.gridx=1;
        gbc.gridy=5;
        txtNumCamiseta=new JTextField(5);
        panel.add(txtNumCamiseta, gbc);
        
        gbc.gridx=0;
        gbc.gridy=7;
        panel.add(new JLabel("Equipo:"), gbc);
        gbc.gridx=1;
        gbc.gridy=7;
        equipos=new JComboBox();
        initComboBox();
        panel.add(equipos, gbc);
        
        gbc.gridx=1;
        gbc.gridy=8;
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
            gdE = new GD_Programa_E("src/Archivos/Programa_E/Jugadores.txt");
            if (txtNombre.getText().equals("") || txtApellido.getText().equals("") || txtCedula.getText().equals("") || txtEdad.getText().equals("") || txtNombreDeportivo.getText().equals("")) {
                throw new Exception("Debe llenar todos los campos.");
            }
            if (!gdE.validNumeros(txtCedula.getText())) {
                throw new Exception("El campo cedula debe contener numeros.");
            }
            gdE.validaCedula(txtCedula.getText());
            if (!gdE.validNumeros(txtEdad.getText())) {
                throw new Exception("El campo edad debe contener numeros.");
            }
            if (!gdE.validNumeros(txtNumCamiseta.getText())) {
                throw new Exception("El campo numero de camiseta debe contener numeros.");
            }
            if (equipos.getSelectedItem()==null) {
                throw new Exception("La lista de equipos esta vacia deve crear un equipo.");
            }
            gdE.verificarDuplicados(txtCedula.getText());

            gdE.creaJugador(txtNombre.getText(), txtApellido.getText(), txtCedula.getText(), Integer.parseInt(txtEdad.getText()), txtNombreDeportivo.getText(), Integer.parseInt(txtNumCamiseta.getText()), (String)equipos.getSelectedItem());
            
            agregarJugador();
            
            txtNombre.setText("");
            txtApellido.setText("");
            txtCedula.setText("");
            txtNombreDeportivo.setText("");
            txtEdad.setText("");
            txtNumCamiseta.setText("");
            
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
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
    
    private void agregarJugador(){
        try {
            gdE = new GD_Programa_E();
            gdE.listarEquipo("src/Archivos/Programa_E/Equipos.txt");
            List<Equipo>equipos=gdE.agregaJugador("src/Archivos/Programa_E/Jugadores.txt");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
