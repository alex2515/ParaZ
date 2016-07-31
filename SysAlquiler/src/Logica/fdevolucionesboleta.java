package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fdevolucionesboleta {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"Nº", "COD.PROD", "DESCRIPCION", "CANT.", "P.UNIT", "SUBTOTAL","ESTADO"};

        String[] registro = new String[7];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select*from detalleboleta where idcomprobante like '%"+buscar+"%' order by idcomprobante desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idcomprobante");
                registro[1] = rs.getString("idproducto");
                registro[2] = rs.getString("nombre_producto");
                registro[3] = rs.getString("cantidad_producto");
                registro[4] = rs.getString("precio_unitario");
                registro[5] = rs.getString("subtotal");
                registro[6] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
}
//
//    public boolean insertar(vreserva dts) {
//        sSQL = "insert into reserva (idhabitacion,idcliente,idtrabajador,tipo_reserva,fecha_reserva,fecha_ingresa,fecha_salida,costo_alojamiento,estado)"
//                + "values (?,?,?,?,?,?,?,?,?)";
//        try {
//
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//            pst.setInt(1, dts.getIdhabitacion());
//            pst.setInt(2, dts.getIdcliente());
//            pst.setInt(3, dts.getIdtrabajador());
//            pst.setString(4, dts.getTipo_reserva());
//            pst.setDate(5, dts.getFecha_reserva());
//            pst.setDate(6, dts.getFecha_ingresa());
//            pst.setDate(7, dts.getFecha_salida());
//            pst.setDouble(8, dts.getCosto_alojamiento());
//            pst.setString(9, dts.getEstado());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                return true;
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
//    public boolean editar(vreserva dts) {
//        sSQL = "update reserva set idhabitacion=?,idcliente=?,idtrabajador=?,tipo_reserva=?,fecha_reserva=?,fecha_ingresa=?,fecha_salida=?,costo_alojamiento=?,estado=?"
//                + " where idreserva=?";
//
//        try {
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//            pst.setInt(1, dts.getIdhabitacion());
//            pst.setInt(2, dts.getIdcliente());
//            pst.setInt(3, dts.getIdtrabajador());
//            pst.setString(4, dts.getTipo_reserva());
//            pst.setDate(5, dts.getFecha_reserva());
//            pst.setDate(6, dts.getFecha_ingresa());
//            pst.setDate(7, dts.getFecha_salida());
//            pst.setDouble(8, dts.getCosto_alojamiento());
//            pst.setString(9, dts.getEstado());
//
//            pst.setInt(10, dts.getIdreserva());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                return true;
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
//    public boolean eliminar(vreserva dts) {
//        sSQL = "delete from reserva where idreserva=?";
//
//        try {
//
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//
//            pst.setInt(1, dts.getIdreserva());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                return true;
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
//    public boolean pagar(vreserva dts) {
//        sSQL = "update reserva set estado='Pagada'"
//                + " where idreserva=?";
//        //alt + 39
//
//        try {
//            PreparedStatement pst = cn.prepareStatement(sSQL);
//
//            pst.setInt(1, dts.getIdreserva());
//
//            int n = pst.executeUpdate();
//
//            if (n != 0) {
//                return true;
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
//}
