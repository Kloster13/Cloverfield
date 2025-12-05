package cloverfield.persistence;

import cloverfield.domain.Cloverfield;
import cloverfield.domain.Resident;
import cloverfield.domain.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class DataContainer implements Serializable
{
  private Cloverfield cloverfield;
  private ArrayList<Resident> residents;
  private ArrayList<Task> tasks;

  public DataContainer()
  {
    residents = new ArrayList<>();
    tasks = new ArrayList<>();
    cloverfield=new Cloverfield();
  }

  public ArrayList<Resident> getResidents()
  {
    return residents;
  }

  public ArrayList<Task> getTasks()
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
