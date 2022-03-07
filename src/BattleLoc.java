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

        System.out.println();
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

                        if (this.getAward() == "Food")
                            this.getPlayer().getInventory().setFood(true);
                        if (this.getAward() == "Firewood")
                            this.getPlayer().getInventory().setFirewood(true);
                        if (this.getAward() == "Water")
                            this.getPlayer().getInventory().setWater(true);

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

            if (this.getMonster().getId() == 4
                    && this.getMonster().getHealth() < this.getPlayer().getGameChar().getHealth()) {
                int x = random.nextInt(100) + 1;

                if (x >= 1 && x <= 15) {
                    x = random.nextInt(100) + 1;

                    if (x >= 1 && x <= 20) {
                        System.out.println("You found rifle");

                        if (this.getPlayer().getInventory().getArmour().getId() < 3) {
                            this.getPlayer().getInventory().setWeapon(new Weapon(3, "Rifle", 7, 45));
                        }
                    } else if (x >= 21 && x <= 50) {
                        System.out.println("You found sword");

                        if (this.getPlayer().getInventory().getArmour().getId() < 2) {
                            this.getPlayer().getInventory().setWeapon(new Weapon(2, "Sword", 3, 35));
                        }
                    } else {
                        System.out.println("You found pistol");

                        if (this.getPlayer().getInventory().getArmour().getId() < 1) {
                            this.getPlayer().getInventory().setWeapon(new Weapon(1, "Pistol", 2, 25));
                        }
                    }
                } else if (x >= 16 && x <= 30) {
                    x = random.nextInt(100) + 1;

                    if (x >= 1 && x <= 20) {
                        System.out.println("You found heavy armour");

                        if (this.getPlayer().getInventory().getArmour().getId() < 3) {
                            this.getPlayer().getInventory().setArmour(new Armour(3, "Heavy", 5, 40));
                        }
                    } else if (x >= 21 && x <= 50) {
                        System.out.println("You found medium armour");

                        if (this.getPlayer().getInventory().getArmour().getId() < 2) {
                            this.getPlayer().getInventory().setArmour(new Armour(2, "Medium", 3, 25));
                        }
                    } else {
                        System.out.println("You found light armour");

                        if (this.getPlayer().getInventory().getArmour().getId() < 1) {
                            this.getPlayer().getInventory().setArmour(new Armour(1, "Light", 1, 15));
                        }
                    }
                } else if (x >= 31 && x <= 55) {
                    x = random.nextInt(100) + 1;

                    if (x >= 1 && x <= 20) {
                        System.out.println("You found 10 gold!");
                        this.getPlayer().getGameChar().setMoney(this.getPlayer().getGameChar().getMoney() + 10);
                    } else if (x >= 21 && x <= 50) {
                        System.out.println("You found 5 gold!");
                        this.getPlayer().getGameChar().setMoney(this.getPlayer().getGameChar().getMoney() + 5);
                    } else {
                        System.out.println("You found 1 gold!");
                        this.getPlayer().getGameChar().setMoney(this.getPlayer().getGameChar().getMoney() + 1);
                    }
                } else {
                    System.out.println("Unlucky! No items dropped from this monster");
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getGameChar().getHealth()) {
                this.getPlayer().getGameChar()
                        .setMoney(this.getPlayer().getGameChar().getMoney() + this.getMonster().getGold());
            } else {
                return false;
            }
        }

        return true;
    }

    public void afterHits() {
        System.out.println();
        System.out.println("Your health: " + this.getPlayer().getGameChar().getHealth());
        System.out.println("Health of the monster: " + this.getMonster().getHealth());

        if (this.getMonster().getHealth() < 1) {
            System.out.println("The monster is dead");
        }
    }

    public void playerStatus() {
        System.out.println();
        System.out.println("######        \t#######      \t#######     \t######");
        System.out
                .println("Block: "
                        + this.getPlayer().getGameChar().getInventory().getArmour().getBlock() + "\tDamage: "
                        + this.getPlayer().getGameChar().getTotalDamage() + "\tHealth: "
                        + this.getPlayer().getGameChar().getHealth() + "\tMoney: "
                        + this.getPlayer().getGameChar().getMoney());
    }

    public void monsterStatus() {
        System.out.println();
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
