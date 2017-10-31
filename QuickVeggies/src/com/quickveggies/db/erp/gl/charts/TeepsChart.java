/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quickveggies.db.erp.gl.charts;

import com.quickveggies.db.erp.gl.GeneralLedgerSystem;
import com.quickveggies.entities.DBuyerTableLine;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
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
public class TeepsChart 
{
    private static TeepsChart TEEPS_CHART;
    
    public static TeepsChart getInstance()
    {
        return TEEPS_CHART;
    }
    
    public static TeepsChart initialize(BarChart teepBarChart, TableView<DBuyerTableLine> tvTeeps)
    {
        if(TEEPS_CHART == null)
            TEEPS_CHART = new TeepsChart(teepBarChart, tvTeeps);
        return TEEPS_CHART;
    }
    
    private BarChart teepBarChart;
    private ObservableList<DBuyerTableLine> buyerDeals;
    private TableView<DBuyerTableLine> tvTeeps;

    private TeepsChart(BarChart teepBarChart, TableView<DBuyerTableLine> tvTeeps)
    {
        this.glSystem = GeneralLedgerSystem.getInstance();
        this.teepBarChart = teepBarChart;
        this.tvTeeps = tvTeeps;
        this.tvTeeps.itemsProperty().addListener(new ChangeListener<ObservableList<DBuyerTableLine>>()
        {
            @Override
            public void changed(ObservableValue<? extends ObservableList<DBuyerTableLine>> observable, ObservableList<DBuyerTableLine> oldValue, ObservableList<DBuyerTableLine> newValue)
            {
                buyerDeals = newValue;
//                buyerDeals.addListener((ListChangeListener.Change<? extends DBuyerTableLine> c) 
//                                        -> {});
                plotTeepBarChart();
            }
        });
    }
    
    private GeneralLedgerSystem glSystem;
    
    private ObservableList<XYChart.Data<String,Integer>> teepBarChartData = FXCollections.observableArrayList();
    
    private void plotTeepBarChart()
    {
        teepBarChart.getXAxis().setLabel("Date");
        teepBarChart.getYAxis().setLabel("Commission");
        TableColumn<DBuyerTableLine,?> colCom = tvTeeps.getColumns().get(4);
        List<String> blk = new ArrayList<String>();
        String date;
        int sum = 0;
        
        for(int i = 0; i<buyerDeals.size(); i++)
        {
            date = buyerDeals.get(i).getDate();
            
            if(!blk.contains(date))
            {
                for(int j = i; j<buyerDeals.size(); j++)
                    if(buyerDeals.get(j).getDate().equals(date))
                        sum += ((Integer)colCom.getCellObservableValue(buyerDeals.get(j)).getValue());
                
                blk.add(date);
                teepBarChartData.add(new XYChart.Data<String,Integer>(date, sum));
                sum = 0;
            }
        }
        XYChart.Series<String, Integer> s = new XYChart.Series<String, Integer>();
        s.getData().addAll(teepBarChartData);
        teepBarChart.getData().addAll(s);
    }
}
