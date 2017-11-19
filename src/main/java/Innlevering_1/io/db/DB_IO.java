package Innlevering_1.io.db;

import Innlevering_1.utils.dbutils.DBUtil;
import Innlevering_1.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Innlevering_1.io.db.DBConnection.conn;
import static Innlevering_1.utils.dbutils.DBUtil.*;
import static Innlevering_1.utils.dbutils.Queries.*;

/**
 * This class handles all input and output concerning the database
 *
 * @Author Jesper Dahl Ellingsen
 */
public class DB_IO
{
    private DBUtil dbUtil;

    public DB_IO()
    {
        dbUtil = new DBUtil();
    }

    /**
     * Adds "SUBJECT", "TEACHER" and "ROOM" tables to the database
     *
     * @throws SQLException
     */
    public void createTables() throws SQLException
    {
        Statement stmt = conn.createStatement();

        //Todo: find a way to have all the tableSQLs as one SQL
        stmt.execute(sqlTableTeacher);
        stmt.execute(sqlTableSubject);
        stmt.execute(sqlTableRoom);
    }

    /**
     * Deletes all the tables in the database
     *
     * @throws SQLException
     */
    public void deleteAllTables() throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.execute(sqlDeleteAllTables);
    }

    /**
     * Inserts the inputs into the database
     *
     * @param inputs is the list of objects that is going to be inserted into the table, only objects of type Subject, Teacher and Room is allowed
     * @return The amount of things inserted into the database
     * @throws IllegalArgumentException
     * @throws SQLException
     */
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

    /**
     * This method retrieves the requested table from the database
     *
     * @param table The table that you want to retrieve
     * @return A list of strings, the strings are the lines in the table
     * @throws IllegalArgumentException
     * @throws SQLException
     */
    public List<String> getTable(String table) throws IllegalArgumentException, SQLException
    {
        String structureCheck = table.toUpperCase();

        //This structure check prevents SQL Injection
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

    /**
     * Retrieves a specified subject from the database
     *
     * @param subjectCode The code for the subject you want to retrieve
     * @return The subject as a string
     * @throws SQLException
     */
    public String getSpecifiedSubject(String subjectCode) throws SQLException
    {
        String structureCheck = subjectCode.toLowerCase();

        if(dbUtil.structureCheckSubject(structureCheck))
        {
            String sql = "SELECT * FROM " + SUBJECT + " WHERE CODE = ?";

            PreparedStatement pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, subjectCode);
            ResultSet rs = pStatement.executeQuery();

            rs.next();
            int rowSize = dbUtil.getColumns(SUBJECT).size();
            String[] array = new String[rowSize];

            for (int i = 1; i <= rowSize; i++)
                array[i - 1] = rs.getString(i);

            return String.join(" || ", array);
        }
        else
            return String.format("\"%s\" is not a subjectcode\n", structureCheck);
    }

    /**
     * Retrieves a specified teacher from the database
     *
     * @param employeeID The id for the teacher you want to retrieve
     * @return The teacher as a string
     * @throws SQLException
     */
    public String getSpecifiedTeacher(int employeeID) throws SQLException
    {
        if(dbUtil.structureCheckTeacher(employeeID))
        {
            String sql = "SELECT * FROM " + TEACHER + " WHERE EMPLOYEEID = ?";

            PreparedStatement pStatement = conn.prepareStatement(sql);
            pStatement.setInt(1, employeeID);
            ResultSet rs = pStatement.executeQuery();

            rs.next();
            int rowSize = dbUtil.getColumns(TEACHER).size();
            String[] array = new String[rowSize];

            for (int i = 1; i <= rowSize; i++)
                array[i - 1] = rs.getString(i);

            return String.join(" || ", array);
        }
        else
            return String.format("\"%s\" is not an employeeID\n", employeeID);
    }

    /**
     * Retrieves a specified room from the database
     *
     * @param roomNumber The roomnumber for the room you want to retrieve
     * @return The room as a string
     * @throws SQLException
     */
    public String getSpecifiedRoom(String roomNumber) throws SQLException
    {
        String structureCheck = roomNumber.toLowerCase();

        if(dbUtil.structureCheckRoom(structureCheck))
        {
            String sql = "SELECT * FROM " + ROOM + " WHERE ROOMNUMBER = ?";

            PreparedStatement pStatement = conn.prepareStatement(sql);
            pStatement.setString(1, roomNumber);
            ResultSet rs = pStatement.executeQuery();

            rs.next();
            int rowSize = dbUtil.getColumns(ROOM).size();
            String[] array = new String[rowSize];

            for (int i = 1; i <= rowSize; i++)
                array[i - 1] = rs.getString(i);

            return String.join(" || ", array);
        }
        else
            return String.format("\"%s\" is not a roomnumber\n", structureCheck);
    }
}
