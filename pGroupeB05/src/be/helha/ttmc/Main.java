package be.helha.ttmc;

import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Menu;

public class Main
{
    public static void main( String args[] )
    {
        System.out.println(
                String.format( "%s by Giorgio Caculli LA196672, Guillaume Lambert LA198116, Tanguy Taminiau LA199566",
                        GUIConstant.TITLE ) );
        new Menu( args );
    }
}
