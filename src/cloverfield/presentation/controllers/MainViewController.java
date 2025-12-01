package cloverfield.presentation.controllers;

import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainViewController
{
  public Button handleResident;
  public Button handlePoint;
  public Button greenPoints;

  public void onHandleResident(ActionEvent actionEvent)
  {
  }

  public void onHandlePoint(ActionEvent actionEvent)
  {
    ViewManager.showView("ManageTask");
  }

  public void onGreenPoints(ActionEvent actionEvent)
  {
  }
}
