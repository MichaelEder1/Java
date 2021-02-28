package kwm.geom;

import kwm.Drawing;

import java.awt.*;

public class Point2D
{
    public int x;
    public int y;
    public int radius;
    public Color color;
    public Point2D next = null;

    public Point2D(int x, int y, int radius, Color color)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public Point2D(int x, int y, int radius)
    {
        this(x, y, radius, new Color(0, 0, 0));
    }

    public Point2D(int x, int y)
    {
        this(x, y, 5, new Color(0, 0, 0));
    }

    public Point2D()
    {
        this(0, 0, 5, new Color(0, 0, 0));
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }


    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int getRadius()
    {
        return radius;
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
    }


    public void drawPoint()
    {
        Drawing.graphics.setColor(this.color);
        Drawing.graphics.fillOval(this.x - this.radius, this.y - this.radius,
            this.radius * 2, this.radius * 2);
    }
}