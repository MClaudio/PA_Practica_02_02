package Vista;

import Controlador.GD_Programa_A;
import Modelo.Programa_A.Autor;
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

public class VntCrear_A_Articulo extends JInternalFrame implements ActionListener {

    private JTextField titulo;
    private JTextField abstrat;
    private JTextField paginaInicio;
    private JTextField paginaFin;
    private JComboBox autor;
    private GD_Programa_A gdA;

    public VntCrear_A_Articulo() {
        implement();
    }

    private void implement() {
        setTitle("");
        setClosable(true);
        setMaximizable(true);
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints abc = new GridBagConstraints();

        panel.add(new JLabel("Titulo"));
        titulo = new JTextField(15);
        panel.add(titulo);

        abc.gridx = 0;
        abc.gridy = 1;
        panel.add(new JLabel("Abstract"), abc);
        abc.gridx = 1;
        abc.gridy = 1;
        abstrat = new JTextField(15);
        panel.add(abstrat, abc);

        abc.gridx = 0;
        abc.gridy = 2;
        panel.add(new JLabel("Pagina de Inicio"), abc);
        abc.gridx = 1;
        abc.gridy = 2;
        paginaInicio = new JTextField(5);
        panel.add(paginaInicio, abc);

        abc.gridx = 0;
        abc.gridy = 3;
        panel.add(new JLabel("Pagina Fin"), abc);
        abc.gridx = 1;
        abc.gridy = 3;
        paginaFin = new JTextField(5);
        panel.add(paginaFin, abc);

        abc.gridx = 0;
        abc.gridy = 4;
        panel.add(new JLabel("Autor"), abc);
        abc.gridx = 1;
        abc.gridy = 4;
        autor = new JComboBox();
        listarAutor();
        panel.add(autor, abc);

        abc.gridx = 1;
        abc.gridy = 5;
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

    public void listarAutor() {
        gdA = new GD_Programa_A();
        try {
            List<Autor> autores = gdA.listarAutor("src/Archivos/Programa_A/Autor.txt");
            String[] listAutor = new String[autores.size()];
            for (int i = 0; i < autores.size(); i++) {
                Autor get = autores.get(i);
                listAutor[i] = get.getNombre();
            }
            autor.setModel(new DefaultComboBoxModel(listAutor));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardar() {
        try {
            gdA = new GD_Programa_A("src/Archivos/Programa_A/Articulo.txt");
            if (titulo.getText().equals("") || abstrat.getText().equals("") || paginaInicio.getText().equals("") || paginaFin.getText().equals("")) {
                throw new Exception("Deve llenar todos los campos.");
            }
            if (autor.getSelectedItem() == null) {
                throw new Exception("La lista de autores esta vacia");
            }
            gdA.agregarArticulo(titulo.getText(), abstrat.getText(), paginaInicio.getText(), paginaFin.getText(), (String) autor.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Datos Guardados", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
