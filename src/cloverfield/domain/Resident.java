package cloverfield.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Resident implements Serializable
{
  private String name;
  private int id;
  private boolean active;
  private ArrayList<Task> activeTasks;
  private int personalPoints;

  public Resident(String name)
  {
    this.name = name;
    activeTasks = new ArrayList<>();
  }
  public Resident(String name, int id, boolean active)
  {
    this.name = name;
    this.active = active;
    activeTasks = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public void addPoints(int pointsToAdd)
  {
    this.personalPoints += pointsToAdd;
  }

  public void addPoints(int pointsToAdd, double multiplier)
  {
    if (!active)
    {
      pointsToAdd = (int) (multiplier * pointsToAdd);
    }
    this.personalPoints += pointsToAdd;
  }

  public void reducePoints(int pointsToRemove)
  {
    if(personalPoints-pointsToRemove<0){
      throw new InvalidTaskException("Points can´t be negative");
    }
    this.personalPoints-=pointsToRemove;
  }

  @Override public String toString()
  {
    return name + "{ " + "active=" + active + ", activeTasks=" + activeTasks
        + ", personalPoints=" + personalPoints + '}';
  }
}
