package cloverfield.domain;

import java.time.LocalDate;

public class Barter extends Task
{
  Resident createdBy;

  public Barter(String description, int pointsGained, Resident createdBy)
  {
    super(description, pointsGained);
    this.createdBy=createdBy;
  }

  @Override public void completeTask(Resident completedBy)
  {
    super.setCompletedDate(LocalDate.now());
    completedBy.addPoints(super.getPointsGained());
    createdBy.addPoints(super.getPointsGained()*-1);
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
  }
}