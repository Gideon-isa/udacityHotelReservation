package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

/**
 * An intermediary between the admin and the Service Class
 *
 * @author Gideon Isa
 */
public class AdminResource {

    private static final ReservationService reservationService = ReservationService.getInstance();
    private static final CustomerService customerService = CustomerService.getInstance();

    // providing static reference
    public static AdminResource adminResourceInstance;

    public AdminResource() {}

    /**
     * To Limit the instantiating of Admin Resource to be only one throughout the programme.
     *
     * @return Admin Reseource
     */
    public static AdminResource getInstance() {
        if(adminResourceInstance == null) {
            adminResourceInstance = new AdminResource();
        }
        return adminResourceInstance;
    }


    /**
     * Calls the Customer Service's getCustomer method to get customer by their email
     * to the Admin User.
     *
     * @param email - Customer's email
     * @return customer - Result
     */
    public Customer getCustomer(String email) {
        return CustomerService.getInstance().getCustomer(email);
    }


    /**
     * Calls the Reservation service's addRoom to add rooms to the collection
     * from the admin.
     *
     * @param rooms - room
     */
    public void addRoom(List<IRoom> rooms) {
        for(IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }


    /**
     * Calls the Reservation service's getAllRoom method
     * to see all rooms.
     * @return
     */
    public Collection<IRoom> getAllRooms() {
        if(reservationService.getAllRooms().isEmpty()) {
            System.out.println("No rooms have been added on the system.\n" +
                    "Please add available rooms on this system. \n");
        }
       return reservationService.getAllRooms();
    }


    /**
     * Calls the Customer Service's getAllCustomer method to see all customers
     *
     * @return - Collection of customers
     */
    public Collection<Customer> getAllCustomers() {
        return CustomerService.customerServiceInstance.getAllCustomer();
    }


    /**
     * Calls the Reservation Service's printAllReservation to see all reservation made.
     */
    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}
