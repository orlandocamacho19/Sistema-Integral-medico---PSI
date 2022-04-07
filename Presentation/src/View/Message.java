/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import SIM.App;
import SIM.MessageType;
import java.awt.Color;
import java.awt.Component;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

/**
 *
 * @author orlandocamacho
 */
public class Message extends javax.swing.JFrame {
    
    private int mouseX;
    private int mouseY;
    
    /**
     * Creates new form Message
     */
    public Message(Component C, MessageType type, String action, String text) {
        initComponents();
        String separator = File.separator;

        if (separator.equals("/")) {
            setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 22, 22));
        } else {
            setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 0, 0));
        }
        
        fillComponents(type, action, text);
        
        setLocationRelativeTo(C);
        setVisible(true);
    }
    
    public void fillComponents(MessageType type, String action, String text){
        btnAprove.setText("Aceptar");
        btnCancel.setText("Cancelar");
        
        switch (type) {
            case QUESTION:
                icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/interrogation.png")));
                break;
            case CORRECT:
                icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/check.png")));
                content.add(containerBtnAprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 360, 40));
                containerBtnCancel.hide();
                break;
            case ERROR:
                icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exclamation.png")));
                content.add(containerBtnAprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 360, 40));
                containerBtnCancel.hide();
                break;
        }
        title.setText(action);
        this.text.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        containerBtnCancel = new Components.RoundedPanel();
        btnCancel = new javax.swing.JButton();
        containerBtnAprove = new Components.RoundedPanel();
        btnAprove = new javax.swing.JButton();
        roundedPanel1 = new Components.RoundedPanel();
        icon = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topBar.setBackground(new java.awt.Color(232, 240, 254));
        topBar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        topBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topBarMouseDragged(evt);
            }
        });
        topBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topBarMousePressed(evt);
            }
        });
        topBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        title.setForeground(new java.awt.Color(35, 36, 37));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Action");
        topBar.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 35));

        getContentPane().add(topBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 35));

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerBtnCancel.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnCancel.setLayout(new java.awt.BorderLayout());

        btnCancel.setBackground(new java.awt.Color(37, 119, 241));
        btnCancel.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar");
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCancelMouseMoved(evt);
            }
        });
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelMouseExited(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        containerBtnCancel.add(btnCancel, java.awt.BorderLayout.CENTER);

        content.add(containerBtnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 180, 40));

        containerBtnAprove.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnAprove.setLayout(new java.awt.BorderLayout());

        btnAprove.setBackground(new java.awt.Color(37, 119, 241));
        btnAprove.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnAprove.setForeground(new java.awt.Color(255, 255, 255));
        btnAprove.setText("Continuar");
        btnAprove.setBorderPainted(false);
        btnAprove.setContentAreaFilled(false);
        btnAprove.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnAproveMouseMoved(evt);
            }
        });
        btnAprove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAproveMouseExited(evt);
            }
        });
        btnAprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAproveActionPerformed(evt);
            }
        });
        containerBtnAprove.add(btnAprove, java.awt.BorderLayout.CENTER);

        content.add(containerBtnAprove, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, 40));

        roundedPanel1.setBackground(new java.awt.Color(232, 240, 254));
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/check.png"))); // NOI18N
        icon.setMaximumSize(new java.awt.Dimension(14, 14));
        icon.setMinimumSize(new java.awt.Dimension(14, 14));
        icon.setPreferredSize(new java.awt.Dimension(14, 14));
        roundedPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        content.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 50));

        text.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        text.setForeground(new java.awt.Color(35, 36, 37));
        text.setText("jLabel2");
        content.add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 290, 50));

        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 35, 400, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void topBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMouseDragged
        int X = evt.getXOnScreen();
        int Y = evt.getYOnScreen();

        setLocation(X - mouseX, Y - mouseY + 1);
    }//GEN-LAST:event_topBarMouseDragged

    private void topBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topBarMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_topBarMousePressed

    private void btnCancelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseMoved
        containerBtnCancel.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnCancelMouseMoved

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        containerBtnCancel.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnAproveMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAproveMouseMoved
        containerBtnAprove.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnAproveMouseMoved

    private void btnAproveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAproveMouseExited
        containerBtnAprove.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnAproveMouseExited

    private void btnAproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAproveActionPerformed
        dispose();
    }//GEN-LAST:event_btnAproveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAprove;
    private javax.swing.JButton btnCancel;
    private Components.RoundedPanel containerBtnAprove;
    private Components.RoundedPanel containerBtnCancel;
    private javax.swing.JPanel content;
    private javax.swing.JLabel icon;
    private Components.RoundedPanel roundedPanel1;
    private javax.swing.JLabel text;
    private javax.swing.JLabel title;
    private javax.swing.JPanel topBar;
    // End of variables declaration//GEN-END:variables
}
