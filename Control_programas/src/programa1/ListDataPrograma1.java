package programa1;

import Conexion.Conexion;
import Conexion.bugReport;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ListDataPrograma1 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private PreparedStatement pstm;

    public ListDataPrograma1() throws ClassNotFoundException, SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getData();
        focusNewRow();
        customTablePrograma1();
        ocultarSubMenu();
        validarRegistroDelDia();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void getData() throws ClassNotFoundException, SQLException {
        try {
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
        } catch (Exception e) {
            int dialog = JOptionPane.OK_OPTION;
            int warning = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showOptionDialog(null, "Error en el Servidor. \n No se han podido mostrar los datos", "Error", dialog, warning,null,new Object[]{"Reportar error"},"Reportar error");
            bugReport r = new bugReport();
            
            //reporte de errores por correo... modificar correo a conveniencia
            try {
                r.enviarReporte("correodeejemplo@gmail.com", "Reporte de fallos!", "Error de conexion a bd y/o servidor: "+e);
            } catch (MessagingException ex) {
                Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(e);
        }
    }

    public void ocultarSubMenu() {
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnNoProgramar.setVisible(false);
        btnOk.setVisible(false);
    }

    public void validarRegistroDelDia() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_validarRegistroPrograma1()");
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customTablePrograma1() {

        Font f = new Font("Verdana", Font.BOLD, 15);

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
        TablaPrograma1.setShowVerticalLines(false);

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

    public void focusNewRow() {
        TablaPrograma1.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void addToBlackList() {

        DefaultTableModel modelo = (DefaultTableModel) TablaPrograma1.getModel();

        String nombre = String.valueOf(modelo.getValueAt(TablaPrograma1.getSelectedRow(), 1));

        if (nombre.equals("-- SIN REGISTRAR!! --")) {
            JOptionPane.showMessageDialog(null, "No se puede agregar un registro vacio!");
        } else {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                CallableStatement stm = con.prepareCall("call sp_insertBlackList(?)");
//        
                stm.setString(1, nombre);
                stm.execute();
                JOptionPane.showMessageDialog(null, "Se agrego a " + nombre + " a la lista No Programar");
                btnEditList.setEnabled(true);
            } catch (SQLException ex) {
                Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscarAutores() {
        String filtro = txtBuscar.getText();
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select id, nombre_autor as 'AUTOR/A',"
                    + "date_format(fecha_programado,'%a %d - %M - %Y') as 'FECHA DE PROGRAMADO' "
                    + "from registro_Programa1 where nombre_autor like '%" + filtro + "%' order by id desc");
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
            customTablePrograma1();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPrograma1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnNoProgramar = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnEditList = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaPrograma1.setBackground(new java.awt.Color(204, 204, 204));
        TablaPrograma1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaPrograma1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 720, 330));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(235, 235, 235)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDelete.setBackground(new java.awt.Color(102, 102, 102));
        btnDelete.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(102, 102, 102));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setText("Borrar");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setIconTextGap(0);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        btnEdit.setBackground(new java.awt.Color(102, 102, 102));
        btnEdit.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(102, 102, 102));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.setContentAreaFilled(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setIconTextGap(0);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel1.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        btnNoProgramar.setBackground(new java.awt.Color(102, 102, 102));
        btnNoProgramar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnNoProgramar.setForeground(new java.awt.Color(102, 102, 102));
        btnNoProgramar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnNoProgramar.setText("No Programar");
        btnNoProgramar.setContentAreaFilled(false);
        btnNoProgramar.setFocusPainted(false);
        btnNoProgramar.setIconTextGap(2);
        btnNoProgramar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoProgramarActionPerformed(evt);
            }
        });
        jPanel1.add(btnNoProgramar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel1.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        btnEditList.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnEditList.setForeground(new java.awt.Color(102, 102, 102));
        btnEditList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        btnEditList.setText("Editar Lista");
        btnEditList.setContentAreaFilled(false);
        btnEditList.setFocusPainted(false);
        btnEditList.setIconTextGap(0);
        btnEditList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditListActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 50, 190, 330));

        txtBuscar.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 24, 230, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lupa.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 24, 30, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) TablaPrograma1.getModel();
            String id = String.valueOf(modelo.getValueAt(TablaPrograma1.getSelectedRow(), 0));

            if (TablaPrograma1.getSelectedRow() >= 0) {

                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar registro?", "Confirmacion", dialog);

                if (result == 0) {
                    btnDelete.setVisible(false);
                    btnEdit.setVisible(false);
                    btnNoProgramar.setVisible(false);
                    btnEditList.setEnabled(true);
                    btnOk.setVisible(false);

                    try {
                        int id_fila = Integer.parseInt(id);

                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                        CallableStatement stm = con.prepareCall("call sp_deleteRegPrograma1(" + id_fila + ")");

                        stm.execute();

                        getData();
                        customTablePrograma1();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    focusNewRow();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar de la lista", "Error", icon);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        if (TablaPrograma1.getSelectedRow() >= 0) {
            btnDelete.setVisible(false);
            btnEdit.setVisible(false);
            btnNoProgramar.setVisible(false);
            btnOk.setVisible(false);
            ListDataPrograma1.txtBuscar.setEnabled(false);

            updateRegPrograma1 update = new updateRegPrograma1();
            mainPrograma1.dpMenu.add(update);
            update.toFront();
            update.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditListActionPerformed
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);
        btnNoProgramar.setVisible(true);
        btnEditList.setEnabled(false);
        btnOk.setVisible(true);
    }//GEN-LAST:event_btnEditListActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnNoProgramar.setVisible(false);
        btnEditList.setEnabled(true);
        btnOk.setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnNoProgramarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoProgramarActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) TablaPrograma1.getModel();
            String nombre = String.valueOf(modelo.getValueAt(TablaPrograma1.getSelectedRow(), 1));
            if (TablaPrograma1.getSelectedRow() >= 0) {

                if (nombre.equals("-- SIN REGISTRAR!! --")) {
                    int icon = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog(null, "No se puede seleccionar un registro vacio!", "Error", icon);
                } else {

                    int dialog = JOptionPane.YES_NO_OPTION;
                    int result = JOptionPane.showConfirmDialog(null, "Agregar a " + nombre + " la lista de No Programar?", "Confirmacion", dialog);

                    if (result == 0) {
                        btnEdit.setVisible(false);
                        btnDelete.setVisible(false);
                        btnNoProgramar.setVisible(false);
                        btnEditList.setEnabled(true);
                        btnOk.setVisible(false);
                        addToBlackList();
                        setActionsPrograma1 menu = new setActionsPrograma1();
                        mainPrograma1.dpMenu.add(menu);
                        menu.toFront();
                        menu.setVisible(true);
                        menu.focusNewRow();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un nombre para mover a la lista No Programar", "Error", icon);
        }
    }//GEN-LAST:event_btnNoProgramarActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped

        String busqueda = txtBuscar.getText();
        if (busqueda.trim().equals("")) {
            try {
                getData();
                focusNewRow();
                customTablePrograma1();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            buscarAutores();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TablaPrograma1;
    public static javax.swing.JButton btnDelete;
    public static javax.swing.JButton btnEdit;
    public static javax.swing.JButton btnEditList;
    public static javax.swing.JButton btnNoProgramar;
    public static javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
