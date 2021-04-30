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
    private static final Settings settings = new Settings( "application.properties" );
    private static final ResourceBundle rb = ResourceBundle.getBundle( "be.helha.ttmc.res.strings",
            settings.getLocale() );
    public static final String TITLE = rb.getString( "title" );

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

    // BUTTONS GENERIC
    public static final String BUTTON_RETURN = rb.getString( "button_return" );

    // BUTTONS MAIN MENU
    public static final String BUTTON_PLAY = rb.getString( "button_play" );
    public static final String BUTTON_SETTINGS = rb.getString( "button_settings" );
    public static final String BUTTON_LEAVE_GAME = rb.getString( "button_leave_game" );
    public static final String BUTTON_ADMIN_PANEL = rb.getString( "button_admin_panel" );
    public static final String BUTTON_CREDITS = rb.getString( "button_credits" );

    // BUTTONS MENU PAUSE
    public static final String BUTTON_RESUME = rb.getString( "button_resume" );
    public static final String BUTTON_BACK_TO_MAIN_MENU = rb.getString( "button_back_to_main_menu" );
    public static final String BUTTON_BACK_TO_LOBBY = rb.getString( "button_back_to_lobby" );

    // BUTTONS PLAY MENU
    public static final String BUTTON_SINGLE_PLAYER = rb.getString( "button_single_player" );
    public static final String BUTTON_MULTIPLAYER = rb.getString( "button_multiplayer" );
    public static final String BUTTON_RULES_ACCEPT = rb.getString( "button_rules_accept" );
    public static final String BUTTON_NEW_GAME = rb.getString( "button_new_game" );
    public static final String BUTTON_LOAD_GAME = rb.getString( "button_load_game" );

    // BUTTON ADMIN MENU
    public static final String BUTTON_ADD_CARD = rb.getString( "button_add_card" );
    public static final String BUTTON_LIST_CARD = rb.getString( "button_list_card" );
    public static final String BUTTON_IMPORT_DECK = rb.getString( "button_import_deck" );
    public static final String BUTTON_EXPORT_DECK = rb.getString( "button_export_deck" );

    // DIALOG play
    public static final String DIALOG_PLAY_TITLE = rb.getString( "dialog_play_title" );
    public static final String DIALOG_PLAY_CONTENT = rb.getString( "dialog_play_content" );
    public static final String DIALOG_PLAY_MESSAGE = rb.getString( "dialog_play_message" );

    // DIALOG exit
    public static final String DIALOG_EXIT_CONTENT = rb.getString( "dialog_exit_content" );

    // DIALOG admin
    public static final String DIALOG_LOGIN_TITLE = rb.getString( "dialog_login_title" );
    public static final String DIALOG_LOGIN_CONTENT = rb.getString( "dialog_login_content" );
    public static final String DIALOG_LOGIN_USERNAME = rb.getString( "dialog_login_username" );
    public static final String DIALOG_LOGIN_PASSWORD = rb.getString( "dialog_login_password" );
    public static final String DIALOG_LOGIN_ACCESS_GRANTED = rb.getString( "dialog_login_access_granted" );
    public static final String DIALOG_LOGIN_ACCESS_DENIED = rb.getString( "dialog_login_access_denied" );
    public static final String DIALOG_LOGIN_BUTTON = rb.getString( "dialog_login_button" );

    // CREDITS strings
    public static final String CREDITS_TEAM = rb.getString( "credits_team" );
}
