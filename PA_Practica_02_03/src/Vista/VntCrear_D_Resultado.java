/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Controlador.GD_Programa_D;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Cristian
 */
public class VntCrear_D_Resultado extends JInternalFrame implements ActionListener {

    private JTextField txtTiempoFinal;
    private JTextField txtVelocidad;
    private JTextField txtDistanRecorr;

    JButton btnGuardar;
    JButton btnNuevo;

    JLabel etiqueta1;
    JLabel etiqueta2;
    JLabel etiqueta3;
    JLabel etiqueta4;

    public VntCrear_D_Resultado() {

        initComponents();
    }

    private void initComponents() {
        // TODO Auto-generated method stub

        setSize(400, 300);
        setTitle("INGRESAR RESULTADOS");
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

        etiqueta1 = new JLabel("TIEMPO FINAL (segundos)");
        etiqueta2 = new JLabel("VELOCIDAD (m/seg) ");
        etiqueta3 = new JLabel("DISTANCIA RECORRIDA (m) ");

        txtTiempoFinal = new JTextField(5);
        txtVelocidad = new JTextField(5);
        txtDistanRecorr = new JTextField(5);

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
        panel1.add(txtTiempoFinal, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(etiqueta2, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(txtVelocidad, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel1.add(etiqueta3, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel1.add(txtDistanRecorr, gbc);

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
        txtTiempoFinal.setText(" ");
        txtVelocidad.setText(" ");
        txtDistanRecorr.setText(" ");

    }

    private void guardar() {

        try {
            if (txtTiempoFinal.getText().equals("") || txtVelocidad.getText().equals("") || txtDistanRecorr.getText().equals("")) {
                throw new Exception("Debe llenar todos los campos.");
            }
            GD_Programa_D gdD = new GD_Programa_D("src/Archivos/Programa_D/Resultado.txt");
            gdD.crearResultado(txtTiempoFinal.getText(), txtVelocidad.getText(), txtDistanRecorr.getText());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

}
