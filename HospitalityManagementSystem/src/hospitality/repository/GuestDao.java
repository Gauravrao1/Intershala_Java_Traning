package hospitality.repository;

import hospitality.model.Guest;

public class GuestDao extends InMemoryCrudDao<Guest> {
        public GuestDao() {
                super(Guest::getId, Guest::setId);
        }
}
