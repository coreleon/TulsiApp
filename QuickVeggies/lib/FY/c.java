// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 

package fy;

import java.io.PrintStream;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class c
{

    public static c a()
    {
        return a_fy_c_static_fld;
    }

    public static c a(Stage stage, SplitPane splitpane)
    {
        if(a_fy_c_static_fld == null)
            a_fy_c_static_fld = new c(stage, splitpane);
        return a_fy_c_static_fld;
    }

    private c(Stage stage, SplitPane splitpane)
    {
        a_javafx_stage_Stage_fld = stage;
        a_javafx_stage_Stage_fld.setResizable(true);
        splitpane.getItems().get(0);
        a_javafx_scene_layout_Pane_fld = (AnchorPane)splitpane.getItems().get(1);
        b_javafx_scene_layout_Pane_fld = (AnchorPane)a_javafx_scene_layout_Pane_fld.getChildrenUnmodifiable().get(0);
        c = b_javafx_scene_layout_Pane_fld;
        a_double_fld = b_javafx_scene_layout_Pane_fld.getLayoutX();
        b_double_fld = b_javafx_scene_layout_Pane_fld.getLayoutY();
        a(a_javafx_scene_layout_Pane_fld, c);
    }

    private static void a(Node node, Node node1)
    {
        double d = node1.getLayoutBounds().getWidth() - node.getLayoutBounds().getWidth();
        double d1 = node1.getLayoutBounds().getHeight() - node.getLayoutBounds().getHeight();
        node.resize(node.getLayoutBounds().getWidth() + d, node.getLayoutBounds().getHeight() + d1);
    }

    public final Pane a()
    {
        return b_javafx_scene_layout_Pane_fld;
    }

    private void a()
    {
        System.out.println((new StringBuilder("StageSize: ")).append(a_javafx_stage_Stage_fld.getWidth()).append("x").append(a_javafx_stage_Stage_fld.getHeight()).toString());
    }

    private void b()
    {
        System.out.println((new StringBuilder("MainPaneSize: ")).append(c.getWidth()).append("x").append(c.getHeight()).toString());
    }

    private void c()
    {
        System.out.println((new StringBuilder("RightViewSize: ")).append(a_javafx_scene_layout_Pane_fld.getWidth()).append("x").append(a_javafx_scene_layout_Pane_fld.getHeight()).toString());
    }

    public final void a(Node node)
    {
        Object obj = node;
        System.out.println((new StringBuilder("NodeSize: ")).append(((Pane)obj).getWidth()).append("x").append(((Pane)obj).getHeight()).toString());
        a();
        b();
        c();
        a_javafx_stage_Stage_fld.setResizable(true);
        a_javafx_scene_layout_Pane_fld.setManaged(false);
        a();
        b();
        c();
        a_javafx_scene_layout_Pane_fld.getChildren().removeAll(new Node[] {
            c
        });
        node.autosize();
        a(((Node) (a_javafx_scene_layout_Pane_fld)), node);
        a_javafx_stage_Stage_fld.setWidth(a_javafx_stage_Stage_fld.getWidth() + (node.getLayoutBounds().getWidth() - ((Node) (obj = c)).getLayoutBounds().getWidth()));
        a_javafx_stage_Stage_fld.setHeight(a_javafx_stage_Stage_fld.getHeight() + (node.getLayoutBounds().getHeight() - ((Node) (obj = c)).getLayoutBounds().getHeight()));
        a_javafx_scene_layout_Pane_fld.getChildren().addAll(new Node[] {
            node
        });
        node.setLayoutX(a_double_fld);
        node.setLayoutY(b_double_fld);
        c = (Pane)node;
        a_javafx_scene_layout_Pane_fld.autosize();
        a_javafx_stage_Stage_fld.setResizable(false);
    }

    private static c a_fy_c_static_fld;
    private final Stage a_javafx_stage_Stage_fld;
    private final Pane a_javafx_scene_layout_Pane_fld;
    private final Pane b_javafx_scene_layout_Pane_fld;
    private Pane c;
    private final double a_double_fld;
    private final double b_double_fld;
}
