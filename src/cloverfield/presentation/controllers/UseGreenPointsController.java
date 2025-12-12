package cloverfield.presentation.controllers;

import cloverfield.domain.GreenPointUsage;
import cloverfield.domain.InvalidPointUsage;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import javax.swing.text.View;

public class UseGreenPointsController
{
  DataManager dataManager;
  public TextField descriptionDisplay;
  public Spinner<Integer> greenPointsDisplay;
  public Label statusLabel;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    greenPointsDisplay.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999, 0));
    System.out.println(greenPointsDisplay.getValue());
  }

  public void onCancelButton()
  {
    ViewManager.showView("Cloverfield");
  }

  public void usePointsButton()
  {
    try
    {
      String description = descriptionDisplay.getText();
      int pointsUsed = greenPointsDisplay.getValue();
      GreenPointUsage usage = new GreenPointUsage(description, pointsUsed);
      dataManager.useGreenPoints(usage);
      ViewManager.showView("Cloverfield", pointsUsed + " grønne point brugt!");
    }
    catch (InvalidPointUsage e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
