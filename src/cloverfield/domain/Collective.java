package cloverfield.domain;

import java.time.LocalDate;

public class Collective extends Task
{
  public Collective(String description, int pointsGained)
  {
    super(description, pointsGained);
    super.setType("Fælles");
  }

  @Override public void completeTask(Resident completedBy, Cloverfield cloverfield)
  {
    super.setCompletedDate(LocalDate.now());
    super.setCompletedBy(completedBy);
    completedBy.addPoints(super.getPointsGained(),cloverfield.getActiveMultiplier());
    super.setIsComplete(true);
  }

  @Override public Task copyTask()
  {
    return new Collective(getDescription(),getPointsGained());
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