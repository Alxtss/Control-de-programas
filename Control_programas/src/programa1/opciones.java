/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa1;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Aless
 */
public class opciones extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form opciones
     */
    public opciones() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
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
        btnBack = new javax.swing.JButton();
        btnAddFrecuentes = new javax.swing.JButton();
        btnBlackList = new javax.swing.JButton();
        btnListAutores = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(102, 102, 102));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnBack.setText("Regresar");
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 60));

        btnAddFrecuentes.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        btnAddFrecuentes.setForeground(new java.awt.Color(102, 102, 102));
        btnAddFrecuentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/album.png"))); // NOI18N
        btnAddFrecuentes.setText("Nuevos CD's / Frecuentes");
        btnAddFrecuentes.setBorder(null);
        btnAddFrecuentes.setContentAreaFilled(false);
        btnAddFrecuentes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddFrecuentes.setIconTextGap(8);
        btnAddFrecuentes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAddFrecuentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFrecuentesActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddFrecuentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 220, 180));

        btnBlackList.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnBlackList.setForeground(new java.awt.Color(102, 102, 102));
        btnBlackList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btnBlackList.setText("Lista de NO PROGRAMAR");
        btnBlackList.setBorder(null);
        btnBlackList.setContentAreaFilled(false);
        btnBlackList.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnBlackList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBlackList.setIconTextGap(8);
        btnBlackList.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBlackList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBlackListActionPerformed(evt);
            }
        });
        jPanel1.add(btnBlackList, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, -1, 180));

        btnListAutores.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        btnListAutores.setForeground(new java.awt.Color(102, 102, 102));
        btnListAutores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/clipboard.png"))); // NOI18N
        btnListAutores.setText("Lista de Autores");
        btnListAutores.setBorder(null);
        btnListAutores.setBorderPainted(false);
        btnListAutores.setContentAreaFilled(false);
        btnListAutores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnListAutores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnListAutores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListAutoresActionPerformed(evt);
            }
        });
        jPanel1.add(btnListAutores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 220, 180));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        try {
            setActionsPrograma1 a = new setActionsPrograma1();
            mainPrograma1.dpMenu.add(a);
            a.toFront();
            a.setVisible(true);

            ListDataPrograma1 d = new ListDataPrograma1();
            mainPrograma1.dpView.add(d);
            d.toFront();
            d.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(opciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnBlackListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBlackListActionPerformed
        viewBlackList bl = new viewBlackList();
        mainPrograma1.dpView.add(bl);
        bl.toFront();
        bl.setVisible(true);
    }//GEN-LAST:event_btnBlackListActionPerformed

    private void btnListAutoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListAutoresActionPerformed
        viewListAutores a = new viewListAutores();
        mainPrograma1.dpView.add(a);
        a.toFront();
        a.setVisible(true);
    }//GEN-LAST:event_btnListAutoresActionPerformed

    private void btnAddFrecuentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFrecuentesActionPerformed
        frecuentes_nuevosCds fn = new frecuentes_nuevosCds();
        mainPrograma1.dpView.add(fn);
        fn.toFront();
        fn.setVisible(true);
    }//GEN-LAST:event_btnAddFrecuentesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFrecuentes;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBlackList;
    private javax.swing.JButton btnListAutores;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
