package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.*;

public class CompleteTaskController implements AcceptsStringArgument
{
  public TextArea displayDescription;
  public Label displayType;
  public ComboBox<Resident> completedByDropdown;
  public Label displayPoint;
  public Button cancelButton;
  public Button completeTaskButton;
  public Label statusLabel;
  public Label displayCreatedBy;
  private DataManager dataManager;
  private int taskId;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    completedByDropdown.getItems().addAll(dataManager.getAllResidents());
  }

  @Override public void setArgument(String argument)
  {
    this.taskId = Integer.parseInt(argument);
    Task task = dataManager.getTaskById(taskId);
    completeTaskButton.setDisable(true);
    displayType.setText(task.getType());
    displayDescription.setText(task.getDescription());
    displayPoint.setText(Integer.toString(task.getPointsGained()));
    if (task instanceof Barter)
    {
      displayCreatedBy.setText(((Barter) task).getCreatedBy().getName());
    }

    System.out.println(task.getClass());
  }

  public void onCompletedByDropdown()
  {
    statusLabel.setText("klar!");
    completeTaskButton.setDisable(false);
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageTask");
  }

  public void onCompleteTasKButton()
  {
    int residentId = completedByDropdown.getValue().getId();
    try
    {
      dataManager.completeTaskFromList(taskId, residentId); //TODO tilføj confirmation
      ViewManager.showView("ManageTask");
    }
    catch (InvalidTaskException | InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
