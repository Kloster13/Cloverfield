package cloverfield.domain;

import java.time.LocalDate;

public class Green extends Task
{

  public Green(String description, int pointsGained)
  {
    super(description, pointsGained);
    super.setType("Grøn");
  }

  @Override public void completeTask(Resident completedBy, Cloverfield cloverfield)
  {
    if (isCompleted())
    {
      throw new InvalidTaskException("Opgave allerede færdiggjort");
    }
    super.setCompletedDate(LocalDate.now());
    cloverfield.addPoints(super.getPointsGained());
    super.setCompletedBy(completedBy);
    super.setIsComplete(true);
  }

  @Override public Task copyTask()
  {
    return new Green(getDescription(),getPointsGained());
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