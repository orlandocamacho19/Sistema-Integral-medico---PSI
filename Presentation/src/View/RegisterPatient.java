/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import java.awt.Color;

/**
 *
 * @author orlandocamacho
 */
public class RegisterPatient extends javax.swing.JPanel {

    /**
     * Creates new form ManagePatient
     */
    public RegisterPatient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLDate = new javax.swing.JLabel();
        containerYear = new Components.RoundedPanel();
        cbYear = new javax.swing.JComboBox<>();
        containerDay = new Components.RoundedPanel();
        cbDay = new javax.swing.JComboBox<>();
        jLPatient = new javax.swing.JLabel();
        containerMonth = new Components.RoundedPanel();
        cbMonth = new javax.swing.JComboBox<>();
        jLBeginning = new javax.swing.JLabel();
        containerBtnSchedule = new Components.RoundedPanel();
        btnSchedule = new javax.swing.JButton();
        containerPatient = new Components.RoundedPanel();
        jTextField1 = new javax.swing.JTextField();
        containerEnding = new Components.RoundedPanel();
        jPanel3 = new javax.swing.JPanel();
        jLPatient1 = new javax.swing.JLabel();
        jLDate1 = new javax.swing.JLabel();
        jLBeginning1 = new javax.swing.JLabel();
        containerPatient1 = new Components.RoundedPanel();
        jTextField3 = new javax.swing.JTextField();
        containerEnding1 = new Components.RoundedPanel();
        jTextField4 = new javax.swing.JTextField();
        containerEnding2 = new Components.RoundedPanel();
        jTextField5 = new javax.swing.JTextField();
        jLPatient2 = new javax.swing.JLabel();
        roundedPanel7 = new Components.RoundedPanel();
        jPanel6 = new javax.swing.JPanel();
        jLDay3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        roundedPanel3 = new Components.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLDay2 = new javax.swing.JLabel();
        roundedPanel5 = new Components.RoundedPanel();
        nextMonth1 = new javax.swing.JButton();
        roundedPanel6 = new Components.RoundedPanel();
        previousMonth1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLDay1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        roundedPanel1 = new Components.RoundedPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        roundedIndicator2 = new Components.RoundedIndicator();
        roundedPanel2 = new Components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        roundedIndicator1 = new Components.RoundedIndicator();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 36, 37));
        jLabel2.setText("Registrar paciente");
        title.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDate.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate.setForeground(new java.awt.Color(35, 36, 37));
        jLDate.setText("Fecha de nacimiento:");
        jPanel2.add(jLDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        containerYear.setBackground(new java.awt.Color(244, 243, 243));
        containerYear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbYear.setBackground(new java.awt.Color(244, 243, 243));
        cbYear.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbYear.setForeground(new java.awt.Color(35, 36, 37));
        cbYear.setMaximumRowCount(12);
        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbYear.setSelectedIndex(-1);
        cbYear.setBorder(null);
        cbYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbYear.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbYear.setFocusable(false);
        containerYear.add(cbYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel2.add(containerYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 80, 30));

        containerDay.setBackground(new java.awt.Color(244, 243, 243));
        containerDay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbDay.setBackground(new java.awt.Color(244, 243, 243));
        cbDay.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbDay.setForeground(new java.awt.Color(35, 36, 37));
        cbDay.setMaximumRowCount(12);
        cbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbDay.setSelectedIndex(-1);
        cbDay.setBorder(null);
        cbDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbDay.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbDay.setFocusable(false);
        containerDay.add(cbDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel2.add(containerDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 80, 30));

        jLPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient.setText("Nombre:");
        jPanel2.add(jLPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 10));

        containerMonth.setBackground(new java.awt.Color(244, 243, 243));
        containerMonth.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMonth.setBackground(new java.awt.Color(244, 243, 243));
        cbMonth.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMonth.setForeground(new java.awt.Color(35, 36, 37));
        cbMonth.setMaximumRowCount(12);
        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMonth.setSelectedIndex(-1);
        cbMonth.setBorder(null);
        cbMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMonth.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMonth.setFocusable(false);
        containerMonth.add(cbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel2.add(containerMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 80, 30));

        jLBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning.setText("Telefono:");
        jPanel2.add(jLBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        containerBtnSchedule.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnSchedule.setLayout(new java.awt.BorderLayout());

        btnSchedule.setBackground(new java.awt.Color(37, 119, 241));
        btnSchedule.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnSchedule.setForeground(new java.awt.Color(255, 255, 255));
        btnSchedule.setText("Registrar paciente");
        btnSchedule.setBorderPainted(false);
        btnSchedule.setContentAreaFilled(false);
        btnSchedule.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnSchedulenMouseMoved(evt);
            }
        });
        btnSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnScheduleMouseExited(evt);
            }
        });
        containerBtnSchedule.add(btnSchedule, java.awt.BorderLayout.CENTER);

        jPanel2.add(containerBtnSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 40));

        containerPatient.setBackground(new java.awt.Color(244, 243, 243));
        containerPatient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBackground(new java.awt.Color(244, 243, 243));
        jTextField1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(35, 36, 37));
        jTextField1.setBorder(null);
        jTextField1.setIgnoreRepaint(true);
        containerPatient.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel2.add(containerPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 30));

        containerEnding.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(containerEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 260, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 625));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLPatient1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient1.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient1.setText("Medicamentos:");
        jPanel3.add(jLPatient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 10));

        jLDate1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate1.setForeground(new java.awt.Color(35, 36, 37));
        jLDate1.setText("Fecha de nacimiento:");
        jPanel3.add(jLDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        jLBeginning1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning1.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning1.setText("Telefono:");
        jPanel3.add(jLBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        containerPatient1.setBackground(new java.awt.Color(244, 243, 243));
        containerPatient1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField3.setBackground(new java.awt.Color(244, 243, 243));
        jTextField3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(35, 36, 37));
        jTextField3.setBorder(null);
        jTextField3.setIgnoreRepaint(true);
        containerPatient1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerPatient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 30));

        containerEnding1.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField4.setBackground(new java.awt.Color(244, 243, 243));
        jTextField4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(35, 36, 37));
        jTextField4.setBorder(null);
        jTextField4.setIgnoreRepaint(true);
        containerEnding1.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 30));

        containerEnding2.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField5.setBackground(new java.awt.Color(244, 243, 243));
        jTextField5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(35, 36, 37));
        jTextField5.setBorder(null);
        jTextField5.setIgnoreRepaint(true);
        containerEnding2.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerEnding2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 260, 30));

        jLPatient2.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient2.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient2.setText("Nombre:");
        jPanel3.add(jLPatient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 10));

        roundedPanel7.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(roundedPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 160, 150));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 480, 210));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDay3.setBackground(new java.awt.Color(255, 255, 255));
        jLDay3.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLDay3.setForeground(new java.awt.Color(35, 36, 37));
        jLDay3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDay3.setText("Información del paciente");
        jLDay3.setToolTipText("");
        jPanel6.add(jLDay3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 360, 30));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 480, 50));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel3.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(35, 36, 37));
        jLabel6.setText("Sin citas");
        roundedPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel7.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 110));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 370, 480, 315));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(204, 204, 204)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDay2.setBackground(new java.awt.Color(255, 255, 255));
        jLDay2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLDay2.setForeground(new java.awt.Color(35, 36, 37));
        jLDay2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDay2.setText("Pacientes");
        jLDay2.setToolTipText("");
        jPanel5.add(jLDay2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 180, 30));

        roundedPanel5.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel5.setLayout(new java.awt.BorderLayout());

        nextMonth1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nextDay.png"))); // NOI18N
        nextMonth1.setBorderPainted(false);
        nextMonth1.setContentAreaFilled(false);
        nextMonth1.setFocusPainted(false);
        nextMonth1.setIgnoreRepaint(true);
        roundedPanel5.add(nextMonth1, java.awt.BorderLayout.CENTER);

        jPanel5.add(roundedPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 30, 30));

        roundedPanel6.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel6.setLayout(new java.awt.BorderLayout());

        previousMonth1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previousDay.png"))); // NOI18N
        previousMonth1.setBorderPainted(false);
        previousMonth1.setContentAreaFilled(false);
        previousMonth1.setFocusPainted(false);
        previousMonth1.setIgnoreRepaint(true);
        roundedPanel6.add(previousMonth1, java.awt.BorderLayout.CENTER);

        jPanel5.add(roundedPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 300, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDay1.setBackground(new java.awt.Color(255, 255, 255));
        jLDay1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLDay1.setForeground(new java.awt.Color(35, 36, 37));
        jLDay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDay1.setText("Citas por confirmar del paciente");
        jLDay1.setToolTipText("");
        jPanel4.add(jLDay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 360, 30));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 320, 480, 50));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel1.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 36, 37));
        jLabel3.setText("Manuel de Jesus Valenzuela Vazquez");
        roundedPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(35, 36, 37));
        jLabel5.setText("644 164 3488");
        roundedPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, -1));

        roundedIndicator2.setBackground(new java.awt.Color(254, 78, 137));
        roundedPanel1.add(roundedIndicator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jPanel1.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 60));

        roundedPanel2.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 36, 37));
        jLabel1.setText("631 299 6045");
        roundedPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 36, 37));
        jLabel4.setText("Orlando Camacho Gámez");
        roundedPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, -1));

        roundedIndicator1.setBackground(new java.awt.Color(79, 195, 97));
        roundedPanel2.add(roundedIndicator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        jPanel1.add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 260, 60));

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 300, 575));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSchedulenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSchedulenMouseMoved
        containerBtnSchedule.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnSchedulenMouseMoved

    private void btnScheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnScheduleMouseExited
        containerBtnSchedule.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnScheduleMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSchedule;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private Components.RoundedPanel containerBtnSchedule;
    private Components.RoundedPanel containerDay;
    private Components.RoundedPanel containerEnding;
    private Components.RoundedPanel containerEnding1;
    private Components.RoundedPanel containerEnding2;
    private Components.RoundedPanel containerMonth;
    private Components.RoundedPanel containerPatient;
    private Components.RoundedPanel containerPatient1;
    private Components.RoundedPanel containerYear;
    private javax.swing.JLabel jLBeginning;
    private javax.swing.JLabel jLBeginning1;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLDate1;
    private javax.swing.JLabel jLDay1;
    private javax.swing.JLabel jLDay2;
    private javax.swing.JLabel jLDay3;
    private javax.swing.JLabel jLPatient;
    private javax.swing.JLabel jLPatient1;
    private javax.swing.JLabel jLPatient2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JButton nextMonth1;
    private javax.swing.JButton previousMonth1;
    private Components.RoundedIndicator roundedIndicator1;
    private Components.RoundedIndicator roundedIndicator2;
    private Components.RoundedPanel roundedPanel1;
    private Components.RoundedPanel roundedPanel2;
    private Components.RoundedPanel roundedPanel3;
    private Components.RoundedPanel roundedPanel5;
    private Components.RoundedPanel roundedPanel6;
    private Components.RoundedPanel roundedPanel7;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
