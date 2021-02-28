/*--------------------------------------------------------
 * MeasurementValue.java
 * Author: Michael Eder
 * Date 10.05.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type MeasurementValue.
 * This object saves the name of a measure station, the date, and
 * a temperature value and a next pointer. This is the Object-type
 * which will be stored in the MeasurementArray and MeasurementList.
 --------------------------------------------------------*/
package kwm.statistics;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class MeasurementValue
{
    public String name;
    public GregorianCalendar date;
    public double temperature;
    public MeasurementValue next = null;
    
    public MeasurementValue(String name, GregorianCalendar date, double value)
    {
        this.name = name;
        this.date = date;
        this.temperature = value;
    }
    
    /**
     * getMonth()
     * This method returns the month of the current MeasurementValue
     * @return -> returns month of this MeasurementValue
     */
    public int getMonth()
    {
        return this.date.get(GregorianCalendar.MONTH);
    }
    
    /**
     * printValue()
     * This method prints this MeasurementValue in following form:
     * "Name: 'name' | Datum: 'date' | Temperatur: 'temperature'"
     */
    public void printValue()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = sdf.format(this.date.getTime());
        System.out.println("Name: " + this.name + " | Datum: " + formattedDate + " | Temperatur: " + this.temperature);
    }
}
