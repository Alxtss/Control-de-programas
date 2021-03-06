/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa1;

import Frames.Principal;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Aless
 */
public class mainPrograma1 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    
    /**
     * Creates new form mainHMA
     */
    public mainPrograma1() throws SQLException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        menuActions();
        listData();
    }
    
    public void menuActions(){
        setActionsPrograma1 menu = new setActionsPrograma1();
        dpMenu.add(menu);
        menu.setVisible(true);
    }
    
    public void ocultarBarraTitulo(){
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0,0);
        Barra.setPreferredSize(new Dimension(0,0));
        repaint();
    }
    
    public void listData() throws SQLException{
        ListDataPrograma1 hma = null;
        try {
            hma = new ListDataPrograma1();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        dpView.add(hma);
        hma.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpView = new javax.swing.JDesktopPane();
        dpMenu = new javax.swing.JDesktopPane();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dpView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout dpViewLayout = new javax.swing.GroupLayout(dpView);
        dpView.setLayout(dpViewLayout);
        dpViewLayout.setHorizontalGroup(
            dpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1038, Short.MAX_VALUE)
        );
        dpViewLayout.setVerticalGroup(
            dpViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        getContentPane().add(dpView, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 1040, 380));

        dpMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout dpMenuLayout = new javax.swing.GroupLayout(dpMenu);
        dpMenu.setLayout(dpMenuLayout);
        dpMenuLayout.setHorizontalGroup(
            dpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1038, Short.MAX_VALUE)
        );
        dpMenuLayout.setVerticalGroup(
            dpMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        getContentPane().add(dpMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane dpMenu;
    public static javax.swing.JDesktopPane dpView;
    // End of variables declaration//GEN-END:variables
}
