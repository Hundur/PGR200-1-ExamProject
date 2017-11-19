package Innlevering_1.io.extern_input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle files
 *
 * @author Jesper Dahl Ellingsen
 */
public class FileHandler
{
    public BufferedReader br;

    /**
     * Reads the txtfile
     *
     * @param txtFile The file that is read
     * @return The list of lines in the txtfile
     * @throws IOException
     */
    public List<String> readInput(String txtFile) throws IOException
    {
        br = new BufferedReader(new FileReader(txtFile));
        List<String> stringList = new ArrayList<>();

        String cLine;

        while((cLine = br.readLine()) != null)
        {
            stringList.add(cLine);
        }

        return stringList;
    }
}
