package main;

import entities.*;
import services.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Data
        List<Restaurant> restaurants = new ArrayList<>();
        List<DeliveryPerson> deliveryPersons = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Order o = null;
        // Sample Restaurants & Food Items
        Restaurant r1 = new Restaurant(101, "Hari Om Dhaba");
        r1.addFoodItem(new FoodItem(1, "Panjabi Thali", 340));
        r1.addFoodItem(new FoodItem(2, "Pav Bhaji", 140));
        Restaurant r2 = new Restaurant(102, "Express Inn");
        r2.addFoodItem(new FoodItem(3, "Veg Biryani", 200));
        r2.addFoodItem(new FoodItem(4, "Paneer Butter Masala", 250));
        Restaurant r3 = new Restaurant(103, "Shree Krishna");
        r3.addFoodItem(new FoodItem(5, "Dosa", 120));
        r3.addFoodItem(new FoodItem(6, "Veg Biryani", 250));

        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);

        // Sample Customers
        customers.add(new Customer(1, "om", 1111111111L));
        customers.add(new Customer(2, "rohan", 2222222222L));
        customers.add(new Customer(3, "nilesh", 3333333333L));
        customers.add(new Customer(4, "ram", 4444444444L));
        customers.add(new Customer(5, "sham", 5555555555L));

        // Sample Delivery Persons
        deliveryPersons.add(new DeliveryPerson(1, "Manoj", 7087990078L));
        deliveryPersons.add(new DeliveryPerson(2, "Vikas", 9123456789L));
        deliveryPersons.add(new DeliveryPerson(3, "Suresh", 8001234567L));
        deliveryPersons.add(new DeliveryPerson(4, "Ramesh", 8765432109L));
        deliveryPersons.add(new DeliveryPerson(5, "Anil", 7890123456L));

        AdminService adminService = new AdminService(restaurants, deliveryPersons, orders);
        CustomerService customerService = new CustomerService(customers, restaurants, orders);

        while (true) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                while (true) {
                    System.out.println("\nAdmin Menu:");
                    System.out.println("1. Add Restaurant");
                    System.out.println("2. Add Food Item to Restaurant");
                    System.out.println("3. Remove Food Item from Restaurant");
                    System.out.println("4. View Restaurants and Menus");
                    System.out.println("5. View Orders");
                    System.out.println("6. Add Delivery Person and Show Delivery Persons");
                    System.out.println("7. Assign Delivery Person to Order");
                    System.out.println("8. Exit");
                    System.out.print("Choose an option: ");
                    int adminChoice = sc.nextInt();

                    if (adminChoice == 1) {
                        System.out.print("Enter Restaurant ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Restaurant Name: ");
                        String name = sc.nextLine();
                        adminService.addRestaurant(id, name);
                    } else if (adminChoice == 2) {
                        System.out.print("Enter Restaurant ID: ");
                        int rid = sc.nextInt();
                        System.out.print("Enter Food Item ID: ");
                        int fid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Food Item Name: ");
                        String fname = sc.nextLine();
                        System.out.print("Enter Food Item Price: ");
                        double price = sc.nextDouble();
                        adminService.addFoodItemToRestaurant(rid, new FoodItem(fid, fname, price));
                    } else if (adminChoice == 3) {
                        System.out.print("Enter Restaurant ID: ");
                        int rid = sc.nextInt();
                        System.out.print("Enter Food Item ID: ");
                        int fid = sc.nextInt();
                        adminService.removeFoodItemFromRestaurant(rid, fid);
                    } else if (adminChoice == 4) {
                        adminService.viewRestaurantsAndMenus();
                    } else if (adminChoice == 5) {
                        adminService.viewOrders();
                    } else if (adminChoice == 6) {
                        System.out.print("Enter Delivery Person ID: ");
                        int did = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Delivery Person Name: ");
                        String dname = sc.nextLine();
                        System.out.print("Enter Contact No.: ");
                        long contact = sc.nextLong();
                        adminService.addDeliveryPerson(did, dname, contact);
                        System.out.println(deliveryPersons);
                        
                    } else if (adminChoice == 7) {
                    	System.out.println(o);
                        System.out.print("Enter Order ID: ");
                        int oid = sc.nextInt();
                        System.out.print("Enter Delivery Person ID: ");
                        int did = sc.nextInt();
                        adminService.assignDeliveryPersonToOrder(oid, did);
                    } else if (adminChoice == 8) {
                        System.out.println("Exiting Admin Module");
                        break;
                    }
                }
            } 
            else if (choice == 2) {
                while (true) {
                    System.out.println("\nCustomer Menu:");
                    System.out.println("1. Add Customer");
                    System.out.println("2. View Food Items");
                    System.out.println("3. Add Food to Cart");
                    System.out.println("4. View Cart");
                    System.out.println("5. Place Order");
                    System.out.println("6. View Orders");
                    System.out.println("7. Exit");
                    System.out.print("Choose an option: ");
                    int custChoice = sc.nextInt();

                    if (custChoice == 1) {
                        System.out.print("Enter User ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Username: ");
                        String uname = sc.nextLine();
                        System.out.print("Enter Contact No.: ");
                        long contact = sc.nextLong();
                        customerService.addCustomer(id, uname, contact);
                    } else if (custChoice == 2) {
                        customerService.viewFoodItems();
                    } else if (custChoice == 3) {
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        System.out.print("Enter Restaurant ID: ");
                        int rid = sc.nextInt();
                        System.out.print("Enter Food Item ID: ");
                        int fid = sc.nextInt();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        customerService.addFoodToCart(cid, rid, fid, qty);
                    } else if (custChoice == 4) {
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        customerService.viewCart(cid);
                    } else if (custChoice == 5) {
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        customerService.placeOrder(cid);
                    } else if (custChoice == 6) {
                        System.out.print("Enter Customer ID: ");
                        int cid = sc.nextInt();
                        customerService.viewOrders(cid);
                    } else if (custChoice == 7) {
                        System.out.println("Exiting Customer Module");
                        break;
                    }
                }
            } 
            else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }
}
