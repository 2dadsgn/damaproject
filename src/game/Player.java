package game;

import java.io.Serializable;

public class Player implements Serializable{
    private  String name;
    private int partite_vinte;

    public Player(){
        partite_vinte=0;
    }

    public void set_name(String in){
        name=in;
    }

    public String get_name(){
        return name;
    }

    public void increase_partite_vinte(){
        partite_vinte++;
    }

    public int getPartite_vinte(){
        return partite_vinte;
    }



}
