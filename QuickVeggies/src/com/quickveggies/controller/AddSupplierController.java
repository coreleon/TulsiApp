package com.quickveggies.controller;

import com.quickveggies.GeneralMethods;
import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.Supplier;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField company;

    @FXML
    private TextField proprietor;

    @FXML
    private TextField mobile;

    @FXML
    private TextField mobile2;

    @FXML
    private TextField email;

    @FXML
    private TextField village;

    @FXML
    private TextField po;

    @FXML
    private TextField tehsil;

    @FXML
    private TextField ac;

    @FXML
    private TextField bank;

    @FXML
    private TextField ifsc;

    @FXML
    private Button save;
    
    @FXML
    private ImageView imvPhoto;
    
    @FXML
    private Button btnUploadPhoto;
    
    @FXML
    private Pane imagePanel;
    
    private File imgFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	
    	btnUploadPhoto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				uploadImage(event);
			}
		});
    	
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	try{
            		Long.valueOf(mobile.getText());
            	}catch(NumberFormatException e){
            		GeneralMethods.errorMsg("Please enter appropriate mobile number!");
            		return;
            	}
            	if (mobile2.getText() != null && !mobile2.getText().trim().isEmpty()) {
            		try {
                		Long.valueOf(mobile2.getText());
                		
                	}catch(NumberFormatException e1){ 
                		GeneralMethods.errorMsg("Please enter appropriate mobile 2 number, or leave it empty!");
                		return;
                	}
            	}
            	if(!email.getText().matches(GeneralMethods.emailPattern)){
            		GeneralMethods.errorMsg("Email provided is not property formatted!");
            		return;
            	}
            	
                Supplier supplier = new Supplier(0,"",firstName.getText(),lastName.getText(),
                        company.getText(),proprietor.getText(),mobile.getText(),mobile2.getText(),
                        email.getText(),village.getText(),po.getText(),tehsil.getText(), ac.getText(), bank.getText(), ifsc.getText());
                if (imgFile  != null) {
                	try {
						supplier.setImageStream(new BufferedInputStream(new FileInputStream(imgFile)));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
                }
                try {
                    DatabaseClient.getInstance().saveSupplier(supplier);
                    save.getScene().getWindow().hide();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private void uploadImage(final Event event) {
    	if (!(event.getSource() instanceof Node)) 
    		throw new IllegalArgumentException("The source of the event should be instance of java FX node or any of its' subclass");
 		Window mainStage =  ((Node) event.getSource()).getScene().getWindow();
 		FileChooser fileChooser = new FileChooser();
 		 fileChooser.setTitle("Open Resource File");
 		 fileChooser.getExtensionFilters().addAll( new ExtensionFilter
 		         ("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
 		 File selectedFile = fileChooser.showOpenDialog(mainStage);
 		 if (selectedFile != null) {
 		    try (InputStream is = new FileInputStream(selectedFile)){
 		    	imgFile = selectedFile;
 		    	imvPhoto.setImage(new Image(is));
 		    	imagePanel.setStyle("-fx-border-color: none;");
 			} catch (FileNotFoundException e) {
 				GeneralMethods.errorMsg("Cannot find specified file:" + selectedFile.getName());
 			} catch (IOException e1) {
 				e1.printStackTrace();
 			}
 		 }
     	
     }
}
