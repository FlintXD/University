public class Door implements Actable {
    private Room room1, room2;

    public Door(Room room1, Room room2) {
        this.room1 = room1;
        this.room2 = room2;
    }

    @Override
    public ActResult act(Game game) {
        Room nextRoom = (game.getCurrentRoom() == room1) ? room2 : room1;

        if (nextRoom == null) {
            nextRoom = RoomFactory.generate(game.getCurrentRoom());
            if (game.getCurrentRoom() == room1) {
                room2 = nextRoom;
            } else {
                room1 = nextRoom;
            }
        }

        game.setCurrentRoom(nextRoom); // Переключение текущей комнаты
        return ActResult.NEXT_ROOM;
    }

    @Override
    public String toString() {
        return (room1 == null || room2 == null) ? "Дверь в следующую комнату" : "Дверь в предыдущую комнату";
    }
}
