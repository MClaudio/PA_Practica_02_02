///*
// -(* To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package Vista;

import Controlador.GD_Programa_C;
import Controlador.GD_Programa_F;
import Modelo.Programa_C.Empleado;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
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
 * @author Cristian
 */
public class VntCrear_C_Departamento extends JInternalFrame implements ActionListener {
    
    private JTextField txtNmbreDep;
    private JTextField txtNombreSupe;
    private JTextField txtNumeroDep;
    private JComboBox<String> empleados;
    private GD_Programa_C gdE;
    private JButton btnGuardar;
    private JButton btnNuevo;
    
    private String auxempleados;
    
    public VntCrear_C_Departamento() {
        //this.gdC=gdC;

        initComponents();
        
    }
    
    private void initComponents() {
        // TODO Auto-generated method stub

        setSize(400, 300);
        setTitle("INGRESAR DEPARTAMENTOS");
        setClosable(true);
        setMaximizable(false);
        setMaximizable(true);
        
        getContentPane().setLayout(new FlowLayout());
        
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("botonGuardar");
        
        btnNuevo = new JButton("NUEVO");
        btnNuevo.addActionListener(this);
        btnNuevo.setActionCommand("botonNuevo");
        
        JLabel etiqueta1 = new JLabel("NOMBRE DEPARTAMENTO ");
        JLabel etiqueta2 = new JLabel("NOMBRE SUPERVISOR ");
        JLabel etiqueta3 = new JLabel("NUMERO DEPARTAMENTO ");
        JLabel etiqueta4 = new JLabel("EMPLEADO ");
        
        txtNmbreDep = new JTextField(15);
        txtNombreSupe = new JTextField(15);
        txtNumeroDep = new JTextField(15);
        
        gdE = new GD_Programa_C("src/Archivos/Programa_C/Empleado.txt");
        List<Empleado> empl = null;
        try {
            empl = gdE.leerDatosEmpleado();
        } catch (Exception e) {
            
        }
        empleados = new JComboBox<String>();
        empleados.setBounds(29, 35, 148, 20);
        
        String arreglo[] = gdE.listEmpleados(empl);
        empleados.setModel(new DefaultComboBoxModel<>(arreglo));
        empleados.addActionListener(this);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder("Datos Departamentos "));
        
        getContentPane().add(panel1, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(etiqueta1, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(txtNmbreDep, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(etiqueta2, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(txtNombreSupe, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(etiqueta3, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel1.add(txtNumeroDep, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(etiqueta4, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel1.add(empleados, gbc);
        
        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout());
        botones.add(btnGuardar);
        botones.add(btnNuevo);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel1.add(botones, gbc);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        String comando = evt.getActionCommand();
        
        System.out.println("evento boton " + comando);
        
        if (evt.getSource() == empleados) {
            auxempleados = empleados.getSelectedItem().toString();
        }
        
        switch (comando) {
            
            case "botonGuardar":
                guardar();
                break;
            case "botonNuevo":
                limpiar();
                break;
            default:
                break;
        }
    }
    
    private void limpiar() {
        
        txtNmbreDep.setText("");
        txtNombreSupe.setText("");
        txtNumeroDep.setText("");
        empleados.getItemAt(0);
    }
    
    private void guardar() {
        
        List<Empleado> empl = null;
        try {
            if (txtNmbreDep.getText().equals("") || txtNombreSupe.getText().equals("") || txtNumeroDep.getText().equals("") || auxempleados == empleados.getItemAt(0)) {
                throw new Exception("Debe llenar todos los campos.");
            }
            empl = gdE.leerDatosEmpleado();
            GD_Programa_C gdC = new GD_Programa_C("src/Archivos/Programa_C/Departamento.txt");
            Empleado empleado = gdC.buscarEmpleado(empl, auxempleados);
            //gdC.crearDepartamento(txtNmbreDep.getText(), txtNombreSupe.getText(), txtNumeroDep.getText(), auxempleados);
            gdC.crearDepartamento(txtNmbreDep.getText(), txtNombreSupe.getText(), txtNumeroDep.getText(), empleado.getNombreApellido());
            
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
}
