package game;

import java.io.*;
import java.util.List;

public class Classifica implements Serializable {
    private Player arrayclassifica[];
    private int indice;

    public Classifica(){
        arrayclassifica=new Player[10];
        indice=0;
    }

    public void insert_giocatore(Player player){
        int i=0;
        Player array_tmp[];
        array_tmp=new Player[10];
        array_tmp=arrayclassifica;
        String tmp1=player.get_name().toString();




        if(indice==0){

            arrayclassifica[indice++]=player;
      ;
        }
        else {
            for (i = 0; i < indice; i++) {
                String tmp2=arrayclassifica[i].get_name().toString() ;

                if ( tmp1.equals(tmp2)  && player.getPartite_vinte() == 1) {
                    arrayclassifica[i].increase_partite_vinte();
                    break;
                }
                else if(tmp1.equals(tmp2) && player.getPartite_vinte() == 0){

                    break;

                }
                else if(i==indice-1){
                    arrayclassifica[indice] = player;
                    if (indice<8){
                        indice++;
                    }
                    System.out.println("Giocatore "+player.get_name()+" inserito in posizione  "+" "+indice);
                    break;
                }
                else;
            }


        }


        //ordino la classifica
        if(indice>=2) {
            for (i = 0; i < indice; i++) {
                for (int z = indice - 1; z > 0; z--) {
                    if (arrayclassifica[z].getPartite_vinte() > arrayclassifica[i].getPartite_vinte()) {
                        Player tmp_player;
                        tmp_player = arrayclassifica[i];
                        arrayclassifica[i] = arrayclassifica[z];
                        arrayclassifica[z] = tmp_player;
                    } else ;
                }

            }
        }

    }

    public Player[] getArrayclassifica(){
        return arrayclassifica;
    }

    public int getIndice(){
        return indice;
    }

    //metodo per salvare la classifica
    public void save_stats() throws IOException,ClassNotFoundException{
        Classifica questo=this;
        File fileout=new File("stats","Classifica.ser");
        try {
            FileOutputStream file_out = new FileOutputStream(fileout);
            ObjectOutputStream objwriter = new ObjectOutputStream(file_out);
            objwriter.writeObject(questo);
            objwriter.close();
        }
        catch (IOException exc){
            exc.printStackTrace();
        }

    }

    //metodo per ripristinare stato dell'oggetto
    public void restore_stats() throws IOException,ClassNotFoundException,NotSerializableException{
        Classifica questo=this;
        File file=new File("stats","Classifica.ser");
        Classifica temporaneo=this;


            try {
                FileInputStream file_in = new FileInputStream(file);
                ObjectInputStream obj_in = new ObjectInputStream(file_in);
                Classifica temp =(Classifica)  obj_in.readObject();
                this.arrayclassifica=temp.getArrayclassifica();
                this.indice=temp.getIndice();

            }
            catch (IOException exc){
                exc.printStackTrace();
            }


    }



}

