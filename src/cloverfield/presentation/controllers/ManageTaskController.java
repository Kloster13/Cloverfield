package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageTaskController implements AcceptsStringArgument
{
  private FilteredList<Task> taskList;
  public TableView<Task> taskTable;
  public TableColumn<Task, String> typeColumn;
  public TableColumn<Task, String> descriptionColumn;
  public TableColumn<Task, Integer> pointColumn;
  public TableColumn<Task, String> statusTable;
  public TableColumn<Task, Resident> reservedTable;
  public TableColumn<Task, String> deleteColumn;
  public ComboBox<String> typeDropdown;
  public Button historyToggleButton;
  public Label statusLabel;
  private String typeFilter;
  private boolean completedFilter;
  private boolean showHistoryView;

  public void init(DataManager dataManager)
  {
    typeDropdown.getItems().addAll("Grøn", "Fælles", "Bytte");
    this.showHistoryView = false;
    this.completedFilter = false;

    ObservableList<Task> baseList = FXCollections.observableArrayList(dataManager.getAllTasks());
    taskList = new FilteredList<>(baseList, task -> !task.getIsCompleted());

    SortedList<Task> sortedList = new SortedList<>(taskList);
    sortedList.comparatorProperty().bind(taskTable.comparatorProperty());
    taskTable.setItems(sortedList);


    typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    descriptionColumn.setCellValueFactory(
        new PropertyValueFactory<>("description"));
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

  @Override public void setArgument(String argument)
  {
    statusLabel.setText(argument);
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }

  public void onTypeDropdown()
  {
    typeFilter = typeDropdown.getValue();
    updatePredicate();
  }

  public void onAddButton()
  {
    ViewManager.showView("AddTask");
  }

  public void onCompleteButton()
  {
    try
    {
      int selectedTask = taskTable.getSelectionModel().getSelectedItem()
          .getId();
      ViewManager.showView("CompleteTask", String.valueOf(selectedTask));
    }
    catch (NullPointerException e)
    {
      statusLabel.setText("Vælg opgave for at fortsætte");
    }
  }

  public void onEditButton()
  {
    try
    {
      int selectedTask = taskTable.getSelectionModel().getSelectedItem()
          .getId();
      ViewManager.showView("EditTask", String.valueOf(selectedTask));
    }
    catch (NullPointerException e)
    {
      statusLabel.setText("Vælg opgave for at fortsætte");
    }
  }

  public void onHistoryToggleBottom()
  {
    showHistoryView = !showHistoryView;

    if (showHistoryView)
    {
      completedFilter = true;
      taskList.setPredicate(
          task -> (typeFilter == null || task.getType().equals(typeFilter))
              && task.getIsCompleted());
      historyToggleButton.setText("Se aktive opgaver");
      reservedTable.setText("Færdiggjort af");
      reservedTable.setCellValueFactory(
          new PropertyValueFactory<>("completedBy"));
    }
    else
    {
      completedFilter = false;
      historyToggleButton.setText("Se historik");
      reservedTable.setText("Reserveret af");
      reservedTable.setCellValueFactory(
          new PropertyValueFactory<>("reservedBy"));
      taskList.setPredicate(
          task -> (typeFilter == null || task.getType().equals(typeFilter))
              && !task.getIsCompleted());
    }
    updatePredicate();
  }

  private void updatePredicate()
  {
    taskList.setPredicate(task ->
        ((typeFilter == null) || task.getType().equals(typeFilter)) &&
            (completedFilter == task.getIsCompleted())
    );
  }
}
