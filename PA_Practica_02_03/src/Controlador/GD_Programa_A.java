package Controlador;

import Modelo.Programa_A.Articulo;
import Modelo.Programa_A.Autor;
import Modelo.Programa_A.Revista;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GD_Programa_A {

    private List<Revista> revistas;
    private List<Articulo> articulos;
    private List<Autor> autores;

    private File archivo;

    public GD_Programa_A(String pathname) {
        revistas = new ArrayList<Revista>();
        articulos = new ArrayList<Articulo>();
        autores = new ArrayList<Autor>();
        archivo = new File(pathname);
    }

    public void agragarRevista(String isdn, String numeroEdicion, String nombre, String idioma, String articulo) throws IOException, Exception {
        //System.out.println(archivo.getAbsolutePath());
        //El getAbsolutePath: este metodo de ruta devuelve la ruta absoluta del archivo
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {
                escritura.writeUTF(isdn);
                escritura.writeUTF(numeroEdicion);
                escritura.writeUTF(nombre);
                escritura.writeUTF(idioma);
                escritura.writeUTF(articulo);
            } catch (FileNotFoundException e) {
                throw new Exception("Error al escribir el archivo.");
                
            }finally{
                escritura.close();
            }
        } else {
            throw new Exception("Error el archivo no existe");
        }
    }

    public void agregarArticulo(String titulo, String abstact, String paginaInicio, String paginaFin, String autor) throws IOException, Exception {
        //System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {
                escritura.writeUTF(titulo);
                escritura.writeUTF(abstact);
                escritura.writeUTF(paginaInicio);
                escritura.writeUTF(paginaFin);
                escritura.writeUTF(autor);
            } catch (FileNotFoundException e) {
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();
            }
        }else{
            throw new Exception("Error el archivo no existe.");
        }
    }

    public void agregarAutor(String codigo, String nombre, String anioNacimiento, String nacionalidad) throws IOException, Exception {
        // System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileOutputStream file = new FileOutputStream(archivo, true);
            DataOutputStream escritura = new DataOutputStream(file);
            try {

                escritura.writeUTF(codigo);
                escritura.writeUTF(nombre);
                escritura.writeUTF(anioNacimiento);
                escritura.writeUTF(nacionalidad);

            } catch (FileNotFoundException e) {
                throw new Exception("Error al escribir el archivo.");
            } finally {
                escritura.close();
            }
        } else {
            throw new Exception("El archivo no existe");
        }
    }

    public List<Revista> listarRevista(String pathname) throws FileNotFoundException, IOException, Exception {
        archivo = new File(pathname);
        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                while (true) {
                    Revista revista = new Revista();
                    revista.setIsdn(lectura.readUTF());
                    revista.setNumeroEdicion(lectura.readUTF());
                    revista.setNombre(lectura.readUTF());
                    revista.setIdioma(lectura.readUTF());
                    revistas.add(revista);
                }
            } catch (Exception e) {
                return revistas;
            } finally {
                lectura.close();
            }
        } else {
            throw new Exception("El archivo no existe.");
        }
    }

    public List<Articulo> listarArticulo(String pathname) throws Exception {
        archivo = new File(pathname);
        
        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                while (true) {
                    Articulo articulo = new Articulo();
                    articulo.setTitulo(lectura.readUTF());
                    articulo.setAbstrac(lectura.readUTF());
                    articulo.setPaginaInicio(lectura.readUTF());
                    articulo.setPaginaFin(lectura.readUTF());
                    
                    articulos.add(articulo);
                }
            } catch (Exception e) {
                return articulos;
            } finally {
                lectura.close();
            }
        } else {
            throw new Exception("El archivo no existe");
        }
    }

    public List<Autor> listarAutor(String pathname) throws Exception {
        archivo = new File(pathname);

        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                while (true) {
                    Autor autor123 = new Autor();
                    autor123.setCodigo(lectura.readUTF());
                    autor123.setNombre(lectura.readUTF());
                    autor123.setAnioNacimiento(lectura.readUTF());
                    autor123.setNacionalidad(lectura.readUTF());
                    autores.add(autor123);
                }
            } catch (Exception e) {
                return autores;
            } finally {
                lectura.close();
            }
            
        } else {
            throw new Exception("El archivo no existe");
        }
    }
    
    public String[] listAutor(List<Autor> departament) {

        String[] departaments = new String[departament.size() + 1];
        departaments[0] = "Selecionar";
        for (int i = 0; i < departament.size(); i++) {
            departaments[i + 1] = departament.get(i).getNombre();
        }
        return departaments;
    }
    
    public String[] listArticulo(List<Articulo> departament) {

        String[] departaments = new String[departament.size() + 1];
        departaments[0] = "Selecionar";
        for (int i = 0; i < departament.size(); i++) {
            departaments[i + 1] = departament.get(i).getTitulo();
        }
        return departaments;
    }
}
