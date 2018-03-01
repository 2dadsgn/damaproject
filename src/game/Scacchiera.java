package game;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Scacchiera {

    private static pedina array[][];
    private static GridPane pane1=new GridPane();
    private  static GridPane gridcerchi=new GridPane();
    private static  blackTile array_bt[];
    private static int location[];
    private static boolean is_partita_precedente_finita;
    private static String colore_player;
    private static Dama_rossa arraydame[];
    private static int indice=0;
    private static boolean partita_finita;
    private static int numero_pedine_rosse;
    private static int mosse_rosso;


    //DESIGN PATTERN SINGLETON
    private static Scacchiera ourInstance = new Scacchiera();

    public static Scacchiera getInstance() {
        return ourInstance;
    }
    //

    private Scacchiera() {
        numero_pedine_rosse=12;
        mosse_rosso=0;
        partita_finita=false;
       array =new pedina[8][8];
       arraydame=new Dama_rossa[13];
       location=new int[2];
       is_partita_precedente_finita=true;

        Random ran=new Random();

        int n=ran.nextInt(20)+1;
        ran.setSeed(154888856);

        if(n%2==0){
            colore_player="red";
        }
        else{
            colore_player="blue";
        };

    }

    public static String get_colore_player(){
        return colore_player;
    }

    public static void ins_array_dame(Dama_rossa...dama_rossas){

        int i=0;
        while (i<dama_rossas.length){
            arraydame[i]=dama_rossas[i];
            i++;
        };
    }

    public static int get_mosse_rosso(){
        return mosse_rosso;
    }

    public static boolean get_partita_precedente(){
        return is_partita_precedente_finita;

    }
    public static void set_partita_precedente(){
        is_partita_precedente_finita=partita_finita();
    }

    //METODO PER L INSERIMENTO DELLE PEDINE NELL'ARRAY MULTIDIMENSIONALE
    public static void ins_pedine(pedina...pedinas){

        //LA VARIABILE P  SERVE A TENERE CONTO DELLA POSSIZIONE
        //NELL ARRAY PEDINAS
        int p=0;

        //ciclo inserimento pedine nere
        for (int c = 0; c < 3; c++) {
            if(c%2==0){
                for (int i = 0; i < 8; i = i + 2) {
                    array[i][c] = pedinas[p];
                    pedinas[p++].set_actual_location(i,c);
                }
            }
            else{
                for (int i = 1; i < 8; i = i + 2) {
                    array[i][c] = pedinas[p];
                    pedinas[p++].set_actual_location(i,c);
                }
            }
        }
        //ciclo inserimento pedine rosse
        for(int z=5;z<8;z++){
            if(z%2!=0){
                for(int k=1;k<8;k=k+2){
                    array[k][z] = pedinas[p];
                    pedinas[p++].set_actual_location(k,z);
                }
            }
            else{
                for(int k=0;k<8;k=k+2){
                    array[k][z] = pedinas[p];
                    pedinas[p++].set_actual_location(k,z);
                }
            }
        }
    }

    //METODO PER CREARE LA SCACCHIERA NELLO STAGE GAME
    public static void set_scacchiera(blackTile...blackTiles){

        int d=0;

        //qui viene creata la scacchiera con un ciclo for
        // che inserisce i rettangoli bianchi e neri
        for(int i=0;i<8;i++){

            for(int c=0;c<8;c++){

               if(i%2==0 && c%2==0  || i%2!=0 && c%2!=0){
                   gridcerchi.add(blackTiles[d].getRettangolo(),c,i);
                   blackTiles[d++].ins_pos_in_array(c,i);
               }
               else if(i%2==0 && c%2!=0 || i%2!=0 && c%2==0){
                   gridcerchi.add(new Rectangle(50,50, Color.WHITE),c,i);
               }
            }
        }
    }


    public static GridPane getGridcerchi(pedina...pedinas) {

        //qui viene gestita la gridpane delle pedine
        RowConstraints row_const= new RowConstraints();
        row_const.setMaxHeight(50.0);
        row_const.setMinHeight(50.0);
        row_const.setPrefHeight(50.0);
        row_const.setValignment(VPos.CENTER);

        ColumnConstraints col_const= new ColumnConstraints();
        col_const.setMaxWidth(50.0);
        col_const.setMinWidth(50.0);
        col_const.setPrefWidth(50.0);
        col_const.setHalignment(HPos.CENTER);

        int p=0;

        //ciclo inserimento pedine  cerchi neri
        for (int c = 0; c < 3; c++) {
            if(c%2==0){
                for (int i = 0; i < 8; i = i + 2) {
                    gridcerchi.add(pedinas[p++].get_circle(),i,c);
                    gridcerchi.getColumnConstraints().add(col_const);
                    gridcerchi.getRowConstraints().add(row_const);
                }
            }
            else{
                for (int i = 1; i < 8; i = i + 2) {
                    gridcerchi.add(pedinas[p++].get_circle(),i,c);
                }
            }
        }

        //ciclo inserimento pedine cerchi rossi
        for(int z=5;z<8;z++){
            if(z%2!=0) {
                for (int k = 1; k < 8; k = k+2) {
                    gridcerchi.add(pedinas[p++].get_circle(), k, z);
                }
            }
            else{
                for(int k=0;k<8;k=k+2){
                    gridcerchi.add(pedinas[p++].get_circle(),k,z);
                }
            }
        }
        return gridcerchi;
    }

    //metodo per rimuovere cerhi
    private static void remove_cerchio(pedina pedina){
        gridcerchi.getChildren().remove(pedina.get_circle());
    }

    //metodo per eliminare pedine dalla matrice e cerchio dalla gridpane
    public static void remove_pedina(pedina ped_in){
        int array_locale[];
        array_locale=new int[2];
        array_locale=ped_in.get_actual_location();
        Scacchiera.remove_cerchio(ped_in);
        array[array_locale[0]][array_locale[1]]=null;
        System.out.println("pedina rimossa e inserita");
    }

    //overloading
    public static void remove_pedina(int col, int row){
        Scacchiera.remove_cerchio(array[col][row]);
        array[col][row]=null;
        System.out.println("pedina rimossa");


    }

    //metodo per inserire singola pedina che corrisponde al movimento
    public static void ins_pedina(pedina pedina_in, int x, int y){
        Scacchiera.remove_pedina(pedina_in);
        array[x][y] = pedina_in;

        //assegno alle variabili d'istanza le coordinate
        pedina_in.set_actual_location(x,y);

        //inserisco la variabile d'istanza cerchio nel gridpane
        gridcerchi.add(pedina_in.get_circle(), x, y);

        //controllo delle dame
       if(y==0 && pedina_in instanceof pedinarossa) {
           Dama_rossa dm=arraydame[indice++];

           Scacchiera.remove_pedina(pedina_in);
           array[x][y] = dm;
           dm.set_actual_location(x,y);
           gridcerchi.add(dm.get_circle(), x, y);
       }
       else if(y==7 && pedina_in instanceof pedinanera) {
           Dama_nera dm_nera=new Dama_nera();

           Scacchiera.remove_pedina(pedina_in);
           array[x][y] = dm_nera;
           dm_nera.set_actual_location(x,y);
           gridcerchi.add(dm_nera.get_circle(), x, y);
       }
       else{};

    }

    //controlla se la posizione è libera
    public static boolean is_empty_position(int col, int row){
        return array[col][row] == null;
    }

    //controlla se la pedina nella posizione col,row è rosssa
    public static boolean is_red(int col, int row){
        if(array[col][row] instanceof pedinarossa || array[col][row]  instanceof Dama_rossa){
            return true;
        }
        else if(array[col][row]==null){
            return false;
        }
        else return false;
    }

    //controlla se la pedina nella posizione col,row è  dama rossa
    public static boolean is_reddama(int col, int row){
        if( array[col][row]  instanceof Dama_rossa){
            return true;
        }
        else if(array[col][row]==null){
            return false;
        }
        else return false;
    }

    //controlla se la pedina nella posizione col,row è blue
    public static boolean is_blue(int col, int row){
        if(array[col][row] instanceof pedinanera){
            return true;
        }
        else if(array[col][row]==null){
            return false;
        }
        else return false;
    }

    //metodo per il controllo della presenza di qualche pedina che può mangiare
    public static pedina check_if_anyone_can_eat(){
        for(int i =0;i<8;i++){
            for(int c=0;c<8;c++){

                //se l'oggetto è un istanza di Dama
                if(array[c][i] instanceof Dama_nera){

                    // se la dama si trova in un area specifica nella
                    // quale può mangiare senza violare l indice array
                    if(c>1 && c<6 && i>1 && i<6){

                        //controllo tutto intorno alla dama per pedine da mangiare
                        if((is_red(c+1,i+1) && is_empty_position(c+2,i+2)) ||
                                (is_red(c-1,i+1) && is_empty_position(c-2,i+2)) ||
                                (is_red(c-1,i-1) && is_empty_position(c-2,i-2)) ||
                                (is_red(c+1,i-1) && is_empty_position(c+2,i-2))){
                            return (Dama_nera)array[c][i];
                        }
                        else { };
                    }
                    else if(c<2 && i<2){
                        if(is_red(c+1,i+1) && is_empty_position(c+2,i+2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c<2 && i>1 && i<6){
                        if(is_red(c+1,i+1) && is_empty_position(c+2,i+2)){
                            return (Dama_nera)array[c][i];
                        }
                        else if(is_red(c+1,i-1) && is_empty_position(c+2,i-2)){
                            return (Dama_nera)array[c][i];
                        };
                    }
                    else if(c<2 && i>5){
                        if(is_red(c+1,i-1) && is_empty_position(c+2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c>1 && c<6 && i<2){
                        if(is_red(c+1,i+1) && is_empty_position(c+2,i+2)){
                            return (Dama_nera)array[c][i];
                        }
                        else if(is_red(c-1,i+1) && is_empty_position(c-2,i+2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c>1 && c<6 && i>5){
                        if(is_red(c+1,i-1) && is_empty_position(c+2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                        else if(is_red(c-1,i-1) && is_empty_position(c-2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c>5 && i>5){
                        if(is_red(c-1,i-1) && is_empty_position(c-2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c>5 && i>1 && i<6){
                        if(is_red(c-1,i+1) && is_empty_position(c-2,i+2)){
                            return (Dama_nera)array[c][i];
                        }
                        else if(is_red(c-1,i-1) && is_empty_position(c-2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                    else if(c>5 && i>5){
                        if(is_red(c-1,i-1) && is_empty_position(c-2,i-2)){
                            return (Dama_nera)array[c][i];
                        }
                    }
                }
                else if(array[c][i] instanceof pedinanera && c>1 && i<6 && is_reddama(c-1,i+1)==false && is_red(c-1,i+1)
                        && is_empty_position(c-2,i+2)) {
                    return (pedinanera)array[c][i];
                }
                else if(array[c][i] instanceof pedinanera && c<6 && i<6 &&
                        is_reddama(c+1,i+1)==false && is_red(c+1,i+1) && is_empty_position(c+2,i+2)){
                    return (pedinanera) array[c][i];
                }
                else{}
            }
        }
        return null;
    }

    //restituisce le pedine nere in un array
    public static pedina[] get_array_pedine(){
        int p=0;
        pedina array_locale[];
        array_locale=new pedina[13];
        for(int i=0;i<8;i++){
            for(int c=0;c<8;c++){
                if(array[c][i] instanceof pedinanera || array[c][i] instanceof Dama_nera){
                    array_locale[p++]=array[c][i];
                }
            }
        }
        return array_locale;
    }

    //metodo per il controllo di fine partita
    public static boolean partita_finita(){
        int red_counter=0;
        int black_counter=0;

        int mosse_nero=0;
        for(int i=0;i<8;i++){
            for(int c=0;c<8;c++){
                if(array[c][i] instanceof pedinarossa  ){
                    red_counter++;
                    if(c>0 && c<7){
                        if(is_empty_position(c+1,i-1) || is_empty_position(c-1,i-1)) {
                            mosse_rosso = 1;
                        }
                    }
                    else if(c==0){
                        if(is_empty_position(c+1,i-1)) {
                            mosse_rosso = 1;
                        }

                    }
                    else if(c==7){
                        if(is_empty_position(c-1,i-1)) {
                            mosse_rosso = 1;
                        }

                    }

                }
                else if(array[c][i] instanceof Dama_rossa){
                    black_counter++;
                    if(c>0 && c<7 && i>0 && i<7){
                        if(is_empty_position(c+1,i+1) || is_empty_position(c-1,i+1) ||
                                is_empty_position(c+1,i-1) || is_empty_position(c-1,i-1)) {
                            mosse_rosso=1;
                        }
                    }
                    else if(c>0 && c<7 && i==7){
                        if(is_empty_position(c-1,i-1) || is_empty_position(c+1,i-1)){
                            mosse_rosso=1;
                        }
                    }
                    else if(c==7 && i==7){
                        if(is_empty_position(c-1,i-1)){
                            mosse_rosso=1;
                        }

                    }
                    else if(c==0 && i>0){
                        if(is_empty_position(c+1,i-1) || is_empty_position(c+1,i+1)){
                            mosse_rosso=1;
                        }
                    }
                    else if(c==7){
                        if (is_empty_position(c-1,i+1) || is_empty_position(c-1,i-1)){
                            mosse_rosso=1;
                        }
                    }
                    else if(c==0 && i==0){
                        if(is_empty_position(c+1,i+1) ){
                            mosse_rosso=1;
                        }
                    }
                    else if(c==7 && i==7){
                        if(is_empty_position(c-1,i-1)){
                            mosse_rosso=1;
                        }
                    }
                }
                else if(array[c][i] instanceof pedinanera ){
                    black_counter++;
                    if(c>0 && c<7){
                        if(is_empty_position(c+1,i+1) || is_empty_position(c-1,i+1)) {
                            mosse_rosso = 1;
                        }
                    }
                    else if(c==0){
                        if(is_empty_position(c+1,i+1)) {
                            mosse_rosso = 1;
                        }

                    }
                    else if(c==7){
                        if(is_empty_position(c-1,i+1)) {
                            mosse_rosso = 1;
                        }

                    }

                }
                else if(array[c][i]instanceof Dama_nera) {
                    black_counter++;
                    if (c > 0 && c < 7 && i > 0 && i < 7) {
                        if (is_empty_position(c + 1, i + 1) || is_empty_position(c - 1, i + 1) ||
                                is_empty_position(c + 1, i - 1) || is_empty_position(c - 1, i - 1)) {
                            mosse_rosso = 1;
                        }
                    } else if (c > 0 && c < 7 && i == 7) {
                        if (is_empty_position(c - 1, i - 1) || is_empty_position(c + 1, i - 1)) {
                            mosse_rosso = 1;
                        }
                    } else if (c == 7 && i == 7) {
                        if (is_empty_position(c - 1, i - 1)) {
                            mosse_rosso = 1;
                        }

                    } else if (c == 0 && i > 0) {
                        if (is_empty_position(c + 1, i - 1) || is_empty_position(c + 1, i + 1)) {
                            mosse_rosso = 1;
                        }
                    } else if (c == 7) {
                        if (is_empty_position(c - 1, i + 1) || is_empty_position(c - 1, i - 1)) {
                            mosse_rosso = 1;
                        }
                    } else if (c == 0 && i == 0) {
                        if (is_empty_position(c + 1, i + 1)) {
                            mosse_rosso = 1;
                        }
                    } else if (c == 7 && i == 7) {
                        if (is_empty_position(c - 1, i - 1)) {
                            mosse_rosso = 1;
                        }
                    }
                }
                else {};
            }
        }

        System.out.println(red_counter+"<--R|||B-->"+black_counter);

        if (red_counter == 0 || black_counter == 0 || mosse_rosso==0){
            if(red_counter==0){
                System.out.println("red_counter==0");
            }
            else if(black_counter==0){
                System.out.println("black_counter==0");
            }

            else if(mosse_rosso==0){
                System.out.println("mosse_rosso==0");
            };
            numero_pedine_rosse=0;
            partita_finita=true;
            System.out.println("-----------------PARTITA FINITA!-----------------");
            return true;
        }
        else return false;
    }

    public static int getNumero_pedine_rosse(){
        return numero_pedine_rosse;
    }

    public static boolean get_partita_finita(){
        return partita_finita;
    }


    //restore to previous state gridcerchi,array[][]
    //memento class and scacchiera's method to restore memento

    public static Memento savetomemento(){
        return new Memento(gridcerchi,array);
    }
    public static void restorefrommemento(Memento mem_in){
        gridcerchi=mem_in.get_saved_state_gridcerchi();
        array=mem_in.get_saved_state_arraypedine();
    }

    public static GridPane getcerchi(){
        return gridcerchi;
    }





    // classe privata memento per salvare precedente stato della scacchiera
    public static class Memento{
        private final GridPane gridcerchi_memento;
        private final pedina arraypedinememento[][];

        public Memento(GridPane gridcerchi_memento, pedina[][] arraypedinememento){
            this.arraypedinememento=arraypedinememento;
            this.gridcerchi_memento=gridcerchi_memento;
        }

        private GridPane get_saved_state_gridcerchi(){
            return gridcerchi_memento;
        }

        private pedina[][] get_saved_state_arraypedine(){
            return arraypedinememento;
        }

    }

}
