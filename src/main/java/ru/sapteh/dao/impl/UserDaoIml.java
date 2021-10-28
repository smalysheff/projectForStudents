package ru.sapteh.dao.impl;

import com.mysql.cj.xdevapi.PreparableStatement;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.User;
import ru.sapteh.utils.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoIml implements Dao<User, Integer> {

    private final Connection connection;

    public UserDaoIml() {
        this.connection = Connector.getInstance();
    }


    @Override
    public User findById(Integer id) {
        String query = "SELECT * FROM users WHERE id=?";
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("fisrt_name"),
                        resultSet.getString("last_name")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(
                  new User(
                          resultSet.getInt("id"),
                          resultSet.getString("fisrt_name"),
                          resultSet.getString("last_name")
                  )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
