package com.laioffer.dryer.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnection {
    private Connection conn;
    public MySQLConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(MySQLDBUtil.URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public boolean verifyLogin(String userId, String password) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return false;
        }
        String sql = "SELECT user_id FROM washer_user WHERE user_id = ?  AND password = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean addUser(String userId, String password) {
        if (conn == null) {
            System.err.println("DB connection failed");
            return false;
        }
        String sql = "INSERT IGNORE INTO washer_user VALUES (?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, password);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Washer> getWashers() {
        if (conn == null) {
            return new ArrayList<>();
        }
        List<Washer> list = new ArrayList<>();
        try{
            String sql = "SELECT * FROM washer";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String name = rs.getString("washer_name");
                String id = rs.getString("washer_id");
                list.add(new Washer(name, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
