package hospitality.repository;

import hospitality.model.Room;

public class RoomDao extends InMemoryCrudDao<Room> {
        public RoomDao() {
                super(Room::getId, Room::setId);
        }
}
