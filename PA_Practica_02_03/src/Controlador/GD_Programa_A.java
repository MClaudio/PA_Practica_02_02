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

    public GD_Programa_A() {
        revistas = new ArrayList<Revista>();
        articulos = new ArrayList<Articulo>();
        autores = new ArrayList<Autor>();
    }

    public GD_Programa_A(String pathname) {
        archivo = new File(pathname);
    }

    public void agragarRevista(String isdn, String numeroEdicion, String nombre, String idioma, String articulo) throws IOException, Exception {
        //System.out.println(archivo.getAbsolutePath());
        //El getAbsolutePath: este metodo de ruta devuelve la ruta absoluta del archivo
        try {
            if (archivo.exists()) {
                FileOutputStream file = new FileOutputStream(archivo, true);
                DataOutputStream escritura = new DataOutputStream(file);
                escritura.writeUTF(isdn);
                escritura.writeUTF(numeroEdicion);
                escritura.writeUTF(nombre); 
                escritura.writeUTF(idioma);
                escritura.writeUTF(articulo);
                escritura.close();
            } else {
                throw new Exception("Error el archivo no existe");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarArticulo(String titulo, String abstact, String paginaInicio, String paginaFin, String autor) throws IOException, Exception {
        //System.out.println(archivo.getAbsolutePath());
        try {
            if (archivo.exists()) {
                FileOutputStream file = new FileOutputStream(archivo, true);
                DataOutputStream escritura = new DataOutputStream(file);
                escritura.writeUTF(titulo);
                escritura.writeUTF(abstact);
                escritura.writeUTF(paginaInicio);
                escritura.writeUTF(paginaFin);
                escritura.writeUTF(autor);
                escritura.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void agregarAutor(String codigo, String nombre, String anioNacimiento, String nacionalidad) throws IOException, Exception {
        // System.out.println(archivo.getAbsolutePath());
        try {
            if (archivo.exists()) {
                FileOutputStream file = new FileOutputStream(archivo, true);
                DataOutputStream escritura = new DataOutputStream(file);
                escritura.writeUTF(codigo);
                escritura.writeUTF(nombre);
                escritura.writeUTF(anioNacimiento);
                escritura.writeUTF(nacionalidad);
                escritura.close();
            } else {
                throw new Exception("El archivo no existe");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Revista> listarRevista(String pathname) throws FileNotFoundException, IOException, Exception {
        archivo = new File(pathname);
        if (archivo.exists()) {
            FileInputStream file = new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try{
                while(true){
                    Revista revista=new Revista();
                    revista.setIsdn(lectura.readUTF());
                    revista.setNumeroEdicion(lectura.readUTF());
                    revista.setNombre(lectura.readUTF());
                    revista.setIdioma(lectura.readUTF());
                    String titulo=lectura.readUTF();
                    System.out.println("Titulo"+titulo);
                    for(int j=0; j<articulos.size();j++){
                        Articulo get=articulos.get(j);
                        if(get.getTitulo().equals(titulo)){
                            get.addRevista(revista);
                        }
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                return revistas;
            }finally{
            lectura.close();
            }
        } else {
            throw new Exception("El archivo no existe.");
        }
    }

    public List<Articulo> listarArticulo(String pathname) throws Exception {
        archivo = new File(pathname);
        if (archivo.exists()) {
            FileInputStream file =new FileInputStream(archivo);
            DataInputStream lectura = new DataInputStream(file);
            try {
                String ruta="src/Archivos/Programa_A/Articulo.dat";
                file=new  FileInputStream(ruta);
                lectura=new DataInputStream(file);
                while(true){
                    Articulo articulo=new Articulo();
                    articulo.setTitulo(lectura.readUTF());
                    articulo.setAbstrac(lectura.readUTF());
                    articulo.setPaginaInicio(lectura.readUTF());
                    articulo.setPaginaFin(lectura.readUTF());
                    for(int j = 0; j < autores.size(); j++){
                        Autor get=autores.get(j);
                        if(get.getNombre().equals(lectura.readUTF())){
                            get.addArticulo(articulo);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                while(true){
                    Autor autor=new Autor();
                    autor.setCodigo(lectura.readUTF());
                    autor.setNombre(lectura.readUTF());
                    autor.setCodigo(lectura.readUTF());
                    autor.setNacionalidad(lectura.readUTF());
                    autores.add(autor);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lectura.close();
            }
            return autores;
        } else {
            throw new Exception("El archivo no existe");
        }
    }
}
