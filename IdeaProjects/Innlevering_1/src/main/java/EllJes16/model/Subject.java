package EllJes16.model;

public class Subject
{
    private String code;
    private String program;
    private int participants;

    public Subject(String name, String program, int participants)
    {
        this.code = name;
        this.program = program;
        this.participants = participants;
    }

    public String getName() { return code; }

    public void setName(String name) { this.code = name; }

    public String getProgram() { return program; }

    public void setProgram(String program) { this.program = program; }

    public int getParticipants() { return participants; }

    public void setParticipants(int participants) { this.participants = participants; }
}
