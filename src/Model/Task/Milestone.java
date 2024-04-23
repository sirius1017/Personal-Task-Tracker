package Model.Task;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents the milestone stage in the time frame of the task
 */
public class Milestone extends AbstractTask{

  /**
   * The constructor of Milestone
   * @param name the name of Milestone
   * @param percentage the progress precentage of the Milestone
   * @param completed the boolean value of completed of the Milestone
   */
  public Milestone(String name, int percentage, boolean completed) {
    super(name);
    this.progress = percentage;
    this.isCompleted = completed;
  }

  @Override
  public int getProgress() {
    return progress;
  }

  /**
   * Return true if this milestone is equal to another milestone, otherwise false
   * based on the title of milestone
   * @param o the object compared to this milestone
   * @return  true if this milestone is equal to another milestone, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Milestone)){
      return false;
    }

    Milestone x = (Milestone) o;
    if (title.equals(x.title)){
      return true;
    }

    return false;
  }

  /**
   * Return the hash code of this milestone, based on title
   * @return the hashcode
   */
  @Override
  public int hashCode(){
    return Objects.hash(title);
  }


  @Override
  public String getDescription() {
    // Skip now, improvements in the future
    return null;
  }

  @Override
  public LocalDate getDueDate() {
    // Not required now, Skip now, improvements in the future
    return null;
  }

  @Override
  public Priority getPriority() {
    // Not required now, Skip now, improvements in the future
    return null;
  }

  @Override
  public String getCategory() {
    // Not required now, Skip now, improvements in the future
    return null;
  }

  @Override
  public Status getStatus() {
    // Not required now, Skip now, improvements in the future
    return null;
  }

  @Override
  public Milestones getMilestones() {
    // Not required now, Skip now, improvements in the future
    return null;
  }



  @Override
  public Importance getImportance() {
    // Not required now, Skip now, improvements in the future
    return null;
  }

  @Override
  public long getRemainingDays() {
    // Not required now, Skip now, improvements in the future
    return 0;
  }

  @Override
  public void setCategory(String category) {
  // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setDescription(String description) {
    // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setDueDate(LocalDate date) {
    // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setPriority(Priority priority) {
    // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setStatus(Status status) {
    // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setMilestones(Milestones timeframe) {
    // Not required now, Skip now, improvements in the future
  }

  @Override
  public void setImportance(Importance importance) {
    // Not required now, Skip now, improvements in the future
  }

}
