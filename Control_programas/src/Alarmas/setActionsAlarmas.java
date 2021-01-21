package Alarmas;

import static Alarmas.mainAlarmas.dpView;
import programa2.regPrograma2;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Aless
 */
public class setActionsAlarmas extends javax.swing.JInternalFrame {
    
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form setActionsAlarmas
     */
    public setActionsAlarmas() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
    }
    
    public void ocultarBarraTitulo(){
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0,0);
        Barra.setPreferredSize(new Dimension(0,0));
        repaint();
    }
    
    public void listData() throws SQLException, ClassNotFoundException{
        listData list = null;
        list = new listData();

        dpView.add(list);
        list.setVisible(true);
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
        lblConsultar = new javax.swing.JLabel();
        lblRegistrar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(247, 247, 247));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConsultar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblConsultar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clock.png"))); // NOI18N
        lblConsultar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblConsultarMouseMoved(evt);
            }
        });
        lblConsultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConsultarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblConsultarMouseExited(evt);
            }
        });
        jPanel1.add(lblConsultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, 200, 160));

        lblRegistrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblRegistrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/time-management.png"))); // NOI18N
        lblRegistrar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblRegistrarMouseMoved(evt);
            }
        });
        lblRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblRegistrarMouseExited(evt);
            }
        });
        jPanel1.add(lblRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 190, 160));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VER REGISTRO DE ALARMAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 240, 210, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PROGRAMAR NUEVA ALARMA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 230, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarMouseClicked

        regAlarma reg = new regAlarma();
        mainAlarmas.dpView.add(reg);
        reg.toFront();
        reg.setVisible(true);

    }//GEN-LAST:event_lblRegistrarMouseClicked

    private void lblConsultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarMouseClicked
        
        try {
            listData();
        } catch (SQLException ex) {
            Logger.getLogger(setActionsAlarmas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(setActionsAlarmas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_lblConsultarMouseClicked

    private void lblRegistrarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarMouseMoved
        lblRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_lblRegistrarMouseMoved

    private void lblRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarMouseExited
        lblRegistrar.setBorder(null);
    }//GEN-LAST:event_lblRegistrarMouseExited

    private void lblConsultarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarMouseMoved
        lblConsultar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
    }//GEN-LAST:event_lblConsultarMouseMoved

    private void lblConsultarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblConsultarMouseExited
        lblConsultar.setBorder(null);
    }//GEN-LAST:event_lblConsultarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConsultar;
    private javax.swing.JLabel lblRegistrar;
    // End of variables declaration//GEN-END:variables
}