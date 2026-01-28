
package poo.PL2.Interface;

import java.awt.Font;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import poo.PL2.Clases.Administrador;
import poo.PL2.Clases.DataBase;
import poo.PL2.Clases.Direccion;
import poo.PL2.Clases.Evento;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.SesionErrorHandler;
import poo.PL2.Clases.ValidadorUtilidades;


public class DatosEventoAdmin extends javax.swing.JFrame {

    
    private Evento evento;
    private BufferedImage imagen;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private boolean modificando = false;
    
    public DatosEventoAdmin(Evento evento) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.evento = evento;
      
        
        // Configuración inicial
        jListFechasEvento.setModel(model);
        jListFechasEvento.setEnabled(false);
        jListFechasEvento.setFont(new java.awt.Font("Arial", Font.BOLD, 12));
        
        // Deshabilitar botones inicialmente
        jButtonSeleccionar.setEnabled(false);
        jButtonAnadirFecha.setEnabled(false);
        jButtonBorrarFecha.setEnabled(false);
        jButtonGuardarImagen.setEnabled(false);
        
        configurarArrastrarSoltar();
        
        configurarComponentes();
        // Cargar datos del evento
        cargarDatosEvento();
        
        // Configurar logo
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
    }
    
    private void configurarComponentes(){
        jLabelImagen.setText("Arrastra una imagen aquí o haz clic en Seleccionar");
        jLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    }
    
    private void cargarDatosEvento() {
        // Cargar datos básicos
        jTextFieldTitulo.setText(evento.getTitulo());
        jComboBoxTipoEvento.setSelectedItem(evento.getTipo());
        jFormattedTextFieldPrecioEntrada.setValue(evento.getPrecio());
        
        // Cargar dirección
        Direccion direccion = evento.getDireccion();
        jTextFieldCalle.setText(direccion.getCalle());
        jTextFieldNumero.setText(direccion.getNumero());
        jTextFieldCiudad.setText(direccion.getCiudad());
        jFormattedTextFieldCodigoPostal.setText(direccion.getCodigoPostal());
        
        // Cargar fechas
        model.clear();
        for (LocalDateTime fecha : evento.getFechas()) {
            model.addElement(ValidadorUtilidades.localDateTimeToString(fecha));
        }
        
        // Cargar imagen
        cargarImagenEvento();
        
        // Cargar calificación
        jTextFieldCalificacion.setText(String.format("%.1f", evento.getCalificacion()));
    }
    
    private void cargarImagenEvento() {
        if (evento.getRutaPortada() != null && !evento.getRutaPortada().isEmpty()) {
            try {
                File archivoImagen = new File(evento.obtenerRutaAbsolutaPortada());
                if (archivoImagen.exists()) {
                    imagen = ImageIO.read(archivoImagen);
                    ImageIcon icono = new ImageIcon(imagen.getScaledInstance(
                        jLabelImagen.getWidth(), -1, Image.SCALE_SMOOTH));
                    jLabelImagen.setIcon(icono);
                    jLabelImagen.setText("");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la imagen del evento", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void seleccionarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Imágenes", "jpg", "jpeg", "png", "gif"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            cargarImagen(fileChooser.getSelectedFile());
        }
    }
    
    private void cargarImagen(File archivo) {
        try {
            imagen = ImageIO.read(archivo);
            ImageIcon iconoPrincipal = new ImageIcon(imagen.getScaledInstance(
                jLabelImagen.getWidth(), -1, Image.SCALE_SMOOTH));
            jLabelImagen.setIcon(iconoPrincipal);
            jLabelImagen.setText("");
            jButtonGuardarImagen.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la imagen", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void guardarImagen() {
         if (imagen == null) {
            JOptionPane.showMessageDialog(this, "No hay imagen para guardar", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Ruta donde se guardarán las imágenes
            String rutaBase = "data/imagenesEventos/";
            File directorio = new File(rutaBase);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Nombre único para la imagen
            String nombreArchivo = "evento_" + evento.getTitulo().toLowerCase()
                    .replaceAll("[^a-z0-9]", "_") + "_" + System.currentTimeMillis() + ".png";
            File archivoDestino = new File(rutaBase + nombreArchivo);

            ImageIO.write(imagen, "png", archivoDestino);

            // Actualizar ruta en el evento
            evento.setRutaPortada("data/imagenesEventos/" + nombreArchivo);

            JOptionPane.showMessageDialog(this, "Imagen guardada correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
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
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void guardarCambios() {
        // Validar campos obligatorios
        if (jTextFieldTitulo.getText().isBlank() || jTextFieldCalle.getText().isBlank() || 
            jTextFieldNumero.getText().isBlank() || jTextFieldCiudad.getText().isBlank() || 
            jFormattedTextFieldCodigoPostal.getText().isBlank() || model.getSize() == 0) {
            
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CAMPO_OBLIGATORIO_VACIO);
            return;
        }
        
        // Validar código postal
        if (!ValidadorUtilidades.esCodigoPostalValido(jFormattedTextFieldCodigoPostal.getText())) {
            SesionErrorHandler.mostrarError(SesionErrorHandler.ErrorTipo.CODIGO_POSTAL_NO_VALIDO);
            return;
        }
        
        try {
            
            DataBase db = DataBase.getInstance();
            db.getEventos().remove(evento);
            
            // Actualizar datos del evento
            evento.setTitulo(jTextFieldTitulo.getText());
            evento.setTipo((String) jComboBoxTipoEvento.getSelectedItem());
            evento.setPrecio(Double.parseDouble(jFormattedTextFieldPrecioEntrada.getText()));
            
            // Actualizar dirección
            Direccion nuevaDireccion = new Direccion(
                jTextFieldCalle.getText(),
                jTextFieldNumero.getText(),
                jTextFieldCiudad.getText(),
                jFormattedTextFieldCodigoPostal.getText()
            );
            evento.setDireccion(nuevaDireccion);
            
            // Actualizar fechas
            ArrayList<LocalDateTime> nuevasFechas = new ArrayList<>();
            for (int i = 0; i < model.getSize(); i++) {
                nuevasFechas.add(ValidadorUtilidades.stringToLocalDateTime(model.getElementAt(i)));
            }
            evento.setFechas(nuevasFechas);
            
            // Guardar en la base de datos
            DataBase.getInstance().guardarEvento(evento);
            
            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente");
            bloquearCampos();
            modificando = false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    private void eliminarEvento() {
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que quieres eliminar este evento?", 
            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                DataBase.getInstance().getEventos().remove(evento);
                JOptionPane.showMessageDialog(this, "Evento eliminado correctamente");
                this.dispose();
               
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el evento: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void habilitarCampos() {
        jTextFieldTitulo.setEditable(true);
        jTextFieldCalle.setEditable(true);
        jTextFieldNumero.setEditable(true);
        jTextFieldCiudad.setEditable(true);
        jFormattedTextFieldCodigoPostal.setEditable(true);
        jComboBoxTipoEvento.setEnabled(true);
        jFormattedTextFieldPrecioEntrada.setEditable(true);
        jFormattedTextFieldFechaEvento.setEditable(true);
        
        jButtonSeleccionar.setEnabled(true);
        jButtonAnadirFecha.setEnabled(true);
        jButtonBorrarFecha.setEnabled(true);
        
        modificando = true;
    }
    
    private void bloquearCampos() {
        jTextFieldTitulo.setEditable(false);
        jTextFieldCalle.setEditable(false);
        jTextFieldNumero.setEditable(false);
        jTextFieldCiudad.setEditable(false);
        jFormattedTextFieldCodigoPostal.setEditable(false);
        jComboBoxTipoEvento.setEnabled(false);
        jFormattedTextFieldPrecioEntrada.setEditable(false);
        jFormattedTextFieldFechaEvento.setEditable(false);
        
        jButtonSeleccionar.setEnabled(false);
        jButtonAnadirFecha.setEnabled(false);
        jButtonBorrarFecha.setEnabled(false);
        jButtonGuardarImagen.setEnabled(false);
        
        modificando = false;
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jButtonVolver = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListFechasEvento = new javax.swing.JList<>();
        jTextFieldTitulo = new javax.swing.JTextField();
        jTextFieldCalle = new javax.swing.JTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jTextFieldCiudad = new javax.swing.JTextField();
        jFormattedTextFieldCodigoPostal = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jButtonSeleccionar = new javax.swing.JButton();
        jFormattedTextFieldFechaEvento = new javax.swing.JFormattedTextField();
        jButtonAnadirFecha = new javax.swing.JButton();
        jButtonBorrarFecha = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonModificarDatos = new javax.swing.JButton();
        jButtonGuardarDatos = new javax.swing.JButton();
        jFormattedTextFieldPrecioEntrada = new javax.swing.JFormattedTextField();
        jButtonGuardarImagen = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListReseñas = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCalificacion = new javax.swing.JTextField();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();
        jComboBoxTipoEvento = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS DEL EVENTO");

        jLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Titulo");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Calle");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Número");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ciudad");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Código Postal");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tipo");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Precio por entrada");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Fechas del evento");

        jScrollPane1.setViewportView(jListFechasEvento);

        jTextFieldTitulo.setEditable(false);
        jTextFieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloActionPerformed(evt);
            }
        });

        jTextFieldCalle.setEditable(false);
        jTextFieldCalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCalleActionPerformed(evt);
            }
        });

        jTextFieldNumero.setEditable(false);
        jTextFieldNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumeroActionPerformed(evt);
            }
        });

        jTextFieldCiudad.setEditable(false);
        jTextFieldCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCiudadActionPerformed(evt);
            }
        });

        jFormattedTextFieldCodigoPostal.setEditable(false);
        try {
            jFormattedTextFieldCodigoPostal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCodigoPostal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCodigoPostalActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("€");

        jButtonSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSeleccionar.setText("SELECCIONAR");
        jButtonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSeleccionarActionPerformed(evt);
            }
        });

        jFormattedTextFieldFechaEvento.setEditable(false);
        try {
            jFormattedTextFieldFechaEvento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/#### ##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFechaEvento.setText("00/00/0000 00:00");

        jButtonAnadirFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonAnadirFecha.setText("AÑADIR");
        jButtonAnadirFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirFechaActionPerformed(evt);
            }
        });

        jButtonBorrarFecha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonBorrarFecha.setText("BORRAR");
        jButtonBorrarFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorrarFechaActionPerformed(evt);
            }
        });

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEliminar.setForeground(new java.awt.Color(153, 0, 51));
        jButtonEliminar.setText("ELIMINAR");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonModificarDatos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonModificarDatos.setText("MODIFICAR DATOS");
        jButtonModificarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarDatosActionPerformed(evt);
            }
        });

        jButtonGuardarDatos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardarDatos.setText("GUARDAR DATOS");
        jButtonGuardarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarDatosActionPerformed(evt);
            }
        });

        jFormattedTextFieldPrecioEntrada.setEditable(false);
        try {
            jFormattedTextFieldPrecioEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldPrecioEntrada.setText("00.00");

        jButtonGuardarImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonGuardarImagen.setText("GUARDAR IMAGEN");
        jButtonGuardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarImagenActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jListReseñas);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Reseñas");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Calificación");

        jTextFieldCalificacion.setEditable(false);
        jTextFieldCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCalificacionActionPerformed(evt);
            }
        });

        jComboBoxTipoEvento.setEditable(true);
        jComboBoxTipoEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONCIERTO", "DEPORTE", "MUSICAL", "TEATRO", "FESTIVAL CULTURAL", "CINE", "TALLER" }));
        jComboBoxTipoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonBorrarFecha)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonAnadirFecha)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(jButtonGuardarImagen))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldTitulo)
                                        .addGap(20, 20, 20)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonSeleccionar)
                                    .addComponent(jButtonEliminar)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCiudad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldPrecioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jComboBoxTipoEvento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonGuardarDatos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonModificarDatos, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSeleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jFormattedTextFieldCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jFormattedTextFieldPrecioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jFormattedTextFieldFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAnadirFecha)
                            .addComponent(jButtonGuardarImagen))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBorrarFecha)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonVolver)
                            .addComponent(jButtonGuardarDatos)))
                    .addComponent(jButtonModificarDatos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        this.dispose();  
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jTextFieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloActionPerformed

    private void jTextFieldCalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCalleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCalleActionPerformed

    private void jTextFieldNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumeroActionPerformed

    private void jTextFieldCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCiudadActionPerformed

    private void jFormattedTextFieldCodigoPostalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCodigoPostalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCodigoPostalActionPerformed

    private void jButtonAnadirFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirFechaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        String fecha = jFormattedTextFieldFechaEvento.getText();
        if (!fecha.isBlank() && !fecha.contains("  ")) {
            model.addElement(fecha);
            jFormattedTextFieldFechaEvento.setText("");
        }
    }//GEN-LAST:event_jButtonAnadirFechaActionPerformed

    private void jButtonBorrarFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorrarFechaActionPerformed
        // TODO add your handling code here:
        if (model.getSize() > 0) {
            model.removeElementAt(model.getSize() - 1);
        }
    }//GEN-LAST:event_jButtonBorrarFechaActionPerformed

    private void jButtonModificarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarDatosActionPerformed
        // TODO add your handling code here:
        if (!modificando) {
            habilitarCampos();
        }
    }//GEN-LAST:event_jButtonModificarDatosActionPerformed

    private void jButtonGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarDatosActionPerformed
        // TODO add your handling code here:
        if (modificando) {
            guardarCambios();
        }   
        
    }//GEN-LAST:event_jButtonGuardarDatosActionPerformed

    private void jButtonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSeleccionarActionPerformed
        // TODO add your handling code here:
        seleccionarFoto();
        
    }//GEN-LAST:event_jButtonSeleccionarActionPerformed

    private void jButtonGuardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarImagenActionPerformed
        // TODO add your handling code here:
        guardarImagen();
        
        // AQUÍ VA TODA LA LÓGICA PARA GUARDAR LOS DATOS
        /*
        String rutaImagen = obtenerRutaImagen();
        
        eventoTemporal.setRutaPortada(rutaImagen);
        
        Evento eventoFinal = eventoTemporal.crearEvento();
        admin.registrarEvento(
            eventoFinal.getTitulo(),
            eventoFinal.getTipo(),                  LO HE COPIADO DE CrearEventoImagen
            eventoFinal.getFechas(),                (POR SI TE SIRVE)
            eventoFinal.getDireccion().getCalle(),
            eventoFinal.getDireccion().getNumero(),
            eventoFinal.getDireccion().getCiudad(),
            eventoFinal.getDireccion().getCodigoPostal(),
            eventoFinal.getPrecio(),
            eventoFinal.getRutaPortada()
        );
        */   
        JOptionPane.showMessageDialog(this, "¡Evento creado con éxito!");
    }//GEN-LAST:event_jButtonGuardarImagenActionPerformed

    private void jTextFieldCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCalificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCalificacionActionPerformed

    private void jComboBoxTipoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoEventoActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        eliminarEvento();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadirFecha;
    private javax.swing.JButton jButtonBorrarFecha;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonGuardarDatos;
    private javax.swing.JButton jButtonGuardarImagen;
    private javax.swing.JButton jButtonModificarDatos;
    private javax.swing.JButton jButtonSeleccionar;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JComboBox<String> jComboBoxTipoEvento;
    private javax.swing.JFormattedTextField jFormattedTextFieldCodigoPostal;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaEvento;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrecioEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JList<String> jListFechasEvento;
    private javax.swing.JList<String> jListReseñas;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldCalificacion;
    private javax.swing.JTextField jTextFieldCalle;
    private javax.swing.JTextField jTextFieldCiudad;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables

}
