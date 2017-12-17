/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Programa_C.Departamento;
import Modelo.Programa_C.Empleado;
import Modelo.Programa_C.Empresa;
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

        //  Empleado emp = new Empleado();
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);

            try {

                escritura.writeUTF(nombreApellido);
                escritura.writeUTF(cedula);
                escritura.writeUTF(fehaNac);
                escritura.writeUTF(direccion);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();

            }

        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Empleado> getListEmpleado(String patch) throws Exception {
        archivo = new File(patch);

        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {

                while (true) {
                    Empleado empleado = new Empleado();
                     empleado.setNombreApellido(lectura.readUTF());
                    empleado.setCedula(lectura.readUTF());
                   empleado.setFechaNac(lectura.readUTF());
                    empleado.setDireccion(lectura.readUTF());
                    
                    empleados.add(empleado);
                }

            } catch (Exception e) {
                return empleados;
            } finally {
                lectura.close();
            }
        } else {
            throw new Exception("El archivo no existe");
        }

    }

    public void crearDepartamento(String nombreDep, String nombreSupervisor, String numeroDep, String nombEmpleado) throws Exception {

        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo);
            DataOutputStream escritura = new DataOutputStream(file);
            try {

                escritura.writeUTF(nombreDep);
                escritura.writeUTF(nombreSupervisor);
                escritura.writeUTF(numeroDep);
                escritura.writeUTF(nombEmpleado);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();

            }
        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Departamento> getListDepartamento(String patch) throws Exception {

        archivo = new File(patch);

        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {

                while (true) {
                    Departamento departamento = new Departamento();
                    departamento.setNombreDep(lectura.readUTF());
                    departamento.setNombreSupervisor(lectura.readUTF());
                    departamento.setNumeroDep(lectura.readUTF());
                    departamento.setNomEmpleado(lectura.readUTF());
                   
                    departamentos.add(departamento);
                }

            } catch (Exception e) {
                return departamentos;
            } finally {
                lectura.close();
            }
        } else {
            throw new Exception("El archivo no existe");
        }

    }

    public void crearEmpresa(String nombreEmpresa, String numeroRUC, String numeroSocios, String nombreDepartamento) throws Exception {

        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo);
            DataOutputStream escritura = new DataOutputStream(file);
            
            try{
                escritura.writeUTF(nombreEmpresa);
                escritura.writeUTF(numeroRUC);
                escritura.writeUTF(numeroSocios);
                escritura.writeUTF(nombreDepartamento);
            
            
            }catch (FileNotFoundException e){
                e.printStackTrace();
                throw new Exception("Error al escribir el archivo.");
            }finally{
                escritura.close();
            }
        } else {
            throw new Exception("Error el archivo no existe.");

        }
    }

    public List<Empresa> getListEmpresa(String patch) throws Exception {
        archivo = new File(patch);
        
        if (archivo.exists()) {
            FileInputStream linea = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(linea);
            
            try{
                 while (true) {                    
                    Empresa empresa = new Empresa();
                    empresa.setNombreEmpresa(lectura.readUTF());
                    empresa.setNumeroRUC(lectura.readUTF());
                    empresa.setNumeroSocios(lectura.readUTF());
                    empresa.setNombreDepartamento(lectura.readUTF());
                    
                    empresas.add(empresa);
                }
  
            }catch (Exception e){
                return empresas;
 
            }finally {
                lectura.close();
            }
            
            
        }else{
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
        String[] empleads = new String[emplead.size() + 1];
        empleads[0] = "Selecionar";
        if (empleads.length > 1) {
            for (int i = 0; i < emplead.size(); i++) {
                System.out.println("Empleado " + emplead.get(i).getNombreApellido());

                empleads[i + 1] = emplead.get(i).getNombreApellido();

            }
        }

        return empleads;

    }

    public Empleado buscarEmpleado(List<Empleado> emplead, String asp) {

        for (int i = 0; i < emplead.size() && emplead.get(i) != null; i++) {
            if (asp.equals(emplead.get(i).getNombreApellido())) {

                return emplead.get(i);

            }
        }

        return null;
    }

    public Departamento buscarDepartamento(List<Departamento> departament, String auxDepartamentos) {
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
        } catch (NumberFormatException e) {
            throw new Exception("Formato incorrecto, contiene caracteres");
        }
        if (cedula.length() != 10) {
            throw new Exception("Debe ser de 10 dígitos");
        }

        return true;
    }

    public boolean verificarCedula(List<Empleado> listEmpleados, String cedula) throws Exception {
        int n = 1;
        if (listEmpleados.size() > 0) {
            for (int i = 0; i < listEmpleados.size(); i++) {
                if (cedula.equals(listEmpleados.get(i).getCedula())) {
                    n++;
                }
            }
            if (n > 1) {
                throw new Exception("Esta cedula ya fue registrada");
            }
        }
        return true;
    }
    
        public boolean verificarDepartamento(List<Departamento> listDepartamentos, String nomDepar) throws Exception {
        int n = 1;
        if (listDepartamentos.size() > 0) {
            for (int i = 0; i < listDepartamentos.size(); i++) {
                if (nomDepar.equals(listDepartamentos.get(i).getNombreDep())) {
                    n++;
                }
            }
            if (n > 1) {
                throw new Exception("Este departamento ya fue registrado");
            }
        }
        return true;
    }
        
        
        public boolean verificarEmpresa(List<Empresa> listEmpresas, String nomEmpresa) throws Exception {
        int n = 1;
        if (listEmpresas.size() > 0) {
            for (int i = 0; i < listEmpresas.size(); i++) {
                if (nomEmpresa.equals(listEmpresas.get(i).getNombreEmpresa())) {
                    n++;
                }
            }
            if (n > 1) {
                throw new Exception("Esta empresa ya fue registrada");
            }
        }
        return true;
    }
}
