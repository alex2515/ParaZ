package Logica;

import Datos.vdanza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fdanza {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    //CRUD
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"Nº", "Nombre", "IDReg", "REGION", "IDDepart", "Depart", "IDProvincia", "Provincia", "Descripcion"};

        String[] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT d.iddanza,d.nombre_danza,d.idregion, "
                + "(select nombre_region FROM region Where idregion=d.idregion)as regionn, "
                + "d.iddepartamento, "
                + "(select nombre_departamento FROM departamento where iddepartamento=d.iddepartamento)as departamenton,"
                + "d.idprovincia, "
                + "(select nombre_provincia FROM provincia where idprovincia=d.idprovincia)as provincian,"
                + "d.descripcion FROM danza d "
                + "WHERE nombre_danza like '%" + buscar + "%' order by iddanza desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("iddanza");
                registro[1] = rs.getString("nombre_danza");
                registro[2] = rs.getString("idregion");
                registro[3] = rs.getString("regionn");
                registro[4] = rs.getString("iddepartamento");
                registro[5] = rs.getString("departamenton");
                registro[6] = rs.getString("idprovincia");
                registro[7] = rs.getString("provincian");
                registro[8] = rs.getString("descripcion");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(vdanza dts) {
        sSQL = "insert into danza (nombre_danza,idregion,iddepartamento,idprovincia,descripcion)"
                + "values (?,?,?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setString(1, dts.getNombre_danza());
            pst.setInt(2, dts.getIdregion());
            pst.setInt(3, dts.getIddepartamento());
            pst.setInt(4, dts.getIdprovincia());
            pst.setString(5, dts.getDescripcion());

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

    public boolean editar(vdanza dts) {
        sSQL = "update danza set nombre_danza=?,idregion=?,iddepartamento=?,idprovincia=?,descripcion=?"
                + " where iddanza=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);
            
            pst.setString(1, dts.getNombre_danza());
            pst.setInt(2, dts.getIdregion());
            pst.setInt(3, dts.getIddepartamento());
            pst.setInt(4, dts.getIdprovincia());
            pst.setString(5, dts.getDescripcion());
            
            pst.setInt(6, dts.getIddanza());

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

    public boolean eliminar(vdanza dts) {
        sSQL = "delete from danza where iddanza=?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIddanza());

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
