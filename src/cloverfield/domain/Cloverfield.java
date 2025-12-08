package cloverfield.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cloverfield implements Serializable
{
  private int collectiveGreenPoints;
  private List<GreenPointUsage> historicUses;
  private double activeMultiplier;
  private LocalDate lastCheck;

  public Cloverfield()
  {
    collectiveGreenPoints = 0;
    historicUses = new ArrayList<>();
    activeMultiplier = 1.2;
  }

  public int getCollectiveGreenPoints()
  {
    return collectiveGreenPoints;
  }

  public void setCollectiveGreenPoints(int collectiveGreenPoints)
  {
    this.collectiveGreenPoints = collectiveGreenPoints;
  }

  public List<GreenPointUsage> getHistoricUses()
  {
    return historicUses;
  }

  public double getActiveMultiplier()
  {
    return activeMultiplier;
  }

  public void setHistoricUses(List<GreenPointUsage> historicUses)
  {
    this.historicUses = historicUses;
  }

  public void addPoints(int points)
  {
    collectiveGreenPoints += points;
  }

  public void setActiveMultiplier(double multiplier)
  {
    this.activeMultiplier = multiplier;
  }


  @Override public String toString()
  {
    return "Cloverfield{" + "collectiveGreenPoints=" + collectiveGreenPoints
        + ", historicUses=" + historicUses + '}';
  }
}
