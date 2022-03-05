public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You are in the safe house. Your health has been restored.");
        return true;
    }

}
