package cloverfield.persistence;

import cloverfield.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    tasks.remove(getTaskById(idToDelete));
    save(dataContainer);
  }

  @Override public ArrayList<Task> getAllTasks()
  {
    return load().getTasks();
  }

  @Override public Task getTaskById(int idToGet)
  {
    DataContainer dataContainer = load();
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
    Task task = getTaskById(id);
    task.completeTask(completedBy, cloverfield);
    save(dataContainer);
  }

  // Cloverfield
  @Override public Cloverfield loadCloverfield()
  {
    return load().getCloverfield();
  }

  // Resident
  @Override public Resident getResidentById(int idToGet)
  {
    DataContainer dataContainer = load();
    ArrayList<Resident> residents = dataContainer.getResidents();
    Resident resident = null;
    for (Resident residentToGet : residents)
    {
      if (residentToGet.getId() == idToGet)
      {
        resident = residentToGet;
        break;
      }
    }
    if (resident == null)
    {
      throw new InvalidTaskException("Can't find task in list");
    }
    return resident;
  }


  @Override public void addResident(Resident residentToAdd)
  {
    DataContainer dataContainer = load();
    ArrayList<Resident> residents = dataContainer.getResidents();

    int idToSet = 1;
    for (Resident resident : residents)
    {
      if (resident.equals(residentToAdd))
      {
        throw new InvalidResidentException("Resident already in list");
      }
      if (resident.getId() >= idToSet)
      {
        idToSet = resident.getId() + 1;
      }
    }
    residentToAdd.setId(idToSet);
    residents.add(residentToAdd);
    save(dataContainer);
  }

  @Override public void deleteResident(int idToDelete)
  {
    DataContainer dataContainer = load();
    dataContainer.getResidents().remove(getResidentById(idToDelete));
    save(dataContainer);
  }

  @Override public ArrayList<Resident> getAllResidents()
  {
    return load().getResidents();
  }

  @Override public void editResident(int id, Resident editedResident)
  {

  }


}