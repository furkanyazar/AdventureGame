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
        System.out.println("###  \t#######");
        System.out.println("Id: 1\tAction: Fight");
        System.out.println("Id: 2\tAction: Run");

        int selectedAction = 0;
        boolean choiceControl = false;

        while (!choiceControl) {
            System.out.print("Choose an action: ");

            try {
                selectedAction = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            switch (selectedAction) {
                case 1:
                    if (fight(numberOfMonster)) {
                        System.out.println("All monsters are defeated");
                        return true;
                    }
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

        if (this.getPlayer().getGameChar().getHealth() < 0)
            return false;

        return true;
    }

    public boolean fight(int numberOfMonster) {
        for (int i = 0; i < numberOfMonster; i++) {
            this.getMonster().setHealth(this.getMonster().getDefaultHealth());

            playerStatus();
            monsterStatus();

            while (this.getPlayer().getGameChar().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("###  \t#######");
                System.out.println("Id: 1\tAction: Hit");
                System.out.println("Id: 2\tAction: Run");

                int selectedAction = 0;
                boolean choiceControl = false;

                while (!choiceControl) {
                    System.out.print("Choose an action: ");

                    try {
                        selectedAction = Integer.parseInt(scanner.nextLine());
                    } catch (Exception e) {
                        System.err.print("Wrong choice! ");
                        continue;
                    }

                    if (selectedAction == 1) {
                        System.out.println("You hit!");
                        this.getMonster().setHealth(
                                this.getMonster().getHealth() - this.getPlayer().getGameChar().getTotalDamage());
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println(this.getMonster().getName() + " hit!");
                            int monsterDamage = this.getMonster().getDamage()
                                    - this.getPlayer().getGameChar().getInventory().getArmour().getBlock();
                            if (monsterDamage < 0)
                                monsterDamage = 0;
                            this.getPlayer().getGameChar()
                                    .setHealth(this.getPlayer().getGameChar().getHealth() - monsterDamage);
                        }
                        afterHits();

                        choiceControl = true;
                    } else if (selectedAction == 2) {
                        return false;
                    } else {
                        System.err.print("Wrong choice! ");
                    }
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getGameChar().getHealth()) {
                this.getPlayer().getGameChar()
                        .setMoney(this.getPlayer().getGameChar().getMoney() + this.getMonster().getGold());
            } else {
                return false;
            }
        }

        return false;
    }

    public void afterHits() {
        System.out.println("Your health: " + this.getPlayer().getGameChar().getHealth());
        System.out.println("Health of the monster: " + this.getMonster().getHealth());

        if (this.getMonster().getHealth() < 1) {
            System.out.println("The monster is dead");
        }
    }

    public void playerStatus() {
        System.out.println("######        \t#######      \t#######     \t######");
        System.out
                .println("Block: "
                        + this.getPlayer().getGameChar().getInventory().getArmour().getBlock() + "\tDamage: "
                        + this.getPlayer().getGameChar().getTotalDamage() + "\tHealth: "
                        + this.getPlayer().getGameChar().getHealth() + "\tMoney: "
                        + this.getPlayer().getGameChar().getMoney());
    }

    public void monsterStatus() {
        System.out.println("########      \t#######      \t#######        \t#####");
        System.out.println("Monster: " + this.getMonster().getName() + "\tDamage: " + this.getMonster().getDamage()
                + "\tHealth: " + this.getMonster().getHealth()
                + "\tGold: " + this.getMonster().getGold());
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
