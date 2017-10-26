package EllJes16.model;

public class Room
{
    private String roomNumber;
    private String campus;
    private int capacity;

    public Room(String roomNumber, String campus, int capacity)
    {
        this.roomNumber = roomNumber;
        this.campus = campus;
        this.capacity = capacity;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
