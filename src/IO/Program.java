package IO;

import Controller.Controller;
import Model.TaskTrackerImpl;
import Model.TaskTrackerModel;
import View.JFrameView;
import View.TaskTrackerView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Program {
  private TaskTrackerImpl model;

  public Program() {
    // Load program state on startup
    loadState();
  }

  public void run() {

    TaskTrackerView view = new JFrameView();
    Controller controller = new Controller(model, view);


    // Save program state
    saveState();

  }

  private void saveState() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
      System.out.println("saveState: " + model.getNumOfAllTasks());
      oos.writeObject(model);
      oos.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void loadState() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
      model = (TaskTrackerImpl) ois.readObject();
      System.out.println(model.getNumOfAllTasks());
      // Resume program execution from the loaded state
      // This might involve setting up the JFrame with the restored state
    } catch (IOException | ClassNotFoundException e) {
      // No saved state found, start with initial state
      e.printStackTrace();
      model = new TaskTrackerImpl();
    }
  }

  public static void main(String[] args) {
    Program program = new Program();
    program.run();

  }
}
