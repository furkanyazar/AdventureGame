public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println();

        if (this.getPlayer().getInventory().isFirewood() && this.getPlayer().getInventory().isFood()
                && this.getPlayer().getInventory().isWater()) {
                    System.out.println("Congratulations. You finished the game");
                    System.exit(0);
        }

        System.out.println("You are in the safe house. Your health has been restored");
        this.getPlayer().getGameChar().setHealth(this.getPlayer().getGameChar().getDefaultHealth());

        return true;
    }

}
