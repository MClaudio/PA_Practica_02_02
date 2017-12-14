
package Vista;

import Controlador.GD_Programa_B;
import Modelo.Programa_B.Canton;
import Modelo.Programa_B.Pais;
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

public class VntCrear_B_Provincia extends JInternalFrame implements ActionListener {
    
    private JTextField nombreProvincia;
    private JTextField region;
    private JTextField gobernador;
    private JComboBox pais;
    private GD_Programa_B gdB;
    
    public VntCrear_B_Provincia(){
        implement();
    }
    
    public void implement(){
        setTitle("");
        setClosable(true);
        setMaximizable(true);
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints abc = new GridBagConstraints();

        panel.add(new JLabel("Provincia"));
        nombreProvincia = new JTextField(15);
        panel.add(nombreProvincia);
        
        abc.gridx = 0;
        abc.gridy = 1;
        panel.add(new JLabel("Region"), abc);
        abc.gridx = 1;
        abc.gridy = 1;
        region = new JTextField(15);
        panel.add(region, abc);
        
        abc.gridx = 0;
        abc.gridy = 2;
        panel.add(new JLabel("Gobernador"), abc);
        abc.gridx = 1;
        abc.gridy = 2;
        gobernador = new JTextField(5);
        panel.add(gobernador, abc);
        
        abc.gridx = 0;
        abc.gridy = 3;
        panel.add(new JLabel("Pais"), abc);
        abc.gridx = 1;
        abc.gridy = 3;
        pais = new JComboBox();
        listarPais();
        panel.add(pais, abc);
        
        abc.gridx = 1;
        abc.gridy = 4;
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(this);
        btnGuardar.setActionCommand("btnGuardar");
        panel.add(btnGuardar, abc);
        
        getContentPane().add(panel);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "btnGuardar":
                guardar();
                break;
        }
    }
    
    public void listarPais() {
        gdB = new GD_Programa_B();
        try {
            List<Pais> paises = gdB.listarPais("src/Archivos/Programa_B/Pais.txt");
            String[] listPaises = new String[paises.size()];
            for (int i = 0; i < paises.size(); i++) {
                Pais get = paises.get(i);
                listPaises[i] = get.getNombrePais();
            }
            pais.setModel(new DefaultComboBoxModel(listPaises));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void guardar() {
        try {
            gdB = new GD_Programa_B("src/Archivos/Programa_B/Provincia.txt");
            if (nombreProvincia.getText().equals("") || region.getText().equals("") || gobernador.getText().equals("")) {
                throw new Exception("Deve llenar todos los campos.");
            }
            if (pais.getSelectedItem() == null) {
                throw new Exception("La lista de paises esta vacia");
            }
            gdB.agregarProvincia(nombreProvincia.getText(), region.getText(), gobernador.getText(), (String) pais.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Datos Guardados", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
