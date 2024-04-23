import Controller.*;
import Model.*;
import View.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PersonalTaskTracker {
  public static void main(String[] args){
      TaskTrackerModel model = new TaskTrackerImpl();
      TaskTrackerView view = new JFrameView();
      Controller controller = new Controller(model, view);
  }
}
