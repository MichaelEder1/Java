/*--------------------------------------------------------
 * AntMatrixTest.java
 * Author: Michael Eder
 * Date 10.04.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the functionality of
 * the AntMatrix class. Therefore an object from type
 * AntMatrix was created.
 --------------------------------------------------------*/
import kwm.*;
import kwm.ant.*;
public class AntMatrixTest
{
    public static void main(String[] args)
    {
        Drawing.begin("AntMatrix",600, 600);
        AntMatrix matrix = new AntMatrix(10,10);
        matrix.startAnimation();
        Drawing.end();
    }
}
