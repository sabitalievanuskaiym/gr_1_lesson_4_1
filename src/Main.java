import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 270, 280, 450, 360, 200, 450, 5000};
    public static int[] heroesDamage = {25, 20, 15, 17, 30, 20, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic,", "Thor", "Berserk", "Lucky", "Medic"};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        Thor();
        Berserk();
        Lucky();
        bossHits();
        heroesHit();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefenceType == null) {
            defence = "No defence";
        } else {
            defence = bossDefenceType;
        }*/
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; defence: " + (bossDefenceType == null ? "No defence" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(6) + 2; // 2,3,4,5,6,7
                    hit = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }

            }
        }
    }

    public static void Thor() {
        Random random = new Random();
        boolean ran = random.nextBoolean();
        if (ran) {
            System.out.println("Босс оглушен");
            bossDamage = 0;
        } else {
            bossDamage = 50;
        }

    }

    public static void Berserk() {
        Random random = new Random();
        int ber = random.nextInt(4) + 1;
        if (heroesHealth[4] > 0 && bossHealth > 0) {
            heroesHealth[4] = bossDamage / 2;
        }
    }

    public static void Lucky() {
        Random random = new Random();
        int lucky = random.nextInt(5) + 1;
        if (heroesHealth[5] < 0) {
            heroesHealth[5] = 0;
        }
        switch (lucky) {
            case 1:
                bossDamage = 50;
            case 2:
                bossDamage = 0;
                System.out.println("Lucky");
            case 3:
                bossDamage = 50;
            case 4:
                bossDamage = 50;
        }
    }

    public static void Medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random random = new Random();
            int medic = random.nextInt(heroesAttackType.length);
            if (i == 6) {
                continue;
            }
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0) {
                heroesHealth[i] = heroesHealth[i] + 70;

            }
            System.out.println("MedicHero" + heroesAttackType[medic]);

        }

    }

}

