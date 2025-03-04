import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player;
    private final List<Room> rooms = new ArrayList<>();
    private Room currentRoom;

    public Game(Room startRoom) {
        this.player = new Player(100);
        this.currentRoom = startRoom;
        rooms.add(startRoom);
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public void start() {
        System.out.println("Добро пожаловать в игру!");

        while (true) {
            Room currentRoom = getCurrentRoom();
            ActResult result = currentRoom.act(this);

            if (result == ActResult.GAME_OVER) {
                System.out.println("Игра завершена.");
                break;
            }
        }
    }
}
