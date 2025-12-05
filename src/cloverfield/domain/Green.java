package cloverfield.domain;

import java.time.LocalDate;

public class Green extends Task
{

  public Green(String description, int pointsGained)
  {
    super(description, pointsGained);
    super.setType("Grøn");
  }

  //TODO overvej om denne skal være her
  public Green(String description, int pointsGained, Resident reservedBy)
  {
    super(description, pointsGained, reservedBy);
    super.setType("Grøn");
  }

  @Override public void completeTask(Resident completedBy, Cloverfield cloverfield)
  {
    super.setCompletedDate(LocalDate.now());
    completedBy.addPoints(super.getPointsGained(),cloverfield.getActiveMultiplier());
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