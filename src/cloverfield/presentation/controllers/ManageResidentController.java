package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.View;

public class ManageResidentController implements AcceptsStringArgument
{
  public Label statusLabel;
  private DataManager dataManager;
  public Button addButton;
  public Button editButton;
  public Button backButton;
  public Button detailsButton;
  public TableColumn<Resident, Integer> idColumn;
  public TableColumn<Resident, String> nameColumn;
  public TableColumn<Resident, Integer> pointColumn;
  public TableColumn<Resident, Integer> completedTasksColumn;
  public TableColumn<Resident, Boolean> activeColumn;
  public TableColumn<Resident, String> deleteColumn;
  public TableView<Resident> residentTable;
  private FilteredList<Resident> residentList;

  @Override public void setArgument(String argument)
  {
    statusLabel.setText(argument);
  }

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    residentList = new FilteredList<>(
        FXCollections.observableArrayList(dataManager.getAllResidents()),
        resident -> true);
    residentTable.setItems(residentList);

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    pointColumn.setCellValueFactory(new PropertyValueFactory<>("personalPoints"));
   // completedTasksColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    activeColumn.setCellValueFactory(new PropertyValueFactory<>("active"));
    deleteColumn.setCellFactory(col -> {
      return new TableCell<Resident, String>()
      {
        private final Button deleteButton = new Button("Slet");

        {
          deleteButton.setOnAction(event -> {
            int rowIndex = getIndex();
            if (rowIndex < 0 || rowIndex >= getTableView().getItems().size())
              return;

            int idToDelete = getTableView().getItems().get(getIndex()).getId();
            ViewManager.showView("DeleteResident", String.valueOf(idToDelete));
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

  public void onAddButton(ActionEvent actionEvent)
  {
    ViewManager.showView("AddResident");
  }

  public void onEditButton(ActionEvent actionEvent)
  {
  }

  public void onBack(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }

  public void onDetailsButton(ActionEvent actionEvent)
  {
  }

}
