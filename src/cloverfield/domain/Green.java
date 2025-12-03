package cloverfield.domain;

import java.time.LocalDate;

public class Green extends Task
{

  public Green(String description, int pointsGained)
  {
    super(description, pointsGained);
  }

  public Green(String description, int pointsGained, Resident reservedBy)
  {
    super(description, pointsGained, reservedBy);
  }

  @Override public void completeTask(Resident completedBy)
  {
    super.setCompletedDate(LocalDate.now());
    completedBy.addPoints(super.getPointsGained());
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
  }
}