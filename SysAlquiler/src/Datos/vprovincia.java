
package Datos;

/**
 *
 * @author Ale
 */
public class vprovincia {
    private int idprovincia;
    private int idregion;
    private int iddepartamento;
    private String nombre_provincia;

    public vprovincia() {
    }

    public vprovincia(int idprovincia, int idregion, int iddepartamento, String nombre_provincia) {
        this.idprovincia = idprovincia;
        this.idregion = idregion;
        this.iddepartamento = iddepartamento;
        this.nombre_provincia = nombre_provincia;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }

    public int getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(int iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }
    
    
    
}
