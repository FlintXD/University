public class EmptyChest implements Actable {
    @Override
    public ActResult act(Game game) {
        System.out.println("Это пустой сундук.");
        return ActResult.CONTINUE;
    }

    @Override
    public String toString() {
        return "Пустой сундук";
    }
}