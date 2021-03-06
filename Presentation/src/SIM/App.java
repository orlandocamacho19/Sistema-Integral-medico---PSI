/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package SIM;

import View.*;
import control.PatientControl;
import java.awt.Component;

/**
 *
 * @author orlandocamacho
 */
public class App {

    private static App AppSingleton;
    private MainFrame mainFrame;
    private AppState state;
    
    public static App GetSingleton(){
        return AppSingleton;
    }
    
    public App() {
        App.AppSingleton = this;
        state = AppState.INITIALIZING;
        
        mainFrame = new MainFrame();
        
        mainFrame.setVisible(true);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
    public AppState getState(){
        return state;
    }

    public void setState(AppState state) {
        this.state = state;
        System.gc();
        switch (state) {
            case MANAGEAPPOINTMENTS:
                mainFrame.setContent(new ManageAppointments());
                break;
            case SCHEDULEAPPOINTMENTS:
                mainFrame.setContent(new ScheduleAppointment());
                break;
            case RESCHEDULEAPPOINTMENTS:
                mainFrame.setContent(new RescheduleAppointment());
                break;
            case CANCELAPPOINTMENTS:
                mainFrame.setContent(new CancelAppointment());
                break;
            case REGISTERPATIENT:
                mainFrame.setContent(new RegisterPatient());
                break;
            case EDITPATIENT:
                mainFrame.setContent(new EditPatient());
                break;
            case REGISTERMEDICINE:
                mainFrame.setContent(new RegisterMedicine());
                break;
            case EDITMEDICINE:
                mainFrame.setContent(new EditMedicine());
                break;
        }
    }
    
    /**
     * 
     * @param C
     * @param type
     * @param text 
     */
    public void newMessage(Component C, MessageType type, String action, String text){
        new Message(C, type, action, text);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new App();
    }
    
    
    
    
    
}
