package be.helha.ttmc.ui;

import java.util.ResourceBundle;

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
    public static final String BUTTON_STYLE = "-fx-background-color: plum;" + "-fx-border-radius: 40 40 40 40;"
            + "-fx-background-radius: 40 40 40 40;";
    public static final Stop[] BUTTON_ETAPES =
    { new Stop( 0, Color.BLUEVIOLET ), new Stop( 0.3, Color.ROYALBLUE ), new Stop( 0.7, Color.LIGHTSTEELBLUE ) };
    public static final LinearGradient BUTTON_GRADIENT = new LinearGradient( 0, 1, 0, 0, true, CycleMethod.NO_CYCLE,
            BUTTON_ETAPES );
    public static final Font BUTTON_TEXT = Font.font( "Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 75 );
    public static final Effect BUTTON_EFFECT = new DropShadow( 25, 13, 13, Color.DARKSLATEGREY );
}
