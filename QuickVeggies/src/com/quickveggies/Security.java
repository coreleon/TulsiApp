/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies;

/**
 * Project: QuickVeggies File: Security.java CreatedOn: Date: Oct 25, 2017 Time:
 * 11:34:06 AM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class Security
{
    public static final boolean checkPasswordFormatPolicy(String password, String retypedPassword)
    {
        return password.equals(retypedPassword)&&!password.equals("");
    }
}
