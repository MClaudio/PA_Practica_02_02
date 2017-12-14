/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
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
public class VntCrear_C_Empleado extends JInternalFrame implements ActionListener {

    private JTextField txtNombreApellido;
    private JTextField txtCedula;
    private JTextField txtFechaNac;
    private JTextField txtDirecccion;
    private String auxuniversidades;

    private JButton btnGuardar;
    private JButton btnNuevo;

   

    public VntCrear_C_Empleado() {

        initComponents();
    }

    public void initComponents() {
        // TODO Auto-generated method stub

        setSize(400, 300);
        setTitle("INGRESAR EMPLEADOS");
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

        JLabel etiqueta1 = new JLabel("NOMBRE APELLIDO ");
        JLabel etiqueta2 = new JLabel("CEDULA ");
        JLabel etiqueta3 = new JLabel("FECHA NACIMIENTO ");
        JLabel etiqueta4 = new JLabel("DIRECCION ");

        txtNombreApellido = new JTextField(15);
        txtCedula = new JTextField(15);
        txtFechaNac = new JTextField(15);
        txtDirecccion = new JTextField(15);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder("Datos Empleados "));

        getContentPane().add(panel1, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(etiqueta1, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(txtNombreApellido, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(etiqueta2, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(txtCedula, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(etiqueta3, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel1.add(txtFechaNac, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel1.add(etiqueta4, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel1.add(txtDirecccion, gbc);

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
        txtNombreApellido.setText(" ");
        txtCedula.setText(" ");
        txtFechaNac.setText(" ");
        txtDirecccion.setText(" ");

    }

    private void guardar() {
        try {
            if (txtNombreApellido.getText().equals("") || txtCedula.getText().equals("") || txtFechaNac.getText().equals("") || txtDirecccion.getText().equals("")) {
                throw new Exception("Debe llenar todos los campos.");
            }
            GD_Programa_C gdC = new GD_Programa_C("src/Archivos/Programa_C/Empleado.txt");

            gdC.validarCedula(txtCedula.getText());
            gdC.verificarCedula(gdC.leerDatosEmpleado(), txtCedula.getText());
            gdC.crearEmpleado(txtNombreApellido.getText(), txtCedula.getText(), txtFechaNac.getText(), txtDirecccion.getText());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
}