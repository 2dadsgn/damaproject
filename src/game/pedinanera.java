package game;

//PEDINA AI

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class pedinanera extends pedina {
    private Circle cerchio_nero;
    private int actual_loc[];


    public pedinanera(){
        if(Scacchiera.get_colore_player()=="red") {
            cerchio_nero = new Circle(0.0, 0.0, 20.0, Paint.valueOf("#006758"));
        }
        else if(Scacchiera.get_colore_player()=="blue"){
            //colore rosso!
            cerchio_nero=new Circle(0.0,0.0,20.0, Paint.valueOf("#a00123"));
        };
        actual_loc=new int[2];


    }

    public Circle get_circle(){
        return cerchio_nero;
    }

    public void mangia(){};

    public boolean movimento(){
        String comando=Ai.comando;

        switch (comando) {

            //movimento a sx
            case "muovi a sx":
                Scacchiera.ins_pedina(this, actual_loc[0] - 1, actual_loc[1] + 1);
                Game.set_players_move(false);
            break;

            //movimento a dx
            case "muovi a dx":
                Scacchiera.ins_pedina(this, actual_loc[0] + 1, actual_loc[1] + 1);
                Game.set_players_move(false);
            break;

            //movimento a sx per mangiare
            case "muovi a sx per mangiare":

                Scacchiera.remove_pedina(actual_loc[0]-1,actual_loc[1]+1);
                Scacchiera.ins_pedina(this, actual_loc[0] -2, actual_loc[1] +2);

                //controllo se pedina può mangiare ulteriormente
                // a dx
                if((actual_loc[0])<6 && (actual_loc[1])<6 && Scacchiera.is_red(actual_loc[0]+1,actual_loc[1]+1) &&
                        Scacchiera.is_empty_position(actual_loc[0]+2,actual_loc[1]+2)){

                    Timeline timeline1 = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.remove_pedina(actual_loc[0]+1,actual_loc[1]+1)));
                    timeline1.play();

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.ins_pedina(this, actual_loc[0] + 2, actual_loc[1] + 2)));
                    timeline.play();
                    //controllo se la partita è finita
                    Scacchiera.partita_finita();
                }

                // asx
                else if((actual_loc[0])>1 && (actual_loc[1])<6 && Scacchiera.is_red(actual_loc[0]-1,actual_loc[1]+1) &&
                        Scacchiera.is_empty_position(actual_loc[0]-2,actual_loc[1]+2)){

                    Timeline timeline1 = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.remove_pedina(actual_loc[0]-1,actual_loc[1]+1)));
                    timeline1.play();

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.ins_pedina(this, actual_loc[0] -2, actual_loc[1] + 2)));
                    timeline.play();
                    //controllo se la partita è finita
                    Scacchiera.partita_finita();
                }
                Timeline timeline3 = new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        ae -> Game.set_players_move(false)));
                timeline3.play();

                //controllo se la partita è finita
                Scacchiera.partita_finita();

                System.out.println("la pedina ha mangiato");

            break;

            //movimento a dx per mangiare
            case "muovi a dx per mangiare":
                Scacchiera.remove_pedina(actual_loc[0]+1,actual_loc[1]+1);
                Scacchiera.ins_pedina(this, actual_loc[0] + 2, actual_loc[1] + 2);

                //controllo se pedina può mangiare ulteriormente
                //a dx
                if((actual_loc[0])<6 && (actual_loc[1])<6 && Scacchiera.is_red(actual_loc[0]+1,actual_loc[1]+1) &&
                        Scacchiera.is_empty_position(actual_loc[0]+2,actual_loc[1]+2)){

                    Timeline timeline1 = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.remove_pedina(actual_loc[0]+1,actual_loc[1]+1)));
                    timeline1.play();

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.ins_pedina(this, actual_loc[0] + 2, actual_loc[1] + 2)));
                    timeline.play();
                    //controllo se la partita è finita
                    Scacchiera.partita_finita();

                }
                // a sx
                else if((actual_loc[0])>1 && (actual_loc[1])<6 && Scacchiera.is_red(actual_loc[0]-1,actual_loc[1]+1) &&
                        Scacchiera.is_empty_position(actual_loc[0]-2,actual_loc[1]+2)){

                    Timeline timeline1 = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.remove_pedina(actual_loc[0]-1,actual_loc[1]+1)));
                    timeline1.play();

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(2000),
                            ae -> Scacchiera.ins_pedina(this, actual_loc[0] -2, actual_loc[1] + 2)));
                    timeline.play();
                    //controllo se la partita è finita
                    Scacchiera.partita_finita();
                }
                Timeline timeline2 = new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        ae -> Game.set_players_move(false)));
                timeline2.play();

                //controllo se la partita è finita
                Scacchiera.partita_finita();

                System.out.println("la pedina ha mangiato");
                break;

        }
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
