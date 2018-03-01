package game;

public class Location {
    private static Location ourInstance = new Location();
    private static int location_array[];


    public static Location getInstance() {
        return ourInstance;
    }

    private Location() {
        location_array=new int[2];

    }

    public static void set_location (int col, int row){
        if(col>=0 && row>=0 && col<8 && row<8) {
            location_array[0] = col;
            location_array[1] = row;
        }
    }

    public static int[] get_location(){
       return location_array;
    }
}
