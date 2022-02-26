package com.gafilianog;

// TODO: viewProducts and pick and checkout and notes (expdate, size, version)


import com.gafilianog.customer.Customer;
import com.gafilianog.product.Cloth;
import com.gafilianog.product.Food;
import com.gafilianog.product.Product;
import com.gafilianog.product.Tech;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    HashMap<String, Customer> customerMap = new HashMap<>();
    ArrayList<Product> productLists = new ArrayList<>();

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
//        mainMenu("gafi");
        loginMenu();
    }

    private void loginMenu() {
        int choice;

        do {
            Utilities.narStyleCLS();
            Utilities.banner();
            System.out.print(
                    "Welcome to Padie Shop\n" +
                    "1. Login\n" +
                    "2. Register\n" +
                    "0. Exit\n" +
                    ">> "
            );

            choice = Utilities.checkIntInput();

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else if (choice != 0) {
                System.out.print("Only choose between 0-2!");
                Utilities.scan.nextLine();
            }
        } while (choice != 0);
        Utilities.exit();
    }

    private void login() {
        String username, password;
        boolean verified = false;

        do {
            Utilities.narStyleCLS();
            System.out.println("-----Login-----");
            System.out.print("Username: ");
            username = Utilities.scan.nextLine();

            System.out.print("Password: ");
            password = Utilities.scan.nextLine();

            if (customerMap.containsKey(username) && password.equals(customerMap.get(username).getPassword()))
                verified = true;
            else if (username.equals("admin") && password.equals("admin123")) {
                adminMenu();
                return;
            } else {
                System.out.print("User tidak teregistrasi dalam aplikasi");
                Utilities.scan.nextLine();
            }
        } while (!verified);
        System.out.print(
                "\nLogin berhasil\n" +
                "Press ENTER to continue...");
        Utilities.scan.nextLine();
        mainMenu(username);
    }

    private void register() {
        String username, fullName, email, password;
        boolean isOK;

        Utilities.narStyleCLS();
        System.out.println("-----Register-----");

        do {
            isOK = false;
            System.out.print("Input Username : ");
            username = Utilities.scan.nextLine();

            if (username.length() < 3 || username.length() > 16)
                System.out.println("Username must contains 3-16 characters!\n");
            else
                isOK = true;
        } while (!isOK);

        do {
            isOK = false;
            System.out.print("Input Full Name: ");
            fullName = Utilities.scan.nextLine();

            if (fullName.length() < 3 || fullName.length() > 16)
                System.out.println("Full name must contains 3-16 characters!\n");
            else if (!fullName.matches("^[\\p{L} .'-]+$"))
                System.out.println("Only input alphabet!\n");
            else
                isOK = true;
        } while (!isOK);

        do {
            isOK = false;
            System.out.print("Input Email    : ");
            email = Utilities.scan.nextLine();

            if (email.length() < 5 || email.length() > 30)
                System.out.println("Email must contains 5-30 characters!\n");
            else if (!email.contains("@"))
                System.out.println("Email must contains '@' symbol!\n");
            else if (!email.endsWith(".com") && !email.endsWith(".net"))
                System.out.println("Email must ends with '.com' or '.net'!\n");
            else
                isOK = true;
        } while (!isOK);

        do {
            isOK = false;
            System.out.print("Input Password : ");
            password = Utilities.scan.nextLine();

            if (password.length() < 8 || password.length() > 40)
                System.out.println("Password must contains 8-40 characters!\n");
            else if (!password.matches("[A-Za-z0-9]+"))
                System.out.println("Password must be alphanumeric!\n");
            else
                isOK = true;
        } while (!isOK);

        customerMap.put(username, new Customer(username, fullName, email, password));
        System.out.print(
                "\nAccount created successfully\n" +
                "Press ENTER to back to login menu...");
        Utilities.scan.nextLine();
    }

    private void mainMenu(String username) {
        int choice;

        while (true) {
            Utilities.narStyleCLS();
            Utilities.banner();

            System.out.println("Hello " + customerMap.get(username).getFullName());
            System.out.print("1. GO SHOP NOW!!!\n" + //CTA
                    "2. View purchase history\n" +
                    "3. Add balance\n" +
                    "4. Check balance\n" +
                    "5. Logout\n" +
                    "0. Exit\n" +
                    ">> ");

            choice = Utilities.checkIntInput();

            switch (choice) {
                case 0:
                    Utilities.exit();

                case 1:
                    purchasingMenu();
                    break;

                case 2:
                    System.out.println("lol");
                    break;

                case 3:
                    addBalance(username);
                    break;

                case 4:
                    System.out.print("Your balance: Rp" + customerMap.get(username).getBalance());
                    Utilities.scan.nextLine();
                    break;

                case 5:
                    if (logoutConfirmation())
                        return;
                    else
                        break;

                default:
                    System.out.print("Only choose between 0-5!");
                    Utilities.scan.nextLine();
                    break;
            }
        }
    }

    private void addBalance(String username) {
        boolean isOk = false;
        int addBalance;

        do {
            System.out.print("\nEnter the amount you want to add (input '0' to go back): Rp");
            addBalance = Utilities.checkIntInput();

            if (addBalance == 0) {
                return;
            } else if (addBalance < 0) {
                System.out.print("Nominal uang tidak valid.");
                Utilities.scan.nextLine();
            } else
                isOk = true;
        } while (!isOk);

        customerMap.get(username).setBalance(addBalance);
        System.out.println("Your new balance: Rp" + customerMap.get(username).getBalance());
        System.out.print("Press ENTER to continue...");
        Utilities.scan.nextLine();
    }

    private boolean logoutConfirmation() {
        char confirmation;

        System.out.print("You need to LOGIN again if you want to shopping.\n" +
                "Are you sure want to logout? (Y/N): ");
        confirmation = Utilities.scan.next().toLowerCase().charAt(0);

        return confirmation == 'y';
    }

    private void adminMenu() {
        int choice, price;
        String name;

        while (true) {
            Utilities.narStyleCLS();
            System.out.print("-----Admin-----\n" +
                    "1. Add Food product\n" +
                    "2. Add Cloth product\n" +
                    "3. Add Tech product\n" +
                    "0. Logout\n" +
                    ">> ");
            choice = Utilities.checkIntInput();
            if (choice == 0)
                return;

            name = isNameProdOk();
            price = isPriceProdOk();

            if (choice == 1) {
                // TODO: EXP Date
                productLists.add(new Food(name + " [F]", price * 1.1, "Date"));
            } else if (choice == 2) {
                String size;
                boolean isOK = false;

                do {
                    System.out.print("Size (S/M/L/XL): ");
                    size = Utilities.scan.nextLine();
                    if (size.equals("S") || size.equals("M") || size.equals("L") || size.equals("XL"))
                        isOK = true;
                    else
                        System.out.println("Try again!");
                } while (!isOK);

                productLists.add(new Cloth(name + " [C]", price * 1.25, size));
            } else if (choice == 3) {
                String ver;
                ver = Utilities.scan.nextLine();
                productLists.add(new Tech(name + " [T]", price * 1.3, ver));
            } else {
                System.out.print("Stupid admin, you've been trained for this.");
                Utilities.scan.nextLine();
                continue;
            }

            System.out.println("Successfully added");
            System.out.print("Press ENTER to continue...");
            Utilities.scan.nextLine();
        }
    }

    private String isNameProdOk() {
        String name;
        boolean isOK;

        do {
            isOK = false;
            System.out.print("Name : ");
            name = Utilities.scan.nextLine();

            if (name.length() >= 3 && name.length() <= 16)
                isOK = true;
            else
                System.out.println("Must contains 3-16 characters!");
        } while (!isOK);

        return name;
    }

    private int isPriceProdOk() {
        int price;
        boolean isOK;

        do {
            isOK = false;
            System.out.print("Price: Rp");
            price = Utilities.checkIntInput();

            if (price >= 1000)
                isOK = true;
            else
                System.out.println("Must be at least Rp1000!");
        } while (!isOK);

        return price;
    }

    private void purchasingMenu() {
        int choice;

        do {
            Utilities.narStyleCLS();
            Utilities.banner();

            System.out.print("-----Shopping-----\n" +
                    "1. View all products\n" +
                    "2. Checkout\n" +
                    "0. Back to main menu\n" +
                    ">> ");

            choice = Utilities.checkIntInput();

            if (choice == 1) {
                viewProducts();
            } else if (choice == 2) {
                checkOut();
            } else if (choice != 0) {
                System.out.print("Only choose between 0-2!");
                Utilities.scan.nextLine();
            }
        } while (choice != 0);
    }

    private void viewProducts() {
        System.out.println("| No. |          Product Name          |     Price     |");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < productLists.size(); i++) {
            System.out.printf("| %-3d | %-32s | %-15f |\n", i + 1, productLists.get(i).getName(), productLists.get(i).getPrice());
        }
    }

    private void checkOut() {}
}

// TODO: 1. Fitur purchasing
// TODO: 2. Fitur history pembelian