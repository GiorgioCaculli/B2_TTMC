package be.helha.ttmc.model;

import java.util.HashMap;

public enum Theme
{
    IMPROBABLE, INFORMATICS, PLEASURE, SCHOOL;
    
    public HashMap< Theme, String > backgroundColor()
    {
        HashMap< Theme, String > themes = new HashMap< Theme, String >();
        themes.put( IMPROBABLE, "#dda0dd" ); // Mauve
        themes.put( INFORMATICS, "#ffe4c4" ); // Orange
        themes.put( PLEASURE, "#add8e6" ); // Bleu
        themes.put( SCHOOL, "#90ee90" ); // Vert
        return themes;
    }
}
