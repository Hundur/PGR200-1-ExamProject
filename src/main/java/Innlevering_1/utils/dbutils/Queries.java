package Innlevering_1.utils.dbutils;

/**
 * Queries used in the program
 *
 * @Author Jesper Dahl Ellingsen
 */
public class Queries
{
    public static final String sqlTableSubject =
                              "CREATE TABLE SUBJECT " +
                              "(SUBJECTID INT(4) AUTO_INCREMENT, " +
                              "CODE VARCHAR(10) NOT NULL, " +
                              "PROGRAM VARCHAR(50) NOT NULL, " +
                              "PARTICIPANTS INT(3) NOT NULL, " +
                              "PRIMARY KEY(SUBJECTID)); ",

                              sqlTableTeacher =
                              "CREATE TABLE TEACHER " +
                              "(EMPLOYEEID INT(3) AUTO_INCREMENT, " +
                              "AVAILABLE VARCHAR(3) NOT NULL, " +
                              "FULLNAME VARCHAR(100) NOT NULL, " +
                              "PRIMARY KEY(EMPLOYEEID)); ",

                              sqlTableRoom =
                              "CREATE TABLE ROOM " +
                              "(ROOMNUMBER VARCHAR(4) NOT NULL, " +
                              "CAMPUS VARCHAR(10) NOT NULL, " +
                              "CAPACITY INT(3) NOT NULL, " +
                              "PRIMARY KEY(ROOMNUMBER)); ",

                              sqlGetSubjects =
                              "SELECT CODE " +
                              "FROM SUBJECT",

                              sqlGetTeachers =
                              "SELECT EMPLOYEEID " +
                              "FROM TEACHER",

                              sqlGetRooms =
                              "SELECT ROOMNUMBER " +
                              "FROM ROOM",

                              sqlDeleteAllTables =
                              "DROP TABLE SUBJECT, TEACHER, ROOM;",

                              sqlShowTables =
                              "SHOW TABLES;";
}