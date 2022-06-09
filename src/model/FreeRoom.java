package model;

/**
 * A Free room class for modeling free room instances
 *
 * @author Gideon Isa
 * February 04, 2022
 */

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, double roomPrice, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    /**
     * To give a description of the Free room
     * @return - String Results
     */
    @Override
    public String toString() {
        return "The room number is " + getRoomNumber() + " and the price is " + getRoomNumber() + "\n " +
                "and the room type is " + getRoomType();
    }
}
