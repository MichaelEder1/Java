/*--------------------------------------------------------
 * GameOfLife.java
 * Author: Michael Eder
 * Date 18.04.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the functionality of
 * the CellMatrix class. Therefore an object from type
 * CellMatrix was created.
 --------------------------------------------------------*/
import kwm.*;
import kwm.graphics.*;

public class GameOfLife {
    public static void main(String[] args) {
        Drawing.begin("Game of Life", 800, 800);

        CellMatrix game = new CellMatrix(35, 15, 25);
        game.startAnimation();
        Drawing.end();
    }
}
