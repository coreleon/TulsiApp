/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.db.erp;

import com.quickveggies.db.DatabaseClient;
import com.quickveggies.entities.Company;
import com.quickveggies.entities.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Project: QuickVeggies File: Accountant.java CreatedOn: Date: Oct 23, 2017
 Time: 7:24:59 AM

 Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public final class CompanyAdmin extends User
{
    public static final List<CompanyAdmin> getCompanyAdmins(Company company)
    {
        try
        {
            ResultSet res = DatabaseClient.getInstance().getResult("select * from users where companyId=" + company.getId() + " and role=" + User.Role.ADMIN.name() + ";");
            List<CompanyAdmin> ls = new ArrayList<CompanyAdmin>();
            
            while (res.next())
                ls.add(new CompanyAdmin(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6)));
            
            return ls;
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        
        return null;
    }
    
    private CompanyAdmin(int id, String name, String password, String email, int companyId, String role)
    {
        super(id, name, password, email, companyId, role);
    }
}
