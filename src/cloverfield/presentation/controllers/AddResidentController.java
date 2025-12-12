package cloverfield.presentation.controllers;

import cloverfield.domain.InvalidResidentException;
import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.*;


public class AddResidentController
{
  public Spinner<Integer> pointInput;
  private DataManager dataManager;
  public TextField nameInput;
  public Button cancelButton;
  public Button addResidentButton;
  public Label statusLabel;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    addResidentButton.setDisable(true);

    pointInput.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999999, 0));
  }

  public void onNameInput()
  {
    addResidentButton.setDisable(false);
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageResident");
  }

  public void onAddResidentButton()
  {
    try
    {
      Resident residentToAdd = new Resident(nameInput.getText());
      residentToAdd.setPersonalPoints(pointInput.getValue());
      dataManager.addResident(residentToAdd);
      ViewManager.showView("ManageResident",nameInput.getText()+" blev tilføjet!");
    }
    catch (InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
