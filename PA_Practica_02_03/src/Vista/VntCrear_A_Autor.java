
package Vista;

import Controlador.GD_Programa_A;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VntCrear_A_Autor extends JInternalFrame implements ActionListener {
    
    private JTextField codigo;
    private JTextField nombre;
    private JTextField anioNacimiento;
    private JTextField nacionalidad;
    private GD_Programa_A gdA;
    
    public VntCrear_A_Autor(){
        implement();
    }
    
    private void implement(){
        setTitle("");
        setClosable(true);
        setMaximizable(true);
        setSize(300,200);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints abc=new GridBagConstraints();
        
        panel.add(new JLabel("Codigo"));
        codigo=new JTextField(10);
        panel.add(codigo);
        
        abc.gridx=0;
        abc.gridy=1;
        panel.add(new JLabel("Nombre del Autor"),abc);
        abc.gridx=1;
        abc.gridy=1;
        nombre=new JTextField(15);
        panel.add(nombre,abc);
        
        abc.gridx=0;
        abc.gridy=2;
        panel.add(new JLabel("Año de Nacimiento"),abc);
        abc.gridx=1;
        abc.gridy=2;
        anioNacimiento=new JTextField(5);
        panel.add(anioNacimiento, abc);
        
        abc.gridx=0;
        abc.gridy=3;
        panel.add(new JLabel("Nacionalidad"),abc);
        abc.gridx=1;
        abc.gridy=3;
        nacionalidad=new JTextField(5);
        panel.add(nacionalidad,abc);
        
        abc.gridx=1;
        abc.gridy=4;
        JButton btnGuardar=new JButton("Guardar"); 
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("btnGuardar");
        panel.add(btnGuardar,abc);
        
        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("btnGuardar")) {
            btnGuardar();
        }
    }
    
    public void btnGuardar(){
        try{
            gdA=new GD_Programa_A("src/Archivos/Programa_A/Autor.txt");
            if (codigo.getText().equals("") || nombre.getText().equals("") || anioNacimiento.getText().equals("") || nacionalidad.getText().equals("")) {
                throw new Exception("Porfavor rellene todos los campos");
            }
            gdA.agregarAutor(codigo.getText(), nombre.getText(), anioNacimiento.getText(), nacionalidad.getText());
            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
            codigo.setText("");
            nombre.setText("");
            anioNacimiento.setText("");
            nacionalidad.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
