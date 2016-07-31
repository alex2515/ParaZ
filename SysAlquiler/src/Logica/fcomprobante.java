package Logica;

import Datos.valquiler_detalle;
import Datos.vcomprobante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fcomprobante {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistros;

    //CRUD
//    public DefaultTableModel mostrar(String buscar) {
//        DefaultTableModel modelo;
//
//        String[] titulos = {"idcomprobante", "idcliente", "idtrabajador", "Total"};
//
//        String[] registro = new String[4];
//
//        totalregistros = 0;
//        modelo = new DefaultTableModel(null, titulos);
//        sSQL= "SELECT *FROM comprobante";
//            
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sSQL);
//
//            while (rs.next()) {
//                registro[0] = rs.getString("idcomprobante");
//                registro[1] = rs.getString("idcliente");
//                registro[2] = rs.getString("idtrabajador");
//                registro[3] = rs.getString("total");
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
    public boolean insertar(vcomprobante dts) {
        sSQL = "insert into comprobante (idcliente,idtrabajador,total)"
                + "values (?,?,?)";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getIdcliente());
            pst.setInt(2, dts.getIdtrabajador());
            pst.setDouble(3, dts.getTotal());

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
