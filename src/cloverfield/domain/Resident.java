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
    if (name.isEmpty())
    {
      throw new InvalidResidentException("Navn må ikke være blankt");
    }
    this.name = name;
    activeTasks = new ArrayList<>();
    active = true;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    if (name.isEmpty())
    {
      throw new InvalidResidentException("Navn må ikke være blankt");
    }
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

  public int getPersonalPoints()
  {
    return personalPoints;
  }

  public void setPersonalPoints(int personalPoints)
  {
    if (personalPoints < 0)
    {
      throw new InvalidResidentException("Personlige point må ikke være negative");
    }
    this.personalPoints = personalPoints;
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
    setPersonalPoints(getPersonalPoints() - pointsToRemove);
  }

  public void addReserved(Task task)
  {
    if (activeTasks.size() > 2)
    {
      throw new InvalidResidentException("Beboer kan maks have 3 reserverede opgaver");
    }
    activeTasks.add(task);
  }

  public void removeReserved(Task task)
  {
    activeTasks.remove(task);
  }

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
}
