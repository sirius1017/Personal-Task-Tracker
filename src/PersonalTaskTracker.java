import Controller.*;
import Model.*;
import View.*;

public class PersonalTaskTracker {
  public static void main(String[] args){
      TaskTrackerModel model = new TaskTrackerImpl();
      TaskTrackerView view = new JFrameView();
      Controller controller = new Controller(model, view);
  }
}
