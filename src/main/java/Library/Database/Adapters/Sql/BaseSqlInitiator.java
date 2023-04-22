package Library.Database.Adapters.Sql;

import Library.Database.Sqlite;
import Library.Database.Enums.SupportSqlDB;
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
