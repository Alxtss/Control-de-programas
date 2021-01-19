package programa1;

import Conexion.Conexion;
import Conexion.Preferencias;
import static programa1.regPrograma1.listValidator;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static programa1.ListDataPrograma1.TablaPrograma1;

public class updateRegPrograma1 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    private PreparedStatement pstm;

    public updateRegPrograma1() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        lblMensaje.setVisible(false);
        txtNombre.setVisible(false);
        setValuesToEdit();
        mostrarListaAutores();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void focusNewRow() {
        TablaPrograma1.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void focusRowEdited(int fila) {
        TablaPrograma1.getSelectionModel().setSelectionInterval(fila, fila);
    }

    public void goToMenu() {
        setActionsPrograma1 menu = new setActionsPrograma1();
        mainPrograma1.dpMenu.add(menu);
        menu.toFront();
        menu.setVisible(true);
    }

    public void setValuesToEdit() {

        DefaultTableModel modelo = (DefaultTableModel) TablaPrograma1.getModel();
        String nombre = String.valueOf(modelo.getValueAt(TablaPrograma1.getSelectedRow(), 1));

        if (nombre.equals("-- SIN REGISTRAR!! --")) {
            txtNombre.setText("");
        } else {
            txtNombre.setText(nombre);
            txtNombre.setVisible(true);
        }

    }

    public void getData() throws ClassNotFoundException, SQLException {
        Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
        DefaultTableModel modelo = new DefaultTableModel();
        ResultSet rs = con.ejecutarSelect("select * from view_registroPrograma1");
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
        TablaPrograma1.setModel(modelo);
        con.desconectar();

    }

    public void customTablePrograma1() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < TablaPrograma1.getModel().getColumnCount(); i++) {
            TablaPrograma1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TablaPrograma1.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        TablaPrograma1.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        TablaPrograma1.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        TablaPrograma1.setSelectionBackground(new Color(46, 204, 113));
        TablaPrograma1.setSelectionForeground(new Color(23, 32, 42));
        TablaPrograma1.getTableHeader().setResizingAllowed(false);
        TablaPrograma1.setBackground(new Color(248, 249, 249));
        TablaPrograma1.setForeground(Color.darkGray);
        TablaPrograma1.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        TablaPrograma1.setDefaultEditor(Object.class, null);

        TablaPrograma1.getTableHeader().setReorderingAllowed(false);
        TablaPrograma1.setRowHeight(38);
        TablaPrograma1.setGridColor(new java.awt.Color(0, 0, 0));

        TablaPrograma1.getColumnModel().getColumn(0).setPreferredWidth(100);
        TablaPrograma1.getColumnModel().getColumn(1).setPreferredWidth(500);
        TablaPrograma1.getColumnModel().getColumn(2).setPreferredWidth(500);

        TablaPrograma1.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaPrograma1.getColumnModel().getColumn(0).setMinWidth(0);
        TablaPrograma1.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        TablaPrograma1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public void ListAutoresNoFrecuentes() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_autoresNoFrecuentes");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListAutoresFrecuentes() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_filtroAutoresFrecuentes");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getListAutores() {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_WEEK);

        switch (dia) {
            case 1:
                ListAutoresFrecuentes();
                break;
            case 2:
                ListAutoresNoFrecuentes();
                break;
            case 3:
                ListAutoresFrecuentes();
                break;
            case 4:
                ListAutoresNoFrecuentes();
                break;
            case 5:
                ListAutoresFrecuentes();
                break;
            case 6:
                ListAutoresNoFrecuentes();
                break;
            case 7:
                ListAutoresFrecuentes();
                break;
            default:
                ListAutoresNoFrecuentes();
                break;
        }
        System.out.println(dia);
    }

    public void ListNuevosCds() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_filtroNuevosCds");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarListaAutores() {
        Preferencias p = new Preferencias();
        p.getPreferences();
        if (listValidator == 1) {
            getListAutores();
        } else {

            if (listValidator == 2) {
                ListNuevosCds();
            } else {
                ListAutoresNoFrecuentes();
            }
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
        jLabel1 = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cbAutores = new javax.swing.JComboBox<>();
        lblNomAutor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre del Autor/a:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 20));

        lblMensaje.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 51, 51));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("msg");
        jPanel1.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 340, -1));

        txtNombre.setEditable(false);
        txtNombre.setBackground(new java.awt.Color(247, 247, 247));
        txtNombre.setFont(new java.awt.Font("Tw Cen MT", 0, 22)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 204, 51));
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNombre.setBorder(null);
        txtNombre.setCaretColor(new java.awt.Color(0, 204, 51));
        txtNombre.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtNombre.setSelectedTextColor(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 260, 30));

        btnUpdate.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(102, 102, 102));
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checkmark.png"))); // NOI18N
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 160, 50));

        btnCancel.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(102, 102, 102));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnCancel.setText("CANCELAR");
        btnCancel.setContentAreaFilled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 150, 50));

        cbAutores.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        cbAutores.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(cbAutores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 240, 40));

        lblNomAutor.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lblNomAutor.setForeground(new java.awt.Color(102, 102, 102));
        lblNomAutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomAutor.setText("EDITANDO REGISTRO:");
        jPanel1.add(lblNomAutor, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 120, 170, 30));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 550, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1040, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        setActionsPrograma1 menu = new setActionsPrograma1();
        mainPrograma1.dpMenu.add(menu);
        menu.toFront();
        menu.setVisible(true);
        ListDataPrograma1.btnEditList.setEnabled(true);
        ListDataPrograma1.txtBuscar.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        DefaultTableModel modelo = (DefaultTableModel) TablaPrograma1.getModel();

        String id = String.valueOf(modelo.getValueAt(TablaPrograma1.getSelectedRow(), 0));
        String nombre = cbAutores.getSelectedItem().toString();
        String validarNombre = txtNombre.getText();
        int fila = TablaPrograma1.getSelectedRow();

        if (nombre.equals("Seleccione")) {
            lblMensaje.setVisible(true);
            lblMensaje.setText("SELECCIONE UN DATO");
            cbAutores.requestFocus();
        } else {
            if (nombre.equals(validarNombre)) {
                lblMensaje.setVisible(true);
                lblMensaje.setText("ESTA SELECCIONANDO EL MISMO NOMBRE!");
            } else {
                try {
                    lblMensaje.setVisible(false);
                    txtNombre.setVisible(false);
                    lblNomAutor.setVisible(false);
                    String sql = "call sp_editRegistroPrograma1(?,?)";

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");

                    pstm = con.prepareStatement(sql);
                    pstm.setString(1, id);
                    pstm.setString(2, nombre);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Registro modificado!");
                    getData();
                    goToMenu();
                    focusRowEdited(fila);
                    customTablePrograma1();
                    ListDataPrograma1.btnEditList.setEnabled(true);
                    ListDataPrograma1.txtBuscar.setEnabled(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(updateRegPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAutores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNomAutor;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
