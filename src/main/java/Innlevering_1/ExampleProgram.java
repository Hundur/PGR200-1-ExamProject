package Innlevering_1;

// This class is only here to run a preset code that will show that everything works

import Innlevering_1.io.IO_Controller;

import java.util.List;

public class ExampleProgram
{
    public static void main(String [] args)
    {
        // This will initalize the IO_Controller, that has a properties file as a parameter,
        // in the constructor will this properties file be used to initalize a DBConnection
        // that creates a connection to the database. The connection can be accessed from anywhere,
        // including the tests (public Static Connection conn).

        IO_Controller db_IO = new IO_Controller("db.properties");

        // createTables will create preset tables ( SUBJECT, TEACHER and ROOM ).

        db_IO.createTables();

        // readInput will read the txt file requested in the parameter, and read each line into a list.

        List<String> listString = db_IO.readInput("C:\\Users\\Bruker\\" +
                                                          "IdeaProjects\\Innlevering_1\\" +
                                                          "src\\test\\java\\Innlevering_1\\" +
                                                          "TestSupplies\\DBTestInput");

        // split will take a list of strings, and split everything in between ||'s, and put make them into
        // Subject, Teacher and Room objects, returns a list of all the objects created

        List<Object> listObjects = db_IO.split(listString);

        // insertIntoTable will take a list of objects, of type Subject, Teacher and Room into their respected tables
        // Subject -> SUBJECT, Teacher -> TEACHER, Room -> ROOM

        db_IO.insertIntoTable(listObjects);

        // getTable will search after the table with the name requested for in the parameter
        // and will return a list of the rows in the requested table.
        // If the requested table is not in the database, the user will get an error message,
        // that will say that the requested table does not exist.

        List<String> listSubject = db_IO.getTable("SUBJECT");
        List<String> listTeacher = db_IO.getTable("TEACHER");
        List<String> listRoom = db_IO.getTable("ROOM");

        // getSpecifiedSubject will search for a subjectcode, specified in the parameter,
        // and will return the row as a String

        String subjectPGR1100 = db_IO.getSpecifiedSubject("PGR1100");
        String subjectDB1100 = db_IO.getSpecifiedSubject("DB1100");

        // print prints out lists of strings, and normal strings

        db_IO.print(listSubject);
        db_IO.print(listTeacher);
        db_IO.print(listRoom);
        db_IO.print(subjectPGR1100);
        db_IO.print(subjectDB1100);

        // deleteAllTables will delete all the tables, will probably only be used for quickly deleting
        // the tables for tests, or this demo.

        db_IO.deleteAllTables();
    }
}
