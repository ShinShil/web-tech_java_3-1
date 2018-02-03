package persistance.implementation;

import business.configuration.AuthProvider;
import business.configuration.PhysicalDatabaseProvider;
import database.IFileDatabase;
import persistance.abstraction.IRoomsDatabase;
import persistance.models.Room;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class Rooms implements IRoomsDatabase {
    private IFileDatabase database = PhysicalDatabaseProvider.roomsDatabase;
    @Override
    public Room[] getAllRooms() {
        Object[] tmp = new Object[0];
        try {
            tmp = database.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Arrays.copyOf(Arrays.stream(Arrays.copyOf(tmp, tmp.length, Room[].class)).sorted((room1, room2) -> {
            return room1.number > room2.number
                    ? 1
                    : room1.number == room2.number ? 0 : -1;
        }).toArray(), tmp.length, Room[].class);
    }

    @Override
    public void UpdateRoom(int id) {
        Room[] users = getAllRooms();
        Optional<Room> roomOptional = Arrays.stream(users).filter(room -> room.id == id).findFirst();
        if(roomOptional.isPresent()) {
            Room oldRoom = roomOptional.get();
            Room newRoom = new Room();
            newRoom.booked = oldRoom.booked == 1 ? 0 : 1;
            newRoom.number = oldRoom.number;
            newRoom.userId = newRoom.booked == 1 ? AuthProvider.authManager.getAuthUser().id : 0;
            try {
                database.update(oldRoom, newRoom);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
