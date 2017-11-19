package Innlevering_1;

import Innlevering_1.io.IO_Controller;
import Innlevering_1.io.extern_input.TerminalInput;
import Innlevering_1.utils.MessageAnalyzer;

import java.sql.SQLException;
import java.util.List;

/**
 * Client for this program
 *
 * @author Jesper Dahl Ellingsen
 */
public class Client
{
    /**
     * Main method for this program
     * @param args
     */
    public static void main(String [] args)
    {
        try
        {
            IO_Controller db_IO = new IO_Controller("db.properties");
            TerminalInput userInput = new TerminalInput();
            MessageAnalyzer analyzer = new MessageAnalyzer();
            String input;

            db_IO.createTables();
            List<String> listString = db_IO.readInput("C:\\Users\\Bruker\\" +
                                                               "IdeaProjects\\Innlevering_1\\" +
                                                               "src\\test\\java\\Innlevering_1\\" +
                                                               "TestSupplies\\DBTestInput");

            List<Object> listObjects = db_IO.split(listString);
            db_IO.insertIntoTable(listObjects);

            System.out.println("If you don't know what to do, type \"Help\"\n" +
                               "To exit, type \"Exit\"\n");

            while (true)
            {
                input = userInput.getTerminalInput().toUpperCase();

                if (input.equals("EXIT"))
                {
                    userInput.close();
                    break;
                }

                System.out.println(analyzer.analyzeMessage(input));
            }

            db_IO.deleteAllTables();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
