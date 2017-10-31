/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.controller;

import com.quickveggies.Main;
import static com.quickveggies.controller.CompanyInfoViewController.session;
import com.quickveggies.entities.Accountant;
import com.quickveggies.entities.Company;
import com.quickveggies.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
public class ExistingAccountantController implements Initializable
{
    @FXML
    private HBox entry;
    @FXML
    private Label username;
    @FXML
    private Label sinceDate;
    @FXML
    private Button revokeAccess;
    
    private Accountant accountant;
    
    public void init(Accountant accountant)
    {
        this.accountant = accountant;
    }
    
    public Accountant getAccountant()
    {
        return this.accountant;
    }
    
    public void refresh()
    {
        this.username.setText(this.accountant.getName());
        this.sinceDate.setText("Since: " + this.accountant.getSinceDate());
        this.revokeAccess.setDisable(!Company.getCurrentCompany().ownedBy(User.getCurrentUser()));
    }
    
    public HBox getEntry()
    {
        return this.entry;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        refresh();
    }
    
    @FXML
    private void removeAccountant()
    {
        try
        {
            AccountantManagementPanelController.getInstance().setLock(this.accountant);
            Accountant.removeAccountant(this.accountant);
//            this.entry.getParent().getChildrenUnmodifiable().removeAll(this.entry);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    
    @FXML
    private void editAccountant()
    {
        AccountantEntryEditorController.getInstance().init(this, false);
    }
}
