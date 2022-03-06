public class Inventory {

    private Armour armour;
    private Weapon weapon;

    public Inventory() {
        this.armour = new Armour(0, "Rag", 0, 0);
        this.weapon = new Weapon(0, "Thump", 0, 0);
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

}
