package Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents Milestones,
 * the list of milestones
 */
public class Milestones {
  private List<Milestone> milestones;

  /**
   * The constructor of Milestones, new ArrayList
   */
  public Milestones(){
    milestones = new ArrayList<>();
  }

  /**
   * Add a new milestone to the time frame
   * @param m the new milestone added
   */
  public void addMilestone(Milestone m){
    if (milestones.contains(m)){
      throw new IllegalArgumentException("The task has already existed.");
    }
    milestones.add(m);
  }

  /**
   * Get the milestone at given index i
   * @param i the given index
   * @return the milestone at index i
   */
  public Milestone getIndex(int i){
    return milestones.get(i);
  }

  /**
   * Get the size of milestones
   * @return the number of milestones
   */
  public int size(){
    return milestones.size();
  }

  /**
   * Return the list of all milestones
   * @return the list of all milestones
   */
  public List<Milestone> allMilestones(){
    return milestones;
  }

  /**
   * Delete a specified milestone from the time frame (if it exists)
   * @param m the milestone removed
   */
  public void deleteMilestone(Milestone m){
    if (!milestones.contains(m)){
      return;
    }

    milestones.remove(m);

    m.setStatus(Status.DELETED);
  }

  /**
   * Return true if each element of Milestones lists is equal
   * @param obj the compared obj
   * @return true if each element of Milestones lists is equal, otherwise false
   */
  @Override
  public boolean equals(Object obj) {
    if ( !(obj instanceof Milestones)){
      return false;
    }
    Milestones x = (Milestones) obj;
    if (this.size() != x.size() ){
      return false;
    }
    for(int i = 0; i<this.size(); i++){
      if (! this.getIndex(i).equals(x.getIndex(i))){
        return false;
      }
    }
    return true;
  }

  /**
   * Get the hashCode of the Milestones
   * @return the hashCode of the Milestones
   */
  @Override
  public int hashCode(){
    return Objects.hash(milestones);
  }
}
