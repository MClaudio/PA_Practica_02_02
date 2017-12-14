
package Modelo.Programa_E;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ClauMldo
 */
public class Equipo {
    private String nombre;
    private String categoria;
    private List<Jugador> jugadores;

    public Equipo() {
        jugadores=new ArrayList<Jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    public void addJugador(Jugador jugador){
        jugadores.add(jugador);
    }
    
    
}
