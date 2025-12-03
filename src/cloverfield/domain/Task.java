package cloverfield.domain;

import java.time.LocalDate;
import java.io.Serializable;

public class Task implements Serializable
{
  private String description;
  private int pointsGained;
  private Resident completedBy;
  private LocalDate completedDate;
  private int id; // skal id være final.... og gæder det også for point... description kan vel ændre sig/ ændres??
  private Resident reservedBy;

  public Task(String description, int pointsGained)
  {
    this.description = description;
    this.pointsGained = pointsGained;

  }

  public Task(String description, int pointsGained, Resident reservedBy)
  {
    this.description = description;
    this.pointsGained = pointsGained;
    this.reservedBy = reservedBy;
  }

  public void completeTask(Resident completedBy)
  {
    this.completedBy = completedBy;
    this.completedDate = LocalDate.now();
    // Er det med vilje at vi ikke har completedDate med i vores uml?? Og skal den have en set i stedet for at blive sat automatisk?
    // ps. Koden blive flagget med en error.... jeg kan ikke finde ud af at løse den.... kan du/i?
  }

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
}
