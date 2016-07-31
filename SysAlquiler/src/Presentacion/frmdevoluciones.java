/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.conexion;
import Logica.fcliente;
import Logica.fdevolucionesboleta;
import static Presentacion.frmalquilerboleta.txtnumbol;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ANDRES
 */
public class frmdevoluciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form Interfaz_devoluciones
     */
    private Object[][] datostabla;
    private conexion mysql = new conexion();  //Instanciamos a mi clase de conexion
    private Connection cn = mysql.conectar(); //Llamamos a mi Función conectar de la clase conexion
    private String sSQL = "";
    private String sSQL2 = "";
    private String sSQL3 = "";
//    fexistencias exis = new fexistencias();

    public frmdevoluciones() {
        initComponents();
        mostrar("");

        txtidcomprobante.setEnabled(false);
        txtidproducto.setEnabled(false);
        txtnombre_producto.setEnabled(false);
        txtcantidad.setEnabled(false);
        cboestado.setEnabled(false);
        Date hoy = new Date();
        txtfecha.setText(hoy.getDate() + "/" + (hoy.getMonth() + 1) + "/" + (hoy.getYear() + 1900));
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fdevolucionesboleta func = new fdevolucionesboleta();
            modelo = func.mostrar(buscar);

            tablalistado.setModel(modelo);
//            ocultar_columnas();
//            lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }

    void actualizarstock(String idproducto, String cantidad) {

        int descontar = Integer.parseInt(cantidad);
        String cap = "";
        int desfinal;

        sSQL = "SELECT * FROM producto WHERE  idproducto='" + idproducto + "'";
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {
                cap = rs.getString(8);
            }

        } catch (Exception e) {

        }

        desfinal = Integer.parseInt(cap) + descontar;

        sSQL2 = "UPDATE producto SET stock='" + desfinal + "' WHERE idproducto = '" + idproducto + "'";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL2);
            pst.executeUpdate();
        } catch (Exception e) {
        }

    }

    void cambiarestado(String idcomprobante, String idproducto, String estado) {

        sSQL3 = "UPDATE detalleboleta SET estado='" + estado + "' WHERE idcomprobante ='" + idcomprobante + "' and idproducto='" + idproducto + "' ";
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL3);
            pst.executeUpdate();

        } catch (Exception e) {

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtbuscarfactura = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnrecepcionar = new javax.swing.JButton();
        txtcantidad = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombre_producto = new javax.swing.JTextField();
        cboestado = new javax.swing.JComboBox<>();
        txtidcomprobante = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setTitle("Devoluciones");

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel1.setText("Número de Boleta:");

        txtbuscarfactura.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        txtbuscarfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarfacturaActionPerformed(evt);
            }
        });
        txtbuscarfactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarfacturaKeyReleased(evt);
            }
        });

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        tablalistado.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablalistadoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablalistado);

        btnrecepcionar.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnrecepcionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/save1.png"))); // NOI18N
        btnrecepcionar.setText("Registrar");
        btnrecepcionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecepcionarActionPerformed(evt);
            }
        });

        txtcantidad.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N

        txtfecha.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel4.setText("Fecha:");

        jLabel5.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel5.setText("Cantidad:");

        btnsalir.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel2.setText("Código artículo:");

        txtidproducto.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel3.setText("Estado:");

        jLabel7.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel7.setText("Nombre:");

        txtnombre_producto.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N

        cboestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Recepcionado", "Alquilado" }));

        txtidcomprobante.setText("N");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnrecepcionar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(cboestado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel5))
                                    .addGap(40, 40, 40)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtnombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel4)
                                    .addGap(23, 23, 23)
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtidcomprobante))
                        .addGap(0, 351, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(txtidcomprobante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cboestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnombre_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnrecepcionar)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Cambria", 0, 12)); // NOI18N
        jLabel6.setText("Detalle del Alquiler Boleta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtbuscarfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscarfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarfacturaActionPerformed


    }//GEN-LAST:event_txtbuscarfacturaActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
//     
//        String[] columnas = {"Numero_factura","Codigo articulo","Descripcion","Cantidad"};
//        datostabla = exis.datos_detallefactura(txtbuscarfactura.getText());
//        DefaultTableModel datosta = new DefaultTableModel(datostabla,columnas);
//        tablalistado.setModel(datosta);
//            
//            


    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnrecepcionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecepcionarActionPerformed

        if ((txtidproducto.getText().equals("")) || (txtcantidad.getText().equals(""))) {
            
            JOptionPane.showMessageDialog(this, "Tener el Numero de Comprobante");
            
        } else {
            
            String idproducto = "", cantidad = "", estado = "", idcomprobante = "";
            
            
            int fila = tablalistado.getSelectedRow();
           
            idcomprobante = tablalistado.getValueAt(fila, 0).toString();
                idproducto = tablalistado.getValueAt(fila, 1).toString();
                cantidad = tablalistado.getValueAt(fila, 3).toString();
//                estado = tablalistado.getValueAt(fila, 6).toString();

                int seleccionado = cboestado.getSelectedIndex();
                estado = ((String) cboestado.getItemAt(seleccionado));
                actualizarstock(idproducto, cantidad);
                cambiarestado(idcomprobante, idproducto, estado);

//            for (int i = 0; i < tablalistado.getRowCount(); i++) {
//
//                idcomprobante = tablalistado.getValueAt(i, 0).toString();
//                idproducto = tablalistado.getValueAt(i, 1).toString();
//                cantidad = tablalistado.getValueAt(i, 3).toString();
////                estado = tablalistado.getValueAt(i, 6).toString();
//
//                int seleccionado = cboestado.getSelectedIndex();
//                estado = ((String) cboestado.getItemAt(seleccionado));
//
//                actualizarstock(idproducto, cantidad);
//            }
//            cambiarestado(idcomprobante, idproducto, estado);
//   if(!txtidproducto.getText().equals("") && !txtfecha.getText().equals("") && !txtidproducto.getText().equals("") && !txtcantidad.getText().equals(""))
//    {
//        if(exis.devolucion(txtbuscarfactura.getText(), txtidproducto.getText(), txtmotivo.getText(), txtfecha.getText(), txtcantidad.getText()))
//    
//        {
//            JOptionPane.showMessageDialog(this, "La devolucion se realizo con exito");
//            exis.update_stock(txtcantidad.getText(), txtidproducto.getText());
//            JOptionPane.showMessageDialog(this, "se actualizo el stock del articulo");
//                txtidproducto.setText("");
//                txtmotivo.setText("");                
//                txtcantidad.setText("");
//                txtfecha.setText("");
//        }
//        else
//         {
//        JOptionPane.showMessageDialog(this, "Error al registrar la devolucion");
//         }
//         }
//        else
//         {
//          JOptionPane.showMessageDialog(this, "Hay campos obligatorios");  
//         }

            txtidcomprobante.setText(null);
            txtidproducto.setText(null);
            txtnombre_producto.setText(null);
            txtcantidad.setText(null);
            mostrar("");
    }//GEN-LAST:event_btnrecepcionarActionPerformed
    }
    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {

            int fila = tablalistado.getSelectedRow();

            txtidcomprobante.setText(tablalistado.getValueAt(fila, 0).toString());
            txtidproducto.setText(tablalistado.getValueAt(fila, 1).toString());
            txtnombre_producto.setText(tablalistado.getValueAt(fila, 2).toString());
            txtcantidad.setText(tablalistado.getValueAt(fila, 3).toString());
            cboestado.setSelectedItem(tablalistado.getValueAt(fila, 6).toString());

        }
    }//GEN-LAST:event_tablalistadoMousePressed

    private void txtbuscarfacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarfacturaKeyReleased
        mostrar(txtbuscarfactura.getText());
    }//GEN-LAST:event_txtbuscarfacturaKeyReleased
    private Connection connection = new conexion().conectar();    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frminicio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnrecepcionar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cboestado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtbuscarfactura;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtfecha;
    private javax.swing.JLabel txtidcomprobante;
    private javax.swing.JTextField txtidproducto;
    private javax.swing.JTextField txtnombre_producto;
    // End of variables declaration//GEN-END:variables
}
