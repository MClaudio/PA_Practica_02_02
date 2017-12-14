package Controlador;

import java.util.ArrayList;
import java.util.List;
import Modelo.Programa_E.Equipo;
import Modelo.Programa_E.Inscripcion;
import Modelo.Programa_E.Jugador;
import Modelo.Programa_F.Consulta;
import Modelo.Programa_F.Doctor;
import Modelo.Programa_F.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author ClauMldo
 */
public class GD_Programa_E {

    private List<Inscripcion> inscripciones;
    private List<Equipo> equipos;

    private File archivo;

    public GD_Programa_E() {
        inscripciones = new ArrayList<Inscripcion>();
        equipos = new ArrayList<Equipo>();

    }

    public GD_Programa_E(String pathname) {
        archivo = new File(pathname);
    }

    public void crearEquipo(String nombre, String categoria) throws Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombre + "|" + categoria + "|\n");
            escritura.close();
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Equipo> listarEquipo(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoNombre = true;
        boolean datoCategiria = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                Equipo equipo = new Equipo();
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char letra = linea.charAt(i);
                        if (letra != '|') {
                            palabra += letra;
                        }
                        else {
                            if (datoNombre == true && !palabra.equals("")) {
                                equipo.setNombre(palabra);
                                datoNombre = false;
                                datoCategiria = true;
                                palabra = "";
                            }
                            if (datoCategiria == true && !palabra.equals("")) {
                                equipo.setCategoria(palabra);
                                datoCategiria = false;
                                datoNombre = true;
                                palabra = "";
                            }
                        }
                    }
                    equipos.add(equipo);
                }
            }
            file.close();
            return equipos;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void creaJugador(String nombre, String apellido, String cedula, int edad, String nombreDeportivo, int numCamiseta, String equipo) throws Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombre + "|" + apellido + "|" + cedula + "|" + edad + "|" + nombreDeportivo + "|" + numCamiseta + "|" + equipo + "|\n");
            escritura.close();
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Equipo> agregaJugador(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoNombre = true;
        boolean datoApellido = false;
        boolean datoCedula = false;
        boolean datoEdad = false;
        boolean datoNomDeportivo = false;
        boolean datoNumCamiseta = false;
        boolean datoEquipo = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                Jugador jugador = new Jugador();
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char letra = linea.charAt(i);
                        if (letra != '|') {
                            palabra += letra;
                        }
                        else {
                            if (datoNombre == true && !palabra.equals("")) {
                                jugador.setNombre(palabra);
                                datoNombre = false;
                                datoApellido = true;
                                palabra = "";
                            }
                            if (datoApellido == true && !palabra.equals("")) {
                                jugador.setApellido(palabra);
                                datoApellido = false;
                                datoCedula = true;
                                palabra = "";
                            }
                            if (datoCedula == true && !palabra.equals("")) {
                                jugador.setCedula(palabra);
                                datoCedula = false;
                                datoEdad = true;
                                palabra = "";
                            }
                            if (datoEdad == true && !palabra.equals("")) {
                                jugador.setEdad(Integer.parseInt(palabra));
                                datoEdad = false;
                                datoNomDeportivo = true;
                                palabra = "";
                            }
                            if (datoNomDeportivo == true && !palabra.equals("")) {
                                jugador.setNombreDeportivo(palabra);
                                datoNomDeportivo = false;
                                datoNumCamiseta = true;
                                palabra = "";
                            }
                            if (datoNumCamiseta == true && !palabra.equals("")) {
                                jugador.setNumCamiseta(Integer.parseInt(palabra));
                                datoNumCamiseta = false;
                                datoEquipo = true;
                                palabra = "";
                            }
                            if (datoEquipo == true && !palabra.equals("")) {
                                for (int j = 0; j < equipos.size(); j++) {
                                    Equipo get = equipos.get(j);
                                    if (get.getNombre().equals(palabra)) {
                                        get.addJugador(jugador);
                                    }
                                }
                                datoEquipo = false;
                                datoNombre = true;
                                palabra = "";
                            }
                        }
                    }
                }
            }
            file.close();
            return equipos;
        }

        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void creaInscripcion(String fecha, int numero, String torneo, String nombreEquipo) throws Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(fecha + "|" + numero + "|" + torneo + "|" + nombreEquipo + "|\n");
            escritura.close();
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Inscripcion> listarInscripciones(String pathname) throws Exception {
        List<Equipo> aux;

        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoFecha = true;
        boolean datoNumero = false;
        boolean datoTorneo = false;
        boolean datoNombreEquipo = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                Inscripcion inscripcion = new Inscripcion();

                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char letra = linea.charAt(i);
                        if (letra != '|') {
                            palabra += letra;
                        }
                        else {
                            if (datoFecha == true && !palabra.equals("")) {
                                inscripcion.setFecha(palabra);
                                datoFecha = false;
                                datoNumero = true;
                                palabra = "";
                            }
                            if (datoNumero == true && !palabra.equals("")) {
                                inscripcion.setNumero(Integer.parseInt(palabra));
                                datoNumero = false;
                                datoTorneo = true;
                                palabra = "";
                            }
                            if (datoTorneo == true && !palabra.equals("")) {
                                inscripcion.setTorneo(palabra);
                                datoTorneo = false;
                                datoNombreEquipo = true;
                                palabra = "";
                            }
                            if (datoNombreEquipo == true && !palabra.equals("")) {
                                listarEquipo("src/Archivos/Programa_E/Equipos.txt");
                                aux = agregaJugador("src/Archivos/Programa_E/Jugadores.txt");

                                for (int j = 0; j < aux.size(); j++) {
                                    Equipo get = aux.get(j);
                                    if (get.getNombre().equals(palabra)) {
                                        inscripcion.setEquipo(get);
                                    }
                                }
                                datoNombreEquipo = false;
                                datoFecha = true;
                                palabra = "";
                            }
                        }
                    }
                    inscripciones.add(inscripcion);
                }
            }
            file.close();
            return inscripciones;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }
    
    public void verificarDuplicados(String cadena) throws Exception {
        String linea = "";
        String palabra = "";
        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (palabra.equals(cadena)) {
                                throw new Exception("Ya existe un dato registrado con los mismos datos.");
                            }
                            palabra = "";
                        }
                    }
                }
            }
            file.close();
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public boolean validNumeros(String cadena) throws Exception {
        try {
            int num = Integer.parseInt(cadena);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void validaCedula(String cedula) throws Exception {
        if (cedula.length() != 10) {
            throw new Exception("Cedula incorrecta debe contener 10 numeros");
        }
    }

    public void imprimeAux(List<Equipo> aux) {
        for (int i = 0; i < aux.size(); i++) {
            Equipo get = aux.get(i);
            System.out.println("Dato " + get.getNombre());
        }
    }
}
