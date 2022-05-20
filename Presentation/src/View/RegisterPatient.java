/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Components.AppointmentToConfirm;
import Components.CustomScrollBarUI;
import Components.PatientInfo;
import Domain.Appointment;
import Domain.Patient;
import SIM.App;
import SIM.MessageType;
import control.AppointmentControl;
import control.PatientControl;
import java.awt.Color;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;

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
        jScrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        
        jScrollPatients.setSize(300, 575);
        jScrollPatients.setPreferredSize(new java.awt.Dimension(300, 575));
        
        jScrollPane3.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        
        jScrollAppointments.setSize(480, 255);
        jScrollAppointments.setPreferredSize(new java.awt.Dimension(480, 255));
        revalidate();
        
        renderPatients();
        
        java.util.Date date = new java.util.Date();
        int year = date.getYear() - 1;
        int month = date.getMonth();
        int day = date.getDate() - 1;

        this.fillComboBoxDay(month + 1, year + 1901);
        this.fillComboBoxMonthYear();

        cbDay.setSelectedIndex(-1);
        cbMonth.setSelectedIndex(-1);
        cbYear.setSelectedIndex(-1);
    }
    
    private void renderPatients(){
        jScrollPatients.removeAll();
        
        List<Patient> patients = PatientControl.getInstance().getPatients();
        
        if (patients != null) {
            if (patients.size() > 7) {
                jScrollPatients.setSize(300, 80 * patients.size() + 20);
                jScrollPatients.setPreferredSize(new java.awt.Dimension(300, 80 * patients.size() + 20));
                revalidate();
            }
        }
        
        if (patients != null) {
            int coorY = 20;
            for (Patient patient : patients) {
                PatientInfo patientinfo = new PatientInfo(this, patient);
                jScrollPatients.add(patientinfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, coorY, -1, -1));
                coorY += 80;
            }
        }
        revalidate();
    }
    
    private void fillComboBoxDay(int month, int year) {
        Calendar mycal = new GregorianCalendar(year, month, 0);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);

        ArrayList<String> days = new ArrayList<String>();

        for (int i = 0; i < daysInMonth; i++) {
            days.add(String.valueOf(i + 1));
        }

        cbDay.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));
    }

    private void fillComboBoxMonthYear() {
        ArrayList<String> months = new ArrayList<String>();
        ArrayList<String> years = new ArrayList<String>();

        for (int i = 0; i < 12; i++) {
            months.add(String.valueOf(i + 1));
        }

        java.util.Date date = new java.util.Date();
        int year = date.getYear();

        for (int i = 0; i < year; i++) {
            years.add(String.valueOf(i + 1901));
        }

        cbMonth.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));
        cbYear.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));
    }
    
    private boolean validateFields() {
        if (jTextField1.getText().isBlank() || jTextField1.getText().isEmpty()) {
            return false;
        }
        if (jTextField2.getText().isBlank() || jTextField2.getText().isEmpty()) {
            return false;
        }
        if (!jTextField9.getText().isBlank() || !jTextField9.getText().isEmpty()) {
            if (jTextField9.getText().length() > 200) {
                return false;
            }
        }
        if (!jTextField8.getText().isBlank() || !jTextField8.getText().isEmpty()) {
            if (jTextField8.getText().length() > 80) {
                return false;
            } else {
                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher mtch = pattern.matcher(jTextField8.getText());
                if (!mtch.matches()) {
                    return false;
                }
            }
        }
        
        if (jTextField1.getText().length() > 100 || jTextField2.getText().length() > 10) {
            return false;
        }
        
        return true;
    }
    
    private void cleanFields() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField9.setText("");
        jTextField8.setText("");
        cbDay.setSelectedIndex(-1);
        cbMonth.setSelectedIndex(-1);
        cbYear.setSelectedIndex(-1);
    }
    
    public void chargePatientInfo(Patient patient){
        jLabel7.setText(patient.getName());
        jLabel10.setText(patient.getPhone());
        jLabel13.setText(patient.getBirthDate().getDate() + "/" + (patient.getBirthDate().getMonth() + 1) + "/" + (patient.getBirthDate().getYear() + 1900));
        
        if (patient.getEmail() != null) {
            jLabel11.setText(patient.getEmail());
        } else {
            jLabel11.setText("");
        }
        
        if (patient.getAddress() != null) {
            jLabel12.setText(patient.getAddress());
        } else {
            jLabel12.setText("");
        }
        
    }
    
    public void renderAppointmensConf(Patient patient){
        jScrollAppointments.removeAll();
        
        List<Appointment> appointments = AppointmentControl.getInstance().getAppointmentFromNowOn();
        List<Appointment> appointmentsNC = new ArrayList<Appointment>();
        if (appointments != null) {
            for (Appointment appointment : appointments) {
                if (appointment.getPatient().getID() == patient.getID() && !appointment.isConfirmation()) {
                    appointmentsNC.add(appointment);
                }
            }
        }
        
        if (!appointmentsNC.isEmpty()) {
            if (appointmentsNC.size() > 12) {
                int rows = (int) Math.ceil(appointmentsNC.size() / 3.0);
                jScrollAppointments.setSize(480, (97 * rows) + 20);
                jScrollAppointments.setPreferredSize(new java.awt.Dimension(480, (97 * rows) + 20));
                revalidate();
            }

            int medC = 1;
            int coorY = 20;
            int coorX = 20;

            jScrollAppointments.removeAll();
            for (Appointment appointment : appointmentsNC) {
                AppointmentToConfirm app = new AppointmentToConfirm(appointment);
                jScrollAppointments.add(app, new org.netbeans.lib.awtextra.AbsoluteConstraints(coorX, coorY, -1, -1));
                coorX += 153;
                if (medC % 3 == 0) {
                    coorY += 117;
                    coorX = 20;
                }
                medC += 1;
            }
        } else {
            jScrollAppointments.removeAll();
            jScrollAppointments.add(new AppointmentToConfirm(null), new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        }
        
        revalidate();
        
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
        btnRegisterPatient = new javax.swing.JButton();
        containerPatient = new Components.RoundedPanel();
        jTextField1 = new javax.swing.JTextField();
        containerEnding = new Components.RoundedPanel();
        jTextField8 = new javax.swing.JTextField();
        jLBeginning2 = new javax.swing.JLabel();
        containerEnding3 = new Components.RoundedPanel();
        jTextField2 = new javax.swing.JTextField();
        jLBeginning3 = new javax.swing.JLabel();
        containerEnding4 = new Components.RoundedPanel();
        jTextField9 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLPatient1 = new javax.swing.JLabel();
        jLDate1 = new javax.swing.JLabel();
        jLBeginning1 = new javax.swing.JLabel();
        containerPatient1 = new Components.RoundedPanel();
        jLabel7 = new javax.swing.JLabel();
        containerEnding1 = new Components.RoundedPanel();
        jLabel13 = new javax.swing.JLabel();
        containerEnding2 = new Components.RoundedPanel();
        jLabel10 = new javax.swing.JLabel();
        jLPatient2 = new javax.swing.JLabel();
        roundedPanel7 = new Components.RoundedPanel();
        jLBeginning4 = new javax.swing.JLabel();
        containerEnding5 = new Components.RoundedPanel();
        jLabel11 = new javax.swing.JLabel();
        jLBeginning5 = new javax.swing.JLabel();
        containerEnding6 = new Components.RoundedPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLDay3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLDay2 = new javax.swing.JLabel();
        roundedPanel5 = new Components.RoundedPanel();
        nextMonth1 = new javax.swing.JButton();
        roundedPanel6 = new Components.RoundedPanel();
        previousMonth1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLDay1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPatients = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollAppointments = new javax.swing.JPanel();

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
        jLBeginning.setText("Email:");
        jPanel2.add(jLBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 10));

        containerBtnSchedule.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnSchedule.setLayout(new java.awt.BorderLayout());

        btnRegisterPatient.setBackground(new java.awt.Color(37, 119, 241));
        btnRegisterPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnRegisterPatient.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterPatient.setText("Registrar paciente");
        btnRegisterPatient.setBorderPainted(false);
        btnRegisterPatient.setContentAreaFilled(false);
        btnRegisterPatient.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRegisterPatientnMouseMoved(evt);
            }
        });
        btnRegisterPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegisterPatientMouseExited(evt);
            }
        });
        btnRegisterPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterPatientActionPerformed(evt);
            }
        });
        containerBtnSchedule.add(btnRegisterPatient, java.awt.BorderLayout.CENTER);

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

        jTextField8.setBackground(new java.awt.Color(244, 243, 243));
        jTextField8.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(35, 36, 37));
        jTextField8.setBorder(null);
        jTextField8.setIgnoreRepaint(true);
        containerEnding.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel2.add(containerEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 260, 30));

        jLBeginning2.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning2.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning2.setText("Telefono:");
        jPanel2.add(jLBeginning2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        containerEnding3.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setBackground(new java.awt.Color(244, 243, 243));
        jTextField2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(35, 36, 37));
        jTextField2.setBorder(null);
        jTextField2.setIgnoreRepaint(true);
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        containerEnding3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel2.add(containerEnding3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 260, 30));

        jLBeginning3.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning3.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning3.setText("Dirección:");
        jPanel2.add(jLBeginning3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 10));

        containerEnding4.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField9.setBackground(new java.awt.Color(244, 243, 243));
        jTextField9.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(35, 36, 37));
        jTextField9.setBorder(null);
        jTextField9.setIgnoreRepaint(true);
        containerEnding4.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel2.add(containerEnding4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 260, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 625));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLPatient1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient1.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient1.setText("Medicamentos:");
        jPanel3.add(jLPatient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, 10));

        jLDate1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate1.setForeground(new java.awt.Color(35, 36, 37));
        jLDate1.setText("Fecha de nacimiento:");
        jPanel3.add(jLDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, 10));

        jLBeginning1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning1.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning1.setText("Telefono:");
        jPanel3.add(jLBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        containerPatient1.setBackground(new java.awt.Color(244, 243, 243));
        containerPatient1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(35, 36, 37));
        containerPatient1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerPatient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 30));

        containerEnding1.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(35, 36, 37));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        containerEnding1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 140, 30));

        jPanel3.add(containerEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 160, 30));

        containerEnding2.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(35, 36, 37));
        containerEnding2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerEnding2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 30));

        jLPatient2.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient2.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient2.setText("Nombre:");
        jPanel3.add(jLPatient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 10));

        roundedPanel7.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel7.setEnabled(false);
        roundedPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(roundedPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 160, 150));

        jLBeginning4.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning4.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning4.setText("Email:");
        jPanel3.add(jLBeginning4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        containerEnding5.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(35, 36, 37));
        containerEnding5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerEnding5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 260, 30));

        jLBeginning5.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning5.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning5.setText("Dirección:");
        jPanel3.add(jLBeginning5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 10));

        containerEnding6.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(35, 36, 37));
        containerEnding6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 30));

        jPanel3.add(containerEnding6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 260, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 110, 480, 270));

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

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 480, 50));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jScrollPatients.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPatients.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane1.setViewportView(jScrollPatients);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 300, 575));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jScrollAppointments.setBackground(new java.awt.Color(255, 255, 255));
        jScrollAppointments.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jScrollPane3.setViewportView(jScrollAppointments);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, 480, 255));
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterPatientnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterPatientnMouseMoved
        containerBtnSchedule.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnRegisterPatientnMouseMoved

    private void btnRegisterPatientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegisterPatientMouseExited
        containerBtnSchedule.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnRegisterPatientMouseExited

    private void btnScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleActionPerformed
        
    }//GEN-LAST:event_btnScheduleActionPerformed

    private void btnRegisterPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterPatientActionPerformed
        if (validateFields()) {
            Patient pt = new Patient();
            pt.setName(jTextField1.getText());
            // (cbYear.getSelectedIndex() + 1901) + "-" + (cbMonth.getSelectedIndex() + 1) + "-" + (cbDay.getSelectedIndex() + 1)
            Date dt = Date.valueOf(LocalDate.of((cbYear.getSelectedIndex() + 1901), (cbMonth.getSelectedIndex() + 1), (cbDay.getSelectedIndex() + 1)));
            pt.setBirthDate(dt);
            pt.setAddress(jTextField9.getText());
            pt.setEmail(jTextField8.getText());
            pt.setPhone(jTextField2.getText());
            if (PatientControl.getInstance().addPatient(pt)) {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.CORRECT, "Registrar Paciente", "Paciente registrado correctamente");
                renderPatients();
                cleanFields();
            } else {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Registrar Paciente", "Imposible registrar paciente - Verifique los datos");
            }
        } else {
            App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Registrar Paciente", "Imposible registrar paciente - Campos vacios o incorrectos.");
        }
    }//GEN-LAST:event_btnRegisterPatientActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char c=evt.getKeyChar();
        
	if(Character.isLetter(c)) {
            evt.consume();
        }
        
        if (jTextField2.getText().length() >= 12) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegisterPatient;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private Components.RoundedPanel containerBtnSchedule;
    private Components.RoundedPanel containerDay;
    private Components.RoundedPanel containerEnding;
    private Components.RoundedPanel containerEnding1;
    private Components.RoundedPanel containerEnding2;
    private Components.RoundedPanel containerEnding3;
    private Components.RoundedPanel containerEnding4;
    private Components.RoundedPanel containerEnding5;
    private Components.RoundedPanel containerEnding6;
    private Components.RoundedPanel containerMonth;
    private Components.RoundedPanel containerPatient;
    private Components.RoundedPanel containerPatient1;
    private Components.RoundedPanel containerYear;
    private javax.swing.JLabel jLBeginning;
    private javax.swing.JLabel jLBeginning1;
    private javax.swing.JLabel jLBeginning2;
    private javax.swing.JLabel jLBeginning3;
    private javax.swing.JLabel jLBeginning4;
    private javax.swing.JLabel jLBeginning5;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLDate1;
    private javax.swing.JLabel jLDay1;
    private javax.swing.JLabel jLDay2;
    private javax.swing.JLabel jLDay3;
    private javax.swing.JLabel jLPatient;
    private javax.swing.JLabel jLPatient1;
    private javax.swing.JLabel jLPatient2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jScrollAppointments;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jScrollPatients;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton nextMonth1;
    private javax.swing.JButton previousMonth1;
    private Components.RoundedPanel roundedPanel5;
    private Components.RoundedPanel roundedPanel6;
    private Components.RoundedPanel roundedPanel7;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
