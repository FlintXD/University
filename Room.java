import java.util.ArrayList;
import java.util.List;

public class Room implements Actable {
    private final String description;
    private final List<Actable> objects = new ArrayList<>();

    public Room(String description) {
        this.description = description;
    }

    public void addObject(Actable object) {
        objects.add(object);
    }

    public String getDescription() {
        return description;
    }

    public boolean hasAliveMonster() {
        return objects.stream()
                .filter(obj -> obj instanceof Monster)
                .anyMatch(obj -> ((Monster) obj).isAlive());
    }

    @Override
    public ActResult act(Game game) {
        if (hasAliveMonster()) {
            System.out.println("В комнате есть живой монстр! Вы должны сразиться с ним.");
        }

        System.out.println("Вы находитесь: " + description);
        System.out.println("Доступные действия:");
        for (int i = 0; i < objects.size(); i++) {
            Actable object = objects.get(i);
            // Если это монстр, показываем его в списке независимо от его состояния
            if (object instanceof Monster) {
                System.out.println((i + 1) + ": " + object);
            } else if (!hasAliveMonster()) {
                // Остальные объекты доступны только если монстр мёртв
                System.out.println((i + 1) + ": " + object);
            }
        }
        System.out.println("0: Выйти из игры");

        int choice = GameEngine.readInt();
        if (choice == 0) {
            return ActResult.GAME_OVER;
        } else if (choice > 0 && choice <= objects.size()) {
            Actable selectedObject = objects.get(choice - 1);
            // Если выбран монстр или монстров больше нет, разрешаем действие
            if (selectedObject instanceof Monster || !hasAliveMonster()) {
                return selectedObject.act(game);
            } else {
                System.out.println("Вы не можете взаимодействовать с этим объектом, пока монстр жив!");
            }
        } else {
            System.out.println("Неверный выбор.");
        }

        return ActResult.CONTINUE;
    }
}
