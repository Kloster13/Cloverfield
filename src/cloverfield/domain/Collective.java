package cloverfield.domain;

import java.time.LocalDate;

public class Collective extends Task
{
  private Cloverfield cloverfield; //TODO Jeg lavet virkelig noget hacking for at kunne overholde forhold

  public Collective(String description, int pointsGained, Cloverfield cloverfield)
  {
    super(description, pointsGained);
    this.cloverfield=cloverfield;
  }

  @Override public void completeTask(Resident completedBy)
  {
    super.setCompletedDate(LocalDate.now());
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
    cloverfield.addPoints(super.getPointsGained());
  }
}