package Model.Task;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public abstract class AbstractTask implements Task  {

  protected String title;
  protected int progress;
  protected boolean isCompleted;
  protected boolean isDeleted;
  protected final LocalDate createDate = LocalDate.now(); // The date firstly created this task.


  /**
   * The constructor of AbstractTask
   * @param name the name of AbstractTask
   * @throws IllegalArgumentException if the name is empty
   */
  public AbstractTask(String name) throws IllegalArgumentException{
      this.title = name;
      if (title.isEmpty()){
        throw new IllegalArgumentException("Task title can't be empty!");
      }
      this.progress = 100;
      this.isCompleted = false;
      this.isDeleted = false;
  }

  @Override
  public LocalDate getCreateDate(){
    return createDate;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public int getProgress(){
    return 100;
  }


  @Override
  public void setTitle(String title) throws IllegalArgumentException {
    if (title.isEmpty()){
      throw new IllegalArgumentException("Name can't be empty!");
    }

    this.title = title;
  }


  @Override
  public boolean isCompleted() {
    return isCompleted;
  }

  @Override
  public void setCompleted(){
    if (isCompleted == true){
      isCompleted = false;
    } else{
      isCompleted = true;
    }
  }

  @Override
  public boolean isDeleted() {
    return isDeleted;
  }
  @Override
  public void setDeleted(){
    if (isDeleted == true){
      isDeleted = false;

    } else{
      isDeleted = true;

    }
  }

}
