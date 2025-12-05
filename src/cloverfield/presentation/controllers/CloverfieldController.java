package cloverfield.presentation.controllers;
import cloverfield.persistence.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CloverfieldController
{
    @FXML private Button btnShowCollective;
    @FXML private Button btnUseGreenPoints;
    @FXML private Button btnUpdateStatus;

    private DataManager dataManager;

    @FXML private void initialize()
    {System.out.println("CloverfieldController initialized");}
    public void init(DataManager dataManager)
    {this.dataManager = dataManager; System.out.println("CloverfieldController setDataManager");}


    @FXML private void ShowCollectiveGreenPoints(ActionEvent actionEvent)
    {System.out.println("Clicked:ShowCollectiveGreenPoints");}

    @FXML private void UseGreenPoints(ActionEvent actionEvent)
    {System.out.println("Clicked:UseGreenPoints");}


    @FXML private  void UpdateActiveStatusOfResidents(ActionEvent actionEvent)
    {System.out.println("Clicked:UpdateStatus");}


}
