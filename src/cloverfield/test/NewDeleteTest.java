package cloverfield.test;

import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;

public class NewDeleteTest
{
  public static void main(String[] args)
  {
    DataManager dataManager = new FileDataManager();
    dataManager.deleteResident(4);
  }
}
