import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        String playerName = "";

        System.out.println("Welcome to the adventure game!");
        System.out.print("Enter name: ");

        while (playerName.length() < 3) {
            playerName = scanner.nextLine();
            if (playerName.length() < 3)
                System.out.print("Length must be at least 3 characters! Enter name: ");
        }

        Player player = new Player(playerName);

        System.out.println("Hi " + player.getName() + ", welcome to this dark and foggy island!");
        System.out.println("Everything that happens here is real!");

        player.selectChar();

        while (player.selectLoc());

        System.err.println("Game over!");
    }

}
