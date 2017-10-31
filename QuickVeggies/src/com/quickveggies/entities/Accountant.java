/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.entities;

import com.quickveggies.db.DatabaseClient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: QuickVeggies File: Accountant.java CreatedOn: Date: Oct 24, 2017
 * Time: 12:20:06 PM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class Accountant extends User
{
    private static Accountant currentAccountant;

    public static Accountant getCurrentAccountant()
    {
        return currentAccountant;
    }

    public static Accountant registerCurrentAccountant(Accountant accountant)
    {
        if(currentAccountant == null)
        {
            currentAccountant = DatabaseClient.getInstance().getAccountantByName(accountant.getName());
            User.registerCurrentUser(currentAccountant);
        }
        return currentAccountant;
    }
    
    public static void addAccountant(Accountant newAccountant) throws Exception
    {
        if(Company.getCurrentCompany().ownedBy(User.getCurrentUser()))
        {
            Company.getCurrentCompany().updateAccountantsList(User.getCurrentUser(), 
                    DatabaseClient.getInstance().addAccountant(newAccountant), true);
        }
        else throw new Exception("Insufficient permissions to revoke Accountant access!");
    }
    
    public static void updateAccountant(Accountant existingAccountant) throws Exception
    {
        if(Company.getCurrentCompany().ownedBy(User.getCurrentUser()))
            DatabaseClient.getInstance().updateAccountant(existingAccountant);
        else throw new Exception("Insufficient permissions to revoke Accountant access!");
    }
    
    public static void removeAccountant(Accountant accountant) throws Exception
    {
        if(Company.getCurrentCompany().ownedBy(User.getCurrentUser()))
        {
            DatabaseClient.getInstance().removeAccountantById(accountant.getId());
            Company.getCurrentCompany().updateAccountantsList(User.getCurrentUser(), accountant, false);
        }
        else throw new Exception("Insufficient permissions to revoke Accountant access!");
    }
    
    public static final List<Accountant> getAccountantsList(Company forCompany)
    {
        if(forCompany == null) // Must be not null
            return null;
        if(forCompany != Company.getCurrentCompany()) // AND ALSO must be the currentCompany.
            return null;
        
        List<Accountant> ls = new ArrayList<Accountant>();
        String sql = "select * from accountants where companyId="+forCompany.getId()+";";

        try(ResultSet res = DatabaseClient.getInstance().getResult(sql))
        {
            while(res.next())
                ls.add(new Accountant(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4), res.getString(5)));
            return ls;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private int id;
    private String sinceDate;

    public Accountant(String name, String password, int companyId, String sinceDate)
    {
        super(name, password, null, companyId, Role.USER.name());
        this.id = -1;
        this.sinceDate = sinceDate;
    }
    
    public Accountant(int id, String name, String password, int companyId, String sinceDate)
    {
        super(name, password, null, companyId, Role.USER.name());
        this.id = id;
        this.sinceDate = sinceDate;
    }
    
    @Override
    public int getId()
    {
        return this.id;
    }
    
    public String getSinceDate()
    {
        return this.sinceDate;
    }
    
    public boolean equals(Object obj)
    {
        return obj instanceof Accountant && getName().equals(((Accountant)obj).getName());
    }
}
