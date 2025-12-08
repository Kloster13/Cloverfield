package cloverfield.presentation.core;

import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;
import cloverfield.presentation.controllers.*;

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
      case CompleteTaskController ctrl ->ctrl.init(getDataManager());
      case DeleteTaskController ctrl ->ctrl.init(getDataManager());
      case EditTaskController ctrl->ctrl.init(getDataManager());
      case ManageResidentController ctrl->ctrl.init(getDataManager());
      case AddResidentController ctrl->ctrl.init(getDataManager());
      case DeleteResidentController ctrl -> ctrl.init(getDataManager());
      case EditResidentController ctrl -> ctrl.init(getDataManager());
      case HomeController ctrl -> ctrl.init(getDataManager());
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
