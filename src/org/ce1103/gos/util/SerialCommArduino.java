package org.ce1103.gos.util;

import static jssc.SerialPort.MASK_RXCHAR;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

import org.ce1103.gos.entities.Player;
import org.ce1103.gos.view.GameViewManager;

public class SerialCommArduino {
	
	static SerialPort arduinoPort = null;
	public static ObservableList<String> portList;
	
	public static boolean shootButton = false;
	public static boolean leftButton = false;
	public static boolean rightButton= false;
	public static boolean downButton= false;
	public static boolean upButton= false;

	
    public static void detectPort(){
        
        portList = FXCollections.observableArrayList();
 
        String[] serialPortNames = SerialPortList.getPortNames();
        for(String name: serialPortNames){
            System.out.println(name);
            portList.add(name);
        }
    }
    
public static boolean connectArduino(String port){
        
        System.out.println("connectArduino");
        
        boolean success = false;
        SerialPort serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK_RXCHAR);  
            serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        
                        String st = serialPort.readString(serialPortEvent.getEventValue()).toString();
                        
                    	while(true) {
                    		
                    		if(st.contains("s")) {
                    			shootButton = true;
                           	
                           		
                           		
                           	}else if(st.contains("u")) {
                           		upButton = true;
                           		Player.upPressed = true;
                           		
                           		
                           	}else if(st.contains("d")) {
                           		downButton = true;
                           		Player.downPressed = true;
                           		
                           		
                         	}else if(st.contains("r")) {
                         		rightButton = true;
                         		Player.rightPressed = true;
                           		
                         		
                         	}else if(st.contains("l")) {
                         		leftButton = true;
                         		Player.leftPressed = true;
                         		
                         	}
                         	else if(st.contains("n")) {
                         		Player.downPressed = false;
                         		Player.leftPressed = false;
                         		Player.rightPressed = false;
                         		Player.upPressed = false;
                         		
                         	}
                    		break;
                    		
                    	}
                       
                        Platform.runLater(() -> {
     
                        });
                        
                    } catch (SerialPortException ex) {
                        Logger.getLogger(SerialCommArduino.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            
            arduinoPort = serialPort;
            success = true;
        } catch (SerialPortException ex) {
            Logger.getLogger(SerialCommArduino.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
        }

        return success;
    }
    

	public void disconnectArduino(){
        System.out.println("disconnectArduino()");
        if(arduinoPort != null){
            try {
                arduinoPort.removeEventListener();
                
                if(arduinoPort.isOpened()){
                    arduinoPort.closePort();
                }
                
                arduinoPort = null;
            } catch (SerialPortException ex) {
                Logger.getLogger(SerialCommArduino.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
}