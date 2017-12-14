package Vista;

import Controlador.GD_Programa_E;
import Modelo.Programa_E.Equipo;
import Modelo.Programa_E.Jugador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ClauMldo
 */
public class VtnListar_E_Jugadores extends JInternalFrame implements ActionListener {

    private GD_Programa_E gdE;
    private JComboBox equipos;
    private JPanel panel;
    private JTable tlbJugadores;

    public VtnListar_E_Jugadores() {
        initComponets();
    }

    private void initComponets() {
        setTitle("Listar Jugadores");
        setClosable(true);
        setMaximizable(true);
        setSize(650, 500);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(new FlowLayout());
        equipos = new JComboBox();
        initComboBox();
        panelBusqueda.add(equipos);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this);
        btnBuscar.setActionCommand("btnBuscar");
        panelBusqueda.add(btnBuscar);

        getContentPane().add(panelBusqueda, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        tlbJugadores = new JTable();
        tlbJugadores.setModel(new VtnListar_E_JugadoresModel());
        JScrollPane scroll = new JScrollPane(tlbJugadores);
        getContentPane().add(scroll, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("btnBuscar")) {
            btnBuscar();
        }
    }

    public void initComboBox() {
        try {
            gdE = new GD_Programa_E();
            List<Equipo> equipo = gdE.listarEquipo("src/Archivos/Programa_E/Equipos.txt");
            String[] datos = new String[equipo.size()];
            for (int i = 0; i < equipo.size(); i++) {
                Equipo get = equipo.get(i);
                datos[i] = get.getNombre();
            }
            equipos.setModel(new DefaultComboBoxModel(datos));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBuscar() {
        try {
            gdE.listarEquipo("src/Archivos/Programa_E/Equipos.txt");
            List<Equipo> equipos = gdE.agregaJugador("src/Archivos/Programa_E/Jugadores.txt");
            for (int i = 0; i < equipos.size(); i++) {
                Equipo get = equipos.get(i);
                if (this.equipos.getSelectedItem().equals(get.getNombre())) {
                    tlbJugadores.setModel(new VtnListar_E_JugadoresModel(get.getJugadores()));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lista Vacia", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}
