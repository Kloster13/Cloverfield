package cloverfield.test;

import cloverfield.domain.Cloverfield;
import cloverfield.persistence.DataContainer;
import cloverfield.persistence.FileAccessException;
import cloverfield.persistence.FileDataManager;

public class FileTester
{
  static void main()
  {
    FileDataManager dm = new FileDataManager();
    try
    {
      DataContainer test = dm.load();
      Cloverfield cloverfield = test.getCloverfield();
      cloverfield.addPoints(200);
      System.out.println(cloverfield);
      dm.save(test);

    }
    catch (FileAccessException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
