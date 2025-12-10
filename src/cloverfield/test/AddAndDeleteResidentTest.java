package cloverfield.test;

import cloverfield.domain.InvalidResidentException;
import cloverfield.domain.Resident;
import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;

public class AddAndDeleteResidentTest
{
  public static void main(String[] args)
  {
    // This only work properly when Bob is not in list.
    DataManager dm = new FileDataManager();
    System.out.println(dm.getCloverfield());

    System.out.println(dm.getAllResidents());
    Resident bob = new Resident("Bob");
    Resident don = new Resident("Don");
    try
    {
      dm.addResident(don);
    }
    catch (InvalidResidentException e)
    {
      System.out.println(e.getMessage());
    }
    try
    {
      dm.addResident(bob);
    }
    catch (InvalidResidentException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println(dm.getAllResidents());
    try
    {
      int idBob=bob.getId();
      dm.deleteResident(idBob);
    }
    catch (InvalidResidentException e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println(dm.getAllResidents());


  }
}
