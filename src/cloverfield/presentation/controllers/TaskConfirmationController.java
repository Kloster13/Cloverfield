package cloverfield.presentation.controllers;

import cloverfield.domain.Barter;
import cloverfield.domain.Green;
import cloverfield.domain.Task;
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
  private DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  @Override public void setArgument(Object argument)
  {
    Task task = (Task) argument;
    if(task.getType().equals("Bytte"))
    {
      task = (Barter) argument;
    }
    else
    {
      task = (Task) argument;
      System.out.println(task);
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
  }

  public void onCancelButton()
  {
    ViewManager.showView("AddTask");
  }

}
