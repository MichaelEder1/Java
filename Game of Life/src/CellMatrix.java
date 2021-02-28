/*--------------------------------------------------------
 * CellMatrix.java
 * Author: Michael Eder
 * Date 18.04.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type CellMatrix
 * The CellMatrix is a matrix with size width by height.
 * The functions can be used to simulate the GameOfLife by
 * british mathematician Conway. Every 0.5 second the rules
 * will be applied to the matrix and then a new Cell Generation
 * will be generated and drawn.
 *
 --------------------------------------------------------*/
package kwm.graphics;

import kwm.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CellMatrix implements ActionListener {
    //Global variables
    public static final int OFFSET_X = 50;
    public static final int OFFSET_Y = 50;
    public static final int SIZE = 30;

    public Timer timer;
    public static int delay = 500;
    public boolean[][] gameMatrix;
    public int p;

    public CellMatrix(int p, int width, int height) {
        this.gameMatrix = new boolean[width][height];
        this.p = p;
    }

    /**
     * startAnimation()
     * This method starts the timer. Moreover it
     * initiates the matrix for the first time and
     * then draws the matrix.
     */
    public void startAnimation() {
        initiateMatrix();
        draw();
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    /**
     * initiateMatrix()
     * This method initialises the matrix corresponding to
     * value p. Therefore a random number will be generated.
     * If the random number is bigger than p, the cell is true,
     * else it will be false.
     */
    public void initiateMatrix() {
        //calculate if a cell is dead (false) or alive (true)
        for (int i = 0; i < this.gameMatrix.length; i++) {
            for (int j = 0; j < this.gameMatrix[i].length; j++) {
                gameMatrix[i][j] = isAlive();
            }
        }
    }

    /**
     * isAlive()
     * This method calculates a random number and returns true/false
     * if this random number is bigger/smaller than p
     *
     * @return returns true, if p is bigger than random, else false
     */
    public boolean isAlive() {
        return (Math.random() < (double) this.p / 100);
    }

    /**
     * generateCellGeneration()
     * This function is responsible for generating a new cell generation.
     * Itr first generates a auxiliary matrix in which the new true/false values
     * will be stored. Than it calls the checkNeighbour() function, which checks
     * the neighbour of each cell in the matrix. After that, the new matrix
     * will replace the old one.
     */
    public void generateCellGeneration() {
        boolean[][] matrixNew = new boolean[this.gameMatrix.length][this.gameMatrix[0].length];
        for (int i = 0; i < this.gameMatrix.length; i++) {
            for (int j = 0; j < this.gameMatrix[i].length; j++) {
                checkNeighbour(i, j, matrixNew);
            }
        }
        this.gameMatrix = matrixNew;
    }

    /**
     * checkNeigbour(int i, int j, boolean[][] matrixNew)
     * This method checks every neighbour of every cell in the matrix.
     * Therefore a new array neighbourhoodArray will be created. In this
     * array the values of all neighbours of a cell (i,j) of the matrix will
     * be stored. Than, the functions checks, how many cells around a specific
     * cell (i,j) are alive (=true). For this a for-loop iterates over the
     * filled neighbourhoodArray and counts how many times, the value is true.
     * Depending on the amount of true neighbour cells, rules will be applied.
     *
     * @param i         the current column index of the matrix
     * @param j         the current row index of the matrix
     * @param matrixNew the auxiliary matrix in which the new values will be stored
     */
    public void checkNeighbour(int i, int j, boolean[][] matrixNew) {
        int trueCounter = 0;
        //Boolean matrix is set to false by default
        boolean[] neighbourhoodArray = new boolean[8];
        fillNeighbourArray(i, j, neighbourhoodArray);

        for (int k = 0; k < neighbourhoodArray.length; k++) {
            if (neighbourhoodArray[k]) {
                trueCounter++;
            }
        }
        switch (trueCounter) {
            case 2:
            case 3:
                birthOrStayAlive(i, j, trueCounter, matrixNew);
                break;
            default:
                cellDies(i, j, matrixNew);
                break;
        }
    }

    /**
     * fillNeighbourArray(int i, int j, boolean[][] neighbourhoodArray)
     * This method writes the values of the surrounding neighbor cells
     * of a specific cell (i,j) of a matrix in the neighbourhoodArray.
     * Beforehand the value will be written in the array, it will be checked,
     * if this cell exists, so if the cell is not outside the matrix.
     *
     * @param i                  the current column index of the matrix
     * @param j                  the current row index of the matrix
     * @param neighbourhoodArray the array, in which the values will be stored
     */
    public void fillNeighbourArray(int i, int j, boolean[] neighbourhoodArray) {
        //upper-left cell
        if (i - 1 >= 0 && j - 1 >= 0) {
            neighbourhoodArray[0] = this.gameMatrix[i - 1][j - 1];
        }
        //upper-middle cell
        if (j - 1 >= 0) {
            neighbourhoodArray[1] = this.gameMatrix[i][j - 1];
        }
        //upper-right cell
        if (j - 1 >= 0 && i + 1 < gameMatrix.length) {
            neighbourhoodArray[2] = this.gameMatrix[i + 1][j - 1];
        }
        //middle-right cell
        if (i - 1 >= 0) {
            neighbourhoodArray[3] = this.gameMatrix[i - 1][j];
        }
        //middle-left cell
        if (i + 1 < gameMatrix.length) {
            neighbourhoodArray[4] = this.gameMatrix[i + 1][j];
        }
        //lower-left cell
        if (i - 1 >= 0 && j + 1 < gameMatrix[i].length) {
            neighbourhoodArray[5] = this.gameMatrix[i - 1][j + 1];
        }
        //lower-middle cell
        if (j + 1 < gameMatrix[i].length) {
            neighbourhoodArray[6] = this.gameMatrix[i][j + 1];
        }
        //lower-right cell
        if (i + 1 < gameMatrix.length && j + 1 < gameMatrix[i].length) {
            neighbourhoodArray[7] = this.gameMatrix[i + 1][j + 1];
        }
    }

    /**
     * birtOrStayAlive(int i, int j, int counter, boolean[][] matrixNew)
     * This function will be called, if a cell(i,j) has two or three neighbours.
     * If a cell is dead (=false) and has exactly three alive neighbours, it will
     * also get alive. If a cell is already alive and has two or three alive neighbours
     * the cell will stay alive.
     *
     * @param i         the current column index of the matrix
     * @param j         the current row index of the matrix
     * @param counter   the counter holds the amount of alive neighbours
     * @param matrixNew the auxiliary matrix in which the new values will be stored
     */
    public void birthOrStayAlive(int i, int j, int counter, boolean[][] matrixNew) {
        if (!this.gameMatrix[i][j] && counter == 3) {
            matrixNew[i][j] = true;
        } else if (this.gameMatrix[i][j] && (counter == 2 || counter == 3)) {
            matrixNew[i][j] = true;
        }
    }

    /**
     * cellDies(int i, int j, boolean[][] matrixNew)
     * This function sets a cell(i,j) to false and let it "die".
     *
     * @param i         the current column index of the matrix
     * @param j         the current row index of the matrix
     * @param matrixNew the auxiliary matrix in which the new values will be stored
     */
    public void cellDies(int i, int j, boolean[][] matrixNew) {
        matrixNew[i][j] = false;
    }

    /**
     * draw()
     * This method iterates on every cell within the matrix and calls
     * the drawSquare(i,j) method, which will then color the squares
     * corresponding to their true/false values.
     */
    public void draw() {
        for (int i = 0; i < gameMatrix.length; i++) {
            for (int j = 0; j < this.gameMatrix[i].length; j++) {
                drawSquare(i, j);
            }
        }
    }

    /**
     * drawSquare(int i, int j)
     * This method draws the cells and colors them corresponding
     * to there true/false value. Then it calls the drawAnt() function.
     *
     * @param i this variable holds the current column index of the matrix
     * @param j this variable holds the current row index of the matrix
     */
    public void drawSquare(int i, int j) {
        int x = OFFSET_X + i * SIZE;
        int y = OFFSET_Y + j * SIZE;
        if (this.gameMatrix[i][j]) {
            Drawing.graphics.setColor(Color.red);
        } else {
            Drawing.graphics.setColor(Color.white);
        }
        Drawing.graphics.fillRect(x, y, SIZE, SIZE);
        Drawing.graphics.setColor(Color.black);
        Drawing.graphics.drawRect(x, y, SIZE, SIZE);
        Drawing.paint();
    }

    /**
     * actionPerformed(ActionEvent e)
     * This method tells the ActionListener what to do after the delay
     *
     * @param e is the event.
     */
    public void actionPerformed(ActionEvent e) {
        generateCellGeneration();
        draw();
    }
}
