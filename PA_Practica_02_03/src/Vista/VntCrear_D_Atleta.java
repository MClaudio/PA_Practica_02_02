/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Controlador.GD_Programa_D;
import Modelo.Programa_D.Resultado;
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
public class VntCrear_D_Atleta extends JInternalFrame implements ActionListener {

    private JTextField txtNombreApellido;
    private JTextField txtCedula;
    private JTextField txtFechaNac;
    private JTextField txtDireccion;
    private JTextField txtAltura;
    private JTextField txtPeso;
    private JComboBox<String> resultados;
    private String auxresultados;
    private GD_Programa_D gdD;

    private JButton btnGuardar;
    private JButton btnNuevo;

    public VntCrear_D_Atleta() {

        initComponents();
    }

    private void initComponents() {
        // TODO Auto-generated method stub

        setSize(400, 300);
        setTitle("INGRESAR ATLETAS");
        setClosable(true);
        setMaximizable(false);
        setMaximizable(true);

        getContentPane().setLayout(new FlowLayout());

        gdD = new GD_Programa_D("src/Archivos/Programa_D/Resultado.txt");
        List<Resultado> result = null;
        try {
            result = gdD.leerDatosResultado();
        } catch (Exception e) {

        }
        resultados = new JComboBox<String>();
        resultados.setBounds(29, 35, 148, 20);
        String arreglo[] = gdD.listResultados(result);
        resultados.setModel(new DefaultComboBoxModel<>(arreglo));
        resultados.addActionListener(this);

        btnGuardar = new JButton("GUARDAR");
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("botonGuardar");

        btnNuevo = new JButton("NUEVO");
        btnNuevo.addActionListener(this);
        btnNuevo.setActionCommand("botonNuevo");

        JLabel etiqueta1 = new JLabel("NOMBRE APELLIDO ");
        JLabel etiqueta2 = new JLabel("NUMERO CEDULA ");
        JLabel etiqueta3 = new JLabel("FECHA NACIMIENTO ");
        JLabel etiqueta4 = new JLabel("DIRECCION ");
        JLabel etiqueta5 = new JLabel("ALTURA (cm) ");
        JLabel etiqueta6 = new JLabel("PESO (kg) ");
        JLabel etiqueta7 = new JLabel("RESULTADO (seg)");

        txtNombreApellido = new JTextField(15);
        txtCedula = new JTextField(15);
        txtFechaNac = new JTextField(15);
        txtDireccion = new JTextField(15);
        txtAltura = new JTextField(15);
        txtPeso = new JTextField(15);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBorder(BorderFactory.createTitledBorder("Datos Atletas "));

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
        panel1.add(txtDireccion, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel1.add(etiqueta5, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel1.add(txtAltura, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel1.add(etiqueta6, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel1.add(txtPeso, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel1.add(etiqueta7, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel1.add(resultados, gbc);

        JPanel botones = new JPanel();
        botones.setLayout(new FlowLayout());
        botones.add(btnGuardar);
        botones.add(btnNuevo);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel1.add(botones, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        String comando = evt.getActionCommand();

        System.out.println("evento boton " + comando);

        if (evt.getSource() == resultados) {
            auxresultados = resultados.getSelectedItem().toString();
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

    }

    private void guardar() {

        List<Resultado> resultado = null;
        try {
            GD_Programa_D gdD = new GD_Programa_D("src/Archivos/Programa_D/Atleta.txt");

            if (txtNombreApellido.getText().equals("") || txtCedula.getText().equals("") || txtFechaNac.getText().equals("") || txtDireccion.getText().equals("") || txtAltura.getText().equals("") || txtPeso.getText().equals("") || auxresultados == resultados.getItemAt(0)) {
                throw new Exception("Debe llenar todos los campos.");
            }

            gdD.validarCedula(txtCedula.getText());
            gdD.verificarCedula(gdD.leerDatosAtleta(), txtCedula.getText());
            resultado = gdD.leerDatosResultado();
            Resultado result = gdD.buscarResutado(resultado, auxresultados);
            gdD.crearAtleta(txtNombreApellido.getText(), txtCedula.getText(), txtFechaNac.getText(), txtDireccion.getText(), txtAltura.getText(), txtPeso.getText(), result.getTiempoFinal());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

}
