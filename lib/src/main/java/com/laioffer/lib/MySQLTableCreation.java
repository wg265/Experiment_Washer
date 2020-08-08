package com.laioffer.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class MySQLTableCreation {

        public static void main(String[] args) {
            try {
                // Step 1 Connect to MySQL.
                System.out.println("Connecting to " + MySQLDBUtil.URL);
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                Connection conn = DriverManager.getConnection(MySQLDBUtil.URL);

                if (conn == null) {
                    return;
                }
                Statement statement = conn.createStatement();
                String sql = "set foreign_key_checks = 0";
                statement.executeUpdate(sql);
                sql = "DROP TABLE IF EXISTS washer_user";
                statement.executeUpdate(sql);

                sql = "CREATE TABLE washer_user ("
                        + "user_id VARCHAR(255) NOT NULL,"
                        + "password VARCHAR(255) NOT NULL,"
                        + "PRIMARY KEY (user_id)"
                        + ")";
                statement.executeUpdate(sql);
                sql = "DROP TABLE IF EXISTS washer";
                statement.executeUpdate(sql);
                sql = "CREATE TABLE washer (" +
                        "washer_id VARCHAR(255) NOT NULL," +
                        "washer_name VARCHAR(255) NOT NULL," +
                        "PRIMARY KEY (washer_id)" +
                        ")";
                statement.executeUpdate(sql);
                sql = "DROP TABLE IF EXISTS reservation";
                statement.executeUpdate(sql);
                sql = "CREATE TABLE reservation (" +
                        "washer_id VARCHAR(255) NOT NULL," +
                        "user_id VARCHAR(255) NOT NULL," +
                        "start_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "end_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                        "PRIMARY KEY (washer_id)," +
                        "FOREIGN KEY (user_id) REFERENCES washer_user(user_id)," +
                        "FOREIGN KEY (washer_id) REFERENCES washer(washer_id)" +
                        ")";
                statement.executeUpdate(sql);
                sql = "set foreign_key_checks = 1";
                statement.executeUpdate(sql);
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

