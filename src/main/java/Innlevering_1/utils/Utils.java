package Innlevering_1.utils;

import java.io.IOException;
import java.io.InputStream;
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
}
