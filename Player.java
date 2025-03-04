public class Player {
    private int health;
    private int coins;

    public Player(int health) {
        this.health = health;
        this.coins = 0;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public int getCoins() {
        return coins;
    }

    public void heal(int amount) {
        health += amount;
        System.out.println("Ваше здоровье восстановлено на " + amount + " очков.");
    }
}