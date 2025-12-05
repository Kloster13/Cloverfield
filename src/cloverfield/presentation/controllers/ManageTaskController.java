package cloverfield.presentation.controllers;

import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileAccessException;
import cloverfield.persistence.FileDataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class ManageTaskController
{
  public Button addButton;
  public Button completeButton;
  public Button editButton;
  public Button backButton;
  public TableColumn typeColumn;
  public TableColumn descriptionColumn;
  public TableColumn pointColumn;
  public TableColumn statusTable;
  public TableColumn reservedTable;
  public TableColumn deleteColumn;
  public TableView table;
  public TableView taskTable;
  public ComboBox typeDropdown;
  private DataManager dataManager;
  private FilteredList<Task> taskList;

  public void init(DataManager dataManager)
  {
    typeDropdown.getItems().addAll("Grøn", "Fælles", "Bytte");

    this.dataManager = dataManager;
    try
    {
      typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
      descriptionColumn.setCellValueFactory(
          new PropertyValueFactory<>("description"));
      pointColumn.setCellValueFactory(
          new PropertyValueFactory<>("pointsGained"));
      statusTable.setCellValueFactory(
          new PropertyValueFactory<>("isCompleted"));
      reservedTable.setCellValueFactory(
          new PropertyValueFactory<>("reservedBy"));

      taskList = new FilteredList<>(
          FXCollections.observableArrayList(dataManager.getAllTasks()),
          task -> true);
      taskTable.setItems(taskList);
    }
    catch (FileAccessException e)
    {
      Alert error = new Alert(Alert.AlertType.ERROR, e.getMessage());
      error.show();
    }
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }

  public void onStatusDropdown(ActionEvent actionEvent)
  {

  }

  public void onTypeDropdown()
  {
    String selected = typeDropdown.getValue().toString();
    taskList.setPredicate(task->task.getType().equals(selected));
  }

  public void onSearchByName(KeyEvent keyEvent)
  {
  }

  public void onAddButton(ActionEvent actionEvent)
  {
    ViewManager.showView("AddTask");
  }

  public void onCompleteButton(ActionEvent actionEvent)
  {
  }

  public void onEditButton(ActionEvent actionEvent)
  {
  }
}
