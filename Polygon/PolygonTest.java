/*--------------------------------------------------------
 * PolygonTest.java
 * Author: Michael Eder
 * Date 15.05.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the functionality of the
 * PolygonArray and PolygonList class. Therefore, two
 * objects will be created with help of the IPolygon interface.
 * Then, some points are added to the object and then drawn.
 *
 --------------------------------------------------------*/

import kwm.geom.*;
import kwm.*;

public class PolygonTest {
    public static void main(String[] args) {
        Drawing.begin("IPolygon");
        IPolygon polygon = new PolygonArray(200);

        //Drawing a "M"
        polygon.appendPoint(new Point2D(50, 200));
        polygon.appendPoint(new Point2D(50, 50));
        polygon.appendPoint(new Point2D(75, 50));
        polygon.appendPoint(new Point2D(100, 100));
        polygon.appendPoint(new Point2D(125, 50));
        polygon.appendPoint(new Point2D(150, 50));
        polygon.appendPoint(new Point2D(150, 200));
        polygon.appendPoint(new Point2D(125, 200));
        polygon.appendPoint(new Point2D(125, 100));
        polygon.appendPoint(new Point2D(100, 150));
        polygon.appendPoint(new Point2D(75, 100));
        polygon.appendPoint(new Point2D(75, 200));
        polygon.draw();

        IPolygon polygon2 = new PolygonList();
        //calculate RGB value of a color into an int value: http://www.shodor.org/stella2java/rgbint.html
        polygon2.setFillColor(16753920);
        //Drawing a "E"
        polygon2.appendPoint(new Point2D(50, 200));
        polygon2.appendPoint(new Point2D(50, 50));
        polygon2.appendPoint(new Point2D(125, 50));
        polygon2.appendPoint(new Point2D(125, 75));
        polygon2.appendPoint(new Point2D(75, 75));
        polygon2.appendPoint(new Point2D(75, 105));
        polygon2.appendPoint(new Point2D(125, 105));
        polygon2.appendPoint(new Point2D(125, 135));
        polygon2.appendPoint(new Point2D(75, 135));
        polygon2.appendPoint(new Point2D(75, 170));
        polygon2.appendPoint(new Point2D(125, 170));
        polygon2.appendPoint(new Point2D(125, 200));
        polygon2.appendPoint(new Point2D(50, 200));
        polygon2.expand(2.0);
        polygon2.move(100, 0);
        polygon2.draw();

        Drawing.end();
    }
}
