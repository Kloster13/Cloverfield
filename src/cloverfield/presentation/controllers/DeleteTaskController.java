package cloverfield.presentation.controllers;

import cloverfield.domain.Barter;
import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class DeleteTaskController implements AcceptsStringArgument
{
  private DataManager dataManager;
  public Label typeDisplay;
  public Label pointsDisplay;
  public Label createdByDisplay;
  public Label reservedByDisplay;
  public Text descriptionDisplay;
  private int taskId;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  @Override public void setArgument(String argument)
  {
    this.taskId = Integer.parseInt(argument);
    Task task = dataManager.getTaskById(taskId);

    typeDisplay.setText(task.getType());
    pointsDisplay.setText(String.valueOf(task.getPointsGained()));
    descriptionDisplay.setText(task.getDescription());
    if (!(task.getReservedBy() == null))
    {
      reservedByDisplay.setText(task.getReservedBy().getName());
    }
    if (task instanceof Barter)
    {
      createdByDisplay.setText(((Barter) task).getCreatedBy().getName());
    }
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageTask");
  }

  public void onConfirmButton()
  {
    dataManager.deleteTask(taskId);
    ViewManager.showView("ManageTask", "Opgave blev slettet");
  }
}
