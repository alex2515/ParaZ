
package Datos;

/**
 *
 * @author Ale
 */
public class vregion {
    private int idregion;
    private String nombre_region;

    public vregion(int idregion, String nombre_region) {
        this.idregion = idregion;
        this.nombre_region = nombre_region;
    }

    public vregion() {
    }

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }

    public String getNombre_region() {
        return nombre_region;
    }

    public void setNombre_region(String nombre_region) {
        this.nombre_region = nombre_region;
    }
    
    
    
}
