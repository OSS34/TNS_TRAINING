package services;

import entities.*;

import java.util.*;

public class CustomerService {
    private List<Customer> customers;
    private List<Restaurant> restaurants;
    private List<Order> orders;
    private int orderCounter = 1;

    public CustomerService(List<Customer> customers, List<Restaurant> restaurants, List<Order> orders) {
        this.customers = customers;
        this.restaurants = restaurants;
        this.orders = orders;
    }

    public void addCustomer(int id, String name, long contact) {
        customers.add(new Customer(id, name, contact));
        System.out.println("Customer created successfully!");
    }

    public void viewFoodItems() {
        System.out.println("Restaurants and Menus:");
        for (Restaurant r : restaurants) {
            System.out.println("Restaurant ID: " + r.getId() + ", Name: " + r.getName());
            for (FoodItem f : r.getMenu()) {
                System.out.println("- Food Item ID: " + f.getId() + ", Name: " + f.getName() + ", Price: Rs. " + f.getPrice());
            }
        }
    }

    public void addFoodToCart(int customerId, int restaurantId, int foodId, int quantity) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurantId) {
                for (FoodItem f : r.getMenu()) {
                    if (f.getId() == foodId) {
                        customer.getCart().addItem(f, quantity);
                        System.out.println("Food item added to cart!");
                        return;
                    }
                }
            }
        }
        System.out.println("Food item not found!");
    }

    public void viewCart(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            System.out.println(customer.getCart());
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void placeOrder(int customerId) {
        Customer customer = getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        if (customer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        Order order = new Order(orderCounter++, customer);
        for (Map.Entry<FoodItem, Integer> entry : customer.getCart().getItems().entrySet()) {
            order.addItem(entry.getKey(), entry.getValue());
        }
        orders.add(order);
        customer.getCart().getItems().clear();
        System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId());
    }

    public void viewOrders(int customerId) {
        System.out.println("Orders:");
        for (Order o : orders) {
            if (o.getCustomer().getUserId() == customerId) {
                System.out.println(o);
            }
        }
    }

    private Customer getCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id) return c;
        }
        return null;
    }
}
