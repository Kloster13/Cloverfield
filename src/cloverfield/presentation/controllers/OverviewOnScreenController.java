package cloverfield.presentation.controllers;

import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OverviewOnScreenController
{
  private FilteredList<Task> taskList;
  public TableView<Task> taskTable;
  public TableColumn<Task, String> typeColumn;
  public TableColumn<Task, String> descriptionColumn;
  public TableColumn<Task, Integer> pointColumn;
  public ComboBox<String> typeDropdown;

  public void init(DataManager dataManager)
  {
    typeDropdown.getItems().addAll("Grøn", "Fælles", "Bytte");

    taskList = new FilteredList<>(FXCollections.observableArrayList(dataManager.getAllTasks()),
        task -> !task.getIsCompleted());
    taskTable.setItems(taskList);
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    pointColumn.setCellValueFactory(new PropertyValueFactory<>("pointsGained"));
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }

  public void onTypeDropdown()
  {
    String typeFilter = typeDropdown.getValue();
    taskList.setPredicate(task -> task.getType().equals(typeFilter) && !task.getIsCompleted());
  }
}
