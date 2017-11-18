package Innlevering_1.utils.dbutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Innlevering_1.io.db.DBConnection.conn;
import static Innlevering_1.utils.dbutils.Queries.*;

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
            if(table.toLowerCase().equals(rs.getString(1)))
                return true;
        }
        return false;
    }

    public boolean structureCheckSubject(String code) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlGetSubjects);

        while(rs.next())
        {
            if(code.toLowerCase().equals(rs.getString(1).toLowerCase()))
                return true;
    }
        return false;
    }

    public boolean structureCheckTeacher(int employeeId) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlGetTeachers);

        while(rs.next())
        {
            if(employeeId == rs.getInt(1))
                return true;
        }
        return false;
    }

    public boolean structureCheckRoom(String roomNumber) throws SQLException
    {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlGetRooms);

        while(rs.next())
        {
            if(roomNumber.toLowerCase().equals(rs.getString(1).toLowerCase()))
                return true;
        }
        return false;
    }
}
