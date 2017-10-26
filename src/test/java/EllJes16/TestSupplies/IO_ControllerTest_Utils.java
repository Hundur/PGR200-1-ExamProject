package EllJes16.TestSupplies;

import EllJes16.model.*;

import java.util.ArrayList;
import java.util.List;

public class IO_ControllerTest_Utils
{
    public static final String SUBJECT = "SUBJECT",
                               TEACHER = "TEACHER",
                               ROOM    = "ROOM";

    public static List<Object> listNormal()
    {
        List<Object> inputList;

        inputList = new ArrayList<>();

        inputList.add(new Subject("DB1100", "Databaser", 100));
        inputList.add(new Subject("PGR1100", "Programmering", 50));
        inputList.add(new Teacher("Jesper Dahl Ellingsen", "yes"));
        inputList.add(new Teacher("Sebastian Langseth Knutsen", "no"));
        inputList.add(new Room("F101", "Fjerdingen", 70));
        inputList.add(new Room("V101", "Vulkan", 130));

        return inputList;
    }
}