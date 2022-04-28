/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Components.AppointmentNERP;
import Components.AppointmentSRP;
import Components.CustomScrollBarUI;
import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import SIM.App;
import SIM.MessageType;
import control.AppointmentControl;
import control.PatientControl;
import java.awt.Color;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

/**
 *
 * @author orlandocamacho
 */
public class ScheduleAppointment extends javax.swing.JPanel {

    /**
     * Creates new form ScheduleAppointment
     */
    public ScheduleAppointment() {
        initComponents();
        AppointmentsScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollAppointments.setSize(220, 1029);
        scrollAppointments.setPreferredSize(new java.awt.Dimension(220, 1029));
        revalidate();
        renderAppointments();
        
        this.loadPatients();

        Date date = new Date();
        int year = date.getYear() - 1;
        int month = date.getMonth();
        int day = date.getDate() - 1;

        this.fillComboBoxDay(month + 1, year + 1901);
        this.fillComboBoxMonthYear();

        cbDay.setSelectedIndex(day);
        cbMonth.setSelectedIndex(month);
        cbYear.setSelectedIndex(year);
    }
    
     private void renderAppointments(){   
//        Timestamp currentTime = Timestamp.from(Instant.now());
//        Object[] appointments = AppointmentControl.getInstance().getAppointmentByWeek(currentTime);
//        int coorX = 20;
//        
//        for (Object appointment : appointments) {           
//            List<Appointment> appointmentsByDay = (List<Appointment>) appointment;
//            if (appointmentsByDay != null) {
//                for (Appointment appointmentByDay : appointmentsByDay) {
//                    int hour = appointmentByDay.getStartTime().getHours() - 11;
//                    int min = appointmentByDay.getStartTime().getMinutes()/15;
//                    int coorY = (hour * 128) + (min * 32) + 2;
//                    
//                    if (appointmentByDay.getaType() == AppointmentType.Surgical) {
//                        AppointmentSRP appointmentS = new AppointmentSRP(appointmentByDay);
//                        scrollAppointments.add(appointmentS, new org.netbeans.lib.awtextra.AbsoluteConstraints(coorX, coorY, -1, -1));
//                    } else {
//                        AppointmentNERP appointmentSP1 = new AppointmentNERP(appointmentByDay);
//                        scrollAppointments.add(appointmentSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(coorX, coorY, -1, -1));
//                    }
//                }
//            }
//            coorX += 150;
//        }
        
        int sepY = 123;
        for (int i = 0; i < 8; i++) {
            JSeparator jSeparator = new javax.swing.JSeparator();
            jSeparator.setForeground(new java.awt.Color(204, 204, 204));
            scrollAppointments.add(jSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, sepY, 180, -1));
            sepY += 128;
        }
    }

    private void loadPatients() {
        for (Patient patient : PatientControl.getInstance().getPatients()) {
            cbPatient.addItem(patient);
        }
        cbPatient.setSelectedIndex(-1);
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

        Date date = new Date();
        int year = date.getYear();

        for (int i = 0; i < year; i++) {
            years.add(String.valueOf(i + 1901));
        }

        cbMonth.setModel(new DefaultComboBoxModel<String>(months.toArray(new String[0])));
        cbYear.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));
    }

    private void updateHour() {
        if (cbService.getSelectedItem() != null) {
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
    }

    private void cleanFields() {
        cbPatient.setSelectedIndex(-1);
        cbService.setSelectedIndex(-1);
        cbHourBeginning.setSelectedIndex(-1);
        cbMinuteBeginning.setSelectedIndex(-1);
        cbHourEnding.setSelectedIndex(-1);
        cbMinuteEnding.setSelectedIndex(-1);
        tAReason.setText("");
    }

    private boolean validateFields() {
        if (cbPatient.getSelectedIndex() == -1 || cbService.getSelectedIndex() == -1) {
            return false;
        }
        
        if (cbHourBeginning.getSelectedIndex() == -1 || cbMinuteBeginning.getSelectedIndex() == -1 ) {
            return false;
        }

        return true;
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
        jLTitle = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLEnding = new javax.swing.JLabel();
        containerBeginning = new Components.RoundedPanel();
        cbMinuteBeginning = new javax.swing.JComboBox<>();
        jLNotes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLDate = new javax.swing.JLabel();
        containerReason = new Components.RoundedPanel();
        tAReason = new javax.swing.JTextArea();
        containerBeginning1 = new Components.RoundedPanel();
        cbHourBeginning = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        containerYear = new Components.RoundedPanel();
        cbYear = new javax.swing.JComboBox<>();
        containerDay = new Components.RoundedPanel();
        cbDay = new javax.swing.JComboBox<>();
        jLPatient = new javax.swing.JLabel();
        containerMonth = new Components.RoundedPanel();
        cbMonth = new javax.swing.JComboBox<>();
        containerEnding1 = new Components.RoundedPanel();
        cbHourEnding = new javax.swing.JComboBox<>();
        jLBeginning = new javax.swing.JLabel();
        jLService = new javax.swing.JLabel();
        containerBtnSchedule = new Components.RoundedPanel();
        btnSchedule = new javax.swing.JButton();
        containerService = new Components.RoundedPanel();
        cbService = new javax.swing.JComboBox<>();
        containerPatient = new Components.RoundedPanel();
        cbPatient = new javax.swing.JComboBox<>();
        containerEnding = new Components.RoundedPanel();
        cbMinuteEnding = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLSun = new javax.swing.JLabel();
        AppointmentsScrollPane = new javax.swing.JScrollPane();
        scrollAppointments = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1080, 685));
        setPreferredSize(new java.awt.Dimension(1080, 685));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLTitle.setForeground(new java.awt.Color(35, 36, 37));
        jLTitle.setText("Agendar cita");
        title.add(jLTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLEnding.setForeground(new java.awt.Color(35, 36, 37));
        jLEnding.setText("Final:");
        jPanel2.add(jLEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, 10));

        containerBeginning.setBackground(new java.awt.Color(244, 243, 243));
        containerBeginning.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteBeginning.setBackground(new java.awt.Color(244, 243, 243));
        cbMinuteBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteBeginning.setMaximumRowCount(12);
        cbMinuteBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteBeginning.setSelectedIndex(-1);
        cbMinuteBeginning.setBorder(null);
        cbMinuteBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteBeginning.setFocusable(false);
        cbMinuteBeginning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMinuteBeginningActionPerformed(evt);
            }
        });
        containerBeginning.add(cbMinuteBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel2.add(containerBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 160, 55, 30));

        jLNotes.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLNotes.setForeground(new java.awt.Color(35, 36, 37));
        jLNotes.setText("Notas:");
        jPanel2.add(jLNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 10));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 36, 37));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(":");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, 10, 30));

        jLDate.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate.setForeground(new java.awt.Color(35, 36, 37));
        jLDate.setText("Fecha:");
        jPanel2.add(jLDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 10));

        containerReason.setBackground(new java.awt.Color(244, 243, 243));
        containerReason.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tAReason.setBackground(new java.awt.Color(244, 243, 243));
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

        jPanel2.add(containerReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 260, 160));

        containerBeginning1.setBackground(new java.awt.Color(244, 243, 243));
        containerBeginning1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourBeginning.setBackground(new java.awt.Color(244, 243, 243));
        cbHourBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbHourBeginning.setMaximumRowCount(12);
        cbHourBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18" }));
        cbHourBeginning.setSelectedIndex(-1);
        cbHourBeginning.setBorder(null);
        cbHourBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourBeginning.setFocusable(false);
        cbHourBeginning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHourBeginningActionPerformed(evt);
            }
        });
        containerBeginning1.add(cbHourBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel2.add(containerBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 55, 30));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 36, 37));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 160, 10, 30));

        containerYear.setBackground(new java.awt.Color(244, 243, 243));
        containerYear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbYear.setBackground(new java.awt.Color(244, 243, 243));
        cbYear.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbYear.setForeground(new java.awt.Color(35, 36, 37));
        cbYear.setMaximumRowCount(12);
        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
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
        cbDay.setBorder(null);
        cbDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbDay.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbDay.setFocusable(false);
        containerDay.add(cbDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel2.add(containerDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 80, 30));

        jLPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient.setText("Paciente:");
        jPanel2.add(jLPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 10));

        containerMonth.setBackground(new java.awt.Color(244, 243, 243));
        containerMonth.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMonth.setBackground(new java.awt.Color(244, 243, 243));
        cbMonth.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMonth.setForeground(new java.awt.Color(35, 36, 37));
        cbMonth.setMaximumRowCount(12);
        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMonth.setBorder(null);
        cbMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMonth.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMonth.setFocusable(false);
        containerMonth.add(cbMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        jPanel2.add(containerMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 80, 30));

        containerEnding1.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourEnding.setBackground(new java.awt.Color(244, 243, 243));
        cbHourEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourEnding.setForeground(new java.awt.Color(35, 36, 37));
        cbHourEnding.setMaximumRowCount(12);
        cbHourEnding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        cbHourEnding.setSelectedIndex(-1);
        cbHourEnding.setBorder(null);
        cbHourEnding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourEnding.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourEnding.setEnabled(false);
        cbHourEnding.setFocusable(false);
        containerEnding1.add(cbHourEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel2.add(containerEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 55, 30));

        jLBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning.setText("Inicio:");
        jPanel2.add(jLBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 10));

        jLService.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLService.setForeground(new java.awt.Color(35, 36, 37));
        jLService.setText("Servicio:");
        jPanel2.add(jLService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 10));

        containerBtnSchedule.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnSchedule.setLayout(new java.awt.BorderLayout());

        btnSchedule.setBackground(new java.awt.Color(37, 119, 241));
        btnSchedule.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnSchedule.setForeground(new java.awt.Color(255, 255, 255));
        btnSchedule.setText("Agendar cita");
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
        btnSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScheduleActionPerformed(evt);
            }
        });
        containerBtnSchedule.add(btnSchedule, java.awt.BorderLayout.CENTER);

        jPanel2.add(containerBtnSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 40));

        containerService.setBackground(new java.awt.Color(244, 243, 243));
        containerService.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbService.setBackground(new java.awt.Color(244, 243, 243));
        cbService.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbService.setForeground(new java.awt.Color(35, 36, 37));
        cbService.setMaximumRowCount(12);
        cbService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nutricional", "Quirurgica", "Estetica" }));
        cbService.setSelectedIndex(-1);
        cbService.setBorder(null);
        cbService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbService.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbService.setFocusable(false);
        cbService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbServiceActionPerformed(evt);
            }
        });
        containerService.add(cbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        jPanel2.add(containerService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 260, 30));

        containerPatient.setBackground(new java.awt.Color(244, 243, 243));
        containerPatient.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbPatient.setBackground(new java.awt.Color(244, 243, 243));
        cbPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbPatient.setForeground(new java.awt.Color(35, 36, 37));
        cbPatient.setMaximumRowCount(12);
        cbPatient.setBorder(null);
        cbPatient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbPatient.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbPatient.setFocusable(false);
        containerPatient.add(cbPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        jPanel2.add(containerPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 30));

        containerEnding.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteEnding.setBackground(new java.awt.Color(244, 243, 243));
        cbMinuteEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteEnding.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteEnding.setMaximumRowCount(12);
        cbMinuteEnding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteEnding.setSelectedIndex(-1);
        cbMinuteEnding.setBorder(null);
        cbMinuteEnding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteEnding.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteEnding.setEnabled(false);
        cbMinuteEnding.setFocusable(false);
        containerEnding.add(cbMinuteEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel2.add(containerEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 160, 55, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 625));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLSun.setBackground(new java.awt.Color(255, 255, 255));
        jLSun.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLSun.setForeground(new java.awt.Color(35, 36, 37));
        jLSun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLSun.setText("Day");
        jLSun.setToolTipText("");
        jPanel1.add(jLSun, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 140, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 60, 220, 50));

        AppointmentsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        AppointmentsScrollPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        AppointmentsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollAppointments.setBackground(new java.awt.Color(255, 255, 255));
        scrollAppointments.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 36, 37));
        jLabel3.setText("12:00");
        scrollAppointments.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 123, 20, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(35, 36, 37));
        jLabel4.setText("14:00");
        scrollAppointments.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 379, 20, -1));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(35, 36, 37));
        jLabel5.setText("13:00");
        scrollAppointments.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 251, 20, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(35, 36, 37));
        jLabel6.setText("15:00");
        scrollAppointments.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 507, 20, -1));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(35, 36, 37));
        jLabel7.setText("16:00");
        scrollAppointments.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 635, 20, -1));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(35, 36, 37));
        jLabel8.setText("17:00");
        scrollAppointments.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 763, 20, -1));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(35, 36, 37));
        jLabel9.setText("18:00");
        scrollAppointments.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 891, 20, -1));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(35, 36, 37));
        jLabel10.setText("19:00");
        scrollAppointments.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1019, 20, -1));

        AppointmentsScrollPane.setViewportView(scrollAppointments);

        add(AppointmentsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 220, 575));
    }// </editor-fold>//GEN-END:initComponents

    private void tAReasonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tAReasonKeyTyped
        if (tAReason.getText().length() == 200)
            evt.consume();
    }//GEN-LAST:event_tAReasonKeyTyped

    private void btnSchedulenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSchedulenMouseMoved
        containerBtnSchedule.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnSchedulenMouseMoved

    private void btnScheduleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnScheduleMouseExited
        containerBtnSchedule.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnScheduleMouseExited

    private void btnScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScheduleActionPerformed
        if (validateFields()) {
            Timestamp startTime = Timestamp.valueOf((cbYear.getSelectedIndex() + 1901) + "-" + (cbMonth.getSelectedIndex() + 1) + "-" + (cbDay.getSelectedIndex() + 1) + " " + cbHourBeginning.getSelectedItem().toString() + ":" + cbMinuteBeginning.getSelectedItem().toString() + ":00");
            Patient patient = (Patient) cbPatient.getSelectedItem();
            AppointmentType type;
            Type patientType;

            if (AppointmentControl.getInstance().getAppointmentByPatient(patient) != null) {
                patientType = Type.Recurrent;
            } else {
                patientType = Type.New;
            }

            if (cbService.getSelectedIndex() == 0) {
                type = AppointmentType.Nutritional;
            } else if (cbService.getSelectedIndex() == 1) {
                type = AppointmentType.Surgical;
            } else {
                type = AppointmentType.Esthetic;
            }
            
            if (AppointmentControl.getInstance().addAppointment(new Appointment(startTime, patient, type, patientType, false, tAReason.getText()))) {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.CORRECT, "Agendar cita", "Cita agendada correctamente");
                cleanFields();
            } else {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Agendar cita", "Imposible agendar cita - Verifique los datos");
            }
        } else {
            App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Agendar cita", "Imposible agendar cita - Campos vacios");
        }
    }//GEN-LAST:event_btnScheduleActionPerformed

    private void cbServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbServiceActionPerformed
        this.updateHour();
    }//GEN-LAST:event_cbServiceActionPerformed

    private void cbHourBeginningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHourBeginningActionPerformed
        this.updateHour();
    }//GEN-LAST:event_cbHourBeginningActionPerformed

    private void cbMinuteBeginningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMinuteBeginningActionPerformed
        this.updateHour();
    }//GEN-LAST:event_cbMinuteBeginningActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AppointmentsScrollPane;
    private javax.swing.JButton btnSchedule;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbHourBeginning;
    private javax.swing.JComboBox<String> cbHourEnding;
    private javax.swing.JComboBox<String> cbMinuteBeginning;
    private javax.swing.JComboBox<String> cbMinuteEnding;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<Patient> cbPatient;
    private javax.swing.JComboBox<String> cbService;
    private javax.swing.JComboBox<String> cbYear;
    private Components.RoundedPanel containerBeginning;
    private Components.RoundedPanel containerBeginning1;
    private Components.RoundedPanel containerBtnSchedule;
    private Components.RoundedPanel containerDay;
    private Components.RoundedPanel containerEnding;
    private Components.RoundedPanel containerEnding1;
    private Components.RoundedPanel containerMonth;
    private Components.RoundedPanel containerPatient;
    private Components.RoundedPanel containerReason;
    private Components.RoundedPanel containerService;
    private Components.RoundedPanel containerYear;
    private javax.swing.JLabel jLBeginning;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLEnding;
    private javax.swing.JLabel jLNotes;
    private javax.swing.JLabel jLPatient;
    private javax.swing.JLabel jLService;
    private javax.swing.JLabel jLSun;
    private javax.swing.JLabel jLTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel scrollAppointments;
    private javax.swing.JTextArea tAReason;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
