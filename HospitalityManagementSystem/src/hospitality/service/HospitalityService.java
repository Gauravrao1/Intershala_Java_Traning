package hospitality.service;

import hospitality.model.Guest;
import hospitality.model.Hotel;
import hospitality.model.Reservation;
import hospitality.model.Room;
import hospitality.repository.GuestDao;
import hospitality.repository.HotelDao;
import hospitality.repository.ReservationDao;
import hospitality.repository.RoomDao;
import java.time.LocalDate;
import java.util.List;

public class HospitalityService {
        private final HotelDao hotelDao = new HotelDao();
        private final RoomDao roomDao = new RoomDao();
        private final GuestDao guestDao = new GuestDao();
        private final ReservationDao reservationDao = new ReservationDao();

        public HospitalityService() {
                hotelDao.create(new Hotel(0, "Sunrise Palace", "Delhi", "WiFi, Pool, Breakfast"));
                roomDao.create(new Room(0, 1, "101", "Deluxe", 3500, "Available"));
                guestDao.create(new Guest(0, "Aarav Sharma", "aarav@example.com", "9876543210"));
        }

        public List<Hotel> hotels() {
                return hotelDao.findAll();
        }

        public List<Room> rooms() {
                return roomDao.findAll();
        }

        public List<Guest> guests() {
                return guestDao.findAll();
        }

        public List<Reservation> reservations() {
                return reservationDao.findAll();
        }

        public Hotel addHotel(String name, String location, String amenities) {
                return hotelDao.create(new Hotel(0, name, location, amenities));
        }

        public Room addRoom(int hotelId, String number, String type, double price) {
                return roomDao.create(new Room(0, hotelId, number, type, price, "Available"));
        }

        public Guest addGuest(String name, String email, String phone) {
                return guestDao.create(new Guest(0, name, email, phone));
        }

        public Reservation reserve(int roomId, int guestId, LocalDate checkIn, LocalDate checkOut) {
                if (!checkOut.isAfter(checkIn))
                        throw new IllegalArgumentException("Check-out must be after check-in.");
                if (roomDao.findById(roomId).isEmpty())
                        throw new IllegalArgumentException("Room does not exist.");
                if (guestDao.findById(guestId).isEmpty())
                        throw new IllegalArgumentException("Guest does not exist.");
                if (!isAvailable(roomId, checkIn, checkOut))
                        throw new IllegalArgumentException("Room is already reserved for those dates.");
                return reservationDao.create(new Reservation(0, roomId, guestId, checkIn, checkOut));
        }

        public boolean isAvailable(int roomId, LocalDate checkIn, LocalDate checkOut) {
                return reservationDao.findAll().stream().noneMatch(reservation -> reservation.getRoomId() == roomId
                                && checkIn.isBefore(reservation.getCheckOut())
                                && checkOut.isAfter(reservation.getCheckIn()));
        }

        public double calculateCost(Reservation reservation) {
                return roomDao.findById(reservation.getRoomId()).orElseThrow().getPrice() * reservation.getNights();
        }

        public void deleteHotel(int id) {
                hotelDao.delete(id);
        }

        public void deleteRoom(int id) {
                roomDao.delete(id);
        }

        public void deleteGuest(int id) {
                guestDao.delete(id);
        }

        public void deleteReservation(int id) {
                reservationDao.delete(id);
        }

        public String hotelName(int id) {
                return hotelDao.findById(id).map(Hotel::getName).orElse("Unknown");
        }

        public String guestName(int id) {
                return guestDao.findById(id).map(Guest::getName).orElse("Unknown");
        }
}
