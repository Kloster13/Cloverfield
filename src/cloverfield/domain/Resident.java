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

  public Resident(String name, int id, boolean active)
  {
    this.name = name;
    this.active = active;
    activeTasks = new ArrayList<>();
  }

  public void addPoints(int pointsToAdd)
  {
    if (!active)
    {
      pointsToAdd = (int) (1.2*pointsToAdd);
    }
    this.personalPoints += pointsToAdd;
  }

  @Override public String toString()
  {
    return name+"{ "+ "active="
        + active + ", activeTasks=" + activeTasks + ", personalPoints="
        + personalPoints + '}';
  }
}
