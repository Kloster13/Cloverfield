package cloverfield.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class GreenPointUsage implements Serializable
{
  private String usageDescription;
  private int greenPoints;
  private LocalDate dateSpent;

  public GreenPointUsage(String usageDescription, int greenPoints,
      LocalDate dateSpent)
  {
    this.usageDescription = usageDescription;
    this.greenPoints = greenPoints;
    dateSpent= LocalDate.now();
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
