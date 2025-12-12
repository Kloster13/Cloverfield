package cloverfield.presentation.controllers;

import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.Button;

public class MainViewController
{
  public Button handleResident;
  public Button handlePoint;
  public Button greenPoints;
  public Button showCatalogButton;

  public void onHandleResident()
  {
    ViewManager.showView("ManageResident");
  }

  public void onHandleTask()
  {
    ViewManager.showView("ManageTask");
  }

  public void onGreenPoints()
  {
    ViewManager.showView("Cloverfield");
  }

  public void onShowCatalogButton()
  {
    ViewManager.showView("OverviewOnScreen");
  }
}
