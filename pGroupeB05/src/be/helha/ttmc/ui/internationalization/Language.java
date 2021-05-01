package be.helha.ttmc.ui.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class Language
{
    private String language;
    private String country;
    private Locale locale;
    private ResourceBundle rb;
    
    public Language( String language, String country )
    {
        this.language = language;
        this.country = country;
        this.locale = new Locale( language, country );
        rb = ResourceBundle.getBundle( "be.helha.ttmc.res.strings", locale );
    }
    
    public abstract String getString( String id );
    
    public ResourceBundle getResourceBundle()
    {
        return rb;
    }
    
    public String getLanguage()
    {
        return language;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public Locale getLocale()
    {
        return locale;
    }
}
