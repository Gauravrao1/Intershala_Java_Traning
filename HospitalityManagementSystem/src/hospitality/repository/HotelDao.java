package hospitality.repository;

import hospitality.model.Hotel;

public class HotelDao extends InMemoryCrudDao<Hotel> {
        public HotelDao() {
                super(Hotel::getId, Hotel::setId);
        }
}
