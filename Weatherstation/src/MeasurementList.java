/*--------------------------------------------------------
 * MeasurementList.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type MeasurementList
 * MeasurementList implements IStatistics and stores
 * a list of MeasurementValues. It implements all functions
 * of the Interface IStatistics.
 --------------------------------------------------------*/
package kwm.statistics;

import java.util.GregorianCalendar;

public class MeasurementList implements IStatistics
{
    public MeasurementValue head;
    public int numberOfEntries;
    
    /**
     * addSample(String name, GregorianCalender date, double value)
     * This method adds a new Object from type MeasurementValue into the list.
     *
     * @param name  -> the name of the measuring station
     * @param date  -> the date temperature the value was measured
     * @param value -> the value of temperature
     */
    public void addSample(String name, GregorianCalendar date, double value)
    {
        MeasurementValue newVal = new MeasurementValue(name, date, value);
        MeasurementValue currentVal = this.head;
        if (currentVal == null)
        {
            this.head = newVal;
            this.numberOfEntries++;
            return;
        }
        while (currentVal.next != null)
        {
            currentVal = currentVal.next;
        }
        currentVal.next = newVal;
        this.numberOfEntries++;
    }
    
    /**
     * /**
     * getMeanTemperature(int month)
     * This method returns the mean value of the measured data in the list.
     * Depending on the given param, it calcs the mean value for a month or for all.
     *
     * @param month -> given month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns meanTemperature as double
     */
    public double getMeanTemperature(int month)
    {
        MeasurementList list2 = this;
        if (month < 11 && month >= 0)
        {
            list2 = filterList(list2, month);
        }
        MeasurementValue currentVal = list2.head;
        double meanTemperature = 0;
        int counter = 0;
        while (currentVal != null)
        {
            meanTemperature += currentVal.temperature;
            counter++;
            currentVal = currentVal.next;
        }
        return meanTemperature / counter;
    }
    
    /**
     * getMedianTemperature(int month)
     * This method calculates the median value of the temperatures.
     * Depending on the given param, it calculates the mean value for a month or for all.
     * To calculate the median, the list has to be sorted ascending. For this, it
     * will be sorted.
     *
     * @param month -> given month for calculating mean value, if value is not a
     *              valid month, it will be calculated for every month.
     * @return -> returns median value as double.
     */
    public double getMedianTemperature(int month)
    {
        MeasurementList list2 = new MeasurementList();
        sortList(list2);
        if (month < 11 && month >= 0)
        {
            list2 = filterList(list2, month);
        }
        MeasurementValue currentVal = list2.head;
        MeasurementValue prev = null;
        for (int i = 0; i < (list2.numberOfEntries / 2); i++)
        {
            prev = currentVal;
            currentVal = currentVal.next;
        }
        if (list2.numberOfEntries % 2 != 0)
        {
            return currentVal.temperature;
        }
        assert prev != null;
        return ((prev.temperature + currentVal.temperature) / 2);
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
            MeasurementList list2;
            list2 = filterList(this, month);
            variance = list2.calcVariance();
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
        MeasurementValue currentVal = this.head;
        while (currentVal != null)
        {
            double x = currentVal.temperature - average;
            result += Math.pow(x, 2);
            currentVal = currentVal.next;
        }
        return result / this.numberOfEntries;
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
        MeasurementValue currentVal = this.head;
        while (currentVal != null)
        {
            average += currentVal.temperature;
            currentVal = currentVal.next;
        }
        return average / this.numberOfEntries;
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
        MeasurementList list2 = new MeasurementList();
        sortList(list2);
        if (month < 11 && month >= 0)
        {
            list2 = filterList(list2, month);
        }
        MeasurementValue currentValue = list2.head;
        while (currentValue != null)
        {
            currentValue.printValue();
            currentValue = currentValue.next;
        }
        
        System.out.println("--------------------------------------------");
        
        long startMeanTemperature = System.currentTimeMillis();
        double meanTemperature = list2.getMeanTemperature(month);
        long endMeanTemperature = System.currentTimeMillis();
        
        System.out.println("Mittelwert: " + meanTemperature);
        
        long startMedianTemperature = System.currentTimeMillis();
        double medianTemperature = list2.getMedianTemperature(month);
        long endMedianTemperature = System.currentTimeMillis();
        
        System.out.println("Median: " + medianTemperature);
        
        long startVariance = System.currentTimeMillis();
        double variance = list2.getVariance(month);
        long endVariance = System.currentTimeMillis();
        
        System.out.println("Varianz: " + variance);
        
        long startStandardDerivation = System.currentTimeMillis();
        double standardDeviation = list2.getStandardDeviation(month);
        long endStandardDerivation = System.currentTimeMillis();
        
        System.out.println("Standard Abweichung: " + standardDeviation);
        
        System.out.println("--------------------------------------------");
        
        System.out.println("Zeit Mittelwert: " + (endMeanTemperature - startMeanTemperature) + " Millisekunden");
        System.out.println("Zeit Median: " + (endMedianTemperature - startMedianTemperature) + " Millisekunden");
        System.out.println("Zeit Varianz: " + (endVariance - startVariance) + " Millisekunden");
        System.out.println("Zeit Standardabweichung: " + (endStandardDerivation - startStandardDerivation) + " Millisekunden");
        
        System.out.println("--------------------------------------------");
    }
    
    /**
     * sortList(MeasurementArray array)
     * This method sorts the list by ascending order.
     * For this, the auxiliary method insertSorted is called, in each
     * loop iteration.
     *
     * @param list2 -> The array, which should be sorted.
     */
    public void sortList(MeasurementList list2)
    {
        MeasurementValue currentVal = this.head;
        while (currentVal != null)
        {
            list2.insertSorted(list2, currentVal);
            currentVal = currentVal.next;
        }
    }
    
    /**
     * insertSorted(MeasurementListe list2, MeasurementValue measurementValue)
     * This method sorts the given measurementValue into the given list2.
     *
     * @param list2 -> the list, in which the given value should be sorted in
     * @param measurementValue -> the value, which should be sorted.
     * @return -> returns sorted list
     */
    public MeasurementList insertSorted(MeasurementList list2, MeasurementValue measurementValue)
    {
        MeasurementValue prev = null; //saves previous node
        MeasurementValue currentVal = list2.head; //save firstNode of list
        MeasurementValue newVal = new MeasurementValue(measurementValue.name, measurementValue.date, measurementValue.temperature);
        
        //as long as there are tasks AND the temperature is smaller than temperature of given one
        while (currentVal != null && currentVal.temperature < measurementValue.temperature)
        {
            prev = currentVal;
            currentVal = currentVal.next;
        }
        //Exception: the found element is the head = insert value before head = creating new firstNode
        if (prev == null)
        {
            //sets the new measurementValue as head
            this.head = newVal;
        }
        else
        {
            prev.next = newVal;
        }
        newVal.next = currentVal;
        this.numberOfEntries++;
        return list2;
    }
    
    /**
     * filterList (month)
     * This method filters all entries in the list for given month and saves them in new list.
     *
     * @param month -> the month which should be filtered
     * @return -> returns new MeasurementList only containing values for specific month
     */
    public MeasurementList filterList(MeasurementList list2, int month)
    {
        MeasurementList list3 = new MeasurementList();
        MeasurementValue currentVal = list2.head;
        while (currentVal != null)
        {
            if (month == currentVal.getMonth())
            {
                list3.addSample(currentVal.name, currentVal.date, currentVal.temperature);
            }
            currentVal = currentVal.next;
        }
        return list3;
    }
}
