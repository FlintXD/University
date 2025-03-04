public class HealingChest implements Actable {
    private final int healthRestore;
    private boolean isEmpty;

    public HealingChest(int healthRestore) {
        this.healthRestore = healthRestore;
        this.isEmpty = false;
    }

    @Override
    public ActResult act(Game game) {
        if (isEmpty) {
            System.out.println("Сундук пуст.");
            return ActResult.CONTINUE;
        }

        System.out.println("Вы нашли сундук! Ваше здоровье восстановлено на " + healthRestore + " единиц.");
        game.getPlayer().heal(healthRestore);
        isEmpty = true;
        return ActResult.CONTINUE;
    }

    @Override
    public String toString() {
        return isEmpty ? "Пустой сундук" : "Сундук с восстановлением здоровья";
    }
}