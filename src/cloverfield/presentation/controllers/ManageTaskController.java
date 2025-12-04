package cloverfield.presentation.controllers;

import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;

public class ManageTaskController
{
  private DataManager dataManager;

  public void init(DataManager dataManager){
    this.dataManager=dataManager;
  }

  public void onBack()
  {
    ViewManager.showView("Home");
  }
}
