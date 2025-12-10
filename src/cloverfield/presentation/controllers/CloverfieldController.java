package cloverfield.presentation.controllers;

import cloverfield.persistence.DataManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CloverfieldController
{

  public Button showGreenPoints;
  public Button useGreenPoints;
  public Button historicUses;

  private DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  public void onShowCollectiveGrenPointsButton(ActionEvent actionEvent)
  {
  }

  public void onUseGreenPointsButton(ActionEvent actionEvent)
  {
  }

  public void onShowHistoricUsesButton(ActionEvent actionEvent)
  {

  }
}
