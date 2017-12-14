package Controlador;

import Modelo.Programa_B.Canton;
import Modelo.Programa_B.Pais;
import Modelo.Programa_B.Provincia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GD_Programa_B {

    private List<Pais> paises;
    private List<Provincia> provincias;
    private List<Canton> cantones;
    private File archivo;

    public GD_Programa_B() {
        paises = new ArrayList<Pais>();
        provincias = new ArrayList<Provincia>();
        cantones = new ArrayList<Canton>();
    }

    public GD_Programa_B(String pathname) {
        archivo = new File(pathname);
    }

    public void agregarPais(String nombreContinente, String nombrePais, String idioma) throws IOException {
        //System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreContinente + "|" + nombrePais + "|" + idioma + "|\n");
            escritura.close();
        }

    }

    public void agregarProvincia(String nombreProvincia, String region, String gobernador, String pais) throws IOException {
        //System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreProvincia + "|" + region + "|" + gobernador + "|" + pais + "|\n");
            escritura.close();
        }
    }

    public void agregarCanton(String nombreCanton, String codigoPostal, String numeroHabitantes, String provincia) throws IOException {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreCanton + "|" + codigoPostal + "|" + numeroHabitantes + "|" + provincia + "|\n");
            escritura.close();
        }
    }

    public List<Pais> listarPais(String pathname) throws FileNotFoundException, IOException, Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datonombreContinente = true;
        boolean datonombrePais = false;
        boolean datoidioma = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {
                linea = lectura.readLine();
                if (linea != null) {
                    Pais pais = new Pais();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (datonombreContinente == true && !palabra.equals("")) {
                                pais.setNombreContinente(palabra);
                                palabra = "";
                                datonombreContinente = false;
                                datonombrePais = true;
                            }
                            if (datonombrePais == true && !palabra.equals("")) {
                                pais.setNombrePais(palabra);
                                palabra = "";
                                datonombrePais = false;
                                datoidioma = true;
                            }
                            if (datoidioma == true && !palabra.equals("")) {
                                pais.setIdioma(palabra);
                                palabra = "";
                                datoidioma = false;
                                datonombreContinente = true;
                            }
                        }
                    }
                    paises.add(pais);
                }
            }
            file.close();
            return paises;
        }
        else {
            throw new Exception("El archivo no existe.");
        }
    }

    public List<Provincia> listarProvincia(String pathname) throws FileNotFoundException, IOException, Exception {
        archivo = new File(pathname);
        List<Pais> aux;
        String linea = "";
        String palabra = "";
        boolean datonombreProvincia = true;
        boolean datoregion = false;
        boolean datogobernador = false;
        boolean datopais = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {
                linea = lectura.readLine();

                if (linea != null) {
                    Provincia provincia = new Provincia();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (datonombreProvincia == true && !palabra.equals("")) {
                                provincia.setNombreProvincia(palabra);
                                palabra = "";
                                datonombreProvincia = false;
                                datoregion = true;
                            }
                            if (datoregion == true && !palabra.equals("")) {
                                provincia.setRegion(palabra);
                                palabra = "";
                                datoregion = false;
                                datogobernador = true;
                            }
                            if (datogobernador == true && !palabra.equals("")) {
                                provincia.setGobernador(palabra);
                                palabra = "";
                                datogobernador = false;
                                datopais = true;
                            }
                            if (datopais == true && !palabra.equals("")) {
                                System.out.println("Nombre Pais:" + palabra);         
                                aux = listarPais("src/Archivos/Programa_B/Pais.txt");
                                for (int j = 0; j < aux.size(); j++) {
                                    Pais get = aux.get(j);
                                    if (get.getNombrePais().equals(palabra)) {
                                        provincia.setPais(get);
                                    }
                                }
                                palabra = "";
                                datopais = false;
                                datonombreProvincia = true;
                            }
                        }
                    }
                    provincias.add(provincia);
                }
            }
            file.close();
            return provincias;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public List<Canton> listarCanton(String pathname) throws FileNotFoundException, Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datonombreCanton = true;
        boolean datocodigoPostal = false;
        boolean datonumeroHabitantes = false;
        boolean datoprovincias = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {
                Canton canton = new Canton();
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (datonombreCanton == true && !palabra.equals("")) {
                                canton.setNombreCanton(palabra);
                                palabra = "";
                                datonombreCanton = false;
                                datocodigoPostal = true;
                            }
                            if (datocodigoPostal == true && !palabra.equals("")) {
                                canton.setCodigoPostal(palabra);
                                palabra = "";
                                datocodigoPostal = false;
                                datonumeroHabitantes = true;
                            }
                            if (datonumeroHabitantes == true && !palabra.equals("")) {
                                canton.setNumeroHabitantes(palabra);
                                palabra = "";
                                datonumeroHabitantes = false;
                                datoprovincias = true;
                            }
                            if (datoprovincias == true && !palabra.equals("")) {
                                List<Provincia> provincia = listarProvincia("src/Archivos/Programa_B/Provincia.txt");
                                String[] dato = new String[3];
                                dato = palabra.split("");
                                for (int j = 0; j < provincia.size(); j++) {
                                    Provincia get = provincia.get(j);
                                    if (get.getNombreProvincia().equals(dato[0])) {
                                        canton.setProvincia(get);
                                    }
                                }
                                datoprovincias = false;
                                datonombreCanton = true;
                            }
                        }
                    }
                    cantones.add(canton);
                }
            }
            file.close();
            return cantones;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }
}
