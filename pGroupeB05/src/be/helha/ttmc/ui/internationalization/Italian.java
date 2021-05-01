package be.helha.ttmc.ui.internationalization;

public class Italian extends Language
{
    public Italian()
    {
        super( "it", "IT" );
    }

    @Override
    public String getString( String id )
    {
        return getResourceBundle().getString( id );
    }

}
