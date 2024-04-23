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

/**
 * This class tests methods of TaskTrackerImpl, task,
 * milestone and Milestones
 */
public class TaskTrackerImplTest {

  TaskTrackerModel taskTracker;
  private Task task1, task2, task3, task4;
  private Milestone milestone1, milestone2;
  private Milestones milestonesList;

  /**
   * Setup some constructor os TaskImpl, Milestone, Milestones and TaskTracker
   * @throws Exception IllegalArgumentException
   */
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

  /**
   * Test if the method od addTask()
   */
  @Test
  public void testAddTask() {
    assertEquals(4, taskTracker.getNumOfAllTasks());
  }

  /**
   * Test addTask() of one type of invalid input
   * @throws Exception IllegalArgumentException
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidAddTask1() throws Exception{
    Task task5 = new TaskImpl("taskTest1", "Copy taskTest 1",
        LocalDate.of(2024, 12, 1),
       Importance.NOTIMPORTANT, Status.INPROGRESS, "WORK");
    taskTracker.addTask(task5);
  }

  /**
   * Test addTask() of another type of invalid input
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddTask2() throws Exception {
    taskTracker.addTask(task1);
  }

  /**
   * Test the method of deleteTask(Task)
   */
  @Test
  public void testDeleteTask() {
    taskTracker.deleteTask(task4);
    assertEquals(3, taskTracker.getNumOfAllTasks());
    taskTracker.deleteTask(task3);
    assertEquals(2, taskTracker.getNumOfAllTasks());
    taskTracker.deleteTask(task3);
    assertEquals(2, taskTracker.getNumOfAllTasks());
  }


  /**
   * Test the method of getTasksUncompletedBefore(LocalDate)
   */
  @Test
  public void testGetTasksUncompletedBefore() {
    assertEquals(4, taskTracker.getTasksUncompletedBefore
        (LocalDate.of(2025, 12, 31)).size());
    assertEquals(3, taskTracker.getTasksUncompletedBefore
        (LocalDate.of(2024, 7, 31)).size());
  }

  /**
   * Test the method of getNumOfAllTasks()
   */
  @Test
  public void testGetNumOfAllTasks() {
    assertEquals(4, taskTracker.getNumOfAllTasks());
  }

  /**
   * Test the method of getNumOfTasksUnCompleted(LocalDate)
   */
  @Test
  public void getNumOfTasksUnCompleted() {
    assertEquals(4, taskTracker.getNumOfTasksUnCompleted(LocalDate.of(2025, 12, 31)));
    assertEquals(3, taskTracker.getNumOfTasksUnCompleted(LocalDate.of(2024, 7, 31)));
  }

  /**
   * Test the method of getMenuListNumber()
   */
  @Test
  public void getMenuListNumber() {
    assertEquals((Integer)4, taskTracker.getMenuListNumber().get(10));
    assertEquals((Integer)1, taskTracker.getMenuListNumber().get(0));
  }

  /**
   * Test the method of getNumOfTasksOfPriority(Priority)
   */
  @Test
  public void getNumOfTasksOfPriority() {
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.A));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.B));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.C));
    assertEquals(1, taskTracker.getNumOfTasksOfPriority(Priority.D));
  }

  /**
   * Test the method of getTasksOfPriority(Priority)
   */
  @Test
  public void testGetTasksOfPriority() {
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.A).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.B).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.C).size());
    assertEquals(1, taskTracker.getTasksOfPriority(Priority.D).size());
  }

  /**
   * Test the method of editTimeFrame(Task, Milestones)
   */
  @Test
  public void testEditTimeFrame() {
    taskTracker.editTimeFrame(task1, milestonesList);
    assertEquals(milestonesList, task1.getMilestones());
  }

  /**
   * Test the method of editTaskPriority(Task, Priority)
   */
  @Test
  public void testEditTaskPriority() {
    taskTracker.editTaskPriority(task1, Priority.D);
    assertEquals(Priority.D, task1.getPriority());
    taskTracker.editTaskPriority(task2, Priority.B);
    assertEquals(Priority.B, task2.getPriority());
  }

  /**
   * Test the method of testEditTaskDescription(Task, String)
   */
  @Test
  public void testEditTaskDescription() {
    taskTracker.editTaskDescription(task1, "new");
    taskTracker.editTaskDescription(task2, "newnew");
    assertEquals("new", task1.getDescription());
    assertEquals("newnew", task2.getDescription());
  }

  /**
   * Test the method of editTaskDueDate(Task, LocalDate)
   */
  @Test
  public void testEditTaskDueDate() {
    taskTracker.editTaskDueDate(task1, LocalDate.of(2024, 6, 1));
    taskTracker.editTaskDueDate(task2, LocalDate.of(2025, 1, 1));
    assertEquals(LocalDate.of(2024, 6, 1), task1.getDueDate());
    assertEquals(LocalDate.of(2025, 1, 1), task2.getDueDate());
  }

  /**
   * Test the method of editTaskTitle(Task, String)
   */
  @Test
  public void testEditTaskTitle() {
    taskTracker.editTaskTitle(task1, "title1");
    taskTracker.editTaskTitle(task2, "title2");
    assertEquals("title1", task1.getTitle());
    assertEquals("title2", task2.getTitle());
  }

  /**
   * Test the method of editTaskStatus(Task, Status)
   */
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

  /**
   * Test method equals() of Milestones and TaskTrackerImpl
   */
  @Test
  public void testEquals() {
    Milestones M = new Milestones();
    M.addMilestone(milestone1);
    assertFalse(milestonesList.equals(M));
    assertFalse(M.equals(milestonesList));
    M.addMilestone(milestone2);
    assertTrue(milestonesList.equals(M));
    assertTrue(M.equals(milestonesList));

    TaskTrackerModel T = new TaskTrackerImpl();
    T.addTask(task1);
    T.addTask(task2);
    assertFalse(taskTracker.equals(T));
    assertFalse(T.equals(taskTracker));
    T.addTask(task3);
    T.addTask(task4);
    assertTrue(taskTracker.equals(T));
    assertTrue(T.equals(taskTracker));
  }

  /**
   * Test the method of hashCode() of Milestones,
   * TaskTrackerImpl
   */
  @Test
  public void testHashCode(){
    Milestones M = new Milestones();
    M.addMilestone(milestone1);
    assertNotEquals(milestonesList.hashCode(), M.hashCode());
    M.addMilestone(milestone2);
    assertEquals(milestonesList.hashCode(), M.hashCode());

    TaskTrackerModel T = new TaskTrackerImpl();
    T.addTask(task1);
    T.addTask(task2);
    assertNotEquals(taskTracker.hashCode(), T.hashCode());
    T.addTask(task3);
    T.addTask(task4);
    assertEquals(taskTracker.hashCode(), T.hashCode());
  }

  /**
   * Test the method of getIndex(int) of TaskTrackerImpl
   */
  @Test
  public void testGetIndex(){
    assertEquals(task1, taskTracker.getIndex(0));
    assertEquals(task2, taskTracker.getIndex(1));
    assertEquals(task3, taskTracker.getIndex(2));
    assertEquals(task4, taskTracker.getIndex(3));
  }

}