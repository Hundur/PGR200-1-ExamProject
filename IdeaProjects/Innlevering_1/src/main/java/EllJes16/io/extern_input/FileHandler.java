package EllJes16.io.extern_input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{
    public BufferedReader br;

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
