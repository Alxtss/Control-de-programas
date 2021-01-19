package programa1;

import Conexion.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class setActionsPrograma1 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private PreparedStatement pstm;

    public setActionsPrograma1() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getBlackList();
        customTableBlackList();
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

            blackList.setModel(modelo);

            con.desconectar();
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

        for (int i = 0; i < blackList.getModel().getColumnCount(); i++) {
            blackList.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        blackList.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        blackList.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);

        blackList.setSelectionBackground(new Color(211, 84, 0));
        blackList.setSelectionForeground(Color.WHITE);
        blackList.getTableHeader().setResizingAllowed(false);
        blackList.setBackground(new Color(248, 249, 249));
        blackList.setForeground(new Color(211, 84, 0));

        blackList.setFont(new java.awt.Font("Tw Cen MT", 1, 17));
        blackList.setDefaultEditor(Object.class, null);
        blackList.setShowVerticalLines(false);

        blackList.getTableHeader().setReorderingAllowed(false);
        blackList.setRowHeight(38);
        blackList.setGridColor(new java.awt.Color(0, 0, 0));

        blackList.getColumnModel().getColumn(0).setPreferredWidth(50);
        blackList.getColumnModel().getColumn(1).setPreferredWidth(550);
        blackList.getColumnModel().getColumn(2).setPreferredWidth(0);

        blackList.getColumnModel().getColumn(0).setMaxWidth(0);
        blackList.getColumnModel().getColumn(0).setMinWidth(0);
        blackList.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        blackList.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        blackList.getColumnModel().getColumn(2).setMaxWidth(0);
        blackList.getColumnModel().getColumn(2).setMinWidth(0);
        blackList.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        blackList.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
    }

    public void focusNewRow() {
        blackList.scrollRectToVisible(blackList.getCellRect(blackList.getRowCount() - 1, 0, true));
        blackList.getSelectionModel().setSelectionInterval(blackList.getRowCount() - 1, blackList.getRowCount() - 1);
    }

    public void limpiar() {
        ListDataPrograma1.btnDelete.setVisible(false);
        ListDataPrograma1.btnEdit.setVisible(false);
        ListDataPrograma1.btnOk.setVisible(false);
        ListDataPrograma1.btnNoProgramar.setVisible(false);        
        ListDataPrograma1.btnEditList.setEnabled(false);
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
        blackList = new javax.swing.JTable();
        btnOpciones = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(249, 249, 249));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        blackList.setBackground(new java.awt.Color(248, 248, 248));
        blackList.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(blackList);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 240, 250));

        btnOpciones.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnOpciones.setForeground(new java.awt.Color(102, 102, 102));
        btnOpciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/settings.png"))); // NOI18N
        btnOpciones.setText("Opciones");
        btnOpciones.setContentAreaFilled(false);
        btnOpciones.setFocusPainted(false);
        btnOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpcionesActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, -1));

        btnRegistrar.setFont(new java.awt.Font("Tw Cen MT", 1, 17)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(107, 145, 81));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add-reg2.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR PROGRAMA DEL DIA");
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrar.setIconTextGap(15);
        btnRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpcionesActionPerformed
        opciones o = new opciones();
        mainPrograma1.dpMenu.add(o);
        o.toFront();
        o.setVisible(true);
        ListDataPrograma1.jPanel2.setVisible(false);
    }//GEN-LAST:event_btnOpcionesActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        try {
            regPrograma1 reg = new regPrograma1();
            mainPrograma1.dpMenu.add(reg);
            reg.toFront();
            reg.setVisible(true);
            limpiar();
        }catch(Exception e){
            System.out.println(e);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable blackList;
    private javax.swing.JButton btnOpciones;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
