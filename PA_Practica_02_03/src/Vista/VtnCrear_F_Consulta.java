package Vista;

import Controlador.GD_Programa_F;
import Modelo.Programa_F.Doctor;
import Modelo.Programa_F.Paciente;
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

/**
 *
 * @author ClauMldo
 */
public class VtnCrear_F_Consulta extends JInternalFrame implements ActionListener {

    private GD_Programa_F gdF;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JComboBox doctores;
    private JComboBox pacientes;

    public VtnCrear_F_Consulta() {
        initComponets();
    }

    private void initComponets() {
        setTitle("Crear Consulta");
        setClosable(true);
        setMaximizable(true);
        setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.add(new JLabel("Fecha:"));
        txtFecha = new JTextField(10);
        panel.add(txtFecha);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Hora:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtHora = new JTextField(5);
        panel.add(txtHora, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Doctor:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        doctores = new JComboBox();
        listarDoctores();
        panel.add(doctores, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Pciente:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        pacientes = new JComboBox();
        listarPacientes();
        panel.add(pacientes, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
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

    private void listarDoctores() {
        gdF = new GD_Programa_F();
        try {
            List<Doctor> doctor = gdF.leeDatosMedico("src/Archivos/Programa_F/Medicos.txt");
            String[] listDoctores = new String[doctor.size()];
            for (int i = 0; i < doctor.size(); i++) {
                Doctor get = doctor.get(i);
                listDoctores[i] = get.getNombre() + " " + get.getApellido();
            }

            doctores.setModel(new DefaultComboBoxModel(listDoctores));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPacientes() {
        gdF = new GD_Programa_F();
        try {
            List<Paciente> paciente = gdF.leerDatosPacientes("src/Archivos/Programa_F/Pacientes.txt");
            String[] listPaciente = new String[paciente.size()];
            for (int i = 0; i < paciente.size(); i++) {
                Paciente get = paciente.get(i);
                listPaciente[i] = get.getNombre() + " " + get.getApellido();
            }
            pacientes.setModel(new DefaultComboBoxModel(listPaciente));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnGuardar() {
        try {
            gdF = new GD_Programa_F("src/Archivos/Programa_F/Consultas.txt");

            if (txtFecha.getText().equals("") || txtHora.getText().equals("")) {
                throw new Exception("Deve llenar todos los campos.");
            }
            if (doctores.getSelectedItem() == null || pacientes.getSelectedItem() == null) {
                throw new Exception("La lista de doctores o pacientes esta vacia.");
            }
            /*if (gdF.verificarConsulta(txtFecha.getText(), (String) pacientes.getSelectedItem())) {
            throw new Exception("El paciente ya tiene una cita con el doctor selecionado en la fecha propuesta.");
            }*/

            gdF.creaConsulta(txtFecha.getText(), txtHora.getText(), (String) doctores.getSelectedItem(), (String) pacientes.getSelectedItem());

            txtFecha.setText("");
            txtHora.setText("");

            JOptionPane.showMessageDialog(this, "Datos Guardados con exito...", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
