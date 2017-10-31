/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.db.erp.gl;

import com.quickveggies.entities.Company;
import fy.FinancialYears;

/**
 * Project: QuickVeggies File: GeneralLedgerSystem.java CreatedOn: Date: Oct 21,
 * 2017 Time: 2:55:42 PM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class GeneralLedgerSystem
{
    private static  GeneralLedgerSystem instance;
    
    public static GeneralLedgerSystem getInstance()
    {
        return instance;
    }
    
    public static GeneralLedgerSystem getInstance(Company currentCompany)
    {
        if(instance == null)
            instance = new GeneralLedgerSystem(currentCompany);
        return instance;
    }
    
    private final Company company;
    private final FinancialYears fys;
    private final ExpendituresList expList;

    public GeneralLedgerSystem(Company currentCompany)
    {
        this.company = currentCompany;
        this.expList = ExpendituresList.getInstance(this);
        this.fys = new FinancialYears();
    }
    
    public Company getCompany()
    {
        return this.company;
    }
    
    public FinancialYears getFYs()
    {
        return this.expList.getFYs();
    }
    
    public void updateFYs(String dateOfLedgerAction)
    {
        this.fys.update(dateOfLedgerAction);
    }
    
    public ExpendituresList getExpendituresList()
    {
        return this.expList;
    }
    
    public static abstract class LedgingEntity<T extends LedgingEntity> implements Comparable<T>
    {
        @Override
        public final int compareTo(T o)
        {
            return this.getDate().compareTo(o.getDate());
        }
        
        public abstract String getDate();
        public abstract boolean equals(T obj);
    }
//    public void setArrivals(Company company)
//    {
//        this.company = company;
//    }
//    public void setCompany(Company company)
//    {
//        this.company = company;
//    }
}
