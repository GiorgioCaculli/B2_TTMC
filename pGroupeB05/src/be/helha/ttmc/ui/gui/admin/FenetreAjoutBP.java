package be.helha.ttmc.ui.gui.admin;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Question;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.serialization.Serialization;
import be.helha.ttmc.ui.GUIConstant;
import be.helha.ttmc.ui.Settings;
import be.helha.ttmc.ui.gui.admin.MenuAdminBP.MenuAdminMainVB;

public class FenetreAjoutBP extends BorderPane
{
    private Label lblChal, lblAns;
    private List< Label > labelsCha;
    private List< TextField > textsfieldCha;
    private List< TextField > textsfieldAns;
    private Button buttonsOK, buttonCancel, buttonReturn;
    private Label lblTheme, lblAuthor, lblSubject;
    private TextField txtAuthor, txtSubject;
    private ComboBox< String > cb;
    private int minChallenges = 4;
    private Deck d;
    private Settings s;

    public FenetreAjoutBP( Deck d, Settings s )
    {
        this.d = d;
        this.s = s;
        // creation de la partie superieure de la fenetre
        /*AnchorPane anch = new AnchorPane();

        AnchorPane.setTopAnchor( getLblTheme(), 4. );
        AnchorPane.setLeftAnchor( getLblTheme(), 10. );

        AnchorPane.setLeftAnchor( getCb(), 60. );

        AnchorPane.setTopAnchor( getLblAuthor(), 4. );
        AnchorPane.setLeftAnchor( getLblAuthor(), ( double ) s.getWidth() / 3 );

        AnchorPane.setLeftAnchor( getTxtAuthor(), ( double ) s.getWidth() / 2 );
        AnchorPane.setRightAnchor( getTxtAuthor(), 10. );

        AnchorPane.setTopAnchor( getLblSubject(), 39. );
        AnchorPane.setLeftAnchor( getLblSubject(), 7. );

        AnchorPane.setTopAnchor( getTxtSubject(), 35. );
        AnchorPane.setLeftAnchor( getTxtSubject(), 60. );
        AnchorPane.setRightAnchor( getTxtSubject(), 10. );*/
        
        VBox cardAuthorThemeSubjectBox = new VBox();
        
        HBox authorThemeBox = new HBox();
        authorThemeBox.getChildren().add( getLblTheme() );
        authorThemeBox.getChildren().add( getCb() );
        authorThemeBox.getChildren().add( getLblAuthor() );
        authorThemeBox.getChildren().add( getTxtAuthor() );
        
        HBox subjectBox = new HBox();
        subjectBox.getChildren().add( getLblSubject() );
        subjectBox.getChildren().add( getTxtSubject() );
        
        cardAuthorThemeSubjectBox.getChildren().add( authorThemeBox );
        cardAuthorThemeSubjectBox.getChildren().add( subjectBox );

        /*anch.getChildren().addAll( getLblTheme(), getCb(), getLblAuthor(), getTxtAuthor(), getLblSubject(),
                getTxtSubject() );*/

        // creation de la partie inferieure de la fenetre
        GridPane grid = new GridPane();
        grid.setPadding( new Insets( 10., 10., 10., 10. ) );
        grid.setHgap( 5. );
        grid.setVgap( 10. );
        int nbcols = 11;
        for ( int i = 0; i < nbcols; i++ )
        {
            ColumnConstraints colConstr = new ColumnConstraints();
            colConstr.setPercentWidth( 100. / nbcols );
            grid.getColumnConstraints().add( colConstr );
        }
        // ajout des labels de legendes
        grid.add( getLblChal(), 0, 1 );
        grid.add( getLblAns(), 7, 1 );

        for ( int i = 1; i <= minChallenges; i++ )
        {
            // ajout des labels pour les niveaux de questions
            grid.add( getLabelsCha().get( i - 1 ), 0, i + 1 );

            // ajout des champs de textes des questions
            grid.add( getTextsfieldCha().get( i - 1 ), 1, i + 1, 6, 1 );

            // ajout des champs de textes des reponses
            grid.add( getTextsfieldAns().get( i - 1 ), 7, i + 1, 5, 1 );

        }

        // ajout des bouttons
        grid.add( getButtonReturn(), 8, minChallenges + 3 );
        grid.add( getButtonCancel(), 9, minChallenges + 3 );
        grid.add( getButtonsOK(), 10, minChallenges + 3 );
        GridPane.setHalignment( getButtonCancel(), HPos.CENTER );
        GridPane.setHalignment( getButtonsOK(), HPos.CENTER );

        // ajout des differents composants dans la borderPane
        VBox vb = new VBox();
        vb.getChildren().addAll( cardAuthorThemeSubjectBox, grid );
        vb.setAlignment( Pos.CENTER );
        setCenter( vb );
        setStyle( GUIConstant.WINDOW_STYLE );
    }

    public List< Label > getLabelsCha()
    {

        if ( this.labelsCha == null )
        {
            labelsCha = new ArrayList<>();
            for ( int i = 1; i <= minChallenges; i++ )
            {
                this.labelsCha.add( new Label( i + " :" ) );
            }
        }
        return labelsCha;
    }

    public List< TextField > getTextsfieldCha()
    {
        if ( this.textsfieldCha == null )
        {
            textsfieldCha = new ArrayList<>();
            for ( int i = 1; i <= minChallenges; i++ )
            {
                this.textsfieldCha.add( new TextField() );
            }
        }
        return textsfieldCha;
    }

    public List< TextField > getTextsfieldAns()
    {
        if ( this.textsfieldAns == null )
        {
            textsfieldAns = new ArrayList<>();
            for ( int i = 1; i <= minChallenges; i++ )
            {
                this.textsfieldAns.add( new TextField() );
            }
        }
        return textsfieldAns;
    }

    public Button getButtonReturn()
    {
        if ( buttonReturn == null )
        {
            buttonReturn = new Button( "Return" );
            buttonReturn.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    Serialization.saveGame( d );
                    MenuAdminBP mabp = ( MenuAdminBP ) getParent().getParent();
                    mabp.setVisibleNode( MenuAdminMainVB.class.getSimpleName() );
                }
            } );
        }
        return buttonReturn;
    }

    public Button getButtonsOK()
    {
        if ( buttonsOK == null )
        {
            buttonsOK = new Button( "Add" );
            buttonsOK.setOnAction( new EventHandler< ActionEvent >()
            {

                public void handle( ActionEvent arg0 )
                {

                    // verification que tous les champs soient remplis
                    if ( !getTxtAuthor().getText().isEmpty() && !getTxtSubject().getText().isEmpty() )
                    {

                        int test = 0;
                        for ( int i = 0; i < getMinChallenges(); i++ )
                        {
                            if ( !getTextsfieldAns().get( i ).getText().isEmpty()
                                    && !getTextsfieldCha().get( i ).getText().isEmpty() )
                            {
                                test++;
                            }
                        }
                        if ( test == getMinChallenges() )
                        {
                            // ajout de la carte apres verification
                            BasicCard b = new BasicCard( getTxtAuthor().getText(), Theme.valueOf( getCb().getValue() ),
                                    getTxtSubject().getText() );
                            for ( int i = 0; i < getMinChallenges(); i++ )
                            {
                                Question q = new Question( getTxtAuthor().getText(),
                                        Theme.valueOf( getCb().getValue() ), getTxtSubject().getText(),
                                        getTextsfieldCha().get( i ).getText(), getTextsfieldAns().get( i ).getText() );
                                if ( !b.add( q ) )
                                {
                                    Alert alert = new Alert( AlertType.ERROR,
                                            "One of the questions is already present on the card!" );
                                    alert.showAndWait();
                                    break;
                                }

                            }
                            if ( !d.add( b ) )
                            {

                                Alert alert = new Alert( AlertType.ERROR, "The card already exists in the deck!" );
                                alert.showAndWait();
                                return;
                            }
                            Alert alert = new Alert( AlertType.INFORMATION, "The card has been added to the deck!" );
                            alert.showAndWait();
                            return;
                        }

                    } // affichage d'une fenetre d'information quand les champs ne sont pas complets

                    Alert alert = new Alert( AlertType.WARNING, "At lest one field is empty! Please, fill it!" );
                    alert.showAndWait();

                }
            } );
        }
        return buttonsOK;
    }

    public Button getButtonCancel()
    {
        if ( buttonCancel == null )
        {
            buttonCancel = new Button( "Clean" );
            buttonCancel.setOnAction( new EventHandler< ActionEvent >()
            {

                @Override
                public void handle( ActionEvent arg0 )
                {
                    Alert alert = new Alert( AlertType.CONFIRMATION, "Do you want to empty the fields?" );
                    if ( alert.showAndWait().get() == ButtonType.OK )
                    {
                        getTxtAuthor().clear();
                        getTxtSubject().clear();
                        for ( int i = 0; i < getMinChallenges(); i++ )
                        {
                            getTextsfieldAns().get( i ).clear();
                            getTextsfieldCha().get( i ).clear();
                        }
                    }

                }
            } );
        }
        return buttonCancel;
    }

    public void setMinChallenges( int newVal )
    {
        if ( newVal >= 4 )
            this.minChallenges = newVal;
    }

    public Label getLblTheme()
    {
        if ( lblTheme == null )
            lblTheme = new Label( "Theme :" );
        return lblTheme;
    }

    public Label getLblAuthor()
    {
        if ( lblAuthor == null )
            lblAuthor = new Label( "Author :" );
        return lblAuthor;
    }

    public Label getLblSubject()
    {
        if ( lblSubject == null )
            lblSubject = new Label( "Subject :" );
        return lblSubject;
    }

    public TextField getTxtAuthor()
    {
        if ( txtAuthor == null )
        {
            txtAuthor = new TextField();
            txtAuthor.setMaxWidth( Double.MAX_VALUE );
            txtAuthor.setMinWidth( s.getWidth() / 2 );
        }
        return txtAuthor;
    }

    public TextField getTxtSubject()
    {
        if ( txtSubject == null )
            txtSubject = new TextField();
        return txtSubject;
    }

    public ComboBox< String > getCb()
    {
        if ( cb == null )
        {
            cb = new ComboBox<>();
            for ( Theme t : Theme.values() )
            {
                cb.getItems().addAll( t.toString() );
            }
            cb.setValue( Theme.IMPROBABLE.toString() );
        }
        return cb;
    }

    public Label getLblChal()
    {
        if ( lblChal == null )
            lblChal = new Label( "Challenge :" );
        return lblChal;
    }

    public Label getLblAns()
    {
        if ( lblAns == null )
            lblAns = new Label( "Answers :" );
        return lblAns;
    }

    public int getMinChallenges()
    {
        return minChallenges;
    }

    public FenetreAjoutBP( int test )
    {

        setMinChallenges( test );

        // creation de la partie superieure de la fenetre
        AnchorPane anch = new AnchorPane();

        AnchorPane.setTopAnchor( getLblTheme(), 4. );
        AnchorPane.setLeftAnchor( getLblTheme(), 10. );

        AnchorPane.setLeftAnchor( getCb(), 60. );

        AnchorPane.setTopAnchor( getLblAuthor(), 4. );
        AnchorPane.setLeftAnchor( getLblAuthor(), 190. );

        AnchorPane.setLeftAnchor( getTxtAuthor(), 240. );
        AnchorPane.setRightAnchor( getTxtAuthor(), 10. );

        AnchorPane.setTopAnchor( getLblSubject(), 39. );
        AnchorPane.setLeftAnchor( getLblSubject(), 7. );

        AnchorPane.setTopAnchor( getTxtSubject(), 35. );
        AnchorPane.setLeftAnchor( getTxtSubject(), 60. );
        AnchorPane.setRightAnchor( getTxtSubject(), 10. );

        anch.getChildren().addAll( getLblTheme(), getCb(), getLblAuthor(), getTxtAuthor(), getLblSubject(),
                getTxtSubject() );

        // creation de la partie inferieure de la fenetre
        GridPane grid = new GridPane();
        grid.setPadding( new Insets( 10., 10., 10., 10. ) );
        grid.setHgap( 5. );
        grid.setVgap( 10. );
        int nbcols = 11;
        for ( int i = 0; i < nbcols; i++ )
        {
            ColumnConstraints colConstr = new ColumnConstraints();
            colConstr.setPercentWidth( 100. / nbcols );
            grid.getColumnConstraints().add( colConstr );
        }
        // ajout des labels de legendes
        grid.add( getLblChal(), 0, 1 );
        grid.add( getLblAns(), 7, 1 );

        for ( int i = 1; i <= minChallenges; i++ )
        {
            // ajout des labels pour les niveaux de questions
            grid.add( getLabelsCha().get( i - 1 ), 0, i + 1 );

            // ajout des champs de textes des questions
            grid.add( getTextsfieldCha().get( i - 1 ), 1, i + 1, 6, 1 );

            // ajout des champs de textes des reponses
            grid.add( getTextsfieldAns().get( i - 1 ), 7, i + 1, 5, 1 );

        }

        // ajout des bouttons
        grid.add( getButtonCancel(), 9, minChallenges + 3 );
        grid.add( getButtonsOK(), 10, minChallenges + 3 );
        GridPane.setHalignment( getButtonCancel(), HPos.CENTER );
        GridPane.setHalignment( getButtonsOK(), HPos.CENTER );

        // ajout des differents composants dans la borderPane
        VBox vb = new VBox();
        vb.getChildren().addAll( anch, grid );
        this.setCenter( vb );
    }

}
