package cloverfield.persistence;

import cloverfield.domain.Resident;
import cloverfield.domain.Task;

import java.util.ArrayList;

public interface DataManager
{
  public abstract void addTask(Task task);
  public abstract void deleteTask(int idToDelete);
  public abstract ArrayList<Task> getAllTasks();
  public abstract Task getTaskById(int idToGet);
  public abstract void editTask(int id,Task editedTask);
  public abstract void completeTaskFromList(int id, Resident completedBy);
}
