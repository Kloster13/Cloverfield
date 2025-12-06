package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsObjectArgument;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.swing.text.View;

public class EditTaskController implements AcceptsStringArgument
{
  public ComboBox<String> typeInput;
  public ComboBox<Resident> reservedByInput;
  public TextArea descriptionInput;
  public Spinner<Integer> pointInput;
  public ComboBox<Resident> createdBy;
  public Button addTaskButton;
  public Label statusLabel;
  private DataManager dataManager;
  private Task taskToShow;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    typeInput.getItems().addAll("Grøn", "Fælles", "Bytte");
    createdBy.getItems().addAll(dataManager.getAllResidents());
    reservedByInput.getItems().addAll(dataManager.getAllResidents());
  }

  @Override public void setArgument(String argument)
  {
    this.taskToShow = dataManager.getTaskById(Integer.parseInt(argument));
    typeInput.setValue(taskToShow.getType());
    descriptionInput.setText(taskToShow.getDescription());
    if (taskToShow instanceof Barter)
    {
      createdBy.setValue(((Barter) taskToShow).getCreatedBy());
    }
    else
    {
      createdBy.setDisable(true);
    }

    if (taskToShow.getReservedBy() != null)
    {
      reservedByInput.setValue(taskToShow.getReservedBy());
    }

    pointInput.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, taskToShow.getPointsGained()));

    BooleanBinding minimumFilled = descriptionInput.textProperty().isNotEmpty()
        .and(typeInput.valueProperty().isNotNull());
    addTaskButton.disableProperty().bind(minimumFilled.not());
  }

  public void onTaskTypeDropdown()
  {
    if (typeInput.getValue().equals("Bytte"))
    {
      createdBy.setDisable(false);
    }
    else
    {
      createdBy.setValue(null);
      createdBy.setDisable(true);
    }
  }

  public void onReservedBy()
  {
  }

  public void onCreatedBy()
  {
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageTask");
  }

  public void onAddTasKButton()
  {
    String taskType = typeInput.getValue();
    String descriptionInputText = descriptionInput.getText();
    int pointsValue = pointInput.getValue();
    Resident createdByValue = null;
    if (taskType.equals("Bytte"))
    {
      createdByValue = createdBy.getValue();
    }

    Task task = null;
    try
    {
      switch (taskType)
      {
        case "Grøn":
          task = new Green(descriptionInputText, pointsValue);
          break;
        case "Fælles":
          task = new Collective(descriptionInputText, pointsValue);
          break;
        case "Bytte":
          task = new Barter(descriptionInputText, pointsValue, createdByValue);
          break;
      }
      if (reservedByInput.getValue() != null)
      {
        task.setReservedBy(reservedByInput.getValue());
      }
      dataManager.editTask(taskToShow.getId(), task);
      ViewManager.showView("ManageTask");
    }
    catch (InvalidTaskException | InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
