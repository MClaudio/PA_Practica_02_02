
package Modelo.Programa_B;

import java.util.ArrayList;
import java.util.List;

public class Provincia {
    private String nombreProvincia;
    private String region;
    private String gobernador;
    
    private Pais pais;
    
    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGobernador() {
        return gobernador;
    }

    public void setGobernador(String gobernador) {
        this.gobernador = gobernador;
    }   

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    
}
