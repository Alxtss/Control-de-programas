package programa2;

import Conexion.Conexion;
import Conexion.bugReport;
import programa1.ListDataPrograma1;
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
import javax.mail.MessagingException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ListDataPrograma2 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form RegPrograma2
     */
    public ListDataPrograma2() throws ClassNotFoundException, SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getData();
        focusNewRow();
        customTablePrograma2();
        validarRegistroDelDia();
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnOk.setVisible(false);
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
            
            ResultSet rs = con.ejecutarSelect("select * from view_registroPrograma2");
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
            TablaPrograma2.setModel(modelo);
            con.desconectar();
        } catch (Exception e) {
            int dialog = JOptionPane.OK_OPTION;
            int warning = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showOptionDialog(null, "Error en el Servidor. \n No se han podido mostrar los datos", "Error", dialog, warning,null,new Object[]{"Reportar error"},"Reportar error");
            bugReport r = new bugReport();
            
            // enviar correo de reporte... modificar correo a conveniencia
            try {
                r.enviarReporte("correodeejemplo@gmail.com", "Reporte de fallos!", "Error de conexion a bd y/o servidor: "+e);
            } catch (MessagingException ex) {
                Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(e);
        }

    }

    public void validarRegistroDelDia() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_validarRegistroPrograma2()");
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void focusNewRow() {
        TablaPrograma2.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void customTablePrograma2() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < TablaPrograma2.getModel().getColumnCount(); i++) {
            TablaPrograma2.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TablaPrograma2.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        TablaPrograma2.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        TablaPrograma2.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        TablaPrograma2.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        TablaPrograma2.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);

        TablaPrograma2.setSelectionBackground(new Color(46, 204, 113));
        TablaPrograma2.setSelectionForeground(new Color(23, 32, 42));
        TablaPrograma2.setBackground(new Color(248, 249, 249));
        TablaPrograma2.setForeground(Color.darkGray);
        TablaPrograma2.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        TablaPrograma2.setDefaultEditor(Object.class, null);
        TablaPrograma2.setShowVerticalLines(false);

        TablaPrograma2.getTableHeader().setReorderingAllowed(false);
        TablaPrograma2.setRowHeight(38);
        TablaPrograma2.setGridColor(new java.awt.Color(0, 0, 0));

        TablaPrograma2.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaPrograma2.getColumnModel().getColumn(1).setPreferredWidth(400);
        TablaPrograma2.getColumnModel().getColumn(2).setPreferredWidth(300);
        TablaPrograma2.getColumnModel().getColumn(3).setPreferredWidth(240);
        TablaPrograma2.getColumnModel().getColumn(4).setPreferredWidth(350);

        TablaPrograma2.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaPrograma2.getColumnModel().getColumn(0).setMinWidth(0);
        TablaPrograma2.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        TablaPrograma2.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    public void buscarPorTitulo() {
        String filtro = txtBuscar.getText();
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select id as 'N° REG.',nombre_autor as 'AUTOR/A', "
                    + "titulo_mensaje as 'TITULO DEL MENSAJE', \n"
                    + "    date_format(fecha_emision,'%m-%d-%Y') as 'FECHA DEL DISCO', \n"
                    + "    date_format(fecha_programado,'%a %d - %M - %Y') as 'FECHA DE PROGRAMADO'\n"
                    + "    from registro_Programa2 where titulo_mensaje like '%" + filtro + "%' order by id desc");
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

            TablaPrograma2.setModel(modelo);

            con.desconectar();
            customTablePrograma2();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma2.class.getName()).log(Level.SEVERE, null, ex);
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
        TablaPrograma2 = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnEditList = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setBorder(null);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setForeground(new java.awt.Color(247, 247, 247));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaPrograma2.setBackground(new java.awt.Color(204, 204, 204));
        TablaPrograma2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaPrograma2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 840, 330));

        btnDelete.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(102, 102, 102));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDelete.setText("Borrar");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnDelete.setIconTextGap(0);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 220, -1, -1));

        btnEdit.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(102, 102, 102));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil.png"))); // NOI18N
        btnEdit.setText("Editar");
        btnEdit.setContentAreaFilled(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnEdit.setIconTextGap(0);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel2.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, -1, -1));

        btnEditList.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnEditList.setForeground(new java.awt.Color(102, 102, 102));
        btnEditList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        btnEditList.setText("Editar Lista");
        btnEditList.setContentAreaFilled(false);
        btnEditList.setIconTextGap(0);
        btnEditList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditListActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditList, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 190, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel1.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 60, 190, 330));

        txtBuscar.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 330, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lupa.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 30, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed

        try {
            DefaultTableModel modelo = (DefaultTableModel) TablaPrograma2.getModel();
            String id = String.valueOf(modelo.getValueAt(TablaPrograma2.getSelectedRow(), 0));
            
            if (TablaPrograma2.getSelectedRow() >= 0) {

                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar registro?", "Confirmacion", dialog);

                if (result == 0) {

                    btnDelete.setVisible(false);
                    btnEdit.setVisible(false);
                    btnEditList.setEnabled(true);
                    btnOk.setVisible(false);

                    try {
                        int id_fila = Integer.parseInt(id);

                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                        CallableStatement stm = con.prepareCall("call sp_deleteRegPrograma2(" + id_fila + ")");

                        stm.execute();

                        getData();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    focusNewRow();
                    customTablePrograma2();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar de la lista", "Error", icon);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        if (TablaPrograma2.getSelectedRow() >= 0) {
            btnDelete.setVisible(false);
            btnEdit.setVisible(false);
            btnOk.setVisible(false);
            ListDataPrograma2.txtBuscar.setEnabled(false);

            updateRegPrograma2 update = new updateRegPrograma2();
            MainPrograma2.dpMenu.add(update);
            update.toFront();
            update.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "seleccione un registro");
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditListActionPerformed
        btnDelete.setVisible(true);
        btnEdit.setVisible(true);
        btnEditList.setEnabled(false);
        btnOk.setVisible(true);
    }//GEN-LAST:event_btnEditListActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);
        btnEditList.setEnabled(true);
        btnOk.setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        String busqueda = txtBuscar.getText();
        if (busqueda.trim().equals("")) {
            try {
                getData();
                focusNewRow();
                customTablePrograma2();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            buscarPorTitulo();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TablaPrograma2;
    public static javax.swing.JButton btnDelete;
    public static javax.swing.JButton btnEdit;
    public static javax.swing.JButton btnEditList;
    public static javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
