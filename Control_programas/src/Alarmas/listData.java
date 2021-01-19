package Alarmas;

import static Alarmas.mainAlarmas.dpView;
import Conexion.Conexion;
import Conexion.bugReport;
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

/**
 *
 * @author Aless
 */
public class listData extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form listData
     */
    public listData() throws ClassNotFoundException, SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getData();
        focusNewRow();
        customTableAlarmas();
        btnEliminar.setVisible(false);
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
            ResultSet rs = con.ejecutarSelect("select * from view_alarmas");
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
            TablaAlarmas.setModel(modelo);
            con.desconectar();
        } catch (Exception e) {
            int dialog = JOptionPane.OK_OPTION;
            int warning = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showOptionDialog(null, "Error en el Servidor. \n No se han podido mostrar los datos", "Error", dialog, warning,null,new Object[]{"Reportar error"},"Reportar error");
            bugReport r = new bugReport();
            
            //reporte de fallas por correo... modificar direccion de correo a conveniencia
            try {
                r.enviarReporte("correodeejemplo@gmail.com", "Reporte de fallos!", "Error de conexion a bd y/o servidor: "+e);
            } catch (MessagingException ex) {
                Logger.getLogger(listData.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(e);
        }
    }

    public void focusNewRow() {
        TablaAlarmas.getSelectionModel().setSelectionInterval(0, 0);
    }

    public void customTableAlarmas() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < TablaAlarmas.getModel().getColumnCount(); i++) {
            TablaAlarmas.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TablaAlarmas.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        TablaAlarmas.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        TablaAlarmas.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        TablaAlarmas.setSelectionBackground(new Color(254, 250, 127));
        TablaAlarmas.setSelectionForeground(new Color(23, 32, 42));
//        TablaAlarmas.getTableHeader().setResizingAllowed(false);
        TablaAlarmas.setBackground(new Color(248, 249, 249));
        TablaAlarmas.setForeground(Color.darkGray);
        TablaAlarmas.setFont(new java.awt.Font("Tw Cen MT", 0, 17));
        TablaAlarmas.setDefaultEditor(Object.class, null);
        TablaAlarmas.setShowVerticalLines(false);

        TablaAlarmas.getTableHeader().setReorderingAllowed(false);
        TablaAlarmas.setRowHeight(38);
        TablaAlarmas.setGridColor(new java.awt.Color(0, 0, 0));

        TablaAlarmas.getColumnModel().getColumn(0).setPreferredWidth(0);
        TablaAlarmas.getColumnModel().getColumn(1).setPreferredWidth(680);
        TablaAlarmas.getColumnModel().getColumn(2).setPreferredWidth(400);

        TablaAlarmas.getColumnModel().getColumn(0).setMaxWidth(0);
        TablaAlarmas.getColumnModel().getColumn(0).setMinWidth(0);
        TablaAlarmas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        TablaAlarmas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAlarmas = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel.setBackground(new java.awt.Color(247, 247, 247));
        jPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaAlarmas.setBackground(new java.awt.Color(204, 204, 204));
        TablaAlarmas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaAlarmas);

        jPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 740, 280));

        btnBack.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnBack.setForeground(new java.awt.Color(102, 102, 102));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnBack.setText("REGRESAR");
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(102, 102, 102));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnEliminar.setText("Borrar");
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setIconTextGap(0);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        jPanel3.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 50, 50));

        btnEdit.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(102, 102, 102));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/document.png"))); // NOI18N
        btnEdit.setText("Editar Lista");
        btnEdit.setContentAreaFilled(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel3.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, -1));

        jPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 180, 280));

        getContentPane().add(jPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Borrar?", "Confirmacion", dialog);

        if (result == 0) {
            btnEliminar.setVisible(false);
            btnOk.setVisible(false);
            btnEdit.setEnabled(true);

            DefaultTableModel modelo = (DefaultTableModel) TablaAlarmas.getModel();

            String id = String.valueOf(modelo.getValueAt(TablaAlarmas.getSelectedRow(), 0));

            try {
                int id_fila = Integer.parseInt(id);

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                CallableStatement stm = con.prepareCall("call sp_deleteAlarmas(" + id_fila + ")");

                stm.execute();

                getData();
                customTableAlarmas();
            } catch (SQLException ex) {
                Logger.getLogger(listData.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(listData.class.getName()).log(Level.SEVERE, null, ex);
            }

            focusNewRow();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        setActionsAlarmas menu = new setActionsAlarmas();
        dpView.add(menu);
        menu.toFront();
        menu.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        btnEliminar.setVisible(true);
        btnEdit.setEnabled(false);
        btnOk.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        btnEliminar.setVisible(false);
        btnEdit.setEnabled(true);
        btnOk.setVisible(false);
    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TablaAlarmas;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnOk;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
