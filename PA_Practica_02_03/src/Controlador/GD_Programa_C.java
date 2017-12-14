/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Programa_C.Departamento;
import Modelo.Programa_C.Empleado;
import Modelo.Programa_C.Empresa;
import Modelo.Programa_F.Doctor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristian
 */
public class GD_Programa_C {

    private List<Empleado> empleados;
    private List<Departamento> departamentos;
    private List<Empresa> empresas;
    private File archivo;

    public GD_Programa_C(String path) {
        empleados = new ArrayList<Empleado>();
        departamentos = new ArrayList<Departamento>();
        empresas = new ArrayList<Empresa>();
        archivo = new File(path);
    }

    public void crearEmpleado(String nombreApellido, String cedula, String fehaNac, String direccion) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreApellido + "|" + cedula + "|" + fehaNac + "|" + direccion + "|\n");
            escritura.close();

            leerDatosEmpleado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Empleado> leerDatosEmpleado() throws Exception {
        String linea = "";
        String palabra = "";
        boolean datoNombreApellido = true;
        boolean datoCedula = false;
        boolean datofehaNac = false;
        boolean datoDireccion = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Empleado empleado = new Empleado();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datoNombreApellido == true && !palabra.equals("")) {
                                empleado.setNombreApellido(palabra);
                                palabra = "";
                                System.out.println("Nombre: " + palabra);
                                datoNombreApellido = false;
                                datoCedula = true;
                            }
                            if (datoCedula == true && !palabra.equals("")) {
                                empleado.setCedula(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datoCedula = false;
                                datofehaNac = true;
                            }
                            if (datofehaNac == true && !palabra.equals("")) {
                                empleado.setFechaNac(palabra);
                                palabra = "";
                                datofehaNac = false;
                                datoDireccion = true;
                            }
                            if (datoDireccion == true && !palabra.equals("")) {
                                empleado.setDireccion(palabra);
                                palabra = "";
                                datoDireccion = false;
                                datoNombreApellido = true;
                            }

                            //System.out.print("[" + palabra + "]");
                        }
                    }
                    empleados.add(empleado);
                }
            }
            file.close();
            return empleados;
        } else {
            throw new Exception("El archivo no existe");
        }

    }

    public void crearDepartamento(String nombreDep, String nombreSupervisor, String numeroDep, String nombEmpleado) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreDep + "|" + nombreSupervisor + "|" + numeroDep + "|" + nombEmpleado + "|\n");
            escritura.close();

            leerDatosEmpleado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Departamento> leerDatosDepartamento() throws Exception {
        String linea = "";
        String palabra = "";
        Empleado empleado = null;
        boolean datonombreDep = true;
        boolean datonombreSupervisor = false;
        boolean datonumeroDep = false;
        boolean datoEmpleado = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Departamento departamento = new Departamento();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datonombreDep == true && !palabra.equals("")) {
                                departamento.setNombreDep(palabra);
                                palabra = "";
                                System.out.println("Nombre: " + palabra);
                                datonombreDep = false;
                                datonombreSupervisor = true;
                            }
                            if (datonombreSupervisor == true && !palabra.equals("")) {
                                departamento.setNombreSupervisor(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datonombreSupervisor = false;
                                datonumeroDep = true;
                            }
                            if (datonumeroDep == true && !palabra.equals("")) {
                                departamento.setNumeroDep(palabra);
                                palabra = "";
                                datonumeroDep = false;
                                datoEmpleado = true;
                            }
                            if (datoEmpleado == true && !palabra.equals("")) {
                                departamento.setNomEmpleado(palabra);
                                empleado = null;
                                datoEmpleado = false;
                                datonombreDep = true;
                            }

                            //System.out.print("[" + palabra + "]");
                        }
                    }
                    departamentos.add(departamento);
                }
            }
            file.close();
            return departamentos;
        } else {
            throw new Exception("El archivo no existe");
        }

    }
    
      public void crearEmpresa(String nombreEmpresa, String numeroRUC, String numeroSocios, String nombreDepartamento) throws Exception {

        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(nombreEmpresa + "|" + numeroRUC + "|" + numeroSocios + "|" + nombreDepartamento + "|\n");
            escritura.close();

            leerDatosEmpleado();

        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Empresa> leerDatosEmpresas() throws Exception {
        String linea = "";
        String palabra = "";
       // Empleado empleado = null;
        boolean datonombreEmpresa = true;
        boolean datonumeroRUC = false;
        boolean datonumeroSocios = false;
        boolean datonombreDepartamento = false;

        if (archivo.exists() && archivo.isFile()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {

                linea = lectura.readLine();
                if (linea != null) {
                    Empresa empresa = new Empresa();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datonombreEmpresa == true && !palabra.equals("")) {
                                empresa.setNombreEmpresa(palabra);
                                palabra = "";
                                System.out.println("Nombre Empresa: " + palabra);
                                datonombreEmpresa = false;
                                datonumeroRUC = true;
                            }
                            if (datonumeroRUC == true && !palabra.equals("")) {
                                empresa.setNumeroRUC(palabra);
                                palabra = "";
                                System.out.println("Apelldo: " + palabra);
                                datonumeroRUC = false;
                                datonumeroSocios = true;
                            }
                            if (datonumeroSocios == true && !palabra.equals("")) {
                                empresa.setNumeroSocios(palabra);
                                palabra = "";
                                datonumeroSocios = false;
                                datonombreDepartamento = true;
                            }
                            if (datonombreDepartamento == true && !palabra.equals("")) {
                                empresa.setNombreDepartamento(palabra);
                                //empleado = null;
                                datonombreDepartamento = false;
                                datonombreEmpresa = true;
                            }

                        }
                    }
                    empresas.add(empresa);
                }
            }
            file.close();
            return empresas;
        } else {
            throw new Exception("El archivo no existe");
        }

    }


    public String[] listDepartamentos(List<Departamento> departament) {

        String[] departaments = new String[departament.size() + 1];
        departaments[0] = "Selecionar";
        for (int i = 0; i < departament.size(); i++) {
            departaments[i + 1] = departament.get(i).getNombreDep();
        }
        return departaments;
    }

    public String[] listEmpleados(List<Empleado> emplead) {
        String[] empleads = new String[emplead.size() +1];
        empleads[0] = "Selecionar";
        if (empleads.length>1) {
                  for (int i = 0; i < emplead.size(); i++) {
                      System.out.println("Empleado "+emplead.get(i).getNombreApellido());
            
                 empleads[i + 1] = emplead.get(i).getNombreApellido();

                  }
        }
  
        return empleads;

    }

    public Empleado buscarEmpleado(List<Empleado> emplead,String asp) {

        for (int i = 0; i < emplead.size()&&emplead.get(i)!=null; i++) {
            if (asp.equals(emplead.get(i).getNombreApellido())) {
             
                return emplead.get(i);

            }
        }

        return null;
    }

    public Departamento buscarDepartamento(List<Departamento> departament,String auxDepartamentos) {
         for (int i = 0; i < departament.size(); i++) {
            if (auxDepartamentos.equals(departament.get(i).getNombreDep())) {
                return departament.get(i);

            }
        }

        return null;
        
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
    
    public boolean verificarCedula(List<Empleado> listEmpleados ,String cedula) throws Exception {
		int n=1;
		if (listEmpleados.size()>0) {
			for (int i = 0; i < listEmpleados.size(); i++) {
				if (cedula.equals(listEmpleados.get(i).getCedula())) {
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
