package cloverfield.domain;

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

  public void completeTask()
  {
    super.completeTask(null); // er det her rigtigt?
  }
}
// Skal der være en reservedBy... det skal der nok hvis den skal tildeles. Men det er måske en fejl fra min side...