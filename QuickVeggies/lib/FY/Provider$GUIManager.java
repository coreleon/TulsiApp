// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

// Referenced classes of package fy:
//            Provider, c

public static class litPane
{

    public static void initialize(Stage stage1, SplitPane splitPane)
    {
        c.a(stage1, splitPane);
    }

    public static void replaceMainPane(Node newNode)
    {
        c.a().a(newNode);
    }

    public static void reset()
    {
        c c1 = c.a();
        c1.a(c1.a());
    }

    public litPane()
    {
    }
}
