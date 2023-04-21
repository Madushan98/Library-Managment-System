package Library.Database.Adapters;

import Enums.SupportSqlDB;
import Library.Database.Sqlite;
import Library.Database.Interfaces.SqlDatabase;

public class BaseSqlInitiator {
  protected SqlDatabase database;

  public BaseSqlInitiator(SupportSqlDB name) {
    switch (name) {
      case SQLITE:
        database = Sqlite.getInstance();
        break;
      default:
        throw new IllegalArgumentException("Invalid database name");
    }
  }
}
