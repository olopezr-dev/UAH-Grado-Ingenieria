
package poo.PL2.Interface;

import javax.swing.JOptionPane;
import poo.PL2.Clases.AuthService;
import poo.PL2.Clases.Cliente;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.SesionErrorHandler;
import poo.PL2.Clases.UsuarioValidador;
import poo.PL2.Clases.ValidadorUtilidades;

public class ModificarDatos extends javax.swing.JFrame {

    private final Cliente cliente;
    
    public ModificarDatos(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.cliente = cliente;
        bloquearCampos();
        cargarDatosCliente();
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }
    
    private void cargarDatosCliente() {
        jTextFieldNombre.setText(cliente.getNombre());
        jFormatedTextFieldTelefono.setValue(cliente.getTelefono());
        jTextFieldCorreo.setText(cliente.getCorreo());
        jTextFieldContrasena.setText(cliente.getContrasena());
        jCheckBoxVip.setSelected(cliente.isVip());
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCorreo = new javax.swing.JTextField();
        jCheckBoxVip = new javax.swing.JCheckBox();
        jTextFieldContrasena = new javax.swing.JTextField();
        jButtonVolver = new javax.swing.JButton();
        jButtonGuardarCambios = new javax.swing.JButton();
        jFormatedTextFieldTelefono = new javax.swing.JFormattedTextField();
        jButtonDireccion = new javax.swing.JButton();
        jButtonMetodoPago = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MIS DATOS");

        jLabel2.setText("Nombre");

        jTextFieldNombre.setEditable(false);
        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Teléfono");

        jLabel6.setText("VIP");

        jLabel7.setText("Correo");

        jLabel8.setText("Contraseña");

        jTextFieldCorreo.setEditable(false);
        jTextFieldCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoActionPerformed(evt);
            }
        });

        jCheckBoxVip.setText("SÍ");
        jCheckBoxVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVipActionPerformed(evt);
            }
        });

        jTextFieldContrasena.setEditable(false);
        jTextFieldContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldContrasenaActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonGuardarCambios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGuardarCambios.setText("GUARDAR CAMBIOS");
        jButtonGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarCambiosActionPerformed(evt);
            }
        });

        jFormatedTextFieldTelefono.setEditable(false);
        jFormatedTextFieldTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormatedTextFieldTelefonoActionPerformed(evt);
            }
        });

        jButtonDireccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDireccion.setText("DIRECCIÓN");
        jButtonDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDireccionActionPerformed(evt);
            }
        });

        jButtonMetodoPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonMetodoPago.setText("MÉTODO DE PAGO");
        jButtonMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMetodoPagoActionPerformed(evt);
            }
        });

        jButtonModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonModificar.setText("MODIFICAR");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonDireccion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                        .addComponent(jButtonMetodoPago))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonVolver))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldContrasena, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                    .addComponent(jCheckBoxVip)
                                    .addComponent(jTextFieldNombre)
                                    .addComponent(jTextFieldCorreo)
                                    .addComponent(jFormatedTextFieldTelefono))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jButtonGuardarCambios)))))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDireccion)
                    .addComponent(jButtonMetodoPago))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormatedTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jCheckBoxVip))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVolver)
                    .addComponent(jButtonGuardarCambios)
                    .addComponent(jButtonModificar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoActionPerformed
        // TODO add your handling code here:;
        
    }//GEN-LAST:event_jTextFieldCorreoActionPerformed

    private void jCheckBoxVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVipActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jCheckBoxVipActionPerformed

    private void jTextFieldContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldContrasenaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextFieldContrasenaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new PortalCliente(cliente)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        
        String nombreNuevo = jTextFieldNombre.getText();
        String telefonoNuevo = jFormatedTextFieldTelefono.getText();
        String contrasenaNueva = jTextFieldContrasena.getText();
        
        if (telefonoNuevo.isBlank()){
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.TELEFONO_VACIO); 
            return;
        }
        else if (contrasenaNueva.isBlank()){
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CONTRASENA_VACIA); 
            return;
        }
        else if (nombreNuevo.isBlank()){
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CAMPO_OBLIGATORIO_VACIO); 
            return;
        }
        else if (!UsuarioValidador.EsTelefonoValido(telefonoNuevo)){
           SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.TELEFONO_NO_VALIDO); 
           return;
        }
        else if (!ValidadorUtilidades.esContrasenaSegura(contrasenaNueva)){
           SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CONTRASENA_NO_VALIDA); 
           return;
        }
        try{
            AuthService authService = new AuthService(); 
            Cliente clienteNuevo = new Cliente(nombreNuevo, telefonoNuevo, cliente.getDireccion(), cliente.getTarjetaCredito(),
                                            cliente.isVip(), cliente.getCorreo(), contrasenaNueva);
            
            authService.actualizarCliente(cliente.getCorreo(), clienteNuevo);
            
            this.cliente.setNombre(nombreNuevo);
            this.cliente.setTelefono(telefonoNuevo);
            this.cliente.setContrasena(contrasenaNueva);
        
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
            bloquearCampos();
            }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarCambiosActionPerformed

    private void jFormatedTextFieldTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormatedTextFieldTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormatedTextFieldTelefonoActionPerformed

    private void jButtonDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDireccionActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ModificarDireccion(cliente)); // Correo
    }//GEN-LAST:event_jButtonDireccionActionPerformed

    private void jButtonMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMetodoPagoActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ModificarMetodoDePago(cliente)); // Método de pago
    }//GEN-LAST:event_jButtonMetodoPagoActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        habilitarCampos();
    }//GEN-LAST:event_jButtonModificarActionPerformed
    
    
    private void bloquearCampos(){
        jTextFieldNombre.setEditable(false);
        jFormatedTextFieldTelefono.setEditable(false);
        jTextFieldContrasena.setEditable(false);
        jCheckBoxVip.setEnabled(false);
    }
    
    private void habilitarCampos(){
        jTextFieldNombre.setEditable(true);
        jFormatedTextFieldTelefono.setEditable(true);
        jTextFieldContrasena.setEditable(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDireccion;
    private javax.swing.JButton jButtonGuardarCambios;
    private javax.swing.JButton jButtonMetodoPago;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JCheckBox jCheckBoxVip;
    private javax.swing.JFormattedTextField jFormatedTextFieldTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JTextField jTextFieldContrasena;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}
