package cloverfield.test;

import cloverfield.domain.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskDomainTester
{
  public static void main(String[] args)
  {
    Cloverfield cloverfield = new Cloverfield();
    Resident bob = new Resident("Bob");
    Resident don = new Resident("Don");
    Green green = new Green("Feje", 10);
    Collective collective = new Collective("Også feje", 20);
    Barter barter = new Barter("fej for mig", 25, bob);

    bob.addPoints(30);
    ArrayList<Task> tasks = new ArrayList<>(
        Arrays.asList(green, collective, barter));

    for (Task task : tasks)
    {
      System.out.println(bob);
      System.out.println(don);
      System.out.println("------------------");
      System.out.println(task);
      task.completeTask(don,cloverfield);
    }
    System.out.println(bob);
    System.out.println(don);
  }
}
