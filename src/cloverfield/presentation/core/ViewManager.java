  package cloverfield.presentation.core;

  import javafx.fxml.FXMLLoader;
  import javafx.scene.Parent;
  import javafx.scene.Scene;
  import javafx.scene.control.Alert;
  import javafx.scene.layout.BorderPane;
  import javafx.stage.Stage;

  import java.io.IOException;
  import java.util.Objects;

  public class ViewManager
  {
    private static BorderPane mainLayout;
    private static String fxmlDirectoryPath = "/fxml/";

    public static void init(Stage primaryStage, String initialView) throws IOException
    {
      BorderPane root = FXMLLoader.load(Objects.requireNonNull(
          ViewManager.class.getResource(fxmlDirectoryPath + initialView + ".fxml")));
      mainLayout = root;
      Scene scene = new Scene(root, 800, 600);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Kløverly");
      primaryStage.show();
    }

    public static void showView(String viewName)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
        Parent root = loader.load();
        Object controller = loader.getController();
        ControllerConfigurator.configure(controller);
        mainLayout.setCenter(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        Alert error = new Alert(Alert.AlertType.ERROR, "cannot find view" + viewName);
        error.show();
        throw new RuntimeException(e);
      }
    }

    public static void showView(String viewName, String argument)
    {

      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
        Parent root = loader.load();
        AcceptsStringArgument controller = loader.getController();
        ControllerConfigurator.configure(controller);
        controller.setArgument(argument);
        mainLayout.setCenter(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        Alert error = new Alert(Alert.AlertType.ERROR, "cannot find view" + viewName);
        error.show();
        throw new RuntimeException(e);
      }
    }

    public static void showView(String viewName, Object argument)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
        Parent root = loader.load();
        AcceptsObjectArgument controller = loader.getController();
        ControllerConfigurator.configure(controller);
        controller.setArgument(argument);
        mainLayout.setCenter(root);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        Alert error = new Alert(Alert.AlertType.ERROR, "cannot find view" + viewName);
        error.show();
        throw new RuntimeException(e);
      }
    }
  }
