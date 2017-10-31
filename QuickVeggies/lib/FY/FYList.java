// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import com.sun.javafx.collections.ObservableListWrapper;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import javafx.collections.ListChangeListener;

// Referenced classes of package fy:
//            FinancialYears, Financialyear

public abstract class FYList extends ObservableListWrapper
    implements Cloneable
{

    public static final FYList newInstance()
    {
        return a.clone();
    }

    public FYList(List list)
    {
        super(list);
    }

    public boolean addAll(Collection c)
    {
        beginChange();
        c = super.addAll(c);
        endChange();
        return c;
        c;
        endChange();
        throw c;
    }

    public FYList clone()
    {
        return (FYList)clone();
        printStackTrace();
        return null;
    }

    public abstract boolean isCurrentFY(Financialyear financialyear);

    public abstract Financialyear getCurrentFY();

    public abstract void update(String s);

    public abstract void add(String s);

    public abstract String getNewestDate();

    public abstract String getOldestDate();

    public abstract Financialyear[] getFYs();

    public abstract Financialyear getFyByDate(LocalDate localdate);

    public abstract int getNumberOfFyWeeksSinceFoundation();

    public abstract String[] getFyNames();

    public abstract String getFyName(int i);

    public abstract Financialyear getFyByYear(int i);

    public abstract int getFyWeekNumberByDate(LocalDate localdate);

    public abstract String getColorForWeekByDate(LocalDate localdate);

    public abstract void addFYChangeListener(ListChangeListener listchangelistener);

    public abstract void addFYChangeListener(FinancialYears.FYChangeListener fychangelistener);

    public abstract void removeFYChangeListener(FinancialYears.FYChangeListener fychangelistener);

    public volatile Object clone()
    {
        return clone();
    }

    private static final FYList a = new FinancialYears();

}
