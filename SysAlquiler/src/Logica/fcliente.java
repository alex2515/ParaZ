package Logica;

import Datos.vcliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fcliente {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    //CRUD
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"Nº", "Tipo", "Numero", "Nombre","Paterno", "Materno","Direccion","IDReg","REGION",
            "IDDepart","Depart", "IDProvincia","Provincia","Telefono","Email"};

        String[] registro = new String[15];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL= "SELECT e.idpersona,e.tipo_documento,e.num_documento,e.nombre_persona,e.apaterno,e.amaterno,e.direccion,e.idregion, "
                + "(select nombre_region FROM region Where idregion=e.idregion)as regionn, "
                + "e.iddepartamento, "
                + "(select nombre_departamento FROM departamento where iddepartamento=e.iddepartamento)as departamenton,"
                + "e.idprovincia, "
                + "(select nombre_provincia FROM provincia where idprovincia=e.idprovincia)as provincian,"
                + "e.telefono,e.email FROM persona e INNER JOIN cliente c ON e.idpersona=c.idpersona WHERE num_documento like '%"+buscar+"%' order by e.idpersona desc";
            
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("tipo_documento");
                registro[2] = rs.getString("num_documento");
                registro[3] = rs.getString("nombre_persona");
                registro[4] = rs.getString("apaterno");
                registro[5] = rs.getString("amaterno");
                registro[6] = rs.getString("direccion");
                registro[7] = rs.getString("idregion");
                registro[8] = rs.getString("regionn");
                registro[9] = rs.getString("iddepartamento");
                registro[10] = rs.getString("departamenton");
                registro[11] = rs.getString("idprovincia");
                registro[12] = rs.getString("provincian");                
                registro[13] = rs.getString("telefono");
                registro[14] = rs.getString("email");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vcliente dts) {
        sSQL = "insert into persona (idregion,iddepartamento,idprovincia,tipo_documento,num_documento,"
                + "nombre_persona,apaterno,amaterno,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?,?,?,?,?)";
        
        sSQL2 = "insert into cliente (idpersona)"
                + "values ((select idpersona from persona order by idpersona desc limit 1))";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdregion());
            pst.setInt(2, dts.getIddepartamento());
            pst.setInt(3, dts.getIdprovincia());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getNombre_persona());
            pst.setString(7, dts.getApaterno());
            pst.setString(8, dts.getAmaterno());
            pst.setString(9, dts.getDireccion());
            pst.setString(10, dts.getTelefono());
            pst.setString(11, dts.getEmail());
            

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(vcliente dts) {
        sSQL = "update persona set idregion=?,iddepartamento=?,idprovincia=?,tipo_documento=?,num_documento=?,nombre_persona=?,apaterno=?,amaterno=?,"
                + " direccion=?,telefono=?,email=? where idpersona=?";

//        sSQL2 = "update cliente "
//                + " where idpersona=?";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            pst.setInt(1, dts.getIdregion());
            pst.setInt(2, dts.getIddepartamento());
            pst.setInt(3, dts.getIdprovincia());
            pst.setString(4, dts.getTipo_documento());
            pst.setString(5, dts.getNum_documento());
            pst.setString(6, dts.getNombre_persona());
            pst.setString(7, dts.getApaterno());
            pst.setString(8, dts.getAmaterno());
            pst.setString(9, dts.getDireccion());
            pst.setString(10, dts.getTelefono());
            pst.setString(11, dts.getEmail());
            pst.setInt(12,dts.getIdpersona());

//            pst2.setInt(2, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
//                int n2 = pst2.executeUpdate();
//
//                if (n2 != 0) {
                    return true;
//
//                } else {
//                    return false;
//                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vcliente dts) {
        sSQL = "delete from cliente where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst.setInt(1, dts.getIdpersona());

            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
