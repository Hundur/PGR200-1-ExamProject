package Innlevering_1.Tests;

import Innlevering_1.io.IO_Controller;
import Innlevering_1.model.Room;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static Innlevering_1.TestSupplies.IO_ControllerTest_Utils.*;
import static Innlevering_1.io.db.DBConnection.conn;
import static org.junit.Assert.*;

public class IO_ControllerDBTests
{
    IO_Controller io_Control;
    List<Object> inputList;
    Statement stmt;

    Boolean deleted = true;

    @Before
    public void setUp()
    {
        io_Control = new IO_Controller("dbTest.properties");
        io_Control.createTables();
        deleted = false;

    }

    @After
    public void tearDown()
    {
        if( !deleted ) { io_Control.deleteAllTables(); }
        deleted = false;
    }



    @Test
    public void createTableTest()
    {
        try
        {
            // ARRANGE

            // createTable is called in @Before, but the test will still test if a database is created!
            String sql = "SHOW TABLES";
            ResultSet rs;

            // ACT

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // ASSERT

            rs.next();
            assertEquals("room", rs.getString(1));
            rs.next();
            assertEquals("subject", rs.getString(1));
            rs.next();
            assertEquals("teacher", rs.getString(1));
        }
        catch (SQLException e) { e.printStackTrace(); }
    }

    @Test
    public void deleteAllTablesTest()
    {
        try
        {
            // ARRANGE

            String sql = "SHOW TABLES";
            ResultSet rs;

            stmt = conn.createStatement();

            // ACT

            io_Control.deleteAllTables();
            deleted = true;

            rs = stmt.executeQuery(sql);
            rs.next();
            rs.getString(1);

            // ASSERT

            // If the test does not get an exception from the last line of code
            // the test will fail
            fail();

        }
        catch (SQLException e)
        {
            // If the test goes into this catch, the test passes
        }
    }

    @Test
    public void insertIntoTableNormal()
    {
        // ARRANGE

        inputList = listNormal();

        // ACT / ASSERT

        assertEquals(6, io_Control.insertIntoTable(inputList));
    }

    @Test
    public void insertIntoTableMultipleTimes()
    {
        // This test checks if you can add things in the database multiple times after database creation

        // ARRANGE

        int inputAmount;
        Room r;
        Room r2;
        inputList = listNormal();

        // ACT

        inputAmount = io_Control.insertIntoTable(inputList);

        // Changing roomNumber of rooms so it does not input multiple of the same primary key in database
        // Resulting in error

        r = (Room) inputList.get(5);
        r2 = (Room) inputList.get(4);

        inputList.remove(5);
        inputList.remove(4);

        r.setRoomNumber("F102");
        r2.setRoomNumber("V102");

        inputList.add(r);
        inputList.add(r2);


        inputAmount += io_Control.insertIntoTable(inputList);

        // ASSERT

        assertEquals(12, inputAmount);
    }

    @Test
    public void insertIntoTableWrongObjectType()
    {
        // ARRANGE

        inputList = listNormal();

        // ACT

        // Inputting an object that aren't supposed to be in the list
        inputList.add(io_Control);

        // ASSERT

        assertEquals(-1, io_Control.insertIntoTable(inputList));
    }

    @Test
    public void getTableTest()
    {
        //ARRANGE

        List<String> listString = new ArrayList<>();
        inputList = listNormal();

        //ACT

        io_Control.insertIntoTable(inputList);

        //ASSERT

        listString = io_Control.getTable(SUBJECT);
        assertEquals(2, listString.size());

        listString = io_Control.getTable(TEACHER);
        assertEquals(2, listString.size());

        listString = io_Control.getTable(ROOM);
        assertEquals(2, listString.size());
    }

    @Test
    public void getTableExceptionTest()
    {

        //ARRANGE

        inputList = listNormal();

        //ACT

        io_Control.insertIntoTable(inputList);

        //ASSERT

        assertEquals(null, io_Control.getTable("Not a table"));
    }

    @Test
    public void getSpecifiedStuffTest()
    {
        //ARRANGE

        inputList = listNormal();

        //ACT

        io_Control.insertIntoTable(listNormal());

        //ASSERT

        assertEquals("2 || PGR1100 || Programmering || 50", io_Control.getSpecifiedSubject("PGR1100"));
        assertEquals("1 || DB1100 || Databaser || 100", io_Control.getSpecifiedSubject("DB1100"));

        assertEquals("1 || yes || Jesper Dahl Ellingsen", io_Control.getSpecifiedTeacher(1));
        assertEquals("2 || no || Sebastian Langseth Knutsen", io_Control.getSpecifiedTeacher(2));

        assertEquals("F101 || Fjerdingen || 70", io_Control.getSpecifiedRoom("F101"));
        assertEquals("V101 || Vulkan || 130", io_Control.getSpecifiedRoom("V101"));
    }
}
