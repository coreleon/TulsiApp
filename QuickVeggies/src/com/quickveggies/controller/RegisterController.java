package com.quickveggies.controller;

import com.Tester;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.quickveggies.CosmeticStyles;
import com.quickveggies.GeneralMethods;
import com.quickveggies.Main;
import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.User;
import com.quickveggies.misc.CryptDataHandler;
import java.util.NoSuchElementException;

public class RegisterController implements Initializable
{

    @FXML
    private Button reg;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass1;

    @FXML
    private Label invalid;

    @FXML
    private TextField email;

    @FXML
    private Button back;

    public void initialize(URL location, ResourceBundle resources)
    {
        CosmeticStyles.addHoverEffect(reg, back);
        pass.setId("txtinput");
        pass1.setId("txtinput");
        email.setId("txtinput");
        username.setId("txtinput");
        reg.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                try
                {
                    int userExistence = checkIfUserNameOrEmailExists();
//Tester._("checkIfUserNameOrEmailExists()", userExistence);
                    if(userExistence != 0)
                    {
                        if(userExistence == 1)
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Registration error!");
                            alert.setContentText("A user with the name \"" + username.getText() + "\" already exists!");
                            alert.setHeaderText(null);
                            alert.showAndWait();
                            username.setText("");
                            username.requestFocus();
                            return;
                        }
                        if(userExistence == 2)
                        {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Registration error!");
                            alert.setContentText("A user with the email \"" + email.getText() + "\" already exists!");
                            alert.setHeaderText(null);
                            alert.showAndWait();
                            email.setText("");
                            email.requestFocus();
                            return;
                        }
                    }
                    
                    if(pass.getText().contains(" ")||pass.getText().contains(";")
                            ||pass.getText().contains("'")
                            ||pass.getText().contains("\""))
                    {
                        System.out.println("bad pass");
                        pass.setText("");
                        pass1.setText("");
                        invalid.setVisible(true);
                        pass.requestFocus();
                        return;
                    }
                    if(pass.getText().equals(pass1.getText())&&!pass.getText().equals(""))
                    {
                        System.out.println("okay, pass is good");
                        if(email.getText().toUpperCase().matches(GeneralMethods.emailPattern))
                        {
                            System.out.println("mail matches pattern!");
                            String name = username.getText();
                            String password = CryptDataHandler.getInstance().encrypt(pass.getText());
                            String mail = email.getText();
                            User user = new User(name, password, mail, -1, User.Role.USER.name()); //-1 means that this user works in no company as an Accountant.
                            DatabaseClient.getInstance().saveUser(user);
                            User.registerCurrentUser(user);  // Now the system starts to roll...
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("User created!");
                            alert.setHeaderText(null);
                            alert.setContentText("User "+name+" successfully created.");
                            alert.showAndWait();
                            new Main().replaceSceneContent("/login.fxml");
                        }
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
Tester._("checkIfUserNameOrEmailExists()", e);
                }
            }
        });
        back.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                new Main().replaceSceneContent("/login.fxml");
            }
        });
    }

    private final int checkIfUserNameOrEmailExists() throws SQLException
    {
        try
        {
            if(DatabaseClient.getInstance().getUserByName(username.getText())!=null)
            {
                return 1;
            }
            if(DatabaseClient.getInstance().getUserByEmail(email.getText())!=null)
            {
                return 2;
            }
        }
        catch(NoSuchElementException exc)
        {
            return 0;
        }
        return 0;
    }
}
