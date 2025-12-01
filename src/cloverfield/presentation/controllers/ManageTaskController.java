package cloverfield.presentation.controllers;

import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;

public class ManageTaskController
{
  private DataManager dataManager;

  public void init(DataManager dataManager){
    this.dataManager=dataManager;
  }
}
