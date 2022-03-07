import java.util.Random;

public class BattleLoc extends Location {

    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int numberOfMonster = this.numberOfMonster();

        System.out.println("You're in the " + this.getName().toLowerCase() + " now");
        System.out.println("Be careful! " + numberOfMonster + " " + this.getMonster().getName().toLowerCase()
                + (numberOfMonster == 1 ? " lives here" : "s live here"));
        System.out.println("###  \t########");
        System.out.println("Id: 1\tAction: Fight");
        System.out.println("Id: 2\tAction: Run");

        int selectedProcess = 0;
        boolean choiceControl = false;

        while (!choiceControl) {
            System.out.print("Choose an action: ");

            try {
                selectedProcess = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            switch (selectedProcess) {
                case 1:
                    System.out.println("Action fight selected");
                    // to be coded...
                    choiceControl = true;
                    break;
                case 2:
                    choiceControl = true;
                    break;
                default:
                    System.err.print("Wrong choice! ");
                    continue;
            }
        }

        return true;
    }

    public int numberOfMonster() {
        Random random = new Random();

        return random.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

}
