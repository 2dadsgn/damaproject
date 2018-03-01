package GUI;


import game.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.NotSerializableException;

//LA CLASSSE WINDOW ESTENDE LA CLASSE APPLICATION E AVVIA L'APPLICAZIONE
public class window extends Application  {

    Label ins_nome=new Label("Insert player's name");
    TextField ins_nome_in=new TextField();
    Label mod=new Label("Chose diffculty");
    RadioButton easy=new RadioButton("easy");
    RadioButton hard=new RadioButton("hard");
    Button play=new Button("PLAY");
    ToggleGroup gruppo=new ToggleGroup();
     Stage pregame;
    String player;
    pedina array_pedine[];
    Button stats=new Button("Stats");
    Scacchiera.Memento mem;
    String arraystring[]=new String[10];
    Classifica classifica=new Classifica();


    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage stage){
       Stage pregame=new Stage();
        pregame.setTitle("Dama");


        //CREAZIONE PANNELLO E PROPRIETÀ
        FlowPane pane=new FlowPane();
        Insets ins=new Insets(00,00.0,0.0,100.0);
        pane.setPadding(ins);
        pane.setMargin(ins_nome,ins);

        //Acqusizione delle statistiche
        try{
            if(new File("stats","Classifica.ser").exists()) {
                classifica.restore_stats();
            }
            else {
                classifica.save_stats();
            }
        }
        catch (IOException exc){
            exc.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        };

        for(int q=0;q<classifica.getIndice();q++){
            Player arrayplayer[];
            arrayplayer=new Player[8];
            arrayplayer=classifica.getArrayclassifica();
            arraystring[q]=arrayplayer[q].get_name()+" "+arrayplayer[q].getPartite_vinte();
        }

        //Tasto stats e relativa azione
        stats.setAlignment(Pos.CENTER_LEFT);
        stats.setStyle("-fx-background-color: #455ff4; -fx-font-weight: 700; -fx-font-family: fantasy;-fx-font-size: 17");
        stats.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vertbox=new VBox();
                HBox hbox11=new HBox();
                HBox hbox12=new HBox();
                HBox hbox13=new HBox();
                HBox hbox14=new HBox();
                HBox hbox15=new HBox();
                HBox hbox16=new HBox();
                HBox hbox17=new HBox();
                HBox hbox18=new HBox();
                HBox hbox19=new HBox();

                Label title= new Label("PLAYER'S NAME   /  GAMES WON");
                Label player1= new Label(arraystring[0]);
                Label player2= new Label(arraystring[1]);
                Label player3= new Label(arraystring[2]);
                Label player4= new Label(arraystring[3]);
                Label player5= new Label(arraystring[4]);
                Label player6= new Label(arraystring[5]);
                Label player7= new Label(arraystring[6]);
                Label player8= new Label(arraystring[7]);
                hbox11.getChildren().add(title);
                hbox12.getChildren().add(player1);
                hbox13.getChildren().add(player2);
                hbox14.getChildren().add(player3);
                hbox15.getChildren().add(player4);
                hbox16.getChildren().add(player5);
                hbox17.getChildren().add(player6);
                hbox18.getChildren().add(player7);
                hbox19.getChildren().add(player8);

                hbox11.setAlignment(Pos.TOP_CENTER);
                hbox12.setAlignment(Pos.CENTER);
                hbox13.setAlignment(Pos.CENTER);
                hbox14.setAlignment(Pos.CENTER);
                hbox15.setAlignment(Pos.CENTER);
                hbox16.setAlignment(Pos.CENTER);
                hbox17.setAlignment(Pos.CENTER);
                hbox18.setAlignment(Pos.CENTER);
                hbox19.setAlignment(Pos.CENTER);
                vertbox.setPadding(new Insets(10.0,70.0,10.0,100.0));
                vertbox.getChildren().addAll(hbox11,hbox12,hbox13,hbox14,hbox15,hbox16,hbox17,hbox18,hbox19);
                FlowPane pannello_stats=new FlowPane();
                pannello_stats.getChildren().add(vertbox);
                Scene scenastats=new Scene(pannello_stats,400,400);
                Stage statsstage=new Stage();
                statsstage.setScene(scenastats);
                statsstage.show();




            }
        });


        //ASSEGNAZIONE PROPRIETÀ AL TASTO PLAY
        play.setAlignment(Pos.BOTTOM_RIGHT);
        play.setStyle("-fx-background-color: #48f442; -fx-font-weight: 700; -fx-font-family: fantasy;-fx-font-size: 17;");

            play.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

                    player = ins_nome_in.getText();
                    Player giocatore = new Player();
                    giocatore.set_name(player);


                    //----------STAGE DELLA SCACCHIERA-------------------
                    try {

                        String difficoltà;
                        if (easy.isSelected()) {

                            Game.set_difficolta("easy");
                        } else {

                            Game.set_difficolta("hard");
                        }
                        ;

                        //controllo if per determinare se la partita precendente è finita
                        if (Scacchiera.get_partita_precedente() == true) {

                            //creazione dello stage game
                            Stage game = new Stage();
                            game.setTitle(player + " versus AI in modalità " + Game.get_difficolta());


                            //evento per il salvataggio della partita alla chiusura della finestra
                            game.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent windowEvent) {
                                    System.out.println("partita salvata");
                                    Scacchiera.set_partita_precedente();
                                    System.out.println("La partita precedente è finita " + Scacchiera.get_partita_precedente());

                                    //crea memento
                                    mem = Scacchiera.savetomemento();
                                    game.close();

                                    try {

                                        if (Scacchiera.partita_finita() == true) {
                                            if (Scacchiera.getNumero_pedine_rosse() > 0 && Scacchiera.get_mosse_rosso()>0) {
                                                giocatore.increase_partite_vinte();
                                                classifica.insert_giocatore(giocatore);
                                                classifica.save_stats();
                                            }
                                            else{
                                                classifica.insert_giocatore(giocatore);
                                                classifica.save_stats();
                                            };

                                        }

                                    }
                                    catch (ClassNotFoundException cex) {
                                        cex.printStackTrace();
                                    }
                                    catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });


                            //generazione pedine nere
                            pedinanera ped1 = new pedinanera();
                            pedinanera ped2 = new pedinanera();
                            pedinanera ped3 = new pedinanera();
                            pedinanera ped4 = new pedinanera();
                            pedinanera ped5 = new pedinanera();
                            pedinanera ped6 = new pedinanera();
                            pedinanera ped7 = new pedinanera();
                            pedinanera ped8 = new pedinanera();
                            pedinanera ped9 = new pedinanera();
                            pedinanera ped10 = new pedinanera();
                            pedinanera ped11 = new pedinanera();
                            pedinanera ped12 = new pedinanera();

                            //rosse
                            pedinarossa ped13 = new pedinarossa();
                            pedinarossa ped14 = new pedinarossa();
                            pedinarossa ped15 = new pedinarossa();
                            pedinarossa ped16 = new pedinarossa();
                            pedinarossa ped17 = new pedinarossa();
                            pedinarossa ped18 = new pedinarossa();
                            pedinarossa ped19 = new pedinarossa();
                            pedinarossa ped20 = new pedinarossa();
                            pedinarossa ped21 = new pedinarossa();
                            pedinarossa ped22 = new pedinarossa();
                            pedinarossa ped23 = new pedinarossa();
                            pedinarossa ped24 = new pedinarossa();

                            //creazione tasselli neri
                            blackTile bt1 = new blackTile();
                            blackTile bt2 = new blackTile();
                            blackTile bt3 = new blackTile();
                            blackTile bt4 = new blackTile();
                            blackTile bt5 = new blackTile();
                            blackTile bt6 = new blackTile();
                            blackTile bt7 = new blackTile();
                            blackTile bt8 = new blackTile();
                            blackTile bt9 = new blackTile();
                            blackTile bt10 = new blackTile();
                            blackTile bt11 = new blackTile();
                            blackTile bt12 = new blackTile();
                            blackTile bt13 = new blackTile();
                            blackTile bt14 = new blackTile();
                            blackTile bt15 = new blackTile();
                            blackTile bt16 = new blackTile();
                            blackTile bt17 = new blackTile();
                            blackTile bt18 = new blackTile();
                            blackTile bt19 = new blackTile();
                            blackTile bt20 = new blackTile();
                            blackTile bt21 = new blackTile();
                            blackTile bt22 = new blackTile();
                            blackTile bt23 = new blackTile();
                            blackTile bt24 = new blackTile();
                            blackTile bt25 = new blackTile();
                            blackTile bt26 = new blackTile();
                            blackTile bt27 = new blackTile();
                            blackTile bt28 = new blackTile();
                            blackTile bt29 = new blackTile();
                            blackTile bt30 = new blackTile();
                            blackTile bt31 = new blackTile();
                            blackTile bt32 = new blackTile();


                            //inserimento dei due gridpane nello stackpane (con inserimento tasselli neri e pedine solo grafica)
                            Scacchiera.set_scacchiera(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13,
                                    bt14, bt15, bt16, bt17, bt18, bt19, bt20, bt21, bt22, bt23, bt24, bt25, bt26, bt27,
                                    bt28, bt29, bt30, bt31, bt32);
                            GridPane scacchiera_completa = Scacchiera.getGridcerchi(ped1, ped2, ped3, ped4, ped5, ped6,
                                    ped7, ped8, ped9, ped10, ped11, ped12, ped13, ped14, ped15, ped16, ped17, ped18, ped19,
                                    ped20, ped21, ped22, ped23, ped24);

                            //inserimento delle pedine oggetti nella matrice di gioco
                            Scacchiera.ins_pedine(ped1, ped2, ped3, ped4, ped5, ped6, ped7, ped8, ped9, ped10, ped11, ped12, ped13,
                                    ped14, ped15, ped16, ped17, ped18, ped19, ped20, ped21, ped22, ped23, ped24);


                            //avvio della SCENA
                            Scene sceneggiata = new Scene(scacchiera_completa, 400, 400);
                            game.setScene(sceneggiata);
                            game.show();


                            //se computer ha pedine rosse allora muove per primp
                            if (Scacchiera.get_colore_player() == "blue") {
                                Game.set_players_move(true);

                                Timeline timeline = new Timeline(new KeyFrame(
                                        Duration.millis(2000),
                                        ae -> Ai.gioca()));
                                timeline.play();

                            }
                            ;

                            //generazione dame e inserimento in array di riserva della scacchiera
                            Dama_rossa dama1 = new Dama_rossa();
                            Dama_rossa dama2 = new Dama_rossa();
                            Dama_rossa dama3 = new Dama_rossa();
                            Dama_rossa dama4 = new Dama_rossa();
                            Dama_rossa dama5 = new Dama_rossa();
                            Dama_rossa dama6 = new Dama_rossa();
                            Dama_rossa dama7 = new Dama_rossa();
                            Dama_rossa dama8 = new Dama_rossa();
                            Dama_rossa dama9 = new Dama_rossa();
                            Dama_rossa dama10 = new Dama_rossa();
                            Dama_rossa dama11 = new Dama_rossa();
                            Dama_rossa dama12 = new Dama_rossa();

                            Scacchiera.ins_array_dame(dama1, dama2, dama3, dama4, dama5, dama6, dama7, dama8, dama9, dama10, dama11, dama12);
                            dama1.movimento();
                            dama2.movimento();
                            dama3.movimento();
                            dama4.movimento();
                            dama5.movimento();
                            dama6.movimento();
                            dama7.movimento();
                            dama8.movimento();
                            dama9.movimento();
                            dama10.movimento();
                            dama11.movimento();
                            dama12.movimento();


                            //generazione pedine
                            ped13.movimento();
                            ped14.movimento();
                            ped15.movimento();
                            ped16.movimento();
                            ped17.movimento();
                            ped18.movimento();
                            ped19.movimento();
                            ped20.movimento();
                            ped21.movimento();
                            ped22.movimento();
                            ped23.movimento();
                            ped24.movimento();

                            bt1.get_location();
                            bt2.get_location();
                            bt3.get_location();
                            bt4.get_location();
                            bt5.get_location();
                            bt6.get_location();
                            bt7.get_location();
                            bt8.get_location();
                            bt9.get_location();
                            bt10.get_location();
                            bt11.get_location();
                            bt12.get_location();
                            bt13.get_location();
                            bt14.get_location();
                            bt15.get_location();
                            bt16.get_location();
                            bt17.get_location();
                            bt18.get_location();
                            bt19.get_location();
                            bt20.get_location();
                            bt21.get_location();
                            bt22.get_location();
                            bt23.get_location();
                            bt24.get_location();
                            bt25.get_location();
                            bt26.get_location();
                            bt27.get_location();
                            bt28.get_location();
                            bt29.get_location();
                            bt30.get_location();
                            bt31.get_location();
                            bt32.get_location();



                        } else {
                            //creazione dello stage game

                            Stage game = stage;
                            game.setTitle(player + " versus AI in modalità " + Game.get_difficolta());

                            //evento per il salvataggio della partita alla chiusura della finestra
                            game.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                @Override
                                public void handle(WindowEvent windowEvent) {
                                    System.out.println("partita salvata");
                                    Scacchiera.set_partita_precedente();
                                    System.out.println(Scacchiera.get_partita_precedente());

                                    //crea memento
                                    mem = Scacchiera.savetomemento();

                                    //salvataggio del giocatore nella classifica
                                    try {

                                        if (Scacchiera.partita_finita() == true) {
                                            if (Scacchiera.getNumero_pedine_rosse() > 0 && Scacchiera.get_mosse_rosso()>0) {
                                                giocatore.increase_partite_vinte();
                                                classifica.insert_giocatore(giocatore);
                                                classifica.save_stats();
                                            }
                                            else {
                                                classifica.insert_giocatore(giocatore);
                                                classifica.save_stats();
                                            };

                                        }
                                    } catch (ClassNotFoundException cex) {
                                        cex.printStackTrace();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }


                                }
                            });

                            //restore from memento
                            Scacchiera.restorefrommemento(mem);

                            //avvio della SCENA
                            StackPane scacchiera_completa1 = new StackPane();
                            scacchiera_completa1.getChildren().addAll(Scacchiera.getcerchi());
                            Scene sceneggiata1 = new Scene(scacchiera_completa1, 400, 400);
                            game.setScene(sceneggiata1);
                            game.show();
                        }
                        ;

                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }

                    //------------FINE STAGE SCACCHIERA----------------
                }

            });

        //FINE ASSEGNAZIONE AZIONE AL TASTO PLAY


        //ASSEGNAZIONE PROPRIETÀ AL TEXTFIELD GIOCATORE
        ins_nome_in.setStyle("-fx-alignment: center");


        //CREAZIONE DEI BOX PER SISTEMARE GLI ELEMENTI
        VBox vox=new VBox();
        HBox box1=new HBox();
        HBox box2=new HBox();
        HBox box3=new HBox();
        HBox box4=new HBox();
        HBox box5=new HBox();
        HBox box6=new HBox();

        //INSERIMENTO DEI PULSANTI NEL GRUPPO E IMPOSTAZIONE DELL INSETS PER LA SEPARAZIONE
        easy.setToggleGroup(gruppo);
        hard.setToggleGroup(gruppo);
        easy.setSelected(true);
        hard.setSelected(true);
        easy.setPadding(new Insets(0.0,10.0,0.0,0.0));
        hard.setPadding(new Insets(0.0,0.0,0.0,10.0));

        //INSERIMENTO NODI NEI BOX CON RELATIVO PADDING
        box1.getChildren().addAll(ins_nome);
        box5.getChildren().add(ins_nome_in);
        box2.getChildren().add(mod);
        box3.getChildren().addAll(easy,hard);
        box4.getChildren().addAll(stats,box6,play);

        box1.setAlignment(Pos.TOP_CENTER);
        box2.setAlignment(Pos.CENTER);
        box3.setAlignment(Pos.CENTER);
        box4.setAlignment(Pos.CENTER);

        box1.setPadding(new Insets(30.0,20.0,10.0,20.0));
        box2.setPadding(new Insets(10.0,20.0,20.0,20.0));
        box3.setPadding(new Insets(10.0,20.0,20.0,20.0));
        box4.setPadding(new Insets(30.0,20.0,50.0,20.0));
        box5.setPadding(new Insets(10.0,20.0,20.0,20.0));
        box6.setPadding(new Insets(10.0,20.0,20.0,20.0));

        //INSERIMENTO VBOX IN HBOX  E HBOX IN PANNELLO
        vox.getChildren().addAll(box1,box5,box2,box3,box4);
        pane.getChildren().add(vox);

        Scene scena= new Scene(pane,400,300);
        stage.setScene(scena);
        stage.show();

    }
}

