package Model.Task;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents the concrete class implementing Task interface,
 * extending AbstractTask
 */
public class TaskImpl extends AbstractTask  {

  private String description;
  private LocalDate duedate;
  private Importance importance;

  private Status status;
  private Milestones milestones = null;
  private String category;
  private Priority priority;

  /**
   * The constructor of TaskImpl
   * @param title then title of task
   * @param description the description of task
   * @param duedate the duedate of task
   * @param importance the importance of task
   * @param status the status of task
   * @param milestones the Milestones of task
   * @param category the category of task
   * @throws IllegalArgumentException empty title and invalid duedate
   */
  public TaskImpl(String title, String description, LocalDate duedate,
      Importance importance, Status status, Milestones milestones, String category) throws
      IllegalArgumentException{
    super(title);
    this.duedate = duedate;
    if (duedate.isBefore(createDate)){
      throw new IllegalArgumentException("Due date must be after today!");
    }
    this.description = description;

    this.importance = importance;
    this.status = status;
    this.milestones = milestones;
    this.category = category;
    if (getRemainingDays() <= 30 && getImportance() == Importance.IMPORTANT){
      this.priority = Priority.A;
    }
    if (getRemainingDays() > 30 && getImportance() == Importance.IMPORTANT){
      this.priority = Priority.B;
    }
    if (getRemainingDays() <= 30 && getImportance() == Importance.NOTIMPORTANT){
      this.priority = Priority.C;
    }
    if (getRemainingDays() > 30  && getImportance() == Importance.NOTIMPORTANT){
      this.priority = Priority.D;
    }

    switch (status){
      case COMPLETED -> {
        isCompleted = true;
        break;
      }
      case DELETED -> {
        isDeleted = true;
        break;
      }
      default -> {
        break;
      }
    }
  }

  /**
   * Contructor of TaskImpl
   * @param title the name of task
   * @param description the description of task
   * @param duedate the duedate of task
   * @param importance the importance of task
   * @param status the status of task
   * @param category the category of task
   * @throws IllegalArgumentException if empty title or invalid dudate
   */
  public TaskImpl(String title, String description, LocalDate duedate,
      Importance importance, Status status, String category) throws IllegalArgumentException{
    super(title);
    this.duedate = duedate;
    if (duedate.isBefore(createDate)){
      throw new IllegalArgumentException("Due date must be after today!");
    }

    this.description = description;
    this.importance = importance;
    this.status = status;
    this.category = category;
    if (getRemainingDays() <= 30 && getImportance() == Importance.IMPORTANT){
      this.priority = Priority.A;
    }
    if (getRemainingDays() > 30 && getImportance() == Importance.IMPORTANT){
      this.priority = Priority.B;
    }
    if (getRemainingDays() <= 30 && getImportance() == Importance.NOTIMPORTANT){
      this.priority = Priority.C;
    }
    if (getRemainingDays() > 30  && getImportance() == Importance.NOTIMPORTANT){
      this.priority = Priority.D;
    }

    switch (status){
      case COMPLETED -> {
        isCompleted = true;
        break;
      }
      case DELETED -> {
        isDeleted = true;
        break;
      }
      default -> {
        break;
      }
    }

  }


  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void setDescription(String description){
    this.description = description;
  }
  @Override
  public Status getStatus(){
    return status;
  }

  @Override
  public void setStatus(Status status) {
    this.status = status;
    switch (status){
      case COMPLETED -> {
        isCompleted = true;
        break;
      }
      case DELETED -> {
        isDeleted = true;
        break;
      }
      default -> {
        break;
      }
    }
  }

  @Override
  public String getCategory(){
    return category;
  }

  @Override
  public void setCategory(String category){
    if (new Category().contains(category)){
      this.category = category;
    } else{
      throw new IllegalArgumentException("The new category doesn't exist!");
    }
  }

  @Override
  public Milestones getMilestones() {
    return milestones;
  }
  @Override
  public void setMilestones(Milestones newMilestones) {
    this.milestones = newMilestones;
  }

  @Override
  public Importance getImportance() {
    return importance;
  }

  @Override
  public void setImportance(Importance importance) {
    this.importance = importance;
  }

  @Override
  public LocalDate getDueDate(){
    return duedate;
  }

  @Override
  public void setDueDate(LocalDate date) {
    this.duedate = date;
  }

  @Override
  public long getRemainingDays() {
    LocalDate today = LocalDate.now();
    return DAYS.between(today, duedate);
  }

  @Override
  public Priority getPriority(){
    return priority;
  }

  @Override
  public void setPriority(Priority priority) {
    this.priority = priority;
  }


  @Override
  public void setCompleted(){
    if (isCompleted == true){
      isCompleted = false;
      setStatus(Status.INPROGRESS);
    } else{
      isCompleted = true;
      setStatus(Status.COMPLETED);
    }
  }

  @Override
  public void setDeleted(){
    if (isDeleted == true){
      isDeleted = false;
      setStatus(Status.INPROGRESS);
    } else{
      isDeleted = true;
      setStatus(Status.DELETED);
    }
  }

  // hightlight: if override equals(), override hashCode(), and vice-versa.
  /**
   * Return true if the title of two TaskImpls are equal, otherwise false
   * @param o  the compared object
   * @return Return true if the title of two TaskImpls are equal, otherwise false
   */
  @Override
  public boolean equals(Object o) {

    if (!(o instanceof TaskImpl)){
      return false;
    }

    TaskImpl x = (TaskImpl) o;
    if (title.equals(x.title)){
      return true;
    }

    return false;
  }

  // hightlight: if override equals(), override hashCode(), and vice-versa.

  /**
   * Get the hashCode of this TaskImpl
   * @return the hashCode of this TaskImpl
   */
  @Override
  public int hashCode(){
    return Objects.hash(title);
  }

}
