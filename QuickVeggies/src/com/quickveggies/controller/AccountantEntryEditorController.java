/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.controller;

import com.quickveggies.Main;
import com.quickveggies.entities.Accountant;
import com.quickveggies.entities.Company;
import com.quickveggies.misc.CryptDataHandler;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Project: QuickVeggies File: ExistingAccountantController.java CreatedOn:
 Date: Oct 24, 2017 Time: 12:21:48 PM

 Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class AccountantEntryEditorController implements Initializable
{
    private static AccountantEntryEditorController inst = new AccountantEntryEditorController();
    private static Stage accEditorStage = new Stage();
    
    public static AccountantEntryEditorController getInstance()
    {
        return inst;
    }
    
    static 
    {
        try
        {
            accEditorStage.setOnCloseRequest(new EventHandler<WindowEvent>()
            {
                public void handle(WindowEvent event)
                {
                    Main.getStage().getScene().getRoot().setEffect(null);
                }
            });
            FXMLLoader loader = new FXMLLoader(AccountantEntryEditorController.class.getClass().getResource("/accounts/AccountantEntryEditor.fxml"));
            loader.setController(inst);
            Scene scene = new Scene((Parent)loader.load());
            scene.setOnKeyPressed(new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent event)
                {
                    if(event.getCode()==KeyCode.ESCAPE)
                    {
                        Main.getStage().getScene().getRoot().setEffect(null);
                        accEditorStage.close();
                    }
                }
            });
            accEditorStage.setOnCloseRequest((WindowEvent e) -> accEditorStage.hide());
            accEditorStage.setOnShowing((WindowEvent e) -> inst.init(inst.accEntryCtrl, true));
            accEditorStage.setScene(scene);
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
    }
    
    public ExistingAccountantController getEditedAccountantEntry()
    {
        return this.accEntryCtrl;
    }
    
    @FXML
    private TextField name;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField rtpass;
    @FXML
    private Label msg;
    @FXML
    private TextField cname;
    @FXML
    private TextField sdate;
    @FXML
    private Button addAccountant;
    @FXML
    private Button cancel;
    
    private ExistingAccountantController accEntryCtrl;
    private boolean addState = true;

    public AccountantEntryEditorController()
    {
    }
    
    public void init(ExistingAccountantController accEntryCtrl, boolean state)
    {
        if(state)
        {
            if(accEntryCtrl.getAccountant() != null)
            {
                this.addState = false;
                accEditorStage.setTitle("Edit Accountant Info - " + accEntryCtrl.getAccountant().getName());
                accEditorStage.centerOnScreen();
                accEditorStage.initModality(Modality.APPLICATION_MODAL);
                this.name.setText(accEntryCtrl.getAccountant().getName());
                this.pass.setText(CryptDataHandler.getInstance().decrypt(accEntryCtrl.getAccountant().getPassword()));
                this.rtpass.setText(CryptDataHandler.getInstance().decrypt(accEntryCtrl.getAccountant().getPassword()));
                this.cname.setText(Company.getCurrentCompany().getName());
                this.sdate.setText(accEntryCtrl.getAccountant().getSinceDate());
                addAccountant.setText("Update");
                addAccountant.setDisable(true);
                this.name.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        addAccountant.setDisable(!nv && !name.getText().equals(accEntryCtrl.getAccountant().getName()));
                    }
                });
                this.pass.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        addAccountant.setDisable(!nv && !pass.getText().equals(CryptDataHandler.getInstance().decrypt(accEntryCtrl.getAccountant().getPassword())));
                    }
                });
                this.rtpass.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        if(!nv && !rtpass.getText().equals(pass.getText()))
                        {
                            msg.setText("Passwords don't match!");
                            rtpass.requestFocus();
                            rtpass.selectAll();
                        }
                        else
                            msg.setText("");
                    }
                });
            }
            else
            {
                this.addState = true;
                accEditorStage.setTitle("New Accountant Info");
                accEditorStage.centerOnScreen();
                accEditorStage.initModality(Modality.APPLICATION_MODAL);
                this.name.setText("");
                this.pass.setText("");
                this.rtpass.setText("");
                this.cname.setText("");
                this.sdate.setText("");
                addAccountant.setText("Add");
                addAccountant.setDisable(true);
                ChangeListener<Boolean> cl = new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        validate();
                    }
                };

                this.name.focusedProperty().addListener(cl);
                this.pass.focusedProperty().addListener(cl);
                this.rtpass.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        if(!nv && !rtpass.getText().equals(pass.getText()))
                        {
                            msg.setText("Passwords don't match!");
                            rtpass.requestFocus();
                            rtpass.selectAll();
                        }
                        else
                            msg.setText("");
                    }
                });
                this.rtpass.focusedProperty().addListener(new ChangeListener<Boolean>()
                {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> o, Boolean ov, Boolean nv)
                    {
                        if(!nv && rtpass.getText().equals(""))
                        {
                            msg.setText("Retype password!");
                            rtpass.requestFocus();
                            rtpass.selectAll();
                        }
                        else
                            msg.setText("");

                        validate();
                    }
                });
            }
        }
        else
        {
            this.accEntryCtrl = accEntryCtrl;
            accEditorStage.show();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        init(this.accEntryCtrl, true);
    }
    
    private void validate()
    {
        boolean disabled;
        addAccountant.setDisable(disabled=(name.getText().equals("") || pass.getText().equals("") || rtpass.getText().equals("")));
        
        if(!disabled && this.addState && this.sdate.getText().equals(""))
        {
            this.cname.setText(Company.getCurrentCompany().getName());
            this.sdate.setText(LocalDate.now().toString());
        }
    }
    
    @FXML
    private void updateAccountants()
    {
        try
        {
            if(this.addState)
            {
                Accountant ac = new Accountant(
                        name.getText(), 
                        CryptDataHandler.getInstance().encrypt(pass.getText()), 
                        Company.getCurrentCompany().getId(), 
                        sdate.getText());
                AccountantManagementPanelController.getInstance().setLock(ac);
                Accountant.addAccountant(ac);
            }
            else
            {
                this.accEntryCtrl.getAccountant().setName(name.getText());
                this.accEntryCtrl.getAccountant().setPassword(CryptDataHandler.getInstance().encrypt(pass.getText()));
                Accountant.updateAccountant(this.accEntryCtrl.getAccountant());
                this.accEntryCtrl.refresh();
            }
            accEditorStage.hide();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
