package be.helha.ttmc.ui;

import java.util.ResourceBundle;

public class GUIConstant
{
    private static final Settings settings = new Settings( "application.properties" );
    private static final ResourceBundle rb = ResourceBundle.getBundle( "be.helha.ttmc.res.strings",
            settings.getLocale() );
    public static final String TITLE = rb.getString( "title" );
    
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
    
    // BUTTONS GENERIC
    public static final String BUTTON_RETURN = rb.getString( "button_return" );
    
    // DIALOG exit
    public static final String DIALOG_EXIT_CONTENT = rb.getString( "dialog_exit_content" );
}
