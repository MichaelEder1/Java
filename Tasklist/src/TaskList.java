/*--------------------------------------------------------
 * TaskList.java
 * Author: Michael Eder
 * Date 28.04.2020
 * Task: Aufgabe 1
 *
 * This class creates an object of the data type TaskList.
 * A TaskList is a linked list, which holds nodes in form
 * of Task objects. Further more this class has several
 * functions for e.g. add a new Task into the list, remove
 * a task, check, if a task already exists, sort the whole
 * TaskList by due date of the task and also a function to
 * print the whole TaskList.
 *
 --------------------------------------------------------*/

package kwm.tasklist;

public class TaskList
{
	public Task firstNode;
	public int numOfTask = 0;
	public boolean sorted = false;
	
	public TaskList()
	{
		this.firstNode = null;
	}
	
	/**
	 * searchTask(String id)
	 * This method searches the whole list, if a Task with the
	 * given id already exists. If so, the matching task will be returned.
	 *
	 * @param id = the id, which should be searched for
	 * @return Task/null = if task was found function returns Task, else returns null.
	 */
	public Task searchTask(String id)
	{
		Task task = this.firstNode; //save first node into a variable
		while (task != null) //as longs as there is a task
		{
			if (task.id.equals(id))
			{
				return task;
			}
			task = task.next; //update task position in list
		}
		return null;
	}
	
	/**
	 * addTask(Task t)
	 * This method adds a new task to the TaskList at first position
	 *
	 * @param t = this is the Task object, which should be added to current the TaskList
	 * @return true/false = returns true, if the task has an unique id and hence
	 * could be added to TaskList, else returns false, and nothing happens.
	 */
	public boolean addTask(Task t)
	{
		if (searchTask(t.id) == null)
		{
			//saves the firstNode as next Pointer
			t.next = this.firstNode;
			//updates firstNode
			this.firstNode = t;
			numOfTask++;
			return true;
		}
		return false;
	}
	
	/**
	 * removeTask(String id)
	 * This method removes a Task with a specific id from TaskList.
	 *
	 * @param id = the given id of a task, which should be removed
	 * @return Task/null = if task was found function returns Task, else returns null.
	 */
	public Task removeTask(String id)
	{
		//save previous task node
		Task prev = null;
		//save first task node
		Task task = this.firstNode;
		while (task != null && !task.id.equals(id))
		{
			//set prev to current task
			prev = task;
			//set task to next task
			task = task.next;
		}
		if (task != null)//We've found the id
		{
			//Exception: the head will be removed
			if (prev == null)
			{
				//update firstNode to the next task in list
				this.firstNode = task.next;
			}
			else
			{
				//update next pointer of the prev. node to the task.next node (skip the removed)
				prev.next = task.next;
			}
			numOfTask--;
			return task;
		}
		return null;
	}
	
	/**
	 * getOpenTasks()
	 * This method returns a new TaskList with every open task.
	 *
	 * @return list2 = new TaskList which only contains open tasks.
	 */
	public TaskList getOpenTasks()
	{
		TaskList list2 = new TaskList();
		Task task = this.firstNode;
		while (task != null)
		{
			if (task.status)
			{
				//creates new task  and adds it in list2
				list2.addTask(task.getNewTask());
			}
			task = task.next;
		}
		return list2;
	}
	
	/**
	 * getTasksByProcessor(String processor)
	 * This method returns a new TaskList which only contains task by a specific processor.
	 *
	 * @param processor = name of processor, which should ne filtered for
	 * @return list2 = new TaskList which only contains task from specific processor
	 */
	public TaskList getTasksByProcessor(String processor)
	{
		TaskList list2 = new TaskList();
		Task task = this.firstNode;
		while (task != null)
		{
			if (task.bearbeiter.equals(processor))
			{
				//creates new task and adds it in list2
				list2.addTask(task.getNewTask());
			}
			task = task.next;
		}
		return list2;
	}
	
	/**
	 * printTaskList()
	 * This method prints the whole TaskList by using the output() function of the Task class.
	 */
	public void printTaskList()
	{
		Task task = this.firstNode;
		
		while (task != null)
		{
			task.output();
			task = task.next;
		}
	}
	
	/**
	 * getNumOfEntries()
	 * This method returns the current number of entries in TaskList
	 *
	 * @return numOfTask = numbers of tasks in TaskList
	 */
	public int getNumOfTask()
	{
		return this.numOfTask;
	}
	
	/**
	 * sortByDate()
	 * This method sorts the whole TaskList by due date.
	 * Therefore it uses the auxiliary method insertSorted()
	 */
	public void sortByDate()
	{
		TaskList list2 = new TaskList();
		Task task = this.firstNode;
		while (task != null)
		{
			TaskList.insertSorted(list2, task);
			task = task.next;
		}
		this.sorted = true;
		this.firstNode = list2.firstNode;
	}
	
	/**
	 * insertSorted()
	 * This method is responsible for sorting the TaskList by due date.
	 * For this, it fills a new TaskList (=list2), with given task.
	 * It inserts a new task before a existing task, if the date is smaller
	 * than the date of the existing task. Else it inserts the task at the
	 * next higher position.
	 *
	 * @param list2 = the new created TaskList which now holds the sorted TaskList
	 * @param task  = the task, which should be added into the sorted TaskList
	 * @return list2 = returns the new created sorted list
	 */
	public static TaskList insertSorted(TaskList list2, Task task)
	{
		Task prev = null; //saves previous node
		Task node = list2.firstNode; //save firstNode of list
		Task t = task.getNewTask();
		
		//as long as there are tasks AND the due date is smaller than date of given task
		while (node != null && node.datum.compareTo(task.datum) < 0)
		{
			prev = node;
			node = node.next;
		}
		//Exception: the found element is the firstNode = insert task before firstNode = creating new firstNode
		if (prev == null)
		{
			//sets the new task as firstNode
			list2.firstNode = t;
		}
		else
		{
			prev.next = t;
		}
		t.next = node;
		return list2;
	}
}