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
