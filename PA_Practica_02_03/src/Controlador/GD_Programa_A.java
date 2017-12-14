package Controlador;

import Modelo.Programa_A.Articulo;
import Modelo.Programa_A.Autor;
import Modelo.Programa_A.Revista;
import Modelo.Programa_F.Doctor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(isdn + "|" + numeroEdicion + "|" + nombre + "|" + idioma + "|" + articulo + "|\n ");
            escritura.close();
        } else {
            throw new Exception("Error el archivo no existe");
        }
    }

    public void agregarArticulo(String titulo, String abstact, String paginaInicio, String paginaFin, String autor) throws IOException, Exception {
        //System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(titulo + "|" + abstact + "|" + paginaInicio + "|" + paginaFin + "|" + autor + "|\n ");
            escritura.close();
        }
    }

    public void agregarAutor(String codigo, String nombre, String anioNacimiento, String nacionalidad) throws IOException, Exception {
        // System.out.println(archivo.getAbsolutePath());
        if (archivo.exists()) {
            FileWriter file = new FileWriter(archivo, true);
            BufferedWriter escritura = new BufferedWriter(file);
            escritura.append(codigo + "|" + nombre + "|" + anioNacimiento + "|" + nacionalidad + "|\n ");
            escritura.close();
        }else{
            throw new Exception("El archivo no existe");
        }
    }

    public List<Revista> listarRevista(String pathname) throws FileNotFoundException, IOException, Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datoisbn = true;
        boolean datonumeroEdicion = false;
        boolean datonombre = false;
        boolean datoidioma = false;
        boolean datoarticulo = false;

        if (archivo.exists()) {

            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {
                Revista revista = new Revista();
                linea = lectura.readLine();
                if (linea != null) {
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datoisbn == true && !palabra.equals("")) {
                                revista.setIsdn(palabra);
                                palabra = "";
                                System.out.println("Nomnre: " + palabra);
                                datoisbn = false;
                                datonumeroEdicion = true;
                            }
                            if (datonumeroEdicion == true && !palabra.equals("")) {
                                revista.setNumeroEdicion(palabra);
                                palabra = "";
                                System.out.println("Numero de Edicion: " + palabra);
                                datonumeroEdicion = false;
                                datonombre = true;
                            }
                            if (datonombre == true && !palabra.equals("")) {
                                revista.setNombre(palabra);
                                palabra = "";
                                System.out.println("Nombre Revista: " + palabra);
                                datonombre = false;
                                datoidioma = true;
                            }
                            if (datoidioma == true && !palabra.equals("")) {
                                revista.setIdioma(palabra);
                                palabra = "";
                                System.out.println("Idioma: " + palabra);
                                datoidioma = false;
                                datoisbn = true;
                            }
                            if (datoarticulo == true && !palabra.equals("")) {
                                List<Articulo> articulos = listarArticulo("src/Archivos/Programa_A/Articulo.txt");
                                String[] dato = new String[3];
                                dato = palabra.split(" ");
                                for (int j = 0; j < articulos.size(); j++) {
                                    Articulo get = articulos.get(j);
                                    if (get.getTitulo().equals(dato[0])) {
                                        revista.setArticulos(get);
                                    }
                                }
                                datoarticulo = false;
                                datoisbn = true;
                                palabra = "";
                            }
                        }
                    }
                    revistas.add(revista);
                }
            }
            file.close();
            return revistas;
        } else {
            throw new Exception("El archivo no existe.");
        }
    }

    public List<Articulo> listarArticulo(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datotitulo = true;
        boolean datoabstract = false;
        boolean datopaginainicio = false;
        boolean datopaginafin = false;
        boolean datoautores = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);
            while (linea != null) {
                linea = lectura.readLine();
                if (linea != null) {
                Articulo articulo = new Articulo();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datotitulo == true && !palabra.equals("")) {
                                articulo.setTitulo(linea);
                                palabra = "";
                                System.out.println("Nomnre: " + palabra);
                                datotitulo = false;
                                datoabstract = true;
                            }
                            if (datoabstract == true && !palabra.equals("")) {
                                articulo.setAbstrac(palabra);
                                palabra = "";
                                System.out.println("Numero de Edicion: " + palabra);
                                datoabstract = false;
                                datopaginainicio = true;
                            }
                            if (datopaginainicio == true && !palabra.equals("")) {
                                articulo.setPaginaInicio(palabra);
                                palabra = "";
                                System.out.println("Nombre Revista: " + palabra);
                                datopaginainicio = false;
                                datopaginafin = true;
                            }
                            if (datopaginafin == true && !palabra.equals("")) {
                                articulo.setPaginaFin(palabra);
                                palabra = "";
                                System.out.println("Idioma: " + palabra);
                                datopaginafin = false;
                                datoautores = false;
                            }

                            if (datoautores == true && !palabra.equals("")) {
                                List<Autor> autores = listarAutor("src/Archivos/Programa_A/Autor.txt");
                                String[] dato = new String[3];
                                dato = palabra.split("");
                                for (int j = 0; j < autores.size(); j++) {
                                    Autor get = autores.get(j);
                                    if (get.getNombre().equals(dato[1])) {
                                        articulo.setAutores(get);
                                    }
                                }
                                datoautores = false;
                                datotitulo = true;
                            }
                        }
                    }
                    articulos.add(articulo);
                }
            }
            file.close();
            return articulos;
        } else {
            throw new Exception("El archivo no existe");
        }
    }

    public List<Autor> listarAutor(String pathname) throws Exception {
        archivo = new File(pathname);
        String linea = "";
        String palabra = "";
        boolean datocodigo = true;
        boolean datonombre = false;
        boolean datoanioNacimiento = false;
        boolean datonacionalidad = false;

        if (archivo.exists()) {
            FileReader file = new FileReader(archivo);
            BufferedReader lectura = new BufferedReader(file);

            while (linea != null) {
                linea = lectura.readLine();
                if (linea != null) {
                    Autor autor = new Autor();
                    for (int i = 0; i < linea.length(); i++) {
                        char caracter = linea.charAt(i);
                        if (caracter != '|') {
                            palabra += caracter;
                        } else {
                            if (datocodigo == true && !palabra.equals("")) {
                                autor.setCodigo(palabra);
                                datocodigo = false;
                                datonombre = true;
                                palabra = "";
                            }
                            if (datonombre == true && !palabra.equals("")) {
                                autor.setNombre(linea);
                                datonombre = false;
                                datoanioNacimiento = true;
                                palabra = "";
                            }
                            if (datoanioNacimiento == true && !palabra.equals("")) {
                                autor.setAnioNacimiento(palabra);
                                datoanioNacimiento = false;
                                datonacionalidad = true;
                                palabra = "";
                            }
                            if (datonacionalidad == true && !palabra.equals("")) {
                                autor.setNacionalidad(palabra);
                                datonacionalidad = false;
                                datocodigo = true;
                                palabra = "";
                            }
                        }
                    }
                    autores.add(autor);
                }
            }
            file.close();
            return autores;
        } else {
            throw new Exception("El archivo no existe");
        }
    }
}
