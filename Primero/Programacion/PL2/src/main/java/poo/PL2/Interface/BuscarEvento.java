
package poo.PL2.Interface;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import poo.PL2.Clases.Cliente;
import poo.PL2.Clases.DataBase;
import poo.PL2.Clases.Evento;
import poo.PL2.Clases.Navegacion;
import poo.PL2.Clases.ValidadorUtilidades;


public class BuscarEvento extends javax.swing.JFrame {

    private DefaultTableModel tableModel;
    private List<Evento> eventosMostrados;
    private Cliente cliente;
    
    public BuscarEvento(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        bloquearCampos();
        inicializarTabla();
        cargarTodosEventos();
        configurarComponentes();
        agregarListeners();
        configurarDobleClickTabla();
        
        this.setLocationRelativeTo(null); // Centra la ventana
        
        Navegacion.ponerLogo(jLabelJavaEvents, jLabelJavaEvents1);
   
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        jFormattedTextFieldFechaEvento = new javax.swing.JFormattedTextField();
        jComboBoxTipoEvento = new javax.swing.JComboBox<>();
        jCheckBoxTitulo = new javax.swing.JCheckBox();
        jCheckBoxCiudad = new javax.swing.JCheckBox();
        jComboBoxRangoPrecioEntrada = new javax.swing.JComboBox<>();
        jCheckBoxFechaEvento = new javax.swing.JCheckBox();
        jCheckBoxTipoEvento = new javax.swing.JCheckBox();
        jTextFieldTitulo = new javax.swing.JTextField();
        jCheckBoxPrecioEntrada = new javax.swing.JCheckBox();
        jTextFieldCiudad = new javax.swing.JTextField();
        jButtonVolver = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBoxRangoPrecioEntrada1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEventos = new javax.swing.JTable();
        jButtonReiniciarFiltros = new javax.swing.JButton();
        jLabelJavaEvents = new javax.swing.JLabel();
        jLabelJavaEvents1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUSCADOR");

        try {
            jFormattedTextFieldFechaEvento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/#### ##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldFechaEvento.setText("00/00/0000 00:00");
        jFormattedTextFieldFechaEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFechaEventoActionPerformed(evt);
            }
        });

        jComboBoxTipoEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONCIERTO", "DEPORTE", "MUSICAL", "TEATRO", "FESTIVAL CULTURAL", "CINE", "TALLER" }));
        jComboBoxTipoEvento.setToolTipText("");
        jComboBoxTipoEvento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxTipoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoEventoActionPerformed(evt);
            }
        });

        jCheckBoxTitulo.setText("TÍTULO");
        jCheckBoxTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTituloActionPerformed(evt);
            }
        });

        jCheckBoxCiudad.setText("CIUDAD");
        jCheckBoxCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxCiudadActionPerformed(evt);
            }
        });

        jComboBoxRangoPrecioEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 - 10€", "11 - 20€", "21 - 30€", "+ 30€" }));

        jCheckBoxFechaEvento.setText("FECHA");

        jCheckBoxTipoEvento.setText("TIPO");
        jCheckBoxTipoEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTipoEventoActionPerformed(evt);
            }
        });

        jTextFieldTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTituloActionPerformed(evt);
            }
        });

        jCheckBoxPrecioEntrada.setText("PRECIO");
        jCheckBoxPrecioEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPrecioEntradaActionPerformed(evt);
            }
        });

        jTextFieldCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCiudadActionPerformed(evt);
            }
        });

        jButtonVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonVolver.setText("VOLVER");
        jButtonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolverActionPerformed(evt);
            }
        });

        jCheckBox1.setText("CALIFICACION");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jComboBoxRangoPrecioEntrada1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Más de 0", "Más de 1", "Más de 2", "Más de 3", "Más de 4" }));
        jComboBoxRangoPrecioEntrada1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRangoPrecioEntrada1ActionPerformed(evt);
            }
        });

        jTableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Titulo", "Calificación", "Ciudad", "Precio", "Fecha", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEventos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableEventos);
        if (jTableEventos.getColumnModel().getColumnCount() > 0) {
            jTableEventos.getColumnModel().getColumn(0).setResizable(false);
            jTableEventos.getColumnModel().getColumn(1).setResizable(false);
            jTableEventos.getColumnModel().getColumn(2).setResizable(false);
            jTableEventos.getColumnModel().getColumn(3).setResizable(false);
            jTableEventos.getColumnModel().getColumn(4).setResizable(false);
            jTableEventos.getColumnModel().getColumn(5).setResizable(false);
        }

        jButtonReiniciarFiltros.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonReiniciarFiltros.setText("REINICIAR FILTROS");
        jButtonReiniciarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReiniciarFiltrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxCiudad)
                            .addComponent(jCheckBoxTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCiudad, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                            .addComponent(jComboBoxTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTitulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBoxPrecioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxRangoPrecioEntrada1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxRangoPrecioEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonReiniciarFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelJavaEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelJavaEvents1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxTitulo)
                    .addComponent(jTextFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jComboBoxRangoPrecioEntrada1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxPrecioEntrada)
                    .addComponent(jComboBoxRangoPrecioEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxCiudad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxTipoEvento)
                    .addComponent(jCheckBoxFechaEvento)
                    .addComponent(jFormattedTextFieldFechaEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonVolver)
                    .addComponent(jButtonReiniciarFiltros))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void configurarComponentes() {
        jTableEventos.setToolTipText("Haga doble click sobre un evento para más información"); 
        jCheckBoxTitulo.setToolTipText("Dejar vacío para ignorar este filtro");
        jCheckBoxCiudad.setToolTipText("Dejar vacío para ignorar este filtro");
        jCheckBoxTipoEvento.setToolTipText("Seleccione un tipo de evento");
        jCheckBoxFechaEvento.setToolTipText("Formato: dd/mm/aaaa hh:mm");
        
        UIManager.put("ToolTip.font", new Font("Arial", Font.BOLD, 12));  // Fuente personalizada
    }
    
    private void bloquearCampos() {
        // Campos de texto
        jTextFieldTitulo.setEnabled(false);
        jTextFieldCiudad.setEnabled(false);

        // Comboboxes
        jComboBoxTipoEvento.setEnabled(false);
        jComboBoxRangoPrecioEntrada.setEnabled(false);
        jComboBoxRangoPrecioEntrada1.setEnabled(false);

        // Campo de fecha
        jFormattedTextFieldFechaEvento.setEnabled(false);

        // Opcional: Desmarcar checkboxes (si quieres que estén desactivados inicialmente)
        jCheckBoxTitulo.setSelected(false);
        jCheckBoxCiudad.setSelected(false);
        jCheckBoxTipoEvento.setSelected(false);
        jCheckBoxPrecioEntrada.setSelected(false);
        jCheckBox1.setSelected(false); // Calificación
        jCheckBoxFechaEvento.setSelected(false);
    }
    
    private void resetearFiltros() {
        
        jTextFieldTitulo.setText("");
        jTextFieldCiudad.setText("");
        jComboBoxTipoEvento.setSelectedIndex(0);
        jComboBoxRangoPrecioEntrada.setSelectedIndex(0);
        jComboBoxRangoPrecioEntrada1.setSelectedIndex(0);
        jFormattedTextFieldFechaEvento.setText("00/00/0000 00:00");

        // Bloquea campos nuevamente
        bloquearCampos();

        // Recarga todos los eventos
        cargarTodosEventos();
    }
    
    private void inicializarTabla() {
    // Obtener el modelo que NetBeans ya creó
        tableModel = (DefaultTableModel) jTableEventos.getModel();

    // Limpiar las filas de ejemplo que puso NetBeans
    tableModel.setRowCount(0);

    // Configurar las propiedades que necesites
    tableModel.setColumnIdentifiers(new String[]{"Titulo", "Tipo", "Ciudad", "Precio", "Fecha", "Calificación"});
    
    jTableEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    }


    
    private void cargarTodosEventos() {
        DataBase db = DataBase.getInstance();
        eventosMostrados = new ArrayList<>(db.getEventos());
        actualizarTabla();
    }
    
    private void actualizarTabla() {
        tableModel.setRowCount(0);
        
        for (Evento evento : eventosMostrados) {
            // Obtener la próxima fecha (primera fecha del array)
            String proximaFecha = evento.getFechas().isEmpty() ? "Sin fecha" : 
                ValidadorUtilidades.localDateTimeToString(evento.getFechas().get(0));
            
            Object[] fila = {
                evento.getTitulo(),
                evento.getTipo(),
                evento.getDireccion().getCiudad(),
                String.format("%.2f€", evento.getPrecio()),
                proximaFecha,
                String.format("%.1f", evento.getCalificacion())
            };
            tableModel.addRow(fila);
        }
    }
    
    private void agregarListeners() {
        // Listener para campos de texto
        DocumentListener documentListener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { aplicarFiltros(); }
            @Override public void removeUpdate(DocumentEvent e) { aplicarFiltros(); }
            @Override public void changedUpdate(DocumentEvent e) { aplicarFiltros(); }
        };
        
        jTextFieldTitulo.getDocument().addDocumentListener(documentListener);
        jTextFieldCiudad.getDocument().addDocumentListener(documentListener);
        
        // Listeners para combobox y checkboxes
        jComboBoxTipoEvento.addActionListener(e -> aplicarFiltros());
        jComboBoxRangoPrecioEntrada.addActionListener(e -> aplicarFiltros());
        jComboBoxRangoPrecioEntrada1.addActionListener(e -> aplicarFiltros());
        
        jCheckBoxTitulo.addActionListener(e -> toggleFiltro(jTextFieldTitulo, jCheckBoxTitulo));
        jCheckBoxCiudad.addActionListener(e -> toggleFiltro(jTextFieldCiudad, jCheckBoxCiudad));
        jCheckBoxTipoEvento.addActionListener(e -> toggleFiltro(jComboBoxTipoEvento, jCheckBoxTipoEvento));
        jCheckBoxPrecioEntrada.addActionListener(e -> toggleFiltro(jComboBoxRangoPrecioEntrada, jCheckBoxPrecioEntrada));
        jCheckBox1.addActionListener(e -> toggleFiltro(jComboBoxRangoPrecioEntrada1, jCheckBox1));
        jCheckBoxFechaEvento.addActionListener(e -> toggleFiltro(jFormattedTextFieldFechaEvento, jCheckBoxFechaEvento));
    }
    
    private void configurarDobleClickTabla() {
        jTableEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Detecta doble click
                    int filaSeleccionada = jTableEventos.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        abrirDetalleEvento(filaSeleccionada);
                    }
                }
            }
        });
    }

    private void abrirDetalleEvento(int fila) {
        String tituloEvento = (String) tableModel.getValueAt(fila, 0);
        Evento evento = DataBase.getInstance().getEventoPorTitulo(tituloEvento);

        if (evento != null) {
            new DatosEvento(evento, cliente).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                "Error al cargar los detalles del evento", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void toggleFiltro(JComponent componente, JCheckBox checkBox) {
        boolean estaActivo = checkBox.isSelected();
        componente.setEnabled(estaActivo);

        // Limpiar campo si se desactiva el filtro
        if (!estaActivo) {
            if (componente instanceof JTextField) {
                ((JTextField) componente).setText("");
            } else if (componente instanceof JFormattedTextField) {
                ((JFormattedTextField) componente).setText("");
            } else if (componente instanceof JComboBox) {
                ((JComboBox<?>) componente).setSelectedIndex(0); // Vuelve al primer item
            }
        }
        if (componente == jFormattedTextFieldFechaEvento && !estaActivo) {
            ((JFormattedTextField) componente).setText("00/00/0000 00:00");
        }

        aplicarFiltros(); // Actualizar resultados
    }
    
    private void aplicarFiltros() {
        DataBase db = DataBase.getInstance();
        eventosMostrados = new ArrayList<>(db.getEventos());
        
        // Filtro por título
        if (jCheckBoxTitulo.isSelected()) {
            String busqueda = jTextFieldTitulo.getText().trim();
            if (!busqueda.isEmpty()) {
                eventosMostrados.removeIf(e -> 
                    !e.getTitulo().toLowerCase().contains(busqueda.toLowerCase())
                );
            }
            // Si está vacío: no filtra (muestra todos los que cumplan otros filtros)
        }
        
        // Filtro por ciudad
        if (jCheckBoxCiudad.isSelected()) {
            String busqueda = jTextFieldCiudad.getText().trim();
            if (!busqueda.isEmpty()) {
                eventosMostrados.removeIf(e -> 
                    !e.getDireccion().getCiudad().toLowerCase().contains(busqueda.toLowerCase())
                );
            }
        }
        
        // Filtro por tipo de evento
        if (jCheckBoxTipoEvento.isSelected()) {
            String tipoSeleccionado = (String) jComboBoxTipoEvento.getSelectedItem();
            eventosMostrados.removeIf(e -> !e.getTipo().equalsIgnoreCase(tipoSeleccionado));
        }
        
        // Filtro por precio
        if (jCheckBoxPrecioEntrada.isSelected()) {
            String rangoPrecio = (String) jComboBoxRangoPrecioEntrada.getSelectedItem();
            eventosMostrados.removeIf(e -> !cumpleRangoPrecio(e.getPrecio(), rangoPrecio));
        }
        
        // Filtro por calificación
        if (jCheckBox1.isSelected()) {
            String calificacionMinima = (String) jComboBoxRangoPrecioEntrada1.getSelectedItem();
            int minCalificacion = Integer.parseInt(calificacionMinima.replaceAll("[^0-9]", ""));
            eventosMostrados.removeIf(e -> e.getCalificacion() < minCalificacion);
        }
        
        // Filtro por fecha
        if (jCheckBoxFechaEvento.isSelected()) {
            String fechaTexto = jFormattedTextFieldFechaEvento.getText();
            try {
                LocalDateTime fechaFiltro = ValidadorUtilidades.stringToLocalDateTime(fechaTexto);
                eventosMostrados.removeIf(e -> 
                    e.getFechas().stream().noneMatch(fecha -> 
                        fecha.isEqual(fechaFiltro) || fecha.isAfter(fechaFiltro)
                    )
                );
            } catch (Exception e) {
                // Si hay error en el formato, no filtrar
            }
        }
        
        actualizarTabla();
    }
    
    private boolean cumpleRangoPrecio(double precio, String rango) {
        if (rango.equals("0 - 10€")) return precio >= 0 && precio <= 10;
        if (rango.equals("11 - 20€")) return precio >= 11 && precio <= 20;
        if (rango.equals("21 - 30€")) return precio >= 21 && precio <= 30;
        if (rango.equals("+ 30€")) return precio > 30;
        return true;
    }
     
    
    
    
    private void jCheckBoxTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTituloActionPerformed

    private void jTextFieldTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTituloActionPerformed

    private void jCheckBoxPrecioEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPrecioEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxPrecioEntradaActionPerformed

    private void jCheckBoxCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxCiudadActionPerformed

    private void jTextFieldCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCiudadActionPerformed

    private void jCheckBoxTipoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTipoEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxTipoEventoActionPerformed

    private void jFormattedTextFieldFechaEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFechaEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldFechaEventoActionPerformed

    private void jButtonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolverActionPerformed
        // TODO add your handling code here:
        Navegacion.cambiarVentana(this, new PortalCliente(cliente)); // Volver
    }//GEN-LAST:event_jButtonVolverActionPerformed

    private void jComboBoxTipoEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoEventoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoEventoActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBoxRangoPrecioEntrada1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRangoPrecioEntrada1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxRangoPrecioEntrada1ActionPerformed

    private void jButtonReiniciarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReiniciarFiltrosActionPerformed
        // TODO add your handling code here:
        resetearFiltros();
        JOptionPane.showMessageDialog(this, "Todos los filtros han sido restablecidos");
    }//GEN-LAST:event_jButtonReiniciarFiltrosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonReiniciarFiltros;
    private javax.swing.JButton jButtonVolver;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBoxCiudad;
    private javax.swing.JCheckBox jCheckBoxFechaEvento;
    private javax.swing.JCheckBox jCheckBoxPrecioEntrada;
    private javax.swing.JCheckBox jCheckBoxTipoEvento;
    private javax.swing.JCheckBox jCheckBoxTitulo;
    private javax.swing.JComboBox<String> jComboBoxRangoPrecioEntrada;
    private javax.swing.JComboBox<String> jComboBoxRangoPrecioEntrada1;
    private javax.swing.JComboBox<String> jComboBoxTipoEvento;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaEvento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelJavaEvents;
    private javax.swing.JLabel jLabelJavaEvents1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEventos;
    private javax.swing.JTextField jTextFieldCiudad;
    private javax.swing.JTextField jTextFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
