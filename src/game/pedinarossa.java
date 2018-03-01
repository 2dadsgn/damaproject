package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class pedinarossa extends pedina {
    private  Circle cerchio;
    private int location[];
    private int actual_loc[];
    private int pedi=0;

    public pedinarossa(){
        location=new int[2];
        if(Scacchiera.get_colore_player()=="red") {
            //colore rosso!
            cerchio = new Circle(0.0, 0.0, 20.0, Paint.valueOf("#a00123"));
        }
        else if(Scacchiera.get_colore_player()=="blue"){
            cerchio=new Circle(0.0,0.0,20.0, Paint.valueOf("#006758"));
        }
        actual_loc=new int[2];
    }

    public Circle get_circle(){
               return cerchio;
    }

    public void mangia(){};

    //metodo per muovere le pedine
    public boolean movimento(){

        pedina ped=this;
        this.get_circle().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                location = Location.get_location();
                int x,y;
                x=actual_loc[0];
                y=actual_loc[1];

                System.out.println("Attuale posizione  "+actual_loc[0]+"--"+actual_loc[1]);
                System.out.println("Posizione del movimento  "+location[0]+"--"+location[1]);

                //controllo if per verificare se il movimento è consentito
                //movimento a sx
                if(location[0]==x-1 && location[1]==y-1 && Scacchiera.is_empty_position(location[0],location[1])==true
                        && Game.has_player_moved()==false){
                    System.out.println("Movimento a sx consentito");
                    Scacchiera.remove_pedina(actual_loc[0],actual_loc[1]);
                    Scacchiera.ins_pedina(ped, location[0], location[1]);

                    System.out.println("Si muove xy--" + location[0] + "--" + location[1]);
                    ped.set_actual_location(location[0],location[1]);
                    Game.set_players_move(true);

                    //ritardare azione AI

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Ai.gioca()));
                    timeline.play();

                }
                //movimento a dx di una casella
                else if(location[0]==x+1 && location[1]==y-1 && Scacchiera.is_empty_position(location[0],location[1])==true
                        && Game.has_player_moved()==false){
                    System.out.println("Movimento a dx consentito");
                    Scacchiera.ins_pedina(ped, location[0], location[1]);
                    System.out.println("Si muove xy--" + location[0] + "--" + location[1]);
                    ped.set_actual_location(location[0],location[1]);
                    Game.set_players_move(true);

                    //ritardare azione AI
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Ai.gioca()));
                    timeline.play();
                }
                //movimento a sinistra di due caselle per cattura pedina avversaria
                else if(location[0]==x-2 && location[1]==y-2 && Scacchiera.is_empty_position(x-1,y-1)==false
                        && Scacchiera.is_empty_position(x-2,y-2)==true && Scacchiera.is_blue(x-1,y-1)
                        && Game.has_player_moved()==false){
                    Scacchiera.remove_pedina(x-1,y-1);
                    Scacchiera.ins_pedina(ped, location[0], location[1]);
                    System.out.println("Si muove xy--" + location[0] + "--" + location[1]);
                    ped.set_actual_location(location[0],location[1]);

                    if(location[0]<2 && location[1]>1 && Scacchiera.is_blue(location[0]+1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]+2,location[1]-2)) {


                    }
                    else if(location[0]>1 && location[0]<6 && location[1]>1 && Scacchiera.is_blue(location[0]+1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]+2,location[1]-2)) {


                    }
                    else if(location[0]>1 && location[0]<6 && location[1]>1 && Scacchiera.is_blue(location[0]-1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]-2,location[1]-2)) {



                    }
                    else if(location[0]>5 && location[1]>1 && Scacchiera.is_blue(location[0]-1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]-2,location[1]-2)) {


                    }
                    else {
                        Game.set_players_move(true);

                        //controllo se la partita è finita
                        Scacchiera.partita_finita();

                        //ritardare azione AI
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000),
                                ae -> Ai.gioca()));
                        timeline.play();

                    } ;

                }
                //movimento di due caselle a destra per cattura pedina avversaria
                else if(location[0]==x+2 && location[1]==y-2 && Scacchiera.is_empty_position(x+1,y-1)==false
                        && Scacchiera.is_empty_position(x+2,y-2)==true && Scacchiera.is_blue(x+1,y-1)
                        && Game.has_player_moved()==false){
                    Scacchiera.remove_pedina(x+1,y-1);
                    Scacchiera.ins_pedina(ped, location[0], location[1]);
                    System.out.println("Si muove xy--" + location[0] + "--" + location[1]);
                    ped.set_actual_location(location[0],location[1]);

                    if(location[0]<2 && location[1]>1 && Scacchiera.is_blue(location[0]+1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]+2,location[1]-2)) {


                    }
                    else if(location[0]>1 && location[0]<6 && location[1]>1 && Scacchiera.is_blue(location[0]+1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]+2,location[1]-2)) {

                    }
                    else if(location[0]>1 && location[0]<6 && location[1]>1 && Scacchiera.is_blue(location[0]-1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]-2,location[1]-2)) {



                    }
                    else if(location[0]>5 && location[1]>1 && Scacchiera.is_blue(location[0]-1,location[1]-1) &&
                            Scacchiera.is_empty_position(location[0]-2,location[1]-2)) {


                    }
                    else {
                        Game.set_players_move(true);

                        //controllo se la partita è finita
                        Scacchiera.partita_finita();

                        //ritardare azione AI
                        Timeline timeline = new Timeline(new KeyFrame(
                                Duration.millis(2000),
                                ae -> Ai.gioca()));
                        timeline.play();

                    } ;

                }
                //movimento non consentito
                else{
                    System.out.println("Movimento non consentito");
                }
                System.out.println(Game.has_player_moved());
                System.out.println("______________________________");
            }
        });
        return false;

    };

    public void set_actual_location(int col, int row){
        actual_loc[0]=col;
        actual_loc[1]=row;
    }

    public int[] get_actual_location(){
        return actual_loc;
    }

}
