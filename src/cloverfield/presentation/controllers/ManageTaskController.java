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
  public Button historyToggleButton;
  private DataManager dataManager;
  private FilteredList<Task> taskList;
  private String typeFilter;
  private boolean completedFilter;
  private boolean showHistoryView;

  public void init(DataManager dataManager)
  {
    typeDropdown.getItems().addAll("Grøn", "Fælles", "Bytte");
    this.showHistoryView = false;
    this.completedFilter=false;
    this.dataManager = dataManager;

    taskList = new FilteredList<>(FXCollections.observableArrayList(dataManager.getAllTasks()),
        task -> !task.getIsCompleted());
    taskTable.setItems(taskList);

    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    pointColumn.setCellValueFactory(new PropertyValueFactory<>("pointsGained"));
    statusTable.setCellValueFactory(new PropertyValueFactory<>("isCompleted"));
    reservedTable.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
    deleteColumn.setCellFactory(col -> {
      return new TableCell<Task, String>()
      {
        private final Button deleteButton = new Button("Slet");

        {
          deleteButton.setOnAction(event -> {
            int rowIndex = getIndex();
            if (rowIndex < 0 || rowIndex >= getTableView().getItems().size())
              return;

            int idToDelete = getTableView().getItems().get(getIndex()).getId();
            ViewManager.showView("DeleteTask", String.valueOf(idToDelete));
          });
        }

        @Override protected void updateItem(String item, boolean empty)
        {
          super.updateItem(item, empty);
          setGraphic(empty ? null : deleteButton);
          setText(null);
        }
      };
    });
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }

  public void onTypeDropdown()
  {
    typeFilter = typeDropdown.getValue();
    taskList.setPredicate(
        task -> task.getType().equals(typeFilter) && task.getIsCompleted() == completedFilter);
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
    int selectedTask = taskTable.getSelectionModel().getSelectedItem().getId();
    System.out.println(selectedTask);
    ViewManager.showView("EditTask", String.valueOf(selectedTask));
  }

  public void onHistoryToggleBottom()
  {
    showHistoryView = !showHistoryView;

    if (showHistoryView)
    {
      completedFilter=true;
      taskList.setPredicate(
          task -> (typeFilter==null||task.getType().equals(typeFilter)) && task.getIsCompleted());
      historyToggleButton.setText("Se aktive opgaver");
      reservedTable.setText("Færdiggjort af");
      reservedTable.setCellValueFactory(new PropertyValueFactory<>("completedBy"));
    }
    else
    {
      completedFilter=false;
      historyToggleButton.setText("Se historik");
      reservedTable.setText("Reserveret af");
      reservedTable.setCellValueFactory(new PropertyValueFactory<>("reservedBy"));
      taskList.setPredicate(
          task -> (typeFilter==null||task.getType().equals(typeFilter)) && !task.getIsCompleted());
    }


  }
}
