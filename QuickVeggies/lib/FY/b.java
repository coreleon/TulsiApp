// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import java.time.LocalDate;

public final class b extends Exception
{

    public b(LocalDate localdate, LocalDate localdate1)
    {
        super((new StringBuilder("InvalidFinancialYearRangeException: [")).append(localdate).append("-").append(localdate1).append("]").toString());
    }

    public b(String s)
    {
        super((new StringBuilder("InvalidFinancialYearRangeException: ")).append(s).toString());
    }
}
