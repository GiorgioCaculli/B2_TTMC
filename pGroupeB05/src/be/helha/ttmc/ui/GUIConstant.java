package be.helha.ttmc.ui;

import java.util.ResourceBundle;

public class GUIConstant
{
    private static final Settings settings = new Settings( "application.properties" );
    private static final ResourceBundle rb = ResourceBundle.getBundle( "be.helha.ttmc.res.strings",
            settings.getLocale() );
    public static final String TITLE = rb.getString( "title" );
    
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
}
