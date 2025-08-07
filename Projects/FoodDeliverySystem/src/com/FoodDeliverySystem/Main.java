package com.FoodDeliverySystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Online Food Delivery System ===");
            System.out.println("1. Admin Panel");
            System.out.println("2. Customer Panel");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    adminMenu(adminService, sc);
                    break;
                case 2:
                    customerMenu(customerService, sc);
                    break;
                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu(AdminService adminService, Scanner sc) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Update Restaurant");
            System.out.println("3. View All Restaurants");
            System.out.println("4. Add Food Item");
            System.out.println("5. Update Food Item");
            System.out.println("6. Remove Food Item");
            System.out.println("7. View All Food Items");
            System.out.println("8. View Restaurants & Menus");
            System.out.println("9. Add Delivery Person");
            System.out.println("10. Assign Delivery Person to Order");
            System.out.println("11. View All Orders");
            System.out.println("12. Back");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (ch) {
                case 1:
                    System.out.print("Enter Restaurant ID: ");
                    int rId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Restaurant Name: ");
                    String rName = sc.nextLine();
                    adminService.addRestaurant(rId, rName);
                    break;
                case 2:
                    System.out.print("Enter Restaurant ID: ");
                    int updId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    adminService.updateRestaurant(updId, newName);
                    break;
                case 3:
                    adminService.viewAllRestaurants();
                    break;
                case 4:
                    System.out.print("Enter Food Item ID: ");
                    int fId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Food Name: ");
                    String fName = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double fPrice = sc.nextDouble();
                    System.out.print("Enter Restaurant ID: ");
                    int frId = sc.nextInt();
                    adminService.addFoodItem(fId, fName, fPrice, frId);
                    break;
                case 5:
                    System.out.print("Enter Food ID: ");
                    int upfId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String nName = sc.nextLine();
                    System.out.print("Enter New Price: ");
                    double nPrice = sc.nextDouble();
                    adminService.updateFoodItem(upfId, nName, nPrice);
                    break;
                case 6:
                    System.out.print("Enter Food ID to Remove: ");
                    int rfId = sc.nextInt();
                    adminService.removeFoodItem(rfId);
                    break;
                case 7:
                    adminService.viewAllFoodItems();
                    break;
                case 8:
                    adminService.viewRestaurantsAndMenus();
                    break;
                case 9:
                    System.out.print("Enter Delivery Person ID: ");
                    int dId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String dName = sc.nextLine();
                    System.out.print("Enter Contact No: ");
                    long contact = sc.nextLong();
                    adminService.addDeliveryPerson(dId, dName, contact);
                    break;
                case 10:
                    System.out.print("Enter Order ID: ");
                    int ordId = sc.nextInt();
                    System.out.print("Enter Delivery Person ID: ");
                    int delId = sc.nextInt();
                    adminService.assignDeliveryPerson(ordId, delId);
                    break;
                case 11:
                    adminService.viewAllOrders();
                    break;
                case 12:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void customerMenu(CustomerService customerService, Scanner sc) {
        System.out.print("Enter Your Customer ID: ");
        int custId = sc.nextInt();
        sc.nextLine();

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Register");
            System.out.println("2. Search Food");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View My Orders");
            System.out.println("7. Cancel Order");
            System.out.println("8. Leave Review");
            System.out.println("9. Back");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (ch) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Contact No: ");
                    long contact = sc.nextLong();
                    customerService.addCustomer(custId, name, contact);
                    break;
                case 2:
                    System.out.print("Enter food name or keyword: ");
                    String keyword = sc.nextLine();
                    customerService.searchFoodItems(keyword);
                    break;
                case 3:
                    System.out.print("Enter Food Item ID: ");
                    int foodId = sc.nextInt();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    customerService.addToCart(custId, foodId, qty);
                    break;
                case 4:
                    customerService.viewCart(custId);
                    break;
                case 5:
                    System.out.print("Enter Delivery Address: ");
                    String addr = sc.nextLine();
                    customerService.placeOrder(custId, addr);
                    break;
                case 6:
                    customerService.viewOrders(custId);
                    break;
                case 7:
                    System.out.print("Enter Order ID to Cancel: ");
                    int oId = sc.nextInt();
                    customerService.cancelOrder(oId, custId);
                    break;
                case 8:
                    System.out.print("Enter Food Item ID: ");
                    int revFid = sc.nextInt();
                    System.out.print("Enter Rating (1-5): ");
                    int rating = sc.nextInt();
                    sc.nextLine(); // consume
                    System.out.print("Enter Comment: ");
                    String comment = sc.nextLine();
                    customerService.leaveReview(custId, revFid, rating, comment);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
