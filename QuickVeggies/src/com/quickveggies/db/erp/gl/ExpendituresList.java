/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickveggies.db.erp.gl;

import com.quickveggies.db.DatabaseClient;
import com.quickveggies.db.erp.gl.charts.ExpensesCharts;
import com.quickveggies.entities.Company;
import com.quickveggies.entities.Expenditure;
import fy.FinancialYears;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

/**
 * Project: QuickVeggies File: ExpendituresList.java CreatedOn: Date: Oct 21,
 * 2017 Time: 3:10:24 PM
 *
 * Description:
 *
 * @author George Maroulis <tiger@safari>
 */
public class ExpendituresList
{
    private static ExpendituresList instance;
    
    static public ExpendituresList getInstance()
    {
        return instance;
    }
    
    static ExpendituresList getInstance(GeneralLedgerSystem currentGL)
    {
        if(instance == null)
            instance = new ExpendituresList(currentGL);
        return instance;
    }
    
    private final GeneralLedgerSystem glSystem;
    private final List<Expenditure> expensesList;
    private final ExpensesCharts expensesCharts;

    private ExpendituresList(GeneralLedgerSystem currentGL)
    {
        this.expensesList = new LinkedList<Expenditure>();
        this.glSystem = currentGL;
        this.expensesCharts = ExpensesCharts.getInstance(this);
        this.updateExpensesList(null);
    }
    
    public boolean addExpenditure(Expenditure expenditure)
    {
        return this.updateExpensesList(expenditure);
    }
    
    public BarChart getExpensesBarChart()
    {
        return this.expensesCharts.getBarChart();
    }
    
    public PieChart getExpensesPieChart()
    {
        return this.expensesCharts.getPieChart();
    }
    
    public List<Expenditure> getExpendituresList()
    {
        this.expensesList.sort(Comparator.naturalOrder());
        return this.expensesList;
    }
    
    public List<Expenditure> getExpenditureList(DatabaseClient dbclient)
    {
        return dbclient.getExpenditureList();
    }
    
    public ExpensesCharts getExpendituresCharts()
    {
        return this.expensesCharts;
    }
    
    public FinancialYears getFYs()
    {
        return this.glSystem.getFYs();
    }
    
    public Company getCompany()
    {
        return this.glSystem.getCompany();
    }
    
    public List<Expenditure> getExpenditureListByDate(LocalDate from, LocalDate to) 
    {
        return getExpenditureList(" date >= " + from + " date <= " + to);
    }
    
    private List<Expenditure> getExpenditureList(String extraConditions)
    {
        String sql;
        
        if(extraConditions.equals(""))
            sql = "select * from expenditures where companyId="+getCompany().getId()+" order by date assc;";
        else
            sql = "select * from expenditures where companyId="+getCompany().getId()+" and "+extraConditions+" order by date assc;";
        
        List<Expenditure> list = new ArrayList<>();
        try
        {
            Expenditure xpr;
            InputStream ins;
            ResultSet rs = DatabaseClient.getInstance().getResult(sql);
            while(rs.next())
            {
                xpr = new Expenditure(
                            rs.getInt(1), 
                            rs.getInt(2), 
                            rs.getString(3), 
                            rs.getString(4), 
                            rs.getString(5), 
                            rs.getString(6), 
                            rs.getString(7), 
                            ins=rs.getBinaryStream(8));
                
                if(ins!=null)
                    xpr.setReceipt(ins);
                
                list.add(xpr);
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    
    private boolean updateExpensesList(Expenditure newExpenditure)
    {
        if(newExpenditure != null)
        {
            newExpenditure = DatabaseClient.getInstance().addExpenditure(newExpenditure);

            if(newExpenditure != null)
            {
                this.expensesList.add(newExpenditure);
                this.glSystem.updateFYs(newExpenditure.getDate());
            }
            
            expensesCharts.update();
            return newExpenditure != null;
        }
        else
        {
            Expenditure lastExp = getNewestExpenditure();

            if(lastExp == null)
            {
                this.expensesList.addAll(getExpenditureList(""));
                this.glSystem.updateFYs(this.getOldestExpenditure().getDate());
                this.glSystem.updateFYs(this.getNewestExpenditure().getDate());
            }
            
            expensesCharts.update();
            return true;
        }
    }
    
    public boolean isEmpty()
    {
        return this.expensesList.isEmpty();
    }
    
    public Expenditure getNewestExpenditure()
    {
        try
        {
            this.expensesList.sort(Comparator.naturalOrder());
            return this.expensesList.get(this.expensesList.size()-1);
        }
        catch(IndexOutOfBoundsException exc)
        {
            return null;
        }
    }
    
    public Expenditure getOldestExpenditure()
    {
        try
        {
            this.expensesList.sort(Comparator.naturalOrder());
            return this.expensesList.get(0);
        }
        catch(IndexOutOfBoundsException exc)
        {
            return null;
        }
    }
}
