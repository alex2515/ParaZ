
package Datos;

import java.sql.Date;

/**
 *
 * @author Ale
 */
public class vdevolucion extends vcomprobante {
    private int idproducto;
    private int idtrabajador;
    private Date fecha_recepcion;
    private String cantidad;

    public vdevolucion() {
    }

    public vdevolucion(int idproducto, int idtrabajador, Date fecha_recepcion, String cantidad) {
        this.idproducto = idproducto;
        this.idtrabajador = idtrabajador;
        this.fecha_recepcion = fecha_recepcion;
        this.cantidad = cantidad;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
