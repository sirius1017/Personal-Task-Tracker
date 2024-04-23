package Model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the category of the task
 */
public class Category {
  private List<String> list;

  // Constructs Category, with default categories of tasks
  public Category(){
    this.list = new ArrayList<>();
    list.add("WORK");
    list.add("STUDY");
    list.add("WORKOUT");
    list.add("TRAVEL");
    list.add("OTHERS");
  }

  /**
   * add category to this list of categories
   * @param category the category added
   */
  public void addCategory(String category){
    list.add(category);
  }

  /**
   * Get the category list of category
   * @return the list of categories
   */
  public List<String> getCategoryList(){
    return list;
  }

  /**
   * Return true if this category list contains the given category, otherwise false
   * @param category the given category
   * @return true if this category list contains the given category, otherwise false
   */
  public boolean contains(String category){
    if (list.contains(category)){
      return true;
    } else{
      return false;
    }
  }
}
