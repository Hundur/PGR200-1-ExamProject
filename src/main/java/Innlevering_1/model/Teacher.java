package Innlevering_1.model;

public class Teacher
{
    private int employeeID;
    private String available;
    private String fullName;

    public Teacher(String fullName, String available)
    {
        this.fullName = fullName;
        availableCheck(available);
    }

    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public String getAvailable() { return available; }

    public void setAvailable(String available)
    {
        availableCheck(available);
    }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getEmployeeID() { return employeeID; }

    private void availableCheck(String available)
    {
        // TODO: replace this in db with booleans (enum or int types)
        try
        {
            available.toLowerCase();
            if (available.equals("yes"))
                this.available = available;
            else if (available.equals("no"))
                this.available = available;
            else
                throw new IllegalArgumentException("Only valid entries for \"Available\" is \"yes/no\"");
        }
        catch ( IllegalArgumentException e ) { System.out.println(e.getMessage()); }
    }
}
