// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import a;
import java.security.NoSuchAlgorithmException;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

// Referenced classes of package fy:
//            FYList, c

public class Provider
{
    public static class GUIManager
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

        public GUIManager()
        {
        }
    }


    public Provider()
    {
    }

    public static String newShaInstance(String algorithm, String password)
    {
        a.a();
        return a.a(password);
        printStackTrace();
        return null;
    }

    public static FYList newFYListInstance()
    {
        return FYList.newInstance();
    }
}
