package cloverfield.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Barter extends Task
{
  Resident createdBy;

  public Barter(String description, int pointsGained, Resident createdBy)
  {
    super(description, pointsGained);
    this.createdBy=createdBy;
  }

  @Override public void completeTask(Resident completedBy, Cloverfield cloverfield)
  {
    super.setCompletedDate(LocalDate.now());
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
    completedBy.addPoints(super.getPointsGained(),cloverfield.getActiveMultiplier());
    createdBy.reducePoints(super.getPointsGained());
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    Barter barter = (Barter) o;
    return Objects.equals(createdBy, barter.createdBy);
  }

  @Override public int hashCode()
  {
    return Objects.hash(super.hashCode(), createdBy);
  }
}