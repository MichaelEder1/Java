/*--------------------------------------------------------
 * MeasurementTest.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the functionality of
 * the MeasurementList/Measurement class. Therefore a
 * MeasurementList/MeasurementArray object from type IStatistics
 * will be created and than, the functions will be tested.
 --------------------------------------------------------*/
import kwm.statistics.*;

import java.util.GregorianCalendar;

public class MeasurementTest
{
    public static void main(String[] args)
    {
        IStatistics container = new MeasurementArray();
        
        //For-Loop to test the program with lots of entries.
        /*long startAddSample = System.currentTimeMillis();
        for (int i = 0; i < 40000; i++)
        {
            generateRandomSample(container);
        }
        long endAddSample = System.currentTimeMillis();*/
        
        long startAddSample = System.currentTimeMillis();
        container.addSample("Station1", new GregorianCalendar(2020, 4, 6), 22.4);
        container.addSample("Station2", new GregorianCalendar(2020, 2, 10), 10.3);
        container.addSample("Station3", new GregorianCalendar(2020, 3, 3), 15.7);
        container.addSample("Station4", new GregorianCalendar(2020, 5, 4), 28.6);
        container.addSample("Station5", new GregorianCalendar(2020, 4, 29), 23.1);
        container.addSample("Station6", new GregorianCalendar(2020, 5, 9), 13.4);
        container.addSample("Station7", new GregorianCalendar(2020,3,9), 12.4);
        long endAddSample = System.currentTimeMillis();
        
        container.printSample(12);
        System.out.println("Zeit addSample: " + (endAddSample - startAddSample) + " Millisekunden");
    }
    
    /**
     * generateRandomSample(Istatistics container)
     * This method creates random samples of mesaurement values, which will be
     * added to the IStatistics "container".
     * @param container -> the IStatistics object, which the value should be added
     */
    public static void generateRandomSample(IStatistics container)
    {
        int day = (int) getRandomNumber(30);
        int month = (int) getRandomNumber(11);
        double temperature = getRandomNumber(44);
        double roundOff = Math.round(temperature * 10.0) / 10.0;
        container.addSample("radomStation", new GregorianCalendar(2020, month, day), roundOff);
    }
    
    /**
     * getRandomNumber(int max)
     * calculates a random number between 0 and the given max param.
     * @param max -> the highest possible random number
     * @return -> returns a random number between 0 and max.
     */
    public static double getRandomNumber(int max)
    {
        return Math.random() * max;
    }
}
