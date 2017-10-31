package com.quickveggies;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

    private static final boolean DEBUG_MODE = false; //Todo: =false
    private static final int DASHBOARD_WIDTH = 792;
    private static final int DASHBOARD_HEIGHT = 660;
    private static File file = new File("output.txt");
    private static final String SMSTemplate = "Dear ${PartyName} your purchases for date ${Date} is valued ${TotalAmt} ."
            +"Pay within 3 days and avail a flat 1% cashback.";

    private QVCustomOutputStream qOs;
    private static Stage stage;

    public Main()
    {
        UserGlobalParameters.SET_SMS_TEMPLATE(SMSTemplate, false);
        try
        {
            qOs = new QVCustomOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            //TODO:uncomment below to move logging info to file instead of standard output
            // System.setOut(qOs);
            // System.setErr(qOs);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error creating file");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        stage = primaryStage;
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/program_icon.png")));stage.setResizable(true);
        String whatToLoad = "/login.fxml";
        if(DEBUG_MODE)
        {
            //Todo: debug mode only
            whatToLoad = "/dashboardz.fxml";
        }
        Parent root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage.setTitle("Accounting System Login");
        stage.setScene(new Scene(root, DASHBOARD_WIDTH, DASHBOARD_HEIGHT));
        stage.setMinWidth(stage.getScene().getWidth());
        stage.setMinHeight(stage.getScene().getHeight());
        stage.show();
    }

    public Parent replaceSceneContent(String fxml)
    {
        Parent page = null;
        try
        {
            page = FXMLLoader.load(getClass().getResource(fxml));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Scene scene;
        if(!fxml.contains("register")&&!fxml.contains("login"))
        {
            scene = new Scene(page, DASHBOARD_WIDTH, DASHBOARD_HEIGHT);
        }
        else
        {
            scene = new Scene(page);
        }
        scene.getStylesheets().add("css/style.css");
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setTitle("Quick Veggies");
        stage.show();
        stage.centerOnScreen();
        return page;
    }

    public static void closeCurrentStage(Stage stage)
    {
        stage.close();
    }

    public static Stage getStage()
    {
        return stage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
