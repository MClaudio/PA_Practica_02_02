/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_C;
import Modelo.Programa_C.Empleado;
import Modelo.Programa_C.Empresa;
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
public class VntListar_C_Empresa extends JInternalFrame {
    
     private JTable tlbEmpresas;
    private JPanel panel;
    private GD_Programa_C gdC;

    public VntListar_C_Empresa() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Empresas");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdC = new GD_Programa_C("src/Archivos/Programa_C/Empresa.txt");

            String[] cabezera = {"#", "Nombre Empresa", "RUC", "Numero Socios", "Departamento"};

            List<Empresa> emp = gdC.leerDatosEmpresas();
            String[][] datos = new String[emp.size()][cabezera.length];
            for (int i = 0; i < emp.size(); i++) {
                Empresa get = emp.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getNombreEmpresa();
                datos[j][2] = get.getNumeroRUC();
                datos[j][3] = get.getNumeroSocios();
                datos[j][4] = get.getNombreDepartamento();
            }
            tlbEmpresas = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbEmpresas);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
