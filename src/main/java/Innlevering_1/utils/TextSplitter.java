package Innlevering_1.utils;

import Innlevering_1.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Splits the text
 *
 * @Author Jesper Dahl Ellingsen
 */
public class TextSplitter
{
    /**
     * Splits the text and creates objects from the information in the text
     *
     * @param listString A list of lines to split
     * @return A list of objects of type Subject, Teacher and Rooms
     */
    public List<Object> split(List<String> listString)
    {
        List<Object> objectList = new ArrayList<>();
        int listPosition = 0;

        while(! listString.get(listPosition).toUpperCase().equals("FILEEND"))
        {
            listPosition++;
            if (listString.get(listPosition).toUpperCase().equals("SUBJECT"))
            {
                listPosition++;
                while (! listString.get(listPosition).equals(""))
                {
                    Subject s = splitIntoSubject(listString.get(listPosition));

                    objectList.add(s);
                    listPosition++;
                }
            }
            else if (listString.get(listPosition).toUpperCase().equals("TEACHER"))
            {
                listPosition++;
                while (! listString.get(listPosition).equals(""))
                {
                    Teacher t = splitIntoTeacher(listString.get(listPosition));

                    objectList.add(t);
                    listPosition++;
                }
            }
            else if (listString.get(listPosition).toUpperCase().equals("ROOM"))
            {
                listPosition++;
                while (! listString.get(listPosition).equals(""))
                {
                    Room r = splitIntoRoom(listString.get(listPosition));

                    objectList.add(r);
                    listPosition++;
                }
            }
        }
        return objectList;
    }

    // .replaceAll("\\+$", ""); Removes the uneeded space at the end of the strings
    private Subject splitIntoSubject(String infoLine)
    {
        String[] subjectInfo = infoLine.split("\\|\\|");

        String code = subjectInfo[0].trim();
        String program = subjectInfo[1].trim();
        int participants = Integer.parseInt(subjectInfo[2].trim());

        return new Subject(code, program, participants);
    }

    private Teacher splitIntoTeacher(String infoLine)
    {
        String[] teacherInfo = infoLine.split("\\|\\|");

        String fullName = teacherInfo[0].trim();
        String available = teacherInfo[1].trim();

        return new Teacher(fullName, available);
    }

    private Room splitIntoRoom(String infoLine)
    {
        String[] roomInfo = infoLine.split("\\|\\|");

        String roomNumber = roomInfo[0].trim();
        String campus = roomInfo[1].trim();
        int capacity = Integer.parseInt(roomInfo[2].trim());

        return new Room(roomNumber, campus, capacity);
    }
}

