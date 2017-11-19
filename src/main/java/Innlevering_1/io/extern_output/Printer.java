package Innlevering_1.io.extern_output;

import java.util.List;

// Testing this class is reduntant, you are not supposed to test methods from
// classes that aren't yours

/**
 * Class for printing tables to the terminal
 *
 * @author Jesper Dahl Ellingsen
 */
public class Printer
{
    public static void print(String msg)
    {
        System.out.println(msg);
    }

    public static void print(List<String> msgs)
    {
        for(String msg : msgs)
            System.out.println(msg);
    }
}
