package be.helha.ttmc.ui.gui.util;

import java.util.List;

public class IteratorMusic
{
    private List< String > listMusic;
    private int index;

    /*
     * Constructeur de l'itérator 
     * 
     */
   
    public IteratorMusic( List< String > le )
    {
        super();
        this.listMusic = le;
        index = 0;
    }
    
    /*
     * Méthode permettant de récupérer la taille de la liste de l'itérator 
     */
    public int listeSize()
    {
        return listMusic.size();
    }

    /* 
     * méthode permettant de renvoyer l'objet avec lequel on est entrain d'interagir
     */
    
    public String item()
    {
    	// Instruction permettant de boucler et d'éviter de dépasser la taille de la liste  
        if ( index == listMusic.size() )
        {
            reset();
        }

        return listMusic.get( index );
    }

    
    /*
     *	Méthode permettant de passer à l'objet suivant
     */    
    
    public void next()
    {
        index++;
    }

    
    /*
     * Méthode permettant de revenir à l'objet précédant
     */
    public void preview()
    {
        index--;
    }
    
    /*
     * Méthode permettant de revenir au début de la liste
     */

    public void reset()
    {
        index = 0;
    }

    /*
     * Méthode permettant de récupérer la position, dans la liste, sur laquelle on est
     */
    
    public int getIndex()
    {
        return index;
    }
}
