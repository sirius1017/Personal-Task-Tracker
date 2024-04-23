package Model.Task;

import java.time.LocalDate;

/**
 * This interface represents a task created by user.
 */
// Design principle: Use interface types over concrete classes wherever possible.
public interface Task {

  /**
   * Return the title of this task
   * @return the title of this task
   */
  String getTitle();

  /**
   * Return the description of this task
   * @return the description of this task
   */
  String getDescription();

  /**
   * Return the due date of this task
   * @return the due date of this task
   */
  LocalDate getDueDate();

  /**
   * Return the priority of this task
   * @return the priority of this task
   */
  Priority getPriority();

  /**
   * Get the category of this task
   * @return the category of this task
   */
  String getCategory();

  /**
   * Get the status of this task
   * @return the status of this task
   */
  Status getStatus();

  /**
   * Get the time frame of this task
   * @return the time frame of this task
   */
  Milestones getMilestones();

  /**
   * Get the progress in percentage of this task
   * @return the percentage progress of this task
   */
  int getProgress();

  /**
   * Get the importance of this task
   * @return the importance of this task
   */
  Importance getImportance();

  /**
   * Get the remaining days due of this task
   * @return the remaining days due of this task
   */
  long getRemainingDays();

  /**
   * Return the date when the task was firstly created
   * @return the date when the task was firstly created
   */
  LocalDate getCreateDate();

  /**
   * Modify the title of this task
   * @param title set the title to this new title
   */
  void setTitle(String title);

  /**
   * Modify the category of this task
   * @param category set the category to this new category
   */
  void setCategory(String category);

  /**
   * Modify the description of this task
   * @param description set the description to this new description
   */
  void setDescription(String description);

  /**
   * Modify the due date of this task
   * @param date set the due date to this new date
   */
  void setDueDate(LocalDate date) ;

  /**
   * Modify the priority of this task
   * @param priority set the priority to this new priority
   */
  void setPriority(Priority priority);

  /**
   * Modify the status of this task
   * @param status set the status to this new status
   */
  void setStatus(Status status);

  /**
   * Modify the time frame of this task
   * @param timeframe set the timeframe to this new timeframe
   */
  void setMilestones(Milestones timeframe);

  /**
   * Modify the importance of this task
   * @param importance set the importance to this new importance
   */
  void setImportance(Importance importance);

  /**
   * Return the boolean value of the completion of this task
   * @return true if the task is completed, otherwise false
   */
  boolean isCompleted();

  /**
   * Return the boolean value of the deletion of this task
   * @return true if this task is deleted, otherwise false
   */
  boolean isDeleted();

  /**
   * Set the completed status of the task
   * modify to true if false, false otherwise
   */
  public void setCompleted();

  /**
   * Set the deleted status of the task
   * modify to true if false, false otherwise
   */
  public void setDeleted();

}
