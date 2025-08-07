package com.FoodDeliverySystem;

import java.sql.*;

public class AdminService {

    public void addRestaurant(int id, String name) {
        String sql = "INSERT INTO Restaurant (id, name) VALUES (?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("Restaurant added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRestaurant(int id, String newName) {
        String sql = "UPDATE Restaurant SET name = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newName);
            pst.setInt(2, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Restaurant updated successfully!");
            else System.out.println("Restaurant not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllRestaurants() {
        String sql = "SELECT * FROM Restaurant";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFoodItem(int id, String name, double price, int restaurantId) {
        String sql = "INSERT INTO FoodItem (id, name, price, restaurant_id) VALUES (?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setDouble(3, price);
            pst.setInt(4, restaurantId);
            pst.executeUpdate();
            System.out.println("Food item added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFoodItem(int id, String newName, double newPrice) {
        String sql = "UPDATE FoodItem SET name = ?, price = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newName);
            pst.setDouble(2, newPrice);
            pst.setInt(3, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Food item updated successfully!");
            else System.out.println("Food item not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFoodItem(int id) {
        String sql = "DELETE FROM FoodItem WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            int rows = pst.executeUpdate();
            if (rows > 0) System.out.println("Food item removed successfully!");
            else System.out.println("Food item not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllFoodItems() {
        String sql = "SELECT f.id, f.name, f.price, r.name AS restaurant " +
                     "FROM FoodItem f JOIN Restaurant r ON f.restaurant_id = r.id";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Price: %.2f | Restaurant: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getString("restaurant"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewRestaurantsAndMenus() {
        String restaurantSQL = "SELECT * FROM Restaurant";
        String foodSQL = "SELECT * FROM FoodItem WHERE restaurant_id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement resStmt = con.prepareStatement(restaurantSQL);
             PreparedStatement foodStmt = con.prepareStatement(foodSQL);
             ResultSet rs = resStmt.executeQuery()) {
            while (rs.next()) {
                int resId = rs.getInt("id");
                String resName = rs.getString("name");
                System.out.println("Restaurant ID: " + resId + ", Name: " + resName);
                foodStmt.setInt(1, resId);
                try (ResultSet frs = foodStmt.executeQuery()) {
                    while (frs.next()) {
                        System.out.printf(" - Food ID: %d, Name: %s, Price: %.2f%n",
                                frs.getInt("id"), frs.getString("name"), frs.getDouble("price"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDeliveryPerson(int id, String name, long contactNo) {
        String sql = "INSERT INTO DeliveryPerson (id, name, contactNo) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setLong(3, contactNo);
            pst.executeUpdate();
            System.out.println("Delivery person added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void assignDeliveryPerson(int orderId, int deliveryPersonId) {
        String sql = "UPDATE Orders SET delivery_person_id = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, deliveryPersonId);
            pst.setInt(2, orderId);
            int rows = pst.executeUpdate();
            if (rows > 0)
                System.out.println("Delivery person assigned to order successfully!");
            else
                System.out.println("Order not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllOrders() {
        String orderSQL = "SELECT o.id AS orderId, c.username AS customerName, " +
                "o.status, o.delivery_address, o.created_at, d.name AS deliveryPerson " +
                "FROM Orders o JOIN Customer c ON o.customer_id = c.id " +
                "LEFT JOIN DeliveryPerson d ON o.delivery_person_id = d.id";

        String itemSQL = "SELECT oi.food_item_id, fi.name, fi.price, oi.quantity " +
                "FROM OrderItems oi JOIN FoodItem fi ON oi.food_item_id = fi.id " +
                "WHERE oi.order_id = ?";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement orderStmt = con.prepareStatement(orderSQL);
             PreparedStatement itemStmt = con.prepareStatement(itemSQL);
             ResultSet orderRS = orderStmt.executeQuery()) {

            while (orderRS.next()) {
                int orderId = orderRS.getInt("orderId");
                String customer = orderRS.getString("customerName");
                String status = orderRS.getString("status");
                String address = orderRS.getString("delivery_address");
                String date = orderRS.getString("created_at");
                String deliveryPerson = orderRS.getString("deliveryPerson");

                System.out.printf("Order ID: %d | Customer: %s | Status: %s | Address: %s | Date: %s | Delivery Person: %s%n",
                        orderId, customer, status, address, date,
                        (deliveryPerson != null ? deliveryPerson : "Not Assigned"));

                itemStmt.setInt(1, orderId);
                try (ResultSet itemRS = itemStmt.executeQuery()) {
                    while (itemRS.next()) {
                        String itemName = itemRS.getString("name");
                        double price = itemRS.getDouble("price");
                        int qty = itemRS.getInt("quantity");
                        System.out.printf(" - %s x%d = Rs. %.2f%n", itemName, qty, price * qty);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
