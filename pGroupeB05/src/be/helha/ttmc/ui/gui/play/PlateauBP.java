package be.helha.ttmc.ui.gui.play;

import java.util.ArrayList;
import java.util.List;

import be.helha.ttmc.model.Deck;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauBP extends BorderPane
{
    private int nbCases;
    private List< Rectangle > cases;
    private List< Label > num;
    private PionCircle pion;

    private final double WIDTH_RECT = 35.;
    private final double HEIGHT_RECT = 30.;
    private final double MOUV_X = WIDTH_RECT + 1;
    private final double MOUV_Y = HEIGHT_RECT + 1;

    public PlateauBP( Deck d )
    {
        nbCases = d.getCards().size();
        AnchorPane anch = new AnchorPane();
        getCases();
        getNum();
        int test = 0;
        int rangY = 0;
        String text;
        for ( int i = 0; i < cases.size(); i++ )
        {
            double mouvX = ( i + 1 ) * MOUV_X;
            text = ( i + 1 ) + "";
            Label lab = new Label( text );
            if ( ( i + 1 ) * MOUV_X > 790 - WIDTH_RECT * 3 )
            {
                if ( ( int ) ( ( ( i + 1 ) * MOUV_X ) / ( 790 - WIDTH_RECT * 3 ) ) > rangY )
                {
                    rangY++;
                    test = 0;
                }

                mouvX = ( test + 1 ) * MOUV_X;
                test++;
            }
            double mouvY = rangY * MOUV_Y;
            getCases().get( i ).setY( mouvY );
            lab.setTranslateY( mouvY );

            if ( i == d.getCards().size() - 1 )
                lab.setText( text + "\nFinish" );
            lab.setLayoutX( mouvX );
            getNum().add( lab );
            getCases().get( i ).setX( mouvX );
            anch.getChildren().addAll( getCases().get( i ), getNum().get( i ) );
        }
        anch.getChildren().add( getPion() );
        setCenter( anch );
    }

    public PionCircle getPion()
    {
        if ( pion == null )
        {
            pion = new PionCircle( getCases().get( 0 ).getX() + WIDTH_RECT / 2,
                    getCases().get( 0 ).getY() + HEIGHT_RECT / 2, WIDTH_RECT / 3 );
        }
        return pion;
    }

    public double getHEIGHT_RECT()
    {
        return HEIGHT_RECT;
    }

    protected List< Rectangle > getCases()
    {
        if ( cases == null )
        {
            cases = new ArrayList<>();
            for ( int i = 0; i < nbCases; i++ )
            {
                Rectangle rect = new Rectangle( WIDTH_RECT, HEIGHT_RECT );
                rect.setStroke( Color.BLUE );
                rect.setFill( Color.ALICEBLUE );
                cases.add( rect );
            }
        }
        return cases;

    }

    public double getWIDTH_RECT()
    {
        return WIDTH_RECT;
    }

    private List< Label > getNum()
    {
        if ( num == null )
            num = new ArrayList<>();
        return num;
    }
}
