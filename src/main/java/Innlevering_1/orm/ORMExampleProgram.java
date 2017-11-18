package Innlevering_1.orm;

import Innlevering_1.model.Subject;
import com.j256.ormlite.logger.LocalLog;

import java.sql.SQLException;

public class ORMExampleProgram
{
    public static void main(String [] args)
    {
        try
        {
            System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
            DBConnector dbConnector = new DBConnector("db.properties");
            ORM_DB_IO io = new ORM_DB_IO(dbConnector.getConnSource());

            io.createTable();
            io.insertIntoDatabase(new Subject("PGR1100", "Programmering", 40));
            io.retrieveSpecifiedSubject("PGR1100");
            io.dropTables();
            io.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
