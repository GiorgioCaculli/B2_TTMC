package be.helha.ttmc.ui.internationalization;

public class English extends Language
{
    public English()
    {
        super( "en", "UK" );
    }
    
    @Override
    public String getString( String id )
    {
        return getResourceBundle().getString( id );
    }

}
