/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Controlador.GD_Programa_F;
import Modelo.Programa_C.Empleado;
import Modelo.Programa_F.Doctor;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Cristian
 */
public class VntListar_C_Empleado extends JInternalFrame{
    
    private JTable tlbEmpleados;
    private JPanel panel;
    private GD_Programa_C gdC;

    public VntListar_C_Empleado() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Doctores");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdC = new GD_Programa_C("src/Archivos/Programa_C/Empleado.txt");

            String[] cabezera = {"#", "Nombre Apellido", "Cedula", "Fecha Nacimiento", "Direccion"};

            List<Empleado> empl = gdC.leerDatosEmpleado();
            String[][] datos = new String[empl.size()][cabezera.length];
            for (int i = 0; i < empl.size(); i++) {
                Empleado get = empl.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getNombreApellido();
                datos[j][2] = get.getCedula();
                datos[j][3] = get.getFechaNac();
                datos[j][4] = get.getDireccion();
            }
            tlbEmpleados = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbEmpleados);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
