package meppelink.data_access;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import meppelink.chapter_5.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL implements DAO_MySQL<User> {
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                // Step 1: make a statement (Statement or PreparedStatement)
                Statement statement = connection.createStatement();
                // Step 2: Execute a query (plain SQL or stored procedure) and return the results
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                // Step 3: Get data from the results
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    char[] password = resultSet.getString("password").toCharArray();
                    String status = resultSet.getString("status");
                    User user = new User(id, firstName, lastName, email, phone, password, status);
                    users.add(user);
                }
                resultSet.close();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Get all users failed");
            System.out.println(e.getMessage());
        }
        return users;
    }

    public int add(User user) {
        int numRowsAffected = 0;
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                String sql = "INSERT INTO users (first_name, last_name, email, phone, password, status)" +
                        "VALUES (?,?,?,?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, user.getFirst_name());
                statement.setString(2, user.getLast_name());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getPhone());
                statement.setString(5, new String(user.getPassword()));
                statement.setString(6, user.getStatus());

                numRowsAffected = statement.executeUpdate();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Add user failed");
            System.out.println(e.getMessage());
        }
        return numRowsAffected;
    }

    public int getUserId(User user) {
        int userId = 0;
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, user.getEmail());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            userId = resultSet.getInt("id");
            statement.close();
            resultSet.close();
        } catch(SQLException e) {
            System.out.println("Get user failed");
            System.out.println(e.getMessage());
        }
        return userId;
    }

    public int insertUser2FACode(int userID, int code, String method) {
        int numRowsAffected = 0;
        try(Connection connection = getConnection()) {
            if(connection.isValid(2)) {
                String sql = "INSERT INTO 2fa_codes (user_id, code, method)" +
                        "VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, userID);
                statement.setInt(2, code);
                statement.setString(3, method);

                numRowsAffected = statement.executeUpdate();
                statement.close();
            }
        } catch(SQLException e) {
            System.out.println("Add 2fa code failed");
            System.out.println(e.getMessage());
        }
        return numRowsAffected;
    }
}