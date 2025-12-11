package cloverfield.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cloverfield implements Serializable
{
  private int collectiveGreenPoints;
  private ArrayList<GreenPointUsage> historicUses;
  private double activeMultiplier;
  private LocalDate lastCheck;

  public Cloverfield()
  {
    collectiveGreenPoints = 0;
    historicUses = new ArrayList<>();
    lastCheck = LocalDate.now();
    activeMultiplier = 1.2;
  }

  public LocalDate getLastCheck()
  {
    return lastCheck;
  }

  public void setLastCheck(LocalDate lastCheck)
  {
    this.lastCheck = lastCheck;
  }

  public int getCollectiveGreenPoints()
  {
    return collectiveGreenPoints;
  }

  public List<GreenPointUsage> getHistoricUses()
  {
    return historicUses;
  }

  public double getActiveMultiplier()
  {
    return activeMultiplier;
  }

  public void addPoints(int points)
  {
    collectiveGreenPoints += points;
  }

  public void addHistoric(GreenPointUsage usage)
  {
    if (usage.getGreenPoints() > collectiveGreenPoints)
    {
      throw new InvalidPointUsage("Ikke nok grønne point!!");
    }
    historicUses.add(usage);
    collectiveGreenPoints-= usage.getGreenPoints();
  }
}
