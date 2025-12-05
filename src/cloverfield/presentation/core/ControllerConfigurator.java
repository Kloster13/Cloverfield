package cloverfield.presentation.core;

import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;
import cloverfield.presentation.controllers.AddResidentController;
import cloverfield.presentation.controllers.AddTaskController;
import cloverfield.presentation.controllers.ManageTaskController;
import cloverfield.presentation.controllers.TaskConfirmationController;

public class ControllerConfigurator
{
  public static void configure(Object controller)
  {
    if (controller == null)
      return;
    switch (controller)
    {
      case AddTaskController ctrl -> ctrl.init(getDataManager());
      case ManageTaskController ctrl -> ctrl.init(getDataManager());
      case TaskConfirmationController ctrl -> ctrl.init(getDataManager());
      default -> throw new RuntimeException(
          "Controller of type '" + controller.getClass().getSimpleName()
              + "' not valid.");
    }
  }

  public static DataManager getDataManager()
  {
    return  new FileDataManager();
  }
}
