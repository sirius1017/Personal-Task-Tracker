package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * This class represents the button listener,
 * performed corresponding actions based on specific buttons
 */
public class ButtonListener implements ActionListener {
  Map<String, Runnable> buttonClickedActions;

  /**
   * Empty default constructor
   */
  public ButtonListener(){
  }

  /**
   * Set button clicked action map based on button commands
   * @param map the map between button command and runnable
   */
  public void setButtonClickedActionsMap(Map<String, Runnable> map){
    buttonClickedActions = map;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
