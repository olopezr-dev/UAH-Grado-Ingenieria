
package poo.PL2.Interface;

import javax.swing.JToolTip;
import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.RegistroTemporal;
import poo.PL2.Clases.SesionErrorHandler;
import poo.PL2.Clases.SesionErrorHandler.ErrorTipo;


public class NuevaCuenta extends javax.swing.JFrame {
    
    
    private RegistroTemporal registroTemp;
    
    
    public NuevaCuenta() {
        this(new RegistroTemporal()); // Llama al constructor principal
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }
    
    public NuevaCuenta(RegistroTemporal registroTemp) {
        initComponents();
        configurarComponentes();
        this.setLocationRelativeTo(null); // Centra la ventana
        this.registroTemp = registroTemp;
        
        // Si estamos volviendo atrás, podemos mostrar los datos existentes
        if (registroTemp.getCorreo() != null) {
            jTextFieldCorreo.setText(registroTemp.getCorreo());
        }
    }
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonSiguiente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jPasswordFieldContrasena = new javax.swing.JPasswordField();
        jPasswordFieldRepetirContrasena = new javax.swing.JPasswordField();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldCorreo.setActionCommand("<Not Set>");
        jTextFieldCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Correo");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButtonSiguiente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSiguiente.setText("SIGUIENTE");
        jButtonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSiguienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVA CUENTA");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Repetir contraseña");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                        .addComponent(jButtonSiguiente))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldContrasena)
                    .addComponent(jTextFieldCorreo)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordFieldRepetirContrasena))
                .addGap(0, 66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPasswordFieldContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPasswordFieldRepetirContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSiguiente)
                    .addComponent(jButtonVolver))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTextFieldCorreo.getAccessibleContext().setAccessibleName("");
        jTextFieldCorreo.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configurarComponentes() {
        jTextFieldCorreo.setToolTipText("Correo ejemplo: javaevents@gmail.com"); 
        jPasswordFieldContrasena.setToolTipText("Entre 8 y 12 caracteres, al menos una mayúscula y un número"); 
        
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));  // Fuente personalizada
    }
    
    
    private void jTextFieldCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextFieldCorreoActionPerformed

    private void jButtonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSiguienteActionPerformed
        // TODO add your handling code here:
        String correo = jTextFieldCorreo.getText().trim();
        String contrasena = jPasswordFieldContrasena.getText();
        String confirmacion = jPasswordFieldRepetirContrasena.getText();

        // Validación del correo
        if (correo.isEmpty()) {
            limpiarCampos();
            SesionErrorHandler.mostrarError(ErrorTipo.USUARIO_VACIO);
            return;
        } 
        
        if (correo.contains(" ")) {
            limpiarCampos();
            SesionErrorHandler.mostrarError(ErrorTipo.USUARIO_NO_VALIDO);
            return;
        }

        // Validación de la contraseña
        if (contrasena.isEmpty()) {
            jPasswordFieldContrasena.setText("");
            jPasswordFieldRepetirContrasena.setText("");
            SesionErrorHandler.mostrarError(ErrorTipo.CONTRASENA_VACIA);
            return;
        }
        
        if (contrasena.contains(" ") || contrasena.length() < 4 || contrasena.length() > 12) {
            jPasswordFieldContrasena.setText("");
            jPasswordFieldRepetirContrasena.setText("");
            SesionErrorHandler.mostrarError(ErrorTipo.CONTRASENA_NO_VALIDA);
            return;
        }
        
        if (!contrasena.equals(confirmacion)) {
            jPasswordFieldContrasena.setText("");
            jPasswordFieldRepetirContrasena.setText("");
            SesionErrorHandler.mostrarError(ErrorTipo.CONTRASENA_MAL_REESCRITA);
            return;
        }

        // Todo correcto, guardamos los datos y avanzamos
        registroTemp.setCorreo(correo);
        registroTemp.setContrasena(contrasena);
        
        Navegacion.cambiarVentana(this, new NuevaCuentaDatos(registroTemp));
    }//GEN-LAST:event_jButtonSiguienteActionPerformed
    
    private void limpiarCampos() {
        jTextFieldCorreo.setText("");
        jPasswordFieldContrasena.setText("");
        jPasswordFieldRepetirContrasena.setText("");
    }
    
    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        Navegacion.cambiarVentana(this, new MainMenu()); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JPasswordField jPasswordFieldContrasena;
    private javax.swing.JPasswordField jPasswordFieldRepetirContrasena;
    private javax.swing.JTextField jTextFieldCorreo;
    // End of variables declaration//GEN-END:variables
}
