package Innlevering_1.Tests;

import Innlevering_1.io.db.DBConnection;
import org.junit.Before;
import org.junit.Test;

import static Innlevering_1.io.db.DBConnection.conn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class DBHandlerTest
{
    private DBConnection db;

    @Before
    public void setUp()
    {
        db = new DBConnection("dbTest.properties");
    }

    @Test
    public void generatingConnection()
    {
        assertNotEquals(conn, null);
    }
}
