package model;

import java.util.Date;

/**
 * A class to model the customer's reservation
 *
 * @author Gideon Isa
 * February 04, 2022
 */

public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    /**
     * Provide access to the customer details of the reservation
     * @return - Customer Results
     */
    public Customer getCustomer() {
        return customer;
    }
    /**
     * Provide access to the room details of customer's reservation
     * @return - Room Result
     */
    public IRoom getRoom() {
        return room;
    }
    /**
     * Provide access to the checkInDate of customer's reservation
     * @return - Date Results
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Provide access to the checkOutDate of the customer's reservation
     * @return - Date Results
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Provide a description of reservation
     * @return - String Results
     */
    @Override
    public String toString() {
        return "Reservation:- " +
                "Customer: " + customer +
                "Room: " + room +
                "CheckInDate: " + checkInDate + "\n" +
                "CheckOutDate: " + checkOutDate + "\n";
    }
}

