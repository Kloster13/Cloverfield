package cloverfield.domain;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

public abstract class Task implements Serializable
{
  private String type;
  private String description;
  private int pointsGained;
  private boolean isCompleted;
  private Resident completedBy;
  private LocalDate completedDate;
  private Resident reservedBy;
  private int id;

  public Task(String description, int pointsGained)
  {
    this.description = description;
    this.pointsGained = pointsGained;
  }

  public Task(String description, int pointsGained, Resident reservedBy)
  {
    if (description.isEmpty())
    {
      throw new InvalidTaskException("Must include description");
    }
    this.description = description;
    if (pointsGained < 0)
    {
      throw new InvalidTaskException("Points can't be negative");
    }
    this.pointsGained = pointsGained;
    this.reservedBy = reservedBy;
  }

  public abstract void completeTask(Resident completedBy, Cloverfield cloverfield);

  public String getDescription()
  {
    return description;
  }

  public int getPointsGained()
  {
    return pointsGained;
  }

  public Resident getCompletedBy()
  {
    return completedBy;
  }

  public LocalDate getCompletedDate()
  {
    return completedDate;
  }

  public int getId()
  {
    return id;
  }

  public Resident getReservedBy()
  {
    return reservedBy;
  }

  public void setReservedBy(Resident reservedBy)
  {
    this.reservedBy = reservedBy;
  }

  public String getType()
  {
    return type;
  }

  public boolean getIsCompleted()
  {
    return isCompleted;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setPointsGained(int pointsGained)
  {
    this.pointsGained = pointsGained;
  }

  public void setCompletedBy(Resident completedBy)
  {
    this.completedBy = completedBy;
  }

  public void setCompletedDate(LocalDate completedDate)
  {
    this.completedDate = completedDate;
  }

  protected void setType(String type)
  {
    this.type = type;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setIsComplete(boolean completed)
  {
    isCompleted = completed;
  }

  @Override public String toString()
  {
    return "Task{" + "ID=" + id + ", description='" + description + '\'' + ", pointsGained="
        + pointsGained + ", isCompleted=" + isCompleted + ", completedBy=" + completedBy
        + ", completedDate=" + completedDate + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (o == null || getClass() != o.getClass())
      return false;
    Task task = (Task) o;
    return pointsGained == task.pointsGained && isCompleted == task.isCompleted && Objects.equals(
        description, task.description) && Objects.equals(completedBy, task.completedBy)
        && Objects.equals(completedDate, task.completedDate);
  }

  @Override public int hashCode()
  {
    return Objects.hash(description, pointsGained, isCompleted, completedBy, completedDate);
  }
}
