package cloverfield.test;

import cloverfield.domain.*;
import cloverfield.persistence.DataManager;
import cloverfield.persistence.FileDataManager;

import java.time.LocalDate;

public class AddDummyData
{
  public static void main(String[] args)
  {
    DataManager dm = new FileDataManager();
    Resident bob = new Resident("Bob");
    Resident don = new Resident("Don");
    Resident johnny = new Resident("Johnny");
    Resident Peter = new Resident("Peter");
    GreenPointUsage trille = new GreenPointUsage("Ny trillerbør", 0);
    Green green = new Green("Fej for dig selv", 10);
    Green green2 = new Green("Lad mad for dig selv", 20);
    Collective collective = new Collective("Også feje, men for fællesskabet", 20);
    Collective collective2 = new Collective("Også mad, men for fællesskabet", 20);
    Barter barter = new Barter("fej for mig", 25, bob);
    Barter barter2 = new Barter("lav mad for mig", 35, don);
    dm.addResident(bob);
    dm.addResident(don);
    dm.addResident(johnny);
    dm.addResident(Peter);
    dm.addTask(green);
    dm.addTask(green2);
    dm.addTask(collective);
    dm.addTask(collective2);
    dm.addTask(barter);
    dm.addTask(barter2);
    dm.useGreenPoints(trille);
  }
}
