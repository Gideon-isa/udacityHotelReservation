package service;

import UserInterface.MainMenu;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

/**
 * To Store, retrieve, and process all reservations
 *
 * @author Gideon Isa
 * February 04, 2022
 *
 */

public class ReservationService {

    // ReservationService fields
    private final Collection<IRoom> roomSet = new HashSet<>();
    private final Map<String, IRoom> mapOfRooms = new HashMap<>();
    private final Collection<Reservation> reservationSet = new HashSet<>();
    private final Collection<IRoom> allReservedRooms = new HashSet<>();
    private final Collection<IRoom> allAvailableRooms = new HashSet<>();
    private final Collection<Reservation> customerReservation = new HashSet<>();
    //private final List<String> reservedRoomNumber = new LinkedList<>();

    // Adding a static reference to make sure only one instance of this class is created
    public static ReservationService reservationServiceInstance;

    public ReservationService () {}

    /**
     * To Limit the instantiating of reservation service to be one throughout the programme
     *
     * @return ReservationService - Result
     */
    public static ReservationService getInstance() {
        if(reservationServiceInstance == null) {
            reservationServiceInstance = new ReservationService();
        }
        return reservationServiceInstance;
    }


    /**
     * To add a reserved room to a Collection
     *
     * @param room - The room reserved by a customer
     */
    public void addRoom(IRoom room) {
        roomSet.add(room);
        mapOfRooms.put(room.getRoomNumber(), room);
    }

    /**
     * To provide a quick loop of the collection of the rooms
     * @param roomId - The Room number of the reserved room
     * @return IRoom - Result
     */
    public IRoom getARoom(String roomId) {
        return mapOfRooms.get(roomId);
    }

    /**
     * To reserve a room
     *
     * @param customer - The Customer instance
     * @param room - The Room instance
     * @param checkInDate - The customer's check-in-date
     * @param checkOutDate - the Customer's check-out-date
     * @return Reservation - Results
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation newReservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationSet.add(newReservation);

        // storing the reserved room number
        //reservedRoomNumber.add(room.getRoomNumber());

        // casting the collection to contain rooms
        allReservedRooms.add(room);
        //displaying the new reservation
        System.out.println(newReservation);
        return newReservation;
    }

    /**
     * To display, find, and reserve  a room
     *
     * @param checkInDate - customer's check-in-date
     * @param checkOutDate - customer's check-out-date
     * @return IRoom Collection of available rooms
     */
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        allAvailableRooms.clear();

        for (IRoom room : roomSet) {
            if (!allReservedRooms.contains(room)) {
                allAvailableRooms.add(room);
            }
        }

        for (Reservation reservation : reservationSet) {

                if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckInDate())) {
                        allAvailableRooms.add(reservation.getRoom());
                    }
                if (checkOutDate.after(reservation.getCheckOutDate()) && checkInDate.after(reservation.getCheckOutDate())) {
                        allAvailableRooms.add(reservation.getRoom());
                }
        }

        // To display room(s) if it will be available after seven days
        if (allAvailableRooms.isEmpty()) {

            for (Reservation reservation : reservationSet) {
                // Creating an instance of Date
                Calendar firstCalender = Calendar.getInstance();
                Calendar secondCalender = Calendar.getInstance();

                //Adding seven days to the current date
                firstCalender.setTime(checkInDate);
                firstCalender.add(Calendar.DAY_OF_MONTH, 7);
                Date newCheckInDate = firstCalender.getTime();

                secondCalender.setTime(checkOutDate);
                secondCalender.add(Calendar.DAY_OF_MONTH, 7);
                Date newCheckOutDate = secondCalender.getTime();

//                if (newCheckInDate.after(reservation.getCheckOutDate())) {
//                    System.out.println("The Room below will be available after " + reservation.getCheckOutDate());
//                    System.out.println(reservation.getRoom());
//                }

                if (newCheckInDate.before(reservation.getCheckInDate()) && newCheckOutDate.before(reservation.getCheckInDate())) {
                    System.out.println("The Room below is currently reserved for now but will be available after " + reservation.getCheckOutDate());
                    allAvailableRooms.add(reservation.getRoom());
                    System.out.println(reservation.getRoom());
                }
                if (newCheckOutDate.after(reservation.getCheckOutDate()) && newCheckInDate.after(reservation.getCheckOutDate())) {
                    System.out.println("The Room below is currently reserved for now but will be available after " + reservation.getCheckOutDate());
                    allAvailableRooms.add(reservation.getRoom());
                    System.out.println(reservation.getRoom());
                }

            }

            System.out.println("Please make your reservations in advance.");
            System.out.println("To reserve the room(s) listed above in advance, set your check-in date further than the said date above.");
            System.out.println("Thanks. \n");

            MainMenu.startMainMenu();

        }

        return allAvailableRooms;

    }

    /**
     * To store customer's reservation
     *
     * @param customer - Customer's information
     * @return Reservation os reservation
     */
    public Collection<Reservation> getCustomersReservation(Customer customer) {
        for (Reservation reservation : reservationSet) {
            if(reservation.getCustomer().equals(customer)) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }


    /**
     * To display all reserved rooms
     */
    public void printAllReservation() {
        if(reservationSet.isEmpty()) {
            displayMessage();
        }else {
            for(Reservation reservation : reservationSet) {
                System.out.println(reservation);
            }
        }
    }

    // A method with a default access modifier
    void displayMessage() {
        System.out.println("No reservations.");
    }

    /**
     * To retrieve all the rooms
     *
     * @return Collection of rooms
     */
    public Collection<IRoom> getAllRooms() {
        return roomSet;
    }



}
