package Presentacion;

import Logica.conexion;
import Logica.fproducto;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmvistaproductofactura extends javax.swing.JInternalFrame {

    public frmvistaproductofactura() {
        initComponents();
        mostrar("");
    }

    private conexion mysql = new conexion();  //Instanciamos a mi clase de conexion
    private Connection cn = mysql.conectar(); //Llamamos a mi Función conectar de la clase conexion

    void ocultar_columnas() {
//        tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
//        tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
//        tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
//
//        tablalistado.getColumnModel().getColumn(7).setMaxWidth(0);
//        tablalistado.getColumnModel().getColumn(7).setMinWidth(0);
//        tablalistado.getColumnModel().getColumn(7).setPreferredWidth(0);
//        
//        tablalistado.getColumnModel().getColumn(9).setMaxWidth(0);
//        tablalistado.getColumnModel().getColumn(9).setMinWidth(0);
//        tablalistado.getColumnModel().getColumn(9).setPreferredWidth(0);
//        
//        tablalistado.getColumnModel().getColumn(11).setMaxWidth(0);
//        tablalistado.getColumnModel().getColumn(11).setMinWidth(0);
//        tablalistado.getColumnModel().getColumn(11).setPreferredWidth(0);
    }

    void mostrar(String buscar) {
        try {
            DefaultTableModel modelo;
            fproducto func = new fproducto();
            modelo = func.mostrar(buscar);

            tablalistado.setModel(modelo);
            ocultar_columnas();
            lbltotalregistros.setText("Total Registros " + Integer.toString(func.totalregistros));

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        lbltotalregistros = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnbuscar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Vestimenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Numero de Documento:");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablalistadoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablalistadoMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablalistado);

        lbltotalregistros.setText("jLabel13");

        jLabel3.setText("Registros:");

        btnbuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnbuscar.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/buscar.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltotalregistros)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltotalregistros)
                    .addComponent(jLabel3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMouseClicked
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) frmalquilerfactura.tablalistado.getModel();
            String[] dato = new String[5];

            int fila = tablalistado.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
            } else {

                String idproducto = tablalistado.getValueAt(fila, 0).toString();
                String nombre_producto = tablalistado.getValueAt(fila, 1).toString();
                String precio_diario = tablalistado.getValueAt(fila, 13).toString();
                String stock = tablalistado.getValueAt(fila, 15).toString();

                int c = 0;
                int j = 0;
                String cant = JOptionPane.showInputDialog("Ingrese cantidad");
                if ((cant.equals("")) || (cant.equals("0"))) {

                    JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");

                } else {

                    int canting = Integer.parseInt(cant);
//                    int comp = Integer.parseInt(comparar(idproducto));
//                    if (canting > comp) {
//                        JOptionPane.showMessageDialog(this, "Ud. no cuenta con el stock apropiado");
//                    } else {

//                        for (int i = 0; i < frmalquiler.tablalistado.getRowCount(); i++) {
//                            Object com = frmalquiler.tablalistado.getValueAt(i, 0);
//                            if (idproducto.equals(com)) {
//                                j = i;
//                                frmalquiler.tablalistado.setValueAt(cant, i, 3);
//                                c = c + 1;
//                            }
//                        }
                    if (c == 0) {
                        dato[0] = idproducto;
                        dato[1] = nombre_producto;
                        dato[2] = precio_diario;
                        dato[3] = cant;
                        modelo.addRow(dato);
                        alquiler.tablalistado.setModel(modelo);

                    }
//                    }

                }

            }
        } catch (Exception e) {
        }
        this.dispose();

//        btnguardar.setText("Editar");
//        habilitar();
//        btneliminar.setEnabled(true);
//        accion = "editar";
//
//        int fila = tablalistado.rowAtPoint(evt.getPoint());
//
//        txtidproducto.setText(tablalistado.getValueAt(fila, 0).toString());
//        
//        txtnombre_producto.setText(tablalistado.getValueAt(fila, 1).toString());
//        txtiddanza.setText(tablalistado.getValueAt(fila, 2).toString());
//        txtnombre_danza.setText(tablalistado.getValueAt(fila, 3).toString());
//        txtidregion.setText(tablalistado.getValueAt(fila, 4).toString());
//        cboregion.setSelectedItem(tablalistado.getValueAt(fila, 5).toString());
//        txtiddepartamento.setText(tablalistado.getValueAt(fila, 6).toString());
//        cbodepartamento.setSelectedItem(tablalistado.getValueAt(fila, 7).toString());
//        txtidprovincia.setText(tablalistado.getValueAt(fila, 8).toString());
//        cboprovincia.setSelectedItem(tablalistado.getValueAt(fila, 9).toString());
//        txtdescripcion.setText(tablalistado.getValueAt(fila, 10).toString());        
//        cbotalla.setSelectedItem(tablalistado.getValueAt(fila, 11).toString());
//        txtcaracteristicas.setText(tablalistado.getValueAt(fila, 12).toString());
//        txtprecio_diario.setText(tablalistado.getValueAt(fila, 13).toString());
//        cboestado.setSelectedItem(tablalistado.getValueAt(fila, 14).toString());
//        txtstock.setText(tablalistado.getValueAt(fila, 15).toString());
    }//GEN-LAST:event_tablalistadoMouseClicked

    private void tablalistadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMousePressed
        // TODO add your handling code here:
//        try {
//            DefaultTableModel modelo = (DefaultTableModel) frmalquiler.tablalistado.getModel();
//            String[] dato = new String[5];
//
//            int fila = tablalistado.getSelectedRow();
//
//            if (fila == -1) {
//                JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");
//            } else {
//
//                String idproducto = tablalistado.getValueAt(fila, 0).toString();
//                String nombre_producto = tablalistado.getValueAt(fila, 1).toString();
//                String precio_diario = tablalistado.getValueAt(fila, 13).toString();
//                String stock = tablalistado.getValueAt(fila, 15).toString();
//
//                int c = 0;
//                int j = 0;
//                String cant = JOptionPane.showInputDialog("Ingrese cantidad");
//                if ((cant.equals("")) || (cant.equals("0"))) {
//
//                    JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");
//
//                } else {
//
//                    int canting = Integer.parseInt(cant);
////                    int comp = Integer.parseInt(comparar(idproducto));
////                    if (canting > comp) {
////                        JOptionPane.showMessageDialog(this, "Ud. no cuenta con el stock apropiado");
////                    } else {
//
////                        for (int i = 0; i < frmalquiler.tablalistado.getRowCount(); i++) {
////                            Object com = frmalquiler.tablalistado.getValueAt(i, 0);
////                            if (idproducto.equals(com)) {
////                                j = i;
////                                frmalquiler.tablalistado.setValueAt(cant, i, 3);
////                                c = c + 1;
////                            }
////                        }
//                    if (c == 0) {
//                        dato[0] = idproducto;
//                        dato[1] = nombre_producto;
//                        dato[2] = precio_diario;
//                        dato[3] = cant;
//                        modelo.addRow(dato);
//                        frmalquiler.tablalistado.setModel(modelo);
//
//                    }
////                    }
//
//                }
//
//            }
//        } catch (Exception e) {
//        }

//        if (evt.getClickCount() == 2) {
//
//            int fila = tablalistado.getSelectedRow();
//
//            frmalquiler.txtidproducto.setText(tablalistado.getValueAt(fila, 0).toString());
//            frmalquiler.txtnombre_producto.setText(tablalistado.getValueAt(fila, 1).toString());
//            frmalquiler.cbotalla.setText(tablalistado.getValueAt(fila, 11).toString());
////            frmalquiler.cbotalla.setSelectedItem(tablalistado.getValueAt(fila, 11).toString());
//            frmalquiler.txtcaracteristicas.setText(tablalistado.getValueAt(fila, 12).toString());
//            frmalquiler.txtprecio_diario.setText(tablalistado.getValueAt(fila, 13).toString());
//            frmalquiler.cboestado.setText(tablalistado.getValueAt(fila, 14).toString());
////            frmalquiler.cboestado.setSelectedItem(tablalistado.getValueAt(fila, 14).toString());
//            frmalquiler.txtstock.setText(tablalistado.getValueAt(fila, 15).toString());
//            frmalquiler.txtcantidad.setEnabled(true);
//            this.dispose();
//
//        }

    }//GEN-LAST:event_tablalistadoMousePressed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        // TODO add your handling code here:
        mostrar(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        mostrar(txtbuscar.getText());
    }//GEN-LAST:event_btnbuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
