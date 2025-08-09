package entities;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private Customer customer;
    private Map<FoodItem, Integer> items;
    private String status;
    private DeliveryPerson deliveryPerson;
    private String deliveryAddress;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new HashMap<>();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }
    public String getStatus() { return status; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public String getDeliveryAddress() { return deliveryAddress; }

    public void setStatus(String status) { this.status = status; }
    public void setDeliveryPerson(DeliveryPerson dp) { this.deliveryPerson = dp; }
    public void setDeliveryAddress(String address) { this.deliveryAddress = address; }

    public void addItem(FoodItem item, int quantity) {
        items.put(item, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer.getUsername() +
                ", items=" + items +
                ", status='" + status + '\'' +
                ", deliveryPerson=" + (deliveryPerson == null ? "Not Assigned" : deliveryPerson.getName()) +
                '}';
    }
}
