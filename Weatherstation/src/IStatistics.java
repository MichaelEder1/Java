/*--------------------------------------------------------
 * IStatistics.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This Interface will implement several functions to
 * calculate statistical value. This interfaces is implemented
 * by MeasurementArray and MeasurementList.
 --------------------------------------------------------*/
package kwm.statistics;

import java.util.GregorianCalendar;

public interface IStatistics
{
    /**
     * addSample()
     * adds a new sample into the IStatistics object
     *
     * @param name  -> the name of the measuring station
     * @param date  -> the date temperature the value was measured
     * @param value -> the value of temperature
     */
    public void addSample(String name, GregorianCalendar date, double value);
    
    /**
     * getMeanTemperature(int month)
     * This method returns the mean value of the measured data in a IStatistics object.
     * Depending on the given param, it calculates the mean value for a month or for all.
     *
     * @param month -> month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns meanTemperature as double
     */
    public double getMeanTemperature(int month);
    
    /**
     * getMedianTemperature(int month)
     * This method calculates the median value of the temperatures.
     * Depending on the given param, it calculates the mean value for a month or for all.
     *
     * @param month -> month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns median value as double.
     */
    public double getMedianTemperature(int month);
    
    /**
     * getVariance(int month)
     * This method calculates the variance of temperature values.
     * Depending on the given param, it calculates the variance for a month or for all.
     *
     * @param month -> month for calculating variance, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns variance as double
     */
    public double getVariance(int month);
    
    /**
     * getStandardDerivation(int month)
     * This method calculates the standard derivation.
     * Depending on the given param, it calculates the standard derivation for a month or for all.
     *
     * @param month -> month for calculating standard derivation, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns standard derivation as double
     */
    public double getStandardDeviation(int month);
    
    /**
     * printSample(int month)
     * This method prints all samples for a month or for all.
     *
     * @param month -> month for printing MeasurementValues, if value is not a
     *              valid month, MeasurementValues for every month will be printed.
     */
    public void printSample(int month);
}
