package be.helha.ttmc.ui.gui;

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
import javafx.scene.layout.VBox;
import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.model.Question;
import be.helha.ttmc.model.Theme;
import be.helha.ttmc.serialization.Serialization;

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

    public FenetreAjoutBP( Deck d )
    {
        this.d = d;
        // creation de la partie sup�rieure de la fen�tre
        AnchorPane anch = new AnchorPane();

        anch.setTopAnchor( getLblTheme(), 4. );
        anch.setLeftAnchor( getLblTheme(), 10. );

        anch.setLeftAnchor( getCb(), 60. );

        anch.setTopAnchor( getLblAuthor(), 4. );
        anch.setLeftAnchor( getLblAuthor(), 190. );

        anch.setLeftAnchor( getTxtAuthor(), 240. );
        anch.setRightAnchor( getTxtAuthor(), 10. );

        anch.setTopAnchor( getLblSubject(), 39. );
        anch.setLeftAnchor( getLblSubject(), 7. );

        anch.setTopAnchor( getTxtSubject(), 35. );
        anch.setLeftAnchor( getTxtSubject(), 60. );
        anch.setRightAnchor( getTxtSubject(), 10. );

        anch.getChildren().addAll( getLblTheme(), getCb(), getLblAuthor(), getTxtAuthor(), getLblSubject(),
                getTxtSubject() );

        // cr�ation de la partie inf�rieure de la fen�tre
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
        // ajout des labels de l�gendes
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
        vb.getChildren().addAll( anch, grid );
        vb.setAlignment( Pos.CENTER );
        this.setCenter( vb );
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
                    getParent().getChildrenUnmodifiable().get( 3 ).setVisible( false );
                    getParent().getChildrenUnmodifiable().get( 2 ).setVisible( true );
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
                                            "Une des questions des d�j� pr�sente sur la carte!" );
                                    alert.showAndWait();
                                    break;
                                }

                            }
                            if ( !d.add( b ) )
                            {

                                Alert alert = new Alert( AlertType.ERROR, "La carte est deja presente dans le deck!" );
                                alert.showAndWait();
                                return;
                            }
                            Alert alert = new Alert( AlertType.INFORMATION, "La carte a bien ete rajoutee au deck!" );
                            alert.showAndWait();
                            return;
                        }

                    } // affichage d'une fenetre d'information quand les champs ne sont pas complets

                    Alert alert = new Alert( AlertType.WARNING, "Au moins un champ est vide! Veuillez le remplir !" );
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
                    Alert alert = new Alert( AlertType.CONFIRMATION, "Voulez-vous videz les champs ?" );
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
            txtAuthor = new TextField();
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

        // cr�ation de la partie sup�rieure de la fen�tre
        AnchorPane anch = new AnchorPane();

        anch.setTopAnchor( getLblTheme(), 4. );
        anch.setLeftAnchor( getLblTheme(), 10. );

        anch.setLeftAnchor( getCb(), 60. );

        anch.setTopAnchor( getLblAuthor(), 4. );
        anch.setLeftAnchor( getLblAuthor(), 190. );

        anch.setLeftAnchor( getTxtAuthor(), 240. );
        anch.setRightAnchor( getTxtAuthor(), 10. );

        anch.setTopAnchor( getLblSubject(), 39. );
        anch.setLeftAnchor( getLblSubject(), 7. );

        anch.setTopAnchor( getTxtSubject(), 35. );
        anch.setLeftAnchor( getTxtSubject(), 60. );
        anch.setRightAnchor( getTxtSubject(), 10. );

        anch.getChildren().addAll( getLblTheme(), getCb(), getLblAuthor(), getTxtAuthor(), getLblSubject(),
                getTxtSubject() );

        // cr�ation de la partie inf�rieure de la fen�tre
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
        // ajout des labels de l�gendes
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

        // ajout des diff�rents composants dans la borderPane
        VBox vb = new VBox();
        vb.getChildren().addAll( anch, grid );
        this.setCenter( vb );
    }

}
