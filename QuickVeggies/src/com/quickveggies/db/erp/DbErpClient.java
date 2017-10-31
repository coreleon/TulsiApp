/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quickveggies.db.erp;

import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.DSalesTableLine;
import com.quickveggies.entities.Expenditure;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: QuickVeggies
 * File: DbErpClient.java
 * CreatedOn:
 *      Date: Oct 16, 2017
 *      Time: 5:24:23 PM 
 * 
 * Description:
 * 
 * @author George Maroulis <tiger@safari>
 */
public class DbErpClient {
    
    public static DbErpClient getInstance()
    {
        return new DbErpClient();
    }
    
    private final DatabaseClient dbclient = DatabaseClient.getInstance();

    public DbErpClient()
    {
    }
    
    
    
    public List<DSalesTableLine> getArrivals(DatabaseClient dbclient) {
        try
        {
            return dbclient.getSalesEntries();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            return null;
        }
    }
    
    
}
