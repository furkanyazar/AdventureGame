import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        String playerName = "";

        System.out.println("Welcome to the adventure game!");
        
        while (playerName.length() < 3) {
            System.out.print("Enter name: ");
            playerName = scanner.nextLine();
        }

        Player player = new Player(playerName);

        System.out.println("Hi " + player.getName() + ", welcome to this dark and foggy island!");
        System.out.println("Everything that happens here is real!");

        player.selectChar();
    }

}
