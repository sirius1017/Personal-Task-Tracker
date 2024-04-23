package Controller;

import Model.Task.Importance;
import Model.Task.Milestone;
import Model.Task.Milestones;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import Model.Task.TaskImpl;
import Model.TaskTrackerModel;
import View.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This class represents controller of the personal task tracker.
 * Link model and view
 */
public class Controller {

  private TaskTrackerModel model;
  private TaskTrackerView view;
  private Milestones milestoneList;
  public Controller(TaskTrackerModel model, TaskTrackerView view){
    this.view = view;
    this.model = model;
    configureButtonListener();
  }

  /**
   * Configure button listener based on HashMap
   */
  public void configureButtonListener(){
    Map<String,Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("Create New Task",new CreateNewTaskButtonAction());
    buttonClickedMap.put("Need To Do Now",new NeedToDoNowButtonAction());
    buttonClickedMap.put("Need To Plan For", new NeedToPlanForAction());
    buttonClickedMap.put("Reschedule or Delegate", new RescheduleOrDelegateAction());
    buttonClickedMap.put("If Had Extra Time", new IfHadExtraTimeAction());
    buttonClickedMap.put("All Uncompleted", new AllUncompletedAction());
    buttonClickedMap.put("Today", new TodayAction());
    buttonClickedMap.put("This Week", new ThisWeekAction());
    buttonClickedMap.put("This Month", new ThisMonthAction());
    buttonClickedMap.put("Next Three Months", new NextThreeMonthsAction());
    buttonClickedMap.put("This Year", new ThisYearAction());
    buttonClickedMap.put("All Tasks", new AllAction());
    buttonClickedMap.put("SaveInCreateNewTask", new SaveInCreateNewTaskAction() );
    buttonClickedMap.put("ClearInCreateNewTask", new ClearInCreateNewTaskAction());
    buttonClickedMap.put("SaveInEditTask", new SaveInEditTaskAction() );

    buttonListener.setButtonClickedActionsMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }

  /**
   * Check if the input date is valid
   * @return true if the date is valid, otherwise false
   */
  public boolean isValidDate() {
    String month = view.getInputDueMonth();
    String day = view.getInputDueDay();
    String year = view.getInputDueYear();

    // check if fields are empty
    if (month.isEmpty() || day.isEmpty() || year.isEmpty()) {
      return false;
    }

    // Convert inputs to integers
    try {
      int monthInt = Integer.parseInt(month);
      int dayInt = Integer.parseInt(day);
      int yearInt = Integer.parseInt(year);

      // Check if month, day, and year are within valid ranges
      if (monthInt < 1 || monthInt > 12
          || dayInt < 1 || dayInt > 31
          || yearInt < 1900 || yearInt > 2100) {
        return false;
      }

      if (LocalDate.of(yearInt, monthInt, dayInt).isBefore(LocalDate.now())){
        return false;
      }

    } catch (NumberFormatException e) {
      // If any of the inputs are not integers, it's an invalid date
      return false;
    }

    return true;
  }

  /**
   * Check if the milestones input is valid
   * @return true if it is valid, othervise false
   */
  public boolean isValidMilestone() {
    List<String> milestoneName = view.getMilestoneTextInputList();
    List<String> milestoneProgress =view.getMilestoneProgressInputList();
    List<Boolean> milstoneCompleted = view.getMilestoneCompletedInputList();

    milestoneList = new Milestones();

    // Milestone name can not be empty
    // or both name and progres be empty
    for (int i = 0; i < milestoneName.size(); i++){
      if (milestoneName.get(i).isEmpty()){
        if (!milestoneProgress.get(i).isEmpty()){
          return false;
        }
      }
    }
    for (String s:milestoneProgress){
      try {
        if (s.isEmpty()){
          continue;
        }
        int number= Integer.parseInt(s);

        if (number < 1 || number > 100){
          return false;
        }
      } catch (NumberFormatException e){
        view.milestoneProgressErrorPane();
        return false;
      }
    }

    for (int i = 0; i < milestoneName.size(); i++){
      // Add milestone name of not empty name
      if (milestoneName.get(i).isEmpty()){
        continue;
      }
      milestoneList.addMilestone(new Milestone(milestoneName.get(i),
          Integer.parseInt(milestoneProgress.get(i)),
          milstoneCompleted.get(i)));
    }
    return true;
  }

  /**
   * This class represents the action of CreateNewTask button
   * <p></p>
   */
  class CreateNewTaskButtonAction implements Runnable {

    @Override
    public void run() {
      view.addCreateNewTaskPanel();
      configureButtonListener();
    }
  }

  /**
   * This class represents the action of NeedToDoNowButton button
   * <p></p>
   */
  class NeedToDoNowButtonAction implements Runnable {

    @Override
    public void run() {
      List list =model.getTasksOfPriority(Priority.A);
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of need to plan for button
   * <p></p>
   */
  class NeedToPlanForAction implements Runnable {

    @Override
    public void run() {
      List list =model.getTasksOfPriority(Priority.B);
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of Reschedule
   * OrDelegate button
   */
  class RescheduleOrDelegateAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksOfPriority(Priority.C);
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of IfHadExtraTime button
   * <p></p>
   */
  class IfHadExtraTimeAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksOfPriority(Priority.D);
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of AllUncompleted button
   * <p></p>
   */
  class AllUncompletedAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksUncompletedBefore(LocalDate.of(2999, 12, 31));
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of Today button
   * <p></p>
   */
  class TodayAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksUncompletedBefore(LocalDate.now());
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of ThisWeek button
   * <p></p>
   */
  class ThisWeekAction implements Runnable{

    @Override
    public void run() {
      DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
      DayOfWeek lastDayOfWeek = firstDayOfWeek.plus(6);
      LocalDate endOfWeek = LocalDate.now().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

      List list =model.getTasksUncompletedBefore(endOfWeek);
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of ThisMonth button
   * <p></p>
   */
  class ThisMonthAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksUncompletedBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of NextThreeMonths button
   * <p></p>
   */
  class NextThreeMonthsAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksUncompletedBefore(LocalDate.from(LocalDate.now()).plusMonths(3));
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of this year button
   * <p></p>
   */
  class ThisYearAction implements Runnable{

    @Override
    public void run() {
      List list =model.getTasksUncompletedBefore(LocalDate.now().with(TemporalAdjusters.lastDayOfYear()));
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of All button
   * <p></p>
   */
  class AllAction implements Runnable{

    @Override
    public void run() {
      List list =model.allTasks();
      view.addTaskListPanel(list);
    }
  }

  /**
   * This class represents the action of SaveInCreateNewTask button
   * <p></p>
   */
  class SaveInCreateNewTaskAction implements Runnable {

    @Override
    public void run() {
      String title = view.getInputTitle();

      if (title.isEmpty()) {
        view.saveErrorInNewDialog("Title can't be empty!");
      } else {
        String description = view.getInputDescription();
        String category = view.getInputCategory();
        String day = view.getInputDueDay();
        String month = view.getInputDueMonth();
        String year = view.getInputDueYear();
        Importance importance = view.getInputImportance();
        Status status = view.getInputStatus();

        try {
          if (!isValidMilestone()) {
            throw new IllegalArgumentException("Invalid milestone");
          }
        } catch (IllegalArgumentException ex) {
          view.milestonesErrorPane();
          return;
        }

        try {
          if (!isValidDate()) {
            throw new IllegalArgumentException("Invalid date entered.");
          }
          // Date is valid, continue
          LocalDate duedate =
              LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));

          Task task;
          if (milestoneList.size() > 0) {
            task =
                new TaskImpl(
                    title, description, duedate, importance, status, milestoneList, category);
          } else {
            task = new TaskImpl(title, description, duedate, importance, status, category);
          }

          if (model.allTasks().contains(task)) {
            view.saveErrorInNewDialog("Task title already existed!");
          } else {
            model.addTask(task);
            view.updateMenuPanel(model.getMenuListNumber());
            view.saveInNewDialog();
          }
        } catch (IllegalArgumentException ex) {
          view.dateErrorOptionPane();
        }
      }
    }
  }

  /**
   * This class represents the action of ClearInCreateNewTask button
   * <p></p>
   */
  class ClearInCreateNewTaskAction implements Runnable {

    @Override
    public void run() {
      view.clearAllInput();
    }
  }

  /**
   * This class represents the action of SaveInEditTask
   * <p></p>
   */
  class SaveInEditTaskAction implements Runnable{

    @Override
    public void run() {
      Task selectedTask = view.getSelectedTask();
      List temp = model.getNameList();
      temp.remove(selectedTask.getTitle());
      String newTitle = view.getInputTitle();
      try {
        if (!isValidMilestone()) {
          throw new IllegalArgumentException("Invalid milestone");
        }
       } catch (IllegalArgumentException ex) {
        view.milestonesErrorPane();
        return;
        }


      try {
        if (newTitle.isEmpty() || temp.contains(newTitle)){
          throw new IllegalStateException("Invalid task title!");
        }

        if (!isValidDate()){
          throw new IllegalArgumentException("Invalid date entered.");
        }

        String day = view.getInputDueDay();
        String month = view.getInputDueMonth();
        String year = view.getInputDueYear();
        LocalDate duedate =
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        model.editTaskDueDate(selectedTask, duedate);
        model.editTaskTitle(selectedTask, newTitle);
        model.editTaskDescription(selectedTask, view.getInputDescription());
        model.editTaskCategory(selectedTask, view.getInputCategory());
        model.editTaskPriority(selectedTask, view.getInputPriority());
        model.editTaskStatus(selectedTask, view.getInputStatus());
        model.editTimeFrame(selectedTask, milestoneList);
        view.updateMenuPanel(model.getMenuListNumber());
        System.out.println("Save 1");
        view.saveInNewDialog();
//        configureButtonListener();
        view.addTaskContextPanel(selectedTask);
      } catch (IllegalArgumentException ex1) {
        view.dateErrorOptionPane();
      } catch (IllegalStateException ex2) {
        view.saveErrorInNewDialog("Invalid task title!");
      }
    }
  }
}

