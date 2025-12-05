package cloverfield.presentation.controllers;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class AddTaskController
{
  public ComboBox<String> typeInput;
  public ComboBox<Resident> reservedByInput;
  public TextArea descriptionInput;
  public Button addTaskButton;
  public Button cancelButton;
  public Spinner<Integer> pointInput;
  public Label statusLabel;
  public ComboBox<Resident> createdBy;
  private DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    typeInput.getItems().addAll("Grøn", "Fælles", "Bytte");
    createdBy.getItems().addAll(dataManager.getAllResidents());

    //Spinner
    pointInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));

    BooleanBinding minimumFilled = descriptionInput.textProperty().isNotEmpty()
        .and(typeInput.valueProperty().isNotNull());
    addTaskButton.disableProperty().bind(minimumFilled.not());
  }

  public void onTaskTypeDropdown()
  {
  }

  public void onReservedBy()
  {
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
      ViewManager.showView("TaskConfirmation", task);
    }
    catch (InvalidTaskException |InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }

  }

  public void onCancelButton(ActionEvent actionEvent)
  {
    ViewManager.showView("ManageTask");
  }

  public void onCreatedBy(ActionEvent actionEvent)
  {
  }
}
