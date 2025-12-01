package cloverfield.presentation.controllers;

import cloverfield.persistence.DataManager;

public class AddTaskController
{
  private DataManager dataManager;

  public void init(DataManager dataManager){
    this.dataManager=dataManager;
  }
}
