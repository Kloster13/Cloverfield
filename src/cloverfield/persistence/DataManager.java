package cloverfield.persistence;

import cloverfield.domain.Cloverfield;
import cloverfield.domain.Resident;
import cloverfield.domain.Task;

import java.util.ArrayList;

public interface DataManager
{
  // Task
  public abstract void addTask(Task task);
  public abstract void deleteTask(int idToDelete);
  public abstract ArrayList<Task> getAllTasks();
  public abstract Task getTaskById(int idToGet);
  public abstract void editTask(int id,Task editedTask);
  public abstract void completeTaskFromList(int taskId, int completedById);
  // Cloverfield
  public abstract Cloverfield loadCloverfield(); //TODO ikke sikker på at den skal bruges
  // Resident
  public abstract Resident getResidentById(int id);
  public abstract void addResident(Resident resident);
  public abstract void deleteResident(int idToDelete);
  public abstract ArrayList<Resident> getAllResidents();
  public abstract void editResident(int id, Resident editedResident);

}
