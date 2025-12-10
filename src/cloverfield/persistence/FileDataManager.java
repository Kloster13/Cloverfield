package cloverfield.persistence;

import cloverfield.domain.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class FileDataManager implements DataManager
{
  private final Path path = Paths.get("src", "cloverfield",
      "CloverfieldData.bin");

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

  private void addTaskToResident(DataContainer dataContainer,
      Task taskToReserve, int residentToReserve)
  {
    Resident resident = getResidentByID(dataContainer, residentToReserve);
    resident.addReserved(taskToReserve);
  }

  private void removeTaskFromResident(DataContainer dataContainer,
      int taskToRemove)
  {
    Task task = getTaskById(dataContainer, taskToRemove);
    if (task.getReservedBy() != null)
    {
      int id = task.getReservedBy().getId();
      Resident resident = getResidentByID(dataContainer, id);
      resident.removeReserved(task);
    }
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
      addTaskToResident(dataContainer, taskToAdd,
          taskToAdd.getReservedBy().getId());
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
    removeTaskFromResident(dataContainer, idToDelete);
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
    addTaskToResident(dataContainer, editedTask,
        editedTask.getReservedBy().getId());
    save(dataContainer);
  }

  @Override public void completeTaskFromList(int taskId, int residentId)
  {
    //TODO historik oprydning - lav prio
    DataContainer dataContainer = load();
    Cloverfield cloverfield = dataContainer.getCloverfield();
    Task taskToComplete = getTaskById(dataContainer, taskId);
    Resident residentToComplete = getResidentByID(dataContainer, residentId);

    Task taskCopy = null;
    if (taskToComplete instanceof Collective || taskToComplete instanceof Green)
    {
      taskCopy = taskToComplete.copy();
      taskCopy.setReservedBy(null);
      addTask(taskCopy);
    }

    if (taskToComplete instanceof Barter)
    {
      int createdById = ((Barter) taskToComplete).getCreatedBy().getId();
      Resident createdBy = getResidentByID(dataContainer, createdById);
      createdBy.reducePoints(taskToComplete.getPointsGained());
    }
    removeTaskFromResident(dataContainer, taskId);
    taskToComplete.completeTask(residentToComplete, cloverfield);

    save(dataContainer);

    if (taskCopy != null)
    {
      addTask(taskCopy);
      System.out.println(taskCopy);
    }
  }

  @Override public void reservedTask(int residentId, Task taskToReserve)
  {
    DataContainer dataContainer = load();
  }

  @Override public ArrayList<GreenPointUsage> getAllUses()
  {
    return (ArrayList<GreenPointUsage>) load().getCloverfield()
        .getHistoricUses();
  }

  // Cloverfield
  @Override public Cloverfield getCloverfield()
  {
    return load().getCloverfield();
  }

  @Override public void useGreenPoints(GreenPointUsage usage)
  {
    DataContainer dataContainer = load();
    dataContainer.getCloverfield().addHistoric(usage);
    save(dataContainer);
  }

  @Override public void resetAllPersonalPoints()
  {
    DataContainer dataContainer = load();
    Cloverfield cloverfield = dataContainer.getCloverfield();
    if (!cloverfield.getLastCheck().equals(LocalDate.now())
        && LocalDate.now().getDayOfMonth() == 1)
    {
      ArrayList<Resident> residents = dataContainer.getResidents();
      int pointsToAddCloverfield = 0;
      for (Resident resident : residents)
      {
        pointsToAddCloverfield += resident.getPersonalPoints();
        resident.setPersonalPoints(0);
      }
      cloverfield.addPoints(pointsToAddCloverfield);
    }
    cloverfield.setLastCheck(LocalDate.now());
    save(dataContainer);
  }

  @Override public void setActiveStatus()
  {
    DataContainer dataContainer = load();
    ArrayList<Resident> residents = dataContainer.getResidents();
    residents.sort(Comparator.comparingInt(Resident::getPersonalPoints));
    for (int i = 0; i < residents.size(); i++)
    {
      if ((i < 3) || (residents.get(i).getPersonalPoints() == 0))
      {
        residents.get(i).setActive(false);
      }
      else
      {
        residents.get(i).setActive(true);
      }
    }
    save(dataContainer);
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

  @Override public void editResident(int idToEdit, Resident editedResident)
  {
    deleteResident(idToEdit);
    DataContainer dataContainer = load();
    ArrayList<Resident> residents = dataContainer.getResidents();
    editedResident.setId(idToEdit);
    residents.add(editedResident);
    save(dataContainer);
  }

  @Override public void updateActiveStatusOfResidents()
  {

  }
}