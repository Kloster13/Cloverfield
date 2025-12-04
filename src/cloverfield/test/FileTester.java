package cloverfield.test;

import cloverfield.domain.*;
import cloverfield.persistence.DataContainer;
import cloverfield.persistence.FileAccessException;
import cloverfield.persistence.FileDataManager;

public class FileTester
{
  public static void main(String[] args)
  {
    Resident bob = new Resident("bob");
    Cloverfield cloverfield =new Cloverfield();
    FileDataManager dm = new FileDataManager();
    Green green = new Green("Mad lav for alle",60);
    Green green2=new Green("fej",10);
    Green editedGreen2 = new Green("fej",12,bob);
    editedGreen2.completeTask(bob,cloverfield);
    System.out.println(dm.getAllTasks());
    try
    {
      dm.addTask(green);
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      dm.addTask(green2);
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println(dm.getAllTasks());
    try
    {
      dm.editTask(2,editedGreen2);
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println("After edit");
    System.out.println(dm.getAllTasks());

    try
    {
      dm.deleteTask(2);
    }
    catch (FileAccessException | InvalidTaskException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println(dm.getAllTasks());
  }
}
