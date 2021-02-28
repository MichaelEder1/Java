/*--------------------------------------------------------
 * PolygonArray.java
 * Author: Michael Eder
 * Date 15.05.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type PolygonArray.
 * The class implements the Interface IPolygon. It manages a
 * Vector which is filled by objects of type Point2D.
 * Due it implements IPolygon, it has the functions: setFillColor,
 * appendPoint, expand, move and draw.
 *
 --------------------------------------------------------*/

package kwm.geom;

import kwm.Drawing;

import java.awt.*;
import java.util.Vector;

public class PolygonArray implements IPolygon {
    public Color color;
    public int number;
    public Vector<Point2D> pointsVector;

    public PolygonArray(int color) {
        this.color = new Color(color);
        this.pointsVector = new Vector<>();
    }

    public PolygonArray() {
        this(0);
    }

    /**
     * setFillColor(int color)
     * This method sets the color of the Polygon.
     *
     * @param color -> single int value which should be the color
     */
    public void setFillColor(int color) {
        this.color = new Color(color);
    }

    /**
     * appendPoint(Point2D point)
     * This method appends a new Point2D into the Vector.
     *
     * @param point -> Point2D object, which should be appended
     */
    public void appendPoint(Point2D point) {
        pointsVector.addElement(point);
        this.number++;
    }

    /**
     * expand (double f)
     * This function expands the every x and y value of a Point2D
     * object within the array by given factor f
     *
     * @param f -> the parameter, which the polygon should be expanded
     */
    public void expand(double f) {
        for (Point2D elems : pointsVector) {
            elems.x *= f;
            elems.y *= f;
        }
    }

    /**
     * move(int dx, int dy)
     * This method changes every x and y value of a Point2D object
     * within the whole array by given dx and dy value.
     *
     * @param dx -> value, which should be added to the x values
     * @param dy -> value, which should be added to the y values
     */
    public void move(int dx, int dy) {
        for (Point2D elem : pointsVector) {
            elem.x += dx;
            elem.y += dy;
        }
    }

    /**
     * draw()
     * This method draws a polygon, which includes all the point which are
     * stored in the Vector. But before, the x and y values of a
     * Point2D object will be separated and stored in two arrays, which
     * are as long as the array has entries. Then, the polygon will be
     * drawn with the help of the fillPolygon function.
     */
    public void draw() {
        if (this.number < 2) {
            System.out.println("Zu wenige Punkte zum Zeichnen!\nEs fehlt/fehlen noch " + (2 - this.number) + " Punkt(e)!");
            return;
        }
        int[] x = new int[this.number];
        int[] y = new int[this.number];

        for (int i = 0; i < this.number; i++) {
            x[i] = pointsVector.get(i).getX();
            y[i] = pointsVector.get(i).getY();
        }
        Drawing.graphics.setColor(this.color);
        Drawing.graphics.fillPolygon(x, y, this.number);
        Drawing.paint();
    }
}
