/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Patient;
import control.AppointmentControl;
import control.PatientControl;
import java.awt.Color;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author orlandocamacho
 */
public class RescheduleAppointment extends javax.swing.JPanel {

    /**
     * Creates new form ScheduleAppointment
     */
    public RescheduleAppointment() {
        initComponents();
        this.loadPatients();
        
        Date date = new Date();
        int year = date.getYear() - 1;
        int month = date.getMonth();
        int day = date.getDate() - 1;
        
        this.fillComboBoxDay(month+1, year+1901);
        this.fillComboBoxMonthYear();
        
        cbNewDay.setSelectedIndex(day);
        cbNewMonth.setSelectedIndex(month);
        cbNewYear.setSelectedIndex(year);
    }
    
    private void loadPatients(){
        this.cbPatient.removeAllItems();
        for (Patient patient : PatientControl.getInstance().getPatients()) {
            cbPatient.addItem(patient);
        }
        cbPatient.setSelectedIndex(0);
    }
    
    private void loadAppointments(){
        this.cbScheduleDate.removeAllItems();
        Patient patient = (Patient) cbPatient.getSelectedItem();
        for (Appointment appointment : AppointmentControl.getInstance().getAppointment()) {
            if (patient.getID() == appointment.getPatient().getID()) {
                this.cbScheduleDate.addItem(appointment);
            }
        }
        cbScheduleDate.setSelectedIndex(0);
    }
    
    private void fillService(){
        Appointment app = (Appointment) cbScheduleDate.getSelectedItem();
        if (app.getaType() == AppointmentType.Nutritional) {
            cbService.setSelectedIndex(0);
        } else if (app.getaType() == AppointmentType.Surgical) {
            cbService.setSelectedIndex(1);
        } else {
            cbService.setSelectedIndex(2);
        }
    }
    
    private void fillHours(){
        Appointment app = (Appointment) cbScheduleDate.getSelectedItem();
        cbHourBeginningOld.setSelectedItem(String.valueOf(app.getStartTime().getHours()));
        
        if (app.getStartTime().getMinutes() != 0) {
            cbMinuteBeginningOld.setSelectedItem(String.valueOf(app.getStartTime().getMinutes()));
        } else {
            cbMinuteBeginningOld.setSelectedIndex(0);
        }
        
        if (cbService.getSelectedItem().toString().equals("Nutricional") || cbService.getSelectedItem().toString().equals("Estetica")) {
            if (cbMinuteBeginningOld.getSelectedIndex() == 0 || cbMinuteBeginningOld.getSelectedIndex() == 1 || cbMinuteBeginningOld.getSelectedIndex() == 2) {
                cbHourEndingOld.setSelectedIndex(cbHourBeginningOld.getSelectedIndex());
                cbMinuteEndingOld.setSelectedIndex(cbMinuteBeginningOld.getSelectedIndex() + 1);
            } else {
                cbHourEndingOld.setSelectedIndex(cbHourBeginningOld.getSelectedIndex() + 1);
                cbMinuteEndingOld.setSelectedIndex(0);
            }    
        } else {
            cbHourEndingOld.setSelectedIndex(cbHourBeginningOld.getSelectedIndex() + 1);
            cbMinuteEndingOld.setSelectedIndex(cbMinuteBeginningOld.getSelectedIndex());
        }
        
        
        this.tAReason.setText(app.getReason());
    }
    
    private void fillComboBoxDay(int month, int year){
        Calendar mycal = new GregorianCalendar(year, month, 0);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        
        ArrayList<String> days = new ArrayList<String>();
        
        for (int i = 0; i < daysInMonth; i++) {
            days.add(String.valueOf(i+1));
        }
        
        cbNewDay.setModel(new DefaultComboBoxModel<String>(days.toArray(new String[0])));
    }
    
    private void fillComboBoxMonthYear(){
        ArrayList<String> months = new ArrayList<String>();
        ArrayList<String> years = new ArrayList<String>();
        
        for (int i = 0; i < 12; i++){
            months.add(String.valueOf(i+1));
        }
        
        Date date = new Date();
        int year = date.getYear();
        
        for (int i = 0; i < year ; i++){
            years.add(String.valueOf(i+1901));
        }
        
        cbNewMonth.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));
        cbNewYear.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));
    }
    
    private void updateHour() {
        if (cbService.getSelectedItem().toString().equals("Nutricional") || cbService.getSelectedItem().toString().equals("Estetica")) {
            if (cbMinuteBeginning.getSelectedIndex() == 0 || cbMinuteBeginning.getSelectedIndex() == 1 || cbMinuteBeginning.getSelectedIndex() == 2) {
                cbHourEnding.setSelectedIndex(cbHourBeginning.getSelectedIndex());
                cbMinuteEnding.setSelectedIndex(cbMinuteBeginning.getSelectedIndex() + 1);
            } else {
                cbHourEnding.setSelectedIndex(cbHourBeginning.getSelectedIndex() + 1);
                cbMinuteEnding.setSelectedIndex(0);
            }    
        } else {
            cbHourEnding.setSelectedIndex(cbHourBeginning.getSelectedIndex() + 1);
            cbMinuteEnding.setSelectedIndex(cbMinuteBeginning.getSelectedIndex());
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

        title = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLPatient = new javax.swing.JLabel();
        containerScheduleDate = new Components.RoundedPanel();
        cbScheduleDate = new javax.swing.JComboBox<>();
        jLDate = new javax.swing.JLabel();
        containerPatient = new Components.RoundedPanel();
        cbPatient = new javax.swing.JComboBox<>();
        jLEnding = new javax.swing.JLabel();
        jLBeginning = new javax.swing.JLabel();
        containerNewMonth = new Components.RoundedPanel();
        cbNewMonth = new javax.swing.JComboBox<>();
        jLNewDate = new javax.swing.JLabel();
        containerNewDay = new Components.RoundedPanel();
        cbNewDay = new javax.swing.JComboBox<>();
        containerNewYear = new Components.RoundedPanel();
        cbNewYear = new javax.swing.JComboBox<>();
        jLNotes = new javax.swing.JLabel();
        containerService = new Components.RoundedPanel();
        cbService = new javax.swing.JComboBox<>();
        jLEnding1 = new javax.swing.JLabel();
        jLService = new javax.swing.JLabel();
        containerReason = new Components.RoundedPanel();
        tAReason = new javax.swing.JTextArea();
        jLBeginning1 = new javax.swing.JLabel();
        containerBtnReschedule = new Components.RoundedPanel();
        btnReschedule = new javax.swing.JButton();
        containerEnding1 = new Components.RoundedPanel();
        cbMinuteEndingOld = new javax.swing.JComboBox<>();
        containerBeginning1 = new Components.RoundedPanel();
        cbMinuteBeginningOld = new javax.swing.JComboBox<>();
        containerBeginning2 = new Components.RoundedPanel();
        cbHourBeginningOld = new javax.swing.JComboBox<>();
        containerEnding2 = new Components.RoundedPanel();
        cbHourEndingOld = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        containerEnding = new Components.RoundedPanel();
        cbMinuteEnding = new javax.swing.JComboBox<>();
        containerBeginning = new Components.RoundedPanel();
        cbMinuteBeginning = new javax.swing.JComboBox<>();
        containerBeginning3 = new Components.RoundedPanel();
        cbHourBeginning = new javax.swing.JComboBox<>();
        containerEnding3 = new Components.RoundedPanel();
        cbHourEnding = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1080, 685));
        setPreferredSize(new java.awt.Dimension(1080, 685));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 36, 37));
        jLabel2.setText("Reagendar cita");
        title.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jLPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient.setText("Paciente:");
        add(jLPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        containerScheduleDate.setBackground(new java.awt.Color(232, 240, 255));
        containerScheduleDate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbScheduleDate.setBackground(new java.awt.Color(232, 240, 255));
        cbScheduleDate.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbScheduleDate.setForeground(new java.awt.Color(35, 36, 37));
        cbScheduleDate.setMaximumRowCount(12);
        cbScheduleDate.setBorder(null);
        cbScheduleDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbScheduleDate.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbScheduleDate.setFocusable(false);
        cbScheduleDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbScheduleDateActionPerformed(evt);
            }
        });
        containerScheduleDate.add(cbScheduleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        add(containerScheduleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 260, 30));

        jLDate.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate.setForeground(new java.awt.Color(35, 36, 37));
        jLDate.setText("Fecha agendada:");
        jLDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        containerPatient.setBackground(new java.awt.Color(232, 240, 255));
        containerPatient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbPatient.setBackground(new java.awt.Color(232, 240, 255));
        cbPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbPatient.setForeground(new java.awt.Color(35, 36, 37));
        cbPatient.setMaximumRowCount(12);
        cbPatient.setBorder(null);
        cbPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbPatient.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbPatient.setFocusable(false);
        cbPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPatientActionPerformed(evt);
            }
        });
        containerPatient.add(cbPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        add(containerPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 30));

        jLEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLEnding.setForeground(new java.awt.Color(35, 36, 37));
        jLEnding.setText("Final agendado:");
        jLEnding.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 200, -1, 20));

        jLBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning.setText("Inicio agendado:");
        jLBeginning.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 20));

        containerNewMonth.setBackground(new java.awt.Color(232, 240, 255));
        containerNewMonth.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbNewMonth.setBackground(new java.awt.Color(232, 240, 255));
        cbNewMonth.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbNewMonth.setForeground(new java.awt.Color(35, 36, 37));
        cbNewMonth.setMaximumRowCount(12);
        cbNewMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNewMonth.setBorder(null);
        cbNewMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbNewMonth.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbNewMonth.setFocusable(false);
        containerNewMonth.add(cbNewMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        add(containerNewMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 80, 30));

        jLNewDate.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLNewDate.setForeground(new java.awt.Color(35, 36, 37));
        jLNewDate.setText("Fecha a reagendar:");
        jLNewDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLNewDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 20));

        containerNewDay.setBackground(new java.awt.Color(232, 240, 255));
        containerNewDay.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbNewDay.setBackground(new java.awt.Color(232, 240, 255));
        cbNewDay.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbNewDay.setForeground(new java.awt.Color(35, 36, 37));
        cbNewDay.setMaximumRowCount(12);
        cbNewDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNewDay.setBorder(null);
        cbNewDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbNewDay.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbNewDay.setFocusable(false);
        cbNewDay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbNewDayFocusLost(evt);
            }
        });
        containerNewDay.add(cbNewDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        add(containerNewDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 80, 30));

        containerNewYear.setBackground(new java.awt.Color(232, 240, 255));
        containerNewYear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbNewYear.setBackground(new java.awt.Color(232, 240, 255));
        cbNewYear.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbNewYear.setForeground(new java.awt.Color(35, 36, 37));
        cbNewYear.setMaximumRowCount(12);
        cbNewYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNewYear.setBorder(null);
        cbNewYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbNewYear.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbNewYear.setFocusable(false);
        containerNewYear.add(cbNewYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        add(containerNewYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 80, 30));

        jLNotes.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLNotes.setForeground(new java.awt.Color(35, 36, 37));
        jLNotes.setText("Notas:");
        add(jLNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, 10));

        containerService.setBackground(new java.awt.Color(232, 240, 255));
        containerService.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbService.setBackground(new java.awt.Color(232, 240, 255));
        cbService.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbService.setForeground(new java.awt.Color(35, 36, 37));
        cbService.setMaximumRowCount(12);
        cbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nutricional", "Quirurgica", "Estetica" }));
        cbService.setSelectedIndex(-1);
        cbService.setBorder(null);
        cbService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbService.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbService.setEnabled(false);
        cbService.setFocusable(false);
        containerService.add(cbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        add(containerService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 260, 30));

        jLEnding1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLEnding1.setForeground(new java.awt.Color(35, 36, 37));
        jLEnding1.setText("Final a agendar:");
        jLEnding1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, 20));

        jLService.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLService.setForeground(new java.awt.Color(35, 36, 37));
        jLService.setText("Servicio:");
        add(jLService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, 10));

        containerReason.setBackground(new java.awt.Color(232, 240, 255));
        containerReason.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tAReason.setBackground(new java.awt.Color(232, 240, 255));
        tAReason.setColumns(20);
        tAReason.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        tAReason.setForeground(new java.awt.Color(35, 36, 37));
        tAReason.setLineWrap(true);
        tAReason.setRows(7);
        tAReason.setTabSize(4);
        tAReason.setIgnoreRepaint(true);
        tAReason.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tAReasonKeyTyped(evt);
            }
        });
        containerReason.add(tAReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 140));

        add(containerReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 260, 160));

        jLBeginning1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning1.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning1.setText("Inicio a agendar:");
        jLBeginning1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 20));

        containerBtnReschedule.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnReschedule.setLayout(new java.awt.BorderLayout());

        btnReschedule.setBackground(new java.awt.Color(37, 119, 241));
        btnReschedule.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnReschedule.setForeground(new java.awt.Color(255, 255, 255));
        btnReschedule.setText("Reagendar cita");
        btnReschedule.setBorderPainted(false);
        btnReschedule.setContentAreaFilled(false);
        btnReschedule.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnReschedulenMouseMoved(evt);
            }
        });
        btnReschedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRescheduleMouseExited(evt);
            }
        });
        btnReschedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRescheduleActionPerformed(evt);
            }
        });
        containerBtnReschedule.add(btnReschedule, java.awt.BorderLayout.CENTER);

        add(containerBtnReschedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 260, 40));

        containerEnding1.setBackground(new java.awt.Color(232, 240, 255));
        containerEnding1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteEndingOld.setBackground(new java.awt.Color(232, 240, 255));
        cbMinuteEndingOld.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteEndingOld.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteEndingOld.setMaximumRowCount(12);
        cbMinuteEndingOld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteEndingOld.setBorder(null);
        cbMinuteEndingOld.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteEndingOld.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteEndingOld.setEnabled(false);
        cbMinuteEndingOld.setFocusable(false);
        containerEnding1.add(cbMinuteEndingOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 220, 55, 30));

        containerBeginning1.setBackground(new java.awt.Color(232, 240, 255));
        containerBeginning1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteBeginningOld.setBackground(new java.awt.Color(232, 240, 255));
        cbMinuteBeginningOld.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteBeginningOld.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteBeginningOld.setMaximumRowCount(12);
        cbMinuteBeginningOld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteBeginningOld.setBorder(null);
        cbMinuteBeginningOld.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteBeginningOld.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteBeginningOld.setEnabled(false);
        cbMinuteBeginningOld.setFocusable(false);
        cbMinuteBeginningOld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbMinuteBeginningOldFocusLost(evt);
            }
        });
        containerBeginning1.add(cbMinuteBeginningOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 220, 55, 30));

        containerBeginning2.setBackground(new java.awt.Color(232, 240, 255));
        containerBeginning2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourBeginningOld.setBackground(new java.awt.Color(232, 240, 255));
        cbHourBeginningOld.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourBeginningOld.setForeground(new java.awt.Color(35, 36, 37));
        cbHourBeginningOld.setMaximumRowCount(12);
        cbHourBeginningOld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18" }));
        cbHourBeginningOld.setBorder(null);
        cbHourBeginningOld.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourBeginningOld.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourBeginningOld.setEnabled(false);
        cbHourBeginningOld.setFocusable(false);
        cbHourBeginningOld.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbHourBeginningOldFocusLost(evt);
            }
        });
        containerBeginning2.add(cbHourBeginningOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerBeginning2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 55, 30));

        containerEnding2.setBackground(new java.awt.Color(232, 240, 255));
        containerEnding2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourEndingOld.setBackground(new java.awt.Color(232, 240, 255));
        cbHourEndingOld.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourEndingOld.setForeground(new java.awt.Color(35, 36, 37));
        cbHourEndingOld.setMaximumRowCount(12);
        cbHourEndingOld.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        cbHourEndingOld.setBorder(null);
        cbHourEndingOld.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourEndingOld.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourEndingOld.setEnabled(false);
        cbHourEndingOld.setFocusable(false);
        containerEnding2.add(cbHourEndingOld, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerEnding2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 55, 30));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 36, 37));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 220, 10, 30));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 36, 37));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(":");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 220, 10, 30));

        containerEnding.setBackground(new java.awt.Color(232, 240, 255));
        containerEnding.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteEnding.setBackground(new java.awt.Color(232, 240, 255));
        cbMinuteEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteEnding.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteEnding.setMaximumRowCount(12);
        cbMinuteEnding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteEnding.setBorder(null);
        cbMinuteEnding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteEnding.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteEnding.setEnabled(false);
        cbMinuteEnding.setFocusable(false);
        containerEnding.add(cbMinuteEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 55, 30));

        containerBeginning.setBackground(new java.awt.Color(232, 240, 255));
        containerBeginning.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteBeginning.setBackground(new java.awt.Color(232, 240, 255));
        cbMinuteBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteBeginning.setMaximumRowCount(12);
        cbMinuteBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteBeginning.setBorder(null);
        cbMinuteBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteBeginning.setFocusable(false);
        cbMinuteBeginning.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbMinuteBeginningFocusLost(evt);
            }
        });
        containerBeginning.add(cbMinuteBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 55, 30));

        containerBeginning3.setBackground(new java.awt.Color(232, 240, 255));
        containerBeginning3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourBeginning.setBackground(new java.awt.Color(232, 240, 255));
        cbHourBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbHourBeginning.setMaximumRowCount(12);
        cbHourBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18" }));
        cbHourBeginning.setBorder(null);
        cbHourBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourBeginning.setFocusable(false);
        cbHourBeginning.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbHourBeginningFocusLost(evt);
            }
        });
        containerBeginning3.add(cbHourBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerBeginning3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 55, 30));

        containerEnding3.setBackground(new java.awt.Color(232, 240, 255));
        containerEnding3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourEnding.setBackground(new java.awt.Color(232, 240, 255));
        cbHourEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourEnding.setForeground(new java.awt.Color(35, 36, 37));
        cbHourEnding.setMaximumRowCount(12);
        cbHourEnding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        cbHourEnding.setBorder(null);
        cbHourEnding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourEnding.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourEnding.setEnabled(false);
        cbHourEnding.setFocusable(false);
        containerEnding3.add(cbHourEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        add(containerEnding3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 55, 30));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 36, 37));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(":");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 10, 30));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(35, 36, 37));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(":");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 10, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void tAReasonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tAReasonKeyTyped
        if (tAReason.getText().length() == 200)
        evt.consume();
    }//GEN-LAST:event_tAReasonKeyTyped

    private void btnReschedulenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReschedulenMouseMoved
        containerBtnReschedule.setBackground(new Color(35,111,229));
    }//GEN-LAST:event_btnReschedulenMouseMoved

    private void btnRescheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRescheduleMouseExited
        containerBtnReschedule.setBackground(new Color(37,119,241));
    }//GEN-LAST:event_btnRescheduleMouseExited

    private void btnRescheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRescheduleActionPerformed
        Appointment app = (Appointment)this.cbScheduleDate.getSelectedItem();
        Timestamp startTime = Timestamp.valueOf((cbNewYear.getSelectedIndex() + 1901) + "-" + (cbNewMonth.getSelectedIndex() + 1) + "-" + (cbNewDay.getSelectedIndex() + 1) +" " + cbHourBeginning.getSelectedItem().toString() + ":" + cbMinuteBeginning.getSelectedItem().toString() + ":00");
        
        app.setStartTime(startTime);
        app.setReason(this.tAReason.getText());
        
        System.out.println(AppointmentControl.getInstance().editAppointment(app));
        
    }//GEN-LAST:event_btnRescheduleActionPerformed

    private void cbPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPatientActionPerformed
        this.cbScheduleDate.removeAllItems();
        this.loadAppointments();
        this.fillService();
        this.fillHours();
    }//GEN-LAST:event_cbPatientActionPerformed

    private void cbScheduleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbScheduleDateActionPerformed
        if (cbScheduleDate.getSelectedItem() != null) {
            this.fillService();
            this.fillHours();
            this.cbHourBeginning.enable(true);
            this.cbMinuteBeginning.enable(true);
        } else {
            this.cbHourBeginning.enable(false);
            this.cbMinuteBeginning.enable(false);
        }
        
    }//GEN-LAST:event_cbScheduleDateActionPerformed

    private void cbNewDayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbNewDayFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNewDayFocusLost

    private void cbMinuteBeginningOldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbMinuteBeginningOldFocusLost
        this.updateHour();
    }//GEN-LAST:event_cbMinuteBeginningOldFocusLost

    private void cbHourBeginningOldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbHourBeginningOldFocusLost
        this.updateHour();
    }//GEN-LAST:event_cbHourBeginningOldFocusLost

    private void cbMinuteBeginningFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbMinuteBeginningFocusLost
        this.updateHour();
    }//GEN-LAST:event_cbMinuteBeginningFocusLost

    private void cbHourBeginningFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbHourBeginningFocusLost
        this.updateHour();
    }//GEN-LAST:event_cbHourBeginningFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReschedule;
    private javax.swing.JComboBox<String> cbHourBeginning;
    private javax.swing.JComboBox<String> cbHourBeginningOld;
    private javax.swing.JComboBox<String> cbHourEnding;
    private javax.swing.JComboBox<String> cbHourEndingOld;
    private javax.swing.JComboBox<String> cbMinuteBeginning;
    private javax.swing.JComboBox<String> cbMinuteBeginningOld;
    private javax.swing.JComboBox<String> cbMinuteEnding;
    private javax.swing.JComboBox<String> cbMinuteEndingOld;
    private javax.swing.JComboBox<String> cbNewDay;
    private javax.swing.JComboBox<String> cbNewMonth;
    private javax.swing.JComboBox<String> cbNewYear;
    private javax.swing.JComboBox<Patient> cbPatient;
    private javax.swing.JComboBox<Appointment> cbScheduleDate;
    private javax.swing.JComboBox<String> cbService;
    private Components.RoundedPanel containerBeginning;
    private Components.RoundedPanel containerBeginning1;
    private Components.RoundedPanel containerBeginning2;
    private Components.RoundedPanel containerBeginning3;
    private Components.RoundedPanel containerBtnReschedule;
    private Components.RoundedPanel containerEnding;
    private Components.RoundedPanel containerEnding1;
    private Components.RoundedPanel containerEnding2;
    private Components.RoundedPanel containerEnding3;
    private Components.RoundedPanel containerNewDay;
    private Components.RoundedPanel containerNewMonth;
    private Components.RoundedPanel containerNewYear;
    private Components.RoundedPanel containerPatient;
    private Components.RoundedPanel containerReason;
    private Components.RoundedPanel containerScheduleDate;
    private Components.RoundedPanel containerService;
    private javax.swing.JLabel jLBeginning;
    private javax.swing.JLabel jLBeginning1;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLEnding;
    private javax.swing.JLabel jLEnding1;
    private javax.swing.JLabel jLNewDate;
    private javax.swing.JLabel jLNotes;
    private javax.swing.JLabel jLPatient;
    private javax.swing.JLabel jLService;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextArea tAReason;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
