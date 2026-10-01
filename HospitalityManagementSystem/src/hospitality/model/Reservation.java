package hospitality.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
        private int id;
        private int roomId;
        private int guestId;
        private LocalDate checkIn;
        private LocalDate checkOut;

        public Reservation(int id, int roomId, int guestId, LocalDate checkIn, LocalDate checkOut) {
                this.id = id;
                this.roomId = roomId;
                this.guestId = guestId;
                this.checkIn = checkIn;
                this.checkOut = checkOut;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getRoomId() {
                return roomId;
        }

        public int getGuestId() {
                return guestId;
        }

        public LocalDate getCheckIn() {
                return checkIn;
        }

        public LocalDate getCheckOut() {
                return checkOut;
        }

        public void setCheckIn(LocalDate checkIn) {
                this.checkIn = checkIn;
        }

        public void setCheckOut(LocalDate checkOut) {
                this.checkOut = checkOut;
        }

        public long getNights() {
                return ChronoUnit.DAYS.between(checkIn, checkOut);
        }
}
