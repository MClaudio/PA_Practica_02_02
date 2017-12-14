/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Modelo.Programa_C.Departamento;
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
public class VntCrear_C_Empresa extends JInternalFrame implements ActionListener {
    
    private JTextField txtNombreEmp;
    private JTextField txtNumeroRuc;
    private JTextField txtNumeroSocios;
    private String auxDepartamentos;
    private JComboBox<String> departamentos;
    private GD_Programa_C gdE;
    
    JButton btnGuardar;
    JButton btnNuevo;
    
    JLabel etiqueta1;
    JLabel etiqueta2;
    JLabel etiqueta3;
    JLabel etiqueta4;
    
    public VntCrear_C_Empresa() {
        
        initComponents();
    }
    
    private void initComponents() {
        // TODO Auto-generated method stub

        setSize(400, 300);
        setTitle("INGRESAR EMPRESAS");
        setClosable(true);
        setMaximizable(false);
        setMaximizable(true);
        
        getContentPane().setLayout(new FlowLayout());
        
        gdE = new GD_Programa_C("src/Archivos/Programa_C/Departamento.txt");
        List<Departamento> departaments = null;
        try {
            departaments = gdE.leerDatosDepartamento();
        } catch (Exception e) {
            
        }
        departamentos = new JComboBox<String>();
        departamentos.setBounds(29, 35, 148, 20);
        String arreglo[] = gdE.listDepartamentos(departaments);
        departamentos.setModel(new DefaultComboBoxModel<>(arreglo));
        departamentos.addActionListener(this);
        
        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("botonGuardar");
        
        btnNuevo = new JButton("NUEVO");
        btnNuevo.addActionListener(this);
        btnNuevo.setActionCommand("botonNuevo");
        
        etiqueta1 = new JLabel("NOMBRE EMPRESA ");
        etiqueta2 = new JLabel("NUMERO RUC ");
        etiqueta3 = new JLabel("NUMERO SOCIOS ");
        etiqueta4 = new JLabel("DEPARTAMENTOS ");
        
        txtNombreEmp = new JTextField(15);
        txtNumeroRuc = new JTextField(15);
        txtNumeroSocios = new JTextField(15);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder("Datos Empresa "));
        
        getContentPane().add(panel1, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(etiqueta1, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(txtNombreEmp, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(etiqueta2, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(txtNumeroRuc, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(etiqueta3, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel1.add(txtNumeroSocios, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(etiqueta4, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel1.add(departamentos, gbc);
        
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
        if (evt.getSource() == departamentos) {
            auxDepartamentos = departamentos.getSelectedItem().toString();
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
        txtNombreEmp.setText("");
        txtNumeroRuc.setText("");
        txtNumeroSocios.setText("");
        departamentos.getItemAt(0);
        
    }
    
    private void guardar() {
        
        List<Departamento> departaments = null;
        
        try {
            if (txtNombreEmp.getText().equals("") || txtNumeroRuc.getText().equals("") || txtNumeroSocios.getText().equals("") || auxDepartamentos == departamentos.getItemAt(0)) {
                throw new Exception("Debe llenar todos los campos.");
            }
            departaments = gdE.leerDatosDepartamento();
            GD_Programa_C gdC = new GD_Programa_C("src/Archivos/Programa_C/Empresa.txt");
            
            Departamento departament = gdC.buscarDepartamento(departaments, auxDepartamentos);
            gdC.crearEmpresa(txtNombreEmp.getText(), txtNumeroRuc.getText(), txtNumeroSocios.getText(), departament.getNombreDep());
//            gdC.creaDepartamento(txtNmbreDep.getText(), txtNombreSupe.getText(), txtNumeroDep.getText(),auxempleados );
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
}
