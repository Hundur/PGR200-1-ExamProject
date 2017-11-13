package Innlevering_1.io.db;

import Innlevering_1.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Innlevering_1.io.db.DBConnection.conn;
import static Innlevering_1.io.db.DBUtil.*;
import static Innlevering_1.io.db.Queries.*;

public class DB_IO
{
    private DBUtil dbUtil;

    public DB_IO()
    {
        dbUtil = new DBUtil();
    }

    public void createTables() throws SQLException
    {
        Statement stmt = conn.createStatement();

        //Todo: find a way to have all the tableSQLs as one SQL
        stmt.execute(sqlTableTeacher);
        stmt.execute(sqlTableSubject);
        stmt.execute(sqlTableRoom);
    }

    public void deleteAllTables() throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.execute(sqlDeleteAllTables);
    }

    public int insertIntoTable(List<Object> inputs) throws IllegalArgumentException, SQLException
    {
        int inputAmount = 0;
        for( Object input : inputs )
        {
            if( input instanceof Subject )
            {
                Subject s = (Subject) input;
                inputAmount += insertSubjectIntoTable(s);
            }
            else if( input instanceof Teacher )
            {
                Teacher t = (Teacher) input;
                inputAmount += insertTeacherIntoTable(t);
            }
            else if( input instanceof Room )
            {
                Room r = (Room) input;
                inputAmount += insertRoomIntoTable(r);
            }
            else
            {
                throw new IllegalArgumentException("Object of wrong class in inputlist");
            }
        }
        return inputAmount;
    }

    private int insertSubjectIntoTable(Subject s) throws SQLException
    {
        String sql = "INSERT INTO " + SUBJECT + "(CODE, PROGRAM, PARTICIPANTS) VALUES(?,?,?);";

        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.setString(1, s.getName());
        pStatement.setString(2, s.getProgram());
        pStatement.setInt(3, s.getParticipants());

        return pStatement.executeUpdate();
    }

    private int insertTeacherIntoTable(Teacher t) throws SQLException
    {
        String sql = "INSERT INTO " + TEACHER + "(FULLNAME, AVAILABLE) VALUES(?,?);";

        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.setString(1, t.getFullName());
        pStatement.setString(2, t.getAvailable());

        return pStatement.executeUpdate();
    }

    private int insertRoomIntoTable(Room r) throws SQLException
    {
        String sql = "INSERT INTO " + ROOM + "(ROOMNUMBER, CAMPUS, CAPACITY) VALUES(?,?,?);";

        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.setString(1, r.getRoomNumber());
        pStatement.setString(2, r.getCampus());
        pStatement.setInt(3, r.getCapacity());

        return pStatement.executeUpdate();
    }

    public List<String> getTable(String table) throws IllegalArgumentException, SQLException
    {
        String structureCheck = table.toUpperCase();

        if (dbUtil.structureCheckTable(structureCheck))
        {
            ResultSet rs;
            List<String> listString = new ArrayList<>();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM " + structureCheck + ";";

            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int columnSize = dbUtil.getColumns(table).size();
                String[] array = new String[columnSize];

                for(int i = 1; i <= columnSize; i++)
                    array[i - 1] = rs.getString(i);

                listString.add(String.join(" || ", array));
            }
            return listString;
        }
        else
        {
            throw new IllegalArgumentException(String.format("\"%s\" is not a table, allowed inputs are: %s, %s, %s\n", structureCheck, SUBJECT, TEACHER, ROOM));
        }
    }

    public String getSpecifiedSubject(String subjectCode) throws SQLException
    {
        String sql = "SELECT * FROM " + SUBJECT + " WHERE CODE = ?";

        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.setString(1, subjectCode);
        ResultSet rs = pStatement.executeQuery();

        rs.next();
        int rowSize = dbUtil.getColumns(SUBJECT).size();
        String[] array = new String[rowSize];

        for(int i = 1; i <= rowSize; i++)
           array[i - 1] = rs.getString(i);

        return String.join(" || ", array);
    }
}
