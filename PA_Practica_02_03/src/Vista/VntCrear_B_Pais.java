
package Vista;

import Controlador.GD_Programa_B;
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

public class VntCrear_B_Pais extends JInternalFrame implements ActionListener {

    private JTextField nombreContinente;
    private JTextField nombrePais;
    private JTextField idioma;
    private GD_Programa_B gdB;
    
    public VntCrear_B_Pais(){
        implement();
    }
    
    public void implement(){
        setTitle("");
        setClosable(true);
        setMaximizable(true);
        setSize(300,200);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        panel.add(new JLabel("Continente"));
        nombreContinente = new JTextField(10);
        panel.add(nombreContinente);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Pais"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        nombrePais = new JTextField(5);
        panel.add(nombrePais, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Idioma"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        idioma = new JTextField(5);
        panel.add(idioma, gbc);
                
        gbc.gridx = 1;
        gbc.gridy = 3;
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
        
    private void btnGuardar() {
        try {
            gdB = new GD_Programa_B("src/Archivos/Programa_B/Pais.txt");

            if (nombreContinente.getText().equals("") || nombrePais.getText().equals("") || idioma.getText().equals("")) {
                throw new Exception("Deve llenar todos los campos.");
            }
            gdB.agregarPais(nombreContinente.getText(), nombrePais.getText(),idioma.getText() );

            nombreContinente.setText("");
            nombrePais.setText("");
            idioma.setText("");
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
