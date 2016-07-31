package Logica;

import Datos.valquiler_detalle;
import Datos.vcliente;
import Datos.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class falquiler_detalle {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    //CRUD
//    public DefaultTableModel mostrar(String buscar) {
//        DefaultTableModel modelo;
//
//        String[] titulos = {"Nº", "Tipo", "Numero", "Nombre","Paterno", "Materno","Direccion","IDReg","REGION",
//            "IDDepart","Depart", "IDProvincia","Provincia","Telefono","Email","Sueldo","Acceso","Login","Password","Estado"};
//
//        String[] registro = new String[20];
//
//        totalregistros = 0;
//        modelo = new DefaultTableModel(null, titulos);
//        sSQL= "SELECT e.idpersona,e.tipo_documento,e.num_documento,e.nombre_persona,e.apaterno,e.amaterno,e.direccion,"
//                + "e.idregion, "
//                + "(select nombre_region FROM region Where idregion=e.idregion)as regionn,"
//                + "e.iddepartamento, "
//                + "(select nombre_departamento FROM departamento where iddepartamento=e.iddepartamento)as departamenton,"
//                + " e.idprovincia, "
//                + "(select nombre_provincia FROM provincia where idprovincia=e.idprovincia)as provincian,"
//                + "e.telefono,e.email,t.sueldo,t.acceso,t.login,t.password,t.estado FROM persona e INNER JOIN trabajador t ON e.idpersona=t.idpersona "
//                + "WHERE num_documento like '%"+buscar+"%' order by idpersona desc";
//            
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sSQL);
//
//            while (rs.next()) {
//                registro[0] = rs.getString("idpersona");
//                registro[1] = rs.getString("tipo_documento");
//                registro[2] = rs.getString("num_documento");
//                registro[3] = rs.getString("nombre_persona");
//                registro[4] = rs.getString("apaterno");
//                registro[5] = rs.getString("amaterno");
//                registro[6] = rs.getString("direccion");
//                registro[7] = rs.getString("idregion");
//                registro[8] = rs.getString("regionn");
//                registro[9] = rs.getString("iddepartamento");
//                registro[10] = rs.getString("departamenton");
//                registro[11] = rs.getString("idprovincia");
//                registro[12] = rs.getString("provincian");                
//                registro[13] = rs.getString("telefono");
//                registro[14] = rs.getString("email");
//                registro[15] = rs.getString("sueldo");
//                registro[16] = rs.getString("acceso");
//                registro[17] = rs.getString("login");
//                registro[18] = rs.getString("password");
//                registro[19] = rs.getString("estado");
//
//                totalregistros = totalregistros + 1;
//                modelo.addRow(registro);
//
//            }
//            return modelo;
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e);
//            return null;
//        }
//    }
    public boolean insertar(valquiler_detalle dts) {

        sSQL2 = "insert into alquiler_detalle (idproducto,cantidad,subtotal,fecha_alquiler,fecha_devolucion)"
                + "values ((select idcomprobante from comprobante order by idcomprobante desc limit 1),?,?,?,?,?)";
        try {

            PreparedStatement pst2 = cn.prepareStatement(sSQL2);

            pst2.setInt(1, dts.getIdproducto());
            pst2.setString(2, dts.getCantidad());
            pst2.setDouble(3, dts.getSubtotal());
            pst2.setDate(4, dts.getFecha_alquiler());
            pst2.setDate(5, dts.getFecha_devolucion());

            int n = pst2.executeUpdate();

            if (n != 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}

//    public boolean editar(vtrabajador dts) {
//        sSQL = "update persona set idregion=?,iddepartamento=?,idprovincia=?,"
//                + "tipo_documento=?,num_documento=?,nombre_persona=?,apaterno=?,amaterno=?,"
//                + " direccion=?,telefono=?,email=?,sueldo=?,acceso=?,login=?,password=?,estado=? where idpersona=?";
//
////        sSQL2 = "update cliente "
////                + " where idpersona=?";
//        try {
//
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
//            
//            pst.setInt(1, dts.getIdregion());
//            pst.setInt(2, dts.getIddepartamento());
//            pst.setInt(3, dts.getIdprovincia());
//            
//            pst.setString(4, dts.getTipo_documento());
//            pst.setString(5, dts.getNum_documento());
//            pst.setString(6, dts.getNombre_persona());
//            pst.setString(7, dts.getApaterno());
//            pst.setString(8, dts.getAmaterno());
//            pst.setString(9, dts.getDireccion());
//            pst.setString(10, dts.getTelefono());
//            pst.setString(11, dts.getEmail());
//            pst.setDouble(12, dts.getSueldo());
//            pst.setString(13, dts.getAcceso());
//            pst.setString(14, dts.getLogin());
//            pst.setString(15, dts.getPassword());
//            pst.setString(16, dts.getEstado());
//            
//            pst.setInt(17,dts.getIdpersona());
//
////            pst2.setInt(2, dts.getIdpersona());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
////                int n2 = pst2.executeUpdate();
////
////                if (n2 != 0) {
//                    return true;
////
////                } else {
////                    return false;
////                }
//
//            } else {
//                return false;
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e);
//            return false;
//        }
//    }
//
//    public boolean eliminar(vtrabajador dts) {
//        sSQL = "delete from trabajador where idpersona=?";
//        sSQL2 = "delete from persona where idpersona=?";
//
//        try {
//
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
//
//            pst.setInt(1, dts.getIdpersona());
//
//            pst2.setInt(1, dts.getIdpersona());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                int n2 = pst2.executeUpdate();
//
//                if (n2 != 0) {
//                    return true;
//
//                } else {
//                    return false;
//                }
//
//            } else {
//                return false;
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e);
//            return false;
//        }
//    }
//
//    
//    public DefaultTableModel login(String login, String password) {
//        DefaultTableModel modelo;
//
//        String[] titulos = {"ID", "Nombre", "Apaterno", "Amaterno", "Acceso", "Login", "Clave", "Estado"};
//
//        String[] registro = new String[8];
//
//        totalregistros = 0;
//        modelo = new DefaultTableModel(null, titulos);
//
//        sSQL = "select p.idpersona,p.nombre_persona,p.apaterno,p.amaterno,"
//                + "t.acceso,t.login,t.password,t.estado from persona p inner join trabajador t "
//                + "on p.idpersona=t.idpersona where t.login='"
//                + login + "' and t.password='" + password + "' and t.estado='A'";
//
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sSQL);
//
//            while (rs.next()) {
//                registro[0] = rs.getString("idpersona");
//                registro[1] = rs.getString("nombre_persona");
//                registro[2] = rs.getString("apaterno");
//                registro[3] = rs.getString("amaterno");
//
//                registro[4] = rs.getString("acceso");
//                registro[5] = rs.getString("login");
//                registro[6] = rs.getString("password");
//                registro[7] = rs.getString("estado");
//
//                totalregistros = totalregistros + 1;
//                modelo.addRow(registro);
//
//            }
//            return modelo;
//
//        } catch (Exception e) {
//            JOptionPane.showConfirmDialog(null, e);
//            return null;
//        }
//
//    }
//}
