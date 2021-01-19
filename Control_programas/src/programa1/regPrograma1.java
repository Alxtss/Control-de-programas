/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa1;

import Conexion.Conexion;
import Conexion.Preferencias;
import java.awt.Dimension;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author Aless
 */
public class regPrograma1 extends javax.swing.JInternalFrame {

    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public static int listValidator = 0;

    public regPrograma1() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ocultarBarraTitulo();
        lblMensaje.setVisible(false);
        mostrarListaAutores();
        getSugerenciaDelDia();
    }

    public void ocultarBarraTitulo() {
        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        repaint();
    }
    
    public void getSugerenciaDelDia(){
        String nombre = cbAutores.getItemAt(1);
        txtSugerencia.setText("  "+nombre);        
    }

    public void registrarPrograma() {
        try {
            String nombre = cbAutores.getSelectedItem().toString();

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_programas?useSSL=false", "root", "123456");
            CallableStatement stm = con.prepareCall("call sp_registroPrograma1(?)");

            stm.setString(1, nombre);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(regPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListAutoresNoFrecuentes() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_autoresNoFrecuentes");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListAutoresFrecuentes() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_filtroAutoresFrecuentes");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getListAutores() {

        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_WEEK);
        
        switch (dia) {
            case 1:
                ListAutoresFrecuentes();
                lblNomLista.setText("AUTORES FRECUENTES");
                break;
            case 2:
                ListAutoresNoFrecuentes();
                lblNomLista.setText("AUTORES NO FRECUENTES");
                break;
            case 3:
                ListAutoresFrecuentes();
                lblNomLista.setText("AUTORES FRECUENTES");
                break;
            case 4:
                ListAutoresNoFrecuentes();
                lblNomLista.setText("AUTORES NO FRECUENTES");
                break;
            case 5:
                ListAutoresFrecuentes();
                lblNomLista.setText("AUTORES FRECUENTES");
                break;
            case 6:
                ListAutoresNoFrecuentes();
                lblNomLista.setText("AUTORES NO FRECUENTES");
                break;
            case 7:
                ListAutoresFrecuentes();
                lblNomLista.setText("AUTORES FRECUENTES");
                break;
            default:
                ListAutoresNoFrecuentes();
                lblNomLista.setText("AUTORES NO FRECUENTES");
                break;
        }
        System.out.println(dia);
    }

    public void ListNuevosCds() {
        try {
            Conexion con = new Conexion("localhost", "root", "123456", "gestion_programas");
            ResultSet rs = con.ejecutarSelect("select * from view_filtroNuevosCds");

            cbAutores.addItem("Seleccione");

            while (rs.next()) {
                cbAutores.addItem(rs.getString(2));
            }
            con.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarListaAutores() {
        Preferencias p = new Preferencias();
        p.getPreferences();
        if (listValidator == 1) {
            getListAutores();
        } else {

            if (listValidator == 2) {
                ListNuevosCds();
                lblNomLista.setText("NUEVOS CDS");
            } else {
                ListAutoresNoFrecuentes();
                lblNomLista.setText("AUTORES NO FRECUENTES");
            }
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
        jLabel1 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        cbAutores = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        lblNomLista = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSugerencia = new javax.swing.JTextField();
        lblText = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nombre del Autor/a:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 20));

        btnRegistrar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(102, 102, 102));
        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/checked.png"))); // NOI18N
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 150, 50));

        lblMensaje.setFont(new java.awt.Font("Tw Cen MT", 0, 16)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(255, 51, 51));
        lblMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMensaje.setText("msg");
        jPanel1.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 230, -1));

        btnCancelar.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(102, 102, 102));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/close.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 150, 50));

        cbAutores.setBackground(new java.awt.Color(255, 255, 255));
        cbAutores.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        cbAutores.setForeground(new java.awt.Color(51, 51, 51));
        cbAutores.setBorder(null);
        jPanel1.add(cbAutores, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 230, 40));

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 590, 160));

        lblNomLista.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lblNomLista.setForeground(new java.awt.Color(0, 204, 51));
        lblNomLista.setText("LISTA");
        jPanel1.add(lblNomLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 270, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("PROGRAMANDO DE LA LISTA DE:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        txtSugerencia.setEditable(false);
        txtSugerencia.setBackground(new java.awt.Color(255, 255, 255));
        txtSugerencia.setFont(new java.awt.Font("Tw Cen MT", 0, 20)); // NOI18N
        txtSugerencia.setForeground(new java.awt.Color(0, 102, 102));
        txtSugerencia.setBorder(null);
        jPanel1.add(txtSugerencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 240, 40));

        lblText.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lblText.setForeground(new java.awt.Color(255, 255, 255));
        lblText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblText.setText("SUGERENCIA DE HOY:");
        jPanel1.add(lblText, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/green2.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1040, 80));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 1040, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        String nombre = cbAutores.getSelectedItem().toString();

        if (nombre.equals("Seleccione")) {
            lblMensaje.setVisible(true);
            lblMensaje.setText("SELECCIONE UN AUTOR");
            cbAutores.requestFocus();
        } else {
            try {
                registrarPrograma();
                ListDataPrograma1 list = new ListDataPrograma1();
                mainPrograma1.dpView.add(list);
                list.toFront();
                list.setVisible(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(regPrograma1.class.getName()).log(Level.SEVERE, null, ex);
            }
            setActionsPrograma1 menu = new setActionsPrograma1();
            mainPrograma1.dpMenu.add(menu);
            menu.toFront();
            menu.setVisible(true);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        setActionsPrograma1 menu = new setActionsPrograma1();
        mainPrograma1.dpMenu.add(menu);
        menu.toFront();
        menu.setVisible(true);

        try {
            ListDataPrograma1 l = new ListDataPrograma1();
            mainPrograma1.dpView.add(l);
            l.toFront();
            l.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(setActionsPrograma1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbAutores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNomLista;
    private javax.swing.JLabel lblText;
    private javax.swing.JTextField txtSugerencia;
    // End of variables declaration//GEN-END:variables
}
