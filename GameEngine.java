import java.util.Scanner;

public class GameEngine {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Room startRoom = RoomFactory.generateStartRoom();
        Game game = new Game(startRoom); // Передаем стартовую комнату в конструктор Game

        game.start();

        System.out.println("Игра окончена!");
        System.out.println("Вы собрали " + game.getPlayer().getCoins() + " монет.");
    }

    public static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Введите число:");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
