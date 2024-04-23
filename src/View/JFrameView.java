package View;

import Model.Task.Category;
import Model.Task.Importance;
import Model.Task.Priority;
import Model.Task.Status;
import Model.Task.Task;
import Model.TaskTrackerImpl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class represents the view of Personal Task Tracker
 */
public class JFrameView extends JFrame implements TaskTrackerView  {
  private JPanel menupanel, createNewTaskPanel, taskListPanel, editTaskPanel, milestonesPanel,
      milestonesInNewTaskPanel, taskContextPanel;
  private JPanel titlefieldpanel, categorypanel, descriptionPanel, datepanel, importancepanel,
      statuspanel, prioritypanel;
  private int[] list = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

  private JLabel titleInNewTasklabel, categoryInNewTaskLabel, descritionInNewTaskLabel,
      duedateInNewTaskLabel, importanceInNewTaskLabel, statusInNewTaskLabel,
      milestonesInNewTaskLabel, priorityLabel;

  private JTextField titlefieldInNewTask,  monthfieldInNewTask, dayfieldInNewTask,
      yearfieldInNewTask;
  private JTextArea descriptionareaInNewTask;

  private JComboBox categoryboxInNewTask, importanceboxInNewTask, statusboxInNewTask,
      priorityBox;
  JButton saveInNewTask, clearInNewTask, saveInEditTask, createNewTaskButton,
      editInContext;

  private List<JButton> MenuButton, taskButtonList;
  private List<JTextField> milestoneTextfieldListInNewTask, progressTextfieldListInNewTask;
  private List<String> milestoneInputTextListInNewTask, progressInputListInNewTask;
  private List<JCheckBox> milestoneCompleteCheckBoxtList;

  private List<Boolean> milestoneCompleteInputList;
  private Task selectedTask;

  private String[] menu = new String[]{"Need To Do Now", "Need To Plan For",
      "Reschedule or Delegate", "If Had Extra Time",
      "All Uncompleted", "Today", "This Week", "This Month", "Next Three Months",
      "This Year", "All Tasks"};

  /**
   * The constructor of JFrameView
   * <p></p>
   */
  public JFrameView() {
    this.setPreferredSize(new Dimension(1200, 900));
    this.setMinimumSize(new Dimension(800, 600));

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(dimension.width/ 2 - this.getSize().width / 2,
        dimension.height/2-this.getSize().height/2);
    this.setTitle("Personal Task Tracker");

    TaskTrackerImpl system = new TaskTrackerImpl();
    JPanel titleBar = new JPanel();
    titleBar.setPreferredSize(new Dimension(1200, 100));
    titleBar.setBackground(Color.LIGHT_GRAY);
    titleBar.setLayout(new FlowLayout());

    JLabel titleBarText = new JLabel("    Personal Task Tracker");
    titleBarText.setPreferredSize(new Dimension(1200, 100));
    titleBarText.setFont(new Font("Sans-Serif", Font.BOLD, 40));
    titleBarText.setHorizontalAlignment(JLabel.LEFT);

    titleBar.add(titleBarText);

    //newtask = new NewTaskView();
    this.add(titleBar, BorderLayout.NORTH);

    int[] list = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    menupanel = new JPanel();
    menupanel.setPreferredSize(new Dimension(300, 800));
    menupanel.setBackground(Color.WHITE);
    menupanel.setLayout(new GridLayout(12, 1, 15, 0));

    createNewTaskButton = new JButton("Create New Task");
    createNewTaskButton.setActionCommand("Create New Task");
    createNewTaskButton.setForeground(Color.RED);
    createNewTaskButton.setFont(new Font("Sans-Serif", Font.BOLD, 25));
    menupanel.add(createNewTaskButton);

    MenuButton = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      JButton button = new JButton(menu[i] + "     " + list[i]);
      if (i == 0|| i == 1 || i == 5){
        button.setFont(new Font("Sans-Serif", Font.BOLD, 20));
      } else{
        button.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
      }
      button.setActionCommand(menu[i]);

      MenuButton.add(button);
      menupanel.add(button);
    }
    this.add(menupanel, BorderLayout.WEST);
    this.addCreateNewTaskPanel();

    // Construct save button in edit task panel
    saveInEditTask = new JButton("SAVE");
    saveInEditTask.setActionCommand("SaveInEditTask");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    this.setVisible(true);
    setResizable(false);
  }

  @Override
  public void updateMenuPanel(List<Integer> list) {
    for (int i = 0; i < 11; i++) {
      MenuButton.get(i).setText(menu[i] + "     " + list.get(i));

      revalidate();
      this.setVisible(true);
    }
  }

  @Override
  public void addCreateNewTaskPanel(){
    BorderLayout layout = (BorderLayout) getContentPane().getLayout();
    Component center = layout.getLayoutComponent(BorderLayout.CENTER);
    if (center != null) {
      getContentPane().remove(center);
      revalidate();
      repaint();
    }

    createNewTaskPanel = new JPanel();
    createNewTaskPanel.setPreferredSize(new Dimension(900, 800));
    createNewTaskPanel.setLayout(new BorderLayout());

    // Set title bar of this create new task panel
    JLabel titleText = new JLabel(" Create New Task");
    titleText.setPreferredSize(new Dimension(900, 70));
    titleText.setFont(new Font("Sans-Serif", Font.ROMAN_BASELINE, 25));
    titleText.setHorizontalAlignment(JLabel.LEFT);
    createNewTaskPanel.add(titleText, BorderLayout.NORTH);

    JPanel leftside = new JPanel();
    leftside.setPreferredSize(new Dimension(180, 680));

    JPanel rightside = new JPanel();
    rightside.setPreferredSize(new Dimension(720, 680));

    JPanel downside = new JPanel();
    downside.setPreferredSize(new Dimension(800, 50));

    // Add components of leftside
    // Title label
    titleInNewTasklabel =new JLabel(" Title");
    titleInNewTasklabel.setPreferredSize(new Dimension(180, 50));
    titleInNewTasklabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(titleInNewTasklabel);

    // Category label
    categoryInNewTaskLabel = new JLabel(" Category");
    categoryInNewTaskLabel.setPreferredSize(new Dimension(180, 50));
    categoryInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(categoryInNewTaskLabel);

    // Description label;
    descritionInNewTaskLabel =new JLabel(" Description");
    descritionInNewTaskLabel.setPreferredSize(new Dimension(180, 80));
    descritionInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(descritionInNewTaskLabel);

    // Due date label
    duedateInNewTaskLabel =new JLabel(" Due Date");
    duedateInNewTaskLabel.setPreferredSize(new Dimension(180, 50));
    duedateInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(duedateInNewTaskLabel);

    // Importance label
    importanceInNewTaskLabel = new JLabel(" Importance");
    importanceInNewTaskLabel.setPreferredSize(new Dimension(180, 50));
    importanceInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(importanceInNewTaskLabel);

    // Status label
    statusInNewTaskLabel = new JLabel(" Status");
    statusInNewTaskLabel.setPreferredSize(new Dimension(180, 50));
    statusInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftside.add(statusInNewTaskLabel);

    // Milestones label
    milestonesInNewTaskPanel = new JPanel();
    milestonesInNewTaskPanel.setPreferredSize(new Dimension(180,200));
    milestonesInNewTaskLabel = new JLabel(" Milestones");
    milestonesInNewTaskLabel.setPreferredSize(new Dimension(180,70));
    milestonesInNewTaskLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));

    milestonesInNewTaskLabel.setVerticalAlignment(SwingConstants.CENTER);
    milestonesInNewTaskPanel.add(milestonesInNewTaskLabel, BorderLayout.CENTER);
//    milestonesInNewTaskPanel.add(addInMilestonesButton);
    leftside.add(milestonesInNewTaskPanel);

    // Add components of rightside
    // Text field for title input
    titlefieldpanel = new JPanel();
    titlefieldpanel.setPreferredSize(new Dimension(720, 50));
    titlefieldpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    titlefieldInNewTask = new JTextField(20);
    titlefieldInNewTask.setPreferredSize(new Dimension(50, 20));
    JLabel star1 = new JLabel("*");
    star1.setForeground(Color.RED);
    titlefieldpanel.add(star1);
    titlefieldpanel.add(titlefieldInNewTask);
    rightside.add(titlefieldpanel);

    // ComboBox for category
    categorypanel = new JPanel();
    categorypanel.setPreferredSize(new Dimension(720, 50));
    categorypanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    categoryboxInNewTask= new JComboBox(new Category().getCategoryList().toArray());
    JLabel star2 = new JLabel("*");
    star2.setForeground(Color.RED);
    categorypanel.add(star2);
    categorypanel.add(categoryboxInNewTask);
    rightside.add(categorypanel);

    // Text field for description input
    descriptionPanel = new JPanel();
    descriptionPanel.setPreferredSize(new Dimension(720, 80));
    descriptionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    descriptionareaInNewTask = new JTextArea();
    JScrollPane scrollPane = new JScrollPane(descriptionareaInNewTask);
    scrollPane.setPreferredSize(new Dimension(350, 70));
    descriptionPanel.add(scrollPane);
    rightside.add(descriptionPanel);

    // Due date picker
    datepanel = new JPanel();
    datepanel.setPreferredSize(new Dimension(720, 50));
    datepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    rightside.add(datepanel);
    JLabel star3 = new JLabel("*");
    star3.setForeground(Color.RED);
    datepanel.add(star3);
    monthfieldInNewTask = new JTextField(5);
    dayfieldInNewTask = new JTextField(5);
    yearfieldInNewTask = new JTextField(5);
    JLabel yearlabel = new JLabel("/YYYY");
    JLabel monthlabel = new JLabel("/MM");
    JLabel daylabel = new JLabel("/DD");
    datepanel.add(yearfieldInNewTask);
    datepanel.add(yearlabel);
    datepanel.add(monthfieldInNewTask);
    datepanel.add(monthlabel);
    datepanel.add(dayfieldInNewTask);
    datepanel.add(daylabel);


    // ComboBox for importance
    importancepanel = new JPanel();
    importancepanel.setPreferredSize(new Dimension(720, 50));
    importancepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel star4 = new JLabel("*");
    star4.setForeground(Color.RED);
    importancepanel.add(star4);
    String[] importance = {"Important", "Not important"};
    importanceboxInNewTask = new JComboBox(importance);
    importancepanel.add(importanceboxInNewTask);
    rightside.add(importancepanel);

    // ComboBox for Status
    statuspanel = new JPanel();
    statuspanel.setPreferredSize(new Dimension(720, 50));
    statuspanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel star5 = new JLabel("*");
    star5.setForeground(Color.RED);
    statuspanel.add(star5);
    String[] status = {"In Progress", "Completed", "Deleted"};
    statusboxInNewTask = new JComboBox(status);
    statuspanel.add(statusboxInNewTask);
    rightside.add(statuspanel);

    milestonesPanel = new JPanel(new GridLayout(6, 3, 5, 5));
    milestonesPanel.setPreferredSize(new Dimension(720, 200));
    JLabel tableMilestone = new JLabel("Milestone");
    tableMilestone.setPreferredSize(new Dimension(250, 10));
    JLabel tableProgress = new JLabel("Progress (%)");
    tableProgress.setPreferredSize(new Dimension(150, 10));
    JLabel tableCompleted = new JLabel("Completed");
    tableCompleted.setPreferredSize(new Dimension(200, 10));
    milestonesPanel.add(tableMilestone);
    milestonesPanel.add(tableProgress);
    milestonesPanel.add(tableCompleted);


    milestoneTextfieldListInNewTask = new ArrayList<>();
    progressTextfieldListInNewTask = new ArrayList<>();
    milestoneCompleteCheckBoxtList = new ArrayList<>();
    for (int i = 0; i < 5; i++){
      JTextField milestoneStage = new JTextField();
      JTextField milestoneProgress = new JTextField();
      JCheckBox milestoneCompleted = new JCheckBox("Completed");
      milestonesPanel.add(milestoneStage);
      milestonesPanel.add(milestoneProgress);
      milestonesPanel.add(milestoneCompleted);
      milestoneTextfieldListInNewTask.add(milestoneStage);
      progressTextfieldListInNewTask.add(milestoneProgress);
      milestoneCompleteCheckBoxtList.add(milestoneCompleted);
    }

    rightside.add(milestonesPanel);

    // Add components to downside panel
    saveInNewTask = new JButton("SAVE");
    saveInNewTask.setActionCommand("SaveInCreateNewTask");
    clearInNewTask = new JButton("CLEAR");
    clearInNewTask.setActionCommand("ClearInCreateNewTask");
    saveInNewTask.setPreferredSize(new Dimension(300, 50));
    saveInNewTask.setFont(new Font("Sans-Serif", Font.BOLD, 20));
    downside.add(saveInNewTask);
    clearInNewTask.setPreferredSize(new Dimension(300, 50));
    clearInNewTask.setFont(new Font("Sans-Serif", Font.BOLD, 20));
    downside.add(clearInNewTask);

    createNewTaskPanel.add(leftside, BorderLayout.WEST);
    createNewTaskPanel.add(titleText, BorderLayout.NORTH);
    createNewTaskPanel.add(rightside, BorderLayout.CENTER);
    createNewTaskPanel.add(downside, BorderLayout.SOUTH);

    this.add(createNewTaskPanel, BorderLayout.CENTER);
    setVisible(true);
  }

  @Override
  public List<String> getMilestoneTextInputList(){
    milestoneInputTextListInNewTask = new ArrayList<>();
    for (JTextField s: milestoneTextfieldListInNewTask){
      milestoneInputTextListInNewTask.add(s.getText());
    }
    return milestoneInputTextListInNewTask;
  }

  @Override
  public List<String> getMilestoneProgressInputList(){
    progressInputListInNewTask = new ArrayList<>();
    for (JTextField s: progressTextfieldListInNewTask){
      progressInputListInNewTask.add(s.getText());
    }
    return progressInputListInNewTask;
  }

  @Override
  public void setMilestoneTextInputList(Task task){
    for (JTextField m: milestoneTextfieldListInNewTask){
      m.setText("");
      m.setEnabled(false);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        milestoneTextfieldListInNewTask
            .get(i)
            .setText(task.getMilestones().allMilestones().get(i).getTitle());
      }
    }
  }

  @Override
  public void setMilestoneProgressInputList(Task task){
    for (JTextField p: progressTextfieldListInNewTask){
      p.setText("");
      p.setEnabled(false);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        progressTextfieldListInNewTask
            .get(i)
            .setText(String.valueOf(task.getMilestones().allMilestones().get(i).getProgress()));
      }
    }
  }

  @Override
  public void setMilestoneCompletedInputList(Task task){
    for (JCheckBox m: milestoneCompleteCheckBoxtList){
      m.setSelected(false);
      m.setEnabled(false);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        milestoneCompleteCheckBoxtList
            .get(i)
            .setSelected(task.getMilestones().allMilestones().get(i).isCompleted());
      }
    }
  }

  @Override
  public List<Boolean> getMilestoneCompletedInputList(){
    milestoneCompleteInputList = new ArrayList<>();
    for (JCheckBox c:milestoneCompleteCheckBoxtList){
      milestoneCompleteInputList.add(c.isSelected());
    }
    return milestoneCompleteInputList;
  }

  public void setMilestoneTextInEditTask(Task task){
    for (JTextField m: milestoneTextfieldListInNewTask){
      m.setText("");
      m.setEnabled(true);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        milestoneTextfieldListInNewTask
            .get(i)
            .setText(task.getMilestones().allMilestones().get(i).getTitle());
      }
    }
  }

  public void setMilestoneProgressInEditTask(Task task){
    for (JTextField p: progressTextfieldListInNewTask){
      p.setText("");
      p.setEnabled(true);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        progressTextfieldListInNewTask
            .get(i)
            .setText(String.valueOf(task.getMilestones().allMilestones().get(i).getProgress()));
      }
    }
  }

  @Override
  public void setMilestoneCompletedInEdit(Task task){
    for (JCheckBox m: milestoneCompleteCheckBoxtList){
      m.setSelected(false);
      m.setEnabled(true);
    }

    if (task.getMilestones() != null) {
      for (int i = 0; i < task.getMilestones().allMilestones().size(); i++) {
        milestoneCompleteCheckBoxtList
            .get(i)
            .setSelected(task.getMilestones().allMilestones().get(i).isCompleted());
      }
    }
  }

  @Override
  public void milestonesErrorPane() {
    JOptionPane.showMessageDialog(this,
        "One milestone name and progress must be both empty or both be filled!\n"
            + "The milestone progress must be interger from 1 to 100!",
        "Invalid Milestone Input",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void milestoneProgressErrorPane(){
    JOptionPane.showMessageDialog(this,
        "The milestone progress must be interger from 1 to 100!",
        "Invalid Milestone Progress",
        JOptionPane.ERROR_MESSAGE);
  }

  public void saveInNewDialog(){
//    JDialog d = new JDialog(this);
//
//    // Set the dialog d located at the center of the screen
//    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//    d.setLocation(this.getX()+ (this.getWidth()-d.getWidth())/ 2, this.getY() +
//        (this.getHeight() - d.getHeight())/2);
//    d.setSize(new Dimension(300,150));
////    JPanel dPanel = new JPanel(new BorderLayout());
////    dPanel.add(new JLabel("Created successfully!"), BorderLayout.CENTER );
//    JLabel dLabel =new JLabel("Save task successfully!");
//    dLabel.setHorizontalAlignment(SwingConstants.CENTER);
//    dLabel.setVerticalAlignment(SwingConstants.CENTER);
//    d.add(dLabel);
//    d.setVisible(true);
//    //revalidate();
    JOptionPane.showMessageDialog(this, "Save Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
  }

  public void saveErrorInNewDialog(String text){
    JOptionPane.showMessageDialog(this, text,
        text, JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void dateErrorOptionPane() {
    JOptionPane.showMessageDialog(this, "Invalid Date",
        "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public String getInputTitle() {
    return titlefieldInNewTask.getText();
  }


  @Override
  public void setInputTitle(String title) {
    titlefieldInNewTask.setText(title);
  }

  @Override
  public String getInputDescription() {
    return descriptionareaInNewTask.getText();
  }

  @Override
  public void setInputDescription(String description) {
    descriptionareaInNewTask.setText(description);
  }

  @Override
  public String getInputCategory() {
    return categoryboxInNewTask.getSelectedItem().toString();
  }

  @Override
  public void setCategory(String category) {
    categoryboxInNewTask.setSelectedItem(category);
  }

  @Override
  public String getInputDueMonth() {
    return monthfieldInNewTask.getText();
  }

  @Override
  public String getInputDueDay() {
    return dayfieldInNewTask.getText();
  }

  @Override
  public String getInputDueYear() {
    return yearfieldInNewTask.getText();
  }

  @Override
  public void setDueDate(LocalDate dueDate) {
    yearfieldInNewTask.setText(String.valueOf(dueDate.getYear()));
    monthfieldInNewTask.setText(String.valueOf(dueDate.getMonthValue()));
    dayfieldInNewTask.setText(String.valueOf(dueDate.getDayOfMonth()));
  }

  @Override
  public Status getInputStatus() {
    switch (statusboxInNewTask.getSelectedItem().toString()){
      case "In Progress":
        return Status.INPROGRESS;
      case "Completed":
        return Status.COMPLETED;
      case "Deleted":
        return Status.DELETED;
      default:
        return null;
    }
  }

  @Override
  public void setInputStatus(Status status) {
    statusboxInNewTask.setSelectedItem(status.toString());
  }

  @Override
  public Importance getInputImportance() {
    switch ((String) importanceboxInNewTask.getSelectedItem()){
      case "Important":
        return Importance.IMPORTANT;
      case "Not important":
        return Importance.NOTIMPORTANT;
      default:
        throw new IllegalArgumentException("Invalid importance selected");
    }
  }

  @Override
  public void setInputImportance(Importance importance) {
    importanceboxInNewTask.setSelectedItem(importance.toString());
  }

  @Override
  public void clearAllInput() {
    setInputTitle("");
    setInputDescription("");
    yearfieldInNewTask.setText("");
    monthfieldInNewTask.setText("");
    dayfieldInNewTask.setText("");
    categoryboxInNewTask.setSelectedItem("");
    importanceboxInNewTask.setSelectedItem("");
    statusboxInNewTask.setSelectedItem("");
  }

  public void addTaskListPanel(List<Task> taskList){
    BorderLayout layout = (BorderLayout) getContentPane().getLayout();
    Component center = layout.getLayoutComponent(BorderLayout.CENTER);

    if (center != null) {
      getContentPane().remove(center);
      revalidate();
      repaint();
    }

    taskListPanel = new JPanel();
    taskListPanel.setPreferredSize(new Dimension(900, 800));
    Map<JButton, Task> buttonTaskMap = new HashMap<>();
    taskButtonList = new ArrayList<>();
    for (int i = 0; i<taskList.size(); i++){
      JButton taskButton;

      if (taskList.get(i).getRemainingDays() < 0){
        taskButton = new JButton ("Task  " + taskList.get(i).getTitle() + " Expired "
        + taskList.get(i).getRemainingDays() + " days");
      } else{
        taskButton = new JButton ("Task  " + taskList.get(i).getTitle() + " Due in "
            + taskList.get(i).getRemainingDays() + " days");
      }

      if (taskList.get(i).isDeleted()){
        taskButton.setText ("Task  " + taskList.get(i).getTitle() + " Deleted");
        taskButton.setForeground(Color.GRAY);
      }

      if (taskList.get(i).isCompleted()){
        taskButton.setText ("Task  " + taskList.get(i).getTitle() + " Completed");
        taskButton.setForeground(Color.RED);
      }

      taskButton.setSize(new Dimension(300, 200));
      taskButton.setFont(new Font("Sans-Serif", Font.PLAIN, 35));
      taskButtonList.add(taskButton);
      buttonTaskMap.put(taskButtonList.get(i), taskList.get(i));
      taskListPanel.add(taskButtonList.get(i));
      this.add(taskListPanel,BorderLayout.CENTER);
      taskButtonList.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // direct to corresponding task context panel
          BorderLayout layout = (BorderLayout) getContentPane().getLayout();
          Component center = layout.getLayoutComponent(BorderLayout.CENTER);
          if (center != null) {
            getContentPane().remove(center);
            revalidate();
            repaint();
          }

          JButton clickedButton = (JButton) e.getSource();
          Task correspondingTask = buttonTaskMap.get(clickedButton);
          addTaskContextPanel(correspondingTask);

        }
      });
    }

    setVisible(true);
  }


  @Override
  public Task getSelectedTask(){
    return selectedTask;
  }

  @Override
  public void addTaskContextPanel(Task task){
    // Remove previous panel at the center
    BorderLayout layout = (BorderLayout) getContentPane().getLayout();
    Component center = layout.getLayoutComponent(BorderLayout.CENTER);
    if (center != null) {
      getContentPane().remove(center);
      revalidate();
      repaint();
    }

    // Construct a new panel at the center
    taskContextPanel = new JPanel();
    taskContextPanel.setPreferredSize(new Dimension(900, 800));
    taskContextPanel.setLayout(new BorderLayout());

    // Set title bar of this create new task panel
    JLabel titleInTaskContext = new JLabel(" View Task "+ task.getTitle());
    titleInTaskContext.setPreferredSize(new Dimension(900, 70));
    titleInTaskContext.setFont(new Font("Sans-Serif", Font.ROMAN_BASELINE, 25));
    titleInTaskContext.setHorizontalAlignment(JLabel.LEFT);
    taskContextPanel.add(titleInTaskContext, BorderLayout.NORTH);

    JPanel leftsideInContext = new JPanel();
    leftsideInContext.setPreferredSize(new Dimension(180, 680));

    JPanel rightsideInContext = new JPanel();
    rightsideInContext.setPreferredSize(new Dimension(720, 680));

    JPanel downsideInContext = new JPanel();
    downsideInContext.setPreferredSize(new Dimension(800, 50));

    // Add components of leftside
    // Title label
    leftsideInContext.add(titleInNewTasklabel);

    // Category label
    leftsideInContext.add(categoryInNewTaskLabel);

    // Description label;
    leftsideInContext.add(descritionInNewTaskLabel);

    // Due date label
    leftsideInContext.add(duedateInNewTaskLabel);

    // Importance label
    leftsideInContext.add(importanceInNewTaskLabel);

    // Status label
    leftsideInContext.add(statusInNewTaskLabel);

    // Milestones label
    leftsideInContext.add(milestonesInNewTaskPanel);


    //Priority Label
    priorityLabel = new JLabel(" Priority");
    priorityLabel.setPreferredSize(new Dimension(180, 50));
    priorityLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    leftsideInContext.add(priorityLabel);

    // Add components of rightside
    // Text filed for title of task
    setInputTitle(task.getTitle());
    titlefieldInNewTask.setEnabled(false);
    rightsideInContext.add(titlefieldpanel);

    // ComboBox for category
    setCategory(task.getCategory());
    categoryboxInNewTask.setEnabled(false);
    rightsideInContext.add(categorypanel);



    // Text field for description input
    setInputDescription(task.getDescription());
    descriptionareaInNewTask.setEnabled(false);
    rightsideInContext.add(descriptionPanel);


    // Due date picker
    setDueDate(task.getDueDate());
    yearfieldInNewTask.setEnabled(false);
    monthfieldInNewTask.setEnabled(false);
    dayfieldInNewTask.setEnabled(false);
    rightsideInContext.add(datepanel);

    // ComboBox for importance
    setInputImportance(task.getImportance());
    importanceboxInNewTask.setEnabled(false);
    rightsideInContext.add(importancepanel);

    // ComboBox for Status
    setInputStatus(task.getStatus());
    statusboxInNewTask.setEnabled(false);
    rightsideInContext.add(statuspanel);

    // Milestone
    setMilestoneTextInputList(task);
    setMilestoneProgressInputList(task);
    setMilestoneCompletedInputList(task);
    rightsideInContext.add(milestonesPanel);

    // Add priority box
    prioritypanel = new JPanel();
    prioritypanel.setPreferredSize(new Dimension(720, 50));
    prioritypanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel star6 = new JLabel("*");
    star6.setForeground(Color.RED);
    prioritypanel.add(star6);
    String[] priority = {"A", "B", "C", "D"};
    priorityBox = new JComboBox(priority);
    setInputPriority(task.getPriority());
    priorityBox.setEnabled(false);
    prioritypanel.add(priorityBox);
    rightsideInContext.add(prioritypanel);

    // Add components to downside panel
    editInContext = new JButton("EDIT");
    //editInContext.setActionCommand("EditInTaskContext");
    editInContext.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        addEditTaskPanel(task);
      }
    });

    editInContext.setPreferredSize(new Dimension(300, 50));
    editInContext.setFont(new Font("Sans-Serif", Font.BOLD, 20));
    downsideInContext.add(editInContext);

    taskContextPanel.add(leftsideInContext, BorderLayout.WEST);
    taskContextPanel.add(titleInTaskContext, BorderLayout.NORTH);
    taskContextPanel.add(rightsideInContext, BorderLayout.CENTER);
    taskContextPanel.add(downsideInContext, BorderLayout.SOUTH);

    this.add(taskContextPanel,BorderLayout.CENTER);
    setVisible(true);
  }

  @Override
  public void addEditTaskPanel(Task task){
    selectedTask = task;
    BorderLayout layout = (BorderLayout) getContentPane().getLayout();
    Component center = layout.getLayoutComponent(BorderLayout.CENTER);
    if (center != null) {
      getContentPane().remove(center);
      revalidate();
      repaint();
    }

    editTaskPanel = new JPanel();
    editTaskPanel.setPreferredSize(new Dimension(900, 800));
    editTaskPanel.setLayout(new BorderLayout());

    // Set title bar of this create new task panel
    JLabel titleTextInEditPanel = new JLabel(" Edit Task: " + task.getTitle());
    titleTextInEditPanel.setPreferredSize(new Dimension(900, 70));
    titleTextInEditPanel.setFont(new Font("Sans-Serif", Font.ROMAN_BASELINE, 25));
    titleTextInEditPanel.setHorizontalAlignment(JLabel.LEFT);
    editTaskPanel.add(titleTextInEditPanel, BorderLayout.NORTH);

    JPanel leftsideInEditPanel = new JPanel();
    leftsideInEditPanel.setPreferredSize(new Dimension(180, 680));

    JPanel rightsideInEditPanel = new JPanel();
    rightsideInEditPanel.setPreferredSize(new Dimension(720, 680));

    JPanel downsideInEditPanel = new JPanel();
    downsideInEditPanel.setPreferredSize(new Dimension(800, 50));

    // Add components of leftside
    // Title label
    leftsideInEditPanel.add(titleInNewTasklabel);

    // Category label
    leftsideInEditPanel.add(categoryInNewTaskLabel);

    // Description label;
    leftsideInEditPanel.add(descritionInNewTaskLabel);

    // Due date label
    leftsideInEditPanel.add(duedateInNewTaskLabel);

    // Importance label
    leftsideInEditPanel.add(importanceInNewTaskLabel);

    // Status label
    leftsideInEditPanel.add(statusInNewTaskLabel);

    // Milestones label
    leftsideInEditPanel.add(milestonesInNewTaskPanel);

    // Priority label
    leftsideInEditPanel.add(priorityLabel);

    // Add components of rightside
    // Text field for title input
    setInputTitle(task.getTitle());
    titlefieldInNewTask.setEnabled(true);
    rightsideInEditPanel.add(titlefieldpanel);


    // ComboBox for category
    setCategory(task.getCategory());
    categoryboxInNewTask.setEnabled(true);
    rightsideInEditPanel.add(categorypanel);

    // Text field for description input
    setInputDescription(task.getDescription());
    descriptionareaInNewTask.setEnabled(true);
    rightsideInEditPanel.add(descriptionPanel);


    // Due date picker
    setDueDate(task.getDueDate());
    yearfieldInNewTask.setEnabled(true);
    monthfieldInNewTask.setEnabled(true);
    dayfieldInNewTask.setEnabled(true);
    rightsideInEditPanel.add(datepanel);

    // ComboBox for importance
    setInputImportance(task.getImportance());
    importanceboxInNewTask.setEnabled(true);
    rightsideInEditPanel.add(importancepanel);

    // ComboBox for Status
    setInputStatus(task.getStatus());
    statusboxInNewTask.setEnabled(true);
    rightsideInEditPanel.add(statuspanel);

    // Panel for Milestones
    setMilestoneTextInEditTask(task);
    setMilestoneCompletedInEdit(task);
    setMilestoneProgressInEditTask(task);
    rightsideInEditPanel.add(milestonesPanel);

    // Combo box for priority
    priorityBox.setEnabled(true);
    rightsideInEditPanel.add(prioritypanel);

    // Add components to downside panel
    saveInEditTask.setPreferredSize(new Dimension(300, 50));
    saveInEditTask.setFont(new Font("Sans-Serif", Font.BOLD, 20));
    downsideInEditPanel.add(saveInEditTask);

    editTaskPanel.add(leftsideInEditPanel, BorderLayout.WEST);
    editTaskPanel.add(titleTextInEditPanel, BorderLayout.NORTH);
    editTaskPanel.add(rightsideInEditPanel, BorderLayout.CENTER);
    editTaskPanel.add(downsideInEditPanel, BorderLayout.SOUTH);

    this.add(editTaskPanel, BorderLayout.CENTER);
    setVisible(true);
  }

  @Override
  public Priority getInputPriority(){
    switch (priorityBox.getSelectedItem().toString()){
      case "A":
        return Priority.A;
      case "B":
        return Priority.B;
      case "C":
        return Priority.C;
      case "D":
        return Priority.D;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public void setInputPriority(Priority priority){
    priorityBox.setSelectedItem(priority.toString());
  }

  @Override
  public void addActionListener(ActionListener listener) {
    for (int i = 0; i < MenuButton.size(); i++){
      MenuButton.get(i).addActionListener(listener);
    }

    createNewTaskButton.addActionListener(listener);
    saveInNewTask.addActionListener(listener);
    clearInNewTask.addActionListener(listener);
    saveInEditTask.addActionListener(listener);
    //editInContext.addActionListener(listener);

    if (taskButtonList != null){
      for (int j = 0; j < taskButtonList.size(); j++) {
        taskButtonList.get(j).addActionListener(listener);
      }
    }
    revalidate();
  }
}


