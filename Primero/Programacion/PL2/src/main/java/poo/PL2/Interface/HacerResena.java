
package poo.PL2.Interface;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import poo.PL2.Clases.Cliente;
import poo.PL2.Clases.DataBase;
import poo.PL2.Clases.Evento;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.Resena;
import poo.PL2.Clases.Reserva;


public class HacerResena extends javax.swing.JFrame {

    private Cliente cliente;
    private Reserva reserva;
    private Evento evento;
    private Resena resenaExistente;
    
    public HacerResena(Cliente cliente, Reserva reserva) {
        this.cliente = cliente;
        this.reserva = reserva;
        this.evento = reserva.getEvento();
        
        initComponents();
        
        this.setLocationRelativeTo(null); // Centra la ventana
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
        
        configurarComponentes();
        cargarResenaExistente();
    }
    
    private void configurarComponentes() {
        // Configurar tooltips y fuente
        jTextFieldComentario.setToolTipText("Escriba su comentario sobre el evento");
        jComboBoxCalificacion.setToolTipText("Seleccione una calificación de 0 a 5 estrellas");
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));
        
        // Configurar el combo de calificación
        jComboBoxCalificacion.setSelectedIndex(0); // Por defecto 0 estrellas
        jComboBoxCalificacion.setEnabled(false);
        
        // Habilitar/deshabilitar botones según estado
        actualizarEstadoBotones();
    }
    
    private void cargarResenaExistente() {
        // Buscar si ya existe una reseña de este cliente para este evento
        for (Resena resena : evento.getResenas()) {
            if (resena.getCliente().getCorreo().equals(cliente.getCorreo())) {
                this.resenaExistente = resena;
                break;
            }
        }
        
        if (resenaExistente != null) {
            // Cargar datos de la reseña existente
            jComboBoxCalificacion.setSelectedIndex(resenaExistente.getCalificacion());
            jTextFieldComentario.setText(resenaExistente.getComentario());
            jTextFieldComentario.setEditable(false);
            jButtonPublicar.setText("ACTUALIZAR");
        }
        
        if (resenaExistente != null) {
            // Cargar datos de la reseña existente
            jComboBoxCalificacion.setSelectedIndex(resenaExistente.getCalificacion());
            jTextFieldComentario.setText(resenaExistente.getComentario());

            jTextFieldComentario.setEditable(false);
            jComboBoxCalificacion.setEnabled(false); //  Desactivar comboBox
            jButtonPublicar.setText("PUBLICAR");
        }
    }
    
    private void actualizarEstadoBotones() {
        boolean tieneCalificacion = jComboBoxCalificacion.getSelectedIndex() > 0;
        boolean estaEditando = jTextFieldComentario.isEditable();
        
        jButtonPublicar.setEnabled(tieneCalificacion);
        jButtonEditar.setEnabled(!estaEditando);
    }
    
    private void publicarResena() {
        try {
            int calificacion = jComboBoxCalificacion.getSelectedIndex();
            String comentario = jTextFieldComentario.getText().trim();

            // Validaciones
            if (calificacion < 1) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione una calificación de al menos 1 estrella", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (comentario.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor escriba un comentario", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear o actualizar la reseña
            Resena resena;
            if (resenaExistente == null) {
                resena = new Resena();
                resena.setCliente(cliente);
                evento.getResenas().add(resena);
            } else {
                resena = resenaExistente;
            }
            

            resena.setCalificacion(calificacion);
            resena.setComentario(comentario);

            // Actualizar calificación promedio del evento
            actualizarCalificacionEvento();

            // Eliminar el evento anterior de la base de datos
            DataBase db = DataBase.getInstance();
            db.getEventos().remove(evento); // Evita duplicados

            // Guardar el evento actualizado
            db.guardarEvento(evento);

            JOptionPane.showMessageDialog(this, 
                "¡Reseña publicada con éxito!", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

            jTextFieldComentario.setEditable(false);
            jComboBoxCalificacion.setEnabled(false);
            actualizarEstadoBotones();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al publicar la reseña: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarCalificacionEvento() {
        if (evento.getResenas().isEmpty()) {
            evento.setCalificacion(0);
            return;
        }
        
        double suma = 0;
        for (Resena resena : evento.getResenas()) {
            suma += resena.getCalificacion();
        }
        
        double promedio = suma / evento.getResenas().size();
        evento.setCalificacion(Math.round(promedio * 10.0) / 10.0); // Redondear a 1 decimal
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();
        jComboBoxCalificacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldComentario = new javax.swing.JTextField();
        jButtonVolver = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonPublicar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RESEÑA");

        jComboBoxCalificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 estrellas", "1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas" }));
        jComboBoxCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCalificacionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Calificación");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Comentario");

        jTextFieldComentario.setEditable(false);
        jTextFieldComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldComentarioActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jButtonEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEditar.setText("EDITAR");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonPublicar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonPublicar.setText("PUBLICAR");
        jButtonPublicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPublicarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBoxCalificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonVolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                        .addComponent(jButtonPublicar))
                    .addComponent(jTextFieldComentario)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldComentario, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVolver)
                    .addComponent(jButtonEditar)
                    .addComponent(jButtonPublicar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCalificacionActionPerformed
        // TODO add your handling code here:
        actualizarEstadoBotones();
    }//GEN-LAST:event_jComboBoxCalificacionActionPerformed

    private void jTextFieldComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldComentarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldComentarioActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new ConsultarEventosAtendidos(cliente)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        // TODO add your handling code here:
        jTextFieldComentario.setEditable(true);
        jComboBoxCalificacion.setEnabled(true); // ✅ Activar comboBox al editar
        actualizarEstadoBotones();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonPublicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPublicarActionPerformed
        // TODO add your handling code here:
        publicarResena();      
    }//GEN-LAST:event_jButtonPublicarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonPublicar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxCalificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JTextField jTextFieldComentario;
    // End of variables declaration//GEN-END:variables
}
