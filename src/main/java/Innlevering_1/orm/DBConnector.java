package Innlevering_1.orm;

import Innlevering_1.utils.Utils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Class for getting a connection between database and the program using ORMLite
 *
 * @Author Jesper Dahl Ellingsen
 */
public class DBConnector
{
    ConnectionSource connSource;

    /**
     * Makes a connection between the program and the database
     *
     * @param propfile The file with the information needed to create the connection
     */
    public DBConnector(String propfile)
    {
        try
        {
            Utils utils = new Utils();
            Properties props = utils.getProperties(propfile);
            connSource = new JdbcConnectionSource(String.format("jdbc:mysql://%s:%s/%s",
                                                                    props.getProperty("host"),
                                                                    props.getProperty("port"),
                                                                    props.getProperty("dbname")),
                                                                    props.getProperty("username", "root"),
                                                                    props.getProperty("password", "root"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ConnectionSource getConnSource() { return connSource; }
}
