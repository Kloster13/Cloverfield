package cloverfield.presentation.controllers;

import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.presentation.core.AcceptsStringArgument;
import cloverfield.presentation.core.ViewManager;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EditResidentController implements AcceptsStringArgument
{
  DataManager dataManager;
  public TextField nameInput;
  public Spinner<Integer> pointInput;
  public ComboBox<String> statusInput;
  private int residentId;
  private Resident residentToEdit;

  public void init(DataManager dataManager)
  {
    this.dataManager=dataManager;
  }

  @Override public void setArgument(String argument)
  {
    this.residentId=Integer.parseInt(argument);
    this.residentToEdit=dataManager.getResidentById(residentId);
    nameInput.setText(residentToEdit.getName());
    pointInput.setValueFactory(
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999, residentToEdit.getPersonalPoints()));
  }

  public void onNameInput()
  {
  }

  public void onStatusDropdown()
  {
  }

  public void onCancelButton()
  {
    ViewManager.showView("ManageResident");
  }

  public void updateButton()
  {

  }
}
