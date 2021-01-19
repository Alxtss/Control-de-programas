package programa1;

import Conexion.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class viewBlackList extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private PreparedStatement pstm;

    /**
     * Creates new form viewBlackList
     */
    public viewBlackList() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getBlackList();
        customTableBlackList();
//        focusNewRow();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public void getBlackList() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            DefaultTableModel modelo = new DefaultTableModel();
            ResultSet rs = con.ejecutarSelect("select * from view_blackList");
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

            tbBlackList.setModel(modelo);

            con.desconectar();
//            focusNewRow();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customTableBlackList() {

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(23, 32, 42));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setPreferredSize(new java.awt.Dimension(0, 50));

        for (int i = 0; i < tbBlackList.getModel().getColumnCount(); i++) {
            tbBlackList.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tbBlackList.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tbBlackList.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        tbBlackList.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

        tbBlackList.setSelectionBackground(new Color(211, 84, 0));
        tbBlackList.setSelectionForeground(Color.WHITE);
        tbBlackList.getTableHeader().setResizingAllowed(false);
        tbBlackList.setBackground(new Color(248, 249, 249));
        tbBlackList.setForeground(new Color(211, 84, 0));

        tbBlackList.setFont(new java.awt.Font("Tw Cen MT", 1, 17));
        tbBlackList.setDefaultEditor(Object.class, null);
        tbBlackList.setShowVerticalLines(false);

        tbBlackList.getTableHeader().setReorderingAllowed(false);
        tbBlackList.setRowHeight(38);
        tbBlackList.setGridColor(new java.awt.Color(0, 0, 0));

        tbBlackList.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbBlackList.getColumnModel().getColumn(1).setPreferredWidth(500);
        tbBlackList.getColumnModel().getColumn(2).setPreferredWidth(0);

        tbBlackList.getColumnModel().getColumn(0).setMaxWidth(0);
        tbBlackList.getColumnModel().getColumn(0).setMinWidth(0);
        tbBlackList.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tbBlackList.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tbBlackList.getColumnModel().getColumn(2).setMaxWidth(0);
        tbBlackList.getColumnModel().getColumn(2).setMinWidth(0);
        tbBlackList.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tbBlackList.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);

    }

    public void deleteOfBlackList() {

        try {
            DefaultTableModel modelo = (DefaultTableModel) tbBlackList.getModel();
            String id = String.valueOf(modelo.getValueAt(tbBlackList.getSelectedRow(), 0));
            String nombre = String.valueOf(modelo.getValueAt(tbBlackList.getSelectedRow(), 1));
            
            if (tbBlackList.getSelectedRow() >= 0) {
                int dialog = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "Borrar a "+nombre+" de la lista?", "Confirmacion", dialog);

                if (result == 0) {
                    int id_fila = Integer.parseInt(id);

                    try {
                        updateAutores();
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
                        CallableStatement stm = con.prepareCall("delete from blackList where id=" + id_fila);

                        stm.execute();

                        JOptionPane.showMessageDialog(null, "Se quitó a " + nombre + " de la lista No Programar");
                        getBlackList();
                        customTableBlackList();
                    } catch (HeadlessException | SQLException ex) {
                        Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            int icon = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(null, "Seleccione un nombre para eliminar de la lista","Error",icon);
        }

    }

    public void focusNewRow() {
        tbBlackList.scrollRectToVisible(tbBlackList.getCellRect(tbBlackList.getRowCount() - 1, 0, true));
        tbBlackList.getSelectionModel().setSelectionInterval(tbBlackList.getRowCount() - 1, tbBlackList.getRowCount() - 1);
    }

    public void updateAutores() {
        DefaultTableModel modelo = (DefaultTableModel) tbBlackList.getModel();

        String id_autor = String.valueOf(modelo.getValueAt(tbBlackList.getSelectedRow(), 2));
        int id_fila = Integer.parseInt(id_autor);
        String estado = "Activo";

        try {
            String sql = "update autores set estado=? where id=" + id_fila + ";";

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");

            pstm = con.prepareStatement(sql);
            pstm.setString(1, estado);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(ListDataPrograma1.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBlackList = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbBlackList.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbBlackList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 450, 300));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MODIFICAR REGISTROS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, -1, -1));

        btnDel.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnDel.setForeground(new java.awt.Color(102, 102, 102));
        btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        btnDel.setText("Eliminar de la lista");
        btnDel.setContentAreaFilled(false);
        btnDel.setIconTextGap(0);
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });
        jPanel1.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, -1, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 150, 290, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        deleteOfBlackList();
    }//GEN-LAST:event_btnDelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbBlackList;
    // End of variables declaration//GEN-END:variables
}
