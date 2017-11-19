package Innlevering_1.orm;

import Innlevering_1.model.Subject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * The IO methods using ORMLite
 *
 * @author Jesper Dahl Ellingsen
 */
public class ORM_DB_IO
{
    private ConnectionSource conn;
    private Dao<Subject, String> subjectDao;

    public ORM_DB_IO(ConnectionSource conn) throws SQLException
    {
        this.conn = conn;
        subjectDao = DaoManager.createDao(conn, Subject.class);
    }

    /**
     * Created the table "SUBJECT"
     *
     * @throws SQLException
     */
    public void createTable() throws SQLException
    {
        TableUtils.createTableIfNotExists(conn, Subject.class);
    }

    /**
     * Inserts a Subject into the database
     *
     * @param s Subject to be inserted into the database
     * @throws SQLException
     */
    public void insertIntoDatabase(Subject s) throws SQLException
    {
        subjectDao.createIfNotExists(s);
    }

    /**
     * Retrieves a specified Subject
     *
     * @param subjectCode The code of the subject that is to be retrieved
     * @throws SQLException
     */
    public void retrieveSpecifiedSubject(String subjectCode) throws SQLException
    {
        List<Subject> subjects = subjectDao.queryForEq(Subject.CODE_FIELD_NAME, subjectCode);

        for(Subject subject : subjects)
        {
            int id = subject.getId();
            String code = subject.getName();
            String program = subject.getProgram();
            int participants = subject.getParticipants();

            System.out.println(String.format("Subject: %s || %s || %s || %s", id, code, program, participants));
        }
    }

    /**
     * Drops all the tables in the database
     *
     * @throws SQLException
     */
    public void dropTables() throws SQLException
    {
        TableUtils.dropTable(conn, Subject.class, true);
    }

    /**
     * Closes the connection with the database
     *
     * @throws SQLException
     */
    public void close() throws SQLException
    {
        conn.close();
    }
}
