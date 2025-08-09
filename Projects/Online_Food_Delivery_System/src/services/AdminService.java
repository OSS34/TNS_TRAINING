package services;

import entities.*;

import java.util.*;

public class AdminService {
    private List<Restaurant> restaurants;
    private List<DeliveryPerson> deliveryPersons;
    private List<Order> orders;

    public AdminService(List<Restaurant> restaurants, List<DeliveryPerson> deliveryPersons, List<Order> orders) {
        this.restaurants = restaurants;
        this.deliveryPersons = deliveryPersons;
        this.orders = orders;
    }

    public void addRestaurant(int id, String name) {
        restaurants.add(new Restaurant(id, name));
        System.out.println("Restaurant added successfully!");
    }

    public void addFoodItemToRestaurant(int restaurantId, FoodItem item) {
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurantId) {
                r.addFoodItem(item);
                System.out.println("Food item added successfully!");
                return;
            }
        }
        System.out.println("Restaurant not found!");
    }

    public void removeFoodItemFromRestaurant(int restaurantId, int foodId) {
        for (Restaurant r : restaurants) {
            if (r.getId() == restaurantId) {
                r.removeFoodItem(foodId);
                System.out.println("Food item removed successfully!");
                return;
            }
        }
        System.out.println("Restaurant not found!");
    }

    public void viewRestaurantsAndMenus() {
        System.out.println("Restaurants and Menus:");
        for (Restaurant r : restaurants) {
            System.out.println("Restaurant ID: " + r.getId() + ", Name: " + r.getName());
            for (FoodItem f : r.getMenu()) {
                System.out.println("- Food Item ID: " + f.getId() + ", Name: " + f.getName() + ", Price: Rs. " + f.getPrice());
            }
        }
    }

    public void viewOrders() {
        System.out.println("Orders:");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public void addDeliveryPerson(int id, String name, long contact) {
        deliveryPersons.add(new DeliveryPerson(id, name, contact));
        System.out.println("Delivery person added successfully!");
    }

    public void assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        Order order = null;
        for (Order o : orders) {
            if (o.getOrderId() == orderId) {
                order = o;
                break;
            }
        }
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        for (DeliveryPerson dp : deliveryPersons) {
            if (dp.getDeliveryPersonId() == deliveryPersonId) {
                order.setDeliveryPerson(dp);
                System.out.println("Delivery person assigned to order successfully!");
                return;
            }
        }
        System.out.println("Delivery person not found!");
    }
}
