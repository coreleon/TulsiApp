// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import java.beans.*;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.ListChangeListener;

// Referenced classes of package fy:
//            FYList, Financialyear

public final class FinancialYears extends FYList
{
    public static class FYChangeEvent extends PropertyChangeEvent
    {

        public FYList getSource()
        {
            return (FYList)super.getSource();
        }

        public Financialyear getNewValue()
        {
            return (Financialyear)super.getNewValue();
        }

        public Financialyear getOldValue()
        {
            return (Financialyear)super.getOldValue();
        }

        public volatile Object getOldValue()
        {
            return getOldValue();
        }

        public volatile Object getNewValue()
        {
            return getNewValue();
        }

        public volatile Object getSource()
        {
            return getSource();
        }

        public FYChangeEvent(FYList source, String propertyName, Financialyear oldValue, Financialyear newValue)
        {
            super(source, propertyName, oldValue, newValue);
        }
    }

    public static interface FYChangeListener
        extends PropertyChangeListener
    {

        public abstract void fyChange(FYChangeEvent fychangeevent);
    }


    public FinancialYears()
    {
        super(new ArrayList());
        a_java_beans_PropertyChangeSupport_fld = new PropertyChangeSupport(this);
    }

    public final boolean isCurrentFY(Financialyear fy)
    {
        return getCurrentFY().equals(fy);
    }

    public final Financialyear getCurrentFY()
    {
        if(size() != 0)
            return (Financialyear)get(size() - 1);
        else
            return null;
    }

    public final synchronized void update(String date)
    {
        if(date != null)
        {
            add(date);
            String s;
            if((s = getNewestDate()) != null)
            {
                if(getCurrentFY() != null)
                {
                    if((date = getCurrentFY().getNextFYsToDate(LocalDate.parse(s))) != null)
                    {
                        addAll(date);
                        return;
                    }
                } else
                {
                    LocalDate localdate = LocalDate.now();
                    if((date = LocalDate.parse(date)).isBefore(localdate) || date.isEqual(localdate))
                        addAll(Financialyear.a(date, localdate));
                }
                return;
            }
            addAll(Financialyear.a(LocalDate.parse(date), LocalDate.now()));
        }
        return;
        printStackTrace();
    }

    public final void add(String newDate)
    {
        a_java_util_List_fld.add(newDate);
        a_java_util_List_fld.sort(Comparator.naturalOrder());
    }

    public final String getNewestDate()
    {
        return (String)a_java_util_List_fld.get(a_java_util_List_fld.size() - 1);
        JVM INSTR pop ;
        return null;
    }

    public final String getOldestDate()
    {
        return (String)a_java_util_List_fld.get(0);
        JVM INSTR pop ;
        return null;
    }

    public final Financialyear[] getFYs()
    {
        return (Financialyear[])toArray(new Financialyear[0]);
    }

    public final Financialyear getFyByDate(LocalDate date)
    {
        Financialyear financialyear;
        for(Iterator iterator = iterator(); iterator.hasNext();)
            if((financialyear = (Financialyear)iterator.next()).contains(date))
                return financialyear;

        return null;
    }

    public final int getNumberOfFyWeeksSinceFoundation()
    {
        LocalDate localdate;
        int i;
        localdate = LocalDate.now();
        i = 0;
        Financialyear financialyear;
        for(Iterator iterator = iterator(); iterator.hasNext();)
            if((financialyear = (Financialyear)iterator.next()).contains(localdate))
                i += financialyear.getWeekOfFY(localdate);
            else
                i += financialyear.getLastWeekNumber();

        return i;
        printStackTrace();
        return -1;
    }

    public final String[] getFyNames()
    {
        String as[] = new String[size()];
        for(int i = 0; i < as.length; i++)
            as[i] = ((Financialyear)get(i)).getFyName();

        return as;
    }

    public final String getFyName(int year)
    {
        if((year = getFyByYear(year)) != null)
            return year.getFyName();
        else
            return null;
    }

    public final Financialyear getFyByYear(int year)
    {
        Financialyear financialyear;
        for(Iterator iterator = iterator(); iterator.hasNext();)
            if((financialyear = (Financialyear)iterator.next()).getYear() == year)
                return financialyear;

        return null;
    }

    public final int getFyWeekNumberByDate(LocalDate date)
    {
        Financialyear financialyear = getFyByDate(date);
        if(financialyear != null)
            return financialyear.getWeekOfFY(date);
        break MISSING_BLOCK_LABEL_22;
        printStackTrace();
        return -1;
    }

    public final String getColorForWeekByDate(LocalDate date)
    {
        if((date = getFyByDate(date)) != null)
            return date.getColor();
        else
            return null;
    }

    public final void addFYChangeListener(ListChangeListener listener)
    {
        addListener(listener);
    }

    public final void addFYChangeListener(FYChangeListener listener)
    {
        a_java_beans_PropertyChangeSupport_fld.addPropertyChangeListener(listener);
    }

    public final void removeFYChangeListener(FYChangeListener listener)
    {
        a_java_beans_PropertyChangeSupport_fld.removePropertyChangeListener(listener);
    }

    private final List a_java_util_List_fld = new ArrayList();
    private PropertyChangeSupport a_java_beans_PropertyChangeSupport_fld;
}
