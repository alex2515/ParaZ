
package Datos;

import java.sql.Date;

/**
 *
 * @author Ale
 */
public class valquiler_detalle extends vcomprobante {
    private int idproducto;
    private String cantidad;
    private Double subtotal;
    private Date fecha_alquiler;
    private Date fecha_devolucion;

    public valquiler_detalle() {
    }

    public valquiler_detalle(int idproducto, String cantidad, Double subtotal, Date fecha_alquiler, Date fecha_devolucion) {
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.fecha_alquiler = fecha_alquiler;
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Date getFecha_alquiler() {
        return fecha_alquiler;
    }

    public void setFecha_alquiler(Date fecha_alquiler) {
        this.fecha_alquiler = fecha_alquiler;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    
    
    
    
}
