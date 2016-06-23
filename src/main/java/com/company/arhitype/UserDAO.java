package com.company.arhitype;

import com.company.arhitype.presistens.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Uzed on 19.06.2016.
 */
public interface UserDAO {
    void save(User user);
    void remove(User user);
    void update(User user);
    User findByName(String name);
    User findById(int id);
    List<User> getAll() throws SQLException;
}
