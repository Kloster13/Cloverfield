package cloverfield;

import cloverfield.presentation.core.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class RunCloverfield extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    ViewManager.init(primaryStage,"MainView");
    ViewManager.showView("Home");
  }
}