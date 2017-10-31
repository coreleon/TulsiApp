package com.quickveggies.controller;

import com.quickveggies.entities.Accountant;
import com.quickveggies.entities.Company;
import com.quickveggies.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccountantManagementPanelController implements Initializable
{
    @FXML
    private VBox usermanPane;
    @FXML
    private HBox newAccountantControlPane;
    @FXML
    private ImageView newAccountant;
    @FXML
    private VBox accountantsList;
    
    private static AccountantManagementPanelController instance;
    
    public static AccountantManagementPanelController getInstance()
    {
        return instance;
    }
    
    private AccountantManagementPanelController()
    {
        instance = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            newAccountantControlPane.setDisable(!Company.getCurrentCompany().ownedBy(User.getCurrentUser()));
            Accountant[] al = Company.getCurrentCompany().getAccountantsList();
            this.map = new HashMap<String,ExistingAccountantController>(al.length);
            ExistingAccountantController c;
            
            for(Accountant ac : al)
            {
                FXMLLoader fl = new FXMLLoader(getClass().getResource("/accounts/ExistingAccountant.fxml"));
                
                (c=(ExistingAccountantController)fl.getController()).init(ac);
                accountantsList.getChildren().add((Node)fl.load());
                map.put(ac.getName(), c);
            }
        }
        catch(IOException e1)
        {
            e1.printStackTrace();
        }
    }
    
    private volatile Accountant lock;
    private HashMap<String,ExistingAccountantController> map;
    
    public void setLock(Accountant ac)
    {
        if(lock == null && Company.getCurrentCompany().ownedBy(User.getCurrentUser()))
            this.lock = ac;
    }
    
    @FXML
    public void updateList(Accountant accountant, boolean addAction) throws IOException
    {
        if(lock.equals(accountant))
        {
            if(addAction)
            {
                ExistingAccountantController c;
                FXMLLoader fl = new FXMLLoader(getClass().getResource("/accounts/ExistingAccountant.fxml"));
                (c=(ExistingAccountantController)fl.getController()).init(accountant);
                accountantsList.getChildren().add((Node)fl.load());
                map.put(accountant.getName(), c);
            }
            else
            {
                accountantsList.getChildren().removeAll(map.get(accountant.getName()).getEntry());
                map.remove(accountant.getName());
            }
            
            lock = null;
        }
    }
}
