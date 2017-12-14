package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Programa_F.Consulta;
import Modelo.Programa_F.Doctor;
import Modelo.Programa_F.Paciente;
import java.io.FileNotFoundException;

public class GD_Programa_F {

    private List<Doctor> doctores;
    private List<Paciente> pacientes;
    private List<Consulta> consultas;
    private File archivo;

    public GD_Programa_F() {
        doctores = new ArrayList<Doctor>();
        pacientes = new ArrayList<Paciente>();
        consultas = new ArrayList<Consulta>();
    }

    public GD_Programa_F(String pathname) {
        archivo = new File(pathname);
    }

    public void creaMedico(String especialidad, String cedula, String nombre, String apellido) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombre + "|" + apellido + "|" + cedula + "|" + especialidad + "|\n");
            escritura.close();

            //leeDatosMedico();
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Doctor> leeDatosMedico(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoNombre = true;
        boolean datoApellido = false;
        boolean datoCedula = false;
        boolean datoEspecialidad = false;

        if (archivo.exists()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Doctor doctor = new Doctor();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (datoNombre == true && !palabra.equals("")) {
                                doctor.setNombre(palabra);
                                palabra = "";
                                datoNombre = false;
                                datoApellido = true;
                            }
                            if (datoApellido == true && !palabra.equals("")) {
                                doctor.setApellido(palabra);
                                palabra = "";
                                datoApellido = false;
                                datoCedula = true;
                            }
                            if (datoCedula == true && !palabra.equals("")) {
                                doctor.setCedula(palabra);
                                palabra = "";
                                datoCedula = false;
                                datoEspecialidad = true;
                            }
                            if (datoEspecialidad == true && !palabra.equals("")) {
                                doctor.setEspecialidad(palabra);
                                palabra = "";
                                datoEspecialidad = false;
                                datoNombre = true;
                            }
                        }
                    }
                    doctores.add(doctor);
                }
            }
            file.close();
            return doctores;
        }
        else {
            throw new Exception("El archivo no existe");
        } 
    }

    public void creaPaciente(String nombre, String apellido, String cedula, int edad) throws IOException, Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombre + "|" + apellido + "|" + cedula + "|" + edad + "|\n");
            escritura.close();

            //leeDatosMedico();
        }
        else {
            throw new Exception("Error el archivo no existe.");
        }
    }

    public List<Paciente> leerDatosPacientes(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoNombre = true;
        boolean datoApellido = false;
        boolean datoCedula = false;
        boolean datoEspecialidad = false;

        if (archivo.exists()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Paciente paciente = new Paciente();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        }
                        else {
                            if (datoNombre == true && !palabra.equals("")) {
                                paciente.setNombre(palabra);
                                palabra = "";
                                datoNombre = false;
                                datoApellido = true;
                            }
                            if (datoApellido == true && !palabra.equals("")) {
                                paciente.setApellido(palabra);
                                palabra = "";
                                datoApellido = false;
                                datoCedula = true;
                            }
                            if (datoCedula == true && !palabra.equals("")) {
                                paciente.setCedula(palabra);
                                palabra = "";
                                datoCedula = false;
                                datoEspecialidad = true;
                            }
                            if (datoEspecialidad == true && !palabra.equals("")) {
                                paciente.setEdad(Integer.parseInt(palabra));
                                palabra = "";
                                datoEspecialidad = false;
                                datoNombre = true;
                            }
                        }
                    }
                    pacientes.add(paciente);
                }
            }
            file.close();
            return pacientes;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void creaConsulta(String fecha, String hora, String doctor, String paciente) throws Exception {
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(fecha + "|" + hora + "|" + doctor + "|" + paciente + "|\n");
            escritura.close();
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public List<Consulta> listaConsulta(String pathname) throws Exception {
        archivo=new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoFecha = true;
        boolean datoHora = false;
        boolean datoDoctor = false;
        boolean datoPaciente = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                Consulta consulta = new Consulta();
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char letra = linea.charAt(i);
                        if (letra != '|') {
                            palabra += letra;
                        }
                        else {
                            if (datoFecha == true && !palabra.equals("")) {
                                consulta.setFecha(palabra);
                                datoFecha = false;
                                datoHora = true;
                                palabra = "";
                            }
                            if (datoHora == true && !palabra.equals("")) {
                                consulta.setHora(palabra);
                                datoHora = false;
                                datoDoctor = true;
                                palabra = "";
                            }
                            if (datoDoctor == true && !palabra.equals("")) {
                                List<Doctor> medicos=leeDatosMedico("src/Archivos/Programa_F/Medicos.txt");
                                String[]nombres=new String[2];
                                nombres=palabra.split(" ");
                                
                                for (int j = 0; j < medicos.size(); j++) {
                                    Doctor get = medicos.get(j);
                                    
                                    if (get.getNombre().equals(nombres[0]) && get.getApellido().equals(nombres[1])) {
                                        consulta.setDoctor(get);
                                    }
                                }
                                datoDoctor=false;
                                datoPaciente=true;
                                palabra="";     
                            }
                            if (datoPaciente==true && !palabra.equals("")) {
                                List<Paciente> pacientes=leerDatosPacientes("src/Archivos/Programa_F/Pacientes.txt");
                                String[] nombres=new String[2];
                                nombres=palabra.split(" ");
                                
                                for (int j = 0; j < pacientes.size(); j++) {
                                    Paciente get = pacientes.get(j);
                                    
                                    if (get.getNombre().equals(nombres[0]) && get.getApellido().equals(nombres[1])) {
                                        consulta.setPaciente(get);
                                    }
                                }
                                datoPaciente=false;
                                datoFecha=true;
                                palabra="";
                            }
                        }
                    }
                    consultas.add(consulta);
                }
            }
            file.close();
            return consultas;
        }
        else {
            throw new Exception("El archivo no existe");
        }
    }

    public void verificarDuplicados(String cedula) throws Exception {
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
                            if (palabra.equals(cedula)) {
                                throw new Exception("Ya existe un dato registrado con la misma cedula.");
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

}
