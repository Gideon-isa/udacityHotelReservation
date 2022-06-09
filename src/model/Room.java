package model;

import java.util.Objects;

/**
 * A Room class for modeling instances of Room
 *
 * @author Gideon Isa
 * February 4, 2022
 */

public class Room implements IRoom{
    private final String roomNumber;
    private final Double roomPrice;
    private final RoomType roomType;

    public Room(String roomNumber, double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
    }

    /**
     * Providing access to the Room number
     * @return String Result
     */
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Provides access to the room price
     * @return Double - Result
     */
    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    /**
     * Provides access to the room type
     * @return RoomType - Result
     */
    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Provides access to the state of the room, whether free or not
     * @return boolean - Result
     */
    @Override
    public boolean isFree() {
        return false;
    }

    /**
     * To give a description of the room variables
     * @return - String Results
     */
    @Override
    public String toString() {
        return "Room :- " +
                "Room Number: " + roomNumber +
                ", Room Price Per Night: $" + roomPrice +
                ", RoomType: " + roomType + " Bed \n";
    }

    /**
     *This overrides the equals-method by comparing only the Room numbers
     * The room number is the unique key for distinguishing rooms
     * @param o - object to be compared with
     * @return - boolean - Result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    /**
     * To return an integer of the instead of the hashcode
     * @return - int - Result
     */
    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
