package Model.Task;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.Assert.*;

/**
 * This class is testing methods of Task, Milestone,
 * Milestones, CompareByDueDate
 */
public class TaskTest {

  private Task task1, task2, task3, task4;
  private Milestone milestone1, milestone2;
  private Milestones milestonesList;

  /**
   * Setup the constructor of Task, Milestone and Milestones
   * @throws Exception
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
        LocalDate.of(2024, 5, 15),
        Importance.NOTIMPORTANT, Status.INPROGRESS, "TRAVEL" );

  }

  /**
   * Test the invalid input title of Task
   * @throws Exception IllegalArgumentException
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidTitle() throws Exception{
    Task task = new TaskImpl("", "This is taskTest 1",
        LocalDate.of(2024, 5, 1),
        Importance.IMPORTANT, Status.INPROGRESS, "WORK");
  }

  /**
   * Test the invalid input duedate of Task
   * @throws Exception IllegalArgumentException
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidDueDate() throws Exception{
    Task task = new TaskImpl("task", "This is taskTest 1",
        LocalDate.of(2024, 2, 1),
        Importance.IMPORTANT, Status.INPROGRESS, "WORK");
  }

  /**
   * Test the method of getCreateDate()
   */
  @Test
  public void testGetCreateDate() {
    LocalDate today = LocalDate.now();
    assertEquals(today, task1.getCreateDate());
    assertEquals(today, task2.getCreateDate());
    assertEquals(today, task3.getCreateDate());
    assertEquals(today, milestone1.getCreateDate());
    assertEquals(today, milestone2.getCreateDate());
  }

  /**
   * Test the method of getTitle()
   */
  @Test
  public void testGetTitle() {
    assertEquals("taskTest1", task1.getTitle());
    assertEquals("taskTest2", task2.getTitle());
    assertEquals("taskTest3", task3.getTitle());
    assertEquals("milestone1", milestone1.getTitle());
    assertEquals("milestone2", milestone2.getTitle());

  }

  /**
   * Test the method of getDescription()
   */
  @Test
  public void testGetDescription() {
    assertEquals("This is taskTest 1", task1.getDescription());
    assertEquals("This is taskTest 2", task2.getDescription());
    assertEquals("This is taskTest 3", task3.getDescription());

  }

  /**
   * Test the method of getDueDate()
   */
  @Test
  public void testGetDueDate() {
    LocalDate expected1 = LocalDate.of(2024, 5, 1);
    LocalDate expected2 = LocalDate.of(2024, 12, 1);
    LocalDate expected3 = LocalDate.of(2024, 7, 1);
    assertEquals(expected1, task1.getDueDate());
    assertEquals(expected2, task2.getDueDate());
    assertEquals(expected3, task3.getDueDate());
  }

  /**
   * Test the method of getPriority()
   */
  @Test
  public void testGetPriority() {
    Priority expected1 = Priority.A;
    Priority expected2 = Priority.B;
    Priority expected3 = Priority.D;
    Priority expected4 = Priority.C;

    assertEquals(expected1, task1.getPriority());
    assertEquals(expected2, task2.getPriority());
    assertEquals(expected3, task3.getPriority());
    assertEquals(expected4, task4.getPriority());
  }

  /**
   * Test the method of getCategory()
   */
  @Test
  public void testGetCategory() {
    assertEquals("WORK", task1.getCategory());
    assertEquals("STUDY", task2.getCategory());
    assertEquals("TRAVEL", task3.getCategory());
  }

  /**
   * Test the method of getStatus()
   */
  @Test
  public void testGetStatus() {
    assertEquals(Status.INPROGRESS, task1.getStatus());
    assertEquals(Status.INPROGRESS, task2.getStatus());
    assertEquals(Status.INPROGRESS, task3.getStatus());
  }

  /**
   * Test the method of getMilestones()
   */
  @Test
  public void testGetMilestones() {
    assertNull(task1.getMilestones());
    assertEquals(milestonesList, task2.getMilestones());
    assertEquals(null, task3.getMilestones());
  }

  /**
   * Test the method of getProgress()
   */
  @Test
  public void testGetProgress() {
    assertEquals(100, task1.getProgress());
    assertEquals(100, task2.getProgress());
    assertEquals(100, task3.getProgress());
    assertEquals(70, milestone1.getProgress());
    assertEquals(100, milestone2.getProgress());
  }

  /**
   * Test the method of getImportance()
   */
  @Test
  public void testGetImportance() {
    assertEquals(Importance.IMPORTANT, task1.getImportance());
    assertEquals(Importance.IMPORTANT, task2.getImportance());
    assertEquals(Importance.NOTIMPORTANT, task3.getImportance());
    assertEquals(Importance.NOTIMPORTANT, task4.getImportance());
  }

  /**
   * Test the method of getRemainingDays()
   */
  @Test
  public void testGetRemainingDays() {
    assertEquals(DAYS.between(LocalDate.now(), LocalDate.of(2024, 5, 1)), task1.getRemainingDays());
    assertEquals(DAYS.between(LocalDate.now(), LocalDate.of(2024, 12, 1)), task2.getRemainingDays());
    assertEquals(DAYS.between(LocalDate.now(), LocalDate.of(2024, 7, 1)), task3.getRemainingDays());
    }

  /**
   * Test the method of setTitle(String)
   */
  @Test
  public void testSetTitle() {
    task1.setTitle("newTask1");
    task2.setTitle("newTask2");
    task3.setTitle("newTask3");
    assertEquals("newTask1", task1.getTitle());
    assertEquals("newTask2", task2.getTitle());
    assertEquals("newTask3", task3.getTitle());

  }

  /**
   * Test the method of setCategory(String)
   */
  @Test
  public void testSetCategory(){
    task1.setCategory("TRAVEL");
    assertEquals("TRAVEL", task1.getCategory());
    task2.setCategory("WORK");
    assertEquals("WORK", task2.getCategory());
  }

  /**
   * Test the method of setDescription()
   */
  @Test
  public void testSetDescription() {
    task1.setDescription("newDescription1");
    task2.setDescription("newDescription2");
    task3.setDescription("newDescription3");
    assertEquals("newDescription1", task1.getDescription());
    assertEquals("newDescription2", task2.getDescription());
    assertEquals("newDescription3", task3.getDescription());
  }

  /**
   * Test the method of setDueDate(LocalDate)
   */
  @Test
  public void testSetDueDate() {
    task1.setDueDate(LocalDate.of(2024, 6, 1));
    task2.setDueDate(LocalDate.of(2025, 1, 1));
    task3.setDueDate(LocalDate.of(2024, 9, 1));
    assertEquals(LocalDate.of(2024, 6, 1), task1.getDueDate());
    assertEquals(LocalDate.of(2025, 1, 1), task2.getDueDate());
    assertEquals(LocalDate.of(2024, 9, 1), task3.getDueDate());
  }

  /**
   * Test the method of setImportance(Importance)
   */
  @Test
  public void testSetImportance() {
    task1.setImportance(Importance.NOTIMPORTANT);
    assertEquals(Importance.NOTIMPORTANT, task1.getImportance());
    task1.setImportance(Importance.IMPORTANT);
    assertEquals(Importance.IMPORTANT, task1.getImportance());
  }

  /**
   * Test the method of setMilestones(Milestones)
   */
  @Test
  public void testSetMilestones() {
    task1.setMilestones(milestonesList);
    assertEquals(milestonesList, task1.getMilestones());
    task2.setMilestones(null);
    assertNull(task2.getMilestones());
  }

  /**
   * Test the method of setStatus(String status)
   */
  @Test
  public void testSetStatus() {
    task1.setStatus(Status.COMPLETED);
    assertEquals(Status.COMPLETED, task1.getStatus());
    assertTrue(task1.isCompleted());
    task2.setStatus(Status.DELETED);
    assertEquals(Status.DELETED, task2.getStatus());
    assertTrue(task2.isDeleted());
  }

  /**
   * Test the method of setPriority(Priority)
   */
  @Test
  public void testSetPriority() {
    task1.setPriority(Priority.B);
    task2.setPriority(Priority.D);
    assertEquals(Priority.B, task1.getPriority());
    assertEquals(Priority.D, task2.getPriority());
  }

  /**
   * Test the method of isCompleted()
   */
  @Test
  public void testIsCompleted() {
    assertFalse(task1.isCompleted());
    assertFalse(task2.isCompleted());
    assertFalse(milestone1.isCompleted());
    assertFalse(milestone2.isCompleted());
  }

  /**
   * Test the method of setCompleted()
   */
  @Test
  public void testSetCompleted() {
    task1.setCompleted();
    assertTrue(task1.isCompleted());
    task1.setCompleted();
    assertFalse(task1.isCompleted());
    milestone1.setCompleted();
    assertTrue(milestone1.isCompleted());
    milestone1.setCompleted();
    assertFalse(milestone1.isCompleted());
  }

  /**
   * Test the method of isDeleted()
   */
  @Test
  public void testIsDeleted() {
    assertFalse(task1.isDeleted());
    assertFalse(task2.isDeleted());
    assertFalse(milestone1.isDeleted());
    assertFalse(milestone2.isDeleted());
  }

  /**
   * Test the method of setDeleted()
   */
  @Test
  public void testSetDeleted() {
    task1.setDeleted();
    assertTrue(task1.isDeleted());
    task1.setDeleted();
    assertFalse(task1.isDeleted());
    milestone1.setDeleted();
    assertTrue(milestone1.isDeleted());
    milestone1.setDeleted();
    assertFalse(milestone1.isDeleted());
  }

  /**
   * Test the method of getCreatedDate()
   */
  @Test
  public void testGetCreatedDate() {
    assertEquals(LocalDate.now(), task1.getCreateDate());
    assertEquals(LocalDate.now(), task2.getCreateDate());
  }

  /**
   * Test the method of equals(Object)
   */
  @Test
  public void testEquals() {
    assertFalse(task1.equals(task2));
    assertFalse(task2.equals(task1));
    assertFalse(milestone1.equals(milestone2));
    assertFalse(milestone2.equals(milestone1));
    Task task5 = new TaskImpl("taskTest1", "Copy taskTest 1",
        LocalDate.of(2024, 12, 1),
        Importance.NOTIMPORTANT, Status.INPROGRESS, "WORK");
    assertTrue(task1.equals(task5));
    assertTrue(task5.equals(task1));

    Milestone milestone3 = new Milestone("milestone1", 3, false);
    assertTrue(milestone1.equals(milestone3));
    assertTrue(milestone3.equals(milestone1));
  }

  /**
   * Test the method of hashCode()
   */
  @Test
  public void testHashCode() {
  assertNotEquals(task1.hashCode(), task2.hashCode());
  assertNotEquals(task2.hashCode(), task3.hashCode());
  Task task5 = new TaskImpl("taskTest1", "Copy taskTest 1",
        LocalDate.of(2024, 12, 1),
        Importance.NOTIMPORTANT, Status.INPROGRESS, "WORK");
  assertEquals(task1.hashCode(), task5.hashCode());

  assertNotEquals(milestone1.hashCode(), milestone2.hashCode());
  Milestone milestone3 = new Milestone("milestone1", 3, false);
  assertEquals(milestone1.hashCode(), milestone3.hashCode());
  }

  /**
   * Test the class of CompareByDueDate
   */
  @Test
  public void testCompareByDueDate(){
    assertTrue(new CompareByDueDate().compare(task1, task2) < 0);
    assertTrue(new CompareByDueDate().compare(task2, task1) > 0);
    assertTrue(new CompareByDueDate().compare(task2, task3) > 0);
    assertTrue(new CompareByDueDate().compare(task3, task4) > 0);
  }

  /**
   * Test the method of getIndex(int) of Milestone
   */
  @Test
  public void testGetIndex(){
    assertEquals(milestone1, milestonesList.getIndex(0));
    assertEquals(milestone2, milestonesList.getIndex(1));
  }
}