/*--------------------------------------------------------
 * Task.java
 * Author: Michael Eder
 * Date 28.04.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type Task
 * Task should represent a Node in another class TaskList.
 * A task consists of a unique id, a description, a processor,
 * a due date and a next-Pointer which refers to the next task.
 *
 --------------------------------------------------------*/

package kwm.tasklist;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Task
{
    public String id;
    public String bezeichnung;
    public String bearbeiter;
    public boolean status;
    public GregorianCalendar datum;
    public Task next;
    
    public Task(String id, String bezeichnung, String bearbeiter, boolean status, int year, int month, int day, Task next)
    {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.bearbeiter = bearbeiter;
        this.datum = new GregorianCalendar(year, month, day);
        this.status = status;
        this.next = next;
    }
    
    public Task(String id, String bezeichnung, String bearbeiter, boolean status, int year, int month, int day)
    {
        this(id, bezeichnung, bearbeiter, status, year, month, day, null);
    }
    
    /**
     * getNewTask()
     * This method returns a new object from datatype Task with this values.
     * @return
     */
    public Task getNewTask()
    {
        return new Task(this.id, this.bezeichnung, this.bearbeiter, this.status, this.datum.get(GregorianCalendar.YEAR), this.datum.get(GregorianCalendar.MONTH), this.datum.get(GregorianCalendar.DAY_OF_MONTH));
    }
    
    /**
     * output()
     * This method prints an Task in form:
     * '"ID: " id " | Bezeichnung: " bezeichnung " | Bearbeiter: " bearbeiter " | Status: " status " | Deadline: " date'
     */
    public void output()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String date = sdf.format(datum.getTime());
        System.out.print("ID: " + this.id + " | Bezeichnung: " + this.bezeichnung + " | Bearbeiter: " + this.bearbeiter + " | Status: " + this.status + " | Deadline: " + date);
        System.out.println();
    }
}
