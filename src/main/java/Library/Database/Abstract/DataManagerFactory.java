package Library.Database.Abstract;

import Library.Database.Adapters.InMemory.BookManagerInMemoryAdapter;
import Library.Database.Adapters.InMemory.RecordManagerInMemoryAdapter;
import Library.Database.Adapters.Sql.BookManageSqlAdapter;
import Library.Database.Adapters.Sql.RecordManageSqlAdapter;
import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.DataManager;
import Library.Database.Interfaces.RecordManager;

public class DataManagerFactory implements DataManager {
  public BookManager GetBookManager(SupportInMemoryDB dbType) {
    switch (dbType) {
      case LISTS:
        return new BookManagerInMemoryAdapter(dbType);
      default:
        throw new IllegalArgumentException("Invalid database type");
    }
  }

  public BookManager GetBookManager(SupportSqlDB dbType) {
    switch (dbType) {
      case SQLITE:
        return new BookManageSqlAdapter(dbType);
      default:
        throw new IllegalArgumentException("Invalid database type");
    }
  }

  public RecordManager GetRecordManager(SupportInMemoryDB dbType) {
    switch (dbType) {
      case LISTS:
        return new RecordManagerInMemoryAdapter(dbType);
      default:
        throw new IllegalArgumentException("Invalid database type");
    }
  }

  public RecordManager GetRecordManager(SupportSqlDB dbType) {
    switch (dbType) {
      case SQLITE:
        return new RecordManageSqlAdapter(dbType);
      default:
        throw new IllegalArgumentException("Invalid database type");
    }
  }
}
