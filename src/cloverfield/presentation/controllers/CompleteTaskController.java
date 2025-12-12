package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.*;

public class CompleteTaskController implements AcceptsStringArgument
{
  private DataManager dataManager;
  public TextArea descriptionDisplay;
  public Label typeDisplay;
  public Label pointsDisplay;
  public Button completeTaskButton;
  public Label createdByDisplay;
  public ComboBox<Resident> completedByDropdown;
  public Label statusLabel;
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
    typeDisplay.setText(task.getType());
    descriptionDisplay.setText(task.getDescription());
    pointsDisplay.setText(Integer.toString(task.getPointsGained()));
    if (task instanceof Barter)
    {
      createdByDisplay.setText(((Barter) task).getCreatedBy().getName());
    }
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
      dataManager.setActiveStatus();
      ViewManager.showView("ManageTask", "Opgave oprettet");
    }
    catch (InvalidTaskException | InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
