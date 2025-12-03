package cloverfield.persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDataManager implements DataManager
{
  private final String fileName = "CloverfieldData.bin";

  public FileDataManager() throws FileAccessException
  {
    Path path = Paths.get("src","cloverfield","data",fileName);
    if (!Files.exists(path))
    {
      DataContainer dataContainer = new DataContainer();
      save(dataContainer);
    }
  }

  public void save(DataContainer dataContainer) throws FileAccessException
  {
    Path path = Paths.get("src","cloverfield","data",fileName);

    try (ObjectOutputStream output = new ObjectOutputStream(
        new FileOutputStream(path.toFile())))
    {
      output.writeObject(dataContainer);
    }
    catch (IOException e)
    {
      throw new FileAccessException("Error in saving data");
    }
  }


  public DataContainer load() throws FileAccessException
  {
    Path path = Paths.get("src","cloverfield","data",fileName);
    try (ObjectInputStream input = new ObjectInputStream(
        new FileInputStream(path.toFile())))
    {
      return (DataContainer) input.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new FileAccessException("Error in loading data");
    }
  }

  @Override public void addGreenTask()
  {
    
  }

  @Override public void addBarterTask()
  {

  }

  @Override public void addCollectiveTask()
  {

  }
}
