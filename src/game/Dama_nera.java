package game;

import javafx.scene.paint.Color;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Dama_nera extends pedina {

    //la dama a differenza delle pedina ha un controllo proprio delle mosse
    //la classe AI imaprtisce solo ordini di spostamento ma i controllli vengono
    //effettuati qui all'interno
    private Circle cerchio;
    private int actual_loc[];
    private int location[];
    private String dir;

    public Dama_nera(){
        dir="sali";
        if(Scacchiera.get_colore_player()=="red") {
            cerchio= new Circle(0.0, 0.0, 20.0, Paint.valueOf("#00ddbd"));
        }
        else if(Scacchiera.get_colore_player()=="blue"){
            //colore rosso!
            cerchio=new Circle(0.0,0.0,20.0,Paint.valueOf("#ff0036"));
        };
        actual_loc=new int[2];
        location=new int[2];
    }
    public void set_actual_location(int col, int row){
        actual_loc[0]=col;
        actual_loc[1]=row;
    }

    public int[] get_actual_location(){
        return actual_loc;
    }

    public Circle get_circle(){
        return cerchio;
    }

    //metodo per il movimento
    public boolean movimento(){
        String difficoltà;
        difficoltà=Game.get_difficolta();
        Random ran=new Random();
        int n=ran.nextInt(100)+1;
        int x =this.actual_loc[0];
        int y=this.actual_loc[1];

        switch (difficoltà){
            case "easy":
                if(n<71){
                    //movimento a destra scendere
                    if(x<6 && y<2  && Scacchiera.is_empty_position(x+1,y+1) &&
                            Scacchiera.is_empty_position(x+2,y+2)){
                        Scacchiera.ins_pedina(this, x+ 1, y+ 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;

                    }
                    //movimento a sx per scendere
                    else if(x>1  && y<2 && Scacchiera.is_empty_position(x-1,y+1) &&
                            Scacchiera.is_empty_position(x-2,y+2)){
                        Scacchiera.ins_pedina(this, x-1, y+ 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;
                    }
                    else if(x>1 && x<6 && y>1 && y<6 ){

                        if(dir=="scendi" && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_empty_position(x-2,y+2)){
                            //movimento a scendere verso sx
                           Scacchiera.ins_pedina(this, x - 1, y + 1);
                           Game.set_players_move(false);
                            return true;
                        }
                        else if(dir=="sali" && Scacchiera.is_empty_position(x-1,y-1) &&
                                Scacchiera.is_empty_position(x-2,y-2)){
                            //movimento a salire verso sx
                            Scacchiera.ins_pedina(this, x - 1, y -1);
                            Game.set_players_move(false);
                            return true;
                        };
                    }
                    else if(x<2 && x<6 && y>1 && y<6 ){

                        if(dir=="scendi" && Scacchiera.is_empty_position(x+1,y+1) &&
                                Scacchiera.is_empty_position(x+2,y+2)){
                            //movimento a scendere verso sx
                            Scacchiera.ins_pedina(this, x + 1, y + 1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(dir=="sali" && Scacchiera.is_empty_position(x+1,y-1) &&
                                Scacchiera.is_empty_position(x+2,y-2)){
                            //movimento a salire verso sx
                            Scacchiera.ins_pedina(this, x + 1, y -1);
                            Game.set_players_move(false);
                            return true;
                        };
                    }
                    else if(x>1 && y>1 && y<6){
                        if(dir=="scendi" && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_empty_position(x-2,y+2)){
                            //movimento a scendere verso dx
                            Scacchiera.ins_pedina(this, x + 1, y + 1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(dir=="sali" && Scacchiera.is_empty_position(x-1,y-1) &&
                                Scacchiera.is_empty_position(x-2,y-2)){
                            //movimento a salire verso dx
                            Scacchiera.ins_pedina(this, x + 1, y -1);
                            Game.set_players_move(false);
                            return true;
                        };
                    }
                    //movimento a destra salire
                    else if(x<6 && y>5  && Scacchiera.is_empty_position(x+1,y-1) &&
                            Scacchiera.is_empty_position(x+2,y-2)){
                        Scacchiera.ins_pedina(this, x+1, y- 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;
                    }
                    //movimento a sx per salire
                    else if(x>1  && y<2 && Scacchiera.is_empty_position(x-1,y-1) &&
                            Scacchiera.is_empty_position(x-2,y-2)){
                        Scacchiera.ins_pedina(this, x-1, y- 1);
                        Game.set_players_move(false);
                        dir="sali";
                        return true;
                    }
                    else{
                        return false;
                    };
                }

                //muove la pedina casualmente
                else if(n>70){

                    //muove verso alto a sx
                    if(x>1 && y>1 && Scacchiera.is_empty_position(x-1,y-1)){
                        Scacchiera.ins_pedina(this, x-1, y- 1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //muove verso alto dx
                    else if(x<6 && y>1 && Scacchiera.is_empty_position(x+1,y-1) ){
                        Scacchiera.ins_pedina(this, x+1, y- 1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //movimento a scendere verso sx
                    else if(x>1 && y<6 && Scacchiera.is_empty_position(x-1,y+1) ){
                        Scacchiera.ins_pedina(this, x-1, y+1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //movimento a scendere verso dx
                    else if(x<6 && y<6 && Scacchiera.is_empty_position(x+1,y+1) ){
                        Scacchiera.ins_pedina(this, x+1, y+1);
                        Game.set_players_move(false);
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    //muovi verso i bordi

                    //muove la dama verso sx in basso
                    if(x>4 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x+1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else;

                    }
                    else if(x<7 && y==7){
                        if(Scacchiera.is_empty_position(x-1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x+1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else;
                    }
                    else if(x==7 && y==7){
                        if(Scacchiera.is_empty_position(x-1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                    }
                    else if(x>0 && x<4 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                    }
                    else if(x==0 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                    }
                    else return false;
                }



//-------------------------------------------------------------------------------------------------------------------------------------



            case "hard":
                if(n<71){
                    //movimento a destra scendere
                    if(x<6 && y<2  && Scacchiera.is_empty_position(x+1,y+1) &&
                            Scacchiera.is_empty_position(x+2,y+2) &&
                            Scacchiera.is_red(x,y+2) && Scacchiera.is_empty_position(x+1,y)==false){
                        Scacchiera.ins_pedina(this, x+ 1, y+ 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;

                    }
                    //movimento a sx per scendere
                    else if(x>1  && y<2 && Scacchiera.is_empty_position(x-1,y+1) &&
                            Scacchiera.is_empty_position(x-2,y+2) && Scacchiera.is_red(x,y+2) &&
                            Scacchiera.is_empty_position(x-1,y)==false){
                        Scacchiera.ins_pedina(this, x-1, y+ 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;
                    }
                    else if(x<6 && y>1 && y<6 ){

                        if(dir=="scendi" && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_empty_position(x-2,y+2) &&
                                Scacchiera.is_red(x,y+2) && Scacchiera.is_empty_position(x-1,y)==false){
                            //movimento a scendere verso sx
                            Scacchiera.ins_pedina(this, x - 1, y + 1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(dir=="sali" && Scacchiera.is_empty_position(x-1,y-1) && x>1 &&
                                Scacchiera.is_empty_position(x-2,y-2) &&
                                Scacchiera.is_red(x,y-2) && Scacchiera.is_empty_position(x-1,y)==false){
                            //movimento a salire verso sx
                            Scacchiera.ins_pedina(this, x - 1, y -1);
                            Game.set_players_move(false);
                            return true;
                        };
                    }
                    else if(x>1 && y>1 && y<6){
                        if(dir=="scendi" && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_empty_position(x-2,y+2) &&
                                Scacchiera.is_red(x,y+2) && Scacchiera.is_empty_position(x+1,y)==false){
                            //movimento a scendere verso dx
                            Scacchiera.ins_pedina(this, x + 1, y + 1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(dir=="sali" && Scacchiera.is_empty_position(x-1,y-1) &&
                                Scacchiera.is_empty_position(x-2,y-2) &&
                                Scacchiera.is_red(x,y-2) && Scacchiera.is_empty_position(x+1,y)==false){
                            //movimento a salire verso dx
                            Scacchiera.ins_pedina(this, x + 1, y -1);
                            Game.set_players_move(false);
                            return true;
                        };
                    }
                    //movimento a destra salire
                    else if(x<6 && y>5  && Scacchiera.is_empty_position(x+1,y-1) &&
                            Scacchiera.is_empty_position(x+2,y-2) &&
                            Scacchiera.is_red(x,y-2) && Scacchiera.is_empty_position(x+1,y)==false){
                        Scacchiera.ins_pedina(this, x+1, y- 1);
                        Game.set_players_move(false);
                        dir="sali";
                        return true;
                    }
                    //movimento a sx per salire
                    else if(x>1  && y<2 && Scacchiera.is_empty_position(x-1,y-1) &&
                             Scacchiera.is_red(x,y-2) && Scacchiera.is_empty_position(x-1,y)==false){
                        Scacchiera.ins_pedina(this, x-1, y- 1);
                        Game.set_players_move(false);
                        dir="scendi";
                        return true;
                    }
                    else{
                        return false;
                    };
                }

                //muove la pedina casualmente
                else if(n>70){

                    //muove verso alto a sx
                    if(x>1 && y>1 && Scacchiera.is_empty_position(x-1,y-1)){
                        Scacchiera.ins_pedina(this, x-1, y- 1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //muove verso alto dx
                    else if(x<6 && y>1 && Scacchiera.is_empty_position(x+1,y-1) ){
                        Scacchiera.ins_pedina(this, x+1, y- 1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //movimento a scendere verso sx
                    else if(x>1 && y<6 && Scacchiera.is_empty_position(x-1,y+1) ){
                        Scacchiera.ins_pedina(this, x-1, y+1);
                        Game.set_players_move(false);
                        return true;
                    }
                    //movimento a scendere verso dx
                    else if(x<6 && y<6 && Scacchiera.is_empty_position(x+1,y+1) ){
                        Scacchiera.ins_pedina(this, x+1, y+1);
                        Game.set_players_move(false);
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    //muovi verso il centro

                    //muove la dama verso sx in basso
                    if(x>4 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x+1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else;

                    }
                    else if(x<7 && y==7){
                        if(Scacchiera.is_empty_position(x-1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x+1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else;
                    }
                    else if(x==7 && y==7){
                        if(Scacchiera.is_empty_position(x-1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y-1);
                            Game.set_players_move(false);
                            return true;
                        }
                    }
                    else if(x>0 && x<4 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                    }
                    else if(x==0 && y<7){
                        if(Scacchiera.is_empty_position(x+1,y+1)){
                            Scacchiera.ins_pedina(this, x+1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else if(Scacchiera.is_empty_position(x+1,y-1)){
                            Scacchiera.ins_pedina(this, x-1, y+1);
                            Game.set_players_move(false);
                            return true;
                        }
                        else return false;
                    }
                    else return false;

                }

                break;

        }
        return false;

    }


    //metodo per mangiare
    public void mangia(){

        int x=actual_loc[0];
        int y=actual_loc[1];
        int c=x;
        int i=y;

        //mangia in basso a dx
        if(c>1 && c<6 && i>1 && i<6) {
            if (Scacchiera.is_red(x - 1, y + 1) && Scacchiera.is_empty_position(x - 2, y + 2)) {
                Scacchiera.remove_pedina(x - 1, y + 1);
                Scacchiera.ins_pedina(this, x - 2, y + 2);
                Game.set_players_move(false);
            }
            //mangia in basso a sx
            else if (Scacchiera.is_red(x + 1, y + 1) && Scacchiera.is_empty_position(x + 2, y + 2)) {
                Scacchiera.remove_pedina(x + 1, y + 1);
                Scacchiera.ins_pedina(this, x + 2, y + 2);
                Game.set_players_move(false);
            }
            //mangia in alto a sx
            else if (Scacchiera.is_red(x - 1, y - 1) && Scacchiera.is_empty_position(x - 2, y - 2)) {
                Scacchiera.remove_pedina(x - 1, y - 1);
                Scacchiera.ins_pedina(this, x - 2, y - 2);
                Game.set_players_move(false);
            }
            //mangia in alto a dx
            else if (Scacchiera.is_red(x + 1, y - 1) && Scacchiera.is_empty_position(x + 2, y - 2)) {
                Scacchiera.remove_pedina(x + 1, y - 1);
                Scacchiera.ins_pedina(this, x + 2, y - 2);
                Game.set_players_move(false);
            }
        }
        else if(c<2 && i<2){
            if(Scacchiera.is_red(c+1,i+1) && Scacchiera.is_empty_position(c+2,i+2)){
                Scacchiera.remove_pedina(x + 1, y + 1);
                Scacchiera.ins_pedina(this, x + 2, y + 2);
                Game.set_players_move(false);
            }
        }
        else if(c<2 && i>1 && i<6){
            if(Scacchiera.is_red(c+1,i+1) && Scacchiera.is_empty_position(c+2,i+2)){
                Scacchiera.remove_pedina(x + 1, y + 1);
                Scacchiera.ins_pedina(this, x + 2, y + 2);
                Game.set_players_move(false);
            }
            else if(Scacchiera.is_red(c+1,i-1) && Scacchiera.is_empty_position(c+2,i-2)){
                Scacchiera.remove_pedina(c + 1, i - 1);
                Scacchiera.ins_pedina(this, c + 2, i - 2);
                Game.set_players_move(false);
            };
        }
        else if(c<2 && i>5){
            if(Scacchiera.is_red(c+1,i-1) && Scacchiera.is_empty_position(c+2,i-2)){
                Scacchiera.remove_pedina(c + 1, i - 1);
                Scacchiera.ins_pedina(this, c + 2, i - 2);
                Game.set_players_move(false);
            }
        }
        else if(c>1 && c<6 && i<2){
            if(Scacchiera.is_red(c+1,i+1) && Scacchiera.is_empty_position(c+2,i+2)){
                Scacchiera.remove_pedina(c + 1, i + 1);
                Scacchiera.ins_pedina(this, c + 2, i + 2);
                Game.set_players_move(false);
            }
            else if(Scacchiera.is_red(c-1,i+1) && Scacchiera.is_empty_position(c-2,i+2)){
                Scacchiera.remove_pedina(c -1, i + 1);
                Scacchiera.ins_pedina(this, c - 2, i + 2);
                Game.set_players_move(false);
            }
        }
        else if(c>1 && c<6 && i>5){
            if(Scacchiera.is_red(c+1,i-1) && Scacchiera.is_empty_position(c+2,i-2)){
                Scacchiera.remove_pedina(c + 1, i - 1);
                Scacchiera.ins_pedina(this, c + 2, i - 2);
                Game.set_players_move(false);
            }
            else if(Scacchiera.is_red(c-1,i-1) && Scacchiera.is_empty_position(c-2,i-2)){
                Scacchiera.remove_pedina(c - 1, i - 1);
                Scacchiera.ins_pedina(this, c - 2, i - 2);
                Game.set_players_move(false);
            }
        }

        //il caso c>1 && c<6 && i>1 && i<6 è stato esaminato all inizio

        else if(c>5 && i>5){
            if(Scacchiera.is_red(c-1,i+1) && Scacchiera.is_empty_position(c-2,i+2)){
                Scacchiera.remove_pedina(c - 1, i + 1);
                Scacchiera.ins_pedina(this, c - 2, i + 2);
                Game.set_players_move(false);
            }
        }
        else if(c>5 && i>1 && i<6){
            if(Scacchiera.is_red(c-1,i+1) && Scacchiera.is_empty_position(c-2,i+2)){
                Scacchiera.remove_pedina(c - 1, i + 1);
                Scacchiera.ins_pedina(this, c - 2, i + 2);
                Game.set_players_move(false);
            }
            else if(Scacchiera.is_red(c-1,i-1) && Scacchiera.is_empty_position(c-2,i-2)){
                Scacchiera.remove_pedina(c -1, i - 1);
                Scacchiera.ins_pedina(this, c -2, i - 2);
                Game.set_players_move(false);
            }
        }
        else if(c>5 && i>5){
            if(Scacchiera.is_red(c-1,i-1) && Scacchiera.is_empty_position(c-2,i-2)){
                Scacchiera.remove_pedina(c - 1, i - 1);
                Scacchiera.ins_pedina(this, c - 2, i - 2);
                Game.set_players_move(false);
            }
        }

    }

}
