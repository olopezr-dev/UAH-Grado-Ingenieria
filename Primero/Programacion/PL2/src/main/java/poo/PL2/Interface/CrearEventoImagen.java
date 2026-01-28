
package poo.PL2.Interface;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import poo.PL2.Clases.Administrador;
import poo.PL2.Clases.DataBase;
import poo.PL2.Clases.Evento;
import poo.PL2.Clases.EventoTemporal;
import poo.PL2.Clases.Navegacion;


public class CrearEventoImagen extends javax.swing.JFrame {

    EventoTemporal eventoTemporal = new EventoTemporal();
    Administrador admin = new Administrador();
    
    public CrearEventoImagen(EventoTemporal eventoTemporal) {
        initComponents();
        configurarArrastrarSoltar();  // Configurar Drag & Drop
        this.setLocationRelativeTo(null); // Centra la ventana
        this.eventoTemporal = eventoTemporal;
        
        jButtonCrear.setEnabled(false); // Solo se activa cuando hay imagen
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
        
    }

    // --- Método 1: Seleccionar imagen con JFileChooser ---
    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Imágenes", "jpg", "jpeg", "png", "gif"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            cargarImagen(fileChooser.getSelectedFile());
        }
    }
    
    // --- Método 2 Modificado: Guardar imagen en ruta fija ---
    private void guardarFoto() {
        if (imagen == null) {
            JOptionPane.showMessageDialog(this, "No hay imagen para guardar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ruta fija donde se guardará la imagen
        String rutaBase = "data/imagenesEventos/";  // Para Windows

        // Crear directorio si no existe
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Nombre del archivo (puedes personalizarlo)
        String nombreArchivo = "foto_usuario_" + System.currentTimeMillis() + ".png";
        File archivoDestino = new File(rutaBase + nombreArchivo);

        try {
            ImageIO.write(imagen, "png", archivoDestino);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
     // --- Método 3: Cargar y mostrar imagen (compartido por FileChooser y Drag & Drop) ---
    private void cargarImagen(File archivo) {
        try {
            imagen = ImageIO.read(archivo);
            // Mostrar imagen principal (redimensionada)
            ImageIcon iconoPrincipal = new ImageIcon(imagen.getScaledInstance(
                jLabelImagen.getWidth(), -1, Image.SCALE_SMOOTH));
            jLabelImagen.setIcon(iconoPrincipal);
            jLabelImagen.setText("");

            jButtonCrear.setEnabled(true); // Activar botón de guardado
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Método 4: Configurar Drag & Drop ---
    private void configurarArrastrarSoltar() {
        DropTarget dropTarget = new DropTarget(jLabelImagen, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    java.util.List<File> archivos = (java.util.List<File>) 
                        dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    if (!archivos.isEmpty()) {
                        cargarImagen(archivos.get(0)); // Cargar el primer archivo
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelImagen = new javax.swing.JLabel();
        jButtonSeleccionar = new javax.swing.JButton();
        jButtonCrear = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jLabelJavaEvents1 = new javax.swing.JLabel();
        jLabelJavaEvents = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagen.setText("Arrastra una imagen aquí o haz clic en Seleccionar");
        jLabelImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonSeleccionar.setText("SELECCIONAR");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        jButtonCrear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonCrear.setText("CREAR");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NUEVO EVENTO");

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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonSeleccionar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonVolver)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCrear))
                        .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButtonSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrear)
                    .addComponent(jButtonVolver))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        // TODO add your handling code here:
        seleccionarFoto();    
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        // TODO add your handling code here:
        guardarFoto();
        
        // AQUÍ VA TODA LA LÓGICA PARA GUARDAR LOS DATOS
        String rutaImagen = obtenerRutaImagen();
        
        eventoTemporal.setRutaPortada(rutaImagen);
        
        Evento eventoFinal = eventoTemporal.crearEvento();
        admin.registrarEvento(
            eventoFinal.getTitulo(),
            eventoFinal.getTipo(),
            eventoFinal.getFechas(),
            eventoFinal.getDireccion().getCalle(),
            eventoFinal.getDireccion().getNumero(),
            eventoFinal.getDireccion().getCiudad(),
            eventoFinal.getDireccion().getCodigoPostal(),
            eventoFinal.getPrecio(),
            eventoFinal.getRutaPortada()
        );
                
        JOptionPane.showMessageDialog(this, "¡Evento creado con éxito!");
        Navegacion.cambiarVentana(this, new PortalAdministrador()); // Crear
    }//GEN-LAST:event_jButtonCrearActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new CrearEvento(eventoTemporal)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed
    
    private String obtenerRutaImagen() {
        if (imagen == null) return null;

        // Ruta absoluta directa
        final String RUTA_BASE = "data/imagenesEventos/";

        try {
            // 1. Asegurar que existe el directorio
            File directorioImagenes = new File(RUTA_BASE);
            if (!directorioImagenes.exists()) {
                if (!directorioImagenes.mkdirs()) {
                    throw new IOException("No se pudo crear el directorio de imágenes");
                }
            }

            // 2. Generar nombre de archivo único y seguro
            String nombreArchivo = "evento_" 
                    + eventoTemporal.getTitulo().toLowerCase()
                        .replaceAll("[^a-z0-9]", "_") // Elimina caracteres especiales
                    + "_" + System.currentTimeMillis() 
                    + ".png";

            // 3. Guardar la imagen
            File archivoDestino = new File(RUTA_BASE + nombreArchivo);
            ImageIO.write(imagen, "png", archivoDestino);

            // 4. Retornar la ruta completa para referencia
            return "data/imagenesEventos/" + nombreArchivo;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar la imagen: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    // End of variables declaration//GEN-END:variables
    private BufferedImage imagen;    // Almacena la imagen cargada
}
