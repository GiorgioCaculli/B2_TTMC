package be.helha.ttmc.ui.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/*
 * Classe qui sert à traduire les chaînes de caractères présentes sur l'interface graphique.
 * Dépendamment de la langue, la sous-classe propre à la langue va représenter les
 * chaînes de caractères adequates sur l'interface graphique.
 */
public abstract class Language
{
    private String language; // La langue choisie
    private String country; // Le pays qui parle la langue choisie
    private Locale locale; // Le locale qui sera utilisé lors du lancement du programme
    private ResourceBundle rb; // Le bundle à partir duquel les chaînes seront récupérées
    
    /*
     * Le constructeur prend en paramètre:
     * Une chaîne de caractère propre à la langue choisie
     * Une chaîne de caractère propre au pays qui parle la langue choisie
     * Un "locale" sera généré à partir de la langue et du pays
     * Le ResourceBundle propre à au locale sera initialisé
     */
    public Language( String language, String country )
    {
        this.language = language;
        this.country = country;
        this.locale = new Locale( language, country );
        rb = ResourceBundle.getBundle( "be.helha.ttmc.res.strings", locale );
    }
    
    /*
     * Fonction qui permettra aux sous-classes de récupérer la chaîne de caractère
     * propre à la langue
     */
    public abstract String getString( String id );
    
    /*
     * On souhaite récupérer dans les sous-classes,
     * le ResourceBundle instancié avec le locale correct.
     */
    public ResourceBundle getResourceBundle()
    {
        return rb;
    }
    
    /*
     * Ce getter va nous permettre de récupérer la langue choisie
     */
    public String getLanguage()
    {
        return language;
    }
    
    /*
     * Ce getter va nous permettre de récupérer le pays choisi
     */
    public String getCountry()
    {
        return country;
    }
    
    /*
     * Ce getter va nous permettre de récupérer le locale instancié
     */
    public Locale getLocale()
    {
        return locale;
    }
}
