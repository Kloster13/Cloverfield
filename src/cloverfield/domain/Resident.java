package cloverfield.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

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
    active = true;
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

  public void setName(String name)
  {
    this.name = name;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public boolean isActive()
  {
    return active;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public ArrayList<Task> getActiveTasks()
  {
    return activeTasks;
  }

  public void setActiveTasks(ArrayList<Task> activeTasks)
  {
    this.activeTasks = activeTasks;
  }

  public int getPersonalPoints()
  {
    return personalPoints;
  }

  public void setPersonalPoints(int personalPoints)
  {
    if (personalPoints < 0)
    {
      throw new InvalidTaskException("Points can´t be negative");
    }
    this.personalPoints = personalPoints;
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
    personalPoints += pointsToAdd;
  }

  public void reducePoints(int pointsToRemove)
  {
    setPersonalPoints(getPersonalPoints()-pointsToRemove);
  }

  // TODO Dette er for at listen i task ser pænt ud. Hvis toString skal bruges på en anden måde skal der tænkes en bedre løsning der
  @Override public String toString()
  {
    return name + " (point: " + personalPoints + ")";
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    Resident resident = (Resident) o;
    return personalPoints == resident.personalPoints && Objects.equals(name, resident.name)
        && Objects.equals(activeTasks, resident.activeTasks);
  }

  @Override public int hashCode()
  {
    return Objects.hash(name, activeTasks, personalPoints);
  }
}
