/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Programa_D.Atleta;
import Modelo.Programa_D.Competencia;
import Modelo.Programa_D.Resultado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class GD_Programa_D {

    private List<Resultado> resultados;
    private List<Atleta> atletas;
    private List<Competencia> competencias;
    private File archivo;

    public GD_Programa_D(String path) {
        resultados = new ArrayList<Resultado>();
        atletas = new ArrayList<Atleta>();
        competencias = new ArrayList<Competencia>();
        archivo = new File(path);
    }

    public void crearResultado(String tiempoFinal, String velocidad, String distanciaRecorrida) throws Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(tiempoFinal + "|" + velocidad + "|" + distanciaRecorrida + "|\n");
            escritura.close();

            leerDatosResultado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }

    }

    public List<Resultado> leerDatosResultado() throws Exception {
        String linea = "";
        String palabra = "";
        boolean datotiempoFinal = true;
        boolean datovelocidad = false;
        boolean datodistanciaRecorrida = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Resultado resultado = new Resultado();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datotiempoFinal == true && !palabra.equals("")) {
                                resultado.setTiempoFinal(palabra);
                                palabra = "";
                                System.out.println("Nombre: " + palabra);
                                datotiempoFinal = false;
                                datovelocidad = true;
                            }
                            if (datovelocidad == true && !palabra.equals("")) {
                                resultado.setVelocidad(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datovelocidad = false;
                                datodistanciaRecorrida = true;
                            }
                            if (datodistanciaRecorrida == true && !palabra.equals("")) {
                                resultado.setDistanciaRecorrida(palabra);
                                palabra = "";
                                datodistanciaRecorrida = false;
                                datotiempoFinal = true;
                            }

                            //System.out.print("[" + palabra + "]");
                        }
                    }
                    resultados.add(resultado);
                }
            }
            file.close();
            return resultados;
        } else {
            throw new Exception("El archivo no existe");
        }

    }
    
      public Resultado buscarResutado(List<Resultado> result,String auxresultados) {
            for (int i = 0; i < result.size(); i++) {
            if (auxresultados.equals(result.get(i).getTiempoFinal())) {
                return result.get(i);

            }
        }

        return null;
          
    }
      
     public String[] listResultados(List<Resultado> result) {
         

        String[] resultads = new String[result.size() + 1];
        resultads[0] = "Selecionar";
        for (int i = 0; i < result.size(); i++) {
            resultads[i + 1] = result.get(i).getTiempoFinal();
            //System.out.println(resultados.get(i).getNombreApellido());
        }
        return resultads;

         
    }  

    public void crearAtleta(String nombreApellido, String cedula, String fechaNac, String direccion, String altura, String peso, String resultado) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreApellido + "|" + cedula + "|" + fechaNac + "|" + direccion + "|" + altura + "|" + peso + "|" + resultado + "|\n");
            escritura.close();

            leerDatosResultado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }

    }

    public List<Atleta> leerDatosAtleta() throws Exception {
        String linea = "";
        String palabra = "";
        boolean datonombreApellido = true;
        boolean datocedula = false;
        boolean datofechaNac = false;
        boolean datodireccion = false;
        boolean datoaltura = false;
        boolean datopeso = false;
        boolean datoresultado = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Atleta atleta = new Atleta();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datonombreApellido == true && !palabra.equals("")) {
                                atleta.setNombreApellido(palabra);
                                palabra = "";
                                System.out.println("Nombre: " + palabra);
                                datonombreApellido = false;
                                datocedula = true;
                            }
                            if (datocedula == true && !palabra.equals("")) {
                                atleta.setCedula(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datocedula = false;
                                datofechaNac = true;
                            }
                            if (datofechaNac == true && !palabra.equals("")) {
                                atleta.setFechaNac(palabra);
                                palabra = "";
                                datofechaNac = false;
                                datodireccion = true;
                            }

                            if (datodireccion == true && !palabra.equals("")) {
                                atleta.setDireccion(palabra);
                                palabra = "";
                                datodireccion = false;
                                datoaltura = true;
                            }
                            if (datoaltura == true && !palabra.equals("")) {
                                atleta.setAltura(palabra);
                                palabra = "";
                                datoaltura = false;
                                datopeso = true;
                            }
                            if (datopeso == true && !palabra.equals("")) {
                                atleta.setPeso(palabra);
                                palabra = "";
                                datopeso = false;
                                datoresultado = true;
                            }
                            if (datoresultado == true && !palabra.equals("")) {
                                atleta.setResultado(palabra);
                                palabra = "";
                                datoresultado = false;
                                datonombreApellido = true;
                            }

                            //System.out.print("[" + palabra + "]");
                        }
                    }
                    atletas.add(atleta);
                }
            }
            file.close();
            return atletas;
        } else {
            throw new Exception("El archivo no existe");
        }

    }
    
      public String[] listAtletas(List<Atleta> atlet) {
          
        String[] atlets = new String[atlet.size() + 1];
        atlets[0] = "Selecionar";
        for (int i = 0; i < atlet.size(); i++) {
            atlets[i + 1] = atlet.get(i).getNombreApellido();
        }
        return atlets;
          
    }
      
     public Atleta buscarAtleta(List<Atleta> atlet,String auxatletas) {
          for (int i = 0; i < atlet.size(); i++) {
            if (auxatletas.equals(atlet.get(i).getNombreApellido())) {
                return atlet.get(i);

            }
        }

        return null;
         
    }  

    public void crearCompetencia(String claseCompetencia, String fechaCompetencia, String lugarCompetencia, String atleta) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(claseCompetencia + "|" + fechaCompetencia + "|" + lugarCompetencia + "|" + atleta + "|\n");
            escritura.close();

            leerDatosResultado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }

    }

    public List<Competencia> leerDatosCompetencia() throws Exception {
        String linea = "";
        String palabra = "";
        boolean datoclaseCompetencia = true;
        boolean datofechaCompetencia = false;
        boolean datolugarCompetencia = false;
        boolean datoatleta = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Competencia competencia = new Competencia();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datoclaseCompetencia == true && !palabra.equals("")) {
                                competencia.setClaseCompetencia(palabra);
                                palabra = "";
                                System.out.println("Nombre: " + palabra);
                                datoclaseCompetencia = false;
                                datofechaCompetencia = true;
                            }
                            if (datofechaCompetencia == true && !palabra.equals("")) {
                                competencia.setFechaCompetencia(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datofechaCompetencia = false;
                                datolugarCompetencia = true;
                            }
                            if (datolugarCompetencia == true && !palabra.equals("")) {
                                competencia.setLugarCompetencia(palabra);
                                palabra = "";
                                datolugarCompetencia = false;
                                datoatleta = true;
                            }
                            if (datoatleta == true && !palabra.equals("")) {
                                competencia.setAtleta(palabra);
                                palabra = "";
                                datoatleta = false;
                                datoclaseCompetencia = true;
                            }

                            //System.out.print("[" + palabra + "]");
                        }
                    }
                    competencias.add(competencia);
                }
            }
            file.close();
            return competencias;
        } else {
            throw new Exception("El archivo no existe");
        }

    }
    
     public boolean validarCedula(String cedula) throws Exception {
		// TODO Auto-generated method stub
		try {
			int a = Integer.parseInt(cedula);
		}catch(NumberFormatException e){
			throw new Exception("Formato incorrecto, contiene caracteres");
		}
		if(cedula.length()!=10)
			throw new Exception("Debe ser de 10 dígitos");
		
		
	
		return true;
    }
    
    public boolean verificarCedula(List<Atleta> listAtletas ,String cedula) throws Exception {
		int n=1;
		if (listAtletas.size()>0) {
			for (int i = 0; i < listAtletas.size(); i++) {
				if (cedula.equals(listAtletas.get(i).getCedula())) {
					n++;
				}
			}
			if (n>1) {
			throw new Exception("Esta cedula ya fue registrada");
			}			
		}
                return true;
    }

   


}
