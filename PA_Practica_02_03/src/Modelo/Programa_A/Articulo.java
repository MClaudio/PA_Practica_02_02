
package Modelo.Programa_A;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private String titulo;
    private String abstrac;
    private String paginaInicio;
    private String paginaFin;	
    private Autor autores;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }

    public String getPaginaInicio() {
        return paginaInicio;
    }

    public void setPaginaInicio(String paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    public String getPaginaFin() {
        return paginaFin;
    }

    public void setPaginaFin(String paginaFin) {
        this.paginaFin = paginaFin;
    }

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        this.autores = autores;
    }
    
}
