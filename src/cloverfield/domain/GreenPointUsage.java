package cloverfield.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class GreenPointUsage implements Serializable
{
  private String usageDescription;
  private int greenPoints;
  private LocalDate dateSpent;

  public GreenPointUsage(String usageDescription, int greenPoints)
  {
    if (usageDescription.isEmpty())
    {
      throw new InvalidPointUsage("Beskrivelse må ikke være tom");
    }
    this.usageDescription = usageDescription;
    if (greenPoints < 0)
    {
      throw new InvalidPointUsage("Point pris kan ikke være negativ!");
    }
    this.greenPoints = greenPoints;
    this.dateSpent = LocalDate.now();
  }

  public String getUsageDescription()
  {
    return usageDescription;
  }

  public void setUsageDescription(String usageDescription)
  {
    this.usageDescription = usageDescription;
  }

  public int getGreenPoints()
  {
    return greenPoints;
  }

  public void setGreenPoints(int greenPoints)
  {
    this.greenPoints = greenPoints;
  }

  public LocalDate getDateSpent()
  {
    return dateSpent;
  }

  public void setDateSpent(LocalDate dateSpent)
  {
    this.dateSpent = dateSpent;
  }
}
