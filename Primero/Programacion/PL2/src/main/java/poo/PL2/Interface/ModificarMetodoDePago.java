
package poo.PL2.Interface;

import java.time.YearMonth;
import javax.swing.JOptionPane;
import poo.PL2.Clases.AuthService;
import poo.PL2.Clases.Cliente;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.SesionErrorHandler;
import poo.PL2.Clases.TarjetaCredito;
import poo.PL2.Clases.UsuarioValidador;
import poo.PL2.Clases.ValidadorUtilidades;


public class ModificarMetodoDePago extends javax.swing.JFrame {

    private final Cliente cliente;
    
    public ModificarMetodoDePago(Cliente cliente) {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.cliente = cliente;
        cargarDatosCliente();
        bloquearCampos(); 
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }
    
    private void cargarDatosCliente() {
        
        TarjetaCredito tarjetaCredito = cliente.getTarjetaCredito();
        
        jTextFieldTitular.setText(tarjetaCredito.getTitular());
        jFormattedTextFieldDigitos.setText(tarjetaCredito.getDigitos());
        jFormattedTextFieldFechaCaducidad.setText(ValidadorUtilidades.formatFechaCaducidad(tarjetaCredito.getFechaCaducidad()));     
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTitular = new javax.swing.JTextField();
        jFormattedTextFieldFechaCaducidad = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDigitos = new javax.swing.JFormattedTextField();
        jButtonGuardarCambios = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MIS DATOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TARJETA DE CRÉDITO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Titular");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Dígitos");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Fecha de caducidad");

        jTextFieldTitular.setEditable(false);
        jTextFieldTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitularActionPerformed(evt);
            }
        });

        jFormattedTextFieldFechaCaducidad.setEditable(false);
        jFormattedTextFieldFechaCaducidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/yy"))));
        jFormattedTextFieldFechaCaducidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFechaCaducidadActionPerformed(evt);
            }
        });

        jFormattedTextFieldDigitos.setEditable(false);
        try {
            jFormattedTextFieldDigitos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("################")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDigitos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDigitosActionPerformed(evt);
            }
        });

        jButtonGuardarCambios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGuardarCambios.setText("GUARDAR CAMBIOS");
        jButtonGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarCambiosActionPerformed(evt);
            }
        });

        jButtonModificar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonModificar.setText("MODIFICAR");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonVolver))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonModificar)
                        .addGap(34, 34, 34)
                        .addComponent(jButtonGuardarCambios))
                    .addComponent(jFormattedTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDigitos)
                    .addComponent(jTextFieldTitular))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarCambios)
                    .addComponent(jButtonModificar)
                    .addComponent(jButtonVolver))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        String titularNuevo = jTextFieldTitular.getText();
        String digitosNuevos = jFormattedTextFieldDigitos.getText();
        String fechaCaducidadNueva = jFormattedTextFieldFechaCaducidad.getText();
        
        if (titularNuevo.isBlank() || digitosNuevos.isBlank() || fechaCaducidadNueva.isBlank()){
           SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CAMPO_OBLIGATORIO_VACIO); 
           return;
        }
        if (!UsuarioValidador.EsTarjetaValida(digitosNuevos)){
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.DIGITOS_NO_VALIDOS);
            return;
        }
        if (!UsuarioValidador.EsFechaCaducidadValida(fechaCaducidadNueva)){
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.FECHA_CADUCIDAD_NO_VALIDA);
        }
        try {
            
            YearMonth fechaCaducidadNuevaF = ValidadorUtilidades.parseFechaCaducidad(fechaCaducidadNueva);
            
            AuthService authService = new AuthService();
            TarjetaCredito tarjetaCreditoNueva = new TarjetaCredito(titularNuevo, digitosNuevos, fechaCaducidadNuevaF);
            
            Cliente clienteNuevo = new Cliente(cliente.getNombre(), cliente.getTelefono(), cliente.getDireccion(), tarjetaCreditoNueva,
                                            cliente.isVip(), cliente.getCorreo(), cliente.getContrasena());
            
            authService.actualizarCliente(cliente.getCorreo(), clienteNuevo);
            
            this.cliente.setTarjetaCredito(tarjetaCreditoNueva);
            
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
            bloquearCampos();
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonGuardarCambiosActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        habilitarCampos();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jTextFieldTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTitularActionPerformed

    private void jFormattedTextFieldDigitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDigitosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDigitosActionPerformed

    private void jFormattedTextFieldFechaCaducidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFechaCaducidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFechaCaducidadActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ModificarDatos(cliente)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void bloquearCampos(){
        jTextFieldTitular.setEditable(false);
        jFormattedTextFieldDigitos.setEditable(false);
        jFormattedTextFieldFechaCaducidad.setEditable(false);
    }
    
    private void habilitarCampos(){
        jTextFieldTitular.setEditable(true);
        jFormattedTextFieldDigitos.setEditable(true);
        jFormattedTextFieldFechaCaducidad.setEditable(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGuardarCambios;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JFormattedTextField jFormattedTextFieldDigitos;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaCaducidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JTextField jTextFieldTitular;
    // End of variables declaration//GEN-END:variables
}
