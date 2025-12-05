package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
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
  public TableColumn<Task, String> typeColumn;
  public TableColumn<Task, String> descriptionColumn;
  public TableColumn<Task, Integer> pointColumn;
  public TableColumn<Task, String> statusTable;
  public TableColumn<Task, Resident> reservedTable;
  public TableColumn<Task, String> deleteColumn;
  public TableView<Task> table;
  public TableView<Task> taskTable;
  public ComboBox<String> typeDropdown;
  public ComboBox<Boolean> statusDropdown;
  private DataManager dataManager;
  private FilteredList<Task> taskList;
  private String typeFilter;
  private boolean completedFilter;

  public void init(DataManager dataManager)
  {
    typeDropdown.getItems().addAll("Grøn", "Fælles", "Bytte");
    statusDropdown.getItems().addAll(true, false);

    this.dataManager = dataManager;

    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    pointColumn.setCellValueFactory(new PropertyValueFactory<>("pointsGained"));
    statusTable.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));
    reservedTable.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));

    taskList = new FilteredList<>(FXCollections.observableArrayList(dataManager.getAllTasks()),
        task -> !task.getIsCompleted());
    taskTable.setItems(taskList);
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }

  public void onStatusDropdown()
  {
    completedFilter = statusDropdown.getValue();
    if (typeFilter == null)
    {
      taskList.setPredicate(task -> task.getIsCompleted() == completedFilter);
    }
    else
    {
      taskList.setPredicate(
          task -> task.getType().equals(typeFilter) && task.getIsCompleted() == completedFilter);
    }
  }

  public void onTypeDropdown()
  {
    typeFilter = typeDropdown.getValue();
    taskList.setPredicate(
        task -> task.getType().equals(typeFilter) && task.getIsCompleted() == completedFilter);
  }

  public void onSearchByName()
  {
  }

  public void onAddButton()
  {
    ViewManager.showView("AddTask");
  }

  public void onCompleteButton()
  {
    int selectedTask = taskTable.getSelectionModel().getSelectedItem().getId();
    ViewManager.showView("CompleteTask", String.valueOf(selectedTask));
  }

  public void onEditButton()
  {

  }
}
