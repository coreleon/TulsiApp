// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import java.text.NumberFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package fy:
//            b

public final class a
    implements Comparable
{

    protected static final a a()
    {
        return a_fy_a_static_fld;
    }

    protected static a a(a a1, LocalDate localdate)
    {
        return new a(a1, localdate);
    }

    protected static List a(LocalDate localdate, LocalDate localdate1)
    {
        if(localdate.isAfter(localdate1))
            throw new b(localdate, localdate1);
        localdate = new a(a_fy_a_static_fld, localdate);
        ArrayList arraylist;
        (arraylist = new ArrayList()).add(localdate);
        arraylist.addAll(localdate.a(localdate1));
        for(localdate = 0; localdate < arraylist.size(); localdate++)
            if(localdate % 2 == 0)
                ((a)arraylist.get(localdate)).a_java_lang_String_fld = "#19AAC4";
            else
                ((a)arraylist.get(localdate)).a_java_lang_String_fld = "#38B8AD";

        return arraylist;
    }

    private static boolean a(LocalDate localdate, LocalDate localdate1)
    {
        return localdate.plusYears(1L).minusDays(1L).isEqual(localdate1);
    }

    private a(LocalDate localdate, LocalDate localdate1)
    {
        a_int_array1d_fld = new int[4];
        if(!a(localdate, localdate1))
        {
            throw new b(localdate, localdate1);
        } else
        {
            c = (new StringBuilder("XXXX-")).append(a_java_text_NumberFormat_static_fld.format(localdate.get(ChronoField.MONTH_OF_YEAR))).append("-").append(a_java_text_NumberFormat_static_fld.format(localdate.get(ChronoField.DAY_OF_MONTH))).toString();
            b_java_lang_String_fld = (new StringBuilder("XXXX-")).append(a_java_text_NumberFormat_static_fld.format(localdate1.get(ChronoField.MONTH_OF_YEAR))).append("-").append(a_java_text_NumberFormat_static_fld.format(localdate1.get(ChronoField.DAY_OF_MONTH))).toString();
            b_java_time_LocalDate_fld = LocalDate.from(localdate);
            a_java_time_LocalDate_fld = LocalDate.from(localdate1);
            a_java_lang_String_fld = "#19AAC4";
            a();
            return;
        }
    }

    private a(a a1, LocalDate localdate)
    {
        a_int_array1d_fld = new int[4];
        if((a1 = new a(a1, localdate.getYear())).b(localdate))
            a1 = a1.b();
        else
        if(a1.c(localdate))
            a1 = a1.c();
        c = a1.c;
        b_java_lang_String_fld = a1.b_java_lang_String_fld;
        b_java_time_LocalDate_fld = a1.b_java_time_LocalDate_fld;
        a_java_time_LocalDate_fld = a1.a_java_time_LocalDate_fld;
        if(!a(b_java_time_LocalDate_fld, a_java_time_LocalDate_fld))
        {
            throw new b(b_java_time_LocalDate_fld, a_java_time_LocalDate_fld);
        } else
        {
            a();
            return;
        }
    }

    private a(a a1, int i)
    {
        a_int_array1d_fld = new int[4];
        c = a1.c;
        b_java_lang_String_fld = a1.b_java_lang_String_fld;
        b_java_time_LocalDate_fld = LocalDate.parse(c.replace("XXXX", String.valueOf(i)));
        a_java_time_LocalDate_fld = LocalDate.parse(b_java_lang_String_fld.replace("XXXX", (new StringBuilder()).append(i + 1).toString()));
        if(!a(b_java_time_LocalDate_fld, a_java_time_LocalDate_fld))
        {
            throw new b(b_java_time_LocalDate_fld, a_java_time_LocalDate_fld);
        } else
        {
            a();
            return;
        }
    }

    private final void a()
    {
        LocalDate localdate = b_java_time_LocalDate_fld;
        for(int i = 0; i < 4; i++)
        {
            LocalDate localdate1 = a(localdate);
            a_int_array1d_fld[i] = (a(localdate1.minusDays(1L)) - a(localdate)) + 1;
            localdate = localdate1;
        }

        return;
        printStackTrace();
    }

    private static LocalDate a(LocalDate localdate)
    {
        int i;
        int j;
        i = localdate.get(ChronoField.DAY_OF_MONTH);
        localdate = (j = ((localdate.get(ChronoField.MONTH_OF_YEAR) + 3) - 1) % 12 + 1) != 1 ? ((LocalDate) (localdate.get(ChronoField.YEAR))) : ((LocalDate) (localdate.get(ChronoField.YEAR) + 1));
        return LocalDate.of(localdate, j, i);
        printStackTrace();
        System.exit(i);
        return null;
    }

    public final List a(LocalDate localdate)
    {
        if(!b(localdate))
            break MISSING_BLOCK_LABEL_47;
        ArrayList arraylist;
        arraylist = new ArrayList();
        for(a a1 = this; !a1.a(localdate); arraylist.add(a1 = a1.b()));
        return arraylist;
        printStackTrace();
        return null;
    }

    public final List b(LocalDate localdate)
    {
        if(!c(localdate))
            break MISSING_BLOCK_LABEL_47;
        ArrayList arraylist;
        arraylist = new ArrayList();
        for(a a1 = this; !a1.a(localdate); arraylist.add(a1 = a1.c()));
        return arraylist;
        printStackTrace();
        return null;
    }

    private final boolean b(LocalDate localdate)
    {
        return localdate.isAfter(a_java_time_LocalDate_fld);
    }

    private final boolean c(LocalDate localdate)
    {
        return localdate.isBefore(b_java_time_LocalDate_fld);
    }

    public final a b()
    {
        return new a(this, c() + 1);
    }

    private final a c()
    {
        return new a(this, c() - 1);
    }

    private static final int a(a a1, LocalDate localdate, ChronoField chronofield)
    {
        if(!a1.a(localdate))
            throw new b((new StringBuilder()).append(localdate).append(" is not contained in ").append(a1).toString());
        if(localdate.get(ChronoField.YEAR) == a1.b_java_time_LocalDate_fld.get(ChronoField.YEAR))
            return (localdate.get(chronofield) - a1.b_java_time_LocalDate_fld.get(chronofield)) + 1;
        else
            return (LocalDate.of(a1.b_java_time_LocalDate_fld.get(ChronoField.YEAR), 12, 31).get(chronofield) - a1.b_java_time_LocalDate_fld.get(chronofield)) + 1 + localdate.get(chronofield);
    }

    public final int a(LocalDate localdate)
    {
        return a(this, localdate, ChronoField.DAY_OF_YEAR);
    }

    public final int b(LocalDate localdate)
    {
        return a(this, localdate, ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    public final int c(LocalDate localdate)
    {
        return a(this, localdate, ChronoField.MONTH_OF_YEAR);
    }

    public final int d(LocalDate localdate)
    {
        localdate = a(localdate);
        int i = 0;
        for(int j = 0; j < 4; j++)
        {
            i += a_int_array1d_fld[j];
            if(localdate <= i)
                return j + 1;
        }

        return -1;
    }

    public final String a(LocalDate localdate)
    {
        if(!a(localdate))
            throw new b((new StringBuilder()).append(localdate).append(" is not contained in ").append(this).toString());
        else
            return Month.of(localdate.get(ChronoField.MONTH_OF_YEAR)).name();
    }

    public final int a()
    {
        return (LocalDate.of(b_java_time_LocalDate_fld.get(ChronoField.YEAR), 12, 31).get(ChronoField.ALIGNED_WEEK_OF_YEAR) - b_java_time_LocalDate_fld.get(ChronoField.ALIGNED_WEEK_OF_YEAR)) + 1 + a_java_time_LocalDate_fld.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    public final int b()
    {
        return (LocalDate.of(b_java_time_LocalDate_fld.get(ChronoField.YEAR), 12, 31).get(ChronoField.DAY_OF_YEAR) - b_java_time_LocalDate_fld.get(ChronoField.DAY_OF_YEAR)) + 1 + a_java_time_LocalDate_fld.get(ChronoField.DAY_OF_YEAR);
    }

    public final boolean a(LocalDate localdate)
    {
        return (localdate.isAfter(b_java_time_LocalDate_fld) || localdate.isEqual(b_java_time_LocalDate_fld)) && (localdate.isBefore(a_java_time_LocalDate_fld) || localdate.isEqual(a_java_time_LocalDate_fld));
    }

    public final String a()
    {
        return (new StringBuilder()).append(b_java_time_LocalDate_fld.get(ChronoField.YEAR)).append("/").append(a_java_time_LocalDate_fld.get(ChronoField.YEAR)).toString();
    }

    public final int c()
    {
        return b_java_time_LocalDate_fld.get(ChronoField.YEAR);
    }

    public final void a(String s)
    {
        a_java_lang_String_fld = s;
    }

    public final String b()
    {
        return a_java_lang_String_fld;
    }

    public final LocalDate a()
    {
        return b_java_time_LocalDate_fld;
    }

    public final LocalDate b()
    {
        return a_java_time_LocalDate_fld;
    }

    public final String c()
    {
        return c;
    }

    public final String d()
    {
        return b_java_lang_String_fld;
    }

    public final LocalDate a(String s)
    {
        return LocalDate.parse(c.replace("XXXX", s));
    }

    public final LocalDate b(String s)
    {
        return LocalDate.parse(b_java_lang_String_fld.replace("XXXX", s));
    }

    public final boolean a(a a1)
    {
        return b_java_time_LocalDate_fld.equals(a1.b_java_time_LocalDate_fld) && a_java_time_LocalDate_fld.equals(a1.a_java_time_LocalDate_fld);
    }

    public final boolean equals(Object obj)
    {
        if(obj instanceof a)
            return a((a)obj);
        else
            return super.equals(obj);
    }

    public final String toString()
    {
        return (new StringBuilder("[")).append(c).append("/").append(b_java_lang_String_fld).append("]: ").append(b_java_time_LocalDate_fld).append("/").append(a_java_time_LocalDate_fld).toString();
    }

    public final int a(a a1)
    {
        return c() - a1.c();
    }

    public final int compareTo(Object obj)
    {
        return a((a)obj);
    }

    private static a a_fy_a_static_fld;
    private static final NumberFormat a_java_text_NumberFormat_static_fld;
    private String a_java_lang_String_fld;
    private String b_java_lang_String_fld;
    private String c;
    private final LocalDate a_java_time_LocalDate_fld;
    private final LocalDate b_java_time_LocalDate_fld;
    private final int a_int_array1d_fld[];

    static 
    {
        (a_java_text_NumberFormat_static_fld = NumberFormat.getInstance()).setMinimumIntegerDigits(2);
        a_fy_a_static_fld = new a(LocalDate.parse("2010-04-01"), LocalDate.parse("2011-03-31"));
        break MISSING_BLOCK_LABEL_35;
        printStackTrace();
    }
}
