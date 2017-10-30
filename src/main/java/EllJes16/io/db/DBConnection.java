package EllJes16.io.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection
{
    public static Connection conn;

    public DBConnection(String propFile)
    {
        try
        {
            System.out.println("\nTrying to establish connection...");
            Properties props = getProperties(propFile);
            this.conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s",
                                                    props.getProperty("host"),
                                                    props.getProperty("port"),
                                                    props.getProperty("dbname")),
                                                    props.getProperty("username","root"),
                                                    props.getProperty("password", "root"));
            System.out.println("Success!\n");
        }
        catch( SQLException e )
        {
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
    private Properties getProperties(String propFile)
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
