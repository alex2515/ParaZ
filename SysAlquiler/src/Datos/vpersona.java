
package Datos;

public class vpersona {
    private int idpersona;
    private int idregion;
    private int iddepartamento;
    private int idprovincia;
    private String tipo_documento;
    private String num_documento;
    private String nombre_persona;
    private String apaterno;
    private String amaterno;
    private String direccion;
    private String telefono;
    private String email;

    public vpersona() {
    }

    public vpersona(int idpersona, int idregion, int iddepartamento, int idprovincia, String tipo_documento, String num_documento, String nombre_persona, String apaterno, String amaterno, String direccion, String telefono, String email) {
        this.idpersona = idpersona;
        this.idregion = idregion;
        this.iddepartamento = iddepartamento;
        this.idprovincia = idprovincia;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.nombre_persona = nombre_persona;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
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

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
}
