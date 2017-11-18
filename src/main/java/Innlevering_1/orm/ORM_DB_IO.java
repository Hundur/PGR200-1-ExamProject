package Innlevering_1.orm;

import Innlevering_1.model.Subject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class ORM_DB_IO
{
    private ConnectionSource conn;
    private Dao<Subject, String> subjectDao;

    public ORM_DB_IO(ConnectionSource conn) throws SQLException
    {
        this.conn = conn;
        subjectDao = DaoManager.createDao(conn, Subject.class);
    }

    public void createTable() throws SQLException
    {
        TableUtils.createTableIfNotExists(conn, Subject.class);
    }

    public void insertIntoDatabase(Subject s) throws SQLException
    {
        subjectDao.createIfNotExists(s);
    }

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

    public void dropTables() throws SQLException
    {
        TableUtils.dropTable(conn, Subject.class, true);
    }

    public void close() throws SQLException
    {
        conn.close();
    }
}
