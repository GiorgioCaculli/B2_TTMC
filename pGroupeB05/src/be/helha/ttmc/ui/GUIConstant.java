package be.helha.ttmc.ui;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GUIConstant
{
    // GRAPHICAL VALUES
    public static final String WINDOW_STYLE = "-fx-background-color: mediumslateblue;" + "-fx-font-size: 15pt;";
    public static final String BUTTON_STYLE = "-fx-background-color: transparent;" + "-fx-border-radius: 150;"
    //+ "-fx-border-width : 2;"+"-fx-border-color: black;"
            + "-fx-background-radius: 150;";
    public static final Stop[] BUTTON_ETAPES =
    { new Stop( 0, Color.THISTLE ), new Stop( 0.3, Color.PINK ), new Stop( 0.7, Color.VIOLET ) };
    public static final LinearGradient BUTTON_GRADIENT = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
            BUTTON_ETAPES );
    public static final Font BUTTON_TEXT = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 62 );
    public static final Effect BUTTON_EFFECT = new DropShadow( 17.5, 10, 10, Color.DARKSLATEGREY );
}
