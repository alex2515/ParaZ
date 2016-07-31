
package Datos;

/**
 *
 * @author Ale
 */
public class vproducto {
    private int idproducto;
    private String nombre_producto;
    private int iddanza;
    private String talla;
    private String caracteristicas;
    private String precio_diario;
    private String estado;
    private String stock;

    public vproducto() {
    }

    public vproducto(int idproducto, String nombre_producto, int iddanza, String talla, String caracteristicas, String precio_diario, String estado, String stock) {
        this.idproducto = idproducto;
        this.nombre_producto = nombre_producto;
        this.iddanza = iddanza;
        this.talla = talla;
        this.caracteristicas = caracteristicas;
        this.precio_diario = precio_diario;
        this.estado = estado;
        this.stock = stock;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getIddanza() {
        return iddanza;
    }

    public void setIddanza(int iddanza) {
        this.iddanza = iddanza;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getPrecio_diario() {
        return precio_diario;
    }

    public void setPrecio_diario(String precio_diario) {
        this.precio_diario = precio_diario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    
}
