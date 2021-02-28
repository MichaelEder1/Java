/*--------------------------------------------------------
 * PolygonList.java
 * Author: Michael Eder
 * Date 15.05.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type PolygonList.
 * The class implements the Interface IPolygon. It manages a
 * linked list which is filled by objects of type Point2D.
 * Due it implements IPolygon, it has the functions: setFillColor,
 * appendPoint, expand, move and draw.
 *
 --------------------------------------------------------*/

package kwm.geom;

import kwm.Drawing;

import java.awt.*;

public class PolygonList implements IPolygon {
    public Color color;
    public Point2D head;
    public int numberOfPoints;

    public PolygonList(int color) {
        this.color = new Color(color);
        this.head = null;
        this.numberOfPoints = 0;
    }

    public PolygonList() {
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
     * This method appends a new Point2D into the linked list.
     * The point will be inserted at the end of the list.
     *
     * @param point -> Point2D object, which should be appended
     */
    public void appendPoint(Point2D point) {
        Point2D newPoint = new Point2D(point.getX(), point.getY());
        Point2D currentPoint = this.head;
        if (currentPoint == null) {
            this.head = newPoint;
        } else {
            while (currentPoint.next != null) {
                currentPoint = currentPoint.next;
            }
            currentPoint.next = newPoint;
        }
        this.numberOfPoints++;
    }

    /**
     * expand (double f)
     * This function expands the every x and y value of a Point2D
     * object within the list by given factor f
     *
     * @param f -> the parameter, which the polygon should be expanded
     */
    public void expand(double f) {
        Point2D currentPoint = this.head;
        while (currentPoint != null) {
            currentPoint.x *= f;
            currentPoint.y *= f;
            currentPoint = currentPoint.next;
        }
    }

    /**
     * move(int dx, int dy)
     * This method changes every x and y value of a Point2D object
     * within the whole list by given dx and dy value.
     *
     * @param dx -> value, which should be added to the x values
     * @param dy -> value, which should be added to the y values
     */
    public void move(int dx, int dy) {
        Point2D currentPoint = this.head;
        while (currentPoint != null) {
            currentPoint.x += dx;
            currentPoint.y += dy;
            currentPoint = currentPoint.next;
        }
    }

    /**
     * draw()
     * This method draws a polygon, which includes all the point which are
     * stored in the linked list. But before, the x and y values of a
     * Point2D object will be separated and stored in two arrays, which
     * are as long as the linked list has entries. Then, the polygon will be
     * drawn with the help of the fillPolygon function.
     */
    public void draw() {
        //X und Y Koordinaten separieren und in Array speichern!
        if (this.numberOfPoints < 2) {
            System.out.println("Zu wenige Punkte zum Zeichnen!\nEs wird/werden noch " + (2 - this.numberOfPoints) + " Punkt(e)!");
            return;
        }
        int[] x = new int[this.numberOfPoints];
        int[] y = new int[this.numberOfPoints];
        int i = 0;
        Point2D currentPoint = this.head;
        while (currentPoint != null) {
            x[i] = currentPoint.getX();
            y[i] = currentPoint.getY();
            i++;
            currentPoint = currentPoint.next;
        }

        Drawing.graphics.setColor(this.color);
        Drawing.graphics.fillPolygon(x, y, this.numberOfPoints);
        Drawing.paint();
    }
}
