/*--------------------------------------------------------
 * AntMatrix.java
 * Author: Michael Eder
 * Date 10.04.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type AntMatrix
 * The AntMatrix is a matrix with size width by height.
 * The functions can be used to start the animation,
 * to move the ant and to draw it. By calling the startAnimation()
 * function a timer with a delay of 1s is started and will
 * endlessly call the nextSecond and draw functions.
 *
 --------------------------------------------------------*/
package kwm.ant;

import kwm.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AntMatrix implements ActionListener
{
    //Global variables
    public static final int OFFSET_X = 50;
    public static final int OFFSET_Y = 50;
    public static final int SIZE = 30;
    
    public Timer timer;
    public static int delay = 1000;
    public boolean[][] antMatrix;
    //posX and posY saves the current position of the ant
    public int posX;
    public int posY;
    //direction saves the current direction the ant is looking at
    public int direction;
    
    public AntMatrix(int width, int height)
    {
        //By default, all fields in a boolean matrix are set to false,
        //so we don't need to set them to false manually
        this.antMatrix = new boolean[width][height];
        //defines the first random coordinates of the ant
        initiateAnt();
    }
    
    /**
     * initiateAnt()
     * This method creates a random position in the matrix where the ant will
     * be placed. Also the direction it looks will be randomly determined.
     */
    public void initiateAnt()
    {
        //get random x and y coordinates within the matrix for the ant
        this.posX = getRandomNumber(this.antMatrix.length);
        this.posY = getRandomNumber(this.antMatrix[0].length);
        //get random direction, in which the ant will look at spawn
        this.direction = getRandomNumber(4);
        //sets the first field the ant appears to true
        this.antMatrix[this.posX][this.posY] = true;
    }
    
    /**
     * getRandomNumber(int max)
     * This method creates a random number between 0 and max.
     *
     * @param max This is the highest number that could be generated
     * @return random Number between 0 and max as integer.
     */
    public int getRandomNumber(int max)
    {
        return (int) (Math.random() * max);
    }
    
    /**
     * startAnimation()
     * This method starts the Timer
     */
    public void startAnimation()
    {
        this.timer = new Timer(AntMatrix.delay, this);
        this.timer.start();
    }
    
    /**
     * actionPerformed(ActionEvent e)
     * This method tells the ActionListener what to do after the delay
     *
     * @param e is the event.
     */
    public void actionPerformed(ActionEvent e)
    {
        nextSecond();
        draw();
    }
    
    /**
     * nextSecond()
     * This method defines what the program should do in the next second.
     * It determines the boolean value of the cell, then applies the rule
     * and last calls the moveAnt() function to move the ant
     */
    public void nextSecond()
    {
        if (this.antMatrix[this.posX][this.posY])
        {
            //Ant is on white field
            ruleOne();
        }
        else
        {
            //Ant is not on white field
            ruleTwo();
        }
        moveAnt();
    }
    
    /**
     * ruleOne()
     * This method applies, when the cell of the ant is true.
     * The ant turns right and sets the cell to false
     */
    public void ruleOne()
    {
        //change direction - ant turns right
        this.direction--;
        this.antMatrix[this.posX][this.posY] = false;
    }
    
    /**
     * ruleTwo()
     * This method applies, when the cell of the ant is false.
     * The ant turns left and sets the cell to true.
     */
    public void ruleTwo()
    {
        //change direction - ant turns left
        this.direction++;
        this.antMatrix[this.posX][this.posY] = true;
    }
    
    /**
     * moveAnt()
     * This method determines, in which direction the ant goes
     * depending on the value in the direction variable.
     */
    public void moveAnt()
    {
        //avoid getting false values for direction
        if (this.direction == 4)
        {
            this.direction = 0;
        }
        if (this.direction == -1)
        {
            this.direction = 3;
        }
        
        //define direction of the ant
        switch (this.direction)
        {
            case 0:
                moveUp();
                break;
            case 1:
                moveRight();
                break;
            case 2:
                moveDown();
                break;
            case 3:
                moveLeft();
                break;
        }
    }
    
    /**
     * moveUp()
     * This method moves the ant upwards,
     * but before, it checks, if the ant is on an edge, and if
     * the ant will switch to the other side of the matrix.
     */
    public void moveUp()
    {
        if (this.posY - 1 == -1)
        {
            this.posY = this.antMatrix[0].length - 1;
        }
        else
        {
            this.posY--;
        }
    }
    
    /**
     * moveRight()
     * This method moves the ant in the right direction,
     * but before, it checks, if the ant is on an edge, and if
     * the ant will switch to the other side of the matrix.
     */
    public void moveRight()
    {
        if (this.posX + 1 == this.antMatrix.length)
        {
            this.posX = 0;
        }
        else
        {
            this.posX++;
        }
    }
    
    /**
     * moveDown()
     * This method moves the ant downwards,
     * but before, it checks, if the ant is on an edge, and if
     * the ant will switch to the other side of the matrix.
     */
    public void moveDown()
    {
        if (this.posY + 1 == this.antMatrix[0].length)
        {
            this.posY = 0;
        }
        else
        {
            this.posY++;
        }
    }
    
    /**
     * moveLeft()
     * This method moves the ant in the left direction,
     * but before, it checks, if the ant is on an edge, and if
     * the ant will switch to the other side of the matrix.
     */
    public void moveLeft()
    {
        if (this.posX - 1 < 0)
        {
            this.posX = this.antMatrix.length - 1;
        }
        else
        {
            this.posX--;
        }
    }
    
    /**
     * draw()
     * This method iterates on every cell within the matrix and calls
     * the drawSquare(i,j) method, which will then color the squares
     * corresponding to their true/false values.
     */
    public void draw()
    {
        for (int i = 0; i < this.antMatrix.length; i++)
        {
            int height = this.antMatrix[i].length;
            for (int j = 0; j < height; j++)
            {
                drawSquare(i, j);
            }
        }
        Drawing.paint();
    }
    
    /**
     * drawSquare(int i, int j)
     * This method draws the cells and colors them corresponding
     * to there true/false value. Then it calls the drawAnt() function.
     *
     * @param i this variable holds the current column index of the matrix
     * @param j this variable holds the current row index of the matrix
     */
    public void drawSquare(int i, int j)
    {
        //these two variables define the coordinates of the
        //left upper corner of a cell in matrix
        int x = OFFSET_X + i * SIZE;
        int y = OFFSET_Y + j * SIZE;
        
        if (this.antMatrix[i][j])
        {
            Drawing.graphics.setColor(Color.gray);
        }
        else
        {
            Drawing.graphics.setColor(Color.white);
        }
        Drawing.graphics.fillRect(x, y, SIZE, SIZE);
        Drawing.graphics.setColor(Color.black);
        Drawing.graphics.drawRect(x, y, SIZE, SIZE);
        drawAnt();
    }
    
    /**
     * drawAnt()
     * This method calls the function in which the ant should be moved
     * depending on the value of direction variable and draws it.
     */
    public void drawAnt()
    {
        //these two variables define the coordinates of the
        //left upper corner of a cell in matrix
        int x = OFFSET_X + this.posX * SIZE;
        int y = OFFSET_Y + this.posY * SIZE;
        Color color = Color.red;
        
        switch (this.direction)
        {
            case 0:
                drawUpAnt(x, y, color);
                break;
            case 1:
                drawRightAnt(x, y, color);
                break;
            case 2:
                drawLowAnt(x, y, color);
                break;
            case 3:
                drawLeftAnt(x, y, color);
                break;
        }
    }
    
    /**
     * drawRightAnt(int x, int y, Color color)
     * This method draws a right-looking ant (as a triangle) into the cell
     *
     * @param x     defines x Coordinate of the upper left corner of the cell
     * @param y     defines y Coordinate of the upper left corner of the cell
     * @param color defines the color of the ant
     */
    public void drawRightAnt(int x, int y, Color color)
    {
        Drawing.graphics.setColor(color);
        Drawing.graphics.fillPolygon(new int[]{(x + SIZE / 2), (x + SIZE), (x + SIZE / 2)}, new int[]{y, (y + SIZE / 2), (y + SIZE)}, 3);
        Drawing.paint();
    }
    
    /**
     * drawLeft(int x, int y, Color color)
     * This method draws a left-looking ant (as a triangle) into the cell
     *
     * @param x     defines x Coordinate of the upper left corner of the cell
     * @param y     defines y Coordinate of the upper left corner of the cell
     * @param color defines the color of the ant
     */
    public void drawLeftAnt(int x, int y, Color color)
    {
        Drawing.graphics.setColor(color);
        Drawing.graphics.fillPolygon(new int[]{(x + SIZE / 2), x, (x + SIZE / 2)}, new int[]{y, (y + SIZE / 2), (y + SIZE)}, 3);
        Drawing.paint();
    }
    
    /**
     * drawUpAnt(int x, int y, Color color)
     * This method draws a up-looking ant (as a triangle) into the cell
     *
     * @param x     defines x Coordinate of the upper left corner of the cell
     * @param y     defines y Coordinate of the upper left corner of the cell
     * @param color defines the color of the ant
     */
    public void drawUpAnt(int x, int y, Color color)
    {
        Drawing.graphics.setColor(color);
        Drawing.graphics.fillPolygon(new int[]{x + SIZE / 2, x + SIZE, x}, new int[]{y + SIZE / 2, y + SIZE, y + SIZE}, 3);
    }
    
    /**
     * drawLowAnt(int x, int y, Color color)
     * This method draws a low-looking ant (as a triangle) into the cell
     *
     * @param x     defines x Coordinate of the upper left corner of the cell
     * @param y     defines y Coordinate of the upper left corner of the cell
     * @param color defines the color of the ant
     */
    public void drawLowAnt(int x, int y, Color color)
    {
        Drawing.graphics.setColor(color);
        Drawing.graphics.fillPolygon(new int[]{x, x + SIZE, x + SIZE / 2}, new int[]{y, y, y + SIZE / 2}, 3);
    }
}