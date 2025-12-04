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

  @Override public void completeTask(Resident completedBy, double multiplier)
  {
    super.setCompletedDate(LocalDate.now());
    completedBy.addPoints(super.getPointsGained(),multiplier);
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
  }

  @Override public boolean equals(Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    return super.equals(o);
  }
}