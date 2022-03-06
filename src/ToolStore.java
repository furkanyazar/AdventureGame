public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Welcome to the tool store!");
        System.out.println("1 - Weapons");
        System.out.println("2 - Armours");
        System.out.println("9 - Exit");

        int selectedOpt = 0;
        boolean choiceControl = false;

        while (!choiceControl) {
            System.out.print("Choose an option: ");

            try {
                selectedOpt = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            switch (selectedOpt) {
                case 1:
                    selectWeapon();
                    choiceControl = true;
                    break;
                case 2:
                    selectArmour();
                    choiceControl = true;
                    break;
                case 9:
                    choiceControl = true;
                    break;
                default:
                    System.err.print("Wrong choice! ");
                    continue;
            }
        }

        return true;
    }

    public void selectWeapon() {
        System.out.println("###  \t#####        \t#######       \t######");

        for (Weapon weapon : Weapon.getWeapons()) {
            System.out.println("Id: " + weapon.getId() + "\tName: " + weapon.getName() + "\tDamage: "
                    + weapon.getDamage() + "\tPrice: " + weapon.getPrice());
        }

        System.out.println("##### For exit enter 9 #####");

        int selectedId = 0;

        while (true) {
            System.out.print("Choose a weapon: ");

            try {
                selectedId = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            if (selectedId == 9)
                break;

            Weapon selectedWeapon = Weapon.getWeaponById(selectedId);

            if (selectedWeapon.getPrice() > this.getPlayer().getGameChar().getMoney())
                System.err.println("You don't have enough money");
            else {
                System.out.println(selectedWeapon.getName() + " was bought");
                this.getPlayer().getGameChar()
                        .setMoney(this.getPlayer().getGameChar().getMoney() - selectedWeapon.getPrice());
                System.out.println("Remaining money: " + this.getPlayer().getGameChar().getMoney());
                this.getPlayer().getGameChar().getInventory().setWeapon(selectedWeapon);
                break;
            }
        }
    }

    public void selectArmour() {
        System.out.println("###  \t#####        \t######       \t######");

        for (Armour armour : Armour.getArmours()) {
            System.out.println("Id: " + armour.getId() + "\tName: " + armour.getName() + "\tBlock: "
                    + armour.getBlock() + "\tPrice: " + armour.getPrice());
        }

        System.out.println("##### For exit enter 9 #####");

        int selectedId = 0;

        while (true) {
            System.out.print("Choose a armour: ");

            try {
                selectedId = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.err.print("Wrong choice! ");
                continue;
            }

            if (selectedId == 9)
                break;

            Armour selectedArmour = Armour.getArmourById(selectedId);

            if (selectedArmour.getPrice() > this.getPlayer().getGameChar().getMoney())
                System.err.println("You don't have enough money");
            else {
                System.out.println(selectedArmour.getName() + " was bought");
                this.getPlayer().getGameChar()
                        .setMoney(this.getPlayer().getGameChar().getMoney() - selectedArmour.getPrice());
                System.out.println("Remaining money: " + this.getPlayer().getGameChar().getMoney());
                this.getPlayer().getGameChar().getInventory().setArmour(selectedArmour);
                break;
            }
        }
    }

}
