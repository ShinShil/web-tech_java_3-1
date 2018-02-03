package persistance.abstraction;

import persistance.models.Room;

public interface IRoomsDatabase {
    Room[] getAllRooms();
    void UpdateRoom(int id);
}
