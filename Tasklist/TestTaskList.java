/*--------------------------------------------------------
 * TestTaskList.java
 * Author: Michael Eder
 * Date 28.04.2020
 * Task: Aufgabe 1
 *
 * This class purpose is to test the functionality of
 * the TaskList and Task class. Therefore six different
 * objects by type Task are created and than added to an
 * object from type TaskList. Afterwards, several functions
 * are called on the list (like add, remove a task or
 * filter or sort the TaskList)
 --------------------------------------------------------*/
import kwm.tasklist.*;

public class TestTaskList
{
    public static void main(String[] args)
    {
        TaskList list = new TaskList();
        Task task1 = new Task("task1", "Java UE06 machen", "Michael", true, 2020, 3, 3);
        Task task2 = new Task("task2", "Präsentation vorbereiten", "Michael", true, 2020, 3, 26);
        Task task3 = new Task("task3", "Teams Meeting", "Thomas", false, 2020, 3, 28);
        Task task4 = new Task("task4", "lernen für Klausur", "Hannes", false, 2020, 4, 10);
        Task task5 = new Task("task1", "Video schneiden", "Michael", false, 2020, 3, 19);
        Task task6 = new Task("task6", "Fragebogen bearbeiten", "Michael", true, 2020, 3, 30);
        
        //add Task 1-5
        boolean add = list.addTask(task1);
        System.out.println(add);
        add = list.addTask(task2);
        System.out.println(add);
        add = list.addTask(task3);
        System.out.println(add);
        add = list.addTask(task4);
        System.out.println(add);
        //Task 5 should not be added due to same task id like task 1
        add = list.addTask(task5);
        System.out.println(add);
        
        System.out.println("-----------------------");
        
        list.printTaskList();
        
        System.out.println("-----------------------");
        
        Task removedTask = list.removeTask("task3");
        removedTask.output();
        
        System.out.println("-----------------------");
        
        int amount = list.getNumOfTask();
        System.out.println(amount);
        
        System.out.println("-----------------------");
        
        list.printTaskList();
        
        System.out.println("-----------------------");
        
        add = list.addTask(task6);
        list.printTaskList();
        
        System.out.println("-----------------------");
        
        TaskList list2 = list.getTasksByProcessor("Hannes");
        list2.printTaskList();
        
        System.out.println("-----------------------");
        
        list.sortByDate();
        list.printTaskList();
        
        System.out.println("-----------------------");
        
        Task searchedTask = list.searchTask("task1");
        searchedTask.output();
        
        System.out.println("-----------------------");
        
        TaskList list3 = list.getOpenTasks();
        list3.printTaskList();
        
        System.out.println("-----------------------");
        
        list3.sortByDate();
        list3.printTaskList();
        
    }
}
