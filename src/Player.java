import java.util.Scanner;

public class Player {

    private String name;
    private GameChar gameChar;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public void selectChar() {
        GameChar[] gameChars = { new Samurai(), new Archer(), new Knight() };

        System.out.println("###  \t##########        \t#######  \t#######   \t######");
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
                System.err.println("Wrong choice");
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
                    System.err.println("Wrong choice");
                    break;
            }

            System.out.println(gameChar.getName() + " has been chosen");
        }
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

}
