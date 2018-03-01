package game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class blackTile extends Tile {
    private Rectangle rettangolo;
    private int array[];

    public blackTile (){
        rettangolo=new Rectangle(50, 50, Color.BLACK);
        array= new int[2];

    }

    public Rectangle getRettangolo(){
        return rettangolo;
    }

    public int[] get_location(){
        this.getRettangolo().setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override public void handle(MouseEvent event){
               Location.set_location(array[0],array[1]);
                System.out.println("\n"+array[0]+"--"+array[1]);
            }

        });
        return array;
    }

    //metodo per linserimento della posizione del tassello
    //al momento dell inserimento in matrice taselli - scacchiera
    public void ins_pos_in_array(int c, int i){
        array[0]=c;
        array[1]=i;

    }
}
