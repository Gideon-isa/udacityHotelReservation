package model;

/**
 * An Interface for the Room and the end User
 *
 * @author Gideon Isa
 * February 04, 2022
 */

public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();
    public boolean isFree();
}
