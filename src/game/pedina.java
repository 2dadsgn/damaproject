package game;

import javafx.scene.shape.Circle;

public abstract class pedina {
    public abstract boolean movimento();
    public abstract void mangia();
    public abstract Circle get_circle();
    public abstract void set_actual_location(int col, int row);
    public abstract int[] get_actual_location();

}
