package cloverfield.domain;

import java.time.LocalDate;

public class Collective extends Task
{
  private Cloverfield cloverfield; //TODO Jeg lavet virkelig noget hacking for at kunne overholde forhold

  public Collective(String description, int pointsGained,
      Cloverfield cloverfield)
  {
    super(description, pointsGained);
    this.cloverfield = cloverfield;
  }

  @Override public void completeTask(Resident completedBy, double multiplier)
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