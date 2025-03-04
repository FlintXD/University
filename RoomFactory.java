import java.util.Random;

public class RoomFactory {
    private static final Random random = new Random();
    private static int roomCounter = 0; // Счетчик комнат

    // Метод для генерации начальной комнаты
    public static Room generateStartRoom() {
        Room startRoom = new Room("Начальная комната");
        startRoom.addObject(new Door(null, null)); // Единственная дверь для перехода в следующую комнату
        return startRoom;
    }

    // Метод для генерации обычных комнат
    public static Room generate(Room previousRoom) {
        roomCounter++; // Увеличиваем номер комнаты

        String description = "Комната " + roomCounter;
        Room room = new Room(description);

        // Добавление двери в предыдущую комнату
        if (previousRoom != null) {
            Door previousDoor = new Door(previousRoom, room);
            room.addObject(previousDoor);
        }

        // Добавление двери в следующую комнату
        Door nextDoor = new Door(room, null);
        room.addObject(nextDoor);

        // Добавление объектов (сундук или монстр)
        boolean chestAdded = false;
        for (int i = 0; i < random.nextInt(2) + 1; i++) {
            int chance = random.nextInt(100);
            if (chance < 30 && !chestAdded) {
                room.addObject(new Chest(random.nextInt(20) + 10, 0));
                chestAdded = true;
            } else if (chance < 40) {
                room.addObject(new HealingChest(30));
            } else if (chance < 70) {
                room.addObject(new Monster("Монстр " + (i + 1), random.nextInt(20) + 10));
            }
        }

        // Добавление босса в 10-й комнате
        if (roomCounter == 10) {
            room.addObject(new Monster("Босс", 1000));
        }

        return room;
    }
}
