package be.helha.ttmc.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Enum meant to contain the accepted themes that can be given to the questions.
 * 
 * @author Giorgio CACULLI LA196672, Guillaume LAMBERT LA198116, Tanguy TAMINIAU
 *         LA199566
 * 
 * @version 1.0
 * 
 * @see BasicCard
 * @see Question
 */
public enum Theme implements Serializable
{
    IMPROBABLE, INFORMATICS, PLEASURE, SCHOOL;

    /**
     * Function meant to be used in order to retrieve the color related to the
     * theme.
     * 
     * @return HTML code used to color the background of the card.
     */
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
