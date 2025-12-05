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
  public ComboBox typeInput;
  public ComboBox reservedByInput;
  public TextArea descriptionInput;
  public Button addTaskButton;
  public Button cancelButton;
  public Spinner pointInput;
  public Label statusLabel;
  public ComboBox createdBy;
  private DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    typeInput.getItems().addAll("Grøn", "Fælles", "Bytte");
    createdBy.getItems().addAll("Bob", "Don"); //TODO

    //Spinner
    pointInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, 0));

    BooleanBinding minimumFilled = descriptionInput.textProperty().isNotEmpty()
        .and(typeInput.valueProperty().isNotNull());
    addTaskButton.disableProperty().bind(minimumFilled.not());
  }

  public void onTaskTypeDropdown()
  {
    String taskType = typeInput.getValue().toString();
    statusLabel.setText(taskType);
  }

  public void onReservedBy()
  {
  }

  public void onAddTasKButton()
  {
    String taskType = typeInput.getValue().toString();
    String descriptionInputText = descriptionInput.getText();
    int pointsValue = (int) pointInput.getValue();
    //TODO add the resident list - skal bruges både i reserved by og created by . VIGTIG!!
    Resident dummy = null;
    if (taskType.equals("Bytte"))
    {
      String createdByValue = createdBy.getValue()
          .toString(); //TODO det skal ændres. Starten skal initialisere listen af residents
      dummy = new Resident(createdByValue); // change!!!!
    }
    else
    {

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
          task = new Barter(descriptionInputText, pointsValue, dummy); //Change
          break;
      }
      ViewManager.showView("TaskConfirmation", task);
    }
    catch (InvalidTaskException e)
    {
      statusLabel.setText(e.getMessage());
    }

  }

  public void onCancelButton(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }

  public void onCreatedBy(ActionEvent actionEvent)
  {
  }
}
