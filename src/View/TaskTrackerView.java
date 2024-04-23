package View;


import Model.Task.Importance;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;


/**
 * This interface represents the interface of the view of Personal Task Tracker
 */
// Design principle: Use interface types over concrete classes wherever possible.
public interface TaskTrackerView {

  /**
   * Return the string title of task input from text field
   * @return the title of task input
   */
  public String getInputTitle();

  /**
   * Set the title of task that is showing what the model stores
   * @param title the title from model
   */
  public void setInputTitle(String title);

  /**
   * Return the string description of task input from text field
   * @return the string description of task input
   */
  public String getInputDescription();

  /**
   * Set the description of task that is showing what the model stores
   * @param description from model
   */
  public void setInputDescription(String description);

  /**
   * Return the category of task choose by user
   * @return the category of task choose by user
   */
  public String getInputCategory();

  /**
   * Set the category of task that is showing what the model stores
   * @param category from model
   */
  public void setCategory(String category);


  /**
   * Return the month of due date of task from combo box
   * @return the month of due date of task from combo box
   */
  public String getInputDueMonth();

  /**
   * Get the day of due date of task from combo box.
   * @return the day of due date of task from combo box.
   */
  public String getInputDueDay();

  /**
   * Get the year of due date of task from combo box.
   * @return the year of due date of task from combo box.
   */
  public String getInputDueYear();

  /**
   * Set the due date of task that is showing what the model stores
   * @param dueDate from model
   */
  public void setDueDate(LocalDate dueDate);

  /**
   * Return the status task from combo box
   * @return the status task from combo box
   */
  public Status getInputStatus();

  /**
   * Set the status of task that is showing what the model stores
   * @param status from model
   */
  public void setInputStatus(Status status);

  /**
   * Return the importance from combo box
   * @return the importance from combo box
   */
  public Importance getInputImportance();

  /**
   * Set the importance of task that is showing what the model stores
   * @param importance from model
   */
  public void setInputImportance(Importance importance);

  /**
   * Get the priority from input combo box in edit panel
   * @return
   */
  public Priority getInputPriority();

  /**
   * Set the priority in edit panel
   * @param priority the new priority
   */
  public void setInputPriority(Priority priority);

  /**
   * Clear all input on the create new task panel
   */
  public void clearAllInput();

  /**
   * Add the task list panel of given list of tasks
   * @param taskList the given list of tasks
   */
  public void addTaskListPanel(List<Task> taskList);


  /**
   * The Optionpane dialog popped up when button presses
   */
  public void saveInNewDialog();

  /**
   * The error optionpane dialog popped up when error happened
   * @param text
   */
  public void saveErrorInNewDialog(String text);

  /**
   * Update the number of lists on menu panel
   * @param list the list of sizes of tasks lists
   */
  public void updateMenuPanel(List<Integer> list);

  /**
   * Add the create new task panel
   */
  public void addCreateNewTaskPanel();

  /**
   * Add the task context panel of given task
   * @param task the given task
   */
  public void addTaskContextPanel(Task task);

  /**
   * Add the edit task panel of given task
   * @param task the given task
   */
  public void addEditTaskPanel(Task task);

  /**
   * The invalid date error option pane
   */
  public void dateErrorOptionPane();

  /**
   * The error optionPane when something wrong in milestones input
   */
  public void milestonesErrorPane();

  /**
   * The error optionPane when something wrong in milestones input progress
   */
  public void milestoneProgressErrorPane();

  /**
   * Get the list of milestone name of text input
   * @return the list of milestone nameof text input
   */
  public List<String> getMilestoneTextInputList();

  /**
   * Return the list of milestone progress of text input
   * @return the list of milestone progress of text input
   */
  public List<String> getMilestoneProgressInputList();

  /**
   * Set the milestone name in task context panel that is showing what the model stores
   * @param task the given task
   */
  public void setMilestoneTextInputList(Task task);

  /**
   * Set the progress of milestones of given task in task context panel from model
   * @param task the given task
   */
  public void setMilestoneProgressInputList(Task task);

  /**
   * Return the list of boolean value of milestone complete of checkbox
   * @return the list of boolean value of milestone complete of checkbox
   */
  public List<Boolean> getMilestoneCompletedInputList();

  /**
   * Set the isComplete list of milestones of given task in task context panel from model
   * @param task the given task
   */
  public void setMilestoneCompletedInputList(Task task);

  /**
   * Set the milestone name in edit task panel of given task that is showing what the model stores
   * @param task the given task
   */
  public void setMilestoneTextInEditTask(Task task);

  /**
   * Set the milestone progress in edit task panel of given task
   * that is showing what the model stores
   * @param task the given task
   */
  public void setMilestoneProgressInEditTask(Task task);

  /**
   * Set the milestone isCompleted in edit task panel of given task \
   * that is showing what the model stores
   * @param task the given task
   */
  public void setMilestoneCompletedInEdit(Task task);

  /**
   * Get the task choosen by user, be view later
   * @return the task choosen by user
   */
  public Task getSelectedTask();

  /**
   * this is to force the view to have a method to set up actions for buttons.
   * All the buttons must be given this action listener
   * <p>
   * Thus our Swing-based implementation of this interface will already have such a
   * method.
   *
   * @param listener
   */
  public void addActionListener(ActionListener listener);



}
