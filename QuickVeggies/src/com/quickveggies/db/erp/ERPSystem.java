/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.db.erp;

import com.quickveggies.db.erp.gl.GeneralLedgerSystem;
import com.quickveggies.entities.Company;

/**
 * Project: QuickVeggies File: ERPSystem.java CreatedOn: Date: Oct 21, 2017
 * Time: 10:03:03 AM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class ERPSystem
{
    private static ERPSystem instance;
    
    public static ERPSystem getInstance(Company currentCompany)
    {
        if(instance == null)
            instance = new ERPSystem(currentCompany);
        return instance;
    }
    
    private final Company company;
    private final GeneralLedgerSystem glSystem;

    public ERPSystem(Company company)
    {
        this.company = company;
        this.glSystem = GeneralLedgerSystem.getInstance(company);
    }
    
    public Company getCompany()
    {
        return this.company;
    }
    
    public GeneralLedgerSystem getGeneralLedgerSystem()
    {
        return this.glSystem;
    }
}
