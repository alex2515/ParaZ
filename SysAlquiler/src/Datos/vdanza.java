
package Datos;

/**
 *
 * @author Ale
 */
public class vdanza {
    private int iddanza;
    private String nombre_danza;
    private int idregion;
    private int iddepartamento;
    private int idprovincia;
    private String descripcion;

    public vdanza() {
    }

    public vdanza(int iddanza, String nombre_danza, int idregion, int iddepartamento, int idprovincia, String descripcion) {
        this.iddanza = iddanza;
        this.nombre_danza = nombre_danza;
        this.idregion = idregion;
        this.iddepartamento = iddepartamento;
        this.idprovincia = idprovincia;
        this.descripcion = descripcion;
    }

    public int getIddanza() {
        return iddanza;
    }

    public void setIddanza(int iddanza) {
        this.iddanza = iddanza;
    }

    public String getNombre_danza() {
        return nombre_danza;
    }

    public void setNombre_danza(String nombre_danza) {
        this.nombre_danza = nombre_danza;
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

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
