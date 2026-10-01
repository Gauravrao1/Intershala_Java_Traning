package hospitality.repository;

import hospitality.model.Reservation;

public class ReservationDao extends InMemoryCrudDao<Reservation> {
        public ReservationDao() {
                super(Reservation::getId, Reservation::setId);
        }
}
