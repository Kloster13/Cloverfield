package cloverfield.presentation.controllers;

import cloverfield.domain.InvalidResidentException;
import cloverfield.domain.InvalidTaskException;
import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.scene.control.*;

public class EditResidentController implements AcceptsStringArgument
{
  public Label statusLabel;
  DataManager dataManager;
  public TextField nameInput;
  public Spinner<Integer> pointInput;
  public ComboBox<String> statusInput;
  private int residentId;
  private Resident residentToEdit;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
    statusInput.getItems().addAll("Aktiv", "Ikke aktiv");
  }

  @Override public void setArgument(String argument)
  {
    this.residentId = Integer.parseInt(argument);
    this.residentToEdit = dataManager.getResidentById(residentId);
    nameInput.setText(residentToEdit.getName());
    pointInput.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(-999, 999,
            residentToEdit.getPersonalPoints()));
    if (residentToEdit.isActive())
    {
      statusInput.setValue("Aktiv");
    }
    else
    {
      statusInput.setValue("Ikke aktiv");
    }
  }

  public void onNameInput()
  {
    try
    {
      residentToEdit.setName(nameInput.getText());
    }
    catch (InvalidResidentException e)
    {
      statusLabel.setText(e.getMessage());
    }
  }

  public void onStatusDropdown()
  {
    System.out.println(statusInput.getValue());
    if (statusInput.getValue().equals("Aktiv"))
    {
      residentToEdit.setActive(true);
    }
    else if (statusInput.getValue().equals("Ikke aktiv"))
    {
      residentToEdit.setActive(false);
    }
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageResident");
  }

  public void updateButton()
  {
    try
    {
      residentToEdit.setPersonalPoints(pointInput.getValue());
      dataManager.editResident(residentId, residentToEdit);
      ViewManager.showView("ManageResident",
          residentToEdit.getName() + " blev opdateret");
    }
    catch (InvalidResidentException  e)
    {
      statusLabel.setText(e.getMessage());
    }
  }
}
