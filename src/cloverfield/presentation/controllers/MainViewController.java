package cloverfield.presentation.controllers;

import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javax.swing.text.View;

public class MainViewController
{
  public Button handleResident;
  public Button handlePoint;
  public Button greenPoints;
  public Button showCatalogButton;

  public void onHandleResident(ActionEvent actionEvent)
  {
    ViewManager.showView("ManageResident");
  }

  public void onHandleTask(ActionEvent actionEvent)
  {
    ViewManager.showView("ManageTask");
  }

  public void onGreenPoints(ActionEvent actionEvent)
  {
    ViewManager.showView("Cloverfield");
  }

  public void onShowCatalogButton()
  {
    ViewManager.showView("OverviewOnScreen");
  }
}
