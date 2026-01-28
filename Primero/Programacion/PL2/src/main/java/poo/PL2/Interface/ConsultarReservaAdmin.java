package poo.PL2.Interface;

import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import poo.PL2.Clases.DataBase;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.Reserva;

public class ConsultarReservaAdmin extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    
    /**
     * Creates new form ConsultarReservaAdmin
     */
    public ConsultarReservaAdmin() {
        initComponents();
        configurarComponentes();
        
        this.setLocationRelativeTo(null); // Centra la ventana
        
        jTableReservas.setEnabled(false);
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
        
        // Configurar el modelo de la tabla
        configurarTabla();
        
        // Configurar DocumentListener para el campo de fecha
        configurarDocumentListener();
        
        // Cargar todas las reservas inicialmente
        cargarReservas(null);
    }
    
    /**
     * Configura el DocumentListener para el campo de fecha
     */
    private void configurarDocumentListener() {
        jFormattedTextFieldFechaReserva.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                procesarCambioFecha();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                procesarCambioFecha();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                procesarCambioFecha();
            }
            
            private void procesarCambioFecha() {
                LocalDate fecha = parsearFecha();
                // Solo filtrar si la fecha es válida o es null (campo vacío)
                if (fecha != null || jFormattedTextFieldFechaReserva.getText().trim().isEmpty() || 
                    jFormattedTextFieldFechaReserva.getText().equals("00/00/0000")) {
                    cargarReservas(fecha);
                }
            }
        });
    }
    
    /**
     * Configura el modelo de la tabla
     */
    private void configurarTabla() {
        tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Título del evento", "Fecha", "Precio", "Código de la factura", "Correo del cliente"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que todas las celdas no sean editables
            }
        };
        
        jTableReservas.setModel(tableModel);
    }
    
    /**
     * Carga todas las reservas, opcionalmente filtrando por fecha mínima
     * @param fechaMinima Fecha mínima para filtrar (null para no filtrar)
     */
    private void cargarReservas(LocalDate fechaMinima) {
        // Limpiar la tabla
        tableModel.setRowCount(0);
        
        // Obtener todas las reservas desde la base de datos
        List<Reserva> reservas = DataBase.getInstance().getReservas();
        
        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reservas en el sistema.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Formateador de fechas
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Filtrar y agregar reservas a la tabla
        for (Reserva reserva : reservas) {
            // Si se especificó fecha mínima y la reserva es anterior, saltarla
            if (fechaMinima != null && reserva.getFechaEvento().toLocalDate().isBefore(fechaMinima)) {
                continue;
            }
            
            // Agregar fila a la tabla
            tableModel.addRow(new Object[]{
                reserva.getEvento().getTitulo(),
                reserva.getFechaEvento().format(dateTimeFormatter),
                String.format("%.2f €", reserva.getPrecioFinal()),
                reserva.getCodigoFactura(),
                reserva.getCliente().getCorreo() // Nueva columna con el correo del cliente
            });
        }
    }
    
     /**
     * Intenta parsear la fecha del campo de texto
     * @return LocalDate o null si no es válida
     */
    private LocalDate parsearFecha() {
        String fechaStr = jFormattedTextFieldFechaReserva.getText().trim();
        
        // Verificar si el campo está vacío o con el valor por defecto
        if (fechaStr.isEmpty() || fechaStr.equals("00/00/0000")) {
            return null;
        }
        
        try {
            return LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            return null; // No mostrar mensaje de error, simplemente no filtrar
        }
    }
    
    private void configurarComponentes() {
        jFormattedTextFieldFechaReserva.setToolTipText("Escriba la fecha mínima a buscar (DD/MM/AAAA)"); 
        
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));  // Fuente personalizada
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldFechaReserva1 = new javax.swing.JFormattedTextField();
        jButtonVolver1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableReservas1 = new javax.swing.JTable();
        jLabelJavaEvents2 = new javax.swing.JLabel();
        jLabelJavaEvents3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldFechaReserva = new javax.swing.JFormattedTextField();
        jButtonVolver = new javax.swing.JButton();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableReservas = new javax.swing.JTable();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("CONSULTA DE RESERVAS");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("A partir del ...");

        try {
            jFormattedTextFieldFechaReserva1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFechaReserva1.setText("00/00/0000");
        jFormattedTextFieldFechaReserva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFechaReserva1ActionPerformed(evt);
            }
        });

        jButtonVolver1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver1.setText("VOLVER");
        jButtonVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolver1ActionPerformed(evt);
            }
        });

        jTableReservas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Título del evento", "Fecha", "Precio", "Codigo de la factura"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableReservas1.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(jTableReservas1);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonVolver1)
                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jFrame1Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextFieldFechaReserva1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelJavaEvents3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelJavaEvents2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 29, Short.MAX_VALUE)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jFormattedTextFieldFechaReserva1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 26, Short.MAX_VALUE)
                .addComponent(jButtonVolver1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE RESERVAS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("A partir del ...");

        try {
            jFormattedTextFieldFechaReserva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFechaReserva.setText("00/00/0000");
        jFormattedTextFieldFechaReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFechaReservaActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jTableReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Título del evento", "Fecha", "Precio", "Codigo de la factura", "Correo cliente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableReservas.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(jTableReservas);
        jTableReservas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (jTableReservas.getColumnModel().getColumnCount() > 0) {
            jTableReservas.getColumnModel().getColumn(0).setResizable(false);
            jTableReservas.getColumnModel().getColumn(1).setResizable(false);
            jTableReservas.getColumnModel().getColumn(2).setResizable(false);
            jTableReservas.getColumnModel().getColumn(3).setResizable(false);
            jTableReservas.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonVolver)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextFieldFechaReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVolver)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFormattedTextFieldFechaReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFechaReservaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFechaReservaActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new PortalAdministrador()); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jFormattedTextFieldFechaReserva1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFechaReserva1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFechaReserva1ActionPerformed

    private void jButtonVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolver1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonVolver1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JButton jButtonVolver1;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaReserva;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaReserva1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JLabel jLabelJavaEvents2;
    private javax.swing.JLabel jLabelJavaEvents3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableReservas;
    private javax.swing.JTable jTableReservas1;
    // End of variables declaration//GEN-END:variables
}
