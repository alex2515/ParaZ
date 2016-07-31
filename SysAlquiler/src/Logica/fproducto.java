package Logica;

import Datos.vproducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fproducto {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    //CRUD
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"Nº", "Nombre", "iddanza", "danza_nombre", "danza_idregion",
            "regionn", "danza_iddepartamento", "Departamenton", "Danza_idprovincia", "Provincian",
            "Danza_Descripcion", "talla", "Caracteriticas", "precio_diario", "Estado", "Stock"};

        String[] registro = new String[16];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "select p.idproducto,p.nombre_producto,p.iddanza,"
                + "(select nombre_danza FROM danza WHERE iddanza=p.iddanza)as danza_nombre,"
                + "(select idregion FROM danza WHERE iddanza=p.iddanza)as danza_idregion,"
                + "(select nombre_region FROM region WHERE idregion=d.idregion)as regionn,"
                + "(select iddepartamento FROM danza WHERE iddanza=p.iddanza)as danza_iddepartamento,"
                + "(select nombre_departamento FROM departamento WHERE iddepartamento=d.iddepartamento)as departamenton,"
                + "(select idprovincia FROM danza WHERE iddanza=p.iddanza)as danza_idprovincia,"
                + "(select nombre_provincia FROM provincia WHERE idprovincia=d.idprovincia)as provincian,"
                + "(select descripcion FROM danza WHERE iddanza=p.iddanza)as danza_descripcion,"
                + "p.talla,p.caracteristicas,p.precio_diario,p.estado,p.stock "
                + "from producto p JOIN danza d ON p.iddanza=d.iddanza"
                + " WHERE nombre_producto like '%" + buscar + "%' order by idproducto desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idproducto");
                registro[1] = rs.getString("nombre_producto");
                registro[2] = rs.getString("iddanza");
                registro[3] = rs.getString("danza_nombre");
                registro[4] = rs.getString("danza_idregion");
                registro[5] = rs.getString("regionn");
                registro[6] = rs.getString("danza_iddepartamento");
                registro[7] = rs.getString("departamenton");
                registro[8] = rs.getString("danza_idprovincia");
                registro[9] = rs.getString("provincian");
                registro[10] = rs.getString("danza_descripcion");
                registro[11] = rs.getString("talla");
                registro[12] = rs.getString("caracteristicas");
                registro[13] = rs.getString("precio_diario");
                registro[14] = rs.getString("estado");
                registro[15] = rs.getString("stock");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vproducto dts) {
        sSQL = "INSERT INTO producto (nombre_producto,iddanza,talla,caracteristicas,"
                + "precio_diario,estado,stock)"
                + "values (?,?,?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getNombre_producto());
            pst.setInt(2, dts.getIddanza());
            pst.setString(3, dts.getTalla());
            pst.setString(4, dts.getCaracteristicas());
            pst.setString(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getStock());

            int n = pst.executeUpdate();

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

    public boolean editar(vproducto dts) {
        sSQL = "update producto set nombre_producto=?,iddanza=?,talla=?,caracteristicas=?,"
                + "precio_diario=?,estado=?,stock=? where idproducto=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dts.getNombre_producto());
            pst.setInt(2, dts.getIddanza());
            pst.setString(3, dts.getTalla());
            pst.setString(4, dts.getCaracteristicas());
            pst.setString(5, dts.getPrecio_diario());
            pst.setString(6, dts.getEstado());
            pst.setString(7, dts.getStock());
            
            pst.setInt(8, dts.getIdproducto());

            int n = pst.executeUpdate();

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

    public boolean eliminar(vproducto dts) {
        sSQL = "delete from producto where idproducto=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdproducto());

            int n = pst.executeUpdate();

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
