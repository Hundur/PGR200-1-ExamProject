package EllJes16.io.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static EllJes16.io.db.DBConnection.conn;
import static EllJes16.io.db.Queries.sqlShowTables;

public class DBUtil
{
    public static final String SUBJECT = "SUBJECT",
                               TEACHER = "TEACHER",
                               ROOM    = "ROOM";

    // No SQL-injection prevention needed in this method,
    // because of no interaction with user, only used for
    // a utility for DB_IO
    public List<String> getColumns(String table) throws SQLException
    {
        List<String> columnList = new ArrayList<>();

        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM " + table + "; ";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsMetaData = rs.getMetaData();

        rs.next();
        for( int i = 0; i < rsMetaData.getColumnCount(); i++)
        {
            columnList.add(rsMetaData.getColumnName(i + 1));
        }
        return columnList;
    }

    public boolean structureCheckTable(String table) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlShowTables);

        while(rs.next())
        {
            if(table.equals(rs.getString(1)));
                return true;
        }
        return false;
    }
}
