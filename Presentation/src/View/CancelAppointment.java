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
import Domain.Patient;
import SIM.App;
import SIM.MessageType;
import control.AppointmentControl;
import control.PatientControl;
import java.awt.Color;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/**
 *
 * @author orlandocamacho
 */
public class CancelAppointment extends javax.swing.JPanel {

    Timestamp currentTime = Timestamp.from(Instant.now());

    /**
     * Creates new form ScheduleAppointment
     */
    public CancelAppointment() {
        initComponents();
        AppointmentsScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        scrollAppointments.setSize(260, 1029);
        scrollAppointments.setPreferredSize(new java.awt.Dimension(260, 1029));
        revalidate();

        renderAppointments(currentTime);
        renderInfoAppointmentsToday();

        this.loadPatients();
    }

    private void renderAppointments(Timestamp currentTime) {
        switch (currentTime.getDay()) {
            case 0:
                jLDay.setText("Dom " + currentTime.getDate());
                break;
            case 1:
                jLDay.setText("Lun " + currentTime.getDate());
                break;
            case 2:
                jLDay.setText("Mar " + currentTime.getDate());
                break;
            case 3:
                jLDay.setText("Mie " + currentTime.getDate());
                break;
            case 4:
                jLDay.setText("Jue " + currentTime.getDate());
                break;
            case 5:
                jLDay.setText("Vie " + currentTime.getDate());
                break;
            case 6:
                jLDay.setText("Sab " + currentTime.getDate());
                break;
        }

        scrollAppointments.removeAll();

        List<Appointment> appointmentsByDay = AppointmentControl.getInstance().getAppointmentByDay(currentTime);
        if (appointmentsByDay != null) {
            for (Appointment appointment : appointmentsByDay) {
                int hour = appointment.getStartTime().getHours() - 11;
                int min = appointment.getStartTime().getMinutes() / 15;
                int coorY = (hour * 128) + (min * 32) + 2;

                if (appointment.getaType() == AppointmentType.Surgical) {
                    AppointmentSRP appointmentS = new AppointmentSRP(appointment);
                    scrollAppointments.add(appointmentS, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, coorY, -1, -1));
                } else {
                    AppointmentNERP appointmentSP1 = new AppointmentNERP(appointment);
                    scrollAppointments.add(appointmentSP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, coorY, -1, -1));
                }
            }
        }

        int sepY = 123;
        int hour = 12;
        for (int i = 0; i < 8; i++) {
            JSeparator jSeparator = new javax.swing.JSeparator();
            JLabel jLabel = new javax.swing.JLabel();

            jSeparator.setForeground(new java.awt.Color(204, 204, 204));
            jLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 8)); // NOI18N
            jLabel.setForeground(new java.awt.Color(35, 36, 37));
            jLabel.setText(hour + ":00");

            scrollAppointments.add(jLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, sepY, 20, -1));
            scrollAppointments.add(jSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, sepY, 220, -1));
            sepY += 128;
            hour += 1;
        }
    }

    private void renderInfoAppointmentsToday(){
        List<Appointment> appointmentsByDay = AppointmentControl.getInstance().getAppointmentByDay(currentTime);
        int nutritionalApp = 0;
        int estheticsApp = 0;
        int surgicalApp = 0;
        int confirmApp = 0;
        
        for (Appointment appointment : appointmentsByDay) {
            if (appointment.getaType() == AppointmentType.Nutritional) {
                nutritionalApp += 1;
            } else if ((appointment.getaType() == AppointmentType.Esthetic)){
                estheticsApp += 1;
            } else {
                surgicalApp += 1; 
            }
            
            if (appointment.isConfirmation()) {
                confirmApp += 1;
            }
            
            jLabel14.setText(String.valueOf(nutritionalApp));
            jLabel26.setText(String.valueOf(estheticsApp));
            jLabel16.setText(String.valueOf(surgicalApp));
            jLabel24.setText(confirmApp + "/" + appointmentsByDay.size());
        }
    }
    
    private void loadPatients() {
        this.cbPatient.removeAllItems();
        for (Patient patient : PatientControl.getInstance().getPatients()) {
            cbPatient.addItem(patient);
        }
        cbPatient.setSelectedIndex(-1);
    }

    private void loadAppointments() {
        if (cbPatient.getSelectedIndex() == -1) {
            this.fillService();
            this.fillHours();

        } else {
            this.cbScheduleDate.removeAllItems();
            Patient patient = (Patient) cbPatient.getSelectedItem();
            List<Appointment> appointments = AppointmentControl.getInstance().getAppointmentByPatient(patient);

            if (appointments != null) {
                for (Appointment appointment : appointments) {
                    this.cbScheduleDate.addItem(appointment);
                }
                cbScheduleDate.setSelectedIndex(0);
            }
        }
    }

    private void fillService() {
        Appointment app = (Appointment) cbScheduleDate.getSelectedItem();
        if (app != null) {
            if (app.getaType() == AppointmentType.Nutritional) {
                cbService.setSelectedIndex(0);
            } else if (app.getaType() == AppointmentType.Surgical) {
                cbService.setSelectedIndex(1);
            } else {
                cbService.setSelectedIndex(2);
            }
        } else {
            cbService.setSelectedIndex(-1);
        }
    }

    private void fillHours() {
        Appointment app = (Appointment) cbScheduleDate.getSelectedItem();
        if (app != null) {
            cbHourBeginning.setSelectedItem(String.valueOf(app.getStartTime().getHours()));

            if (app.getStartTime().getMinutes() != 0) {
                cbMinuteBeginning.setSelectedItem(String.valueOf(app.getStartTime().getMinutes()));
            } else {
                cbMinuteBeginning.setSelectedIndex(0);
            }

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

            this.tAReason.setText(app.getReason());
        } else {
            cbHourBeginning.setSelectedIndex(-1);
            cbMinuteBeginning.setSelectedIndex(-1);
            cbHourEnding.setSelectedIndex(-1);
            cbMinuteEnding.setSelectedIndex(-1);
        }
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

    private void cleanFields() {
        cbPatient.setSelectedIndex(-1);
        tAReason.setText("");
        loadAppointments();
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
        jLDay = new javax.swing.JLabel();
        roundedPanel1 = new Components.RoundedPanel();
        nextDay = new javax.swing.JButton();
        roundedPanel2 = new Components.RoundedPanel();
        previousDay = new javax.swing.JButton();
        AppointmentsScrollPane = new javax.swing.JScrollPane();
        scrollAppointments = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLPatient = new javax.swing.JLabel();
        containerScheduleDate = new Components.RoundedPanel();
        cbScheduleDate = new javax.swing.JComboBox<>();
        jLDate = new javax.swing.JLabel();
        containerPatient = new Components.RoundedPanel();
        cbPatient = new javax.swing.JComboBox<>();
        jLEnding = new javax.swing.JLabel();
        jLBeginning = new javax.swing.JLabel();
        jLNotes = new javax.swing.JLabel();
        containerService = new Components.RoundedPanel();
        cbService = new javax.swing.JComboBox<>();
        jLService = new javax.swing.JLabel();
        containerReason = new Components.RoundedPanel();
        tAReason = new javax.swing.JTextArea();
        containerBtnCancel = new Components.RoundedPanel();
        btnCancel = new javax.swing.JButton();
        containerEnding = new Components.RoundedPanel();
        cbMinuteEnding = new javax.swing.JComboBox<>();
        containerBeginning = new Components.RoundedPanel();
        cbMinuteBeginning = new javax.swing.JComboBox<>();
        containerBeginning1 = new Components.RoundedPanel();
        cbHourBeginning = new javax.swing.JComboBox<>();
        containerEnding1 = new Components.RoundedPanel();
        cbHourEnding = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLDay1 = new javax.swing.JLabel();
        roundedPanel3 = new Components.RoundedPanel();
        nextMonth = new javax.swing.JButton();
        roundedPanel4 = new Components.RoundedPanel();
        previousMonth = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLPatient1 = new javax.swing.JLabel();
        roundedPanel34 = new Components.RoundedPanel();
        roundedPanel40 = new Components.RoundedPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        roundedPanel35 = new Components.RoundedPanel();
        roundedPanel38 = new Components.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        roundedPanel36 = new Components.RoundedPanel();
        roundedPanel55 = new Components.RoundedPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        roundedPanel37 = new Components.RoundedPanel();
        roundedPanel39 = new Components.RoundedPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        roundedPanel5 = new Components.RoundedPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        roundedPanel6 = new Components.RoundedPanel();
        jLabel23 = new javax.swing.JLabel();
        roundedPanel7 = new Components.RoundedPanel();
        jLabel17 = new javax.swing.JLabel();
        roundedPanel8 = new Components.RoundedPanel();
        jLabel18 = new javax.swing.JLabel();
        roundedPanel9 = new Components.RoundedPanel();
        jLabel19 = new javax.swing.JLabel();
        roundedPanel10 = new Components.RoundedPanel();
        jLabel20 = new javax.swing.JLabel();
        roundedPanel11 = new Components.RoundedPanel();
        jLabel21 = new javax.swing.JLabel();
        roundedPanel12 = new Components.RoundedPanel();
        jLabel22 = new javax.swing.JLabel();
        roundedPanel13 = new Components.RoundedPanel();
        jLabel52 = new javax.swing.JLabel();
        roundedPanel14 = new Components.RoundedPanel();
        jLabel53 = new javax.swing.JLabel();
        roundedPanel15 = new Components.RoundedPanel();
        jLabel54 = new javax.swing.JLabel();
        roundedPanel16 = new Components.RoundedPanel();
        jLabel55 = new javax.swing.JLabel();
        roundedPanel17 = new Components.RoundedPanel();
        jLabel56 = new javax.swing.JLabel();
        roundedPanel18 = new Components.RoundedPanel();
        jLabel57 = new javax.swing.JLabel();
        roundedPanel19 = new Components.RoundedPanel();
        jLabel58 = new javax.swing.JLabel();
        roundedPanel20 = new Components.RoundedPanel();
        jLabel59 = new javax.swing.JLabel();
        roundedPanel21 = new Components.RoundedPanel();
        jLabel60 = new javax.swing.JLabel();
        roundedPanel22 = new Components.RoundedPanel();
        jLabel61 = new javax.swing.JLabel();
        roundedPanel23 = new Components.RoundedPanel();
        jLabel62 = new javax.swing.JLabel();
        roundedPanel24 = new Components.RoundedPanel();
        jLabel63 = new javax.swing.JLabel();
        roundedPanel25 = new Components.RoundedPanel();
        jLabel64 = new javax.swing.JLabel();
        roundedPanel26 = new Components.RoundedPanel();
        jLabel65 = new javax.swing.JLabel();
        roundedPanel27 = new Components.RoundedPanel();
        jLabel66 = new javax.swing.JLabel();
        roundedPanel28 = new Components.RoundedPanel();
        jLabel67 = new javax.swing.JLabel();
        roundedPanel29 = new Components.RoundedPanel();
        jLabel68 = new javax.swing.JLabel();
        roundedPanel30 = new Components.RoundedPanel();
        jLabel69 = new javax.swing.JLabel();
        roundedPanel31 = new Components.RoundedPanel();
        jLabel70 = new javax.swing.JLabel();
        roundedPanel32 = new Components.RoundedPanel();
        jLabel71 = new javax.swing.JLabel();
        roundedPanel33 = new Components.RoundedPanel();
        jLabel72 = new javax.swing.JLabel();
        roundedPanel41 = new Components.RoundedPanel();
        jLabel80 = new javax.swing.JLabel();
        roundedPanel42 = new Components.RoundedPanel();
        jLabel81 = new javax.swing.JLabel();
        roundedPanel43 = new Components.RoundedPanel();
        jLabel82 = new javax.swing.JLabel();
        roundedPanel44 = new Components.RoundedPanel();
        jLabel83 = new javax.swing.JLabel();
        roundedPanel45 = new Components.RoundedPanel();
        jLabel84 = new javax.swing.JLabel();
        roundedPanel46 = new Components.RoundedPanel();
        jLabel85 = new javax.swing.JLabel();
        roundedPanel47 = new Components.RoundedPanel();
        jLabel86 = new javax.swing.JLabel();
        roundedPanel48 = new Components.RoundedPanel();
        jLabel87 = new javax.swing.JLabel();
        roundedPanel49 = new Components.RoundedPanel();
        jLabel88 = new javax.swing.JLabel();
        roundedPanel50 = new Components.RoundedPanel();
        jLabel89 = new javax.swing.JLabel();
        roundedPanel51 = new Components.RoundedPanel();
        jLabel90 = new javax.swing.JLabel();
        roundedPanel52 = new Components.RoundedPanel();
        jLabel91 = new javax.swing.JLabel();
        roundedPanel53 = new Components.RoundedPanel();
        jLabel92 = new javax.swing.JLabel();
        roundedPanel54 = new Components.RoundedPanel();
        jLabel93 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1080, 685));
        setPreferredSize(new java.awt.Dimension(1080, 685));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setBackground(new java.awt.Color(255, 255, 255));
        title.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(35, 36, 37));
        jLabel2.setText("Cancelar cita");
        title.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 60));

        add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDay.setBackground(new java.awt.Color(255, 255, 255));
        jLDay.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLDay.setForeground(new java.awt.Color(35, 36, 37));
        jLDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDay.setText("Day");
        jLDay.setToolTipText("");
        jPanel2.add(jLDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 30));

        roundedPanel1.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel1.setLayout(new java.awt.BorderLayout());

        nextDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nextDay.png"))); // NOI18N
        nextDay.setBorderPainted(false);
        nextDay.setContentAreaFilled(false);
        nextDay.setFocusPainted(false);
        nextDay.setIgnoreRepaint(true);
        nextDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextDayActionPerformed(evt);
            }
        });
        roundedPanel1.add(nextDay, java.awt.BorderLayout.CENTER);

        jPanel2.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 30, 30));

        roundedPanel2.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel2.setLayout(new java.awt.BorderLayout());

        previousDay.setBackground(new java.awt.Color(244, 243, 243));
        previousDay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previousDay.png"))); // NOI18N
        previousDay.setBorderPainted(false);
        previousDay.setContentAreaFilled(false);
        previousDay.setFocusPainted(false);
        previousDay.setIgnoreRepaint(true);
        previousDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousDayActionPerformed(evt);
            }
        });
        roundedPanel2.add(previousDay, java.awt.BorderLayout.CENTER);

        jPanel2.add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 60, 260, 50));

        AppointmentsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        AppointmentsScrollPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(204, 204, 204)));
        AppointmentsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollAppointments.setBackground(new java.awt.Color(255, 255, 255));
        scrollAppointments.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        AppointmentsScrollPane.setViewportView(scrollAppointments);

        add(AppointmentsScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 260, 575));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLPatient.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient.setText("Paciente:");
        jPanel1.add(jLPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 10));

        containerScheduleDate.setBackground(new java.awt.Color(244, 243, 243));
        containerScheduleDate.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbScheduleDate.setBackground(new java.awt.Color(244, 243, 243));
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

        jPanel1.add(containerScheduleDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 30));

        jLDate.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLDate.setForeground(new java.awt.Color(35, 36, 37));
        jLDate.setText("Fecha agendada:");
        jLDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

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
        cbPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPatientActionPerformed(evt);
            }
        });
        containerPatient.add(cbPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        jPanel1.add(containerPatient, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 260, 30));

        jLEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLEnding.setForeground(new java.awt.Color(35, 36, 37));
        jLEnding.setText("Final agendado:");
        jLEnding.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, 20));

        jLBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLBeginning.setForeground(new java.awt.Color(35, 36, 37));
        jLBeginning.setText("Inicio agendado:");
        jLBeginning.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 20));

        jLNotes.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLNotes.setForeground(new java.awt.Color(35, 36, 37));
        jLNotes.setText("Notas:");
        jPanel1.add(jLNotes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, 10));

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
        cbService.setEnabled(false);
        cbService.setFocusable(false);
        containerService.add(cbService, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 30));

        jPanel1.add(containerService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 260, 30));

        jLService.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLService.setForeground(new java.awt.Color(35, 36, 37));
        jLService.setText("Servicio:");
        jPanel1.add(jLService, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, 10));

        containerReason.setBackground(new java.awt.Color(244, 243, 243));
        containerReason.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tAReason.setBackground(new java.awt.Color(244, 243, 243));
        tAReason.setColumns(20);
        tAReason.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        tAReason.setForeground(new java.awt.Color(35, 36, 37));
        tAReason.setLineWrap(true);
        tAReason.setRows(7);
        tAReason.setTabSize(4);
        tAReason.setEnabled(false);
        tAReason.setIgnoreRepaint(true);
        containerReason.add(tAReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 140));

        jPanel1.add(containerReason, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 260, 160));

        containerBtnCancel.setBackground(new java.awt.Color(37, 119, 241));
        containerBtnCancel.setLayout(new java.awt.BorderLayout());

        btnCancel.setBackground(new java.awt.Color(37, 119, 241));
        btnCancel.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancelar cita");
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnCancelnMouseMoved(evt);
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

        jPanel1.add(containerBtnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 260, 40));

        containerEnding.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteEnding.setBackground(new java.awt.Color(244, 243, 243));
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

        jPanel1.add(containerEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 160, 55, 30));

        containerBeginning.setBackground(new java.awt.Color(244, 243, 243));
        containerBeginning.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbMinuteBeginning.setBackground(new java.awt.Color(244, 243, 243));
        cbMinuteBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbMinuteBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbMinuteBeginning.setMaximumRowCount(12);
        cbMinuteBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMinuteBeginning.setBorder(null);
        cbMinuteBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbMinuteBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbMinuteBeginning.setEnabled(false);
        cbMinuteBeginning.setFocusable(false);
        containerBeginning.add(cbMinuteBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel1.add(containerBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 160, 55, 30));

        containerBeginning1.setBackground(new java.awt.Color(244, 243, 243));
        containerBeginning1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourBeginning.setBackground(new java.awt.Color(244, 243, 243));
        cbHourBeginning.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourBeginning.setForeground(new java.awt.Color(35, 36, 37));
        cbHourBeginning.setMaximumRowCount(12);
        cbHourBeginning.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18" }));
        cbHourBeginning.setBorder(null);
        cbHourBeginning.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourBeginning.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourBeginning.setEnabled(false);
        cbHourBeginning.setFocusable(false);
        containerBeginning1.add(cbHourBeginning, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel1.add(containerBeginning1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 55, 30));

        containerEnding1.setBackground(new java.awt.Color(244, 243, 243));
        containerEnding1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbHourEnding.setBackground(new java.awt.Color(244, 243, 243));
        cbHourEnding.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        cbHourEnding.setForeground(new java.awt.Color(35, 36, 37));
        cbHourEnding.setMaximumRowCount(12);
        cbHourEnding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
        cbHourEnding.setBorder(null);
        cbHourEnding.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbHourEnding.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        cbHourEnding.setEnabled(false);
        cbHourEnding.setFocusable(false);
        containerEnding1.add(cbHourEnding, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 75, 30));

        jPanel1.add(containerEnding1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 55, 30));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(35, 36, 37));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 160, 10, 30));

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(35, 36, 37));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(":");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, 10, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 300, 625));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLDay1.setBackground(new java.awt.Color(255, 255, 255));
        jLDay1.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLDay1.setForeground(new java.awt.Color(35, 36, 37));
        jLDay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLDay1.setText("Mayo 2022");
        jLDay1.setToolTipText("");
        jPanel3.add(jLDay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 400, 30));

        roundedPanel3.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel3.setLayout(new java.awt.BorderLayout());

        nextMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/nextDay.png"))); // NOI18N
        nextMonth.setBorderPainted(false);
        nextMonth.setContentAreaFilled(false);
        nextMonth.setFocusPainted(false);
        nextMonth.setIgnoreRepaint(true);
        roundedPanel3.add(nextMonth, java.awt.BorderLayout.CENTER);

        jPanel3.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 30, 30));

        roundedPanel4.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel4.setLayout(new java.awt.BorderLayout());

        previousMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/previousDay.png"))); // NOI18N
        previousMonth.setBorderPainted(false);
        previousMonth.setContentAreaFilled(false);
        previousMonth.setFocusPainted(false);
        previousMonth.setIgnoreRepaint(true);
        roundedPanel4.add(previousMonth, java.awt.BorderLayout.CENTER);

        jPanel3.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 520, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLPatient1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N
        jLPatient1.setForeground(new java.awt.Color(35, 36, 37));
        jLPatient1.setText("Citas de hoy:");
        jLPatient1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLPatient1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel5.add(jLPatient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        roundedPanel34.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel40.setBackground(new java.awt.Color(253, 39, 107));
        roundedPanel40.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Quirurgicas");
        roundedPanel40.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        roundedPanel34.add(roundedPanel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 101, 30));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(35, 36, 37));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("#");
        roundedPanel34.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 61, 60));

        jPanel5.add(roundedPanel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 40, 101, 130));

        roundedPanel35.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel38.setBackground(new java.awt.Color(11, 134, 81));
        roundedPanel38.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nutricionales");
        roundedPanel38.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        roundedPanel35.add(roundedPanel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 101, 30));

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(35, 36, 37));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("#");
        roundedPanel35.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 61, 60));

        jPanel5.add(roundedPanel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 101, 130));

        roundedPanel36.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel55.setBackground(new java.awt.Color(37, 119, 241));
        roundedPanel55.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Confirmadas");
        roundedPanel55.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        roundedPanel36.add(roundedPanel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 101, 30));

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(35, 36, 37));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("#/#");
        roundedPanel36.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 61, 60));

        jPanel5.add(roundedPanel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 40, 101, 130));

        roundedPanel37.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel37.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel39.setBackground(new java.awt.Color(254, 182, 0));
        roundedPanel39.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Esteticas");
        roundedPanel39.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        roundedPanel37.add(roundedPanel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 101, 30));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(35, 36, 37));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("#");
        roundedPanel37.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 61, 60));

        jPanel5.add(roundedPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(146, 40, 101, 130));

        add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 520, 185));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        roundedPanel5.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(35, 36, 37));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Lun");
        roundedPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 40, 40));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(35, 36, 37));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Mar");
        roundedPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 40, 40));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(35, 36, 37));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Mie");
        roundedPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 40, 40));

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(35, 36, 37));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Jue");
        roundedPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 40, 40));

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(35, 36, 37));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vie");
        roundedPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 40, 40));

        jLabel27.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(35, 36, 37));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Sab");
        roundedPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 40, 40));

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(35, 36, 37));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Dom");
        roundedPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 40));

        jPanel4.add(roundedPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 440, 40));

        roundedPanel6.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel6.setLayout(new java.awt.BorderLayout());

        jLabel23.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(35, 36, 37));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("7");
        roundedPanel6.add(jLabel23, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 40, 40));

        roundedPanel7.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel7.setLayout(new java.awt.BorderLayout());

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(35, 36, 37));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("1");
        roundedPanel7.add(jLabel17, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 40, 40));

        roundedPanel8.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel8.setLayout(new java.awt.BorderLayout());

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(35, 36, 37));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("2");
        roundedPanel8.add(jLabel18, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 40, 40));

        roundedPanel9.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel9.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(35, 36, 37));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("3");
        roundedPanel9.add(jLabel19, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 40, 40));

        roundedPanel10.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel10.setLayout(new java.awt.BorderLayout());

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(35, 36, 37));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("4");
        roundedPanel10.add(jLabel20, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 40, 40));

        roundedPanel11.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel11.setLayout(new java.awt.BorderLayout());

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(35, 36, 37));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("5");
        roundedPanel11.add(jLabel21, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 40, 40));

        roundedPanel12.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel12.setLayout(new java.awt.BorderLayout());

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(35, 36, 37));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("6");
        roundedPanel12.add(jLabel22, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 40, 40));

        roundedPanel13.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel13.setLayout(new java.awt.BorderLayout());

        jLabel52.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(35, 36, 37));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("14");
        roundedPanel13.add(jLabel52, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 40, 40));

        roundedPanel14.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel14.setLayout(new java.awt.BorderLayout());

        jLabel53.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(35, 36, 37));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("8");
        roundedPanel14.add(jLabel53, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 40, 40));

        roundedPanel15.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel15.setLayout(new java.awt.BorderLayout());

        jLabel54.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(35, 36, 37));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("9");
        roundedPanel15.add(jLabel54, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 40, 40));

        roundedPanel16.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel16.setLayout(new java.awt.BorderLayout());

        jLabel55.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(35, 36, 37));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("10");
        roundedPanel16.add(jLabel55, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 40, 40));

        roundedPanel17.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel17.setLayout(new java.awt.BorderLayout());

        jLabel56.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(35, 36, 37));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("11");
        roundedPanel17.add(jLabel56, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 40, 40));

        roundedPanel18.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel18.setLayout(new java.awt.BorderLayout());

        jLabel57.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(35, 36, 37));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("12");
        roundedPanel18.add(jLabel57, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 40, 40));

        roundedPanel19.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel19.setLayout(new java.awt.BorderLayout());

        jLabel58.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(35, 36, 37));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("13");
        roundedPanel19.add(jLabel58, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 40, 40));

        roundedPanel20.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel20.setLayout(new java.awt.BorderLayout());

        jLabel59.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(35, 36, 37));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("17");
        roundedPanel20.add(jLabel59, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 40, 40));

        roundedPanel21.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel21.setLayout(new java.awt.BorderLayout());

        jLabel60.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(35, 36, 37));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("16");
        roundedPanel21.add(jLabel60, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 40, 40));

        roundedPanel22.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel22.setLayout(new java.awt.BorderLayout());

        jLabel61.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(35, 36, 37));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("18");
        roundedPanel22.add(jLabel61, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 40, 40));

        roundedPanel23.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel23.setLayout(new java.awt.BorderLayout());

        jLabel62.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(35, 36, 37));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("19");
        roundedPanel23.add(jLabel62, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 40, 40));

        roundedPanel24.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel24.setLayout(new java.awt.BorderLayout());

        jLabel63.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(35, 36, 37));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("21");
        roundedPanel24.add(jLabel63, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 40, 40));

        roundedPanel25.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel25.setLayout(new java.awt.BorderLayout());

        jLabel64.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(35, 36, 37));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("20");
        roundedPanel25.add(jLabel64, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 40, 40));

        roundedPanel26.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel26.setLayout(new java.awt.BorderLayout());

        jLabel65.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(35, 36, 37));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("15");
        roundedPanel26.add(jLabel65, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 40, 40));

        roundedPanel27.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel27.setLayout(new java.awt.BorderLayout());

        jLabel66.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(35, 36, 37));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("24");
        roundedPanel27.add(jLabel66, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 40, 40));

        roundedPanel28.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel28.setLayout(new java.awt.BorderLayout());

        jLabel67.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(35, 36, 37));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("23");
        roundedPanel28.add(jLabel67, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 40, 40));

        roundedPanel29.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel29.setLayout(new java.awt.BorderLayout());

        jLabel68.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(35, 36, 37));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("25");
        roundedPanel29.add(jLabel68, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 40, 40));

        roundedPanel30.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel30.setLayout(new java.awt.BorderLayout());

        jLabel69.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(35, 36, 37));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("26");
        roundedPanel30.add(jLabel69, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 40, 40));

        roundedPanel31.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel31.setLayout(new java.awt.BorderLayout());

        jLabel70.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(35, 36, 37));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("28");
        roundedPanel31.add(jLabel70, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 40, 40));

        roundedPanel32.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel32.setLayout(new java.awt.BorderLayout());

        jLabel71.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(35, 36, 37));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("27");
        roundedPanel32.add(jLabel71, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 40, 40));

        roundedPanel33.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel33.setLayout(new java.awt.BorderLayout());

        jLabel72.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(35, 36, 37));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("22");
        roundedPanel33.add(jLabel72, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 40, 40));

        roundedPanel41.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel41.setLayout(new java.awt.BorderLayout());

        jLabel80.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(35, 36, 37));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("31");
        roundedPanel41.add(jLabel80, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 40, 40));

        roundedPanel42.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel42.setLayout(new java.awt.BorderLayout());

        jLabel81.setForeground(new java.awt.Color(35, 36, 37));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("1");
        roundedPanel42.add(jLabel81, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, 40, 40));

        roundedPanel43.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel43.setLayout(new java.awt.BorderLayout());

        jLabel82.setForeground(new java.awt.Color(35, 36, 37));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("3");
        roundedPanel43.add(jLabel82, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 40, 40));

        roundedPanel44.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel44.setLayout(new java.awt.BorderLayout());

        jLabel83.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(35, 36, 37));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("30");
        roundedPanel44.add(jLabel83, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 40, 40));

        roundedPanel45.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel45.setLayout(new java.awt.BorderLayout());

        jLabel84.setForeground(new java.awt.Color(35, 36, 37));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("4");
        roundedPanel45.add(jLabel84, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 40, 40));

        roundedPanel46.setBackground(new java.awt.Color(244, 243, 243));
        roundedPanel46.setLayout(new java.awt.BorderLayout());

        jLabel85.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(35, 36, 37));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("29");
        roundedPanel46.add(jLabel85, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 40, 40));

        roundedPanel47.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel47.setLayout(new java.awt.BorderLayout());

        jLabel86.setForeground(new java.awt.Color(35, 36, 37));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("2");
        roundedPanel47.add(jLabel86, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, 40, 40));

        roundedPanel48.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel48.setLayout(new java.awt.BorderLayout());

        jLabel87.setForeground(new java.awt.Color(35, 36, 37));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("7");
        roundedPanel48.add(jLabel87, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 40, 40));

        roundedPanel49.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel49.setLayout(new java.awt.BorderLayout());

        jLabel88.setForeground(new java.awt.Color(35, 36, 37));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("8");
        roundedPanel49.add(jLabel88, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 40, 40));

        roundedPanel50.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel50.setLayout(new java.awt.BorderLayout());

        jLabel89.setForeground(new java.awt.Color(35, 36, 37));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("10");
        roundedPanel50.add(jLabel89, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 40, 40));

        roundedPanel51.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel51.setLayout(new java.awt.BorderLayout());

        jLabel90.setForeground(new java.awt.Color(35, 36, 37));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("6");
        roundedPanel51.add(jLabel90, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 40, 40));

        roundedPanel52.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel52.setLayout(new java.awt.BorderLayout());

        jLabel91.setForeground(new java.awt.Color(35, 36, 37));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("11");
        roundedPanel52.add(jLabel91, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 40, 40));

        roundedPanel53.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel53.setLayout(new java.awt.BorderLayout());

        jLabel92.setForeground(new java.awt.Color(35, 36, 37));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("5");
        roundedPanel53.add(jLabel92, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 40, 40));

        roundedPanel54.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel54.setLayout(new java.awt.BorderLayout());

        jLabel93.setForeground(new java.awt.Color(35, 36, 37));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("9");
        roundedPanel54.add(jLabel93, java.awt.BorderLayout.CENTER);

        jPanel4.add(roundedPanel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 40, 40));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 520, 390));
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelnMouseMoved
        containerBtnCancel.setBackground(new Color(35, 111, 229));
    }//GEN-LAST:event_btnCancelnMouseMoved

    private void btnCancelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseExited
        containerBtnCancel.setBackground(new Color(37, 119, 241));
    }//GEN-LAST:event_btnCancelMouseExited

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        Appointment app = (Appointment) this.cbScheduleDate.getSelectedItem();
        if (app != null) {
            if (AppointmentControl.getInstance().deleteAppointment(app)) {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.CORRECT, "Cancelar cita", "Cita cancelada correctamente");
                cleanFields();
                renderAppointments(currentTime);
                renderInfoAppointmentsToday();
            } else {
                App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Cancelar cita", "Imposible cancelar cita");
            }
        } else {
            App.GetSingleton().newMessage(App.GetSingleton().getMainFrame(), MessageType.ERROR, "Cancelar cita", "Imposible cancelar cita");
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cbPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPatientActionPerformed
        this.cbScheduleDate.removeAllItems();
        loadAppointments();
        this.loadAppointments();
        this.fillService();
        this.fillHours();
    }//GEN-LAST:event_cbPatientActionPerformed

    private void cbScheduleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbScheduleDateActionPerformed
        if (cbScheduleDate.getSelectedItem() != null) {
            this.fillService();
            this.fillHours();
        }
    }//GEN-LAST:event_cbScheduleDateActionPerformed

    private void nextDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextDayActionPerformed
        currentTime.setTime(currentTime.getTime() + 86400000);
        renderAppointments(currentTime);
    }//GEN-LAST:event_nextDayActionPerformed

    private void previousDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousDayActionPerformed
        currentTime.setTime(currentTime.getTime() - 86400000);
        renderAppointments(currentTime);
    }//GEN-LAST:event_previousDayActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AppointmentsScrollPane;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox<String> cbHourBeginning;
    private javax.swing.JComboBox<String> cbHourEnding;
    private javax.swing.JComboBox<String> cbMinuteBeginning;
    private javax.swing.JComboBox<String> cbMinuteEnding;
    private javax.swing.JComboBox<Patient> cbPatient;
    private javax.swing.JComboBox<Appointment> cbScheduleDate;
    private javax.swing.JComboBox<String> cbService;
    private Components.RoundedPanel containerBeginning;
    private Components.RoundedPanel containerBeginning1;
    private Components.RoundedPanel containerBtnCancel;
    private Components.RoundedPanel containerEnding;
    private Components.RoundedPanel containerEnding1;
    private Components.RoundedPanel containerPatient;
    private Components.RoundedPanel containerReason;
    private Components.RoundedPanel containerScheduleDate;
    private Components.RoundedPanel containerService;
    private javax.swing.JLabel jLBeginning;
    private javax.swing.JLabel jLDate;
    private javax.swing.JLabel jLDay;
    private javax.swing.JLabel jLDay1;
    private javax.swing.JLabel jLEnding;
    private javax.swing.JLabel jLNotes;
    private javax.swing.JLabel jLPatient;
    private javax.swing.JLabel jLPatient1;
    private javax.swing.JLabel jLService;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton nextDay;
    private javax.swing.JButton nextMonth;
    private javax.swing.JButton previousDay;
    private javax.swing.JButton previousMonth;
    private Components.RoundedPanel roundedPanel1;
    private Components.RoundedPanel roundedPanel10;
    private Components.RoundedPanel roundedPanel11;
    private Components.RoundedPanel roundedPanel12;
    private Components.RoundedPanel roundedPanel13;
    private Components.RoundedPanel roundedPanel14;
    private Components.RoundedPanel roundedPanel15;
    private Components.RoundedPanel roundedPanel16;
    private Components.RoundedPanel roundedPanel17;
    private Components.RoundedPanel roundedPanel18;
    private Components.RoundedPanel roundedPanel19;
    private Components.RoundedPanel roundedPanel2;
    private Components.RoundedPanel roundedPanel20;
    private Components.RoundedPanel roundedPanel21;
    private Components.RoundedPanel roundedPanel22;
    private Components.RoundedPanel roundedPanel23;
    private Components.RoundedPanel roundedPanel24;
    private Components.RoundedPanel roundedPanel25;
    private Components.RoundedPanel roundedPanel26;
    private Components.RoundedPanel roundedPanel27;
    private Components.RoundedPanel roundedPanel28;
    private Components.RoundedPanel roundedPanel29;
    private Components.RoundedPanel roundedPanel3;
    private Components.RoundedPanel roundedPanel30;
    private Components.RoundedPanel roundedPanel31;
    private Components.RoundedPanel roundedPanel32;
    private Components.RoundedPanel roundedPanel33;
    private Components.RoundedPanel roundedPanel34;
    private Components.RoundedPanel roundedPanel35;
    private Components.RoundedPanel roundedPanel36;
    private Components.RoundedPanel roundedPanel37;
    private Components.RoundedPanel roundedPanel38;
    private Components.RoundedPanel roundedPanel39;
    private Components.RoundedPanel roundedPanel4;
    private Components.RoundedPanel roundedPanel40;
    private Components.RoundedPanel roundedPanel41;
    private Components.RoundedPanel roundedPanel42;
    private Components.RoundedPanel roundedPanel43;
    private Components.RoundedPanel roundedPanel44;
    private Components.RoundedPanel roundedPanel45;
    private Components.RoundedPanel roundedPanel46;
    private Components.RoundedPanel roundedPanel47;
    private Components.RoundedPanel roundedPanel48;
    private Components.RoundedPanel roundedPanel49;
    private Components.RoundedPanel roundedPanel5;
    private Components.RoundedPanel roundedPanel50;
    private Components.RoundedPanel roundedPanel51;
    private Components.RoundedPanel roundedPanel52;
    private Components.RoundedPanel roundedPanel53;
    private Components.RoundedPanel roundedPanel54;
    private Components.RoundedPanel roundedPanel55;
    private Components.RoundedPanel roundedPanel6;
    private Components.RoundedPanel roundedPanel7;
    private Components.RoundedPanel roundedPanel8;
    private Components.RoundedPanel roundedPanel9;
    private javax.swing.JPanel scrollAppointments;
    private javax.swing.JTextArea tAReason;
    private javax.swing.JPanel title;
    // End of variables declaration//GEN-END:variables
}
