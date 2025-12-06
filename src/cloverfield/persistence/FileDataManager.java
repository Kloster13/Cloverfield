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
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.toFile())))
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
    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(path.toFile())))
    {
      return (DataContainer) input.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new FileAccessException("Error in loading data");
    }
  }

  private Resident getResidentByID(DataContainer dataContainer, int idToGet)
  {
    ArrayList<Resident> residents = dataContainer.getResidents();
    Resident residentToGet = null;
    for (Resident resident : residents)
    {
      if (resident.getId() == idToGet)
      {
        residentToGet = resident;
        break;
      }
    }
    if (residentToGet == null)
    {
      throw new InvalidTaskException("Can't find task in list");
    }
    return residentToGet;
  }

  private void addTaskToResident(DataContainer dataContainer, Task taskToReserve,
      int residentToReserve)
  {
    Resident resident = getResidentByID(dataContainer, residentToReserve);
    resident.addReserved(taskToReserve);
  }

  private void removeTaskFromResident(DataContainer dataContainer, int taskToRemove,
      int residentToReserve)
  {
    Resident resident = getResidentByID(dataContainer, residentToReserve);
    Task task = getTaskById(taskToRemove);
    if (task.getReservedBy() != null)
    {
      resident.removeReserved(task);
    }
    resident.removeReserved(task);
  }

  @Override public Resident getResidentById(
      int idToGet) //TODO hvis vi aldrig bruger denne version skal den fjernes
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

  private Task getTaskById(DataContainer dataContainer, int idToGet)
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
    if (taskToAdd.getReservedBy() != null)
    {
      addTaskToResident(dataContainer, taskToAdd, taskToAdd.getReservedBy().getId());
    }
    taskToAdd.setId(idToSet);
    tasks.add(taskToAdd);
    save(dataContainer);
  }

  @Override public void deleteTask(int idToDelete)
  {
    DataContainer dataContainer = load();
    ArrayList<Task> tasks = dataContainer.getTasks();
    Task task = getTaskById(idToDelete);
    removeTaskFromResident(dataContainer, idToDelete, task.getReservedBy().getId());
    tasks.remove(task);
    save(dataContainer);
  }

  @Override public ArrayList<Task> getAllTasks()
  {
    return load().getTasks();
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

  @Override public void completeTaskFromList(int taskId, int residentId)
  {
    //TODO historik oprydning
    DataContainer dataContainer = load();
    Cloverfield cloverfield = dataContainer.getCloverfield();
    Task taskToComplete = getTaskById(dataContainer, taskId);
    Resident residentToComplete = getResidentByID(dataContainer, residentId);

    if (taskToComplete instanceof Barter)
    {
      int createdById = ((Barter) taskToComplete).getCreatedBy().getId();
      Resident createdBy = getResidentByID(dataContainer, createdById);
      createdBy.reducePoints(taskToComplete.getPointsGained());
    }
    System.out.println(taskId);
    System.out.println(taskToComplete.getReservedBy().getId());
    removeTaskFromResident(dataContainer, taskId, taskToComplete.getReservedBy().getId());

    taskToComplete.completeTask(residentToComplete, cloverfield);

    save(dataContainer);
  }

  @Override public void reservedTask(int residentId, Task taskToReserve)
  {
    DataContainer dataContainer = load();
  }

  // Cloverfield

  @Override public Cloverfield loadCloverfield()
  {
    return load().getCloverfield();
  }
  // Resident

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
    Resident resident = getResidentByID(dataContainer, idToDelete);
    dataContainer.getResidents().remove(resident);
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