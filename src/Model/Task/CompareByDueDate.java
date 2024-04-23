package Model.Task;

import java.util.Comparator;

/**
 * This class is the comparator based on due date
 * implementing Comparator interface
 */
public class CompareByDueDate implements Comparator<Task> {

  /**
   * Return negative if t1 is before t2, 0, if t1 is equal to t2, positive if t1 is after t2
   * @param t1 the first object to be compared.
   * @param t2 the second object to be compared.
   * @return negative if t1 is before t2, 0, if t1 is equal to t2, positive if t1 is after t2
   */
  @Override
  public int compare(Task t1, Task t2) {
    return t1.getDueDate().compareTo(t2.getDueDate());
  }
}
