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
  public TextField descriptionInput;
  public Button cancelButton;
  public Label statusLabel;
  public Spinner<Integer> greenPointsInput;
  DataManager dataManager;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    greenPointsInput.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99999, 0));
    System.out.println(greenPointsInput.getValue());
  }

  public void onCancelButton()
  {
    ViewManager.showView("Cloverfield");
  }

  public void usePointsButton()
  {
    try
    {
      String description = descriptionInput.getText();
      int pointsUsed = greenPointsInput.getValue();
      GreenPointUsage usage = new GreenPointUsage(description, pointsUsed);
      dataManager.useGreenPoints(usage);
      ViewManager.showView("Cloverfield",pointsUsed+" grønne point brugt!");
    }
    catch (InvalidPointUsage e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
