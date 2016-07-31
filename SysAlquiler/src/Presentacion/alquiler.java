package Presentacion;

import Logica.GenerarNumero;
import Logica.conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class alquiler extends javax.swing.JInternalFrame {

    public alquiler() {
        initComponents();
        txtidcliente.setVisible(false);
        lblidpersona.setVisible(false);
        txtiddanza.setVisible(false);
        lbliddanza.setVisible(false);
        txtiddepartamento.setVisible(false);
        txtidregion.setVisible(false);
        txtidprovincia.setVisible(false);
        txtidproducto.setVisible(false);
        lblidproducto.setVisible(false);
        txtidtrabajador.setVisible(false);

        inhabilitar();
        numero_comprobante();
//        mostrar("");
    }

    private conexion mysql = new conexion();  //Instanciamos a mi clase de conexion
    private Connection cn = mysql.conectar(); //Llamamos a mi Función conectar de la clase conexion
    private String sSQL = "";
    private String sSQL2 = "";
    private DefaultTableModel modelo;

    private String accion = "guardar";
    private int filas = 0;

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

    void inhabilitar() {
        cbotipo_documento.setEnabled(false);
        txtidcliente.setEnabled(false);
        txtnombre_producto.setText(null);
        cbotalla.setText(null);
        txtprecio_diario.setText(null);
        cboestado.setText(null);
        txtstock.setText(null);
//        txtcantidad.setText(null);
        txtsubtotal.setText(null);
//        txtcantidad.setEnabled(false);
//        btnquitar.setEnabled(false);
//        btncalcular.setEnabled(false);
        txtcaracteristicas.setText(null);

    }

    void calcular() {
        String precio_diario;
        String can;
        double igv = 0;
        double total = 0;
        double subtotal = 0;
        double precio;
        int cantidad;
        double imp = 0.0;

        for (int i = 0; i < tablalistado.getRowCount(); i++) {

            can = tablalistado.getValueAt(i, 2).toString();
            precio_diario = tablalistado.getValueAt(i, 3).toString();

            precio = Double.parseDouble(precio_diario);
            cantidad = Integer.parseInt(can);
            imp = precio * cantidad;
            subtotal = subtotal + imp;
            tablalistado.setValueAt(Math.rint(imp * 100) / 100, i, 4);

        }
        txttotal.setText("" + Math.rint(subtotal * 100) / 100);

    }

    void descontarstock(String idproducto, String cantidad) {

        int descontar = Integer.parseInt(cantidad);
        String stock_actual = "";
        int stock_final;

        sSQL = "SELECT * FROM producto WHERE  idproducto='" + idproducto + "'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            while (rs.next()) {

                stock_actual = rs.getString(8);//Recupera el Stock antes de la venta
            }

        } catch (Exception e) {

        }

        stock_final = Integer.parseInt(stock_actual) - descontar;//la Operacion para descontar

        sSQL2 = "UPDATE producto SET stock='" + stock_final
                + "' WHERE idproducto = '" + idproducto + "'";//Descuenta y actualiza el Stock del producto
        try {

            PreparedStatement pst = cn.prepareStatement(sSQL2);

            pst.executeUpdate();

        } catch (Exception e) {
        }

    }

    void comprobante() {//Todo lo que vas a mandar a tu Tabla Comprobante
        sSQL = "INSERT INTO comprobante(idcomprobante,idcliente,idtrabajador,total) VALUES (?,?,?,?)";

        int comprobante = Integer.parseInt(txtcomprobante.getText());
        int idcliente = Integer.parseInt(txtidcliente.getText());
        int idtrabajador = Integer.parseInt(txtidtrabajador.getText());
        double total = Double.parseDouble(txttotal.getText());

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, comprobante);
            pst.setInt(2, idcliente);
            pst.setInt(3, idtrabajador);
            pst.setDouble(4, total);

            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");
            }

        } catch (SQLException ex) {
//            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void detalleboleta() {//Todo lo que vas a mandar a tu tabla Detalle_Alquiler
        for (int i = 0; i < tablalistado.getRowCount(); i++) {

            sSQL = "INSERT INTO alquiler_detalle (idcomprobante,idproducto,cantidad,"
                    + "subtotal,fecha_alquiler,fecha_devolucion) "
                    + "VALUES (?,?,?,?,?,?)";
            int idcomprobante = Integer.parseInt(txtcomprobante.getText());
            int idproducto = Integer.parseInt(tablalistado.getValueAt(i, 0).toString());
            String cantidad = tablalistado.getValueAt(i, 3).toString();
            Double subtotal = Double.parseDouble(tablalistado.getValueAt(i, 4).toString());

            Calendar cal;
            int d, m, a;
            cal = dcfecha_alquiler.getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;

            Date fecha_alquiler = new Date(a, m, d);

            cal = dcfecha_devolucion.getCalendar();
            d = cal.get(Calendar.DAY_OF_MONTH);
            m = cal.get(Calendar.MONTH);
            a = cal.get(Calendar.YEAR) - 1900;
            Date fecha_devolucion = new Date(a, m, d);

            try {

                PreparedStatement pst = cn.prepareStatement(sSQL);
                pst.setInt(1, idcomprobante);
                pst.setInt(2, idproducto);
                pst.setString(3, cantidad);
                pst.setDouble(4, subtotal);
                pst.setDate(5, fecha_alquiler);
                pst.setDate(6, fecha_devolucion);

                pst.executeUpdate();

            } catch (SQLException ex) {
//                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void numero_comprobante() {

        String c = "";
        sSQL = "select max(idcomprobante) from comprobante";
        //String SQL="select count(*) from boleta";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            if (rs.next()) {
                c = rs.getString(1);
            }
            if (c == null) {
                txtcomprobante.setText("00000001");
            } else {
                int j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                txtcomprobante.setText(gen.serie());
            }

        } catch (SQLException ex) {
//            Logger.getLogger(Boleta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtidregion = new javax.swing.JTextField();
        txtiddepartamento = new javax.swing.JTextField();
        txtidprovincia = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        txtiddanza = new javax.swing.JTextField();
        btnbuscardanza = new javax.swing.JButton();
        lbliddanza = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombre_danza = new javax.swing.JLabel();
        cboregion = new javax.swing.JLabel();
        cbodepartamento = new javax.swing.JLabel();
        cboprovincia = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbltotalregistros = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        btnquitar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        btncalcular = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtIgv = new javax.swing.JTextField();
        btnRealizar_Venta = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblidproducto = new javax.swing.JLabel();
        txtidproducto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtcaracteristicas = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnbuscarproducto = new javax.swing.JButton();
        btnAgregarCompra = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtcomprobante = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        dcfecha_alquiler = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        dcfecha_devolucion = new com.toedter.calendar.JDateChooser();
        txtnombre_producto = new javax.swing.JLabel();
        txtprecio_diario = new javax.swing.JLabel();
        txtstock = new javax.swing.JLabel();
        cbotalla = new javax.swing.JLabel();
        cboestado = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblidpersona = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cbotipo_documento = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnbuscarcliente = new javax.swing.JButton();
        txtnombre_persona = new javax.swing.JLabel();
        txtapaterno = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JLabel();
        txtemail = new javax.swing.JLabel();
        txttelefono = new javax.swing.JLabel();
        txtamaterno = new javax.swing.JLabel();
        txtnum_documento = new javax.swing.JLabel();
        txtidcliente = new javax.swing.JTextField();
        txtidtrabajador = new javax.swing.JTextField();
        txtnombre_trabajador = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Danza", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setText("Descripcion: ");

        jLabel10.setText("Region:");

        jLabel11.setText("Departamento:");

        jLabel12.setText("Provinicia:");

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        jScrollPane3.setViewportView(txtdescripcion);

        btnbuscardanza.setText("...");
        btnbuscardanza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscardanzaActionPerformed(evt);
            }
        });

        lbliddanza.setText("iddanza");

        jLabel4.setText("Nombre Danza:");

        txtnombre_danza.setText(".");

        cboregion.setText(".");

        cbodepartamento.setText(".");

        cboprovincia.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbliddanza)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtiddanza, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidregion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtiddepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtidprovincia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtnombre_danza, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnbuscardanza))
                            .addComponent(cboprovincia, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboregion, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbodepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbliddanza)
                    .addComponent(txtiddanza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidregion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtiddepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidprovincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnombre_danza)
                            .addComponent(btnbuscardanza))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cboregion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbodepartamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboprovincia)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle del Alquiler", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lbltotalregistros.setText("jLabel13");

        jLabel3.setText("Registros:");

        jLabel22.setText("Total:");

        btnquitar.setText("Quitar");
        btnquitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquitarActionPerformed(evt);
            }
        });

        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Precio Unitario", "Cantidad", "Subtotal"
            }
        ));
        tablalistado.setName("Descripcion"); // NOI18N
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablalistadoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tablalistado);

        btncalcular.setText("Calcular");
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        jLabel26.setText("IGV:");

        btnRealizar_Venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/boton_imprimir.gif"))); // NOI18N
        btnRealizar_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizar_VentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnRealizar_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnquitar)
                            .addComponent(btncalcular)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltotalregistros)
                        .addGap(39, 39, 39))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnquitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncalcular)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltotalregistros)
                        .addGap(18, 18, 18)
                        .addComponent(btnRealizar_Venta)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la Vestimenta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblidproducto.setText("idproducto");

        jLabel5.setText("Talla:");

        jLabel6.setText("Caracteristicas:");

        txtcaracteristicas.setColumns(20);
        txtcaracteristicas.setFont(new java.awt.Font("Cambria", 0, 10)); // NOI18N
        txtcaracteristicas.setRows(5);
        jScrollPane4.setViewportView(txtcaracteristicas);

        jLabel2.setText("Nombre Vestimenta:");

        jLabel7.setText("Precio:");

        jLabel8.setText("Estado:");

        jLabel13.setText("Stock:");

        btnbuscarproducto.setText("Buscar Vestimenta");
        btnbuscarproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarproductoActionPerformed(evt);
            }
        });

        btnAgregarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/descarga.png"))); // NOI18N
        btnAgregarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCompraActionPerformed(evt);
            }
        });

        jLabel17.setText("SubTotal:");

        jLabel21.setText("Numero Comprobante:");

        jLabel24.setText("Fecha Prestamo:");

        jLabel25.setText("Fecha Devolucion:");

        txtnombre_producto.setText(".");

        txtprecio_diario.setText(".");

        txtstock.setText(".");

        cbotalla.setText(".");

        cboestado.setText(".");

        txtsubtotal.setText(".");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(lblidproducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtstock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtprecio_diario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbotalla, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboestado, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtnombre_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnbuscarproducto)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcfecha_alquiler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dcfecha_devolucion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAgregarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblidproducto)
                    .addComponent(txtidproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnbuscarproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtcomprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre_producto))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(cbotalla))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtprecio_diario))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(cboestado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtstock)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(dcfecha_alquiler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(txtsubtotal))
                                        .addGap(20, 20, 20))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dcfecha_devolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAgregarCompra))))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        lblidpersona.setText("idpersona");

        jLabel14.setText("Tipo Documento:");

        cbotipo_documento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "RUC", "CARNET EXT", "PASAPORTE", "OTROS" }));

        jLabel15.setText("Nombre:");

        jLabel16.setText("Apellidos:");

        jLabel18.setText("Direccion:");

        jLabel19.setText("Telefono:");

        jLabel20.setText("Email:");

        btnbuscarcliente.setText("....");
        btnbuscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarclienteActionPerformed(evt);
            }
        });

        txtnombre_persona.setText(".");

        txtapaterno.setText(".");

        txtdireccion.setText(".");

        txtemail.setText(".");

        txttelefono.setText(".");

        txtamaterno.setText(".");

        txtnum_documento.setText(".");

        txtidcliente.setText(".");

        txtnombre_trabajador.setText(".");

        jLabel23.setText("Colaborador:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblidpersona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidtrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtnombre_trabajador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15))
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtnombre_persona))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtapaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtamaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbotipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtnum_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel20))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtemail)
                                            .addComponent(txtdireccion)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(txttelefono)))
                        .addGap(11, 11, 11)
                        .addComponent(btnbuscarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblidpersona)
                    .addComponent(txtidcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidtrabajador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbotipo_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscarcliente)
                    .addComponent(txtnum_documento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtnombre_persona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtapaterno)
                    .addComponent(txtamaterno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtdireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtemail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txttelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtnombre_trabajador))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(61, 61, 61))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnbuscardanzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscardanzaActionPerformed
        // TODO add your handling code here:
        frmvistadanzaalquiler form = new frmvistadanzaalquiler();
        form.toFront();
        form.setVisible(true);

    }//GEN-LAST:event_btnbuscardanzaActionPerformed

    private void btnbuscarproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarproductoActionPerformed
        // TODO add your handling code here:
        frmvistaproductoboleta form = new frmvistaproductoboleta();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbuscarproductoActionPerformed

    private void btnbuscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarclienteActionPerformed
        // TODO add your handling code here:
        frmvistaclienteboleta form = new frmvistaclienteboleta();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbuscarclienteActionPerformed

    private void btnAgregarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCompraActionPerformed

//        modelo = (DefaultTableModel) this.tablalistado.getModel();
//
//        double sub_total = Double.parseDouble(txtcantidad.getText()) * Double.parseDouble(txtprecio_diario.getText());
//
//        int cantidad = Integer.parseInt(txtcantidad.getText());
//        int stock = Integer.parseInt(txtstock.getText());
//
//        if (stock > cantidad) {
//
//            String descripcion[] = new String[5];
//
//            descripcion[0] = txtidproducto.getText();
//            descripcion[1] = txtnombre_producto.getText();
//            descripcion[2] = txtcantidad.getText();
//            descripcion[3] = txtprecio_diario.getText();
//            descripcion[4] = String.valueOf(sub_total);
//            modelo.addRow(descripcion);
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Stock No Disponible");
//        }
//        inhabilitar();
//        btnquitar.setEnabled(true);
//        btncalcular.setEnabled(true);
//        DefaultTableModel modelo = (DefaultTableModel) this.tablalistado.getModel();
//        
//        double suma = 0;
//
//        modelo.addRow(new Object[filas]);
//        int stock = Integer.parseInt(txtstock.getText());
//        int cantidad = Integer.parseInt(txtcantidad.getText());
//
//        if (!txtcantidad.getText().equals("0")) {
//            if (stock > cantidad) {
//
//                for (int x = 0; x < this.tablalistado.getColumnCount() - 1; x++) {
//
//                    modelo.setValueAt(this.txtitem.getText(), filas, 0);
//                    modelo.setValueAt(this.txtidproducto.getText(), filas, 1);
//                    modelo.setValueAt(this.txtnombre_producto.getText(), filas, 2);
//                    modelo.setValueAt(this.txtprecio_diario.getText(), filas, 3);
//                    modelo.setValueAt(this.txtcantidad.getText(), filas, 4);
//                    modelo.setValueAt(this.txtsubtotal.getText(), filas, 5);
//                    
//
//                }
//                suma = suma + Integer.parseInt(txtsubtotal.getText());
//                txttotal.setText(Double.toString(suma));
//                filas++;
//            }
//        } else {
//            
//            JOptionPane.showMessageDialog(this, "Error al registrar el articulo");
//
//        }
//        if (!txtcantidad.getText().equals("0")) {
//
//            if (stock > cantidad) {
//                if (conexion.registrar_producto(txtcomprobante.getText(), txtidproducto.getText(), txtcantidad.getText(), txtsubtotal.getText())) {
//                    JOptionPane.showMessageDialog(this, "El articulo se registro con exito");
//                    txtcantidad.setText("0");
//                    txtsubtotal.setText("0");
////                    txtdescuento.setText("");
//                } else {
//                    JOptionPane.showMessageDialog(this, "Error al registrar el articulo");
//                }
//            } else {
//                JOptionPane.showMessageDialog(this, "El stock del articulo no soporta la venta por favor actualize en stock");
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "La cantidad no es valida");
//        }
//
//        String[] columnas = {"Numero_factura", "Codigo articulo", "Descripcion", "Cantidad", "SubTotal"};
//
//        tablalistado = conexion.datos_detallefactura(txtcomprobante.getText());
//
//        DefaultTableModel detalle_alquiler = new DefaultTableModel(datostabla, columnas);
//        jTable1.setModel(detalle_alquiler);
//
//        Double totalFactura = conexion.total_factura(txtcomprobante.getText());
//
//        txttotal.setText(totalFactura.toString());
//
//        vigv iva = new vigv(Double.parseDouble(txttotal.getText()));
//
//        iva_art.setText(iva.calcular_iva().toString());
    }//GEN-LAST:event_btnAgregarCompraActionPerformed

    private void btnquitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquitarActionPerformed

        modelo = (DefaultTableModel) tablalistado.getModel();
        int a = tablalistado.getSelectedRow();
        if (a < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla");
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea Eliminar el registro? ");
            if (JOptionPane.OK_OPTION == confirmar) {
                modelo.removeRow(a);
//                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        }
    }//GEN-LAST:event_btnquitarActionPerformed

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMouseClicked

    }//GEN-LAST:event_tablalistadoMouseClicked

    private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed

        calcular();

//        double total = 0;
//        double IGV = 0;
//        double a;
//        double b = 0;
//        for (int i = 0; i < modelo.getRowCount(); i++) {
//            String Calculo = String.valueOf(modelo.getValueAt(i, 4));
////            String Calculo = String.valueOf(modelo.getValueAt(i, 3));
//            a = Double.parseDouble(Calculo);
//            b = b + a;
//            if (i == modelo.getRowCount() - 1) {
//                txttotal.setText("" + b);
//                IGV = b * 0.19;
//                total = b;
//            }
//        }
//
//        txtIgv.setText(IGV + "");
//
//        txttotal.setText("" + (total));

    }//GEN-LAST:event_btncalcularActionPerformed

    private void btnRealizar_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizar_VentaActionPerformed
        // TODO add your handling code here:

        if ((txtidcliente.getText().equals("")) || (txttotal.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Ingrese cliente, producto o realice operacion");
        } else {
            String idproducto = "", cantidad = "";
            for (int i = 0; i < alquiler.tablalistado.getRowCount(); i++) {

                idproducto = alquiler.tablalistado.getValueAt(i, 0).toString();
                cantidad = alquiler.tablalistado.getValueAt(i, 3).toString();

                descontarstock(idproducto, cantidad);//Descontamos 

            }
            
            comprobante();
            detalleboleta();
            inhabilitar();

            DefaultTableModel modelo = (DefaultTableModel) tablalistado.getModel();
            int a = tablalistado.getRowCount() - 1;
            int i;
            for (i = a; i >= 0; i--) {
                modelo.removeRow(i);
            }
//            numero_comprobante();
        }

//
//        accion = "guardar";
//
//        //COMPROBANTE
//        vcomprobante dts = new vcomprobante();
//        fcomprobante func = new fcomprobante();
//
//        dts.setIdcliente(Integer.parseInt(txtidcliente.getText()));
//        dts.setIdtrabajador(Integer.parseInt(txtidtrabajador.getText()));
//        dts.setTotal(Double.parseDouble(txttotal.getText()));
//
//        //ALQUILER_DETALLE
//        valquiler_detalle dts1 = new valquiler_detalle();
//        falquiler_detalle func1 = new falquiler_detalle();
//
//        dts1.setIdproducto(Integer.parseInt(txtidproducto.getText()));
//        dts1.setCantidad((txtcantidad.getText()));
//        dts1.setSubtotal(Double.parseDouble(txtsubtotal.getText()));
//
//        Calendar cal;
//        int d, m, a;
//        cal = dcfecha_alquiler.getCalendar();
//        d = cal.get(Calendar.DAY_OF_MONTH);
//        m = cal.get(Calendar.MONTH);
//        a = cal.get(Calendar.YEAR) - 1900;
//        dts1.setFecha_alquiler(new Date(a, m, d));
//
//        cal = dcfecha_devolucion.getCalendar();
//        d = cal.get(Calendar.DAY_OF_MONTH);
//        m = cal.get(Calendar.MONTH);
//        a = cal.get(Calendar.YEAR) - 1900;
//        dts1.setFecha_devolucion(new Date(a, m, d));
//
//        if (accion.equals("guardar")) {
//            if (func.insertar(dts) && func1.insertar(dts1)) {
//                JOptionPane.showMessageDialog(rootPane, "Se Registro Correctamente");
//
//                inhabilitar();
//
//            }
//
//        }
//        if (accion.equals("guardar")) {
//            if (func1.insertar(dts1)) {
//                JOptionPane.showMessageDialog(rootPane, "Se Registro Correctamente");
//
//                inhabilitar();
//
//            }
//        }

    }//GEN-LAST:event_btnRealizar_VentaActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(alquiler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new alquiler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCompra;
    private javax.swing.JButton btnRealizar_Venta;
    private javax.swing.JButton btnbuscarcliente;
    private javax.swing.JButton btnbuscardanza;
    private javax.swing.JButton btnbuscarproducto;
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btnquitar;
    public static javax.swing.JLabel cbodepartamento;
    public static javax.swing.JLabel cboestado;
    public static javax.swing.JLabel cboprovincia;
    public static javax.swing.JLabel cboregion;
    public static javax.swing.JLabel cbotalla;
    public static javax.swing.JComboBox<String> cbotipo_documento;
    private com.toedter.calendar.JDateChooser dcfecha_alquiler;
    private com.toedter.calendar.JDateChooser dcfecha_devolucion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbliddanza;
    private javax.swing.JLabel lblidpersona;
    private javax.swing.JLabel lblidproducto;
    private javax.swing.JLabel lbltotalregistros;
    public static javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtIgv;
    public static javax.swing.JLabel txtamaterno;
    public static javax.swing.JLabel txtapaterno;
    public static javax.swing.JTextArea txtcaracteristicas;
    private javax.swing.JTextField txtcomprobante;
    public static javax.swing.JTextArea txtdescripcion;
    public static javax.swing.JLabel txtdireccion;
    public static javax.swing.JLabel txtemail;
    public static javax.swing.JTextField txtidcliente;
    public static javax.swing.JTextField txtiddanza;
    public static javax.swing.JTextField txtiddepartamento;
    public static javax.swing.JTextField txtidproducto;
    public static javax.swing.JTextField txtidprovincia;
    public static javax.swing.JTextField txtidregion;
    public static javax.swing.JTextField txtidtrabajador;
    public static javax.swing.JLabel txtnombre_danza;
    public static javax.swing.JLabel txtnombre_persona;
    public static javax.swing.JLabel txtnombre_producto;
    public static javax.swing.JLabel txtnombre_trabajador;
    public static javax.swing.JLabel txtnum_documento;
    public static javax.swing.JLabel txtprecio_diario;
    public static javax.swing.JLabel txtstock;
    private javax.swing.JLabel txtsubtotal;
    public static javax.swing.JLabel txttelefono;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
