package Controlador;

import java.util.ArrayList;
import java.util.List;
import Modelo.Programa_E.Equipo;
import Modelo.Programa_E.Inscripcion;
import Modelo.Programa_E.Jugador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {

                escritura.writeUTF(nombre);
                escritura.writeUTF(categoria);

            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();
            }
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Equipo> listarEquipo(String pathname) throws Exception {
        archivo = new File(pathname);

        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {

                while (true) {
                    Equipo equipo = new Equipo();
                    equipo.setNombre(lectura.readUTF());
                    equipo.setCategoria(lectura.readUTF());
                    equipos.add(equipo);
                }

            } catch (Exception e) {
                return equipos;
            } finally {
                lectura.close();
            }
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void creaJugador(String nombre, String apellido, String cedula, int edad, String nombreDeportivo, int numCamiseta, String equipo) throws Exception {
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {
                escritura.writeUTF(nombre);
                escritura.writeUTF(apellido);
                escritura.writeUTF(cedula);
                escritura.writeInt(edad);
                escritura.writeUTF(nombreDeportivo);
                escritura.writeInt(numCamiseta);
                escritura.writeUTF(equipo);

            } catch (Exception e) {
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();
            }
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Equipo> agregaJugador(String pathname) throws Exception {
        archivo = new File(pathname);
        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                while (true) {
                    Jugador jugador = new Jugador();

                    jugador.setNombre(lectura.readUTF());
                    jugador.setApellido(lectura.readUTF());
                    jugador.setCedula(lectura.readUTF());
                    jugador.setEdad(lectura.readInt());
                    jugador.setNombreDeportivo(lectura.readUTF());
                    jugador.setNumCamiseta(lectura.readInt());
                    String nombre = lectura.readUTF();
                    System.out.println("nombre " + nombre);
                    for (int j = 0; j < equipos.size(); j++) {
                        Equipo get = equipos.get(j);
                        if (get.getNombre().equals(nombre)) {
                            get.addJugador(jugador);
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Fin...");
                return equipos;
            } finally {
                lectura.close();
            }
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void creaInscripcion(String fecha, int numero, String torneo, String nombreEquipo) throws Exception {
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {
                escritura.writeUTF(fecha);
                escritura.writeInt(numero);
                escritura.writeUTF(torneo);
                escritura.writeUTF(nombreEquipo);

            } catch (Exception e) {
                escritura.close();
            }
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Inscripcion> listarInscripciones(String pathname) throws Exception {
        
        List<Equipo> aux;
        archivo = new File(pathname);

        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                while (true) {
                    Inscripcion inscripcion = new Inscripcion();
                    inscripcion.setFecha(lectura.readUTF());
                    inscripcion.setNumero(lectura.readInt());
                    inscripcion.setTorneo(lectura.readUTF());
                    String nom=lectura.readUTF();
                    listarEquipo("src/Archivos/Programa_E/Equipos.dat");
                    aux = agregaJugador("src/Archivos/Programa_E/Jugadores.dat");
                    for (int j = 0; j < aux.size(); j++) {
                        Equipo get = aux.get(j);
                        if (get.getNombre().equals(nom)) {
                            inscripcion.setEquipo(get);
                        }
                    }
                    inscripciones.add(inscripcion);
                }
            } catch (Exception e) {
                return inscripciones;
            } finally {
                lectura.close();
            }
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public boolean validarDupEquipo(String nombre) throws Exception {
        FileInputStream file = new FileInputStream(archivo);
        DataInputStream lectura = new DataInputStream(file);
        try {
            while (true) {
                if (lectura.readUTF().equals(nombre)) {
                    System.out.println("Iguales");
                    return true;
                }
                lectura.readUTF();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validaDupJugador(String cedula) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(archivo);
        DataInputStream lectura = new DataInputStream(file);
        try {
            while (true) {
                lectura.readUTF();
                lectura.readUTF();
                if (lectura.readUTF().equals(cedula)) {
                    System.out.println("Iguales");
                    return true;
                }
                lectura.readInt();
                lectura.readUTF();
                lectura.readInt();
                lectura.readUTF();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validaDupInscripcion(String torneo) throws FileNotFoundException {
        FileInputStream file = new FileInputStream(archivo);
        DataInputStream lectura = new DataInputStream(file);
        try {
            while (true) {
                lectura.readUTF();
                lectura.readInt();
                if (lectura.readUTF().equals(torneo)) {
                    System.out.println("Iguales");
                    return true;
                }
                lectura.readUTF();
            }
        } catch (Exception e) {
            return false;
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
