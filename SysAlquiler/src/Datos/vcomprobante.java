
package Datos;

/**
 *
 * @author Ale
 */
public class vcomprobante {
    private int idcomprobante;
    private int idcliente;
    private int idtrabajador;
    private Double total;

    public vcomprobante() {
    }

    public vcomprobante(int idcomprobante, int idcliente, int idtrabajador, Double total) {
        this.idcomprobante = idcomprobante;
        this.idcliente = idcliente;
        this.idtrabajador = idtrabajador;
        this.total = total;
    }

    public int getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(int idcomprobante) {
        this.idcomprobante = idcomprobante;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
    
}
