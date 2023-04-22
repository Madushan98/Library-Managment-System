package Library.Database.Interfaces;

import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Enums.SupportSqlDB;

public interface DataManager {
  BookManager GetBookManager(SupportInMemoryDB dbType);

  BookManager GetBookManager(SupportSqlDB dbType);

  RecordManager GetRecordManager(SupportInMemoryDB dbType);

  RecordManager GetRecordManager(SupportSqlDB dbType);
}
