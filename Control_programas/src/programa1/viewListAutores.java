package programa1;

import Conexion.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class viewListAutores extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public viewListAutores() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getListAutores();
        customTableListAutores();
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
        tbListAutores.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        tbListAutores.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);

        tbListAutores.setSelectionBackground(new Color(46, 204, 113));
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
        tbListAutores.getColumnModel().getColumn(1).setPreferredWidth(500);
        tbListAutores.getColumnModel().getColumn(2).setPreferredWidth(300);
        tbListAutores.getColumnModel().getColumn(3).setPreferredWidth(300);

        tbListAutores.getColumnModel().getColumn(0).setMaxWidth(0);
        tbListAutores.getColumnModel().getColumn(0).setMinWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbListAutores.getColumnModel().getColumn(3).setMaxWidth(0);
        tbListAutores.getColumnModel().getColumn(3).setMinWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tbListAutores.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

    }

    public void addToBlackList() {

        DefaultTableModel modelo = (DefaultTableModel) tbListAutores.getModel();

        String nombre = String.valueOf(modelo.getValueAt(tbListAutores.getSelectedRow(), 1));

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_insertBlackList(?)");

            stm.setString(1, nombre);
            stm.execute();
            JOptionPane.showMessageDialog(null, "Se agrego a " + nombre + " a la lista No Programar");
            getListAutores();
            customTableListAutores();
            txtBuscar.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNewAutor() {
        String nombre = txtNombre.getText();
        String codProgram = "P1";

        if (nombre.trim().equals("")) {
            lblMensaje.setText("LLENE LOS RECUADROS!");
            lblMensaje.setVisible(true);
            txtNombre.requestFocus();
        } else {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                CallableStatement stm = con.prepareCall("call sp_insertAutores(?,?)");

                stm.setString(1, nombre);
                stm.setString(2, codProgram);
                stm.execute();
                getListAutores();
                customTableListAutores();
                focusNewRow();
                txtNombre.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(viewListAutores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscarAutores() {
        String filtro = txtBuscar.getText();
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select id as 'ID',nombre_autor as 'NOMBRE DEL AUTOR/A', estado as 'ESTADO',\n"
                    + "    detalle as 'DETALLE' from autores where estado='Activo' and nombre_autor like '%" + filtro + "%'");
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
            customTableListAutores();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void focusNewRow() {
        tbListAutores.scrollRectToVisible(tbListAutores.getCellRect(tbListAutores.getRowCount() - 1, 0, true));
        tbListAutores.getSelectionModel().setSelectionInterval(tbListAutores.getRowCount() - 1, tbListAutores.getRowCount() - 1);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListAutores = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnNoProgramar = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 570, 300));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EDITAR REGISTROS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Nombre:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        lblMensaje.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 51, 51));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("msg");
        jPanel1.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 70, 200, 20));

        txtNombre.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 90, 210, 40));

        btnAdd.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(102, 102, 102));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnAdd.setText("Agregar ");
        btnAdd.setContentAreaFilled(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel1.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, -1, -1));

        btnNoProgramar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnNoProgramar.setForeground(new java.awt.Color(102, 102, 102));
        btnNoProgramar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnNoProgramar.setText("No Programar");
        btnNoProgramar.setContentAreaFilled(false);
        btnNoProgramar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNoProgramar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoProgramarActionPerformed(evt);
            }
        });
        jPanel1.add(btnNoProgramar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, -1, -1));

        btnDelete.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(102, 102, 102));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setText("Borrar");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 250, -1, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 310, 130));

        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 310, 120));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AGREGAR REGISTROS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        txtBuscar.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.setToolTipText("");
        txtBuscar.setName(""); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 240, 40));
        txtBuscar.getAccessibleContext().setAccessibleName("");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lupa.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 30, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        addNewAutor();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnNoProgramarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoProgramarActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) tbListAutores.getModel();
            String nombre = String.valueOf(modelo.getValueAt(tbListAutores.getSelectedRow(), 1));
            
            if (tbListAutores.getSelectedRow() >= 0) {
                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Mover a " + nombre + " a la lista No Programar?", "Confirmacion", dialog);

                if (result == 0) {
                    addToBlackList();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione nombre para añadir a la lista No Programar!", "Error", icon);
        }
    }//GEN-LAST:event_btnNoProgramarActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) tbListAutores.getModel();
            String nombre = String.valueOf(modelo.getValueAt(tbListAutores.getSelectedRow(), 1));

            if (tbListAutores.getSelectedRow() >= 0) {

                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar a " + nombre + " de la Lista de Autores?", "Confirmacion", dialog);

                if (result == 0) {
                    String id = String.valueOf(modelo.getValueAt(tbListAutores.getSelectedRow(), 0));

                    try {
                        int id_fila = Integer.parseInt(id);

                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                        CallableStatement stm = con.prepareCall("call sp_deleteAutores(" + id_fila + ")");

                        stm.execute();
                        getListAutores();
                        customTableListAutores();
                        txtBuscar.setText("");
                    } catch (SQLException ex) {
                        Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un nombre para borrar de la Lista de Autores", "Error", icon);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        buscarAutores();
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNoProgramar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tbListAutores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
