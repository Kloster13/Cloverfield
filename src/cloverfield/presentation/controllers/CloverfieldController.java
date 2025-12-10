package cloverfield.presentation.controllers;

import cloverfield.domain.GreenPointUsage;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class CloverfieldController implements AcceptsStringArgument
{

  public Button useGreenPoints;
  public Label greenPointsDisplay;
  public TableView<GreenPointUsage> usedTable;
  private FilteredList<GreenPointUsage> usesList;
  public TableColumn<GreenPointUsage, String> descriptionColumn;
  public TableColumn<GreenPointUsage, Integer> pointsColumn;
  public TableColumn<GreenPointUsage, LocalDate> dateColumn;
  public Label statusLabel;

  private DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    greenPointsDisplay.setText(String.valueOf(dataManager.getCloverfield().getCollectiveGreenPoints()));
    usesList = new FilteredList<>(
        FXCollections.observableArrayList(dataManager.getAllUses()),
        uses -> true);
    usedTable.setItems(usesList);

    descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("usageDescription"));
    pointsColumn.setCellValueFactory(new PropertyValueFactory<>("greenPoints"));
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateSpent"));
  }
  @Override public void setArgument(String argument)
  {
    statusLabel.setText(argument);
  }

  public void onUseGreenPointsButton()
  {
    ViewManager.showView("UseGreenPoints");
  }
}
