/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javafx.scene.control.Alert;

/**
 * Project: QuickVeggies File: Tester.java CreatedOn: Date: Oct 28, 2017 Time:
 * 11:23:58 AM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class Tester
{
    public static <T> void _(String title, T value)
    {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
                            a.setTitle("checkIfUserNameOrEmailExists()");
                            a.setHeaderText(null);
                            a.setContentText(value.toString());
                            a.showAndWait();
    }
}
