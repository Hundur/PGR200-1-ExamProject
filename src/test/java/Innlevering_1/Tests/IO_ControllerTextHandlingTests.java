package Innlevering_1.Tests;

import Innlevering_1.io.IO_Controller;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import Innlevering_1.model.*;


import static org.junit.Assert.*;
import static Innlevering_1.TestSupplies.IO_ControllerTest_Utils.listNormal;

public class IO_ControllerTextHandlingTests
{
    IO_Controller io_Control;

    @Before
    public void setUp()
    {
        io_Control = new IO_Controller("dbTest.properties");
    }



    @Test
    public void readInputTest()
    {
        // ARRANGE

        int outputNumber = 14;
        List<String> listString;

        // ACT

        listString = io_Control.readInput("C:\\Users\\Bruker\\" +
                                                  "IdeaProjects\\Innlevering_1\\" +
                                                  "src\\test\\java\\Innlevering_1\\" +
                                                  "TestSupplies\\DBTestInput");

        // ASSERT

        assertEquals(outputNumber, listString.size());
    }

    @Test
    public void splitTest()
    {
        // ARRANGE

        List<String> listString;
        List<Object> listObject, listObjectChecker;

        // ACT

        listObjectChecker = listNormal();

        listString = io_Control.readInput("C:\\Users\\Bruker\\" +
                                                  "IdeaProjects\\Innlevering_1\\" +
                                                  "src\\test\\java\\Innlevering_1\\" +
                                                  "TestSupplies\\DBTestInput");
        listObject = io_Control.split(listString);

        //ASSERT

        for (int counter = 0; counter < listObject.size(); counter++)
        {
            Object o = listObject.get(counter);
            Object oCheck = listObjectChecker.get(counter);

            if(o instanceof Subject)
            {
                Subject s = (Subject) o;
                Subject sCheck = (Subject) oCheck;

                assertEquals(sCheck.getName(), s.getName());
                assertEquals(sCheck.getProgram(), s.getProgram());
                assertEquals(sCheck.getParticipants(), s.getParticipants());
            }
            else if(o instanceof Teacher)
            {
                Teacher t = (Teacher) o;
                Teacher tCheck = (Teacher) oCheck;

                assertEquals(tCheck.getFullName(), t.getFullName());
                assertEquals(tCheck.getAvailable(), t.getAvailable());
            }
            else if(o instanceof Room)
            {
                Room r = (Room) o;
                Room rCheck = (Room) oCheck;

                assertEquals(rCheck.getRoomNumber(), r.getRoomNumber());
                assertEquals(rCheck.getCampus(), r.getCampus());
                assertEquals(rCheck.getCapacity(), r.getCapacity());
            }
        }
    }

    @Test
    public void insertIntoTableWtihExternFile()
    {
        // ARRANGE

        List<String> listString;
        List<Object> listObject;

        io_Control.createTables();

        // ACT

        listString = io_Control.readInput("C:\\Users\\Bruker\\" +
                                                   "IdeaProjects\\Innlevering_1\\" +
                                                   "src\\test\\java\\Innlevering_1\\" +
                                                   "TestSupplies\\DBTestInput");
        listObject = io_Control.split(listString);

        // ASSERT

        assertEquals(6, io_Control.insertIntoTable(listObject));
        io_Control.deleteAllTables();
    }
}