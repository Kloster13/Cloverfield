package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CompleteTaskController implements AcceptsStringArgument
{
  private DataManager dataManager;
  public TextArea displayDescription;
  public Label displayType;
  public ComboBox<Resident> completedByDropdown;
  public Label displayPoint;
  public Button cancelButton;
  public Button completeTaskButton;
  public Label statusLabel;
  public Label displayCreatedBy;
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
  }

  public void onCompleteTasKButton()
  {
    int residentId = completedByDropdown.getValue().getId();
    try
    {
      dataManager.completeTaskFromList(taskId, residentId);
      ViewManager.showView("ManageTask", "Opgave oprettet");
    }
    catch (InvalidTaskException | InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageTask");
  }

  public void onCompletedByDropdown()
  {
    statusLabel.setText("klar!");
    completeTaskButton.setDisable(false);
  }

}
