package org.ce1103.gos.guicontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import org.ce1103.gos.gui.Main;
import org.ce1103.gos.gui.ScreensController;
import org.ce1103.gos.util.ControlledScreen;


public class Screen1Controller implements Initializable, ControlledScreen {

	ScreensController myController;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

	@Override
	public void setScreenParent(ScreensController screenPage) {
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    private void goToPrincipal(ActionEvent event){
    	myController.setScreen(Main.screen2ID);
    }

  
		
	
}
