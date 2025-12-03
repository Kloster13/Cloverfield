package cloverfield.domain;

import java.time.LocalDate;

public class Collective extends Task
{
  public Collective(String description, int pointsGained)
  {
    super(description, pointsGained);
  }

  public Collective(String description, int pointsGained, Resident reservedBy)
  {
    super(description, pointsGained, reservedBy);
  }

  public void completeTask()
  {
    super.completeTask(null);
  }
}