package be.helha.ttmc.ui.internationalization;

public class French extends Language
{
    public French()
    {
        super( "fr", "BE" );
    }

    @Override
    public String getString( String id )
    {
        return getResourceBundle().getString( id );
    }

}
