package cloverfield.domain;

import java.time.LocalDate;

public class Collective extends Task
{
  public Collective(String description, int pointsGained)
  {
    super(description, pointsGained);
  }

  @Override public void completeTask(Resident completedBy, Cloverfield cloverfield)
  {
    super.setCompletedDate(LocalDate.now());
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
    cloverfield.addPoints(super.getPointsGained());
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