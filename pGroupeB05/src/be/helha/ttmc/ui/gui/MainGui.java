package be.helha.ttmc.ui.gui;

import be.helha.ttmc.model.BasicCard;
import be.helha.ttmc.model.Deck;
import be.helha.ttmc.serialization.Serialization;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;

public class MainGui extends Application
{
    private Stage stage;
    private int id;
    private static Deck d;
    private BasicCard bc;

    public void setDeck( Deck de )
    {
        d = de;
    }

    @Override
    public void start( Stage primaryStage )
    {
        stage = primaryStage;
        stage.setResizable( false );
        stage.setHeight( 750 );
        stage.setWidth( 750 );
        Scene scene = MenuPrinci();
        primaryStage.setScene( scene );
        primaryStage.getIcons().add( new Image( "be/helha/ttmc/assets/images/paw.png" ) );
        primaryStage.show();
        primaryStage.setTitle( "What do you know about it?" );
    }

    public static boolean ajoutCarteDeck( BasicCard bc )
    {
        return d.add( bc );
    }

    public Scene MenuPrinci()
    {
        MenuPrincipal mp = new MenuPrincipal();
        // gestion du boutton permettant d'acceder a la gestion des cartes

        mp.getBtnGerer().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                stage.setScene( MenuGestion() );

            }
        } );

        mp.getBtnQuitter().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                Alert alert = new Alert( AlertType.CONFIRMATION, "Leave the game ?" );
                if( alert.showAndWait().get() == ButtonType.OK )
                {
                    stage.close();
                }

            }
        } );

        mp.getBtnJouer().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                if( d.getCards().size() == 0 )
                {
                    Alert alert = new Alert( AlertType.ERROR,
                            "Aucun deck de question trouve!\n Veuillez ajouter des cartes depuis l'admin panel!" );
                    alert.showAndWait();
                }else
                    stage.setScene( JouerChoix() );

            }
        } );
        return new Scene( mp );
    }

    public Scene AjoutCarte()
    {
        FenetreAjout fa = new FenetreAjout();
        fa.getButtonReturn().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent event )
            {
                Serialization.saveGame( d );
                stage.setScene( MenuGestion() );

            }
        } );

        return new Scene( fa );
    }

    protected Scene MenuGestion()
    {
        MenuAjout ma = new MenuAjout();
        ma.getBtnAjout().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                stage.setScene( AjoutCarte() );

            }
        } );

        ma.getBtnListe().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                stage.setScene( ListeCartes() );

            }
        } );

        ma.getBtnRetour().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                stage.setScene( MenuPrinci() );

            }
        } );
        return new Scene( ma );
    }

    public Scene ListeCartes()
    {
        ListeCarte lc = new ListeCarte();
        lc.getBtnReturn().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent event )
            {
                stage.setScene( MenuGestion() );

            }
        } );
        return new Scene( lc );
    }

    public Scene JouerChoix()
    {
        JouerChoixQuestion jcq = new JouerChoixQuestion();
        bc = GenererDeck();

        jcq.setLblTheme( bc.getTheme().toString() );
        jcq.setLblSujet( bc.getSubject() );
        // Click sur la question lvl 1
        jcq.getChoix().get( 0 ).setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                id = 0;
                stage.setScene( JeuReponses() );

            }
        } );
        // click sur la question lvl 2
        jcq.getChoix().get( 1 ).setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                id = 1;
                stage.setScene( JeuReponses() );

            }
        } );
        // click sur la question lvl 3
        jcq.getChoix().get( 2 ).setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                id = 2;
                stage.setScene( JeuReponses() );

            }
        } );
        // click sur la question lvl 4
        jcq.getChoix().get( 3 ).setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                id = 3;
                stage.setScene( JeuReponses() );

            }
        } );
        return new Scene( jcq );
    }

    public Scene JeuReponses()
    {
        JeuReponse jr = new JeuReponse();
        jr.setLblQuestion( bc.getQuestions().get( id ).getChallenge() );
        jr.setLblTheme( bc.getTheme().toString() );
        jr.setLblSujet( bc.getSubject() );
        jr.getBtnVal().setOnAction( new EventHandler< ActionEvent >()
        {

            @Override
            public void handle( ActionEvent arg0 )
            {
                if( jr.getTxtRep().getText().equalsIgnoreCase( bc.getQuestions().get( id ).getAnswer() ) )
                {
                    Alert alert = new Alert( AlertType.INFORMATION, "Brava tu as reussi !" );
                    alert.setTitle( "Resultats" );
                    String path = "/be/helha/ttmc/assets/images/banana.gif";
                    ImageView icon = new ImageView( path );
                    icon.setFitHeight( 64 );
                    icon.setFitWidth( 64 );
                    alert.getDialogPane().setGraphic( icon );
                    alert.setHeaderText( null );
                    alert.showAndWait();
                }else
                {
                    Alert alert = new Alert( AlertType.INFORMATION, "La reponse etait : "
                            + bc.getQuestions().get( id ).getAnswer() );
                    alert.setTitle( "Resultats" );
                    ImageView icon = new ImageView( "/be/helha/ttmc/assets/images/sonicPleure.gif" );
                    icon.setFitHeight( 64 );
                    icon.setFitWidth( 64 );
                    alert.getDialogPane().setGraphic( icon );
                    alert.setHeaderText( null );
                    alert.showAndWait();
                    System.out.println( "nope" );
                }
                stage.setScene( JouerChoix() );
            }
        } );

        return new Scene( jr );
    }

    public BasicCard GenererDeck()
    {
        bc = d.tirerCarte();
        return bc;
    }

    public MainGui()
    {
    }
}
