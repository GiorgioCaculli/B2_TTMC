package be.helha.ttmc.ui.gui.play;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PionCircle extends Circle
{
    private Color color;
    public PionCircle( double centerX, double centerY, double radius, Color color )
    {
        super( centerX, centerY, radius );
        this.color = color;
        setFill( color );
    }

    public void setPos( double newX, double newY )
    {
        this.setCenterX( newX );
        this.setCenterY( newY );
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public PionCircle clone()
    {
        return new PionCircle( getCenterX(), getCenterY(), getRadius(), color );
    }
}
