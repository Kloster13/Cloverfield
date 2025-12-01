package cloverfield.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Resident implements Serializable
{
  private String name;
  private int id;
  private boolean active;
  private ArrayList<Task> activeTasks;

  public Resident(String name, int id, boolean active)
  {
    this.name = name;
    this.active = active;
    activeTasks=new ArrayList<>();
  }
}
