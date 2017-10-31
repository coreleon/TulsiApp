/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.db.erp;

import com.quickveggies.entities.Company;
import com.quickveggies.entities.User;
import java.io.InputStream;

/**
 * Project: FiscalChronology File: CompanyCalendar.java CreatedOn: Date: Oct 21,
 * 2017 Time: 8:10:36 AM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class CompanyProfile// extends Company
{
    private static  CompanyProfile instance;
    
    public static CompanyProfile getInstance()
    {
        return instance;
    }
    
    public static CompanyProfile getInstance(User user, Company company)
    {
        if(instance == null)
            instance = new CompanyProfile(user, company);
        return instance;
    }
    
    private final User user;
    
    private CompanyProfile(User user, Company company)
    {
      //  super(company);
        this.user = user;
    }
    
    public User getUser()
    {
        return this.user;
    }

    //@Override
    public void setId(Integer id)
    {
        if(this.user.getRole()==User.Role.ADMIN)
        {
            setId(id);
        }
    }

    //@Override
    public void setName(String name)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setName(name);
    }

    //@Override
    public void setAddress(String address)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setAddress(address);
    }

    //@Override
    public void setWebsite(String website)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setWebsite(website);
    }

    //@Override
    public void setPhone(String phone)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setPhone(phone);
    }

    //@Override
    public void setEmail(String email)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setEmail(email);
    }

    //@Override
    public void setIndustryType(String industryType)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setIndustryType(industryType);
    }

    //@Override
    public String getPassword()
    {
        if(this.user.getRole()==User.Role.ADMIN)
            getPassword();
        return null;
    }

    //@Override
    public void setPassword(String password)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setPassword(password);
    }

    //@Override
    public void setLogo(InputStream logo)
    {
        if(this.user.getRole()==User.Role.ADMIN)
            setLogo(logo);
    }
}
