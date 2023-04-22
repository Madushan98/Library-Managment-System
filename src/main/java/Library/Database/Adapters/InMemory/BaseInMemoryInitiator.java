package Library.Database.Adapters.InMemory;

import Library.Database.InMemory;
import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Interfaces.InMemoryDatabase;

public class BaseInMemoryInitiator {
  protected InMemoryDatabase database;

  public BaseInMemoryInitiator(SupportInMemoryDB name) {
    switch (name) {
      case LISTS:
        database = InMemory.getInstance();
        break;
      default:
        throw new IllegalArgumentException("Invalid database name");
    }
  }
}
