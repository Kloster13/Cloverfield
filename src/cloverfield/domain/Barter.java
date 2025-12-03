package cloverfield.domain;

public class Barter extends Task
{
  Resident createdBy;

  public Barter(String description, int pointsGained)
  {
    super(description, pointsGained);
  }

  public Barter(String description, int pointsGained, Resident reservedBy)
  {
    super(description, pointsGained, reservedBy);
  }

  public void completeTask()
  {
    super.completeTask(null);
  }
}
// jeg er usikker på den sidste ændring her.