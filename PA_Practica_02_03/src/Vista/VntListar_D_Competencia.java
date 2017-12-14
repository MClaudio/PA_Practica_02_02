/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.GD_Programa_D;
import Modelo.Programa_D.Competencia;
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
public class VntListar_D_Competencia extends JInternalFrame {
     private JTable tlbCompetencias;
    private JPanel panel;
    private GD_Programa_D gdD;

    public VntListar_D_Competencia() {
        initComponets();
    }

    public void initComponets() {
        setTitle("Listar Competencias");
        setClosable(true);
        setMaximizable(true);
        setSize(600, 300);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        try {
            gdD = new GD_Programa_D("src/Archivos/Programa_D/Competencia.txt");

            String[] cabezera = {"#", "Tipo Competencia", "Fecha Competencia", "Lugar Competencia","Atletas"};

            List<Competencia> comp = gdD.leerDatosCompetencia();
            String[][] datos = new String[comp.size()][cabezera.length];
            for (int i = 0; i < comp.size(); i++) {
                Competencia get = comp.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getClaseCompetencia();
                datos[j][2] = get.getFechaCompetencia();
                datos[j][3] = get.getLugarCompetencia();
                datos[j][4] = get.getAtleta();
                
      
            }
            tlbCompetencias = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbCompetencias);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
