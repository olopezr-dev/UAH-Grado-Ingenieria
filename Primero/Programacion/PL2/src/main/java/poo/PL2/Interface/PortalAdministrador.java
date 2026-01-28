
package poo.PL2.Interface;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import poo.PL2.Clases.Navegacion;


public class PortalAdministrador extends javax.swing.JFrame {

   
    public PortalAdministrador() {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonGestionEventos = new javax.swing.JButton();
        jButtonConsultaEventos = new javax.swing.JButton();
        jButtonConsultaUsuarios = new javax.swing.JButton();
        jButtonConsultaReservas = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PORTAL DE ADMINISTRADOR");

        jButtonGestionEventos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonGestionEventos.setText("GESTIÓN DE EVENTOS");
        jButtonGestionEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionEventosActionPerformed(evt);
            }
        });

        jButtonConsultaEventos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonConsultaEventos.setText("CONSULTA DE EVENTOS");
        jButtonConsultaEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaEventosActionPerformed(evt);
            }
        });

        jButtonConsultaUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonConsultaUsuarios.setText("CONSULTA DE USUARIOS");
        jButtonConsultaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaUsuariosActionPerformed(evt);
            }
        });

        jButtonConsultaReservas.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonConsultaReservas.setText("CONSULTA DE RESERVAS");
        jButtonConsultaReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaReservasActionPerformed(evt);
            }
        });

        jButtonSalir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSalir.setForeground(new java.awt.Color(153, 0, 0));
        jButtonSalir.setText("CERRAR SESIÓN");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonGestionEventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConsultaEventos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConsultaUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConsultaReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButtonGestionEventos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButtonConsultaEventos)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonConsultaUsuarios)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonConsultaReservas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButtonSalir)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGestionEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionEventosActionPerformed
        // TODO add your handling code here:
        // Crear el popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        
        // Crear las opciones
        JMenuItem itemReservados = new JMenuItem("CREAR NUEVO EVENTO");
        
        // Añadir acciones a las opciones
        itemReservados.addActionListener(e -> {
            Navegacion.cambiarVentana(this, new CrearEvento()); // Crear Evento
        });

        
        // Agregar ítems al menú
        popupMenu.add(itemReservados);
        
        // Mostrar el popup en la posición del botón
        popupMenu.show(jButtonGestionEventos, 2, jButtonGestionEventos.getHeight());
    }//GEN-LAST:event_jButtonGestionEventosActionPerformed

    private void jButtonConsultaEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaEventosActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new BuscarEventoAdmin()); // Consultar Eventos
    }//GEN-LAST:event_jButtonConsultaEventosActionPerformed

    private void jButtonConsultaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaUsuariosActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ConsultarUsuario()); // Consultar Usuarios
    }//GEN-LAST:event_jButtonConsultaUsuariosActionPerformed

    private void jButtonConsultaReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaReservasActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ConsultarReservaAdmin()); // Consultar Reservas
    }//GEN-LAST:event_jButtonConsultaReservasActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new MainMenu()); // Cerrar Sesión
    }//GEN-LAST:event_jButtonSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConsultaEventos;
    private javax.swing.JButton jButtonConsultaReservas;
    private javax.swing.JButton jButtonConsultaUsuarios;
    private javax.swing.JButton jButtonGestionEventos;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    // End of variables declaration//GEN-END:variables
}
