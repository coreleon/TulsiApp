/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies;

import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.User;
import java.sql.ResultSet;

/**
 * Project: QuickVeggies File: SystemManager.java CreatedOn: Date: Oct 23, 2017
 * Time: 11:00:58 AM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class SystemManager
{
    private static final SystemManager instance = new SystemManager();
    
    public static SystemManager getInstance()
    {
        return instance;
    }
    
    private final DatabaseClient dbclient;
    
    private SystemManager()
    {
        this.dbclient = DatabaseClient.getInstance();
    }
    
    
    
}
