package com.FoodDeliverySystem;

import java.sql.*;
public class CustomerService {

    public void addCustomer(int id, String name, long contact) {
        String sql = "INSERT INTO Customer (id, username, contactNo) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setLong(3, contact);
            pst.executeUpdate();
            System.out.println("Customer created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFoodItems(String keyword) {
        String sql = "SELECT f.id, f.name, f.price, r.name AS restaurant " +
                     "FROM FoodItem f JOIN Restaurant r ON f.restaurant_id = r.id " +
                     "WHERE f.name LIKE ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Price: %.2f | Restaurant: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getString("restaurant"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToCart(int customerId, int foodId, int qty) {
        String sql = "INSERT INTO Cart (customer_id, food_item_id, quantity) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            pst.setInt(2, foodId);
            pst.setInt(3, qty);
            pst.setInt(4, qty);
            pst.executeUpdate();
            System.out.println("Food item added to cart!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewCart(int customerId) {
        String sql = "SELECT f.name, f.price, c.quantity FROM Cart c " +
                     "JOIN FoodItem f ON c.food_item_id = f.id " +
                     "WHERE c.customer_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            double total = 0;
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");
                double cost = price * qty;
                total += cost;
                System.out.printf("Food: %s | Qty: %d | Cost: Rs. %.2f%n", name, qty, cost);
            }
            System.out.println("Total Cost: Rs. " + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void placeOrder(int customerId, String address) {
        String orderSQL = "INSERT INTO Orders (id, customer_id, status, delivery_address) VALUES (?, ?, 'Pending', ?)";
        String getCartSQL = "SELECT food_item_id, quantity FROM Cart WHERE customer_id = ?";
        String orderItemSQL = "INSERT INTO OrderItems (order_id, food_item_id, quantity) VALUES (?, ?, ?)";
        String clearCartSQL = "DELETE FROM Cart WHERE customer_id = ?";
        try (Connection con = DBUtil.getConnection()) {
            con.setAutoCommit(false);
            int orderId = (int) (System.currentTimeMillis() % 100000);
            try (PreparedStatement orderStmt = con.prepareStatement(orderSQL);
                 PreparedStatement cartStmt = con.prepareStatement(getCartSQL);
                 PreparedStatement orderItemStmt = con.prepareStatement(orderItemSQL);
                 PreparedStatement clearCartStmt = con.prepareStatement(clearCartSQL)) {
                orderStmt.setInt(1, orderId);
                orderStmt.setInt(2, customerId);
                orderStmt.setString(3, address);
                orderStmt.executeUpdate();

                cartStmt.setInt(1, customerId);
                ResultSet rs = cartStmt.executeQuery();
                while (rs.next()) {
                    int foodId = rs.getInt("food_item_id");
                    int qty = rs.getInt("quantity");
                    orderItemStmt.setInt(1, orderId);
                    orderItemStmt.setInt(2, foodId);
                    orderItemStmt.setInt(3, qty);
                    orderItemStmt.executeUpdate();
                }

                clearCartStmt.setInt(1, customerId);
                clearCartStmt.executeUpdate();

                con.commit();
                System.out.println("Order placed! Order ID: " + orderId);
            } catch (Exception ex) {
                con.rollback();
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewOrders(int customerId) {
        String sql = "SELECT o.id, o.status, o.delivery_address, o.created_at, d.name as deliveryPerson " +
                     "FROM Orders o LEFT JOIN DeliveryPerson d ON o.delivery_person_id = d.id " +
                     "WHERE o.customer_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.printf("Order ID: %d | Status: %s | Address: %s | Date: %s | Delivery: %s%n",
                        rs.getInt("id"), rs.getString("status"),
                        rs.getString("delivery_address"), rs.getString("created_at"),
                        rs.getString("deliveryPerson") != null ? rs.getString("deliveryPerson") : "Not Assigned");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelOrder(int orderId, int customerId) {
        String checkSQL = "SELECT status FROM Orders WHERE id = ? AND customer_id = ?";
        String deleteItemsSQL = "DELETE FROM OrderItems WHERE order_id = ?";
        String deleteOrderSQL = "DELETE FROM Orders WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkSQL)) {
            checkStmt.setInt(1, orderId);
            checkStmt.setInt(2, customerId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                if (!rs.getString("status").equalsIgnoreCase("Pending")) {
                    System.out.println("Cannot cancel. Already processed.");
                    return;
                }

                try (PreparedStatement delItems = con.prepareStatement(deleteItemsSQL);
                     PreparedStatement delOrder = con.prepareStatement(deleteOrderSQL)) {
                    delItems.setInt(1, orderId);
                    delOrder.setInt(1, orderId);
                    delItems.executeUpdate();
                    delOrder.executeUpdate();
                    System.out.println("Order cancelled!");
                }
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void leaveReview(int customerId, int foodItemId, int rating, String comment) {
        String sql = "INSERT INTO Review (customer_id, food_item_id, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, customerId);
            pst.setInt(2, foodItemId);
            pst.setInt(3, rating);
            pst.setString(4, comment);
            pst.executeUpdate();
            System.out.println("Thanks for your review!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
