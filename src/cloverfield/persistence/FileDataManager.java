package cloverfield.persistence;

import cloverfield.domain.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class FileDataManager implements DataManager
{
  private final Path path = Paths.get("src", "cloverfield", "CloverfieldData.bin");

  public FileDataManager() throws FileAccessException
  {
    if (!Files.exists(path))
    {
      DataContainer dataContainer = new DataContainer();
      save(dataContainer);
    }
  }

  public void save(DataContainer dataContainer) throws FileAccessException
  {
    try (ObjectOutputStream output = new ObjectOutputStream(
        new FileOutputStream(path.toFile())))
    {
      output.writeObject(dataContainer);
    }
    catch (IOException e)
    {
      throw new FileAccessException("Error in saving data");
    }
  }

  public DataContainer load() throws FileAccessException
  {
    try (ObjectInputStream input = new ObjectInputStream(
        new FileInputStream(path.toFile())))
    {
      return (DataContainer) input.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new FileAccessException("Error in loading data");
    }
  }

  @Override public void addTask(Task taskToAdd)
  {
    DataContainer dataContainer = load();
    ArrayList<Task> tasks = dataContainer.getTasks();
    int idToSet = 1;
    for (Task task : tasks)
    {
      if (task.equals(taskToAdd))
      {
        throw new InvalidTaskException("Task already in list");
      }
      if (task.getId() >= idToSet)
      {
        idToSet = task.getId() + 1;
      }
    }
    taskToAdd.setId(idToSet);
    tasks.add(taskToAdd);
    save(dataContainer);
  }

  @Override public void deleteTask(int idToDelete)
  {
    DataContainer dataContainer = load();
    ArrayList<Task> tasks = dataContainer.getTasks();
    tasks.remove(getTaskById(idToDelete,dataContainer));
    save(dataContainer);
  }

  @Override public ArrayList<Task> getAllTasks()
  {
    return load().getTasks();
  }

  @Override public Task getTaskById(int idToGet, DataContainer dataContainer)
  {
    ArrayList<Task> tasks = dataContainer.getTasks();
    Task taskToGet = null;
    for (Task task : tasks)
    {
      if (task.getId() == idToGet)
      {
        taskToGet = task;
        break;
      }
    }
    if (taskToGet == null)
    {
      throw new InvalidTaskException("Can't find task in list");
    }
    return taskToGet;
  }

  @Override public void editTask(int idToEdit, Task editedTask)
  {
    deleteTask(idToEdit);
    DataContainer dataContainer = load();
    ArrayList<Task> tasks = dataContainer.getTasks();
    editedTask.setId(idToEdit);
    tasks.add(editedTask);
    save(dataContainer);
  }

  @Override public void completeTaskFromList(int id, Resident completedBy)
  {
    //TODO snak om vi skal en form for historik oprydning
    DataContainer dataContainer = load();
    Cloverfield cloverfield = dataContainer.getCloverfield();
    Task task = getTaskById(id,dataContainer);
    task.completeTask(completedBy, cloverfield);
    save(dataContainer);
  }
}
