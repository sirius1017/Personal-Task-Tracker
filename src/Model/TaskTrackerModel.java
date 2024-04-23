package Model;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import Model.Task.Milestones;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * This interface represents the management of tasks,
 * which is a list of tasks
 */
// Design principle: Use interface types over concrete classes wherever possible.
public interface TaskTrackerModel {

  /**
   * Add the task to the list of task management
   * @param task the tasked needed to be added
   * @throws IllegalArgumentException same task can't be added
   */
  void addTask(Task task) throws IllegalArgumentException;

  /**
   * Get the Task at specified index
   * @param index the given index
   * @return the Task at specified index
   */
  Task getIndex(int index);

  /**
   * Modify the title of the specified task
   * @param task the specified task needed to be modified
   * @param title the new title
   */
  void editTaskTitle(Task task, String title) throws IllegalArgumentException;

  /**
   * Modify the category of the given task
   * @param task the given task needed to be modified
   * @param category the new category
   */
  void editTaskCategory(Task task, String category) throws IllegalArgumentException;

  /**
   * Modify the due date of the specified task
   * @param task the specified task needed to be modified
   * @param date the new due date
   */
  void editTaskDueDate(Task task, LocalDate date);

  /**
   * Modify the description of the specified task
   * @param task the specified task needed to be modified
   * @param description the new description
   */
  void editTaskDescription(Task task, String description);

  /**
   * Modify the priority of the specified task
   * @param task the specified task needed to be modified
   * @param priority the new priority
   */
  void editTaskPriority(Task task, Priority priority);

  /**
   * Modify the time frame of the specified task
   * @param task the specified task needed to be modified
   * @param timeframe the new time frame
   */
  void editTimeFrame(Task task, Milestones timeframe);

  /**
   * Modify the status of the specified task
   * @param task the given task needed to be modified
   * @param status the new status
   */
  void editTaskStatus(Task task, Status status);

  /**
   * Delete the given task
   * @param task the task needed to be deleted from the task management system
   */
  void deleteTask(Task task);

  /**
   * Return a list of tasks given a specified priority
   * @param priority the specified priority
   * @return a list of tasks satisfying that given priority
   */
  List<Task> getTasksOfPriority(Priority priority);

  /**
   * Return the list of all tasks
   * @return the list of all task in task management
   */
  List<Task> allTasks();

  /**
   * Return a list of all uncompleted tasks due before a given date
   * @param date the given date
   * @return the list of all uncompleted tasks due before a given date
   */
  List<Task> getTasksUncompletedBefore(LocalDate date);

  /**
   * Return the number of all tasks in the task management
   * @return the number of all tasks in the task management
   */
  int getNumOfAllTasks();

  /**
   * Return the number of tasks of a given priority
   * @param priority the given priority
   * @return  the number of tasks of a given priority
   */
  int getNumOfTasksOfPriority(Priority priority);

  /**
   * Return the number of uncompleted tasks given a specified due date
   * @param date the specified due date
   * @return the number of uncompleted tasks before the given due date
   */
  int getNumOfTasksUnCompleted(LocalDate date);

  /**
   * Return the map between menu items and corresonding lists of tasks
   * @return the map between menu items and corresonding lists of tasks
   */
  List<Integer> getMenuListNumber();

  /**
   * Get a list of names of all tasks.
   * @return the list of all tasks' names
   */
  List<String> getNameList();
}
