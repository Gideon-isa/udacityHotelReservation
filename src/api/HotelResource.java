package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

/**
 * To serve as an interface between the reservation services and the User
 *
 * @author Gideon Isa
 * February 04, 2022
 */

public class HotelResource {

    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    Collection<Reservation> reservationForCustomer = new HashSet<>();


    // Providing a static reference
    public static HotelResource hotelResourceInstance;

    public HotelResource() {}

    /**
     * To limit the instantiating of the Hotel Resource to be one throughout the programme
     *
     * @return HotelResource - Result
     */
    public static HotelResource getInstance() {
        if(hotelResourceInstance == null) {
            hotelResourceInstance = new HotelResource();
        }
        return hotelResourceInstance;
    }



    /**
     * Calls the Customer Service's get customer method to retrieve the customer
     * by using the customer email.
     *
     * @param email - Customer's email
     * @return Customer - Result
     */
    public Customer getCustomer(String email) {
        return CustomerService.getInstance().getCustomer(email);
    }



    /**
     * Calls the Customer Service's addCustomer method
     * to create a new customer.
     *
     * @param email - Customer's email
     * @param firstName - Customer's first name
     * @param lastName - customer's last name
     */
    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }



    /**
     * Calls the Reservation service getARoom method to get room
     *
     * @param roomNumber - Added Room number
     * @return IRoom - Result
     */
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }



    /**
     * Calls the Reservation Service's reserveARoom method to reserve a room
     * by using the Customer Service's getCustomer method to get the customer.
     *
     * @param customerEmail - Customer's email
     * @param room - reserved room
     * @param checkInDate - Customer's checking-in-date
     * @param checkOutDate - Customer's checking-out-date
     * @return Reservation of collection - Result
     */
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }



    /**
     * Calls customer and the reservation method of the customer and reservation class
     * to get customer's reservation.
     *
     * @param customerEmail - Customer's email
     * @return Reservation Collection - Result
     */
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
       Customer customer = getCustomer(customerEmail);
       reservationForCustomer = reservationService.getCustomersReservation(customer);
       return reservationForCustomer;

    }



    /**
     * Calls the Reservation Service's findRooms method
     * to find all the available rooms
     *
     * @param checkIn - customer's check-in-date
     * @param checkOut - customer's check-out-date
     * @return Collection of available rooms
     */
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }
}
