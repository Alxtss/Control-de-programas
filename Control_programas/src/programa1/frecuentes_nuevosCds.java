package programa1;

import Conexion.Conexion;
import Conexion.Preferencias;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

public class frecuentes_nuevosCds extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form frecuentes
     */
    public frecuentes_nuevosCds() {
        initComponents();
        inicio();
    }

    public void inicio() {
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getListAutores();
        customTableListAutores();
        getFrecuentes();
        customTableFrecuentes();
        getNuevosCds();
        customTableNuevosCds();
        getDataInComboBox();
        spCantidadValidator();
        validarSeleccionLista();
        lblMensaje.setVisible(false);
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void getListAutores() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select * from view_autoresPrograma1");
            ResultSetMetaData md = rs.getMetaData();

            int columnas = md.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(md.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
                fila = null;
            }

            tbListAutores.setModel(modelo);
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customTableListAutores() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < tbListAutores.getModel().getColumnCount(); i++) {
            tbListAutores.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tbListAutores.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tbListAutores.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        tbListAutores.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);

        tbListAutores.setSelectionBackground(new Color(6, 204, 113));
        tbListAutores.setSelectionForeground(new Color(23, 32, 42));
        tbListAutores.getTableHeader().setResizingAllowed(false);
        tbListAutores.setBackground(new Color(248, 249, 249));
        tbListAutores.setForeground(Color.darkGray);

        tbListAutores.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        tbListAutores.setDefaultEditor(Object.class, null);
        tbListAutores.setShowVerticalLines(false);

        tbListAutores.getTableHeader().setReorderingAllowed(false);
        tbListAutores.setRowHeight(38);
        tbListAutores.setGridColor(new java.awt.Color(0, 0, 0));

        tbListAutores.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbListAutores.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbListAutores.getColumnModel().getColumn(2).setPreferredWidth(300);

        tbListAutores.getColumnModel().getColumn(0).setMaxWidth(0);
        tbListAutores.getColumnModel().getColumn(0).setMinWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbListAutores.getColumnModel().getColumn(2).setMaxWidth(0);
        tbListAutores.getColumnModel().getColumn(2).setMinWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

    }

    public void getFrecuentes() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select * from view_autoresFrecuentes");
            ResultSetMetaData md = rs.getMetaData();

            int columnas = md.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(md.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
                fila = null;
            }

            tbFrecuentes.setModel(modelo);
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customTableFrecuentes() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < tbFrecuentes.getModel().getColumnCount(); i++) {
            tbFrecuentes.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tbFrecuentes.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tbFrecuentes.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

        tbFrecuentes.setSelectionBackground(new Color(239, 234, 84));
        tbFrecuentes.setSelectionForeground(new Color(23, 32, 42));
        tbFrecuentes.getTableHeader().setResizingAllowed(false);
        tbFrecuentes.setBackground(new Color(248, 249, 249));
        tbFrecuentes.setForeground(Color.darkGray);

        tbFrecuentes.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        tbFrecuentes.setDefaultEditor(Object.class, null);
        tbFrecuentes.setShowVerticalLines(false);

        tbFrecuentes.getTableHeader().setReorderingAllowed(false);
        tbFrecuentes.setRowHeight(38);
        tbFrecuentes.setGridColor(new java.awt.Color(0, 0, 0));

        tbFrecuentes.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbFrecuentes.getColumnModel().getColumn(1).setPreferredWidth(500);

        tbFrecuentes.getColumnModel().getColumn(0).setMaxWidth(0);
        tbFrecuentes.getColumnModel().getColumn(0).setMinWidth(0);
        tbFrecuentes.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbFrecuentes.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public void focusRowEdited(int fila) {
        tbListAutores.getSelectionModel().setSelectionInterval(fila, fila);
    }

    public void addFrecuentes() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tbListAutores.getModel();
            String nombre = String.valueOf(modelo.getValueAt(tbListAutores.getSelectedRow(), 1));
            int fila = tbListAutores.getSelectedRow();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_insertFrecuentes(?)");

            stm.setString(1, nombre);
            stm.execute();
            getListAutores();
            customTableListAutores();
            getFrecuentes();
            customTableFrecuentes();
            focusRowEdited(fila);
        } catch (SQLException ex) {
            Logger.getLogger(regPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteFrecuentes() {
        DefaultTableModel modelo = (DefaultTableModel) tbFrecuentes.getModel();

        String id = String.valueOf(modelo.getValueAt(tbFrecuentes.getSelectedRow(), 0));
        String nombre = String.valueOf(modelo.getValueAt(tbFrecuentes.getSelectedRow(), 1));
        int id_fila = Integer.parseInt(id);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_deleteOfListFrecuentes(?,?)");
            stm.setInt(1, id_fila);
            stm.setString(2, nombre);
            stm.execute();

            getListAutores();
            customTableListAutores();
            getFrecuentes();
            customTableFrecuentes();
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getNuevosCds() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select * from view_nuevosCds");
            ResultSetMetaData md = rs.getMetaData();

            int columnas = md.getColumnCount();
            for (int i = 1; i <= columnas; i++) {
                modelo.addColumn(md.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
                fila = null;
            }

            tbNuevosCds.setModel(modelo);

            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteOfNuevosCds() {
        DefaultTableModel modelo = (DefaultTableModel) tbNuevosCds.getModel();

        String id = String.valueOf(modelo.getValueAt(tbNuevosCds.getSelectedRow(), 0));
        int id_fila = Integer.parseInt(id);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("delete from nuevos_cds where id=" + id_fila);

            stm.execute();
            
            getNuevosCds();
            customTableNuevosCds();
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vaciarTablaNuevosCds() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("truncate table nuevos_cds");

            stm.execute();

            getNuevosCds();
            customTableNuevosCds();
        } catch (HeadlessException | SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customTableNuevosCds() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < tbNuevosCds.getModel().getColumnCount(); i++) {
            tbNuevosCds.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tbNuevosCds.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tbNuevosCds.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        tbNuevosCds.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        tbNuevosCds.setSelectionBackground(new Color(53, 147, 64));
        tbNuevosCds.setSelectionForeground(Color.WHITE);
        tbNuevosCds.getTableHeader().setResizingAllowed(false);
        tbNuevosCds.setBackground(new Color(248, 249, 249));
        tbNuevosCds.setForeground(Color.darkGray);

        tbNuevosCds.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        tbNuevosCds.setDefaultEditor(Object.class, null);
        tbNuevosCds.setShowVerticalLines(false);

        tbNuevosCds.getTableHeader().setReorderingAllowed(false);
        tbNuevosCds.setRowHeight(38);
        tbNuevosCds.setGridColor(new java.awt.Color(0, 0, 0));

        tbNuevosCds.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbNuevosCds.getColumnModel().getColumn(1).setPreferredWidth(500);
        tbNuevosCds.getColumnModel().getColumn(2).setPreferredWidth(150);

        tbNuevosCds.getColumnModel().getColumn(0).setMaxWidth(0);
        tbNuevosCds.getColumnModel().getColumn(0).setMinWidth(0);
        tbNuevosCds.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbNuevosCds.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public void addNewCds() {

        String nombre = cbAutores.getSelectedItem().toString();
        int cantidad = (Integer) spCantidad.getValue();

        if (nombre.equals("Seleccione")) {
            lblMensaje.setText("SELECCIONE UN AUTOR!");
            lblMensaje.setVisible(true);
        } else {
            if (cantidad > 0) {
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                    CallableStatement stm = con.prepareCall("call sp_insertNuevosCds(?,?);");

                    stm.setString(1, nombre);
                    stm.setInt(2, cantidad);
                    stm.execute();
                    getNuevosCds();
                    customTableNuevosCds();
                    cbAutores.setSelectedItem("Seleccione");
                    spCantidad.setValue(1);
                } catch (SQLException ex) {
                    Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                lblMensaje.setText("LA CANTIDAD DE CDS NO PUEDE SER CERO!");
            }
        }
    }

    public void getDataInComboBox() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_autoresPrograma1");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }

            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void spCantidadValidator() {
        SpinnerNumberModel modelo = new SpinnerNumberModel();
        modelo.setMaximum(50);
        modelo.setMinimum(0);
        spCantidad.setModel(modelo);

        JFormattedTextField cantidad = ((JSpinner.NumberEditor) spCantidad.getEditor()).getTextField();
        ((NumberFormatter) cantidad.getFormatter()).setAllowsInvalid(false);
    }

    public void validarSeleccionLista() {
        Preferencias p = new Preferencias();
        p.getPreferences();

        if (regPrograma1.listValidator == 1) {
            btnSelectList2.setEnabled(true);
            btnSelectList.setEnabled(false);
        }
        if (regPrograma1.listValidator == 2) {
            btnSelectList2.setEnabled(false);
            btnSelectList.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JTabbedPane();
        frecuentes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListAutores = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbFrecuentes = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelOfFrecuentes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSelectList = new javax.swing.JButton();
        nuevosCds = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbNuevosCds = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        cbAutores = new javax.swing.JComboBox<>();
        spCantidad = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnSelectList2 = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setForeground(new java.awt.Color(102, 102, 102));
        menu.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N

        frecuentes.setBackground(new java.awt.Color(247, 247, 247));
        frecuentes.setForeground(new java.awt.Color(102, 102, 102));
        frecuentes.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        frecuentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbListAutores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbListAutores);

        frecuentes.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 400, 250));

        tbFrecuentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbFrecuentes);

        frecuentes.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 390, 250));

        btnAdd.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(102, 102, 102));
        btnAdd.setText(">>");
        btnAdd.setContentAreaFilled(false);
        btnAdd.setFocusPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        frecuentes.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 60, 50));

        btnDelOfFrecuentes.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnDelOfFrecuentes.setForeground(new java.awt.Color(102, 102, 102));
        btnDelOfFrecuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelOfFrecuentes.setText("Borrar");
        btnDelOfFrecuentes.setContentAreaFilled(false);
        btnDelOfFrecuentes.setFocusPainted(false);
        btnDelOfFrecuentes.setIconTextGap(0);
        btnDelOfFrecuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelOfFrecuentesActionPerformed(evt);
            }
        });
        frecuentes.add(btnDelOfFrecuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 160, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AUTORES FRECUENTES");
        frecuentes.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 390, 30));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LISTA DE AUTORES");
        frecuentes.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 400, 30));

        btnSelectList.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnSelectList.setForeground(new java.awt.Color(102, 102, 102));
        btnSelectList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnSelectList.setText("Programar de esta lista");
        btnSelectList.setContentAreaFilled(false);
        btnSelectList.setFocusPainted(false);
        btnSelectList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectListActionPerformed(evt);
            }
        });
        frecuentes.add(btnSelectList, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, -1, -1));

        menu.addTab("Autores Frecuentes", frecuentes);

        nuevosCds.setBackground(new java.awt.Color(247, 247, 247));
        nuevosCds.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("AGREGAR REGISTRO DE NUEVOS CD'S");
        nuevosCds.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 349, 33));

        tbNuevosCds.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbNuevosCds);

        nuevosCds.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 400, 240));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Cantidad de Cds:");
        nuevosCds.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        cbAutores.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        nuevosCds.add(cbAutores, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 210, 40));

        spCantidad.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        nuevosCds.add(spCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 60, 40));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Predicador:");
        nuevosCds.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(102, 102, 102));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checkmark.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        nuevosCds.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 140, 60));

        btnBorrar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnBorrar.setForeground(new java.awt.Color(102, 102, 102));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.setContentAreaFilled(false);
        btnBorrar.setFocusPainted(false);
        btnBorrar.setIconTextGap(0);
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        nuevosCds.add(btnBorrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 150, -1, -1));

        btnLimpiar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(102, 102, 102));
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnLimpiar.setText("Limpiar Tabla");
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        nuevosCds.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, -1, -1));

        btnSelectList2.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnSelectList2.setForeground(new java.awt.Color(102, 102, 102));
        btnSelectList2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnSelectList2.setText("Programar de esta lista");
        btnSelectList2.setContentAreaFilled(false);
        btnSelectList2.setFocusPainted(false);
        btnSelectList2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectList2ActionPerformed(evt);
            }
        });
        nuevosCds.add(btnSelectList2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, -1, -1));

        lblMensaje.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 51, 0));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("msg");
        nuevosCds.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 280, -1));

        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nuevosCds.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 180, 130));

        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nuevosCds.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 750, 260));

        menu.addTab("Nuevos CD's", nuevosCds);

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1040, 400));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (tbListAutores.getSelectedRow() >= 0) {
            addFrecuentes();
        } else {
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "seleccione un nombre para añadir a la lista de Autores Frecuentes", "Error", icon);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDelOfFrecuentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelOfFrecuentesActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) tbFrecuentes.getModel();
            String nombre = String.valueOf(modelo.getValueAt(tbFrecuentes.getSelectedRow(), 1));

            if (tbFrecuentes.getSelectedRow() >= 0) {
                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar a " + nombre + " de la lista de Autores Frecuentes?", "Confirmacion", dialog);

                if (result == 0) {
                    deleteFrecuentes();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un nombre de la Lista de Autores Frecuentes para borrar", "Error", icon);
        }
    }//GEN-LAST:event_btnDelOfFrecuentesActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        addNewCds();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) tbNuevosCds.getModel();
            String nombre = String.valueOf(modelo.getValueAt(tbNuevosCds.getSelectedRow(), 1));
            
            if (tbNuevosCds.getSelectedRow() >= 0) {
                
                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar a " + nombre + " de la lista de Nuevos Cds?", "Confirmacion", dialog);
                
                if (result == 0) {
                    deleteOfNuevosCds();
                }
                
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un nombre de la Lista de Nuevos Cds para borrar", "Error", icon);
        }
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed

        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vaciar Lista de Nuevos Cds? \n Esta accion borrara toda la lista de Nuevos Cds", "Confirmacion", dialog);

        if (result == 0) {
            vaciarTablaNuevosCds();
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSelectListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectListActionPerformed
        regPrograma1.listValidator = 1;
        btnSelectList.setEnabled(false);
        btnSelectList2.setEnabled(true);
        Preferencias p = new Preferencias();
        p.preferencias();
    }//GEN-LAST:event_btnSelectListActionPerformed

    private void btnSelectList2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectList2ActionPerformed
        regPrograma1.listValidator = 2;
        btnSelectList.setEnabled(true);
        btnSelectList2.setEnabled(false);
        Preferencias p = new Preferencias();
        p.preferencias();
    }//GEN-LAST:event_btnSelectList2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnDelOfFrecuentes;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSelectList;
    private javax.swing.JButton btnSelectList2;
    private javax.swing.JComboBox<String> cbAutores;
    private javax.swing.JPanel frecuentes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTabbedPane menu;
    private javax.swing.JPanel nuevosCds;
    private javax.swing.JSpinner spCantidad;
    private javax.swing.JTable tbFrecuentes;
    private javax.swing.JTable tbListAutores;
    private javax.swing.JTable tbNuevosCds;
    // End of variables declaration//GEN-END:variables
}
