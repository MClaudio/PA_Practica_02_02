
package Modelo.Programa_A;

import java.util.List;

public class Autor {

    private String codigo;
    private String nombre;
    private String anioNacimiento;
    private String nacionalidad; 
    private List<Articulo> articulos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }    
    
    public void addArticulo(Articulo articulo){
        articulos.add(articulo);
    }
}
