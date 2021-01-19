package Frames;

import Alarmas.mainAlarmas;
import AppPackage.AnimationClass;
import main.singleInstance;
import programa2.MainPrograma2;
import programa1.mainPrograma1;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        pagPrincipal();
    }

    public void pagPrincipal() {
        Home h = new Home();
        dpHome.add(h);
        h.setVisible(true);
        focusHome();
    }
    
    public void cerrar() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "Desea Salir", "Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            new singleInstance().cerrarApp();
            System.exit(0);
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("icons/logo.png"));
        return retValue;
    }

    public void focusHome() {
        lblHome.setIcon(new ImageIcon(getClass().getResource("/img/green2.png")));
        lblProgram.setIcon(null);
        lblManageProg.setIcon(null);
        lblAlarmas.setIcon(null);
        lblPrograma2.setIcon(null);
        lblPrograma1.setIcon(null);
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
        lblContainer.setBorder(null);
    }

    public void focusPrograms() {
        lblHome.setIcon(null);
        lblProgram.setIcon(new ImageIcon(getClass().getResource("/img/green2.png")));
        lblManageProg.setIcon(null);
        lblAlarmas.setIcon(null);
        lblPrograma2.setIcon(null);
        lblPrograma1.setIcon(null);
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
        lblContainer.setBorder(null);
    }

    public void focusManagePrograms() {
        lblHome.setIcon(null);
        lblProgram.setIcon(null);
        lblPrograma2.setIcon(null);
        lblPrograma1.setIcon(null);
        lblManageProg.setIcon(new ImageIcon(getClass().getResource("/img/green2.png")));
        lblAlarmas.setIcon(null);
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
        lblContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
    }

    public void focusAlarmas() {
        lblHome.setIcon(null);
        lblProgram.setIcon(null);
        lblManageProg.setIcon(null);
        lblPrograma2.setIcon(null);
        lblPrograma1.setIcon(null);
        lblAlarmas.setIcon(new ImageIcon(getClass().getResource("/img/green2.png")));
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
        lblContainer.setBorder(null);
    }

    public void focusHma() {
        focusManagePrograms();
        lblPrograma1.setIcon(new ImageIcon(getClass().getResource("/img/white2.png")));
        lblPrograma2.setIcon(null);
        btnPrograma1.setForeground(new java.awt.Color(51, 51, 51));
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
    }

    public void focusEpdo() {
        focusManagePrograms();
        lblPrograma2.setIcon(new ImageIcon(getClass().getResource("/img/white2.png")));
        lblPrograma1.setIcon(null);
        btnPrograma2.setForeground(new java.awt.Color(51, 51, 51));
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));

    }

    public void menuAnimations() {
        AnimationClass btnAlarm = new AnimationClass();
        btnAlarm.jButtonYDown(216, 300, 5, 5, btnAlarmas);

        btnAlarmas.setLocation(18, 300);

        AnimationClass lblAlarm = new AnimationClass();
        lblAlarm.jLabelYDown(216, 300, 5, 5, lblAlarmas);

        AnimationClass btnHma = new AnimationClass();
        btnHma.jButtonXRight(-200, 18, 5, 5, btnPrograma1);

        lblPrograma1.setLocation(0, 214);
        AnimationClass lblHma = new AnimationClass();
        lblHma.jLabelXRight(-225, 0, 5, 5, lblPrograma1);

        AnimationClass btnEpdo = new AnimationClass();
        btnEpdo.jButtonXRight(-200, 18, 5, 5, btnPrograma2);

        lblPrograma2.setLocation(0, 255);
        AnimationClass lblEpdo = new AnimationClass();
        lblEpdo.jLabelXRight(-225, 0, 5, 5, lblPrograma2);
    }

    public void closeMenuAnimations() {
        btnAlarmas.setLocation(18, 216);
        lblAlarmas.setLocation(0, 216);

        btnPrograma1.setLocation(-200, 214);
        lblPrograma1.setLocation(-225, 214);

        btnPrograma2.setLocation(-200, 255);
        lblPrograma2.setLocation(-225, 255);
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
        btnPrograms = new javax.swing.JButton();
        btnManageProg = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnPrograma2 = new javax.swing.JButton();
        btnPrograma1 = new javax.swing.JButton();
        btnAlarmas = new javax.swing.JButton();
        lblContainer = new javax.swing.JLabel();
        lblAlarmas = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblProgram = new javax.swing.JLabel();
        lblManageProg = new javax.swing.JLabel();
        lblPrograma2 = new javax.swing.JLabel();
        lblPrograma1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dpHome = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(33, 33, 39));
        jPanel1.setLayout(null);

        btnPrograms.setBackground(new java.awt.Color(51, 51, 51));
        btnPrograms.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnPrograms.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograms.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/old-radio.png"))); // NOI18N
        btnPrograms.setText("  PROGRAMAS");
        btnPrograms.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPrograms.setBorderPainted(false);
        btnPrograms.setContentAreaFilled(false);
        btnPrograms.setFocusable(false);
        btnPrograms.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnPrograms.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnProgramsMouseMoved(evt);
            }
        });
        btnPrograms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProgramsMouseExited(evt);
            }
        });
        btnPrograms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgramsActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrograms);
        btnPrograms.setBounds(18, 95, 200, 53);

        btnManageProg.setBackground(new java.awt.Color(51, 51, 51));
        btnManageProg.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnManageProg.setForeground(new java.awt.Color(255, 255, 255));
        btnManageProg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clipboard-with-pencil-.png"))); // NOI18N
        btnManageProg.setText("CONTROL DE PROGRAMAS");
        btnManageProg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnManageProg.setBorderPainted(false);
        btnManageProg.setContentAreaFilled(false);
        btnManageProg.setFocusable(false);
        btnManageProg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnManageProg.setIconTextGap(1);
        btnManageProg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnManageProgMouseMoved(evt);
            }
        });
        btnManageProg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnManageProgMouseExited(evt);
            }
        });
        btnManageProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageProgActionPerformed(evt);
            }
        });
        jPanel1.add(btnManageProg);
        btnManageProg.setBounds(18, 154, 200, 56);

        btnHome.setBackground(new java.awt.Color(51, 51, 51));
        btnHome.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home-page.png"))); // NOI18N
        btnHome.setText("  INICIO");
        btnHome.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        btnHome.setFocusable(false);
        btnHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnHome.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnHomeMouseMoved(evt);
            }
        });
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });
        jPanel1.add(btnHome);
        btnHome.setBounds(18, 37, 200, 52);

        btnPrograma2.setBackground(new java.awt.Color(102, 102, 102));
        btnPrograma2.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnPrograma2.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma2.setText("PROGRAMA 2");
        btnPrograma2.setToolTipText("");
        btnPrograma2.setBorder(null);
        btnPrograma2.setBorderPainted(false);
        btnPrograma2.setContentAreaFilled(false);
        btnPrograma2.setFocusable(false);
        btnPrograma2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnPrograma2MouseMoved(evt);
            }
        });
        btnPrograma2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrograma2MouseExited(evt);
            }
        });
        btnPrograma2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrograma2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrograma2);
        btnPrograma2.setBounds(-200, 255, 192, 40);

        btnPrograma1.setBackground(new java.awt.Color(102, 102, 102));
        btnPrograma1.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnPrograma1.setForeground(new java.awt.Color(255, 255, 255));
        btnPrograma1.setText("PROGRAMA 1");
        btnPrograma1.setToolTipText("");
        btnPrograma1.setBorder(null);
        btnPrograma1.setBorderPainted(false);
        btnPrograma1.setContentAreaFilled(false);
        btnPrograma1.setFocusable(false);
        btnPrograma1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnPrograma1MouseMoved(evt);
            }
        });
        btnPrograma1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrograma1MouseExited(evt);
            }
        });
        btnPrograma1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrograma1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrograma1);
        btnPrograma1.setBounds(-200, 214, 192, 40);

        btnAlarmas.setBackground(new java.awt.Color(51, 51, 51));
        btnAlarmas.setFont(new java.awt.Font("Tw Cen MT", 1, 13)); // NOI18N
        btnAlarmas.setForeground(new java.awt.Color(255, 255, 255));
        btnAlarmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/alarm.png"))); // NOI18N
        btnAlarmas.setText("  ALARMAS");
        btnAlarmas.setToolTipText("");
        btnAlarmas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAlarmas.setBorderPainted(false);
        btnAlarmas.setContentAreaFilled(false);
        btnAlarmas.setFocusable(false);
        btnAlarmas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAlarmas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnAlarmasMouseMoved(evt);
            }
        });
        btnAlarmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlarmasMouseExited(evt);
            }
        });
        btnAlarmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlarmasActionPerformed(evt);
            }
        });
        jPanel1.add(btnAlarmas);
        btnAlarmas.setBounds(18, 216, 200, 53);
        jPanel1.add(lblContainer);
        lblContainer.setBounds(0, 210, 220, 90);

        lblAlarmas.setBackground(new java.awt.Color(0, 153, 102));
        lblAlarmas.setForeground(new java.awt.Color(0, 153, 102));
        lblAlarmas.setToolTipText("");
        lblAlarmas.setName(""); // NOI18N
        jPanel1.add(lblAlarmas);
        lblAlarmas.setBounds(0, 216, 220, 53);

        lblHome.setBackground(new java.awt.Color(0, 153, 102));
        lblHome.setForeground(new java.awt.Color(0, 153, 102));
        lblHome.setToolTipText("");
        jPanel1.add(lblHome);
        lblHome.setBounds(0, 37, 220, 52);

        lblProgram.setBackground(new java.awt.Color(0, 153, 102));
        lblProgram.setForeground(new java.awt.Color(0, 153, 102));
        lblProgram.setToolTipText("");
        jPanel1.add(lblProgram);
        lblProgram.setBounds(0, 95, 220, 53);

        lblManageProg.setBackground(new java.awt.Color(0, 153, 102));
        lblManageProg.setForeground(new java.awt.Color(0, 153, 102));
        lblManageProg.setToolTipText("");
        jPanel1.add(lblManageProg);
        lblManageProg.setBounds(0, 154, 220, 56);

        lblPrograma2.setBackground(new java.awt.Color(0, 153, 102));
        lblPrograma2.setForeground(new java.awt.Color(0, 153, 102));
        lblPrograma2.setToolTipText("");
        jPanel1.add(lblPrograma2);
        lblPrograma2.setBounds(-225, 255, 220, 40);

        lblPrograma1.setBackground(new java.awt.Color(0, 153, 102));
        lblPrograma1.setForeground(new java.awt.Color(0, 153, 102));
        lblPrograma1.setToolTipText("");
        lblPrograma1.setName(""); // NOI18N
        jPanel1.add(lblPrograma1);
        lblPrograma1.setBounds(-225, 214, 220, 40);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 220, 590));

        jPanel3.setBackground(new java.awt.Color(65, 65, 65));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 100));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        dpHome.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout dpHomeLayout = new javax.swing.GroupLayout(dpHome);
        dpHome.setLayout(dpHomeLayout);
        dpHomeLayout.setHorizontalGroup(
            dpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        dpHomeLayout.setVerticalGroup(
            dpHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        getContentPane().add(dpHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 1040, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnManageProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageProgActionPerformed

        focusManagePrograms();
        menuAnimations();

    }//GEN-LAST:event_btnManageProgActionPerformed

    private void btnProgramsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgramsActionPerformed
        closeMenuAnimations();
        Programas p = new Programas();
        dpHome.add(p);
        p.setVisible(true);
        focusPrograms();

    }//GEN-LAST:event_btnProgramsActionPerformed

    private void btnPrograma2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrograma2ActionPerformed

        focusEpdo();

        MainPrograma2 epdo = null;
        try {
            epdo = new MainPrograma2();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        dpHome.add(epdo);
        epdo.setVisible(true);

    }//GEN-LAST:event_btnPrograma2ActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        closeMenuAnimations();
        Home h = new Home();
        dpHome.add(h);
        h.setVisible(true);
        focusHome();

    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnPrograma1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrograma1ActionPerformed

        focusHma();

        mainPrograma1 hma = null;
        try {
            hma = new mainPrograma1();
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        dpHome.add(hma);
        hma.setVisible(true);

    }//GEN-LAST:event_btnPrograma1ActionPerformed

    private void btnAlarmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlarmasActionPerformed

        closeMenuAnimations();

        try {
            mainAlarmas alarm = new mainAlarmas();
            dpHome.add(alarm);
            alarm.setVisible(true);
            focusAlarmas();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAlarmasActionPerformed

    private void btnHomeMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnHome.setFont(f);
    }//GEN-LAST:event_btnHomeMouseMoved

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnHome.setFont(f);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnProgramsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProgramsMouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnPrograms.setFont(f);
    }//GEN-LAST:event_btnProgramsMouseMoved

    private void btnProgramsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProgramsMouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnPrograms.setFont(f);
    }//GEN-LAST:event_btnProgramsMouseExited

    private void btnManageProgMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManageProgMouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnManageProg.setFont(f);
    }//GEN-LAST:event_btnManageProgMouseMoved

    private void btnManageProgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManageProgMouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnManageProg.setFont(f);
    }//GEN-LAST:event_btnManageProgMouseExited

    private void btnAlarmasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlarmasMouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnAlarmas.setFont(f);
    }//GEN-LAST:event_btnAlarmasMouseMoved

    private void btnAlarmasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlarmasMouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnAlarmas.setFont(f);
    }//GEN-LAST:event_btnAlarmasMouseExited

    private void btnPrograma1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrograma1MouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnPrograma1.setFont(f);
    }//GEN-LAST:event_btnPrograma1MouseMoved

    private void btnPrograma1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrograma1MouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnPrograma1.setFont(f);
    }//GEN-LAST:event_btnPrograma1MouseExited

    private void btnPrograma2MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrograma2MouseMoved
        Font f = new Font("Tw Cen MT", 1, 14);
        btnPrograma2.setFont(f);
    }//GEN-LAST:event_btnPrograma2MouseMoved

    private void btnPrograma2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrograma2MouseExited
        Font f = new Font("Tw Cen MT", 1, 13);
        btnPrograma2.setFont(f);
    }//GEN-LAST:event_btnPrograma2MouseExited

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cerrar();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> {
//            try {
//                UIManager.setLookAndFeel(new TextureLookAndFeel());
//            } catch (UnsupportedLookAndFeelException ex) {
//                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            new Principal().setVisible(true);
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlarmas;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnManageProg;
    private javax.swing.JButton btnPrograma1;
    private javax.swing.JButton btnPrograma2;
    private javax.swing.JButton btnPrograms;
    public static javax.swing.JDesktopPane dpHome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAlarmas;
    private javax.swing.JLabel lblContainer;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblManageProg;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblPrograma1;
    private javax.swing.JLabel lblPrograma2;
    // End of variables declaration//GEN-END:variables
}
