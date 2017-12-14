/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Controlador.GD_Programa_F;
import Modelo.Programa_C.Departamento;
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
public class VntListar_C_Departamento extends JInternalFrame {
    
     private JTable tlbDepartamentos;
    private JPanel panel;
    private GD_Programa_C gdC;

    public VntListar_C_Departamento() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Departamentos");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdC = new GD_Programa_C("src/Archivos/Programa_C/Departamento.txt");

            String[] cabezera = {"#", "Nombre Departamento", "Nombre Supervidor", "Numero Departamento", "Empleado"};

            List<Departamento> doc = gdC.leerDatosDepartamento();
            String[][] datos = new String[doc.size()][cabezera.length];
            for (int i = 0; i < doc.size(); i++) {
                Departamento get = doc.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getNombreDep();
                datos[j][2] = get.getNombreSupervisor();
                datos[j][3] = get.getNumeroDep();
                datos[j][4] = get.getNomEmpleado();
            }
            tlbDepartamentos = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbDepartamentos);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
