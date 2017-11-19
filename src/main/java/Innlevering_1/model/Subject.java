package Innlevering_1.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Class for object Subject
 *
 * @Author Jesper Dahl Ellingsen
 */
@DatabaseTable(tableName = "SUBJECT")
public class Subject
{
    public static final String CODE_FIELD_NAME = "CODE";
    public static final String PROGRAM_FIELD_NAME = "PROGRAM";
    public static final String PARTICIPANTS_FIELD_NAME = "PARTICIPANTS";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = CODE_FIELD_NAME)
    private String code;

    @DatabaseField(columnName = PROGRAM_FIELD_NAME)
    private String program;

    @DatabaseField(columnName = PARTICIPANTS_FIELD_NAME)
    private int participants;

    public Subject() {}

    /**
     * Created an object of type Subject
     *
     * @param name The name of the subject
     * @param program The program the subject is a part of
     * @param participants The amount of participants
     */
    public Subject(String name, String program, int participants)
    {
        this.code = name;
        this.program = program;
        this.participants = participants;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return code; }

    public void setName(String name) { this.code = name; }

    public String getProgram() { return program; }

    public void setProgram(String program) { this.program = program; }

    public int getParticipants() { return participants; }

    public void setParticipants(int participants) { this.participants = participants; }
}
