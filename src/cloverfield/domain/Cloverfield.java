package cloverfield.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cloverfield implements Serializable
{
  private int collectiveGreenPoints;
  private List<GreenPointUsage> historicUses;

  public Cloverfield()
  {
    collectiveGreenPoints = 0;
    historicUses = new ArrayList<>();
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

  public void setHistoricUses(List<GreenPointUsage> historicUses)
  {
    this.historicUses = historicUses;
  }

  public void addPoints(int points)
  {
    collectiveGreenPoints += points;
  }

  @Override public String toString()
  {
    return "Cloverfield{" + "collectiveGreenPoints=" + collectiveGreenPoints
        + ", historicUses=" + historicUses + '}';
  }
}
