package cloverfield.presentation.controllers;

import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsObjectArgument;
import cloverfield.presentation.core.AcceptsStringArgument;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CompleteTaskController implements AcceptsStringArgument
{
  public TextArea displayDescription;
  public Label displayType;
  public ComboBox createdByDropdown;
  public Label displayPoint;
  public Button cancelButton;
  public Button completeTaskButton;
  private DataManager dataManager;
  private int taskId;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  @Override public void setArgument(String argument)
  {
    this.taskId = Integer.parseInt(argument);
    Task task = dataManager.getTaskById(taskId);
    displayType.setText(task.getType());
    displayDescription.setText(task.getDescription());
    displayPoint.setText(Integer.toString(task.getPointsGained()));

    System.out.println(task.getClass());
  }

  public void onCreatedByDropdown(ActionEvent actionEvent)
  {

  }

  public void onCancelButton(ActionEvent actionEvent)
  {
  }

  public void onCompleteTasKButton(ActionEvent actionEvent)
  {

  }
}
