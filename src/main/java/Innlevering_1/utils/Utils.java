package Innlevering_1.utils;

import Innlevering_1.io.IO_Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Utils
{
    public Properties getProperties(String propFile)
    {
        try
        {
            InputStream inputStream = getClass().getResourceAsStream("/" + propFile);
            Properties props = new Properties();
            props.load(inputStream);
            return props;
        }
        catch( IOException e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTable(String tableName)
    {
        IO_Controller db = new IO_Controller("db.properties");
        List<String> table = db.getTable(tableName);
        String ret = "";

        for(String row : table)
            ret += row + "\n";

        return ret;
    }
}
