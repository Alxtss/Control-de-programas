/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import Conexion.Preferencias;
import Frames.Home;
import Frames.Principal;
import Frames.Programas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.singleInstance;

/**
 *
 * @author Aless
 */
public class Normas extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    /**
     * Creates new form Notas
     */
    public Normas() throws IOException {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        getTextFromFile();
        lblUpdate.setVisible(false);
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }

    public String fileSelect() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Programas.class.getName()).log(Level.SEVERE, null, ex);
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Seleccionar Carpeta del Programa");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
        } else {
            System.out.println("No Selection ");
        }

        File dir = chooser.getSelectedFile();
        return dir.toString();
    }

    public void restartApp() {
        String path = new File(".").getAbsolutePath();
        System.out.println(path);

        try {
            new ProcessBuilder("cmd", "/c start /min " + path + "\\control_programas.jar ^& exit").start();
        } catch (IOException ex) {
            Logger.getLogger(Normas.class.getName()).log(Level.SEVERE, null, ex);
        }
        new singleInstance().cerrarApp();
        System.exit(0);
    }

    public void getTextFromFile() throws IOException {
        Preferencias p = new Preferencias();
        p.getDirectoryPath();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Preferencias.dirNormas));
            String linea = reader.readLine();

            while (linea != null) {
                txtView.append(linea + "\n");
                linea = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);

            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex1) {
                Logger.getLogger(Programas.class.getName()).log(Level.SEVERE, null, ex1);
            }

            int dialog = JOptionPane.YES_NO_OPTION;
            int warning = JOptionPane.WARNING_MESSAGE;
            int info = JOptionPane.INFORMATION_MESSAGE;
            int result = JOptionPane.showConfirmDialog(null, "No se encontro el documento de Normas, probablemente se ha movido de ubicacion!. \n ¿Buscar en carpeta?", "Error", dialog, warning);

            if (result == 0) {
                String dir = fileSelect();
                Preferencias.dirNormas = dir;
                p.selectDirectoryPath();
                JOptionPane.showMessageDialog(null, "Carpeta cambiada con exito!", "Informacion", info);
                JOptionPane.showMessageDialog(null, "¡EL PROGRAMA SE REINICIARA PARA QUE FUNCIONE CORRECTAMENTE!", "AVISO!", warning);

                restartApp();
            }

        } finally {
            ocultarBarraTitulo();
        }

        txtView.setEditable(false);
        txtView.setCaretPosition(0);
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
        lblEdit = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtView = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        lblUpdate = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEdit.setBackground(new java.awt.Color(102, 102, 102));
        lblEdit.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        lblEdit.setForeground(new java.awt.Color(102, 102, 102));
        lblEdit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pencil.png"))); // NOI18N
        lblEdit.setText("Editar");
        lblEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        lblEdit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditMouseClicked(evt);
            }
        });
        jPanel1.add(lblEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 100, 50));

        btnBack.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnBack.setForeground(new java.awt.Color(102, 102, 102));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/next.png"))); // NOI18N
        btnBack.setText("Regresar a Inicio");
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 50));

        txtView.setBackground(new java.awt.Color(255, 255, 255));
        txtView.setColumns(20);
        txtView.setRows(5);
        jScrollPane2.setViewportView(txtView);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 1000, 500));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NORMAS DE LA RADIO");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 310, 50));

        lblUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/info.png"))); // NOI18N
        lblUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUpdateMouseClicked(evt);
            }
        });
        jPanel1.add(lblUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1040, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Home h = new Home();
        Principal.dpHome.add(h);
        h.toFront();
        h.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void lblEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditMouseClicked
        Preferencias p = new Preferencias();
        p.getDirectoryPath();

        try {
            Desktop.getDesktop().open(new File(Preferencias.dirNormas));
        } catch (Exception ex) {
            System.out.println(ex);

            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex1) {
                Logger.getLogger(Programas.class.getName()).log(Level.SEVERE, null, ex1);
            }

            int dialog = JOptionPane.YES_NO_OPTION;
            int warning = JOptionPane.WARNING_MESSAGE;
            int info = JOptionPane.INFORMATION_MESSAGE;
            int result = JOptionPane.showConfirmDialog(null, "Archivo no encontrado. \n ¿Buscar en carpeta?", "Error", dialog, warning);

            if (result == 0) {
                String dir = fileSelect();
                Preferencias.dirNormas = dir;
                p.selectDirectoryPath();
                JOptionPane.showMessageDialog(null, "Carpeta cambiada con exito!", "Informacion", info);
            }
        } finally {
            ocultarBarraTitulo();
        }

        lblUpdate.setVisible(true);
    }//GEN-LAST:event_lblEditMouseClicked

    private void lblUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUpdateMouseClicked
        lblUpdate.setVisible(false);
        txtView.setText("");
        try {
            getTextFromFile();
        } catch (IOException ex) {
            Logger.getLogger(Normas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblUpdateMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEdit;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JTextArea txtView;
    // End of variables declaration//GEN-END:variables
}
