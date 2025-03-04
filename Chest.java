public class Chest implements Actable {
    private final int coins;
    private final int healAmount;
    private boolean isEmpty;

    public Chest(int coins, int healAmount) {
        this.coins = coins;
        this.healAmount = healAmount;
        this.isEmpty = false;
    }

    @Override
    public ActResult act(Game game) {
        if (isEmpty) {
            System.out.println("Сундук пуст.");
            return ActResult.CONTINUE;
        }

        System.out.println("Вы нашли сундук!");
        if (coins > 0) {
            System.out.println("Вы получили " + coins + " монет!");
            game.getPlayer().addCoins(coins);
        }
        if (healAmount > 0) {
            System.out.println("Ваше здоровье восстановлено на " + healAmount + " очков.");
            game.getPlayer().heal(healAmount);
        }

        isEmpty = true;
        return ActResult.CONTINUE;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        return isEmpty ? "Пустой сундук" : "Сундук с наградой";
    }
}