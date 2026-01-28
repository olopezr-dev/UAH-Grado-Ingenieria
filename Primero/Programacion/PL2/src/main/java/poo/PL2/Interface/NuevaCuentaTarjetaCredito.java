
package poo.PL2.Interface;

import java.awt.Font;
import java.io.IOException;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.RegistroTemporal;
import poo.PL2.Clases.SesionErrorHandler;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import poo.PL2.Clases.AuthService;
import poo.PL2.Clases.Cliente;
import poo.PL2.Clases.TarjetaCredito;
import poo.PL2.Clases.ValidadorUtilidades;


public class NuevaCuentaTarjetaCredito extends javax.swing.JFrame {
    
        private final RegistroTemporal registroTemp;
        
    
    public NuevaCuentaTarjetaCredito(RegistroTemporal registroTemp) {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centra la ventana 
        this.registroTemp = registroTemp;
        precargarDatos();
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }
    
    private void precargarDatos() {
        if (registroTemp.getTarjetaCredito() != null) {
            if (registroTemp.getTarjetaCredito().getTitular() != null) {
                jTextFieldTitular.setText(registroTemp.getTarjetaCredito().getTitular());
            }
            if (registroTemp.getTarjetaCredito().getDigitos() != null) {
                jFormattedTextFieldDigitos.setText(registroTemp.getTarjetaCredito().getDigitos());
            }
            if (registroTemp.getTarjetaCredito().getFechaCaducidad() != null) {
                jFormattedTextFieldFechaCaducidad.setText(registroTemp.getTarjetaCredito()
                    .getFechaCaducidad().format(DATE_FORMATTER));
            }
        }
    }
    
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("MM/yy");
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldFechaCaducidad = new javax.swing.JFormattedTextField();
        jTextFieldTitular = new javax.swing.JTextField();
        jButtonCrearCuenta = new javax.swing.JButton();
        jButtonVolver = new javax.swing.JButton();
        jFormattedTextFieldDigitos = new javax.swing.JFormattedTextField();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CUENTA");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TARJETA DE CRÉDITO");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Titular");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Fecha de caducidad");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Dígitos");

        jFormattedTextFieldFechaCaducidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("MM/yy"))));
        jFormattedTextFieldFechaCaducidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFechaCaducidadActionPerformed(evt);
            }
        });

        jTextFieldTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitularActionPerformed(evt);
            }
        });

        jButtonCrearCuenta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCrearCuenta.setText("CREAR CUENTA");
        jButtonCrearCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearCuentaActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCrearCuenta))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldDigitos, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                            .addComponent(jTextFieldTitular))))
                .addContainerGap(26, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jFormattedTextFieldDigitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearCuenta)
                    .addComponent(jButtonVolver))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarComponentes() {
        jFormattedTextFieldFechaCaducidad.setToolTipText("Fecha ejemplo: 30/29"); 
        
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));  // Fuente personalizada
    }
    
    
    private void jFormattedTextFieldFechaCaducidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFechaCaducidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFechaCaducidadActionPerformed

    private void jTextFieldTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTitularActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new NuevaCuentaDireccion(registroTemp)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonCrearCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearCuentaActionPerformed
        // TODO add your handling code here:
        if ((jTextFieldTitular.getText().equals("")) || 
        (jFormattedTextFieldDigitos.getText().equals("")) || 
        (jFormattedTextFieldFechaCaducidad.getText().equals(""))) {
        SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CAMPO_OBLIGATORIO_VACIO);
    } else {
            
        TarjetaCredito tarjetaCredito = new TarjetaCredito(jTextFieldTitular.getText(),
                                            jFormattedTextFieldDigitos.getText(),
                                            null);
            
        //Guardar la fecha de caducidad
        try {
            YearMonth fechaCaducidad = YearMonth.parse(
            jFormattedTextFieldFechaCaducidad.getText(),
            DateTimeFormatter.ofPattern("MM/yy"));
            tarjetaCredito.setFechaCaducidad(fechaCaducidad);
        } catch (DateTimeParseException e) {
            System.out.println("Se ha producido un error"); //hacer un pop up que no le deje
        }
            
        registroTemp.setTarjetaCredito(tarjetaCredito);
         
        try {
            // 1. Crear el cliente con todos los datos
            Cliente nuevoCliente = registroTemp.crearCliente();

            // 2. Registrar el cliente en el sistema
            AuthService authService = new AuthService();
            boolean registroExitoso = authService.registrarCliente(nuevoCliente);

            if (registroExitoso) {
                // 3. Mostrar éxito y abrir portal
                JOptionPane.showMessageDialog(this, "¡Registro completado con éxito!");
                PortalCliente portal = new PortalCliente(nuevoCliente);
                portal.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar el cliente", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
        
 
    }
        
    }//GEN-LAST:event_jButtonCrearCuentaActionPerformed

    private void jFormattedTextFieldDigitosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDigitosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDigitosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrearCuenta;
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
