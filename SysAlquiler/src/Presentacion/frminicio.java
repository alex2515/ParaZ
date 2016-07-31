package Presentacion;

import Logica.ImagenFondo;
import javax.swing.UIManager;

public class frminicio extends javax.swing.JFrame {
    
    public frminicio() {
        
        try {
//            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
//SOLO FALTA ACTIVAR
//            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
        } catch (Exception e) {
        }
        initComponents();
        this.setExtendedState(frminicio.MAXIMIZED_BOTH);
        this.setTitle("Vestuarios Perú");
//        this.escritorio.setBorder(new ImagenFondo());
        lblidpersona.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        lblidpersona = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lblapaterno = new javax.swing.JLabel();
        lblamaterno = new javax.swing.JLabel();
        lblacceso = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnusisreserva = new javax.swing.JMenu();
        mnuarchivo = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem1 = new javax.swing.JMenuItem();
        mnureservas = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        contentMenuItem = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        mnuconsultas = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        mnuconfiguraciones = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        mnuayuda = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnusalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(102, 255, 255));

        lblidpersona.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblidpersona.setForeground(new java.awt.Color(255, 255, 255));
        lblidpersona.setText("jLabel1");
        escritorio.add(lblidpersona);
        lblidpersona.setBounds(10, 90, 130, 15);

        lblnombre.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblnombre.setForeground(new java.awt.Color(255, 255, 255));
        lblnombre.setText("jLabel2");
        escritorio.add(lblnombre);
        lblnombre.setBounds(10, 30, 130, 15);

        lblapaterno.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblapaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblapaterno.setText("jLabel3");
        escritorio.add(lblapaterno);
        lblapaterno.setBounds(10, 50, 130, 15);

        lblamaterno.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblamaterno.setForeground(new java.awt.Color(255, 255, 255));
        lblamaterno.setText("jLabel4");
        escritorio.add(lblamaterno);
        lblamaterno.setBounds(10, 70, 130, 15);

        lblacceso.setFont(new java.awt.Font("Cambria", 3, 12)); // NOI18N
        lblacceso.setForeground(new java.awt.Color(255, 255, 255));
        lblacceso.setText("jLabel7");
        escritorio.add(lblacceso);
        lblacceso.setBounds(10, 10, 130, 15);

        menuBar.setForeground(new java.awt.Color(255, 255, 255));

        mnusisreserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/house-304005_640_converted.png"))); // NOI18N
        mnusisreserva.setMnemonic('f');
        mnusisreserva.setText("* SysAlquiler *");
        mnusisreserva.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        menuBar.add(mnusisreserva);

        mnuarchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Archivo_converted.png"))); // NOI18N
        mnuarchivo.setMnemonic('e');
        mnuarchivo.setText("Archivo");
        mnuarchivo.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        cutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/nuevo empleado.png"))); // NOI18N
        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Danzas");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        mnuarchivo.add(cutMenuItem);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        copyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/ropa.png"))); // NOI18N
        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Vestuarios");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        mnuarchivo.add(copyMenuItem);

        aboutMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        aboutMenuItem1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        aboutMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Proveed.png"))); // NOI18N
        aboutMenuItem1.setMnemonic('a');
        aboutMenuItem1.setText("Clientes");
        aboutMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItem1ActionPerformed(evt);
            }
        });
        mnuarchivo.add(aboutMenuItem1);

        menuBar.add(mnuarchivo);

        mnureservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/al_converted.png"))); // NOI18N
        mnureservas.setMnemonic('h');
        mnureservas.setText("Alquiler");
        mnureservas.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/calculadora.png"))); // NOI18N
        jMenuItem1.setText("Boleta");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnureservas.add(jMenuItem1);

        contentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        contentMenuItem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        contentMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/devolucion.png"))); // NOI18N
        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Devolución");
        contentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contentMenuItemActionPerformed(evt);
            }
        });
        mnureservas.add(contentMenuItem);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/factura_converted (1).png"))); // NOI18N
        jMenuItem9.setText("Factura");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        mnureservas.add(jMenuItem9);

        menuBar.add(mnureservas);

        mnuconsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/busqueda.png"))); // NOI18N
        mnuconsultas.setText("Consultas");
        mnuconsultas.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        mnuconsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuconsultasActionPerformed(evt);
            }
        });

        jMenuItem5.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem5.setText("ConsultaTrabajador");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        mnuconsultas.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem6.setText("Consulta Danza");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        mnuconsultas.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem7.setText("Consulta Vesturarios");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        mnuconsultas.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem8.setText("Consulta Cliente");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        mnuconsultas.add(jMenuItem8);

        jMenuItem10.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem10.setText("Reporte de Venta");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        mnuconsultas.add(jMenuItem10);

        menuBar.add(mnuconsultas);

        mnuconfiguraciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/engranaje_converted.png"))); // NOI18N
        mnuconfiguraciones.setText("Configuraciones");
        mnuconfiguraciones.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/trabajadores.png"))); // NOI18N
        jMenuItem2.setText("Usuarios y Accesos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuconfiguraciones.add(jMenuItem2);

        menuBar.add(mnuconfiguraciones);

        mnuayuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Ayuda.png"))); // NOI18N
        mnuayuda.setText("Ayuda");
        mnuayuda.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem3.setText("Acerca de...");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuayuda.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        jMenuItem4.setText("Ayuda");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        mnuayuda.add(jMenuItem4);

        menuBar.add(mnuayuda);

        mnusalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Salir.png"))); // NOI18N
        mnusalir.setText("Salir");
        mnusalir.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        mnusalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnusalirMouseClicked(evt);
            }
        });
        menuBar.add(mnusalir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        frmproducto form = new frmproducto();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);

    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frmtrabajador form = new frmtrabajador();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void mnusalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnusalirMouseClicked
        
        this.dispose();
    }//GEN-LAST:event_mnusalirMouseClicked

    private void contentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contentMenuItemActionPerformed
        
        frmdevoluciones form = new frmdevoluciones();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
//        alquiler form = new alquiler();
//        escritorio.add(form);
//        form.toFront();
//        form.setVisible(true);
//        alquiler.txtidtrabajador.setText(lblidpersona.getText());
//        alquiler.txtnombre_trabajador.setText(lblnombre.getText() + " " + lblapaterno.getText());
//        frmalquiler.idusuario = Integer.parseInt(lblidpersona.getText());
    }//GEN-LAST:event_contentMenuItemActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        frmacercade form = new frmacercade();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void mnuconsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuconsultasActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_mnuconsultasActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        frmdanza form = new frmdanza();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_cutMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:Boleta form = new Boleta();
        frmalquilerboleta form = new frmalquilerboleta();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void aboutMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItem1ActionPerformed
        // TODO add your handling code here:       
        frmcliente form = new frmcliente();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_aboutMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        frmvistadanza form = new frmvistadanza();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        frmvistaclienteboleta form = new frmvistaclienteboleta();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        frmvistaproductoboleta form = new frmvistaproductoboleta();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        frmvistatrabajador form = new frmvistatrabajador();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        frmalquilerfactura form = new frmalquilerfactura();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        frmreporte form = new frmreporte();
        frminicio.escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed
    
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
    private javax.swing.JMenuItem aboutMenuItem1;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public static javax.swing.JLabel lblacceso;
    public static javax.swing.JLabel lblamaterno;
    public static javax.swing.JLabel lblapaterno;
    public static javax.swing.JLabel lblidpersona;
    public static javax.swing.JLabel lblnombre;
    private javax.swing.JMenuBar menuBar;
    public static javax.swing.JMenu mnuarchivo;
    private javax.swing.JMenu mnuayuda;
    public static javax.swing.JMenu mnuconfiguraciones;
    private javax.swing.JMenu mnuconsultas;
    private javax.swing.JMenu mnureservas;
    private javax.swing.JMenu mnusalir;
    private javax.swing.JMenu mnusisreserva;
    // End of variables declaration//GEN-END:variables

}
