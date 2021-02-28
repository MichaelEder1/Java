/*--------------------------------------------------------
 * IPolygon.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This Interface will implement several functions to
 * manage and draw Point2D objects. This interfaces is implemented
 * by PolygonArray and PolygonList.
 --------------------------------------------------------*/

package kwm.geom;

public interface IPolygon
{
    public void setFillColor(int color);
    public void appendPoint(Point2D point);
    public void expand(double f);
    public void move(int dx, int dy);
    public void draw();
}
