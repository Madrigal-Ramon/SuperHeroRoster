import java.util.Scanner;
import java.util.Arrays;

public class SuperheroRoster {
    private static String[] heroNames;
    private static String[] abilities;
    private static int[] powerLevels;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize arrays with default heroes
        heroNames = new String[]{"Spider-Man", "Iron Man", "Black Widow", "Thor", "Hulk"};
        abilities = new String[]{"Web-slinging", "Flying and high-tech armor", 
                               "Stealth and combat", "God of Thunder", "Super strength"};
        powerLevels = new int[]{85, 90, 80, 95, 99};

        boolean running = true;
        while (running) {
            System.out.println("\n=== Marvel Superhero Roster Manager ===");
            System.out.println("1. View all heroes");
            System.out.println("2. Search for a hero");
            System.out.println("3. Add a new hero");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayAllHeroes();
                    break;
                case 2:
                    searchHeroByUserInput();
                    break;
                case 3:
                    addNewHero();
                    sortHeroesByPower(); // Keep roster sorted
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayAllHeroes() {
        System.out.println("\n=== Current Superhero Roster ===");
        System.out.printf("%-15s | %-25s | %-5s\n", "Name", "Ability", "Power");
        System.out.println("-----------------------------------------------");
        
        for (int i = 0; i < heroNames.length; i++) {
            System.out.printf("%-15s | %-25s | %-5d\n", 
                           heroNames[i], 
                           abilities[i], 
                           powerLevels[i]);
        }

        double averagePower = calculateAveragePower();
        System.out.printf("\nAverage Power Level: %.2f\n", averagePower);
    }

    private static void searchHeroByUserInput() {
        System.out.print("\nEnter hero name to search: ");
        String target = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < heroNames.length; i++) {
            if (heroNames[i].equalsIgnoreCase(target)) {
                System.out.println("\nHero found!");
                System.out.println("Name: " + heroNames[i]);
                System.out.println("Ability: " + abilities[i]);
                System.out.println("Power Level: " + powerLevels[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nHero '" + target + "' not found in the roster.");
        }
    }

    private static void addNewHero() {
        System.out.println("\n=== Add New Superhero ===");
        
        System.out.print("Enter hero name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter hero ability: ");
        String ability = scanner.nextLine();
        
        System.out.print("Enter power level (1-100): ");
        int power = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Expand arrays to accommodate new hero
        heroNames = Arrays.copyOf(heroNames, heroNames.length + 1);
        abilities = Arrays.copyOf(abilities, abilities.length + 1);
        powerLevels = Arrays.copyOf(powerLevels, powerLevels.length + 1);

        // Add new hero to the end
        int lastIndex = heroNames.length - 1;
        heroNames[lastIndex] = name;
        abilities[lastIndex] = ability;
        powerLevels[lastIndex] = power;

        System.out.println("\nNew hero added successfully!");
    }

    private static double calculateAveragePower() {
        int sum = 0;
        for (int power : powerLevels) {
            sum += power;
        }
        return (double) sum / powerLevels.length;
    }

    private static void sortHeroesByPower() {
        // Using bubble sort for simplicity
        for (int i = 0; i < powerLevels.length - 1; i++) {
            for (int j = 0; j < powerLevels.length - i - 1; j++) {
                if (powerLevels[j] < powerLevels[j + 1]) {
                    // Swap power levels
                    int tempPower = powerLevels[j];
                    powerLevels[j] = powerLevels[j + 1];
                    powerLevels[j + 1] = tempPower;

                    // Swap names
                    String tempName = heroNames[j];
                    heroNames[j] = heroNames[j + 1];
                    heroNames[j + 1] = tempName;

                    // Swap abilities
                    String tempAbility = abilities[j];
                    abilities[j] = abilities[j + 1];
                    abilities[j + 1] = tempAbility;
                }
            }
        }
    }
}
