package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsObjectArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TaskConfirmationController implements AcceptsObjectArgument
{
  public Label typeDisplay;
  public Label pointsDisplay;
  public Label createdByDisplay;
  public Label reservedByDisplay;
  public Text descriptionDisplay;
  public Button cancelButton;
  public Button confirmButton;
  public Label displayErrors;
  private DataManager dataManager;
  private Task task;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  @Override public void setArgument(Object argument)
  {
    this.task = (Task) argument;
    if (task.getType().equals("Bytte"))
    {
      task = (Barter) argument;
    }
    else
    {
      task = (Task) argument;
    }

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

  public void onConfirmButton()
  {
    try
    {
      dataManager.addTask(task);
      ViewManager.showView("ManageTask");
    }
    catch (InvalidResidentException | InvalidTaskException e)
    {
      displayErrors.setText(
          e.getMessage()); // TODO Dette er noget være lort, men det kræver et check på tidligere view for at vi kan vise den der
    }
  }

  public void onCancelButton()
  {
    ViewManager.showView("AddTask"); //TODO ved overskud skal denne holde værdier - lav prio
  }
}
