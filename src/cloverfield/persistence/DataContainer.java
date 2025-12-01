package cloverfield.persistence;

import cloverfield.domain.Cloverfield;
import cloverfield.domain.Resident;
import cloverfield.domain.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable
{
  private Cloverfield cloverfield;
  private List<Resident> residents;
  private List<Task> tasks;

  public DataContainer()
  {
    residents = new ArrayList<>();
    tasks = new ArrayList<>();
    cloverfield=new Cloverfield();
  }

  public List<Resident> getResidents()
  {
    return residents;
  }

  public List<Task> getTasks()
  {
    return tasks;
  }

  public Cloverfield getCloverfield()
  {
    return cloverfield;
  }

  public void setCloverfield(Cloverfield cloverfield)
  {
    this.cloverfield = cloverfield;
  }

  @Override public String toString()
  {
    return "DataContainer{" + "cloverfield=" + cloverfield + ", residents="
        + residents + ", tasks=" + tasks + '}';
  }
}
