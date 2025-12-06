package cloverfield.test;

import cloverfield.domain.Resident;
import cloverfield.domain.Task;
import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;

import java.util.ArrayList;

public class NewDeleteTest
{
  public static void main(String[] args)
  {
    DataManager dataManager = new FileDataManager();
    ArrayList<Resident> residents = dataManager.getAllResidents();

    ArrayList<Task> tasks = dataManager.getAllTasks();

    for(Resident resident: residents){
      System.out.println(resident.getName());
      System.out.println(resident.getActiveTasks());
      System.out.println("-------------------------------");
    }

    for(Task task: tasks){
      System.out.println(task);
      System.out.println("-------------------------------");
    }
  }
}
