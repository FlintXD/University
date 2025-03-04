import java.util.Random;

public class Monster implements Actable {
    private final String name;
    private int health;

    public Monster(String name, int health) {
        this.name = name;
        this.health = health;
    }

    @Override
    public ActResult act(Game game) {
        if (!isAlive()) {
            System.out.println("Это тело мертвого " + name + ".");
            return ActResult.CONTINUE;
        }

        System.out.println("Вы встретили " + name + "! Начинается бой.");
        while (health > 0 && game.getPlayer().isAlive()) {
            System.out.println("Ваше здоровье: " + game.getPlayer().getHealth());
            System.out.println("Здоровье монстра: " + health);
            System.out.println("1: Атаковать");
            System.out.println("2: Рискнуть");
            System.out.println("0: Бежать");

            int choice = GameEngine.readInt();

            if (choice == 1) {
                health -= 10;
                game.getPlayer().decreaseHealth(5);
                System.out.println("Вы нанесли 10 урона.");
            } else if (choice == 2) {
                if (Math.random() < 0.1) {
                    health = 0;
                    System.out.println("Вы нанесли критический урон!");
                } else {
                    game.getPlayer().decreaseHealth(20);
                    System.out.println("Вы промахнулись и получили 20 урона.");
                }
            } else if (choice == 0) {
                System.out.println("Вы сбежали от " + name + "!");
                return ActResult.CONTINUE;
            }
        }

        if (!game.getPlayer().isAlive()) {
            System.out.println("Вы погибли!");
            return ActResult.GAME_OVER;
        }

        Random random = new Random();
        int coins = random.nextInt(10) + 5;
        game.getPlayer().addCoins(coins);
        System.out.println("Вы победили " + name + " и получили " + coins + " монет.");

        // Если побежден босс
        if (name.equals("Босс")) {
            System.out.println("Поздравляем! Вы победили босса и завершили игру!");
            return ActResult.GAME_OVER;
        }

        return ActResult.CONTINUE;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return isAlive() ? "Монстр: " + name : "Мёртвый монстр";
    }
}
