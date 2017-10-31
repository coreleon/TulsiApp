/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.quickveggies.db.erp.gl.charts;

import com.quickveggies.db.erp.gl.ExpendituresList;
import com.quickveggies.entities.Expenditure;
import fy.Financialyear;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;

/**
 * Project: QuickVeggies
 File: ExpensesCharts.java
 CreatedOn:
      Date: Oct 20, 2017
      Time: 12:19:53 AM 
 
 Description:
 * 
 * @author George Maroulis <tiger@safari>
 */
public class ExpensesCharts 
{
    public final static ExpensesChartsViewType DEFAULT_VIEWOPTION = ExpensesChartsViewType.BY_QUARTER;
    private static ExpensesCharts EXPENSES_CHARTS;
    
    public static ExpensesCharts initialize(ChoiceBox fyOption,
            ChoiceBox viewOption, 
            DatePicker fromDateSelection, 
            DatePicker toDateSelection, 
            Label msg, 
            BarChart expensesBarChart, 
            PieChart expensesPieChart)
    {
        if(EXPENSES_CHARTS != null)
            EXPENSES_CHARTS.init(fyOption, viewOption, fromDateSelection, toDateSelection, msg, expensesBarChart, expensesPieChart);
        return EXPENSES_CHARTS;
    }
    
    public static ExpensesCharts getInstance()
    {
        return EXPENSES_CHARTS;
    }
    
    public static ExpensesCharts getInstance(ExpendituresList expensesList)
    {
        if(EXPENSES_CHARTS == null)
            EXPENSES_CHARTS = new ExpensesCharts(expensesList);
        return EXPENSES_CHARTS;
    }

    private ExpensesCharts(ExpendituresList expensesList)
    {
        this.option = DEFAULT_VIEWOPTION;
        this.expensesList = expensesList;
        this.fys = new fy.FinancialYears<Financialyear>();
        this.fys.update(this.expensesList.getOldestExpenditure().getDate());
        this.fys.update(this.expensesList.getNewestExpenditure().getDate());
    }
    
    private final fy.FinancialYears<Financialyear> fys;
    /**
     * The selected view type.
     */
    private final ExpendituresList expensesList;
    /**
     * The selected view type.
     */
    private ExpensesChartsViewType option;
    
    private ChoiceBox fyOption;
    /**
     * The selected view type.
     */
    private ChoiceBox viewOption;
    /**
     * The selected view type.
     */
    private DatePicker dateFrom;
    /**
     * The selected view type.
     */
    private DatePicker dateTo;
    /**
     * The selected view type.
     */
    private Label msg;
    private String error = "-fx-text-fill: #FF0000";
    private String normal = "-fx-text-fill: #0000FF";
    
    /**
     * The selected view type.
     */
    private PieChart expensesPieChart;
    /**
     * The selected view type.
     */
    private BarChart expensesBarChart;
    private Axis x;
    private Axis y;
    private LocalDate expe;
    private Financialyear selectedFY;
    
    
    public void setFYOption(int selectedFyOption)
    {
        if(selectedFyOption == 0)
            restrictDatePisckersToFY(null);
        else
            restrictDatePisckersToFY(fys.get(selectedFyOption-1));
        
        plotExpenses();
    }
    
    public void setViewOption(ExpensesChartsViewType viewOption) throws Exception
    {
        if(viewOption == null)
            throw new Exception("ExpensesChartsViewType cannot be null!");
            
        this.option = viewOption;
        
        switch(viewOption)
        {
            case BY_DAY:
                dateFrom.setShowWeekNumbers(false);
                dateTo.setShowWeekNumbers(false);
                break;
            case BY_WEEK:
                dateFrom.setShowWeekNumbers(true);
                dateTo.setShowWeekNumbers(true);
                break;
            case BY_MONTH:
                dateFrom.setShowWeekNumbers(false);
                dateTo.setShowWeekNumbers(false);
                break;
            case BY_QUARTER:
                dateFrom.setShowWeekNumbers(false);
                dateTo.setShowWeekNumbers(false);
                break;
            default:
                throw new AssertionError();
        }
        
        plotExpenses();
    }
    
    public ExpensesChartsViewType getOption()
    {
        return this.option;
    }
    
    public BarChart getBarChart()
    {
        return this.expensesBarChart;
    }
    
    public PieChart getPieChart()
    {
        return this.expensesPieChart;
    }
    
    public void update()
    {
        if(this.expensesList != null && !this.expensesList.isEmpty())
        {
            this.expRangeFrom = LocalDate.parse(this.expensesList.getOldestExpenditure().getDate());
            this.expRangeTo = LocalDate.parse(this.expensesList.getNewestExpenditure().getDate());
            plotExpenses();
        }
    }
    
    private List<Expenditure> filteredExpList = new ArrayList<Expenditure>();
    private final List<XYChart.Data<String,Double>> barDataExpenses = new ArrayList<XYChart.Data<String,Double>>();
    private final ObservableList<PieChart.Data> pieDataExpenses = FXCollections.observableArrayList();
    
    private void filterExpenses()
    {
        filteredExpList.clear();
        long from = this.from.toEpochDay();
        long to = this.to.toEpochDay();
        long expDateLong;
        
        for(Expenditure exp : this.expensesList.getExpendituresList())
        {
            expDateLong = LocalDate.parse(exp.getDate()).toEpochDay();
            
            if(expDateLong <= to && expDateLong >= from)
                filteredExpList.add(exp);
        }
    }
    
    private void plotExpenses()
    {
        filterExpenses();
        barDataExpenses.clear();
        pieDataExpenses.clear();
        String expDate;
        double sum = 0D;
        
        switch(getOption())
        {
            case BY_DAY:
                this.x.setLabel("Days");
                for(Expenditure exp : filteredExpList)
                {
                    expDate = exp.getDate();

                    for(Expenditure expR : filteredExpList)
                    {
                        if(expR.getDate().equals(expDate))
                        {
                            filteredExpList.remove(expR);
                            sum += Double.parseDouble(expR.getAmount());
                        }
                        else
                            break;
                    }
                    barDataExpenses.add(new XYChart.Data<String,Double>(expDate, sum));
                    pieDataExpenses.add(new PieChart.Data(expDate, sum));
                    sum = 0D;
                }
                break;
            case BY_WEEK:
                this.x.setLabel("Weeks");
                int weekNo = 0;
                
                try
                {
                    for(Expenditure exp : filteredExpList)
                    {
                        weekNo = this.selectedFY.getWeekOfFY(LocalDate.parse(exp.getDate()));
//
//                        if(currentWeekNo == -1)
//                            currentWeekNo = weekNo;
//                        else if(weekNo != currentWeekNo)
//                        {
//                            plotExpenses.put(Integer.toString(currentWeekNo), sum);
//                            currentWeekNo = weekNo;
//                            sum = 0D;
//                        }

                        for(Expenditure expR : filteredExpList)
                        {
                            if(this.selectedFY.getWeekOfFY(LocalDate.parse(expR.getDate())) == weekNo)
                            {
                                filteredExpList.remove(expR);
                                sum += Double.parseDouble(expR.getAmount());
                            }
                            else
                                break;
                        }
                        barDataExpenses.add(new XYChart.Data<String,Double>(Integer.toString(weekNo), sum));
                        pieDataExpenses.add(new PieChart.Data(Integer.toString(weekNo), sum));
                        sum = 0D;
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                break;
            case BY_MONTH:
                this.x.setLabel("Months");
                String month;
                
                try
                {
                    for(Expenditure exp : filteredExpList)
                    {
                        month = this.selectedFY.getMonthNameOfFY(LocalDate.parse(exp.getDate()));
//
//                        if(currentWeekNo == -1)
//                            currentWeekNo = weekNo;
//                        else if(weekNo != currentWeekNo)
//                        {
//                            plotExpenses.put(Integer.toString(currentWeekNo), sum);
//                            currentWeekNo = weekNo;
//                            sum = 0D;
//                        }

                        for(Expenditure expR : filteredExpList)
                        {
                            if(this.selectedFY.getMonthNameOfFY(LocalDate.parse(expR.getDate())).equals(month))
                            {
                                filteredExpList.remove(expR);
                                sum += Double.parseDouble(expR.getAmount());
                            }
                            else
                                break;
                        }
                        barDataExpenses.add(new XYChart.Data<String,Double>(month, sum));
                        pieDataExpenses.add(new PieChart.Data(month, sum));
                        sum = 0D;
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                break;
            case BY_QUARTER:
                this.x.setLabel("Quarters");
                int quarter = 0;
                
                try
                {
                    for(Expenditure exp : filteredExpList)
                    {
                        quarter = this.selectedFY.getQuarterOfFY(LocalDate.parse(exp.getDate()));
//
//                        if(currentWeekNo == -1)
//                            currentWeekNo = weekNo;
//                        else if(weekNo != currentWeekNo)
//                        {
//                            plotExpenses.put(Integer.toString(currentWeekNo), sum);
//                            currentWeekNo = weekNo;
//                            sum = 0D;
//                        }

                        for(Expenditure expR : filteredExpList)
                        {
                            if(this.selectedFY.getQuarterOfFY(LocalDate.parse(expR.getDate())) == quarter)
                            {
                                filteredExpList.remove(expR);
                                sum += Double.parseDouble(expR.getAmount());
                            }
                            else
                                break;
                        }
                        barDataExpenses.add(new XYChart.Data<String,Double>(Integer.toString(quarter), sum));
                        pieDataExpenses.add(new PieChart.Data(Integer.toString(quarter), sum));
                        sum = 0D;
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
                break;
            default:
                throw new AssertionError();
        }
        
        XYChart.Series<String,Double> ser = new XYChart.Series<String,Double>();
        ser.getData().addAll(barDataExpenses);
        this.expensesBarChart.getData().addAll(barDataExpenses);
        this.expensesPieChart.setData(pieDataExpenses);
    }
    
    private void showMsg(String type, String msg)
    {
        this.msg.setStyle(type);
        this.msg.setText(msg);
    }
    
    private LocalDate expRangeFrom;
    private LocalDate expRangeTo;
    private LocalDate from;
    private LocalDate to;
    
    public void init(ChoiceBox fyOption,
            ChoiceBox viewOption, 
            DatePicker fromDateSelection, 
            DatePicker toDateSelection, 
            Label msg, 
            BarChart expensesBarChart, 
            PieChart expensesPieChart)
    {
        this.dateFrom = fromDateSelection;
        this.dateFrom.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                from = dateFrom.getValue();
                
                switch(getOption())
                {
                    case BY_DAY:
                        dateTo.setValue(from);
                        plotExpenses();
                        dateTo.requestFocus();
                        break;
                    case BY_WEEK:
                        dateTo.setValue(from.plusWeeks(1));
                        plotExpenses();
                        dateTo.requestFocus();
                        break;
                    case BY_MONTH:
                        dateTo.setValue(from.plusMonths(1));
                        plotExpenses();
                        dateTo.requestFocus();
                        break;
                    case BY_QUARTER:
                        if(to != null)
                        {
                            dateFrom.setStyle(normal);
                            showMsg(normal, "");

                            if(to.compareTo(from)<0)
                            {
                                showMsg(error, "Wrong date range selected!");
                                dateFrom.requestFocus();
                                dateFrom.setStyle(error);
                            }
                        }
                        plotExpenses();
                        dateTo.requestFocus();
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        });
        this.dateFrom.setDayCellFactory(new Callback<DatePicker, DateCell>()
        {
            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if(item.isBefore(expRangeFrom))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #808080;");
                        }
                    }
                };
            }
        });
        this.dateTo = toDateSelection;
        this.dateTo.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                dateTo.setStyle(normal);
                showMsg(normal, "");
                to = dateTo.getValue();
                
                if(to.compareTo(from)<0)
                {
                    msg.setStyle(error);
                    showMsg(error, "Wrong date range selected!");
                    dateTo.requestFocus();
                    dateTo.setStyle(error);
                    return;
                }
                else
                    plotExpenses();
            }
        });
        this.dateTo.setDayCellFactory(new Callback<DatePicker, DateCell>()
        {
            @Override
            public DateCell call(final DatePicker datePicker)
            {
                return new DateCell()
                {
                    @Override
                    public void updateItem(LocalDate item, boolean empty)
                    {
                        super.updateItem(item, empty);
                        if(item.isAfter(expRangeTo))
                        {
                            setDisable(true);
                            setStyle("-fx-background-color: #808080;");
                        }
                    }
                };
            }
        });
        this.fyOption = fyOption;
        this.fyOption.setItems(FXCollections.observableArrayList("all"));
        for(Financialyear fy : this.fys)
            this.fyOption.getItems().addAll(fy.getFyName());
        this.fys.addFYChangeListener((ListChangeListener.Change<? extends Financialyear> e)-> { for(Financialyear fy : e.getAddedSubList()) fyOption.getItems().addAll(fy.getFyName());});
        this.fyOption.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener(){
                                        @Override
                                        public void changed(ObservableValue observable, Object oldValue, Object newValue){
                                            setFYOption((Integer)newValue);
                                        }});
        
        this.viewOption = viewOption;
        this.viewOption.setItems(FXCollections.observableArrayList("By Day", "By Week", "By Month", "By Quarter"));
        this.viewOption.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                try
                {
                    switch(newValue)
                    {
                        case "By Day":
                            setViewOption(ExpensesChartsViewType.BY_DAY);
                            msg.setText("Select a date.");
                            
                            break;
                        case "By Week":
                            setViewOption(ExpensesChartsViewType.BY_WEEK);
                            msg.setText("Select a date as a week start.");
                            
                            break;
                        case "By Month":
                            setViewOption(ExpensesChartsViewType.BY_MONTH);
                            msg.setText("Select a date as a month start.");
                            
                            break;
                        case "By Quarter":
                            setViewOption(ExpensesChartsViewType.BY_QUARTER);
                            msg.setText("Select a date range.");
                            
                            break;
                        default:
                            msg.setText("");
                            throw new AssertionError();
                    }
                    dateFrom.requestFocus();
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
            }
        });
        this.msg = msg;
        this.msg.setText("");
        this.msg.setWrapText(true);
        
        this.expensesBarChart = expensesBarChart;
        this.x = this.expensesBarChart.getXAxis();
        this.y = this.expensesBarChart.getYAxis();
        this.y.setLabel("Expenses");
        this.expensesPieChart = expensesPieChart;
    }
    
    private void restrictDatePisckersToFY(Financialyear fy)
    {
        if(fy != null)
        {
            Callback<DatePicker, DateCell> limit = new Callback<DatePicker, DateCell>() {
                                                @Override
                                                public DateCell call(final DatePicker datePicker)
                                                {
                                                    return new DateCell()
                                                    {
                                                        @Override
                                                        public void updateItem(LocalDate item, boolean empty)
                                                        {
                                                            super.updateItem(item, empty);
                                                            if(!fy.contains(item))
                                                            {
                                                                setDisable(true);
                                                                setStyle("-fx-background-color: #808080;");
                                                            }
                                                        }
                                                    };
                                                }};
            this.dateFrom.setDayCellFactory(limit);
            this.dateTo.setDayCellFactory(limit);
        }
        else
        {
            this.dateFrom.setDayCellFactory(new Callback<DatePicker, DateCell>()
            {
                @Override
                public DateCell call(final DatePicker datePicker)
                {
                    return new DateCell()
                    {
                        @Override
                        public void updateItem(LocalDate item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if(item.isBefore(expRangeFrom))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #808080;");
                            }
                        }
                    };
                }
            });
            this.dateTo.setDayCellFactory(new Callback<DatePicker, DateCell>()
            {
                @Override
                public DateCell call(final DatePicker datePicker)
                {
                    return new DateCell()
                    {
                        @Override
                        public void updateItem(LocalDate item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if(item.isAfter(expRangeTo))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #808080;");
                            }
                        }
                    };
                }
            });
        }
    }
    
    private final Callback<DatePicker, DateCell> defaultFromLimit = new Callback<DatePicker, DateCell>()
            {
                @Override
                public DateCell call(final DatePicker datePicker)
                {
                    return new DateCell()
                    {
                        @Override
                        public void updateItem(LocalDate item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if(item.isBefore(expRangeFrom))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #808080;");
                            }
                        }
                    };
                }
            };
    private final Callback<DatePicker, DateCell> defaultToLimit = new Callback<DatePicker, DateCell>()
            {
                @Override
                public DateCell call(final DatePicker datePicker)
                {
                    return new DateCell()
                    {
                        @Override
                        public void updateItem(LocalDate item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if(item.isAfter(expRangeTo))
                            {
                                setDisable(true);
                                setStyle("-fx-background-color: #808080;");
                            }
                        }
                    };
                }
            };

    public static enum ExpensesChartsViewType
    {
        BY_DAY,
        BY_WEEK,
        BY_MONTH,
        BY_QUARTER;
    }
}
