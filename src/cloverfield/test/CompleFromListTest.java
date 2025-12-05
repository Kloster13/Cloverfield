package cloverfield.test;

import cloverfield.domain.*;
import cloverfield.persistence.FileAccessException;
import cloverfield.persistence.FileDataManager;

import java.util.ArrayList;
import java.util.Arrays;

public class CompleFromListTest
{
  public static void main(String[] args)
  {
    FileDataManager dm = new FileDataManager();
    Cloverfield cloverfield = dm.load().getCloverfield();
    Resident bob = new Resident("Bob", 1, true);
    Resident don = new Resident("Don", 2, false);
    Green green = new Green("Feje", 10);
    Collective collective = new Collective("Også feje", 20);
    Barter barter = new Barter("fej for mig", 25, bob);

    bob.addPoints(30);
    try
    {
      dm.addTask(green);
      dm.addTask(collective);
      dm.addTask(barter);
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      ArrayList<Task> tasks = dm.getAllTasks();
      System.out.println(dm.getAllTasks());
      for (Task task : tasks)
      {
        System.out.println(bob);
        System.out.println(don);
        System.out.println("------------------");
        System.out.println(task);
        dm.completeTaskFromList(task.getId(),don.getId());
        System.out.println(task);
      }
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println(dm.getAllTasks());
  }
}
