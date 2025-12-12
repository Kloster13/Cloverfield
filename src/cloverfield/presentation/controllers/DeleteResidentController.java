package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class DeleteResidentController implements AcceptsStringArgument
{
  private DataManager dataManager;
  public Label nameDisplay;
  public Label pointsDisplay;
  private int residentId;
  private Resident resident;

  public void init(DataManager dataManager)
  {
    this.dataManager = dataManager;
  }

  @Override public void setArgument(String argument)
  {
    this.residentId = Integer.parseInt(argument);
    this.resident = dataManager.getResidentById(residentId);

    nameDisplay.setText(resident.getName());
    pointsDisplay.setText(String.valueOf(resident.getPersonalPoints()));
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageResident");
  }

  public void onConfirmButton()
  {
    dataManager.deleteResident(residentId);
    ViewManager.showView("ManageResident",
        resident.getName()+ " blev fjernet permanent");
  }
}
