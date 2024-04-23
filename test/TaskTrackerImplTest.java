import static org.junit.Assert.*;

import Model.Task.Importance;
import Model.Task.Milestone;
import Model.Task.Milestones;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import Model.Task.TaskImpl;
import Model.TaskTrackerImpl;
import Model.TaskTrackerModel;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class TaskTrackerImplTest {

  TaskTrackerModel taskTracker;
  private Task task1, task2, task3, task4;
  private Milestone milestone1, milestone2;
  private Milestones milestonesList;

  @Before
  public void setUp() throws Exception {
    task1 = new TaskImpl("taskTest1", "This is taskTest 1",
        LocalDate.of(2024, 5, 1),
        Importance.IMPORTANT, Status.INPROGRESS, "WORK");
    milestone1 = new Milestone("milestone1", 70, false);
    milestone2 = new Milestone("milestone2", 100, false);
    milestonesList = new Milestones();
    milestonesList.addMilestone(milestone1);
    milestonesList.addMilestone(milestone2);
    task2 = new TaskImpl("taskTest2", "This is taskTest 2",
        LocalDate.of(2024, 12, 1),
        Importance.IMPORTANT, Status.INPROGRESS, milestonesList,"STUDY");
    task3 = new TaskImpl("taskTest3","This is taskTest 3",
        LocalDate.of(2024, 7, 1),
        Importance.NOTIMPORTANT, Status.INPROGRESS, "TRAVEL" );
    task4 = new TaskImpl("taskTest4","This is taskTest 4",
        LocalDate.of(2024, 5, 20),
        Importance.NOTIMPORTANT, Status.INPROGRESS, "TRAVEL" );
    taskTracker = new TaskTrackerImpl();
    taskTracker.addTask(task1);
    taskTracker.addTask(task2);
    taskTracker.addTask(task3);
    taskTracker.addTask(task4);
  }

  @Test
  public void testAddTask() {
    assertEquals(4, taskTracker.getNumOfAllTasks());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidAddTask1() throws Exception{
    Task task5 = new TaskImpl("taskTest1", "Copy taskTest 1",
        LocalDate.of(2024, 12, 1),
       Importance.NOTIMPORTANT, Status.INPROGRESS, "WORK");
    taskTracker.addTask(task5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddTask2() throws Exception {
    taskTracker.addTask(task1);
  }

  @Test
  public void testDeleteTask() {
    taskTracker.deleteTask(task4);
    assertEquals(3, taskTracker.getNumOfAllTasks());
    taskTracker.deleteTask(task3);
    assertEquals(2, taskTracker.getNumOfAllTasks());
    taskTracker.deleteTask(task3);
    assertEquals(2, taskTracker.getNumOfAllTasks());
  }


  @Test
  public void testGetTasksUncompletedBefore() {
    assertEquals(4, taskTracker.getTasksUncompletedBefore
        (LocalDate.of(2025, 12, 31)).size());
    assertEquals(3, taskTracker.getTasksUncompletedBefore
        (LocalDate.of(2024, 7, 31)).size());
  }

  @Test
  public void testGetNumOfAllTasks() {
    assertEquals(4, taskTracker.getNumOfAllTasks());
    }

  @Test
  public void getNumOfTasksUnCompleted() {
    assertEquals(4, taskTracker.getNumOfTasksUnCompleted(LocalDate.of(2025, 12, 31)));
    assertEquals(3, taskTracker.getNumOfTasksUnCompleted(LocalDate.of(2024, 7, 31)));
  }

  @Test
  public void getMenuListNumber() {
    assertEquals((Integer)4, taskTracker.getMenuListNumber().get(10));
    assertEquals((Integer)1, taskTracker.getMenuListNumber().get(0));
  }

  @Test
  public void getNumOfTasksOfPriority() {
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.A));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.B));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.C));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.D));
  }

  @Test
  public void getTasksOfPriority() {
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.A).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.B).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.C).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.D).size());
  }

  @Test
  public void editTimeFrame() {
    taskTracker.editTimeFrame(task1, milestonesList);
    assertEquals(milestonesList, task1.getMilestones());
  }

  @Test
  public void editTaskPriority() {
    taskTracker.editTaskPriority(task1, Priority.D);
    assertEquals(Priority.D, task1.getPriority());
    taskTracker.editTaskPriority(task2, Priority.B);
    assertEquals(Priority.B, task2.getPriority());
  }

  @Test
  public void editTaskDescription() {
    taskTracker.editTaskDescription(task1, "new");
    taskTracker.editTaskDescription(task2, "newnew");
    assertEquals("new", task1.getDescription());
    assertEquals("newnew", task2.getDescription());
  }

  @Test
  public void editTaskDueDate() {
    taskTracker.editTaskDueDate(task1, LocalDate.of(2024, 6, 1));
    taskTracker.editTaskDueDate(task2, LocalDate.of(2025, 1, 1));
    assertEquals(LocalDate.of(2024, 6, 1), task1.getDueDate());
    assertEquals(LocalDate.of(2025, 1, 1), task2.getDueDate());
  }

  @Test
  public void editTaskTitle() {
    taskTracker.editTaskTitle(task1, "title1");
    taskTracker.editTaskTitle(task2, "title2");
    assertEquals("title1", task1.getTitle());
    assertEquals("title2", task2.getTitle());
  }

  @Test
  public void testEditTaskStatus(){
    taskTracker.editTaskStatus(task1, Status.COMPLETED);
    assertEquals(Status.COMPLETED, task1.getStatus());
    assertEquals(Status.COMPLETED, taskTracker.allTasks().get(0).getStatus());
    assertTrue(taskTracker.allTasks().get(0).isCompleted());
    assertTrue(task1.isCompleted());
    taskTracker.editTaskStatus(task2, Status.DELETED);
    assertEquals(Status.DELETED, task2.getStatus());
    assertEquals(Status.DELETED, taskTracker.allTasks().get(1).getStatus());
    assertTrue(taskTracker.allTasks().get(1).isDeleted());
    assertTrue(task2.isDeleted());

  }
}