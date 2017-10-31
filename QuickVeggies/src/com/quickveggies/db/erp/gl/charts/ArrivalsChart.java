/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quickveggies.db.erp.gl.charts;

import com.quickveggies.db.erp.gl.GeneralLedgerSystem;
import com.quickveggies.entities.DSalesTableLine;
import fy.FinancialYears;
import fy.Financialyear;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Project: QuickVeggies
 File: ArrivalsChart.java
 CreatedOn:
      Date: Oct 20, 2017
      Time: 12:19:53 AM 
 
 Description:
 * 
 * @author George Maroulis <tiger@safari>
 */
public class ArrivalsChart 
{
    private static ArrivalsChart ARRIVALS_CHARTS;
    
    public static ArrivalsChart getInstance()
    {
        return ARRIVALS_CHARTS;
    }
    
    public static ArrivalsChart initialize(BarChart arrivalBarChart, 
            ComboBox filterSelection, 
            Label gross, 
            Label cases, 
            ObservableList<DSalesTableLine> lines, 
            TableView<DSalesTableLine> salesDashTable)
    {
        if(ARRIVALS_CHARTS == null)
            ARRIVALS_CHARTS = new ArrivalsChart(arrivalBarChart, filterSelection, gross, cases, lines, salesDashTable);
        return ARRIVALS_CHARTS;
    }

    private ArrivalsChart(BarChart arrivalBarChart, 
            ComboBox filterSelection, 
            Label gross, 
            Label cases, 
            ObservableList<DSalesTableLine> lines, 
            TableView<DSalesTableLine> salesDashTable)
    {
        this.glSystem = GeneralLedgerSystem.getInstance();
        
        this.arrivalBarChart = arrivalBarChart;
        this.filterSelection = filterSelection;
        this.gross = gross;
        this.cases = cases;
        this.lines = lines;
        this.lines.addListener(
                    (ListChangeListener.Change<? extends DSalesTableLine> c) 
                            -> {String f;
                                for(DSalesTableLine d : c.getAddedSubList()){ 
                                        f=d.getFruit(); 
                                        if(!fruits.contains(f))
                                            fruits.addAll(d.getFruit());
                                        this.glSystem.updateFYs(d.getDate());
                                    }
                                });
        this.salesDashTable = salesDashTable;
        
        filterSelection.setOnAction(
                (Event e) 
                -> { plotArrivalsBarChart((String)filterSelection.getSelectionModel().getSelectedItem()); });
        fruits.addListener(
                (ListChangeListener.Change<? extends String> c) 
                        -> { for(final String d : c.getAddedSubList()) 
                        { filters.addAll(getOptionsForFruit(d));}filters.sort(Comparator.reverseOrder());});
        
        this.fys = GeneralLedgerSystem.getInstance().getFYs();
        this.fyMap = new HashMap<String,Financialyear>();
        this.filterSelection.setItems(filters);
    }
    
    private GeneralLedgerSystem glSystem;
    
    private FinancialYears fys;
    
    private Map<String,Financialyear> fyMap;
    
    private BarChart arrivalBarChart;
    
    private ObservableList<XYChart.Data<String,Integer>> arrivalBarChartData = FXCollections.observableArrayList();

    private ObservableList<DSalesTableLine> lines = FXCollections.observableArrayList();
    
    private ObservableList<String> filters = FXCollections.observableArrayList();

    private ObservableList<String> fruits = FXCollections.observableArrayList();
    
    private TableView<DSalesTableLine> salesDashTable;
    
    private ComboBox filterSelection;
    
    private Label gross;
    
    private Label cases;
    
    private void plotArrivalsBarChart(String selectedFilter)
    {
        String[] sp = selectedFilter.split(" \\(Y\\) ");
        String fruitName = sp[0];
        Financialyear fy = fyMap.get(sp[1]);
        arrivalBarChartData.clear();
        arrivalBarChart.getXAxis().setLabel("Date");
        arrivalBarChart.getYAxis().setLabel("Gross");
        TableColumn<DSalesTableLine,?> colGross = salesDashTable.getColumns().get(13);
        TableColumn<DSalesTableLine,?> colCases = salesDashTable.getColumns().get(7);
        List<String> blk = new ArrayList<String>();
        String date;
        int gr;
        int sum = 0;
        int grossValue = 0;
        int casesValue = 0;
        
        for(int i = 0; i<lines.size(); i++)
        {
            date = lines.get(i).getDate();
            
            if(fy.contains(LocalDate.parse(date)))
            {
                if(!blk.contains(date))
                {
                    for(int j = i; j<lines.size(); j++)
                    {
                        if(lines.get(j).getDate().equals(date) && lines.get(j).getFruit().equals(fruitName))
                        {
                            gr = ((Integer)colGross.getCellObservableValue(lines.get(j)).getValue());
                            sum += gr;
                            grossValue += gr;
                            casesValue += ((Integer)colCases.getCellObservableValue(lines.get(j)).getValue());
                        }
                    }
                    
                    blk.add(date);
                    arrivalBarChartData.add(new XYChart.Data<String,Integer>(date, sum));
                    sum = 0;
                }
            }
        }
        XYChart.Series<String, Integer> s = new XYChart.Series<String, Integer>();
        s.getData().addAll(arrivalBarChartData);
        arrivalBarChart.getData().addAll(s);
        this.gross.setText(Integer.toString(grossValue));
        this.cases.setText(Integer.toString(casesValue));
    }
    
    private String[] getOptionsForFruit(String fruitName)
    {
        Financialyear[] fs = this.fys.getFYs();
        String[] options = new String[fs.length];
        
        for(int fy = 0; fy<fs.length; fy++)
            options[fy] = fruitName + " (F) " + fyToShortForm(fs[fy]);
        return options;
    }
    
    private String fyToShortForm(Financialyear fy)
    {
        String opf;
        
        if(this.fys.isCurrentFY(fy))
            opf = "this FY";
        else
            opf = "FY " + Integer.toString(fy.getFYStartDate().getYear()).substring(2)
                    + "-" + Integer.toString(fy.getFYEndDate().getYear()).substring(2);
            
        if(fyMap.get(opf) == null)
            fyMap.put(opf, fy);
        
        return opf;
    }
}
