package cloverfield.presentation.controllers;

import cloverfield.persistence.DataManager;

public class HomeController
{
  public void init(DataManager dataManager)
  {
    dataManager.resetAllPersonalPoints();
  }
}
