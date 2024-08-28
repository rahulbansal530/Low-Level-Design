import java.sql.Date;
import java.util.List;

public class HotelManagementSystem {
    public static void main(String[] args) {
        
    }
}

class Hotel {
    Integer id;
    String name;
    Location location;
    List<Room> rooms;
}

class Location {
    Integer pin;
    String street;
    String area;
    String city;
    String state;
    String country;
}

class Room {
    Integer roomNumber;
    RoomStyle roomStyle;
    RoomStatus roomStatus;
    double price;
    List<RoomKey> roomkeys;
    List<HouseKeepingLog> houseKeepingLogs;
}

class RoomKey {
    int id;
    String barCode;
    Date issuedAt;
    Boolean isActive;
    Boolean isMaster;
}

class HouseKeepingLog {
    String description;
    Date startDate;
    int duration;
    HouseKeeper houseKeeper;
}

class Person {
    Integer id;
    String name;
    String phone;
}

class HouseKeeper {

}

enum RoomStyle {
    NORMAL, PREMIUM;
}

enum RoomStatus {
    AVAILABLE, OCCUPIED, NOT_AVAILABLE, MAINTAINENCE;
}

