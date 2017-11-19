package Innlevering_1.io.db;

import Innlevering_1.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Generates the connection between database and the program
 *
 * @Author Jesper Dahl Ellingsen
 */
public class DBConnection
{
    public static Connection conn;

    /**
     * Creates the connection between the program and the database
     *
     * @param propFile Propertiesfile with information needed to create the connection
     */
    public DBConnection(String propFile)
    {
        try
        {
            System.out.println("\nTrying to establish connection...");
            Utils utils = new Utils();
            Properties props = utils.getProperties(propFile);
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
}
