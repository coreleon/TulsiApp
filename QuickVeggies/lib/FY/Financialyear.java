// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import java.time.LocalDate;
import java.util.*;

// Referenced classes of package fy:
//            a, b

public class Financialyear
    implements Comparable
{

    protected static List a(LocalDate localdate, LocalDate localdate1)
    {
        return a(fy.a.a(localdate, localdate1));
    }

    public Financialyear()
    {
        a a1;
        a1 = null;
        a1 = fy.a.a(a_fy_Financialyear_static_fld.a_fy_a_fld, LocalDate.now());
        break MISSING_BLOCK_LABEL_25;
        printStackTrace();
        a_fy_a_fld = a1;
        return;
    }

    private Financialyear(a fy)
    {
        a_fy_a_fld = fy;
    }

    private static List a(List list)
    {
        ArrayList arraylist = new ArrayList();
        a a1;
        for(list = list.iterator(); list.hasNext(); arraylist.add(new Financialyear(a1)))
            a1 = (a)list.next();

        return arraylist;
    }

    public List getNextFYsToDate(LocalDate toDate)
    {
        return a(a_fy_a_fld.a(toDate));
    }

    public List getPreviousFYsToDate(LocalDate toDate)
    {
        return a(a_fy_a_fld.b(toDate));
    }

    public Financialyear getNextFY()
    {
        return new Financialyear(a_fy_a_fld.b());
    }

    public int getDayOfFY(LocalDate date)
    {
        return a_fy_a_fld.a(date);
    }

    public int getWeekOfFY(LocalDate date)
    {
        return a_fy_a_fld.b(date);
    }

    public int getMonthOfFY(LocalDate date)
    {
        return a_fy_a_fld.c(date);
    }

    public int getQuarterOfFY(LocalDate date)
    {
        return a_fy_a_fld.d(date);
    }

    public String getMonthNameOfFY(LocalDate date)
    {
        return a_fy_a_fld.a(date);
    }

    public int getLastWeekNumber()
    {
        return a_fy_a_fld.a();
    }

    public int getNumberOfDays()
    {
        return a_fy_a_fld.b();
    }

    public boolean contains(LocalDate date)
    {
        return a_fy_a_fld.a(date);
    }

    public String getFyName()
    {
        return a_fy_a_fld.a();
    }

    public int getYear()
    {
        return a_fy_a_fld.c();
    }

    public void setColor(String color)
    {
        a_fy_a_fld.a(color);
    }

    public String getColor()
    {
        return a_fy_a_fld.b();
    }

    public LocalDate getFYStartDate()
    {
        return a_fy_a_fld.a();
    }

    public LocalDate getFYEndDate()
    {
        return a_fy_a_fld.b();
    }

    public String getFYStart()
    {
        return a_fy_a_fld.c();
    }

    public String getFYEnd()
    {
        return a_fy_a_fld.d();
    }

    public LocalDate getFYStartForYear(String year)
    {
        return a_fy_a_fld.a(year);
    }

    public LocalDate getFYEndForYear(String year)
    {
        return a_fy_a_fld.b(year);
    }

    public boolean equals(Financialyear obj)
    {
        return a_fy_a_fld.a(obj.a_fy_a_fld);
    }

    public String toString()
    {
        return a_fy_a_fld.toString();
    }

    public int compareTo(Financialyear o)
    {
        return a_fy_a_fld.a(o.a_fy_a_fld);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Financialyear)obj);
    }

    private static Financialyear a_fy_Financialyear_static_fld = new Financialyear(fy.a.a());
    private final a a_fy_a_fld;

}
