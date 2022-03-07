import java.util.Scanner;

public class Player {

    private String name;
    private GameChar gameChar;

    private Location location = null;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public void selectChar() {
        GameChar[] gameChars = { new Samurai(), new Archer(), new Knight() };

        System.out.println();
        System.out.println("###  \t#####        \t#######  \t#######   \t######");
        for (GameChar gameChar : gameChars) {
            System.out.println("Id: " + gameChar.getId() + "\tName: " + gameChar.getName() + "\tDamage: "
                    + gameChar.getDamage() + "\tHealth: " + gameChar.getHealth() + "\tMoney: " + gameChar.getMoney());
        }

        int selectedChar = 0;
        boolean choiceControl = false;

        while (!choiceControl) {
            System.out.print("Choose a character to start the game: ");

            try {
                selectedChar = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            switch (selectedChar) {
                case 1:
                    gameChar = gameChars[0];
                    choiceControl = true;
                    break;
                case 2:
                    gameChar = gameChars[1];
                    choiceControl = true;
                    break;
                case 3:
                    gameChar = gameChars[2];
                    choiceControl = true;
                    break;
                default:
                    System.err.print("Wrong choice! ");
                    continue;
            }

            System.out.println(gameChar.getName() + " has been chosen");
        }
    }

    public boolean selectLoc() {
        printInfo();

        System.out.println();
        System.out.println("###  \t#####           \t############");
        System.out.println(
                "Id: 1\tName: Safe House\tDescription: This is a safe home for you. There are no enemies here.");
        System.out.println("Id: 2\tName: Tool Store\tDescription: Shop to buy weapons or armour");
        System.out.println("Id: 3\tName: Cave\t\tDescription: Be careful! The monster may come but you can earn food");
        System.out.println(
                "Id: 4\tName: Forest\t\tDescription: Be careful! The monster may come but you can earn firewood");
        System.out
                .println("Id: 5\tName: River\t\tDescription: Be careful! The monster may come but you can earn water");
        System.out.println("##### For exit enter 9 #####");

        int selectedLoc = 0;
        boolean choiceControl = false;

        while (!choiceControl) {
            System.out.print("Choose the location you want to go to: ");

            try {
                selectedLoc = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            switch (selectedLoc) {
                case 1:
                    location = new SafeHouse(this);
                    choiceControl = true;
                    break;
                case 2:
                    location = new ToolStore(this);
                    choiceControl = true;
                    break;
                case 3:
                    if (this.getInventory().isFood()) {
                        System.out.println("You have already cleared this area");
                        break;
                    }

                    location = new Cave(this);
                    choiceControl = true;
                    break;
                case 4:
                    if (this.getInventory().isFirewood()) {
                        System.out.println("You have already cleared this area");
                        break;
                    }

                    location = new Forest(this);
                    choiceControl = true;
                    break;
                case 5:
                    if (this.getInventory().isWater()) {
                        System.out.println("You have already cleared this area");
                        break;
                    }

                    location = new River(this);
                    choiceControl = true;
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.err.print("Wrong choice! ");
                    continue;
            }
        }

        return location.onLocation();
    }

    public void printInfo() {
        System.out.println();
        System.out.println("######      \t#######        \t######        \t#######      \t#######     \t######");
        System.out.println("Armour: " + this.getGameChar().getInventory().getArmour().getName() + "\tWeapon: "
                + this.getGameChar().getInventory().getWeapon().getName() + "\tBlock: "
                + this.getGameChar().getInventory().getArmour().getBlock() + "\tDamage: "
                + this.getGameChar().getTotalDamage() + "\tHealth: " + this.getGameChar().getHealth() + "\tMoney: "
                + this.getGameChar().getMoney());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameChar getGameChar() {
        return gameChar;
    }

    public void setGameChar(GameChar gameChar) {
        this.gameChar = gameChar;
    }

    public Inventory getInventory() {
        return this.getGameChar().getInventory();
    }

}
