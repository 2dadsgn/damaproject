package game;

import java.util.Random;

public class Ai {

    public static String comando;
    private static pedina array_pedine [];
    private static Ai ourInstance = new Ai();


    public static Ai getInstance() {
        return ourInstance;
    }

    private Ai() {
        array_pedine= new pedina[12];
    }


    public static void gioca(){
        String difficolta=Game.get_difficolta();
        Random ran=new Random();
        int n=ran.nextInt(100)+1;
        int locazione_attuale[];
        locazione_attuale=new int[2];

        switch(difficolta){
            case "easy":
            //-------Azione 1
                //se ha la possibilità di mangiare
                if(Scacchiera.check_if_anyone_can_eat()!=null ) {
                    System.out.println("azione 1");
                    pedina pedina =  Scacchiera.check_if_anyone_can_eat();
                    locazione_attuale = pedina.get_actual_location();

                    //controllo sulla pedina che può mangiare nel caso sia una dama
                    if(Scacchiera.check_if_anyone_can_eat() instanceof Dama_nera){
                        Scacchiera.check_if_anyone_can_eat().mangia();
                       break;
                    }
                    //altrimenti  è una pedina
                    //movimento per mangiare a dx
                    else if (locazione_attuale[0] < 6 && Scacchiera.is_red(locazione_attuale[0] + 1, locazione_attuale[1] + 1) &&
                            Scacchiera.is_empty_position(locazione_attuale[0] + 2, locazione_attuale[1] + 2) &&
                            Scacchiera.is_reddama(locazione_attuale[0]+1,locazione_attuale[1]+1)==false) {
                        comando = "muovi a dx per mangiare";
                        pedina.movimento();
                        break;
                    }
                    //movimento a sx per mangiare pedina
                    else if (locazione_attuale[0] > 1 && Scacchiera.is_red(locazione_attuale[0] - 1, locazione_attuale[1] + 1) &&
                            Scacchiera.is_empty_position(locazione_attuale[0] - 2, locazione_attuale[1] + 2) &&
                            Scacchiera.is_reddama(locazione_attuale[0]-1,locazione_attuale[1]+1)==false) {
                        comando = "muovi a sx per mangiare";
                        pedina.movimento();
                        break;
                    }
                    else {};
                    //------- Azione2.1
                }

                //70% muove la pedina con minor possibilità di essere mangiata
                else if(n>100){

                    System.out.println("azione 2.1");
                    array_pedine=Scacchiera.get_array_pedine();
                    int lunghezza_array=0;

                    //determino il numero di pedine
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };

                    //scorro l array delle pedine per trovare la pedina da muovere
                    for(int i=0;i<lunghezza_array;i++){
                        locazione_attuale=array_pedine[i].get_actual_location();
                        int x=locazione_attuale[0];
                        int y=locazione_attuale[1];

                        //controllo if per determinare pedina con minor probabilità di essere mangiata
                        if( array_pedine[i] instanceof Dama_nera && array_pedine[i].movimento()){

                            break;

                        }
                        else if(x==0 && y<6 && Scacchiera.is_empty_position(x+1,y+1) &&
                                Scacchiera.is_red(x,y+2)==false && Scacchiera.is_red(x+2,y+2)==false){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==0 && y==6 && Scacchiera.is_empty_position(x+1,y+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==1 && y<6 && Scacchiera.is_empty_position(x-1,y+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==1 && y<6 && Scacchiera.is_empty_position(x+1,y+1) &&
                                Scacchiera.is_red(x,y+2)==false && Scacchiera.is_red(x+2,y+2)==false){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x>1 && x<6 && y<6 && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_red(x-2,y+2)==false && Scacchiera.is_red(x,y+2)==false){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x>1 && x<6 && y==6 && Scacchiera.is_empty_position(x-1,y+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x>1 && x<6 && y==6 && Scacchiera.is_empty_position(x+1,y+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==6 & y<6 && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_red(x,y+2)==false && Scacchiera.is_red(x-2,y+2)==false ){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==6 && y==6 && Scacchiera.is_empty_position(x-1,y+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==6 && y==6 && Scacchiera.is_empty_position(x+1,y+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==6 && y<6 && Scacchiera.is_empty_position(x+1,y+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x==7 && y<6 && Scacchiera.is_empty_position(x-1,y+1) &&
                                Scacchiera.is_red(x,y+2)==false && Scacchiera.is_red(x-2,y+2)==false){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else{
                            System.out.println("NON SO COSA FARE,---> azione 2.2");
                            if(array_pedine[i+1]==null){
                                array_pedine=Scacchiera.get_array_pedine();
                                Random random_1=new Random();
                                //determino il numero di pedine

                                while(array_pedine[lunghezza_array]!=null){
                                    lunghezza_array++;
                                };
                                while(Game.has_player_moved()!=false) {
                                    System.out.println("while player hasnt moved");
                                    int lung=lunghezza_array;
                                    int numero = random_1.nextInt(lung) + 0;
                                    locazione_attuale=array_pedine[numero].get_actual_location();

                                    //controllo se dama
                                    if( array_pedine[numero] instanceof Dama_nera && array_pedine[numero].movimento()){

                                        break;

                                    }
                                    else if(locazione_attuale[0]<7 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                                        comando="muovi a dx";
                                        array_pedine[numero].movimento();
                                        break;
                                    }
                                    else if(locazione_attuale[0]>0 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)) {
                                        comando="muovi a sx";
                                        array_pedine[numero].movimento();
                                        break;
                                    }
                                    else{
                                        Ai.gioca();
                                    }
                                }
                            }
                        }
                    }
                }

         //------Azione 2.2
                // se la probabilità rientra nel 30% muove casualmente
                else if( n>0){
                    System.out.println("azione 2.2");
                    array_pedine=Scacchiera.get_array_pedine();
                    Random random_1=new Random();
                    //determino il numero di pedine
                    int lunghezza_array=0;
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };
                    if(lunghezza_array==0){
                        break;
                    }
                    while(Game.has_player_moved()!=false) {

                        int lung=lunghezza_array;

                        int numero = random_1.nextInt(lung) + 0;
                        locazione_attuale=array_pedine[numero].get_actual_location();

                        // controllo se dama
                        if( array_pedine[numero] instanceof Dama_nera && array_pedine[numero].movimento()){

                            break;

                        }
                        else if(locazione_attuale[0]<7 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                            comando="muovi a dx";
                            array_pedine[numero].movimento();
                            break;
                        }
                        else if(locazione_attuale[0]>0 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)) {
                            comando="muovi a sx";
                            array_pedine[numero].movimento();
                            break;
                        }
                        else{
                            Ai.gioca();
                        }
                    }
                }
        //-------Azione 3
                //muove le pedine verso i bordi
                else{
                    System.out.println("azione 3");
                    array_pedine=Scacchiera.get_array_pedine();
                    int lunghezza_array=0;
                    //determino il numero di pedine
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };
                    if(lunghezza_array==0){
                        break;
                    }
                    for(int i=0;i<lunghezza_array;i++) {
                        locazione_attuale = array_pedine[i].get_actual_location();

                        //controllo se dama
                        if( array_pedine[i] instanceof Dama_nera && array_pedine[i].movimento()){

                            break;

                        }
                        else if(locazione_attuale[0]==1 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(locazione_attuale[0]==6 && locazione_attuale[1]<6 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(locazione_attuale[0]==0 && locazione_attuale[1]<6 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(locazione_attuale[0]==7 && locazione_attuale[1]<6 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else {};
                    }
                }

    //------------FINE CASO EASY-------------------------------------------------------------------
                    break;


    //---------------------------------------------------INIZIO CASO HARD-----------------------------------------------------------

            case "hard":
                //-------Azione 1
                //se ha la possibilità di mangiare


                if(Scacchiera.check_if_anyone_can_eat()!=null ) {
                    System.out.println("azione 1");
                    pedina pedina =  Scacchiera.check_if_anyone_can_eat();
                    locazione_attuale = pedina.get_actual_location();

                    //controllo sulla pedina che può mangiare nel caso sia una dama
                    if(Scacchiera.check_if_anyone_can_eat() instanceof Dama_nera){
                        Scacchiera.check_if_anyone_can_eat().mangia();
                        break;
                    }
                    //altrimenti  è una pedina
                    //movimento per mangiare a dx
                    else if (locazione_attuale[0] < 6 && Scacchiera.is_red(locazione_attuale[0] + 1, locazione_attuale[1] + 1) &&
                            Scacchiera.is_empty_position(locazione_attuale[0] + 2, locazione_attuale[1] + 2) &&
                            Scacchiera.is_reddama(locazione_attuale[0]+1,locazione_attuale[1]+1)==false) {
                        comando = "muovi a dx per mangiare";
                        pedina.movimento();
                        break;
                    }
                    //movimento a sx per mangiare pedina
                    else if (locazione_attuale[0] > 1 && Scacchiera.is_red(locazione_attuale[0] - 1, locazione_attuale[1] + 1) &&
                            Scacchiera.is_empty_position(locazione_attuale[0] - 2, locazione_attuale[1] + 2) &&
                            Scacchiera.is_reddama(locazione_attuale[0]-1,locazione_attuale[1]+1)==false) {
                        comando = "muovi a sx per mangiare";
                        pedina.movimento();
                        break;
                    }
                    else {};

                }
//------- Azione2.1
                //70% muove la pedina con maggior possibilità di mangiare
                else if(n<70){

                    System.out.println("azione 2.1");
                    array_pedine=Scacchiera.get_array_pedine();
                    int lunghezza_array=0;

                    //determino il numero di pedine
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };
                    if(lunghezza_array==0){
                        break;
                    }
                    //scorro l array delle pedine per trovare la pedina da muovere
                    for(int i=0;i<lunghezza_array;i++){
                        locazione_attuale=array_pedine[i].get_actual_location();
                        int x=locazione_attuale[0];
                        int y=locazione_attuale[1];
                        System.out.println(x+" "+y);

                        //controllo if per determinare pedina con maggior possibilità di mangiare
                        if( array_pedine[i] instanceof Dama_nera && array_pedine[i].movimento()){

                            break;

                        }
                        //altrimenti è una pedina
                        //controllo per muovere a destra e mangiare pedina di sx
                        else if(x>1 && x<6 && y<6 && Scacchiera.is_empty_position(x+2, y)==false &&
                                Scacchiera.is_empty_position(x+1,y+1) && Scacchiera.is_empty_position(x+2,y+2) &&
                                Scacchiera.is_red(x,y+2) && Scacchiera.is_red(x-1,y+3)==false){
                            comando="muovi a dx";
                            array_pedine[i].movimento();

                            break;
                        }
                        //muovere a sx e mangiare pedina a dx
                        else if(x>1 && x<6 && y<6 && Scacchiera.is_empty_position(x-2, y)==false &&
                                Scacchiera.is_empty_position(x-1,y+1) && Scacchiera.is_empty_position(x-2,y+2) &&
                                Scacchiera.is_red(x,y+2) && Scacchiera.is_red(x+1,y+3)==false){
                            comando="muovi a sx";
                            array_pedine[i].movimento();

                            break;
                        }

                        else{

                            if(array_pedine[i+1]==null){
                                System.out.println("entra in else");
                                array_pedine=Scacchiera.get_array_pedine();
                                Random random_1=new Random();
                                //determino il numero di pedine
                                lunghezza_array=0;
                                while(array_pedine[lunghezza_array]!=null){
                                    lunghezza_array++;
                                };
                                while(Game.has_player_moved()!=false) {

                                    int lung=lunghezza_array;
                                    int numero = random_1.nextInt(lung) + 0;
                                    locazione_attuale=array_pedine[numero].get_actual_location();

                                    if( array_pedine[numero] instanceof Dama_nera && array_pedine[numero].movimento()){

                                        break;

                                    }
                                    else if(locazione_attuale[0]<7 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                                        comando="muovi a dx";
                                        array_pedine[numero].movimento();
                                        break;
                                    }
                                    else if(locazione_attuale[0]>0 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)) {
                                        comando="muovi a sx";
                                        array_pedine[numero].movimento();
                                        break;
                                    }
                                    else{
                                        System.out.println("qui");
                                    };
                                }
                            }
                        }
                    }
                }
                //------Azione 2.2
                // se la probabilità rientra nel 30% muove casualmente
                else if( n>70){
                    System.out.println("azione 2.2");
                    array_pedine=Scacchiera.get_array_pedine();
                    Random random_1=new Random();
                    //determino il numero di pedine
                    int lunghezza_array=0;
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };
                    if(lunghezza_array==0){
                        break;
                    }
                    while(Game.has_player_moved()!=false) {

                        int lung=lunghezza_array;
                        int numero = random_1.nextInt(lung) + 0;
                        locazione_attuale=array_pedine[numero].get_actual_location();

                        if( array_pedine[numero] instanceof Dama_nera && array_pedine[numero].movimento()){

                                break;

                        }
                        else if(locazione_attuale[0]<7 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]+1,locazione_attuale[1]+1)){
                            comando="muovi a dx";
                            array_pedine[numero].movimento();
                            break;
                        }
                        else if(locazione_attuale[0]>0 && locazione_attuale[1]<7 && Scacchiera.is_empty_position(locazione_attuale[0]-1,locazione_attuale[1]+1)) {
                            comando="muovi a sx";
                            array_pedine[numero].movimento();
                            break;
                        }
                        else{
                            System.out.println("entra in else di 2.2");
                        }
                    }
                }
                //-------Azione 3
                //muove le pedine verso il centro della scacchiera
                else{
                    System.out.println("azione 3");
                    array_pedine=Scacchiera.get_array_pedine();
                    int lunghezza_array=0;

                    //determino il numero di pedine
                    while(array_pedine[lunghezza_array]!=null){
                        lunghezza_array++;
                    };
                    if(lunghezza_array==0){
                        break;
                    }
                    for(int i=0;i<lunghezza_array;i++) {
                        locazione_attuale = array_pedine[i].get_actual_location();
                        int x=locazione_attuale[0];
                        int y=locazione_attuale[1];

                        //controllo se dama
                        if( array_pedine[i]instanceof Dama_nera && array_pedine[i].movimento()){

                            break;

                        }
                        else if(x>3 && y<7 && Scacchiera.is_empty_position(x-1,y+1)){
                            comando="muovi a sx";
                            array_pedine[i].movimento();
                            break;
                        }
                        else if(x<5 && y<7 && Scacchiera.is_empty_position(x+1,y+1)){
                            comando="muovi a dx";
                            array_pedine[i].movimento();
                            break;
                        };
                    }
                }

                //------------FINE CASO HARD-------------------------------------------------------------------
                break;
        }
    }
}
