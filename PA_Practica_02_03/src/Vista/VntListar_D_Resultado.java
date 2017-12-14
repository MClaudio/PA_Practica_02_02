/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;


import Controlador.GD_Programa_D;
import Modelo.Programa_C.Empleado;
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
public class VntListar_D_Resultado extends JInternalFrame {
    
      private JTable tlbResultados;
    private JPanel panel;
    private GD_Programa_D gdD;

    public VntListar_D_Resultado() {
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
            gdD = new GD_Programa_D("src/Archivos/Programa_D/Resultado.txt");

            String[] cabezera = {"#", "Tiempo Final (seg)", "Velocidad (m/seg)", "Distancia Recorrida (m)"};

            List<Resultado> resul = gdD.leerDatosResultado();
            String[][] datos = new String[resul.size()][cabezera.length];
            for (int i = 0; i < resul.size(); i++) {
                Resultado get = resul.get(i);
                int j = i;
                datos[j][0] = "" + (j + 1);
                datos[j][1] = get.getTiempoFinal();
                datos[j][2] = get.getVelocidad();
                datos[j][3] = get.getDistanciaRecorrida();
      
            }
            tlbResultados = new JTable(datos, cabezera);
            JScrollPane scroll = new JScrollPane(tlbResultados);
            panel.add(scroll, BorderLayout.CENTER);
            getContentPane().add(panel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: Algunos datos no an sido ingresados.", e.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
