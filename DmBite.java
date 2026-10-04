import java.util.Scanner;

public class Main {

    // Food names
    static String[] foods = {
        "Burger",
        "Fries",
        "Chicken Meal",
        "Iced Coffee"
    };

    // Food prices
    static double[] prices = {
        99.00,
        59.00,
        129.00,
        79.00
    };

    // Quantity of each food ordered
    static int[] quantity = new int[4];

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        System.out.println("=================================");
        System.out.println("           WELCOME TO DMBITE");
        System.out.println("        Fast Food. Easy Ordering.");
        System.out.println("=================================");

        do {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. View Food Menu");
            System.out.println("2. Order Food");
            System.out.println("3. View My Order");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.println("===============================");

            try {

                System.out.print("Enter choice: ");
                choice = input.nextInt();

                switch (choice) {

                    case 1:
                        showFoods();
                        break;

                    case 2:
                        orderFood();
                        break;

                    case 3:
                        showOrder();
                        break;

                    case 4:
                        checkout();
                        break;

                    case 5:
                        System.out.println(
                            "\nThank you for using DMBite!"
                        );
                        break;

                    default:
                        System.out.println(
                            "Invalid choice. Please choose 1-5."
                        );
                }

            } catch (Exception e) {

                System.out.println(
                    "Invalid input. Please enter a number."
                );

                input.nextLine();

                choice = 0;

            } finally {

                System.out.println(
                    "-------------------------------"
                );
            }

        } while (choice != 5);

        input.close();
    }

    // Shows the food menu
    public static void showFoods() {

        System.out.println("\n========== FOOD MENU ==========");

        for (int i = 0; i < foods.length; i++) {

            System.out.printf(
                "%d. %s - PHP %.2f%n",
                i + 1,
                foods[i],
                prices[i]
            );
        }

        System.out.println("===============================");
    }

    // Allows the customer to order multiple foods
    public static void orderFood() {

        String again = "Y";

        do {

            showFoods();

            try {

                System.out.print("Choose food number: ");
                int food = input.nextInt();

                if (food < 1 || food > foods.length) {

                    System.out.println(
                        "Invalid food number."
                    );

                    continue;
                }

                System.out.print("Enter quantity: ");
                int amount = input.nextInt();

                if (amount <= 0) {

                    System.out.println(
                        "Quantity must be greater than 0."
                    );

                    continue;
                }

                quantity[food - 1] += amount;

                System.out.println(
                    amount + " " +
                    foods[food - 1] +
                    " added!"
                );

                System.out.print(
                    "Add another food? (Y/N): "
                );

                again = input.next();

            } catch (Exception e) {

                System.out.println(
                    "Please enter numbers only."
                );

                input.nextLine();

                again = "Y";

            } finally {

                System.out.println(
                    "-------------------------------"
                );
            }

        } while (again.equalsIgnoreCase("Y"));
    }

    // Shows the customer's order
    public static void showOrder() {

        double total = 0;
        boolean hasOrder = false;

        System.out.println("\n========== MY ORDER ==========");

        for (int i = 0; i < foods.length; i++) {

            if (quantity[i] > 0) {

                double itemTotal =
                    prices[i] * quantity[i];

                System.out.printf(
                    "%s x%d = PHP %.2f%n",
                    foods[i],
                    quantity[i],
                    itemTotal
                );

                total += itemTotal;
                hasOrder = true;
            }
        }

        if (!hasOrder) {

            System.out.println(
                "Your order is empty."
            );

        } else {

            System.out.println(
                "-------------------------------"
            );

            System.out.printf(
                "Subtotal: PHP %.2f%n",
                total
            );
        }
    }

    // Checkout
    public static void checkout() {

        double total = 0;

        // Calculate total
        for (int i = 0; i < foods.length; i++) {

            total += prices[i] * quantity[i];
        }

        if (total == 0) {

            System.out.println(
                "\nYou have no order yet."
            );

            return;
        }

        System.out.println(
            "\n========== CHECKOUT =========="
        );

        showOrder();

        System.out.print(
            "\nAre you a student? (Y/N): "
        );

        String student = input.next();

        double discount = 0;

        if (student.equalsIgnoreCase("Y")) {

            discount = total * 0.10;

            System.out.println(
                "10% student discount applied!"
            );

        } else if (student.equalsIgnoreCase("N")) {

            System.out.println(
                "No discount applied."
            );

        } else {

            System.out.println(
                "Invalid answer. No discount applied."
            );
        }

        double finalTotal = total - discount;

        System.out.printf(
            "Total: PHP %.2f%n",
            finalTotal
        );

        double payment;

        while (true) {

            try {

                System.out.print(
                    "Enter payment: PHP "
                );

                payment = input.nextDouble();

                if (payment < finalTotal) {

                    System.out.println(
                        "Not enough money. Try again."
                    );

                    continue;
                }

                break;

            } catch (Exception e) {

                System.out.println(
                    "Please enter a valid amount."
                );

                input.nextLine();
            }
        }

        double change = payment - finalTotal;

        System.out.printf(
            "Change: PHP %.2f%n",
            change
        );

        System.out.println(
            "Thank you for ordering from DMBite!"
        );
    }
}
