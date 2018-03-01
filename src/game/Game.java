package game;

import javafx.scene.layout.GridPane;

import java.util.Random;

public class Game {
    private static String difficolta;
    private static boolean player_has_moved;



    public  Game() {

        player_has_moved = false;
    }



    public static void set_difficolta(String diff) {
        difficolta = diff;
    }

    public static String get_difficolta() {
        return difficolta;
    }

    public static boolean has_player_moved() {
        return player_has_moved;
    }

    public static void set_players_move(boolean in) {
        player_has_moved = in;
    }
}

