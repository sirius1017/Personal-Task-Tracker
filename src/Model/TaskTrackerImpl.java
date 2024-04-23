package Model;

import Model.Task.Category;
import Model.Task.CompareByDueDate;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import Model.Task.Milestones;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TaskTrackerImpl implements TaskTrackerModel, Serializable {

  private List<Task> taskList;

  /**
   * The constructor of TaskTrackerImpl
   */
  public TaskTrackerImpl(){
    taskList = new ArrayList<>();
  }
  @Override
  public void addTask(Task task) throws IllegalArgumentException {
    if(taskList.contains(task)){
      throw new IllegalArgumentException("Task name should be different.");
    } else{
      taskList.add(task);
    }
  }


  @Override
  public void deleteTask(Task task) {
    taskList.remove(task);
    task.setStatus(Status.DELETED);
  }

  @Override
  public List<Task> allTasks() {
    return taskList;
  }

  @Override
  public List<Task> getTasksUncompletedBefore(LocalDate date) {
    return taskList.stream()
        .filter(t -> t.getDueDate().isBefore(date.plusDays(1)) && t.getStatus() == Status.INPROGRESS)
        .sorted(new CompareByDueDate())
        .collect(Collectors.toList());
  }

  @Override
  public int getNumOfAllTasks() {
    return taskList.size();
  }

  @Override
  public int getNumOfTasksUnCompleted(LocalDate date) {
    return taskList.stream()
        .filter(t->t.getDueDate().isBefore(date.plusDays(1)) && t.getStatus() == Status.INPROGRESS)
        .mapToInt(t->Integer.valueOf(1))
        .sum();
  }

  @Override
  public List<Integer> getMenuListNumber() {
    List<Integer> numberList = new ArrayList();
    numberList.add(getTasksOfPriority(Priority.A).size());
    numberList.add(getTasksOfPriority(Priority.B).size());
    numberList.add(getTasksOfPriority(Priority.C).size());
    numberList.add(getTasksOfPriority(Priority.D).size());
    numberList.add(getTasksUncompletedBefore(LocalDate.of(2999, 12, 31)).size());
    numberList.add(getTasksUncompletedBefore(LocalDate.now()).size());
    DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
    LocalDate startOfCurrentWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

    // determine last day of current week
    DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);
    LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
    numberList.add(getTasksUncompletedBefore(endOfWeek).size());
    numberList.add(getTasksUncompletedBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth())).size());
    numberList.add(getTasksUncompletedBefore(LocalDate.from(LocalDate.now()).plusMonths(3)).size());
    numberList.add(getTasksUncompletedBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfYear())).size());
    numberList.add(allTasks().size());

    return numberList;
  }

  // Higher-order function: fold, map stream
  // Comparator
  // Lambda expression
  @Override
  public int getNumOfTasksOfPriority(Priority priority) {
    return taskList.stream()
        .filter(t->t.getPriority() == priority && t.getStatus() == Status.INPROGRESS)
        .mapToInt(t->Integer.valueOf(1))
        .sum();
  }

  // Higher-order function: map stream
  // Comparator
  // Lambda expression
  @Override
  public List<Task> getTasksOfPriority(Priority priority) {
    return taskList.stream()
        .filter(t->t.getPriority() == priority && t.getStatus() == Status.INPROGRESS)
        .sorted(new CompareByDueDate())
        .collect(Collectors.toList());
  }

  @Override
  public void editTimeFrame(Task task, Milestones timeframe) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setMilestones(timeframe);
    }
  }

  @Override
  public void editTaskCategory(Task task, String category){
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else {
      if (new Category().contains(category)){

      }
    }
  }

  @Override
  public void editTaskPriority(Task task, Priority priority) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setPriority(priority);
    }

  }

  @Override
  public void editTaskDescription(Task task, String description) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setDescription(description);
    }
  }

  @Override
  public void editTaskDueDate(Task task, LocalDate date) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setDueDate(date);
    }
  }

  @Override
  public void editTaskTitle(Task task, String title) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setTitle(title);
    }
  }

  @Override
  public void editTaskStatus(Task task, Status status) {
    if (!taskList.contains(task)){
      throw new IllegalArgumentException("This task doesn't exist!");
    } else{
      task.setStatus(status);
    }
  }

  @Override
  public List<String> getNameList(){
    List<String> nameList = new ArrayList<>();
    for (Task t:taskList){
      nameList.add(t.getTitle());
    }
    return nameList;
  }
}
