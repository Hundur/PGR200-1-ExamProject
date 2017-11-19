package Innlevering_1.model;

/**
 * Class for object room
 *
 * @Author Jesper Dahl Ellingsen
 */
public class Room
{
    private String roomNumber;
    private String campus;
    private int capacity;

    /**
     * Creates an object of type Room
     *
     * @param roomNumber The number of the room
     * @param campus The campus the room is located at
     * @param capacity The capacity of the room
     */
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
