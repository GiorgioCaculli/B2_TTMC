package be.helha.ttmc.ui.internationalization;

public class Japanese extends Language
{
    public Japanese()
    {
        super( "ja", "JP" );
    }

    @Override
    public String getString( String id )
    {
        return getResourceBundle().getString( id );
    }

}
