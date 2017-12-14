
package Modelo.Programa_B;

public class Canton {
    private String nombreCanton;
    private String codigoPostal;
    private String numeroHabitantes;
    private Provincia provincia;
    
    public String getNombreCanton() {
        return nombreCanton;
    }

    public void setNombreCanton(String nombreCanton) {
        this.nombreCanton = nombreCanton;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroHabitantes() {
        return numeroHabitantes;
    }

    public void setNumeroHabitantes(String numeroHabitantes) {
        this.numeroHabitantes = numeroHabitantes;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    
}
