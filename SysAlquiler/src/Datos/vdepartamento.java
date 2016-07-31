
package Datos;

/**
 *
 * @author Ale
 */
public class vdepartamento {
    private int iddepartamento;
    private int idregion;
    private String nombre_departamento;

    public vdepartamento() {
    }

    public vdepartamento(int iddepartamento, int idregion, String nombre_departamento) {
        this.iddepartamento = iddepartamento;
        this.idregion = idregion;
        this.nombre_departamento = nombre_departamento;
    }

    public int getIddepartamento() {
        return iddepartamento;
    }

    public void setIddepartamento(int iddepartamento) {
        this.iddepartamento = iddepartamento;
    }

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }
    
    
    
}
