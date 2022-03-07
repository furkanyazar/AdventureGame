public class Inventory {

    private Armour armour;
    private Weapon weapon;
    private boolean food;
    private boolean firewood;
    private boolean water;

    public Inventory() {
        this.armour = new Armour(0, "Rag", 0, 0);
        this.weapon = new Weapon(0, "Thump", 0, 0);
        this.food = false;
        this.firewood = false;
        this.water = false;
    }

    public Armour getArmour() {
        return armour;
    }

    public void setArmour(Armour armour) {
        this.armour = armour;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

}
