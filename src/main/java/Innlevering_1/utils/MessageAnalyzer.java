package Innlevering_1.utils;

import Innlevering_1.io.db.DB_IO;
import Innlevering_1.utils.dbutils.DBUtil;

import java.sql.SQLException;

import static Innlevering_1.utils.Utils.getTable;

public class MessageAnalyzer
{
    private final String errorSearchword = "Error: Missing searchword\n" +
            "If you need help, type \"help\"\n";

    public String analyzeMessage(String message) throws SQLException
    {
        String finalMsg;
        String information;
        String msg = message.trim();

        DB_IO io = new DB_IO();
        DBUtil dbUtil = new DBUtil();

        if(msg.contains("GET TABLE"))
        {
            finalMsg = msg.replace("GET TABLE ", "");

            if(finalMsg.equals("GET TABLE"))
                information = errorSearchword;

            else if(dbUtil.structureCheckTable(finalMsg))
                information = getTable(finalMsg);
            else
                information = "Error: " + finalMsg + " is not a valid table\n";
        }

        else if(msg.contains("GET SUBJECT"))
        {
            finalMsg = msg.replace("GET SUBJECT ", "");

            if(finalMsg.equals("GET SUBJECT"))
                information = errorSearchword;
            else if(io.getSpecifiedSubject(finalMsg).equals("null\n"))
                information = "Error: " + finalMsg + " is not a valid subjectcode\n";
            else
                information = io.getSpecifiedSubject(finalMsg);
        }

        else if(msg.contains("GET TEACHER"))
        {
            try
            {
                finalMsg = msg.replace("GET TEACHER ", "");

                if (finalMsg.equals("GET TEACHER"))
                    information = errorSearchword;
                else if (io.getSpecifiedTeacher(Integer.parseInt(finalMsg)).equals("null\n"))
                    information = "Error: " + finalMsg + " is not a valid employeeID\n";
                else
                    information = io.getSpecifiedTeacher(Integer.parseInt(finalMsg));
            }
            catch(NumberFormatException e)
            {
                return "Error: EmployeeID has to be a number\n";
            }
        }

        else if(msg.contains("GET ROOM"))
        {
            finalMsg = msg.replace("GET ROOM ", "");

            if(finalMsg.equals("GET ROOM"))
                information = errorSearchword;
            else if(io.getSpecifiedRoom(finalMsg).equals("null\n"))
                information = "Error: " + finalMsg + " is not a valid roomnumber\n";
            else
                information = io.getSpecifiedRoom(finalMsg);
        }

        else if(msg.equals("HELP"))
            information = "Getting a table: \"Get table ...\"\n" +
                    "Available tables: Subject - Teacher - Room\n" +
                    "Getting a subject: \"Get subject ...\"\n" +
                    "Available subjects: PGR1100 - DB1100\n" +
                    "Getting a teacher: \"Get teacher ...\"\n" +
                    "Available teacherIDs: 1 - 2\n" +
                    "Getting a room: \"Get room ...\"\n" +
                    "Available rooms: F101 - V101\n" +
                    "To exit the server: \"Exit\"\n";

        else
            information = "Error: Not a command\n" +
                    "If you need help, type \"help\"\n";



        return information;
    }
}