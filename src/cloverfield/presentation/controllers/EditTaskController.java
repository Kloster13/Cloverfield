package cloverfield.presentation.controllers;

import cloverfield.domain.Barter;
import cloverfield.domain.Resident;
import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsObjectArgument;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class EditTaskController implements AcceptsStringArgument
{
  public ComboBox<String> typeInput;
  public ComboBox<Resident> reservedByInput;
  public TextArea descriptionInput;
  public Spinner<Integer> pointInput;
  public ComboBox<Resident> createdBy;
  public Button addTaskButton;
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
  }
}
