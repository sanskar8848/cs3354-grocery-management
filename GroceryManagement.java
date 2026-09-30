import java.util.Scanner;

/**
 * GroceryManagement is a simple console-based grocery inventory system.
 * <p>
 * It manages item data using three parallel arrays (names, prices, and
 * stock counts), where the same index across all three arrays refers to
 * the same grocery item. The program provides a menu-driven interface
 * that allows the user to view the current inventory and restock existing
 * items.
 * </p>
 *
 * @author CS3354 Team
 */
public class GroceryManagement {

    /**
     * Prints the current inventory to the console.
     * <p>
     * Iterates through the parallel arrays and prints only the slots
     * that are not empty (i.e., where the name at that index is not
     * {@code null}).
     * </p>
     *
     * @param names  array of item names
     * @param prices array of item prices, parallel to {@code names}
     * @param stocks array of item stock counts, parallel to {@code names}
     */
    public static void printInventory(String[] names, double[] prices, int[] stocks) {
        System.out.println("\n----- Current Inventory -----");
        boolean isEmpty = true;

        for (int i = 0; i < names.length; i++) {
            if (names[i] != null) {
                isEmpty = false;
                System.out.printf("[%d] %-15s $%-8.2f Stock: %d%n",
                        i, names[i], prices[i], stocks[i]);
            } else {
                // Slot is empty; skip printing it.
                continue;
            }
        }

        if (isEmpty) {
            System.out.println("Inventory is empty.");
        }
        System.out.println("------------------------------\n");
    }

    /**
     * Restocks an existing item by adding the given amount to its stock.
     * <p>
     * Searches the {@code names} array for {@code target}. If found, the
     * corresponding index in the {@code stocks} array is increased by
     * {@code amount}. If the item is not found after checking the entire
     * array, a message is printed to notify the user.
     * </p>
     *
     * @param names  array of item names
     * @param stocks array of item stock counts, parallel to {@code names}
     * @param target the name of the item to restock
     * @param amount the quantity to add to the item's current stock
     */
    public static void restockItem(String[] names, int[] stocks, String target, int amount) {
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equalsIgnoreCase(target)) {
                stocks[i] += amount;
                System.out.println(target + " restocked. New stock: " + stocks[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    /**
     * Adds a new item to the first available (null) slot in the arrays.
     * This is a small helper used by the menu so the demo data can grow;
     * it is not one of the three required methods but supports Task 3's
     * menu-driven interaction.
     *
     * @param names  array of item names
     * @param prices array of item prices, parallel to {@code names}
     * @param stocks array of item stock counts, parallel to {@code names}
     * @param name   name of the new item
     * @param price  price of the new item
     * @param stock  initial stock of the new item
     */
    private static void addItem(String[] names, double[] prices, int[] stocks,
                                 String name, double price, int stock) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null) {
                names[i] = name;
                prices[i] = price;
                stocks[i] = stock;
                System.out.println(name + " added to inventory.");
                return;
            }
        }
        System.out.println("Inventory is full. Cannot add new item.");
    }

    /**
     * Program entry point. Sets up the parallel arrays, seeds a few sample
     * items, and runs a menu-driven loop allowing the user to view the
     * inventory, restock an item, add a new item, or exit the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String[] itemNames = new String[10];
        double[] itemPrices = new double[10];
        int[] itemStocks = new int[10];

        // Seed with a few sample items so the menu has data to work with.
        itemNames[0] = "Apples";
        itemPrices[0] = 1.50;
        itemStocks[0] = 20;

        itemNames[1] = "Bread";
        itemPrices[1] = 2.75;
        itemStocks[1] = 15;

        itemNames[2] = "Milk";
        itemPrices[2] = 3.20;
        itemStocks[2] = 10;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("===== Grocery Management Menu =====");
            System.out.println("1. View Inventory");
            System.out.println("2. Restock Item");
            System.out.println("3. Add New Item");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String choiceInput = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    printInventory(itemNames, itemPrices, itemStocks);
                    break;

                case 2:
                    System.out.print("Enter item name to restock: ");
                    String target = scanner.nextLine().trim();
                    System.out.print("Enter amount to add: ");
                    int amount;
                    try {
                        amount = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount.\n");
                        break;
                    }
                    restockItem(itemNames, itemStocks, target, amount);
                    break;
            
                // Case 3: Add a new item to the inventory.
                case 3:
                    System.out.print("Enter new item name: ");
                    String newName = scanner.nextLine().trim();

                    if (newName.isEmpty()) {
                        System.out.println("Item name cannot be empty.");
                        break;
                    }

                    double newPrice;
                    int newStock;

                    try {
                        System.out.print("Enter price: ");
                        newPrice = Double.parseDouble(scanner.nextLine().trim());

                        System.out.print("Enter initial stock: ");
                        newStock = Integer.parseInt(scanner.nextLine().trim());

                        if (newPrice < 0 || newStock < 0) {
                            System.out.println("Price and stock cannot be negative.");
                            break;
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price or stock value.");
                        break;
                    }

                    addItem(itemNames, itemPrices, itemStocks, newName, newPrice, newStock);
                    break;

                // Case 4: Exit the program and close the scanner.
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1-4.");
            }
            System.out.println();
        }
    }
}