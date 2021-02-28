/*--------------------------------------------------------
 * MeasurementArray.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type MeasurementArray
 * MeasurementArray implements IStatistics and stores
 * an array of MeasurementValues. It implements all functions
 * of the Interface IStatistics.
 --------------------------------------------------------*/
package kwm.statistics;

import java.util.GregorianCalendar;

public class MeasurementArray implements IStatistics
{
    public MeasurementValue[] a = new MeasurementValue[10];
    public int number = 0;
    
    /**
     * addSample(String name, GregorianCalender date, double value)
     * This method adds a new Object from type MeasurementValue into
     * the array. But before, it checks, if there is enough space.
     *
     * @param name  -> the name of the measuring station
     * @param date  -> the date temperature the value was measured
     * @param value -> the value of temperature
     */
    public void addSample(String name, GregorianCalendar date, double value)
    {
        if (this.number >= a.length)
        {
            resize();
        }
        a[this.number] = new MeasurementValue(name, date, value);
        this.number++;
    }
    
    /**
     * getMeanTemperature(int month)
     * This method returns the mean value of the measured data in the array.
     * Depending on the given param, it calculates the mean value for a month or for all.
     *
     * @param month -> given month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns meanTemperature as double
     */
    public double getMeanTemperature(int month)
    {
        double meanTemperature = 0;
        
        MeasurementArray array2 = this;
        if (month < 11 && month >= 0)
        {
            array2 = filterArray(month);
        }
        for (int i = 0; i < array2.number; i++)
        {
            meanTemperature += array2.a[i].temperature;
        }
        return meanTemperature / array2.number;
    }
    
    /**
     * resize()
     * This method doubles the size of the array, when it is full
     */
    public void resize()
    {
        int n = this.number;
        MeasurementValue[] newArr = new MeasurementValue[n * 2];
        System.arraycopy(this.a, 0, newArr, 0, n);
        this.a = newArr;
    }
    
    /**
     * getMedianTemperature(int month)
     * This method calculates the median value of the temperatures.
     * Depending on the given param, it calculates the mean value for a month or for all.
     * To calculate the median, the array has to be sorted ascending. For this, it
     * will be sorted.
     *
     * @param month -> given month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns median value as double.
     */
    public double getMedianTemperature(int month)
    {
        MeasurementArray array2 = sortArray(this);
        if (month < 11 && month >= 0)
        {
            array2 = filterArray(month);
        }
        if (array2.number == 0)
        {
            return 0.0;
        }
        if (array2.number % 2 != 0)
        {
            return array2.a[(array2.number / 2)].temperature;
        }
        return ((array2.a[((array2.number / 2) - 1)].temperature + array2.a[(array2.number / 2)].temperature) / 2);
    }
    
    /**
     * filterArray (month)
     * This method filters all entries in the array for given month and saves them in new array.
     *
     * @param month -> the month which should be filtered
     * @return -> returns new MeasurementArray only containing values for specific month
     */
    public MeasurementArray filterArray(int month)
    {
        MeasurementArray result = new MeasurementArray();
        for (int i = 0; i < this.number; i++)
        {
            if (this.a[i].getMonth() == month)
            {
                result.addSample(this.a[i].name, this.a[i].date, this.a[i].temperature);
            }
        }
        return result;
    }
    
    /**
     * sortArray(MeasurementArray array)
     * This method sorts the array by ascending order.
     * For this, the auxiliary method insertSorted is called, in each
     * loop iteration. At the end, the sorted array will be returned.
     *
     * @param array -> The array, which should be sorted.
     * @return -> returns sorted array.
     */
    public MeasurementArray sortArray(MeasurementArray array)
    {
        for (int i = 0; i < array.number; i++)
        {
            array = insertSorted(array);
        }
        return array;
    }
    
    /**
     * insertSorted(MeasurementArray array)
     * This method sorts the array. For this, we
     * use the insertionSort Algorithm.
     *
     * @param array -> the array, which should be sorted
     * @return -> returns sorted array
     */
    public MeasurementArray insertSorted(MeasurementArray array)
    {
        int n = array.number;
        for (int i = 1; i < n; i++)
        {
            MeasurementValue x = this.a[i];
            int j = i - 1;
            while (j >= 0 && this.a[j].temperature > x.temperature)
            {
                array.a[j + 1] = this.a[j];
                j = j - 1;
            }
            array.a[j + 1] = x;
        }
        return array;
    }
    
    /**
     * getVariance(int month)
     * This method calculates the variance of temperature values.
     * Depending on the given param, it calculates the variance for a month or for all.
     *
     * @param month -> given month for calculating variance, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns variance as double
     */
    public double getVariance(int month)
    {
        double variance;
        if (month < 11 && month >= 0)
        {
            MeasurementArray array2 = filterArray(month);
            variance = array2.calcVariance();
        }
        else
        {
            variance = this.calcVariance();
        }
        return variance;
    }
    
    /**
     * calcVariance()
     * This method calculates the variance
     *
     * @return -> returns variance
     */
    public double calcVariance()
    {
        double result = 0;
        double average = this.getAverage();
        for (int i = 0; i < this.number; i++)
        {
            double x = this.a[i].temperature - average;
            result += Math.pow(x, 2);
        }
        return result / this.number;
    }
    
    /**
     * getAverage()
     * This method calculates the avaerage.
     *
     * @return -> returns average.
     */
    public double getAverage()
    {
        double average = 0;
        for (int i = 0; i < this.number; i++)
        {
            average += this.a[i].temperature;
        }
        return average / this.number;
    }
    
    /**
     * getStandardDerivation(int month)
     * This method calculates the standard derivation.
     * Depending on the given param, it calculates the standard derivation for a month or for all.
     *
     * @param month -> given month for calculating standard derivation, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns standard derivation as double
     */
    public double getStandardDeviation(int month)
    {
        return Math.sqrt(getVariance(month));
    }
    
    /**
     * printSample(int month)
     * This method prints all samples for a month or for all and the calculated results
     * from above functions and the millisecond they need to run.
     *
     * @param month -> given month for printing MeasurementValues, if value is not a
     *              valid month, MeasurementValues for every month will be printed.
     */
    public void printSample(int month)
    {
        MeasurementArray array2 = sortArray(this);
        if (month < 11 && month >= 0)
        {
            array2 = filterArray(month);
        }
        if (array2.number == 0)
        {
            System.out.println("Keine Einträge für Monat " + month + " gefunden!");
            return;
        }
        for (int i = 0; i < array2.number; i++)
        {
            array2.a[i].printValue();
        }
        
        System.out.println("--------------------------------------------");
        
        long startMeanTemperature = System.currentTimeMillis();
        double meanTemperature = array2.getMeanTemperature(month);
        long endMeanTemperature = System.currentTimeMillis();
        
        System.out.println("Mittelwert: " + meanTemperature);
        
        long startMedianTemperature = System.currentTimeMillis();
        double medianTemperature = array2.getMedianTemperature(month);
        long endMedianTemperature = System.currentTimeMillis();
        
        System.out.println("Median: " + medianTemperature);
        
        long startVariance = System.currentTimeMillis();
        double variance = array2.getVariance(month);
        long endVariance = System.currentTimeMillis();
        
        System.out.println("Varianz: " + variance);
        
        long startStandardDerivation = System.currentTimeMillis();
        double standardDeviation = array2.getStandardDeviation(month);
        long endStandardDerivation = System.currentTimeMillis();
        
        System.out.println("Standard Abweichung: " + standardDeviation);
        
        System.out.println("--------------------------------------------");
        
        System.out.println("Zeit Mittelwert: " + (endMeanTemperature - startMeanTemperature) + " Millisekunden");
        System.out.println("Zeit Median: " + (endMedianTemperature - startMedianTemperature) + " Millisekunden");
        System.out.println("Zeit Varianz: " + (endVariance - startVariance) + " Millisekunden");
        System.out.println("Zeit Standardabweichung: " + (endStandardDerivation - startStandardDerivation) + " Millisekunden");
        
        System.out.println("--------------------------------------------");
    }
}
