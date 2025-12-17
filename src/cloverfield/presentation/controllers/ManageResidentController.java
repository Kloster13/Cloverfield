package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageResidentController implements AcceptsStringArgument
{
  public Label statusLabel;
  public Button addButton;
  public Button editButton;
  public Button backButton;
  public TableColumn<Resident, String> nameColumn;
  public TableColumn<Resident, Integer> pointColumn;
  public TableColumn<Resident, Integer> completedTasksColumn;
  public TableColumn<Resident, Boolean> activeColumn;
  public TableColumn<Resident, String> deleteColumn;
  public TableView<Resident> residentTable;

  @Override public void setArgument(String argument)
  {
    statusLabel.setText(argument);
  }

  public void init(DataManager dataManager)
  {

    ObservableList<Resident> residentList = FXCollections.observableArrayList(dataManager.getAllResidents());
    FilteredList<Resident> filteredList = new FilteredList<>(residentList, resident -> true);
    SortedList<Resident> sortedList = new SortedList<>(filteredList);
    sortedList.comparatorProperty().bind(residentTable.comparatorProperty());
    residentTable.setItems(sortedList);

    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    pointColumn.setCellValueFactory(new PropertyValueFactory<>("personalPoints"));
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

  public void onAddButton()
  {
    ViewManager.showView("AddResident");
  }

  public void onEditButton()
  {
    try
    {
      int selectedResident = residentTable.getSelectionModel().getSelectedItem().getId();
      ViewManager.showView("EditResident", String.valueOf(selectedResident));
    }
    catch (NullPointerException e)
    {
      statusLabel.setText("Vælg opgave for at fortsætte");
    }
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }
}
