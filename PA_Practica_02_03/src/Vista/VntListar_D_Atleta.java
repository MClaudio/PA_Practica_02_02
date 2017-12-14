/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_D;
import Modelo.Programa_D.Atleta;
import Modelo.Programa_D.Resultado;
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
public class VntListar_D_Atleta extends JInternalFrame{
    
    private JTable tlbAtletas;
    private JPanel panel;
    private GD_Programa_D gdD;

    public VntListar_D_Atleta() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Resultados");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdD = new GD_Programa_D("src/Archivos/Programa_D/Atleta.txt");

            String[] cabezera = {"#", "Nombre Apellido", "Cedula", "Fecha Nacimiento","Direccion","Altura (cm)","Peso (kg)","Resultado t (seg)"};

            List<Atleta> atl = gdD.leerDatosAtleta();
            String[][] datos = new String[atl.size()][cabezera.length];
            for (int i = 0; i < atl.size(); i++) {
                Atleta get = atl.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getNombreApellido();
                datos[j][2] = get.getCedula();
                datos[j][3] = get.getFechaNac();
                datos[j][4] = get.getDireccion();
                datos[j][5] = get.getAltura();
                datos[j][6] = get.getPeso();
                datos[j][7] = get.getResultado();
      
            }
            tlbAtletas = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbAtletas);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
